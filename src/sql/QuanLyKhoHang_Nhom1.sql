use master
drop database QUANLYKHOHANG_NHOM1
go
create database QUANLYKHOHANG_NHOM1
go 
use QUANLYKHOHANG_NHOM1

create table Taikhoan(
	MaNV varchar(10),
	Pass varchar(10),
	Vaitro bit
	primary key(MaNV)
)

create table Nhanvien(
	MaNV varchar(10),
	HoTen nvarchar(50),
	Ngaysinh date,
	Gioitinh bit,
	MaKho varchar(10),
	CCCD varchar(12),
	SDT varchar(12),
	Email varchar(50),
	Diachi nvarchar(100),
	anh varchar(20),
	ChucVu int
	primary key(MaNV)

)

create table DiaDiem(
	MaDiaDiem varchar(10),
	TenDiaDiem nvarchar(50),
	DiaChi nvarchar(100)
	primary key(MaDiaDiem)
	)

create table KhoHang(
	MaKho varchar(10),
	TenKho nvarchar(50),
	MaNV varchar(10),
	MaDiaDiem varchar(10),
	primary key(MaKho),
	constraint fk_kh_nv foreign key(MaNV) references NhanVien(MaNV),
	constraint fk_kh_dd foreign key(MaDiaDiem) references DiaDiem(MaDiaDiem)
)

create table LoaiHang(
	MaLoai varchar(10),
	TenLoai nvarchar(30)
	primary key (Maloai)

)

create table Khu(
	MaKhu varchar(10),
	TenKhu nvarchar(50),
	MaKho varchar(10),
	MaLoai varchar(10),
	Succhua int,
	primary key(MaKhu),
	constraint fk_k_kh foreign key(MaKho) references KhoHang(Makho),
	constraint fk_k_lh foreign key(MaLoai) references LoaiHang(MaLoai)
)

create table NhaCungCap(
	MaNCC varchar(10),
	TenNCC nvarchar(50),
	SDT varchar(12),
	Email varchar(50),
	DiaChi nvarchar(100),
	MaSoThue varchar(13)
	primary key(MaNCC)
)


create table SanPham(
	MaSP varchar(10),
	TenSP nvarchar(50),
	Gia int,
	DonVi nvarchar(20),
	MaLoai varchar(10),
	MaNCC varchar(10),
	NSX date,
	MHH date,
	primary key(MaSP),
	constraint fk_sp_lh foreign key(MaLoai) references LoaiHang(MaLoai)
)

alter table SANPHAM
add constraint fk_sp_ncc foreign key (MaNCC) references NhaCungCap(MaNCC)

create table SoLuongSP(
	MaKho varchar(10),
	MaSP varchar(10),
	SoLuong int,
	primary key (Makho, MaSP),
	constraint fk_slsp_k foreign key(makho) references khohang(makho),
	constraint fk_slsp_sp foreign key(masp) references sanpham(masp)
)

create table phieudat(
	MaPD varchar(10),
	PD_ThoiGian date,
	PD_TrangThai bit,
	NguoiDat varchar(50)
	primary key(MaPD)

)

create table phieuxuat(
	MaPX varchar(10),
	PX_ThoiGian date,
	PX_TrangThai int,
	Nguoixuat varchar(50)
	primary key (MaPX)
)

create table phieunhap(
	MaPN varchar(10),
	PN_ThoiGian date,
	PN_TrangThai bit,
	Nguoixuat varchar(50)
	primary key (MaPN)
)


create table SanPhamDat(
	STT int identity(1,1),
	MaKho varchar(10),
	MaLoai varchar(10),
	MaSP varchar(10),
	SoLuong int,
	MaPD varchar(10),
	MaPX varchar(10),
	MaPN varchar(10),
	primary key(STT),
	constraint fk_spd_sp foreign key(MaSP) references SanPham(MaSP),
	constraint fk_spd_pd foreign key(MaPD) references PhieuDat(MaPD),
	constraint fk_spd_px foreign key(MaPX) references PhieuXuat(MaPX),
	constraint fk_spd_pn foreign key(MaPN) references PhieuNhap(MaPN)
)



