/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.productDAO;

import model.DatabaseHelper;
import entity.product.NhaCungCap;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Acer
 */
public class NhaCungCapDao {

    final String insertNCC = "INSERT INTO NhaCungCap VALUES (?,?,?,?,?,?,?,?,?);";
    final String deleteNCC = "DELETE NhaCungCap WHERE MaNCC = ?";
    final String updateNCC = "UPDATE NhaCungCap SET TenNCC=?,SDT=?,Email=?,DiaChi =?,MaSoThue=?,Congty=?,NhomNCC=?,ngaytao=? WHERE MaNCC = ?";
    final String selectAllNCC = "SELECT * FROM NhaCungCap";
    final String selectById = "SELECT * FROM NhaCungCap where MaNCC = ?";

    public class KhoDao {

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
            model.setEmail(rs.getString("email"));
            model.setDiaChi(rs.getString("diachi"));
            model.setMaSoThue(rs.getString("MaSoThue"));
            model.setCongty(rs.getString("Congty"));
            model.setNhomNCC(rs.getString("NhomNCC"));
            model.setNgayTaoDate(rs.getDate("ngaytao"));
            return model;
        }
    }

    public void insert(NhaCungCap entity) {
        DatabaseHelper.executeUpdate(insertNCC, entity.getMaNCC(), entity.getTenNCC(), entity.getSDT(),entity.getEmail() ,entity.getDiaChi(), entity.getMaSoThue(),entity.getCongty(),entity.getNhomNCC(),entity.getNgayTaoDate());
    }

    public void update(NhaCungCap entity) {
        DatabaseHelper.executeUpdate(updateNCC, entity.getTenNCC(), entity.getSDT(),entity.getEmail() ,entity.getDiaChi(), entity.getMaSoThue(),entity.getCongty(),entity.getNhomNCC(),entity.getNgayTaoDate(), entity.getMaNCC());
    }

    public void delete(String keytype) {
        DatabaseHelper.executeUpdate(deleteNCC, keytype);
    }

    public List<NhaCungCap> selectAll() {
        return selectBySql(selectAllNCC);
    }

    public NhaCungCap selectById(Integer keytype) {
        List<NhaCungCap> list = selectBySql(selectById, keytype);
        if (list.isEmpty()) {
            return null;//rong
        }
        return list.get(0);
    }

    public List<NhaCungCap> selectBySql(String sql, Object... args) {
        List<NhaCungCap> list = new ArrayList<>();
        try {
            ResultSet rs = DatabaseHelper.executeQuery(sql, args);
            while (rs.next()) {
                NhaCungCap entity = new NhaCungCap();
                entity.setMaNCC(rs.getString("MaNCC"));
                entity.setTenNCC(rs.getString("TenNCC"));
                entity.setSDT(rs.getString("SDT"));
                entity.setEmail(rs.getString("email"));
                entity.setDiaChi(rs.getString("diachi"));
                entity.setMaSoThue(rs.getString("MaSoThue"));
                entity.setCongty(rs.getString("Congty"));
                entity.setNhomNCC(rs.getString("NhomNCC"));
                entity.setNgayTaoDate(rs.getDate("ngaytao"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
