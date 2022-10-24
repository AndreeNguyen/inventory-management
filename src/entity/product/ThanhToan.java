/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity.product;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author My PC
 */
public class ThanhToan {
    private String maPhieuTT;
    private String maPhieu;
    private String congTy;
    private String sdt;
    private float tongTien;
    private float thanhToan;
    private float conLai;
    private Date ngayTao;
    private String phuongThuc;
    private String ghiChu;

    public ThanhToan() {
    }

    
    
    public ThanhToan(String maPhieuTT, String maPhieu, String congTy, String sdt, float tongTien, float thanhToan, float conLai, Date ngayTao, String phuongThuc, String ghiChu) {
        this.maPhieuTT = maPhieuTT;
        this.maPhieu = maPhieu;
        this.congTy = congTy;
        this.sdt = sdt;
        this.tongTien = tongTien;
        this.thanhToan = thanhToan;
        this.conLai = conLai;
        this.ngayTao = ngayTao;
        this.phuongThuc = phuongThuc;
        this.ghiChu = ghiChu;
    }

    public String getMaPhieuTT() {
        return maPhieuTT;
    }

    public void setMaPhieuTT(String maPhieuTT) {
        this.maPhieuTT = maPhieuTT;
    }

    public String getMaPhieu() {
        return maPhieu;
    }

    public void setMaPhieu(String maPhieu) {
        this.maPhieu = maPhieu;
    }

    public String getCongTy() {
        return congTy;
    }

    public void setCongTy(String congTy) {
        this.congTy = congTy;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public float getTongTien() {
        return tongTien;
    }

    public void setTongTien(float tongTien) {
        this.tongTien = tongTien;
    }

    public float getThanhToan() {
        return thanhToan;
    }

    public void setThanhToan(float thanhToan) {
        this.thanhToan = thanhToan;
    }

    public float getConLai() {
        return conLai;
    }

    public void setConLai(float conLai) {
        this.conLai = conLai;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getPhuongThuc() {
        return phuongThuc;
    }

    public void setPhuongThuc(String phuongThuc) {
        this.phuongThuc = phuongThuc;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    
    
    

    
}
