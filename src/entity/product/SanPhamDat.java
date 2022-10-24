/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity.product;

/**
 *
 * @author AQ
 */
public class SanPhamDat {
//    STT int identity(1,1),
//	MaLoai varchar(10),
//	MaSP varchar(10),
//	SoLuong int,
//	MaPD varchar(10),
//	MaPX varchar(10),
//	MaPN varchar(10),
    
    private String MaLoai;
    private String MaSP;
    private int SoLuong;
    private String MaPD;
    private String MaPX;
    private String MaPN;

    public SanPhamDat() {
    }

    public SanPhamDat( String MaLoai, String MaSP, int SoLuong, String MaPD, String MaPX, String MaPN) {
        this.MaLoai = MaLoai;
        this.MaSP = MaSP;
        this.SoLuong = SoLuong;
        this.MaPD = MaPD;
        this.MaPX = MaPX;
        this.MaPN = MaPN;
    }


    public String getMaLoai() {
        return MaLoai;
    }

    public void setMaLoai(String MaLoai) {
        this.MaLoai = MaLoai;
    }

    public String getMaSP() {
        return MaSP;
    }

    public void setMaSP(String MaSP) {
        this.MaSP = MaSP;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public String getMaPD() {
        return MaPD;
    }

    public void setMaPD(String MaPD) {
        this.MaPD = MaPD;
    }

    public String getMaPX() {
        return MaPX;
    }

    public void setMaPX(String MaPX) {
        this.MaPX = MaPX;
    }

    public String getMaPN() {
        return MaPN;
    }

    public void setMaPN(String MaPN) {
        this.MaPN = MaPN;
    }
 
}
