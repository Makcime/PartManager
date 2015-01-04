/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beanPackage;

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

/**
 *
 * @author max
 */
public class projects {
 
    @Resource(name = "coin")
    private DataSource coin;

    private String userName;
    private List<project> projectList;

    /**
     * Creates a new instance of projects
     */
    public projects() {
    }

    @PostConstruct
    public  void init(){    
            Connection con = null;
            this.projectList = new ArrayList<project>();
            try {
                con = coin.getConnection();
                userName = Tools.findName(con,"User" ,1);
                ResultSet rs = Tools.selectFromWhere(con,"*", "Project", "user_id=1");
                while (rs.next()){
                    project p = new project();
                    p.setId(rs.getInt("id"));
                    p.setName(rs.getString("name"));
                    p.setDescription(rs.getString("description"));
                    p.setUser(Tools.findName(con,"User" ,rs.getInt("user_id")));
                    projectList.add(p);
                }   
            } catch (SQLException ex) {
                Logger.getLogger(projects.class.getName()).log(Level.SEVERE, null, ex);
            }
            
    }
    
    public void delete(Integer id){
        Connection con;
        con = null;
        try {
            con = coin.getConnection();
            Tools.deleteProject(con, id);
            init();
        } catch (SQLException ex) {
            Logger.getLogger(project.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<project> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<project> projectList) {
        this.projectList = projectList;
    }
 }
