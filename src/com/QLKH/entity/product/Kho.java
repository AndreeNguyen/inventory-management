/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.QLKH.entity.product;

/**
 *
 * @author Acer
 */
public class Kho {
    private String MaKho;
    private String TenKho;
    private String KhuVuc;
    private String Manv;

    public Kho() {
    }

    public Kho(String MaKho, String TenKho, String KhuVuc, String Manv) {
        this.MaKho = MaKho;
        this.TenKho = TenKho;
        this.KhuVuc = KhuVuc;
        this.Manv = Manv;
    }

    public String getMaKho() {
        return MaKho;
    }

    public void setMaKho(String MaKho) {
        this.MaKho = MaKho;
    }

    public String getTenKho() {
        return TenKho;
    }

    public void setTenKho(String TenKho) {
        this.TenKho = TenKho;
    }

    public String getKhuVuc() {
        return KhuVuc;
    }

    public void setKhuVuc(String KhuVuc) {
        this.KhuVuc = KhuVuc;
    }

    public String getManv() {
        return Manv;
    }

    public void setManv(String Manv) {
        this.Manv = Manv;
    }
    
    
}
