/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedbeanPackage;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import toolsDB.Tools;

/**
 *
 * @author max
 */
public class projects {

	private String userName;
	private List < project > projectList;

    /**
     * Creates a new instance of projects
     */
	public projects() {
	} @PostConstruct public void postConstuct() {
		init();
	}

	public String init() {
		HttpSession session = (HttpSession)
		    FacesContext.getCurrentInstance().getExternalContext().
		    getSession(true);
		Connection con = null;
		con = (Connection) session.getAttribute("con");
		this.projectList = new ArrayList < project > ();
		Integer userId = (Integer) session.getAttribute("userId");
		userName = Tools.findName(con, "User", userId);
		projectList = Tools.listProjects(con, userId);
		return "back";

	}

	public void delete(Integer id) {
            Connection con;
            HttpSession session = (HttpSession)
                    FacesContext.getCurrentInstance().getExternalContext().
                            getSession(true);
            con = (Connection) session.getAttribute("con");
            Tools.deleteProject(con, id);
            init();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List < project > getProjectList() {
		return projectList;
	}

	public void setProjectList(List < project > projectList) {
		this.projectList = projectList;
	}
}
