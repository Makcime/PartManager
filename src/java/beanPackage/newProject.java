/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beanPackage;

import java.sql.Connection;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import toolsDB.Tools;

/**
 *
 * @author max
 */
public class newProject {
    private String name, description;
    /**
     * Creates a new instance of newProject
     */
    public newProject() {
    }
    
    public String save(){
        HttpSession session=
        (HttpSession)
        FacesContext.getCurrentInstance().getExternalContext().getSession(true);
      Connection con = null;
      con=(Connection)session.getAttribute("con");
        Tools.newProject(con, name, description);
//        projects.init()
        return "back";
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
    
    
    
}
