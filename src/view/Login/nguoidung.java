/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.Login;

/**
 *
 * @author doqua
 */
public class nguoidung {
    private String username, pass, roleuser;

    public nguoidung() {
    }

    public nguoidung(String username, String pass, String roleuser) {
        this.username = username;
        this.pass = pass;
        this.roleuser = roleuser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getRoleuser() {
        return roleuser;
    }

    public void setRoleuser(String roleuser) {
        this.roleuser = roleuser;
    }
    
}
