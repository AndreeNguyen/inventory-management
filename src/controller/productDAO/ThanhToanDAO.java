/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.productDAO;

import entity.product.ThanhToan;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.DatabaseHelper;

/**
 *
 * @author My PC
 */
public class ThanhToanDAO {
    public List<ThanhToan> selectBySql(String sql, Object... args) {
        List<ThanhToan> list = new ArrayList<>();
        try {
            ResultSet rs = DatabaseHelper.executeQuery(sql, args);
            while (rs.next()) {
                ThanhToan entity = new ThanhToan();
                entity.setMaPhieu(rs.getString(1));
                entity.setCongTy(rs.getString(2));
                entity.setSdt(rs.getString(3));
                entity.setTongTien(rs.getFloat(4));
                entity.setThanhToan(rs.getFloat(5));
                entity.setConLai(rs.getFloat(6));
                entity.setNgayTao(rs.getDate(7));
                entity.setPhuongThuc(rs.getString(8));
                entity.setGhiChu(rs.getString(9));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
