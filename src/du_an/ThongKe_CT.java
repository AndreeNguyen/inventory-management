/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package du_an;

import java.util.Date;

/**
 *
 * @author LENOVO
 */
public class ThongKe_CT {
    String tenKhoanChi;
    float khoanChi;
    Date thoiGian;
    String hinhThuc;

    public ThongKe_CT() {
    }

    public ThongKe_CT(String tenKhoanChi, float khoanChi, Date thoiGian, String hinhThuc) {
        this.tenKhoanChi = tenKhoanChi;
        this.khoanChi = khoanChi;
        this.thoiGian = thoiGian;
        this.hinhThuc = hinhThuc;
    }

    public String getTenKhoanChi() {
        return tenKhoanChi;
    }

    public void setTenKhoanChi(String tenKhoanChi) {
        this.tenKhoanChi = tenKhoanChi;
    }

    public float getKhoanChi() {
        return khoanChi;
    }

    public void setKhoanChi(float khoanChi) {
        this.khoanChi = khoanChi;
    }

    public Date getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(Date thoiGian) {
        this.thoiGian = thoiGian;
    }

    public String getHinhThuc() {
        return hinhThuc;
    }

    public void setHinhThuc(String hinhThuc) {
        this.hinhThuc = hinhThuc;
    }
    
}
