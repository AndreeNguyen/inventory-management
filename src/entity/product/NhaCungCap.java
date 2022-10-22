/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity.product;

import java.util.Date;

/**
 *
 * @author Acer
 */
public class NhaCungCap {
    private String MaNCC;
    private String TenNCC;
    private String SDT;
    private String Email;
    private String DiaChi;
    private String MaSoThue;
    private String congty;
    private String nhomNCC;
    private Date ngayTaoDate;

    public NhaCungCap() {
    }

    public NhaCungCap(String MaNCC, String TenNCC, String SDT, String Email) {
        this.MaNCC = MaNCC;
        this.TenNCC = TenNCC;
        this.SDT = SDT;
        this.Email = Email;
    }

    
    
    public NhaCungCap(String MaNCC, String TenNCC, String SDT, String Email, String DiaChi, String MaSoThue, String congty, String nhomNCC, Date ngayTaoDate) {
        this.MaNCC = MaNCC;
        this.TenNCC = TenNCC;
        this.SDT = SDT;
        this.Email = Email;
        this.DiaChi = DiaChi;
        this.MaSoThue = MaSoThue;
        this.congty = congty;
        this.nhomNCC = nhomNCC;
        this.ngayTaoDate = ngayTaoDate;
    }

    public String getMaNCC() {
        return MaNCC;
    }

    public void setMaNCC(String MaNCC) {
        this.MaNCC = MaNCC;
    }

    public String getTenNCC() {
        return TenNCC;
    }

    public void setTenNCC(String TenNCC) {
        this.TenNCC = TenNCC;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public String getMaSoThue() {
        return MaSoThue;
    }

    public void setMaSoThue(String MaSoThue) {
        this.MaSoThue = MaSoThue;
    }

    public String getCongty() {
        return congty;
    }

    public void setCongty(String congty) {
        this.congty = congty;
    }

    public String getNhomNCC() {
        return nhomNCC;
    }

    public void setNhomNCC(String nhomNCC) {
        this.nhomNCC = nhomNCC;
    }

    public Date getNgayTaoDate() {
        return ngayTaoDate;
    }

    public void setNgayTaoDate(Date ngayTaoDate) {
        this.ngayTaoDate = ngayTaoDate;
    }

    
}
