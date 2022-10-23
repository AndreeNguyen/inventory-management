/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity.product;

import java.util.Date;

/**
 *
 * @author My PC
 */
public class LichSuGiaoDich {
    private String maPhieu;
    private float tien;
    private Date ngayDat;
    private String tenNV;
    private byte trangThai;

    public LichSuGiaoDich() {
    }

    public LichSuGiaoDich(String maPhieu, float tien, Date ngayDat, String tenNV, byte trangThai) {
        this.maPhieu = maPhieu;
        this.tien = tien;
        this.ngayDat = ngayDat;
        this.tenNV = tenNV;
        this.trangThai = trangThai;
    }

    public String getMaPhieu() {
        return maPhieu;
    }

    public void setMaPhieu(String maPhieu) {
        this.maPhieu = maPhieu;
    }

    public float getTien() {
        return tien;
    }

    public void setTien(float tien) {
        this.tien = tien;
    }

    public Date getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(Date ngayDat) {
        this.ngayDat = ngayDat;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public byte getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(byte trangThai) {
        this.trangThai = trangThai;
    }

    
}
