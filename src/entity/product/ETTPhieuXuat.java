/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity.product;

/**
 *
 * @author AQ
 */
public class ETTPhieuXuat {
//    MaPX varchar(10),
//	PX_ThoiGian date,
//	PX_TrangThai int,
//	MaKho varchar(10),
//	Nguoixuat varchar(50),
    private String MaPX;
    private String PX_ThoiGian;
    private int PX_TrangThai;
    private String Makho;
    private String NguoiXuat;
    
    public ETTPhieuXuat() {
    }

    public ETTPhieuXuat(String MaPX, String PX_ThoiGian,int PX_TrangThai, String Makho, String NguoiXuat) {
        this.MaPX = MaPX;
        this.PX_ThoiGian = PX_ThoiGian;
        this.PX_TrangThai = PX_TrangThai;
        this.Makho = Makho;
        this.NguoiXuat = NguoiXuat;
    }

    public String getMaPX() {
        return MaPX;
    }

    public void setMaPX(String MaPX) {
        this.MaPX = MaPX;
    }

    public String getPX_ThoiGian() {
        return PX_ThoiGian;
    }

    public void setPX_ThoiGian(String PX_ThoiGian) {
        this.PX_ThoiGian = PX_ThoiGian;
    }

    public int getPX_TrangThai() {
        return PX_TrangThai;
    }

    public void setPX_TrangThai(int PX_TrangThai) {
        this.PX_TrangThai = PX_TrangThai;
    }
    

    public String getMakho() {
        return Makho;
    }

    public void setMakho(String Makho) {
        this.Makho = Makho;
    }

    public String getNguoiXuat() {
        return NguoiXuat;
    }

    public void setNguoiXuat(String NguoiXuat) {
        this.NguoiXuat = NguoiXuat;
    }

    
    
}
