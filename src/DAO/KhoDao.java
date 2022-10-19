/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Helper.JdcbHelper;
import Model.Kho;
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
        String sql = "select * from QLKho";
        return select(sql);
    }
   
    
//     public void insert(SanPham model) {
//        String sql = "INSERT INTO SANPHAM (MASP, MaKho, MaLoai,TENSP, GIA, KICHTHUOC, DonViTinh, dateSX, dateHH, KhuLuuTru )"
//                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
//                + "";
//        JdcbHelper.executeUpdate(sql,model.getMaSP(),model.getLoaiSP() , model.getTenSp(), model.getGia(), model.getKichThuoc(), model.getDVT(),model.getNSX(),model.getNHH(),model.getKhu());
//    }


    
    

    private List<Kho> select(String sql, Object... args) {
        List<Kho> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdcbHelper.executeQuery(sql, args);
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
        model.setKhuVuc(rs.getString("KhuVuc"));
        model.setManv(rs.getString("MaNV"));

        return model;
    }
}