create table ChamCong(
	STT int identity(1,1),
	MaNV varchar(10),
	ngaylamviec date,
	checkin time,
	checkout time,
	overtime bit,
	primary key(STT),
	constraint fk_cc_nv foreign key(MaNV) references NhanVien(MaNV)
)

create table Luong(
	STT int identity(1,1),
	MaNV varchar(10), 
	thuongOT money,
	thuongKPI money, 
	luongcoban money,
	Ngaynhan date,
	primary key(STT),
	constraint fk_lg_nv foreign key(MaNV) references NhanVien(MaNV)
)



insert into NHANVIEN values 
('NV001','Trần Phú Thành','2003-09-10',1,'KH002','38553962630','09228881701','Thanhtpps22219@gmail.com','A5/2Q Quốc lộ 1A','1.png',2),
('NV002','Ngô Quốc Cường','2003-08-13',1,'KH001','37323393920','09495119355','Cuonnq332@gmail.com','226 Trần Duy Hưng','2.png',1),
('NV003','Đỗ Quang Tùng','2003-10-12',1,'KH003','60761837689','09301196106','TungDQ345@gmail.com','35 Xa Lộ Hà Nộ','3.png',3),
('NV004','Vũ Quốc Khôi','2003-05-23',1,'KH004','73825090312','09374817456','TKhoiVQ98@gmail.com','97 Bến Tre','4.png',1),
('NV005','Nguyễn Bảo Quôc','1999-06-12',1,'KH002','48169704448','09243195048','Quocnth33443@gmail.com','26 Sơn La','5.png',1)

insert into DiaDiem values
('DD001','Chi nhánh 1',N'Hà Nội'),
('DD002','Chi nhánh 2','TP_HCM'),
('DD003','Chi nhánh 3','Bình Chánh'),
('DD004','Chi nhánh 4','Đà Nẵng')

insert into KhoHang values
('KH001', N'Kho 1','NV001','DD002'),
('KH002', N'Kho 2', 'NV002','DD002'),
('KH003', N'Kho 3','NV003','DD003'),
('KH004', N'Kho 4','NV004','DD001')

--sữa
insert into LOAIHANG values
('LH001',N'Sữa bột'),
('LH002',N'Sữa tươi'),
('LH003',N'Sữa đặc'),
('LH004',N'Sữa chua')

--create table NhaCungCap(
--	MaNCC varchar(10),
--	TenNCC nvarchar(50),
--	SDT varchar(10),
--	Email varchar(50),
--	DiaChi nvarchar(100),
--	MaSoThue varchar(10)
--	primary key(MaNCC)
--)

