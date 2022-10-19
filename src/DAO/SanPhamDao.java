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
                     select TENSP, TENLH, SoLuong, Gia, DonViTinh,KhuLuuTru from SanPham 
                     inner join LoaiHang on SanPham.MALOAI = LOAIHANG.MALOAI
                     inner join SLHoangHoa on SANPHAM.MASP = SLHoangHoa.MASP""";
        return select(sql);
    }

    public void insert(SanPham model) {
        String sql = "INSERT INTO SANPHAM (MASP, MaKho, MaLoai,TENSP, GIA, KICHTHUOC, KhoiLuong, DonViTinh, dateSX, dateHH, KhuLuuTru )"
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
                + "";
        JdcbHelper.executeUpdate(sql, model.getMaSP(), model.getMaKho(), model.getLoaiSP(), model.getTenSp(), model.getGia(), model.getKichThuoc(), model.getKhoiLuong(), model.getDVT(), model.getNSX(), model.getNHH(), model.getKhu());
    }

    public void Update(SanPham model) {
        String sql = "UPDATE SanPham SET MASP=?, MaKho=?, MaLoai=?, TenSP=?, Gia=?, KIchThuoc=?, DonViTinh=?, dateSX=?, dateHH=?, KhuLuuTru=? WHERE MaSP=?";
        JdcbHelper.executeUpdate(sql, model.getMaSP(), model.getMaKho(), model.getLoaiSP(), model.getTenSp(), model.getGia(), model.getKichThuoc(), model.getKhoiLuong(), model.getDVT(), model.getNSX(), model.getNHH(), model.getKhu(), model.getMaSP());
    }

    public SanPham findByName(String MaSP) {
        String sql = """
                     select SANPHAM.MASP,TENLH,SoLuong, TENKHO, TenSP, KhoiLuong, KichThuoc, DonViTinh, Gia, dateSX, dateHH,KhuLuuTru from SanPham 
                     inner join LoaiHang on SanPham.MALOAI = LOAIHANG.MALOAI
                     inner join SLHoangHoa on SANPHAM.MASP = SLHoangHoa.MASP
                     inner join QLKho on QLKho.MaKho = SANPHAM.MaKho
                     where TenSP like ?""";
        List<SanPham> list = selectCT(sql, MaSP);
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
                        rs.getString("TENSP"),
                        rs.getString("TENLH"),
                        rs.getString("SoLuong"),
                        rs.getString("Gia"),
                        rs.getString("DonViTinh"),
                        rs.getString("KhuLuuTru")
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
        model.setTenSp(rs.getString("TenSP"));
        model.setLoaiSP(rs.getString("TenLH"));
        model.setSoLuong(rs.getInt("SoLuong"));
        model.setGia(rs.getFloat("Gia"));
        model.setDVT(rs.getString("DonViTinh"));
        model.setKhu(rs.getString("KhuLuuTru"));
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
        model.setMaKho(rs.getString("TenKho"));
        model.setLoaiSP(rs.getString("TenLH"));
        model.setTenSp(rs.getString("TenSP"));
        model.setKhoiLuong(rs.getString("KhoiLuong"));
        model.setSoLuong(rs.getInt("SoLuong"));
        model.setKichThuoc(rs.getString("KichThuoc"));
        model.setGia(rs.getFloat("Gia"));
        model.setDVT(rs.getString("DonViTinh"));
        model.setNSX(rs.getString("dateSX"));
        model.setNHH(rs.getString("dateHH"));
        model.setKhu(rs.getString("KhuLuuTru"));
        return model;
    }
}
