/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity.product;

/**
 *
 * @author AQ
 */
public class SoLuongSP {

    private String MaKho;
    private String Maloai;
    private String TenLoai;
    private String MaSP;
    private String TenSP;
    private int SoLuong;

    public SoLuongSP() {
    }

    public SoLuongSP(String MaKho, String Maloai, String TenLoai, String MaSP, String TenSP, int SoLuong) {
        this.MaKho = MaKho;
        this.Maloai = Maloai;
        this.TenLoai = TenLoai;
        this.MaSP = MaSP;
        this.TenSP = TenSP;
        this.SoLuong = SoLuong;
    }

    public String getMaKho() {
        return MaKho;
    }

    public void setMaKho(String MaKho) {
        this.MaKho = MaKho;
    }

    public String getMaloai() {
        return Maloai;
    }

    public void setMaloai(String Maloai) {
        this.Maloai = Maloai;
    }

    public String getTenLoai() {
        return TenLoai;
    }

    public void setTenLoai(String TenLoai) {
        this.TenLoai = TenLoai;
    }

    public String getMaSP() {
        return MaSP;
    }

    public void setMaSP(String MaSP) {
        this.MaSP = MaSP;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String TenSP) {
        this.TenSP = TenSP;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }
    
    
}
