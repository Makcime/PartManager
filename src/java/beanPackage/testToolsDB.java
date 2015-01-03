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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.sql.DataSource;
import toolsDB.Tools;

/**
 *
 * @author max
 */
@ManagedBean
@RequestScoped
public class testToolsDB {
    @Resource(name = "coin")
    private DataSource coin;

    
    
    private user u;
    private String login;
    private String def = "admin";
       

    /**
     * Creates a new instance of testToolsDB
     */
    public testToolsDB() {}
    
    public String find(){
    	Connection con;
        String name = "fail"  ;
       try {
           con = coin.getConnection();
            u = Tools.findUser(con, "admin");
            name = u.getName();
            System.out.println(u.getFirst_name());
       } catch (SQLException ex) {
           Logger.getLogger(testToolsDB.class.getName()).log(Level.SEVERE, null, ex);
       }
       
       return name;
   	}
    
    public String foo(){
        return "Hakuna matata";
    }

    public user getU() {
        return u;
    }

    public void setU(user u) {
        this.u = u;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getDef() {
        return def;
    }

    public void setDef(String def) {
        this.def = def;
    }
    
    
    
}
