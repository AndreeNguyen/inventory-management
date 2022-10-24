/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.productDAO;

import entity.product.SanPham;
import model.DatabaseHelper;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.sql.ResultSet;

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
                select SANPHAM.MaSP, TENSP, TenLoai, SoLuong, Gia, DonVi,TenKhu,Khohang.MaKho,TenNCC, TenKho, MaKhu from SanPham 
                inner join LoaiHang on SanPham.MALOAI = LOAIHANG.MALOAI
                inner join SoLuongSP on SANPHAM.MASP = SoLuongSP.MASP
                full outer join Khu on Khu.MaLoai = LoaiHang.MaLoai
                inner join KhoHang on Khu.MaKho = KhoHang.MaKho
                full outer join NhaCungCap on NhaCungCap.MaNCC = SANPHAM.MaNCC
                     """;
        return select(sql);
    }

    public void insert(SanPham model) {
        String sql = "INSERT INTO SANPHAM (MASP, TENSP, GIA, DonVi, NSX, NHH, KhuLuuTru )"
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
                + "";
        DatabaseHelper.executeUpdate(sql, model.getMaSP(), model.getMaKho(), model.getLoaiSP(), model.getTenSp(), model.getGia(), model.getDonVi(), model.getNSX(), model.getNHH(), model.getKhu());
    }

    public void Update(SanPham model) {
        String sql = "UPDATE SanPham SET MASP=?, TenSP=?, Gia=?, DonVi=?, MaLoai=?, MaNCC=?, NSX=?, NHH=?  WHERE MaSP=?";
        DatabaseHelper.executeUpdate(sql, model.getMaSP(), model.getTenSp(), model.getGia(), model.getDonVi(), model.getLoaiSP(), model.getNhaCC(), model.getNSX(), model.getNHH(), model.getMaSP());
    }

    public SanPham findByName(String TenKho, String MaKho, String Soluong, String MaKhu) {
        String sql;
        sql = """
            select SANPHAM.MaSP, TENSP, TenLoai, SoLuong, Gia, DonVi,TenKhu,Khohang.MaKho,TenNCC,TenKho, MaKhu,NSX,NHH, NhaCungCap.MANCC from SanPham 
                        inner join LoaiHang on SanPham.MALOAI = LOAIHANG.MALOAI
                        inner join SoLuongSP on SANPHAM.MASP = SoLuongSP.MASP
                        inner join Khu on Khu.MaLoai = LoaiHang.MaLoai
                        inner join KhoHang on Khu.MaKho = KhoHang.MaKho
                        inner join NhaCungCap on NhaCungCap.MaNCC = SANPHAM.MaNCC
                        where TenKho = ? and SoLuongSP.MaKho = ? and SoLuong =? and MaKhu = ?
              """;
        List<SanPham> list = selectCT(sql, TenKho, MaKho, Soluong, MaKhu);
        return list.size() > 0 ? (list.get(0)) : null;
    }

    public List<Object[]> getTenKhotoTable(String Kho, String masp) {
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                String sql = "{call sp_LayTenKho (?,?)}";
                rs = DatabaseHelper.executeQuery(sql, Kho, masp);
                while (rs.next()) {
                    Object[] model = {
                        rs.getString("MaSP"),
                        rs.getString("TENSP"),
                        rs.getString("TENLOAI"),
                        rs.getString("SoLuong"),
                        rs.getString("Gia"),
                        rs.getString("DonVi"),
                        rs.getString("TenKhu"),
                        rs.getString("MaKho"),
                        rs.getString("TenNCC"),
                        rs.getString("TenKho"),
                        rs.getString("MaKhu")
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
                rs = DatabaseHelper.executeQuery(sql, TenLH);
                while (rs.next()) {
                    Object[] model = {
                        rs.getString("MaSP"),
                        rs.getString("TENSP"),
                        rs.getString("TENLOAI"),
                        rs.getString("SoLuong"),
                        rs.getString("Gia"),
                        rs.getString("DonVi"),
                        rs.getString("TenKhu"),
                        rs.getString("MaKho"),
                        rs.getString("TenNCC"),
                        rs.getString("TenKho"),
                        rs.getString("MaKhu")
                        
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
                rs = DatabaseHelper.executeQuery(sql, args);
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
        model.setNhaCC(rs.getString("TenNCC"));
        model.setTenKhoSP(rs.getString("TenKho"));
        model.setMaKhu(rs.getString("MaKhu"));
        return model;
    }

    private List<SanPham> selectCT(String sql, Object... args) {
        List<SanPham> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = DatabaseHelper.executeQuery(sql, args);
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
        model.setSoLuong(rs.getInt("SoLuong"));
        model.setDonVi(rs.getString("DonVi"));
        model.setNSX(rs.getString("NSX"));
        model.setNHH(rs.getString("NHH"));
        model.setKhu(rs.getString("TenKhu"));
        model.setMaKho(rs.getString("MaKho"));
        model.setNhaCC(rs.getString("MaNCC"));
        model.setTenKhoSP(rs.getString("TenKho"));
        model.setMaKhu(rs.getString("MaKhu"));
        return model;
    }


}
