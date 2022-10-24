/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity.product;

/**
 *
 * @author AQ
 */
public class NV_ChamCong {
    private String Manv;
    private String Ngaylamviec;
    private String checkin;
    private String checkout;
    private boolean Overtime;
    private String MaKho;
    public NV_ChamCong(){
        
    }

    public NV_ChamCong(String Manv, String Ngaylamviec, String checkin, String checkout, boolean Overtime, String MaKho) {
        this.Manv = Manv;
        this.Ngaylamviec = Ngaylamviec;
        this.checkin = checkin;
        this.checkout = checkout;
        this.Overtime = Overtime;
        this.MaKho = MaKho;
    }

    public String getManv() {
        return Manv;
    }

    public void setManv(String Manv) {
        this.Manv = Manv;
    }

    public String getNgaylamviec() {
        return Ngaylamviec;
    }

    public void setNgaylamviec(String Ngaylamviec) {
        this.Ngaylamviec = Ngaylamviec;
    }

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    public boolean isOvertime() {
        return Overtime;
    }

    public void setOvertime(boolean Overtime) {
        this.Overtime = Overtime;
    }

    public String getMaKho() {
        return MaKho;
    }

    public void setMaKho(String MaKho) {
        this.MaKho = MaKho;
    }

}
