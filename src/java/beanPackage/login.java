/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beanPackage;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.sql.DataSource;
import toolsDB.Tools;

import javax.faces.application.FacesMessage;
import javax.servlet.http.HttpSession;
/**
 *
 * @author max
 */
public class login {

    @Resource(name = "coin")
    private DataSource coin;

    
   private String pseudo, passwd;
   private Integer userId;
   
       /**
     * Creates a new instance of login
     */
    public login() {
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String verifyLogin(){
        Connection con;
        Integer userId = 0;
//            con = coin.getConnection();
      HttpSession session=
              (HttpSession)
              FacesContext.getCurrentInstance().getExternalContext().getSession(true);
      con=(Connection)session.getAttribute("con");
      if(con==null){
            try {
                con=coin.getConnection();
                session.setAttribute("con", con);
            } catch (SQLException ex) {
                Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
            }
      }
            userId = Tools.verifyLogin(con, pseudo, passwd);
            session.setAttribute("userId", userId);
            addMessage("User Id = "+userId.toString(), "Data saved");
            if (userId != 0)
                return "index";
            else
                return "idem";
    }

    private void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public String logout(){
        HttpSession session = (HttpSession)
        FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.invalidate();
        return "logout";
    }
    
    public Connection getSessionConnection(){
      HttpSession session=
              (HttpSession)
              FacesContext.getCurrentInstance().getExternalContext().getSession(true);
      Connection con=(Connection)session.getAttribute("con");
      if(con==null){
            try {
                con=coin.getConnection();
                session.setAttribute("con", con);
            } catch (SQLException ex) {
                Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
            }
      }

        return con;
    }
    
}
