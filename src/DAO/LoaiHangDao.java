/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Helper.JdcbHelper;
import Model.LoaiHang;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Acer
 */
public class LoaiHangDao {
    //    public void insert(SanPham model) {
//        String sql = "INSERT INTO SANPHAM() VALUES (?, ?, ?, ?, ?, ?)";
//        JdcbHelper.executeUpdate(sql, );
//    }
//    public List<SanPham> select() {
//        String sql = """
//                     select TENSP, TENLH, SoLuong, Gia, DonViTinh,KhuLuuTru from SanPham 
//                     inner join LoaiHang on SanPham.MALOAI = LOAIHANG.MALOAI
//                     inner join SLHoangHoa on SANPHAM.MASP = SLHoangHoa.MASP""";
//        return select(sql);
//    }
    
    public List<LoaiHang> select(){
        String sql = "select * from LOAIHANG";
        return select(sql);
    }
    
//    public List<LoaiHang> ChangMaLH(String TenLH){
//        String sql = "select MALOAI where TENSP = "+TenLH;
//        return select(sql);
//    }
//     public void insert(LoaiHang model) {
//        String sql = "INSERT INTO SANPHAM (MASP, TENSP, GIA, KICHTHUOC, DonViTinh, dateSX, dateHH, KhuLuuTru )"
//                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"
//                + "";
//        JdcbHelper.executeUpdate(sql,model.getMaSP(),model.getLoaiSP() , model.getTenSp(), model.getGia(), model.getKichThuoc(), model.getDVT(),model.getNSX(),model.getNHH(),model.getKhu());
//    }

    
    

    private List<LoaiHang> select(String sql, Object... args) {
        List<LoaiHang> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdcbHelper.executeQuery(sql, args);
                while (rs.next()) {
                    LoaiHang model = readFromResultSet(rs);
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

    private LoaiHang readFromResultSet(ResultSet rs) throws SQLException {
        LoaiHang model = new LoaiHang();
        model.setMaLH(rs.getString("MaLOAI"));
        model.setTenLH(rs.getString("TenLOAI"));
        return model;
    }
}
