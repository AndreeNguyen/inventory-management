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
public class DoanhThu_tong {
    Date thoiGian;
    float doanhThu;

    public DoanhThu_tong() {
    }

    public DoanhThu_tong(Date thoiGian, float doanhThu) {
        this.thoiGian = thoiGian;
        this.doanhThu = doanhThu;
    }

    public Date getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(Date thoiGian) {
        this.thoiGian = thoiGian;
    }

    public float getDoanhThu() {
        return doanhThu;
    }

    public void setDoanhThu(float doanhThu) {
        this.doanhThu = doanhThu;
    }
    
}
