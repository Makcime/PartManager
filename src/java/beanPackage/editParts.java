/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beanPackage;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class editParts implements Serializable{

    @Resource(name = "coin")
    private DataSource coin;

    private List<part> parts;
    private List<String> categories, suppliers;

    private Connection con = null;

    /**
     * Creates a new instance of editParts
     */
    public editParts() {
    }

    @PostConstruct
    public  void init(){    
            this.parts = new ArrayList<part>();
            HttpSession session=
                    (HttpSession)
                    FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            con=(Connection)session.getAttribute("con");
            this.parts = Tools.listParts(con);
            categories = Tools.listNames(con, "Category");
            suppliers = Tools.listNames(con, "Supplier");
    }
 	
 	public void onRowEdit(RowEditEvent event) {
            part p = ((part) event.getObject());
            boolean ok;
            FacesMessage msg = null;
            
            ok = Tools.editPart(con , p);
            if(ok)
	        msg = new FacesMessage("Part Edited", p.getName());
	    else
	        msg = new FacesMessage("Edit Cancelled - error during database update", p.getName());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", ((part) event.getObject()).getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public List<part> getParts() {
        return parts;
    }

    public void setParts(List<part> parts) {
        this.parts = parts;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public List<String> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<String> suppliers) {
        this.suppliers = suppliers;
    }
    
    
    
    
          


}
