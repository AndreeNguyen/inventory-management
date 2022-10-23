/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.productDAO;


import entity.product.LichSuGiaoDich;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.DatabaseHelper;

/**
 *
 * @author My PC
 */
public class LichSuDAO {
    final String selectLS = "{call sp_lichsugd(?)}";
    final String selectLSno = "{call sp_lichsugdno(?)}";
    

    public LichSuGiaoDich selectById(String keytype) {
        List<LichSuGiaoDich> list = selectBySql(selectLS, keytype);
        if (list.isEmpty()) {
            return null;//rong
        }
        return list.get(0);
    }

    public List<LichSuGiaoDich> selectBySql(String sql, Object... args) {
        List<LichSuGiaoDich> list = new ArrayList<>();
        try {
            ResultSet rs = DatabaseHelper.executeQuery(sql, args);
            while (rs.next()) {
                LichSuGiaoDich entity = new LichSuGiaoDich();
                entity.setMaPhieu(rs.getString(1));
                entity.setTien(rs.getFloat(2));
                entity.setNgayDat(rs.getDate(3));
                entity.setTenNV(rs.getString(4));
                entity.setTrangThai(rs.getByte(5));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
