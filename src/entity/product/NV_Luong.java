/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity.product;

/**
 *
 * @author AQ
 */
public class NV_Luong {
    private String MaNV;
    private double ThuongOT;
    private double ThuongKPI;
    private double luongcb;
    private String NgayNhan;
    private double TongLuong;
    private String MaKho;

    public NV_Luong() {
    }

    
    public NV_Luong(String MaNV, double ThuongOT, double ThuongKPI, double luongcb, String NgayNhan, double TongLuong, String MaKho) {
        this.MaNV = MaNV;
        this.ThuongOT = ThuongOT;
        this.ThuongKPI = ThuongKPI;
        this.luongcb = luongcb;
        this.NgayNhan = NgayNhan;
        this.TongLuong = TongLuong;
        this.MaKho = MaKho;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public double getThuongOT() {
        return ThuongOT;
    }

    public void setThuongOT(double ThuongOT) {
        this.ThuongOT = ThuongOT;
    }

    public double getThuongKPI() {
        return ThuongKPI;
    }

    public void setThuongKPI(double ThuongKPI) {
        this.ThuongKPI = ThuongKPI;
    }

    public double getLuongcb() {
        return luongcb;
    }

    public void setLuongcb(double luongcb) {
        this.luongcb = luongcb;
    }

    public String getNgayNhan() {
        return NgayNhan;
    }

    public void setNgayNhan(String NgayNhan) {
        this.NgayNhan = NgayNhan;
    }

    public double getTongLuong() {
        return TongLuong;
    }

    public void setTongLuong(double TongLuong) {
        this.TongLuong = TongLuong;
    }

    public String getMaKho() {
        return MaKho;
    }

    public void setMaKho(String MaKho) {
        this.MaKho = MaKho;
    }
    
    
}
