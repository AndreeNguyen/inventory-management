/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity.product;

/**
 *
 * @author Acer
 */
public class LoaiHang {
    private String MaLH;
    private String TenLH;
    private String  MaNcc;

    public LoaiHang() {
    }

    public LoaiHang(String MaLH, String TenLH, String MaNcc) {
        this.MaLH = MaLH;
        this.TenLH = TenLH;
        this.MaNcc = MaNcc;
    }

    public String getMaLH() {
        return MaLH;
    }

    public void setMaLH(String MaLH) {
        this.MaLH = MaLH;
    }

    public String getTenLH() {
        return TenLH;
    }

    public void setTenLH(String TenLH) {
        this.TenLH = TenLH;
    }

    public String getMaNcc() {
        return MaNcc;
    }

    public void setMaNcc(String MaNcc) {
        this.MaNcc = MaNcc;
    }
    
}
