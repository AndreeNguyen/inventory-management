/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.productDAO;
import java.sql.*;
import java.util.List;
import model.DatabaseHelper;
import java.util.ArrayList;

/**
 *
 * @author AQ
 */
public class PhieuDatDao {
    
//    public List<ETTPhieuDat> select(){
//        String sql = "Select phieudat.MaPD, PD_ThoiGian, PD_TrangThai, MaKho, NguoiDat, TenLoai, TenSP, SoLuong\n" +
//"	from phieudat join SanPhamDat on phieudat.MaPD = SanPhamDat.MaPD \n" +
//"	join SanPham on SanPhamDat.MaSP = SanPham.MaSP \n" +
//"	join LoaiHang on SanPham.MaLoai = LoaiHang.MaLoai";
//        return select(sql);
//    }
    
//    private List<ETTPhieuDat> select(String sql, Object... args) {
//        List<ETTPhieuDat> list = new ArrayList<>();
//        try {
//            ResultSet rs = null;
//            try {
//                rs = jdcbHelper.executeQuery(sql, args);
//                while (rs.next()) {
//                    ETTPhieuDat model = readFromResultSet(rs);
//                    list.add(model);
//                }
//            } finally {
//                rs.getStatement().getConnection().close();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return list;
//
//    }
    
//    private ETTPhieuDat readFromResultSet(ResultSet rs) throws SQLException {
//        ETTPhieuDat model = new ETTPhieuDat();
//        model.setMaPD(rs.getString("TenSP"));
//        model.setNguoiDat(rs.getString("TenLH"));
//        model.setPD_ThoiGian(rs.getInt("SoLuong"));
//        model.setPD_TrangThai(rs.getFloat("Gia"));
//        model.setDVT(rs.getString("DonViTinh"));
//        return model;
//    }
    
    public List<Object[]> getBangCT(String ma) {
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                String sql = "{call CTPD (?)}";
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
