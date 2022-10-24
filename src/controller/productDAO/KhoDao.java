/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.productDAO;

import entity.product.Kho;
import model.DatabaseHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Acer
 */
public class KhoDao {
        public List<Kho> select() {
        String sql = "select * from KhoHang";
        return select(sql);
    } 

    private List<Kho> select(String sql, Object... args) {
        List<Kho> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = DatabaseHelper.executeQuery(sql, args);
                while (rs.next()) {
                    Kho model = readFromResultSet(rs);
                    list.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }

    private Kho readFromResultSet(ResultSet rs) throws SQLException {
        Kho model = new Kho();
        model.setMaKho(rs.getString("MaKho"));
        model.setTenKho(rs.getString("TenKho"));
        model.setKhuVuc(rs.getString("MaNV"));
        model.setManv(rs.getString("MaDiaDiem"));

        return model;
    }
}
