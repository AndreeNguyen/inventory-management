/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity.product;

/**
 *
 * @author AQ
 */
public class ETTPhieuNhap {
//    MaPN varchar(10),
//	PN_ThoiGian date,
//	PN_TrangThai bit,
//	MaKho varchar(10),
//	Nguoinhap varchar(50),
    private String MaPN;
    private String PN_ThoiGian;
    private int PN_TrangThai;
    private String MaKho;
    private String NguoiNhap;

    public ETTPhieuNhap() {
    }

    public ETTPhieuNhap(String MaPN, String PN_ThoiGian, int PN_TrangThai, String MaKho, String NguoiNhap) {
        this.MaPN = MaPN;
        this.PN_ThoiGian = PN_ThoiGian;
        this.PN_TrangThai = PN_TrangThai;
        this.MaKho = MaKho;
        this.NguoiNhap = NguoiNhap;
    }

    public String getMaPN() {
        return MaPN;
    }

    public void setMaPN(String MaPN) {
        this.MaPN = MaPN;
    }

    public String getPN_ThoiGian() {
        return PN_ThoiGian;
    }

    public void setPN_ThoiGian(String PN_ThoiGian) {
        this.PN_ThoiGian = PN_ThoiGian;
    }

    public int getPN_TrangThai() {
        return PN_TrangThai;
    }

    public void setPN_TrangThai(int PN_TrangThai) {
        this.PN_TrangThai = PN_TrangThai;
    }


    public String getMaKho() {
        return MaKho;
    }

    public void setMaKho(String MaKho) {
        this.MaKho = MaKho;
    }

    public String getNguoiNhap() {
        return NguoiNhap;
    }

    public void setNguoiNhap(String NguoiNhap) {
        this.NguoiNhap = NguoiNhap;
    }
    
    
}