insert into NhaCungCap values
('NCC001','Vinamilk ','09201917684','vinamilk@vinamilk.com.vn','Số 10, Đường Tân Trào, phường Tân Phú, quận 7, Tp. HCM','87925813921'),
('NCC002','TH true MILK ','09191916249','
chamsockhachhang@thmilk.vn',' Số 166 Nguyễn Thái Học, P. Quang Trung, Tp. Vinh','87925813921'),
('NCC003','Dutch Lady ','09703737034',' cskh@frieslandcampina.com.',' Xã Bình Hòa, Huyện Thuận An, Tỉnh Bình Dương','87925813921')

--create table SanPham(
--	MaSP varchar(10),
--	TenSP nvarchar(50),
--	Gia int,
--	DonVi nvarchar(20),
--	MaLoai varchar(10),
--	MaNCC varchar(10),
--	primary key(MaSP),
--	constraint fk_sp_lh foreign key(MaLoai) references LoaiHang(MaLoai)
--)
insert into SANPHAM values 
('SP001',N'Dielac Alpha Gold IQ',150000,N'Lon','LH001','NCC001','2022-08-10','2024-08-10'),
('SP002',N'Sữa tươi Vinamik',37000,N'Hộp','LH001','NCC001','2022-08-10','2023-08-10'),
('SP003',N'Dielac Grow Plus:',150000,N'Lon','LH001','NCC001','2022-08-10','2024-08-10'),
('SP004',N'Dielac Alpha',1599000,N'Lon','LH001','NCC001','2022-08-10','2024-08-10'),
('SP005',N'True Happiness',155500,N'Hộp','LH002','NCC002','2022-08-10','2022-10-10'),
('SP006',N'DUTCH LADY MAMA',40000,N'Hộp','LH001','NCC003','2022-08-10','2022-12-10'),
('SP007',N'Frisomum Gold',15000000,N'Hộp','LH002','NCC003','2022-08-10','2022-11-10'),
('SP008',N'Friso Gold',2000000,N'Lon','LH003','NCC003','2022-08-10','2022-11-24')



--create table Khu(
--	MaKhu varchar(10),
--	TenKhu nvarchar(50),
--	MaKho varchar(10),
--	MaLoai varchar(10),
--	Succhua int,
--	primary key(MaKhu),
--	constraint fk_k_kh foreign key(MaKho) references KhoHang(Makho),
--	constraint fk_k_lh foreign key(MaLoai) references LoaiHang(MaLoai)
--)

-- 1 kho 4 khu --> 4 kho là 16 khu
insert into Khu values
('MK001','Khu A','KH001','LH001',3000),
('MK011','Khu B','KH001','LH002',3000),
('MK111','Khu C','KH001','LH003',3000),

('MK002','Khu A','KH002','LH003',3000),
('MK022','Khu B','KH002','LH001',3000),
('MK222','Khu C','KH002','LH002',3000),

('MK003','Khu A','KH003','LH002',3000),
('MK033','Khu B','KH003','LH001',3000),
('MK333','Khu C','KH003','LH003',3000),

('MK004','Khu A','KH004','LH001',3000),
('MK044','Khu B','KH004','LH002',3000),
('MK444','Khu C','KH004','LH001',3000)


--create table SoLuongSP(
--	MaKho varchar(10),
--	MaSP varchar(10),
--	SoLuong int,
--	primary key (Makho, MaSP),
--	constraint fk_slsp_k foreign key(makho) references khohang(makho),
--	constraint fk_slsp_sp foreign key(masp) references sanpham(masp)
--)
insert into SoLuongSP values
('KH001','SP001',110),
('KH002','SP002',80),
('KH003','SP003',100),
('KH004','SP004',11),
('KH001','SP006',110),
('KH002','SP007',80),
('KH003','SP008',100)

--create table phieudat(
--	MaPD varchar(10),
--	PD_ThoiGian date,
--	PD_TrangThai bit,
--	NguoiDat varchar(50)
--	primary key(MaPD)

--)


--create table phieuxuat(
--	MaPX varchar(10),
--	PX_ThoiGian date,
--	PX_TrangThai int,
--	Nguoixuat varchar(50)
--	primary key (MaPX)
--)

--create table phieunhap(
--	MaPN varchar(10),
--	PN_ThoiGian date,
--	PN_TrangThai bit,
--	Nguoixuat varchar(50)
--	primary key (MaPN)
--)


--create table SanPhamDat(
--	STT int identity(1,1),
--	MaKho varchar(10),
--	MaLoai varchar(10),
--	MaSP varchar(10),
--	SoLuong int,
--	MaPD varchar(10),
--	MaPX varchar(10),
--	MaPN varchar(10),
--	primary key(STT),
--	constraint fk_spd_sp foreign key(MaSP) references SanPham(MaSP),
--	constraint fk_spd_pd foreign key(MaPD) references PhieuDat(MaPD),
--	constraint fk_spd_px foreign key(MaPX) references PhieuXuat(MaPX),
--	constraint fk_spd_pn foreign key(MaPN) references PhieuNhap(MaPN)
--)



--create table ChamCong(
--	STT int identity(1,1),
--	MaNV varchar(10),
--	ngaylamviec date,
--	checkin time,
--	checkout time,
--	overtime bit,
--	primary key(STT),
--	constraint fk_cc_nv foreign key(MaNV) references NhanVien(MaNV)
--)

--create table Luong(
--	STT int identity(1,1),
--	MaNV varchar(10), 
--	thuongOT money,
--	thuongKPI money, 
--	luongcoban money,
--	Ngaynhan date,
--	primary key(STT),
--	constraint fk_lg_nv foreign key(MaNV) references NhanVien(MaNV)
--)
