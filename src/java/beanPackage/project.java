/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beanPackage;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import toolsDB.Tools;

/**
 *
 * @author max
 */
public class project {

    @Resource(name = "coin")
    private DataSource coin;
    
    private String name, description, user;
    private Integer id;

    public project() {
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public void delete(){
        System.out.println("id du projet :"+id);
        Connection con;
        con = null;
        HttpSession session = (HttpSession)
                FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        con=(Connection)session.getAttribute("con");
        Tools.deleteProject(con, id);
    }
    public String edit(){
        return "edit";
    }
}
