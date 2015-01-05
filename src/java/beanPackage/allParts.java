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
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import toolsDB.Tools;
import static toolsDB.Tools.selectAll;

/**
 *
 * @author max
 */
public class allParts implements Serializable{
    @Resource(name = "coin")
    private DataSource coin;
    private List<part> parts;
    
    /**
     * Creates a new instance of allParts
     */
    public allParts() {}
   
    @PostConstruct
    public  void init(){    
            Connection con = null;
            this.parts = new ArrayList<part>();
            HttpSession session=
                    (HttpSession)
                    FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            con=(Connection)session.getAttribute("con");
            this.parts = Tools.listParts(con);            
    }

    public String test(){
        Connection con = null;
        String test = "fail";
        try {
            con = coin.getConnection();
        
                ResultSet rs = selectAll(con, "Part");
                while(rs.next())
                    test += rs.getString("name");
                } catch (SQLException ex) {
            Logger.getLogger(allParts.class.getName()).log(Level.SEVERE, null, ex);
        }
                return test;

    }

    public List<part> getParts() {
        return parts;
    }

    public void setParts(List<part> parts) {
        this.parts = parts;
    }
}
