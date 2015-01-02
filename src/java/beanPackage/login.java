/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beanPackage;

/**
 *
 * @author max
 */
public class login {
    
   private String pseudo, passwd;
   
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

    public void verifyLogin(){
    }
    
}
