/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedbeanPackage;

import java.io.Serializable;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.primefaces.event.RowEditEvent;
import toolsDB.Tools;

/**
 *
 * @author max
 */
public class editParts implements Serializable {
	private List < part > parts, partsToUpdate;
	private List < String > categories, suppliers;
	private Connection con = null;
	private HttpSession session;

    /**
     * Creates a new instance of editParts
     */
	public editParts() {
	} @PostConstruct public void postConstuct() {
		init();
	}

	public String init() {
		this.parts = new ArrayList < part > ();
		session = (HttpSession)
		    FacesContext.getCurrentInstance().getExternalContext().
		    getSession(true);
		con = (Connection) session.getAttribute("con");
		this.parts = Tools.listParts(con);
		categories = Tools.listNames(con, "Category");
		suppliers = Tools.listNames(con, "Supplier");
		return "back";
	}

	public void onRowEdit(RowEditEvent event) {
		part p = ((part) event.getObject());
		boolean ok = true;
		FacesMessage msg = null;
		partsToUpdate =
		    (List < part >) session.getAttribute("partsToUpdate");
		if (partsToUpdate == null) {
			partsToUpdate = new ArrayList < part > ();
		}
		partsToUpdate.add(p);
		session.setAttribute("partsToUpdate", partsToUpdate);
//            ok = Tools.editPart(con , p);

		if (ok)
			msg =
			    new
			    FacesMessage
			    ("Component added to the update cart",
			     p.toString());
		else
			msg =
			    new
			    FacesMessage
			    ("Edit Cancelled - error during database update",
			     p.getName());

		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg =
		    new FacesMessage("Edit Cancelled",
				     ((part) event.getObject()).getName());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String showCart() {
		partsToUpdate =
		    (List < part >) session.getAttribute("partsToUpdate");
		return "cart";
	}
	public void update() {
		FacesMessage msg = null;
		partsToUpdate =
		    (List < part >) session.getAttribute("partsToUpdate");
		Integer updates = 0;
		if (partsToUpdate != null)
		      for (part P:partsToUpdate) {
				if (Tools.editPart(con, P))
					updates++;
			}
		if (updates == partsToUpdate.size())
			msg = new FacesMessage("Update success", "");
		else
			msg =
			    new
			    FacesMessage
			    ("One or more lines of the update failed", "");
		FacesContext.getCurrentInstance().addMessage(null, msg);

		init();
		partsToUpdate = null;
		session.removeAttribute("partsToUpdate");

	}

	public List < part > getParts() {
		return parts;
	}

	public void setParts(List < part > parts) {
		this.parts = parts;
	}

	public List < String > getCategories() {
		return categories;
	}

	public void setCategories(List < String > categories) {
		this.categories = categories;
	}

	public List < String > getSuppliers() {
		return suppliers;
	}

	public void setSuppliers(List < String > suppliers) {
		this.suppliers = suppliers;
	}

	public List < part > getPartsToUpdate() {
		return partsToUpdate;
	}

	public void setPartsToUpdate(List < part > partsToUpdate) {
		this.partsToUpdate = partsToUpdate;
	}
}
