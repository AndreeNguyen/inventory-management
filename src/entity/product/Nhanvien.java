/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity.product;

/**
 *
 * @author AQ
 */
public class Nhanvien {
    private String manv;
    private String hoten;
    private String ngaysinh;
    private String gioitinh;
    private String makho;
    private String CCCD;
    private String SDT;
    private String email;
    private String Diachi;
    private String anh;
    private int ChucVu;
    
    public Nhanvien(){
        
    }

    public Nhanvien(String manv, String hoten, String ngaysinh, String gioitinh, String makho, String CCCD, String SDT, String email, String Diachi, String anh, int ChucVU) {
        this.manv = manv;
        this.hoten = hoten;
        this.ngaysinh = ngaysinh;
        this.gioitinh = gioitinh;
        this.makho = makho;
        this.CCCD = CCCD;
        this.SDT = SDT;
        this.email = email;
        this.Diachi = Diachi;
        this.anh = anh;
        this.ChucVu = ChucVU;
    }

    public String getManv() {
        return manv;
    }

    public void setManv(String manv) {
        this.manv = manv;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

     public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getMakho() {
        return makho;
    }

    public void setMakho(String makho) {
        this.makho = makho;
    }

    public String getCCCD() {
        return CCCD;
    }

    public void setCCCD(String CCCD) {
        this.CCCD = CCCD;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiachi() {
        return Diachi;
    }

    public void setDiachi(String Diachi) {
        this.Diachi = Diachi;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    public int getChucVu() {
        return ChucVu;
    }

    public void setChucVu(int ChucVu) {
        this.ChucVu = ChucVu;
    }

   
    
}
