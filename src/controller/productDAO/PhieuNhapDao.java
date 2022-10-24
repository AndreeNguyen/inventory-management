/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.productDAO;

import model.DatabaseHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author AQ
 */
public class PhieuNhapDao {
    public List<Object[]> getBangCT(String ma) {
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                String sql = "{call CTPN (?)}";
                rs = DatabaseHelper.executeQuery(sql, ma);
                
                while (rs.next()) {
                    Object[] model = {
                        rs.getString("TenLoai"),
                        rs.getString("TenSP"),
                        rs.getString("SoLuong"),
                        
                    };
                    list.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        return list;
    }
}
