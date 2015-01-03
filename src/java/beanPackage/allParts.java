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

   
    
    private List<part> parts;;
    
    /**
     * Creates a new instance of allParts
     */
    public allParts() {}
   
    @PostConstruct
    public  void init(){    
            Connection con = null;
            this.parts = new ArrayList<part>();
            try {
                con = coin.getConnection();
                ResultSet rs = selectAll(con, "Part");
                while (rs.next()){
                    part p = new part();
                    System.out.println("next");
                    p.setId(rs.getInt("id"));
                    p.setName(rs.getString("name"));
                    p.setValue(rs.getString("value"));
                    p.setCategory(Tools.findCat(con, rs.getInt("cat_id")));
                    p.setSupplier(Tools.findSup(con, rs.getInt("sup_id")));
                    p.setSup_ref(rs.getString("sup_ref"));
                    p.setInStock(rs.getInt("in_stock"));
                    p.setUnit_price(rs.getDouble("unit_price"));
                    parts.add(p);
                }   
            } catch (SQLException ex) {
                Logger.getLogger(allParts.class.getName()).log(Level.SEVERE, null, ex);
            }
            
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
