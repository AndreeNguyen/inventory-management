/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.SanPham;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import Helper.JdcbHelper;

/**
 *
 * @author Acer
 */
public class SanPhamDao {

//    public void insert(SanPham model) {
//        String sql = "INSERT INTO SANPHAM() VALUES (?, ?, ?, ?, ?, ?)";
//        JdcbHelper.executeUpdate(sql, );
//    }
    public List<SanPham> select() {
        String sql = """
                     select SANPHAM.MaSP, TENSP, TenLoai, SoLuong, Gia, DonVi,TenKhu,Khohang.MaKho from SanPham 
                                          inner join LoaiHang on SanPham.MALOAI = LOAIHANG.MALOAI
                                          inner join SoLuongSP on SANPHAM.MASP = SoLuongSP.MASP
                     			  inner join Khu on Khu.MaLoai = LoaiHang.MaLoai
                                          inner join KhoHang on Khu.MaKho = KhoHang.MaKho
                     """;
        return select(sql);
    }

    public void insert(SanPham model) {
        String sql = "INSERT INTO SANPHAM (MASP, TENSP, GIA, DonVi, NSX, NHH, KhuLuuTru )"
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
                + "";
        JdcbHelper.executeUpdate(sql, model.getMaSP(), model.getMaKho(), model.getLoaiSP(), model.getTenSp(), model.getGia(), model.getDonVi(), model.getNSX(), model.getNHH(), model.getKhu());
    }

    public void Update(SanPham model) {
        String sql = "UPDATE SanPham SET MASP=?, TenSP=?, Gia=?, DonVi=?, MaLoai=?, MaNCC=?, NSX=?, NHH=?  WHERE MaSP=?";
        JdcbHelper.executeUpdate(sql, model.getMaSP(), model.getTenSp(), model.getGia(), model.getDonVi(), model.getLoaiSP(), model.getNhaCC(), model.getNSX(), model.getNHH(), model.getMaSP());
    }

    public SanPham findByName(String MaSP, String MaKho) {
        String sql = """
                     select SANPHAM.MaSP,TENSP, TenLoai, SoLuong, Gia, DonVi, TenKhu, NSX, NHH, KhoHang.MaKho from SanPham 
                                          inner join LoaiHang on SanPham.MALOAI = LOAIHANG.MALOAI
                                          inner join SoLuongSP on SANPHAM.MASP = SoLuongSP.MASP
                     					 inner join Khu on Khu.MaLoai = LoaiHang.MaLoai
                     					 inner join KhoHang on Khu.MaKho = KhoHang.MaKho
                     					where SANPHAM.MaSP = ? and KhoHang.MaKho = ?""";
        List<SanPham> list = selectCT(sql, MaSP, MaKho);
        return list.size() > 0 ? list.get(0) : null;
    }

    public List<Object[]> getTenKhotoTable(String Kho) {
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                String sql = "{call sp_LayTenKho (?)}";
                rs = JdcbHelper.executeQuery(sql, Kho);
                while (rs.next()) {
                    Object[] model = {
                        rs.getString("MaSP"),
                        rs.getString("TENSP"),
                        rs.getString("TENLOAI"),
                        rs.getString("SoLuong"),
                        rs.getString("Gia"),
                        rs.getString("DonVi"),
                        rs.getString("TenKhu")
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

    public List<Object[]> getLoaiSPtoTable(String TenLH) {
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                String sql = "{call sp_LayTenLoaiHang (?)}";
                rs = JdcbHelper.executeQuery(sql, TenLH);
                while (rs.next()) {
                    Object[] model = {
                        rs.getString("MaSP"),
                        rs.getString("TENSP"),
                        rs.getString("TENLOAI"),
                        rs.getString("SoLuong"),
                        rs.getString("Gia"),
                        rs.getString("DonVi"),
                        rs.getString("TenKhu"),
                        rs.getString("MaKho")
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

    private List<SanPham> select(String sql, Object... args) {
        List<SanPham> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdcbHelper.executeQuery(sql, args);
                while (rs.next()) {
                    SanPham model = readFromResultSet(rs);
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

    private SanPham readFromResultSet(ResultSet rs) throws SQLException {
        SanPham model = new SanPham();
        model.setMaSP(rs.getString("MaSP"));
        model.setTenSp(rs.getString("TenSP"));
        model.setLoaiSP(rs.getString("TenLoai"));
        model.setSoLuong(rs.getInt("SoLuong"));
        model.setGia(rs.getFloat("Gia"));
        model.setDonVi(rs.getString("DonVi"));
        model.setKhu(rs.getString("TenKhu"));
        model.setMaKho(rs.getString("MaKho"));
        return model;
    }

    private List<SanPham> selectCT(String sql, Object... args) {
        List<SanPham> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdcbHelper.executeQuery(sql, args);
                while (rs.next()) {
                    SanPham model = readFromResultSetCT(rs);
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

    private SanPham readFromResultSetCT(ResultSet rs) throws SQLException {
        SanPham model = new SanPham();
        model.setMaSP(rs.getString("MaSP"));
        model.setLoaiSP(rs.getString("TenLoai"));
        model.setTenSp(rs.getString("TenSP"));
        model.setGia(rs.getFloat("Gia"));
        model.setDonVi(rs.getString("DonVi"));
        model.setNSX(rs.getString("NSX"));
        model.setNHH(rs.getString("NHH"));
        model.setKhu(rs.getString("TenKhu"));
        model.setMaKho(rs.getString("MaKho"));
        return model;
    }
}
