/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Acer
 */
public class SanPham {

    private String MaSP;
    private String MaKho;
    private String LoaiSP;
    private String TenSp;
    private float Gia;
    private String KichThuoc;
    private String KhoiLuong;
    private String DVT;
    private String NSX;
    private String NHH;
    private String Khu;
    private int SoLuong;

    public SanPham() {
    }

    
    
    public SanPham(String MaSP, String MaKho, String LoaiSP, String TenSp, float Gia, String KichThuoc, String KhoiLuong, String DVT, String NSX, String NHH, String Khu, int SoLuong) {
        this.MaSP = MaSP;
        this.MaKho = MaKho;
        this.LoaiSP = LoaiSP;
        this.TenSp = TenSp;
        this.Gia = Gia;
        this.KichThuoc = KichThuoc;
        this.KhoiLuong = KhoiLuong;
        this.DVT = DVT;
        this.NSX = NSX;
        this.NHH = NHH;
        this.Khu = Khu;
        this.SoLuong = SoLuong;
    }

    public String getMaSP() {
        return MaSP;
    }

    public void setMaSP(String MaSP) {
        this.MaSP = MaSP;
    }

    public String getMaKho() {
        return MaKho;
    }

    public void setMaKho(String MaKho) {
        this.MaKho = MaKho;
    }

    public String getLoaiSP() {
        return LoaiSP;
    }

    public void setLoaiSP(String LoaiSP) {
        this.LoaiSP = LoaiSP;
    }

    public String getTenSp() {
        return TenSp;
    }

    public void setTenSp(String TenSp) {
        this.TenSp = TenSp;
    }

    public float getGia() {
        return Gia;
    }

    public void setGia(float Gia) {
        this.Gia = Gia;
    }

    public String getKichThuoc() {
        return KichThuoc;
    }

    public void setKichThuoc(String KichThuoc) {
        this.KichThuoc = KichThuoc;
    }

    public String getKhoiLuong() {
        return KhoiLuong;
    }

    public void setKhoiLuong(String KhoiLuong) {
        this.KhoiLuong = KhoiLuong;
    }

    public String getDVT() {
        return DVT;
    }

    public void setDVT(String DVT) {
        this.DVT = DVT;
    }

    public String getNSX() {
        return NSX;
    }

    public void setNSX(String NSX) {
        this.NSX = NSX;
    }

    public String getNHH() {
        return NHH;
    }

    public void setNHH(String NHH) {
        this.NHH = NHH;
    }

    public String getKhu() {
        return Khu;
    }

    public void setKhu(String Khu) {
        this.Khu = Khu;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

   

}
