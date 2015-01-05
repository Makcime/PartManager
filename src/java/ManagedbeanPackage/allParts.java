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
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import toolsDB.Tools;

/**
 *
 * @author max
 */
public class allParts implements Serializable {
	private List < part > parts;

    /**
     * Creates a new instance of allParts
     */
	public allParts() {
	} @PostConstruct public void postConstuct() {
		init();
	}

	public String init() {
		Connection con = null;
		this.parts = new ArrayList < part > ();
		HttpSession session = (HttpSession)
		    FacesContext.getCurrentInstance().getExternalContext().
		    getSession(true);
		con = (Connection) session.getAttribute("con");
		this.parts = Tools.listParts(con);
		return "back";
	}

	public List < part > getParts() {
		return parts;
	}

	public void setParts(List < part > parts) {
		this.parts = parts;
	}
}
