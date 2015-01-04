/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toolsDB;

import beanPackage.part;
import beanPackage.user;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author max
 */
public class Tools {

    public static Integer verifyLogin( Connection con, String login, String passwd){
        Statement stmt=null;
        System.out.println("In fct verifyLOGIN...");
        try {
            stmt = con.createStatement();
            login="'"+login+"'";
            passwd="'"+passwd+"'";
            ResultSet rs=stmt.executeQuery("SELECT * "
                     + "FROM User WHERE login="+login
                     +" AND passwd="+passwd);
            if(rs.next())
                return rs.getInt("id");

        } catch (SQLException ex) {ex.printStackTrace(); }

       return 0;
    }

    public static void insertUser(Connection con, user u)
    {
        try {
            PreparedStatement ps=con.prepareStatement("Insert into User(name, first_name, login, passwd, cp) values(?,?,?,?,?)");
            ps.setString(1,u.getName());
            ps.setString(2,u.getFirst_name());
            ps.setString(3, u.getLogin());
            ps.setString(4, u.getPasswd());
            ps.setString(5, u.getCp());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    public static void newProject(Connection con, String name, String description)
    {
        HttpSession session=
              (HttpSession)
              FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            Integer userId = (Integer)session.getAttribute("userId");
        try {
            PreparedStatement ps=con.prepareStatement("Insert into Project(name, description, user_id) values(?,?,?)");
            ps.setString(1,name);
            ps.setString(2,description);
            ps.setInt(3, userId);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ex);
        }
    
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

    public static String findName(Connection con, String table, Integer id){
        String name = null;
        try {
        ResultSet rs= selectFromWhere(con, "*", table, "Id="+id.toString());
        rs.next();
        name = rs.getString("name");
          } catch (SQLException ex) {
                     System.out.println(ex);

        }
        return name;
    }

    public static String findCat(Connection con, Integer id){
        String name = null;
        name = findName(con, "Category", id);
        return name;
    }

    public static List<String> listNames(Connection con, String table){
        List<String> l = new ArrayList<String>();
        try {
            ResultSet rs= selectAll(con, table);
            while(rs.next()){
                l.add(rs.getString("name"));

            }
          } catch (SQLException ex) {
                     System.out.println(ex);
        }
        return l;
    }
    
    public static String findSup(Connection con, Integer id){
        String name = null;
        try {
        ResultSet rs= selectFromWhere(con, "*", "Supplier", "Id=\""+id.toString()+"\"");
        rs.next();
        name = rs.getString("name");
          } catch (SQLException ex) {
                     System.out.println(ex);

        }
        return name;
    }

    public static ResultSet selectFromWhere(Connection con, String column, String table, String condition){
        PreparedStatement prep;
        ResultSet rs = null;
        try {
        prep = con.prepareStatement("Select " + column + " from " + table + " where " + condition);
        rs=prep.executeQuery();
          } catch (SQLException ex) {
                     System.out.println(ex);
        }
        return rs;
    }

    public static ResultSet selectAll(Connection con, String table){
        PreparedStatement prep;
        ResultSet rs = null;
        try {
        prep = con.prepareStatement("Select * from " + table);
        rs=prep.executeQuery();
        System.out.println("ds Select all");

          } catch (SQLException ex) {
                     System.out.println(ex);
        }
        return rs;
    }
    
    public static String findId(Connection con, String table, String name){
        PreparedStatement prep;
        ResultSet rs = null;
        String id = null;
        try {
        prep = con.prepareStatement("Select * from " + table + " where name=\"" + name + "\"");
        rs=prep.executeQuery();
        rs.next();
        id =rs.getString("id");
          } catch (SQLException ex) {
                     System.out.println(ex);
        }
        return id;
    }

    public static boolean editPart(Connection con, part p) {
        PreparedStatement prep;
        String query;
        boolean ok = false;
        try {
        query = "Update Part SET";
        query+= " name='" + p.getName() +"'";
        query+= ", value='" + p.getValue() +"'";
        if(p.getSup_ref() != p.getNew_sup_ref())
            query+= ", sup_ref='" + p.getNew_sup_ref()+"'";
        query+= ", cat_id=" + findId(con, "Category", p.getCategory());
        query+= ", sup_id=" + findId(con, "Supplier", p.getSupplier());
        query+= " where id="+ p.getId().toString();
        prep = con.prepareStatement(query);
        if(prep.executeUpdate() >  0)
            ok = true;
          } catch (SQLException ex) {
                     System.out.println(ex);
        }
        return ok;
        }

    public static void deleteProject(Connection con, Integer id) {
        PreparedStatement prep;
        try {
            prep = con.prepareStatement("DELETE FROM Project WHERE id="+id.toString());
            prep.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }



}
