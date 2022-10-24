/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view.NhapXuat;

import controller.productDAO.PhieuNhapDao;
import model.DatabaseHelper;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import entity.product.*;

/**
 *
 * @author ADMIN
 */
public class PhieuNhap extends javax.swing.JFrame {

    /**
     * Creates new form PhieuXuat
     */
    public PhieuNhap() {
        initComponents();
        loaddatapn();
        loadslsp();
        setLocationRelativeTo(null);
        btnNhap.setEnabled(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    private static ArrayList<ETTPhieuNhap> listpn = new ArrayList<>();

    public static ArrayList<ETTPhieuNhap> getlistpn() {
        return listpn;
    }

    private static ArrayList<SoLuongSP> listslsp = new ArrayList<>();

    public static ArrayList<SoLuongSP> getlistslsp() {
        return listslsp;
    }
    DATHANG formdh = new DATHANG();
    ArrayList<HangHoaKhoTong> listhhkt = formdh.getlist();

    DefaultTableModel tblmdPNCT;
    PhieuXuat formpx = new PhieuXuat();

    public String keyidentityPN() {
        String MaPNid = "PN";
        String str = "";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DatabaseHelper.oppenConnection();
            String query = "select * from PhieuNhap";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
//            PreparedStatement ps1 = con.prepareStatement(query);
//            ResultSet rs2 = ps1.executeQuery(query);
            int keyid = 1;
            while (rs.next()) {
                keyid++;
            }
            str = String.format("%05d", keyid);
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return MaPNid += str;
    }

    phieudat formpd = new phieudat();
    ArrayList<SanPhamDat> listspd = formpd.getlistspd();
    DefaultTableModel tblmdpx;
    PhieuNhapDao dao = new PhieuNhapDao();

    public void loaddatapn() {
        listpn.clear();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DatabaseHelper.oppenConnection();
            String query = "select * from PhieuNhap";

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(query);

//            PreparedStatement ps1 = con.prepareStatement(query);
//            ResultSet rs2 = ps1.executeQuery(query);
            while (rs.next()) {
                ETTPhieuNhap pn = new ETTPhieuNhap();
                pn.setMaPN(rs.getString(1));
                pn.setPN_ThoiGian(rs.getString(2));
                pn.setPN_TrangThai(rs.getInt(3));
                pn.setMaKho(rs.getString(4));
                pn.setNguoiNhap(rs.getString(5));
                listpn.add(pn);
            }
            filltotablPN();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void loadslsp() {
        listslsp.clear();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DatabaseHelper.oppenConnection();
            String query = "select KhoHang"
                    + ".MaKho, LoaiHang.MaLoai, TenLoai, SanPham.MaSP, TenSP, SoLuong\n"
                    + "from KhoHang join SoLuongSP on KhoHang.MaKho = SoLuongSP.MaKho \n"
                    + "join SanPham on SoLuongSP.MaSP = SanPham.MaSP\n"
                    + "	join LoaiHang on SanPham.MaLoai = LoaiHang.MaLoai";

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(query);

//            PreparedStatement ps1 = con.prepareStatement(query);
//            ResultSet rs2 = ps1.executeQuery(query);
            while (rs.next()) {
                SoLuongSP slsp = new SoLuongSP();
                slsp.setMaKho(rs.getString(1));
                slsp.setMaloai(rs.getString(2));
                slsp.setTenLoai(rs.getString(3));
                slsp.setMaSP(rs.getString(4));
                slsp.setTenSP(rs.getString(5));
                slsp.setSoLuong(rs.getInt(6));
                listslsp.add(slsp);
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void ChiTietPN() {
        jTabbedPane1.setSelectedIndex(1);
        int index = tblPhieunhap.getSelectedRow();
        String MaPNfill = tblPhieunhap.getValueAt(index, 0).toString();
        txtmaphieunhap.setText(MaPNfill);
        filltotblCTPN(MaPNfill);
    }

    public void filltotblCTPN(String MaPNfill) {

        for (ETTPhieuNhap pn : listpn) {
            if (pn.getMaPN().equalsIgnoreCase(MaPNfill)) {
                try {
                    String date = pn.getPN_ThoiGian();
                    java.util.Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
                    dtcThoiGian.setDate(date2);
                    txtNguoiNhap.setText(pn.getNguoiNhap());
                    txtkhonhap.setText(pn.getMaKho());
                    String TrangThai = "";
                    if (pn.getPN_TrangThai() == 1) {
                        radDaXuat.setSelected(true);
                        btnNhap.setEnabled(true);
                    } else if (pn.getPN_TrangThai() == 2) {
                        radDaNhap.setSelected(true);
                        btnNhap.setEnabled(false);
                    }
                } catch (ParseException ex) {
                    System.out.println(ex);
                    Logger.getLogger(phieudat.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void UpdatePhieuNhap() {
        try {
            String MaPN = txtmaphieunhap.getText();
            Connection con = DatabaseHelper.oppenConnection();

            String query2 = "UPDATE PhieuNhap SET PN_ThoiGian = ?, PN_TrangThai = ? WHERE MaPN = ?";
            PreparedStatement ps2 = con.prepareStatement(query2);
            ps2.setString(1, LocalDate.now() + "");
            ps2.setInt(2, 2);
            ps2.setString(3, MaPN);
            ps2.executeUpdate();
            JOptionPane.showMessageDialog(this, "Đã xác nhận nhập sản phẩm thành công");
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(phieudat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updatePX() {
        try {
            String MaPX = "";
            Connection con = DatabaseHelper.oppenConnection();
            for (SanPhamDat spd : listspd) {
                if (spd.getMaPN().equalsIgnoreCase(txtmaphieunhap.getText())) {
                    MaPX = spd.getMaPX();
                    break;
                }
            }

            String query2 = "UPDATE PhieuXuat SET PX_TrangThai = ? WHERE MaPX = ?";
            PreparedStatement ps2 = con.prepareStatement(query2);
            ps2.setInt(1, 3);
            ps2.setString(2, MaPX);
            ps2.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(phieudat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void filltotablPN() {

        int sel = cboTrangThai.getSelectedIndex();
        tblmdpx = (DefaultTableModel) tblPhieunhap.getModel();
        tblmdpx.setRowCount(0);
        Object row[];
        switch (sel) {
            case 0:
                for (ETTPhieuNhap pn : listpn) {
                    String TrangThai = "";
                    switch (pn.getPN_TrangThai()) {
                        case 1 ->
                            TrangThai = "Đã Xuất";
                        case 2 ->
                            TrangThai = "Đã Nhập";
                        default -> {
                            continue;
                        }
                    }
                    row = new Object[]{pn.getMaPN(), pn.getMaKho(), pn.getPN_ThoiGian(), TrangThai};
                    tblmdpx.addRow(row);
                }
                break;

            case 1:
                for (ETTPhieuNhap pn : listpn) {
                    String TrangThai = "";
                    if (pn.getPN_TrangThai() == 1) {
                        TrangThai = "Đã Xuất";
                        row = new Object[]{pn.getMaPN(), pn.getMaKho(), pn.getPN_ThoiGian(), TrangThai};
                        tblmdpx.addRow(row);
                    }
                }
                break;

            case 2:
                for (ETTPhieuNhap pn : listpn) {
                    String TrangThai = "";
                    if (pn.getPN_TrangThai() == 2) {
                        TrangThai = "Đã Nhập";
                        row = new Object[]{pn.getMaPN(), pn.getMaKho(), pn.getPN_ThoiGian(), TrangThai};
                        tblmdpx.addRow(row);
                    }
                }
                break;
            default:
                throw new AssertionError();
        }
    }

//    public boolean booleancheckListSLSP(String TenSP, String MaKho) {
//        boolean checklistslsp = false;
//        for (SoLuongSP slsp : listslsp) {
//            if (slsp.getTenSP().equalsIgnoreCase(TenSP) && slsp.getMaKho().equalsIgnoreCase(MaKho)) {
//                checklistslsp = true;
//                break;
//            }
//        }
//        return checklistslsp;
//    }
//
//    public boolean booleancheckLisHHKT(String TenSP) {
//        boolean checklisthhkt = false;
//        for (HangHoaKhoTong hh : listhhkt) {
//            if (hh.getTenSP().equalsIgnoreCase(TenSP)) {
//                checklisthhkt = true;
//                break;
//            }
//        }
//        return checklisthhkt;
//    }

    public String checkListSLSP(String TenSP, String MaKho) {
        String MaSP = "";
        for (SoLuongSP slsp : listslsp) {
            if (slsp.getTenSP().equalsIgnoreCase(TenSP) && slsp.getMaKho().equalsIgnoreCase(MaKho)) {
                MaSP = slsp.getMaSP();
                System.out.println("Checkup "+ MaSP);
                break;
            }
        }
        return MaSP;
    }

    public String checkLisHHKT(String TenSP) {
        String MaSP = "";
        for (HangHoaKhoTong hh : listhhkt) {
            if (hh.getTenSP().equalsIgnoreCase(TenSP)) {
                MaSP = hh.getMaSP();
                System.out.println("Checkis "+ MaSP);
                break;
            }
        }
        return MaSP;
    }

    public void updateSLSP() {
        //tblmdPNCT = (DefaultTableModel) tblphieunhapchitiet.getModel();
        String MaSP = "", TenSP = "", MaKho = "";
        int Soluong = 0;
        boolean checkud = false, chekcis = false;
        for (int i = 0; i < tblphieunhapchitiet.getRowCount(); i++) {
            MaSP = "";
            boolean checkUP;
            boolean checkIS;
            
            TenSP = tblphieunhapchitiet.getValueAt(i, 1).toString();
            System.out.println("Ten " + TenSP);
            MaKho = txtkhonhap.getText();
            System.out.println("Kho" + MaKho);
            Soluong = Integer.parseInt(tblphieunhapchitiet.getValueAt(i, 2).toString());
            System.out.println(Soluong);
            if(!checkListSLSP(TenSP, MaKho).equalsIgnoreCase("")){
                MaSP = checkListSLSP(TenSP, MaKho);                
                UpdateSLKH(MaSP, MaKho, Soluong);
                checkud = true;               
            }else{
                MaKho = txtkhonhap.getText();
                System.out.println(MaKho);
                MaSP = checkLisHHKT(TenSP);                
                InsertSLKH( MaKho, MaSP, Soluong);
                chekcis = true;
            }

            
        }
//        System.out.println(checkud);
//        System.out.println(chekcis);
        if (checkud == true) {
            JOptionPane.showMessageDialog(this, "Đã Cập nhật số lượng Sản Phẩm Vào Kho " + MaKho);
        }
        if (chekcis == true) {
            JOptionPane.showMessageDialog(this, "Đã Thêm Sản Phẩm Mới Vào Kho " + MaKho);
        }

    }

    public void UpdateSLKH(String MaSP, String MaKho, int SoLuong) {
        try {
            Connection con = DatabaseHelper.oppenConnection();
            String query2 = "UPDATE SoLuongSP SET SoLuong = SoLuong + ? WHERE MaSP = ? AND MaKho = ?";
            PreparedStatement ps2 = con.prepareStatement(query2);
            ps2.setInt(1, SoLuong);
            ps2.setString(2, MaSP);
            ps2.setString(3, MaKho);
            ps2.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(phieudat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void InsertSLKH( String MaKho,String MaSP, int SoLuong) {
        try {
            System.out.println("MaKhois : " + MaKho);
            System.out.println("MaSPis : " + MaSP);
            Connection con = DatabaseHelper.oppenConnection();
            String query2 = "Insert Into SoLuongSP (MaKho, MaSP, SoLuong) VALUES (?, ? , ?) ";
            PreparedStatement ps2 = con.prepareStatement(query2);
            ps2.setString(1, MaKho);
            ps2.setString(2, MaSP);
            ps2.setInt(3, SoLuong);
            ps2.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(phieudat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        TrangThai = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txttimkiem = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnXemChiTiet = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        cboTrangThai = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPhieunhap = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtmaphieunhap = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        lblNguoiNhap = new javax.swing.JLabel();
        txtNguoiNhap = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtkhonhap = new javax.swing.JTextField();
        btnNhap = new javax.swing.JButton();
        btnxong = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        radDaXuat = new javax.swing.JRadioButton();
        radDaNhap = new javax.swing.JRadioButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblphieunhapchitiet = new javax.swing.JTable();
        dtcThoiGian = new com.toedter.calendar.JDateChooser();
        btnfist = new javax.swing.JButton();
        btnblack = new javax.swing.JButton();
        btnnext = new javax.swing.JButton();
        btnlast = new javax.swing.JButton();
        mnbQLKH = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        jmnDatHang = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jmnPhieuDat = new javax.swing.JMenuItem();
        jmnPhieuXuat = new javax.swing.JMenuItem();
        jmnPhieuNhap = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jmnQLNV = new javax.swing.JMenuItem();
        jmnChamCong = new javax.swing.JMenuItem();
        jmnLuong = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        jMenu9 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Xuất Hàng");

        txttimkiem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jLabel2.setText("Tìm kiếm");

        btnXemChiTiet.setText("Xem Chi Tiết");
        btnXemChiTiet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemChiTietActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 160, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 28, Short.MAX_VALUE)
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel4.setText("Kho");

        jLabel5.setText("Trạng Thái");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cboTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Đã Xuất", "Đã Nhập" }));
        cboTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTrangThaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboTrangThai, 0, 150, Short.MAX_VALUE)
                    .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 158, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 151, Short.MAX_VALUE)
        );

        tblPhieunhap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã Phiếu Nhập", "Kho", "Thời Gian", "Trạng Thái"
            }
        ));
        tblPhieunhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblPhieunhapMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblPhieunhap);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(93, 93, 93)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txttimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnXemChiTiet)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 597, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(txttimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnXemChiTiet)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))))
        );

        jTabbedPane1.addTab("Phiếu Nhập", jPanel1);

        jLabel6.setText("Mã Phiếu Nhập :");

        jLabel7.setText("Thời Gian");

        lblNguoiNhap.setText("Người Nhập");

        jLabel9.setText("Kho Nhập");

        txtkhonhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtkhonhapActionPerformed(evt);
            }
        });

        btnNhap.setText("Nhập");
        btnNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhapActionPerformed(evt);
            }
        });

        btnxong.setText("XONG");

        jLabel10.setText("Trạng Thái");

        TrangThai.add(radDaXuat);
        radDaXuat.setText("Đã Xuất");

        TrangThai.add(radDaNhap);
        radDaNhap.setText("Đã Nhập");

        tblphieunhapchitiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Tên Loại", "Sản Phẩm", "Số Lượng"
            }
        ));
        tblphieunhapchitiet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblphieunhapchitietMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tblphieunhapchitiet);

        dtcThoiGian.setDateFormatString("yyyy-MM-dd");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(radDaXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(radDaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(dtcThoiGian, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtmaphieunhap, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 185, Short.MAX_VALUE)
                        .addComponent(btnNhap)
                        .addGap(68, 68, 68)
                        .addComponent(btnxong)
                        .addGap(74, 74, 74))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNguoiNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(50, 50, 50)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNguoiNhap, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                            .addComponent(txtkhonhap))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 758, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtmaphieunhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNguoiNhap)
                    .addComponent(txtNguoiNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(jLabel9)
                        .addComponent(txtkhonhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(dtcThoiGian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnxong, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10)
                        .addComponent(radDaXuat)
                        .addComponent(radDaNhap)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Phiếu Nhập chi tiết", jPanel2);

        btnfist.setText("<<");

        btnblack.setText("<");

        btnnext.setText(">");

        btnlast.setText(">>");

        jMenu3.setText("Tổng Quan");
        mnbQLKH.add(jMenu3);

        jMenu4.setText("Danh Mục Sản Phẩm");
        mnbQLKH.add(jMenu4);

        jMenu5.setText("Nhập, Xuất");

        jmnDatHang.setText("Đặt Hàng");
        jmnDatHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmnDatHangActionPerformed(evt);
            }
        });
        jMenu5.add(jmnDatHang);
        jMenu5.add(jSeparator1);

        jmnPhieuDat.setText("Phiếu Đặt");
        jmnPhieuDat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmnPhieuDatActionPerformed(evt);
            }
        });
        jMenu5.add(jmnPhieuDat);

        jmnPhieuXuat.setText("Phiếu Xuất");
        jmnPhieuXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmnPhieuXuatActionPerformed(evt);
            }
        });
        jMenu5.add(jmnPhieuXuat);

        jmnPhieuNhap.setText("Phiếu Nhập");
        jmnPhieuNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmnPhieuNhapActionPerformed(evt);
            }
        });
        jMenu5.add(jmnPhieuNhap);

        mnbQLKH.add(jMenu5);

        jMenu6.setText("Nhân Viên");

        jmnQLNV.setText("Quản Lý NV");
        jMenu6.add(jmnQLNV);

        jmnChamCong.setText("Chấm Công");
        jMenu6.add(jmnChamCong);

        jmnLuong.setText("Lương");
        jmnLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmnLuongActionPerformed(evt);
            }
        });
        jMenu6.add(jmnLuong);

        mnbQLKH.add(jMenu6);

        jMenu7.setText("Nhà Cung Cấp");
        mnbQLKH.add(jMenu7);

        jMenu9.setText("Thống Kê");
        mnbQLKH.add(jMenu9);

        setJMenuBar(mnbQLKH);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(268, 268, 268)
                .addComponent(btnfist)
                .addGap(40, 40, 40)
                .addComponent(btnblack)
                .addGap(35, 35, 35)
                .addComponent(btnnext)
                .addGap(41, 41, 41)
                .addComponent(btnlast)
                .addGap(269, 269, 269))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 847, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnlast)
                    .addComponent(btnnext)
                    .addComponent(btnblack)
                    .addComponent(btnfist))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhapActionPerformed
        // TODO add your handling code here:
        int ret = JOptionPane.showConfirmDialog(this, "Xác nhận nhập đơn hàng này?");
        if (ret == JOptionPane.YES_OPTION) {
            if (txtmaphieunhap.getText().trim().length() == 0) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn phiếu đặt", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                UpdatePhieuNhap();
                updatePX();
                loaddatapn();
                updateSLSP();
                jTabbedPane1.setSelectedIndex(0);

            }
        }
    }//GEN-LAST:event_btnNhapActionPerformed

    private void tblPhieunhapMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPhieunhapMousePressed
        // TODO add your handling code here:
        if (tblPhieunhap.getRowCount() >= 0) {
            if (evt.getClickCount() == 2) {
                ChiTietPN();
                loadtblPhieuNhap();
                jTabbedPane1.setSelectedIndex(1);

            }
        }
    }//GEN-LAST:event_tblPhieunhapMousePressed

    private void tblphieunhapchitietMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblphieunhapchitietMousePressed
        // TODO add your handling code here:

    }//GEN-LAST:event_tblphieunhapchitietMousePressed

    private void loadtblPhieuNhap() {
        int index = tblPhieunhap.getSelectedRow();
        String mapn = tblPhieunhap.getValueAt(index, 0).toString();

        DefaultTableModel tblmdctpn = (DefaultTableModel) tblphieunhapchitiet.getModel();
        tblmdctpn.setRowCount(0);

        List<Object[]> list = dao.getBangCT(mapn);

        for (Object[] rows : list) {
            tblmdctpn.addRow(rows);
        }
    }

    private void btnXemChiTietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemChiTietActionPerformed
        // TODO add your handling code here:
        if (tblPhieunhap.getRowCount() >= 0) {
            ChiTietPN();
            loadtblPhieuNhap();
            jTabbedPane1.setSelectedIndex(1);
        }

    }//GEN-LAST:event_btnXemChiTietActionPerformed

    private void cboTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTrangThaiActionPerformed
        // TODO add your handling code here:
        filltotablPN();
    }//GEN-LAST:event_cboTrangThaiActionPerformed

    private void jmnDatHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmnDatHangActionPerformed
        // TODO add your handling code here:
        DATHANG formdh = new DATHANG();
        formdh.setVisible(true);
    }//GEN-LAST:event_jmnDatHangActionPerformed

    private void jmnPhieuDatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmnPhieuDatActionPerformed
        // TODO add your handling code here:
        phieudat formpd = new phieudat();
        formpd.setVisible(true);
    }//GEN-LAST:event_jmnPhieuDatActionPerformed

    private void jmnPhieuXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmnPhieuXuatActionPerformed
        // TODO add your handling code here:
        PhieuXuat formpx = new PhieuXuat();
        formpx.setVisible(true);
    }//GEN-LAST:event_jmnPhieuXuatActionPerformed

    private void jmnPhieuNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmnPhieuNhapActionPerformed
        // TODO add your handling code here:
        PhieuNhap formpn = new PhieuNhap();
        formpn.setVisible(true);
    }//GEN-LAST:event_jmnPhieuNhapActionPerformed

    private void jmnLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmnLuongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jmnLuongActionPerformed

    private void txtkhonhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtkhonhapActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtkhonhapActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PhieuNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PhieuNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PhieuNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PhieuNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PhieuNhap().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup TrangThai;
    private javax.swing.JButton btnNhap;
    private javax.swing.JButton btnXemChiTiet;
    private javax.swing.JButton btnblack;
    private javax.swing.JButton btnfist;
    private javax.swing.JButton btnlast;
    private javax.swing.JButton btnnext;
    private javax.swing.JButton btnxong;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboTrangThai;
    private com.toedter.calendar.JDateChooser dtcThoiGian;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JMenuItem jmnChamCong;
    private javax.swing.JMenuItem jmnDatHang;
    private javax.swing.JMenuItem jmnLuong;
    private javax.swing.JMenuItem jmnPhieuDat;
    private javax.swing.JMenuItem jmnPhieuNhap;
    private javax.swing.JMenuItem jmnPhieuXuat;
    private javax.swing.JMenuItem jmnQLNV;
    private javax.swing.JLabel lblNguoiNhap;
    private javax.swing.JMenuBar mnbQLKH;
    private javax.swing.JRadioButton radDaNhap;
    private javax.swing.JRadioButton radDaXuat;
    private javax.swing.JTable tblPhieunhap;
    private javax.swing.JTable tblphieunhapchitiet;
    private javax.swing.JTextField txtNguoiNhap;
    private javax.swing.JTextField txtkhonhap;
    private javax.swing.JTextField txtmaphieunhap;
    private javax.swing.JTextField txttimkiem;
    // End of variables declaration//GEN-END:variables
}
