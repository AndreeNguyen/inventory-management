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
public class ThongKe_TCH {
    String tenCH;
    Date thoiGian;
    float doanhThu;

    public ThongKe_TCH() {
    }

    public ThongKe_TCH(String tenCH, Date thoiGian, float doanhThu) {
        this.tenCH = tenCH;
        this.thoiGian = thoiGian;
        this.doanhThu = doanhThu;
    }

    public String getTenCH() {
        return tenCH;
    }

    public void setTenCH(String tenCH) {
        this.tenCH = tenCH;
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
