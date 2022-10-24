/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity.product;

/**
 *
 * @author AQ
 */
public class ETTPhieuDat {
//    MaPD varchar(10),
//	PD_ThoiGian date,
//	PD_TrangThai bit,
//	NguoiDat varchar(50)
    private String MaPD;
    private String PD_ThoiGian;
    private boolean PD_TrangThai;
    private String Makho;
    private String NguoiDat;
    
    public ETTPhieuDat() {
    }

    public ETTPhieuDat(String MaPD, String PD_ThoiGian, boolean PD_TrangThai, String MaKho, String NguoiDat) {
        this.MaPD = MaPD;
        this.PD_ThoiGian = PD_ThoiGian;
        this.PD_TrangThai = PD_TrangThai;
        this.Makho = MaKho;
        this.NguoiDat = NguoiDat;
    }

    public String getMaPD() {
        return MaPD;
    }

    public void setMaPD(String MaPD) {
        this.MaPD = MaPD;
    }

    public String getPD_ThoiGian() {
        return PD_ThoiGian;
    }

    public void setPD_ThoiGian(String PD_ThoiGian) {
        this.PD_ThoiGian = PD_ThoiGian;
    }

    public boolean isPD_TrangThai() {
        return PD_TrangThai;
    }

    public void setPD_TrangThai(boolean PD_TrangThai) {
        this.PD_TrangThai = PD_TrangThai;
    }

    public String getMakho() {
        return Makho;
    }

    public void setMakho(String Makho) {
        this.Makho = Makho;
    }

    public String getNguoiDat() {
        return NguoiDat;
    }

    public void setNguoiDat(String NguoiDat) {
        this.NguoiDat = NguoiDat;
    }
    
    
}
