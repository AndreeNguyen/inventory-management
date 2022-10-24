/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity.product;

/**
 *
 * @author Acer
 */
public class SanPham {

    private String MaSP;
    private String TenSp;
    private float Gia;
    private String DonVi;
    private String LoaiSP;
    private String NhaCC;
    private String NSX;
    private String NHH;
    private String MaKho;
    private String Khu;
    private int SoLuong;

    public SanPham() {
    }

    public SanPham(String MaSP, String TenSp, float Gia, String DonVi, String LoaiSP, String NhaCC, String NSX, String NHH, String MaKho, String Khu, int SoLuong) {
        this.MaSP = MaSP;
        this.TenSp = TenSp;
        this.Gia = Gia;
        this.DonVi = DonVi;
        this.LoaiSP = LoaiSP;
        this.NhaCC = NhaCC;
        this.NSX = NSX;
        this.NHH = NHH;
        this.MaKho = MaKho;
        this.Khu = Khu;
        this.SoLuong = SoLuong;
    }

    
    
    public String getMaSP() {
        return MaSP;
    }

    public void setMaSP(String MaSP) {
        this.MaSP = MaSP;
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

    public String getDonVi() {
        return DonVi;
    }

    public void setDonVi(String DonVi) {
        this.DonVi = DonVi;
    }

    public String getLoaiSP() {
        return LoaiSP;
    }

    public void setLoaiSP(String LoaiSP) {
        this.LoaiSP = LoaiSP;
    }

    public String getNhaCC() {
        return NhaCC;
    }

    public void setNhaCC(String NhaCC) {
        this.NhaCC = NhaCC;
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

    public String getMaKho() {
        return MaKho;
    }

    public void setMaKho(String MaKho) {
        this.MaKho = MaKho;
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
