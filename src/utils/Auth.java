/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import com.edusys.entity.NhanVien;

/**
 *
 * @author My PC
 */
public class Auth {
    //đối tượng chưa thông tin đăng nhập
    public static NhanVien user ;
    
    public static void clear(){
        Auth.user = null;
    }
    
    public static boolean isLogin(){
        return Auth.user != null;
    }
    
    public static boolean isManager(){
        boolean role = false;
        if (Auth.user.getRole() == 1) {
            role = true;
        }
        return Auth.isLogin() && role;
    }
}
