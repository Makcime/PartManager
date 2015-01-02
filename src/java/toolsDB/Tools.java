/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toolsDB;

import beanPackage.user;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author max
 */
public class Tools {

    public static boolean verifyLogin( Connection con, String login, String passwd){
        Statement stmt=null;
        System.out.println("In fct verifyLOGIN...");
        try {
            stmt = con.createStatement();
            login="'"+login+"'";
            passwd="'"+passwd+"'";
            ResultSet rs=stmt.executeQuery("SELECT * "
                     + "FROM User WHERE login="+login
                     +" AND passwd="+passwd);
            boolean ok=rs.next();
            System.out.println(ok);
            return ok;
            // if(ok)  return true;

        } catch (SQLException ex) {ex.printStackTrace(); }

       return false;
    }

    public static user findUser(Connection con, String login){
        user u=new user();
        try {
        ResultSet rs= selectFromWhere(con, "*", "User", "login=\""+login+"\"");
        rs.next();
        u.setLogin(login);
        u.setId(rs.getInt("id"));
        u.setName(rs.getString("name"));
        u.setFirst_name(rs.getString("first_name"));
        u.setCp(rs.getString("cp"));
          } catch (SQLException ex) {
                     System.out.println(ex);

        }
        return u;
    }

    public static ResultSet selectFromWhere(Connection con, String column, String table, String condition){
        PreparedStatement prep;
        ResultSet rs = null;
        try {
        System.out.println(condition);
        prep = con.prepareStatement("Select " + column + " from " + table + " where " + condition);
        rs=prep.executeQuery();
          } catch (SQLException ex) {
                     System.out.println(ex);
        }
        return rs;
    }


}
