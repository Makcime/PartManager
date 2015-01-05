/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beanPackage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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

	@Resource(name = "coin")
	private DataSource coin;

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
		con = null;
		try {
			con = coin.getConnection();
			Tools.deleteProject(con, id);
			init();
		} catch(SQLException ex) {
			Logger.getLogger(project.class.getName()).
			    log(Level.SEVERE, null, ex);
		}
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
