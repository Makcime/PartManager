/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toolsDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author max
 */
public class Tools {
    
    public static boolean verifyLogin( Connection con, String login, 
                                       String passwd){
        Statement stmt=null;
        try {
            stmt = con.createStatement();
            login="'"+login+"'";
            passwd="'"+passwd+"'";
            ResultSet rs=stmt.executeQuery("Select * "
                     + "from User where login="+login
                     +" and passwd="+passwd);
            boolean ok=rs.next();
            System.out.println(ok);
            if(ok)  return true;

        } catch (SQLException ex) {ex.printStackTrace(); }

      return false;
    }
}
