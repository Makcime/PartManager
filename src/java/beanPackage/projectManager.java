/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beanPackage;

import java.sql.Connection;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import toolsDB.Tools;

/**
 *
 * @author max
 */
public class projectManager {

	private project p;
	private String projectId, name, description, user;

    /**
     * Creates a new instance of projectManager
     */
	public projectManager() {
	} @PostConstruct public void init() {
		HttpSession session = (HttpSession)
		    FacesContext.getCurrentInstance().getExternalContext().
		    getSession(true);
		Connection con = null;
		con = (Connection) session.getAttribute("con");
		getId();
		System.out.println("projectId" + projectId);
		p = Tools.findProject(con, projectId);
		name = p.getName();
		description = p.getDescription();
		user = p.getUser();
	}

	public String getId() {
		FacesContext context = FacesContext.getCurrentInstance();
		projectId =
		    context.getExternalContext().getRequestParameterMap().
		    get("projectId");
		return projectId;
	}

	public project getP() {
		return p;
	}

	public void setP(project p) {
		this.p = p;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}



}
