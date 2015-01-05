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
public class signUp {

	@Resource(name = "coin")
	private DataSource coin;

	private String name, first_name, login, cp, passwd;
	private user u;

    /**
     * Creates a new instance of signUp
     */
	public signUp() {
	} public void save() {
            Connection con;
            u = new user();
            con = getSessionConnection();
            u.setCp(cp);
            u.setFirst_name(first_name);
            u.setLogin(login);
            u.setName(name);
            u.setPasswd(passwd);
            Tools.insertUser(con, u);
	}

	public user getU() {
		return u;
	}

	public void setU(user u) {
		this.u = u;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
        
        public Connection getSessionConnection() {
		HttpSession session = (HttpSession)
		    FacesContext.getCurrentInstance().getExternalContext().
		    getSession(true);
		Connection con = (Connection) session.getAttribute("con");
		if (con == null) {
			try {
				con = coin.getConnection();
				session.setAttribute("con", con);
			}
			catch(SQLException ex) {
				Logger.getLogger(login.class.getName()).
				    log(Level.SEVERE, null, ex);
			}
		}

		return con;
	}
}
