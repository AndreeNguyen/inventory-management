/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.Login;

import model.DatabaseHelper;
import com.edusys.model.DatabaseHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author doqua
 */
public class nguoidungDao {
     public nguoidung checkLogin(String username, String pass) throws Exception {
        String sql = "select * from Nhanvien where username = ? and pass = ?";
        try (
                 Connection con = DatabaseHelper.oppenConnection();  PreparedStatement psmt = con.prepareStatement(sql);) {
            psmt.setString(1, username);
            psmt.setString(2, pass);

            try ( ResultSet rs = psmt.executeQuery();) {
                if (rs.next()) {
                    nguoidung nd = new nguoidung();
                    nd.setUsername(username);
                  
                    nd.setRoleuser(rs.getString("Roleuser"));
                    return nd;
                }
            }
        }
        return null;
    }
}
