/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.QLKH.controller.productDAO;


import com.QLKH.entity.product.NhaCungCap;
import com.QLKH.model.DatabaseHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Acer
 */
public class NhaCungCapDao {

        public List<NhaCungCap> select() {
            String sql = "select * from NhaCungCap";
            return select(sql);
        }

//     public void insert(SanPham model) {
//        String sql = "INSERT INTO SANPHAM (MASP, MaKho, MaLoai,TENSP, GIA, KICHTHUOC, DonViTinh, dateSX, dateHH, KhuLuuTru )"
//                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
//                + "";
//        JdcbHelper.executeUpdate(sql,model.getMaSP(),model.getLoaiSP() , model.getTenSp(), model.getGia(), model.getKichThuoc(), model.getDVT(),model.getNSX(),model.getNHH(),model.getKhu());
//    }
        private List<NhaCungCap> select(String sql, Object... args) {
            List<NhaCungCap> list = new ArrayList<>();
            try {
                ResultSet rs = null;
                try {
                    rs = DatabaseHelper.executeQuery(sql, args);
                    while (rs.next()) {
                        NhaCungCap model = readFromResultSet(rs);
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

        private NhaCungCap readFromResultSet(ResultSet rs) throws SQLException {
            NhaCungCap model = new NhaCungCap();
            model.setMaNCC(rs.getString("MaNCC"));
            model.setTenNCC(rs.getString("TenNCC"));
            model.setSDT(rs.getString("SDT"));
            model.setEmail(rs.getString("Email"));
            model.setDiaChi(rs.getString("DiaChi"));
            model.setMaSoThue(rs.getString("MaSoThue"));
            return model;
        }
    }

