/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view.NhapXuat;

import model.DatabaseHelper;
import controller.productDAO.PhieuDatDao;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import entity.product.ETTPhieuDat;
import entity.product.*;

/**
 *
 * @author ADMIN
 */
public class phieudat extends javax.swing.JFrame {

    /**
     * Creates new form phieudat
     */
    public phieudat() {
        initComponents();
        loaddatapd();
        loaddataSPD();
        btnxacnhan.setEnabled(false);
        btnhoangtac.setEnabled(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private static ArrayList<ETTPhieuDat> listpd = new ArrayList<>();
    private static ArrayList<SanPhamDat> listspd = new ArrayList<>();

    ArrayList<HangHoaKhoTong> listhhkt = DATHANG.getlist();
    PhieuDatDao daoXNHH = new PhieuDatDao();

    public static ArrayList<ETTPhieuDat> getlist() {
        return listpd;
    }

    public static ArrayList<SanPhamDat> getlistspd() {
        return listspd;
    }

    DefaultTableModel tblmdpd;

    DATHANG formdh = new DATHANG();

    private void loadtblCTPD() {
        int column = 0;
        int row = tblphieudat.getSelectedRow();
        String mapd = tblphieudat.getModel().getValueAt(row, column).toString();
        DefaultTableModel tblmdctpd = (DefaultTableModel) tblCTPD.getModel();
        tblmdctpd.setRowCount(0);

        List<Object[]> list = daoXNHH.getBangCT(mapd);

        for (Object[] rows : list) {
            tblmdctpd.addRow(rows);
        }
    }

    public String keyidentity() {

        String MaPDid = "PD";
        String str = "";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DatabaseHelper.oppenConnection();
            String query = "select * from PhieuDat";
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

        return MaPDid += str;
    }

    public void loaddatapd() {
        listpd.clear();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DatabaseHelper.oppenConnection();
            String query = "select * from PhieuDat";

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(query);

//            PreparedStatement ps1 = con.prepareStatement(query);
//            ResultSet rs2 = ps1.executeQuery(query);
            while (rs.next()) {
                if (rs.getBoolean(6) == false) {
                    ETTPhieuDat pd = new ETTPhieuDat();
                    pd.setMaPD(rs.getString(1));
                    pd.setPD_ThoiGian(rs.getString(2));
                    pd.setPD_TrangThai(rs.getBoolean(3));
                    pd.setMakho(rs.getString(4));
                    pd.setNguoiDat(rs.getString(5));
                    listpd.add(pd);
                }
            }
            filltotablPD();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void loaddataSPD() {
        listspd.clear();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DatabaseHelper.oppenConnection();
            String query = "select * from SanPhamDat";

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(query);

//            PreparedStatement ps1 = con.prepareStatement(query);
//            ResultSet rs2 = ps1.executeQuery(query);
            while (rs.next()) {
                if (rs.getBoolean(8) == false) {
                    SanPhamDat spd = new SanPhamDat();
                    spd.setMaLoai(rs.getString(2));
                    spd.setMaSP(rs.getString(3));
                    spd.setSoLuong(rs.getInt(4));
                    spd.setMaPD(rs.getString(5));
                    spd.setMaPX(rs.getString(6));
                    spd.setMaPN(rs.getString(7));
                    listspd.add(spd);
                }
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

//    public void filltotblPD() {
//        tblmdpd = (DefaultTableModel) tblphieudat.getModel();
//        tblmdpd.setRowCount(0);
//        Object row[];
//        for (ETTPhieuDat pd : listpd) {
//            String TrangThai = "";
//            if (pd.isPD_TrangThai() == false) {
//                TrangThai = "Yêu Cầu";
//            } else {
//                TrangThai = "Xác nhận";
//            }
//            row = new Object[]{pd.getMaPD(), pd.getMakho(), pd.getPD_ThoiGian(), TrangThai};
//            tblmdpd.addRow(row);
//        }
//    }
    public void ChiTietPD() {
        jTabbedPane1.setSelectedIndex(1);
        int index = tblphieudat.getSelectedRow();
        String MaPNfill = tblphieudat.getValueAt(index, 0).toString();
        txtmaphietdat.setText(MaPNfill);
        filltotblCTPD(MaPNfill);
    }

    public void filltotblCTPD(String MaPNfill) {

        for (ETTPhieuDat pd : listpd) {
            if (pd.getMaPD().equalsIgnoreCase(MaPNfill)) {
                try {
                    String date = pd.getPD_ThoiGian();
                    Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
                    dtcThoiGian.setDate(date2);
                    txtnguoidat.setText(pd.getNguoiDat());
                    txtkho.setText(pd.getMakho());
                    if (pd.isPD_TrangThai() == false) {
                        radYeuCau.setSelected(true);
                        btnxacnhan.setEnabled(true);
                        btnhoangtac.setEnabled(true);
                    } else {
                        radXacNhan.setSelected(true);
                        btnxacnhan.setEnabled(false);
                        btnhoangtac.setEnabled(false);
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(phieudat.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public String datecovert(Date dt) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String date = df.format(dt);
        return date;
    }

    public String MaSPinlist(String TenSP) {
        String MaSP = "";
        for (HangHoaKhoTong hh : listhhkt) {
            if (hh.getTenSP().equalsIgnoreCase(TenSP)) {
                MaSP = hh.getMaSP();
            }
        }
        return MaSP;
    }

    public void UpdateSoluong() {
        int Slt = 0;
        String MaSP = "";
        try {
            Connection con = DatabaseHelper.oppenConnection();
            for (int i = 0; i < tblCTPD.getRowCount(); i++) {
                String query2 = "UPDATE SoLuongSP SET SoLuong = SoLuong - ? WHERE MaSP = ? and MaKho = 'KHTong'";
                MaSP = MaSPinlist(tblCTPD.getValueAt(i, 1).toString());
                Slt = Integer.parseInt(tblCTPD.getValueAt(i, 2).toString());
                PreparedStatement ps2 = con.prepareStatement(query2);
                ps2.setInt(1, Slt);
                ps2.setString(2, MaSP);
                ps2.executeUpdate();
            }

            radXacNhan.setSelected(true);
            String query = "UPDATE PhieuDat SET PD_TrangThai = 1 WHERE MaPD = ? ";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, txtmaphietdat.getText());
            ps.executeUpdate();
            loaddatapd();
            filltotblCTPD(txtmaphietdat.getText());
            filltotablPD();
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void DeleteSPD() {
        try {
            Connection con = DatabaseHelper.oppenConnection();
            String query2 = "DELETE FROM SanPhamDat WHERE MaPD = ?";
            PreparedStatement ps2 = con.prepareStatement(query2);
            ps2.setString(1, txtmaphietdat.getText());
            ps2.executeUpdate();
            JOptionPane.showMessageDialog(this, "Đã Hủy Yêu Cầu Đặt Hàng");

            jTabbedPane1.setSelectedIndex(0);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void UpdatePhieuXuat() {
        try {
            String MaPX = "";
            Connection con = DatabaseHelper.oppenConnection();
            for (SanPhamDat spd : listspd) {
                if (spd.getMaPD().equalsIgnoreCase(txtmaphietdat.getText())) {
                    MaPX = spd.getMaPX();
                    break;
                }
            }
            String query2 = "UPDATE PhieuXuat SET PX_ThoiGian = ?, PX_TrangThai = ?, MaKho = ?, Nguoixuat = ? WHERE MaPX = ?";
            PreparedStatement ps2 = con.prepareStatement(query2);
            ps2.setString(1, "");
            ps2.setInt(2, 1);
            ps2.setString(3, "KH001");
            ps2.setString(4, "GDKHT");
            ps2.setString(5, MaPX);
            ps2.executeUpdate();
            JOptionPane.showMessageDialog(this, "Đã xác nhận phiếu đặt, vui lòng kiểm tra phiếu xuất");
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
        jScrollPane2 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        TrangThaiPD = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        cboTrangThai = new javax.swing.JComboBox<>();
        txttimkiem = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        btnxemchitiet = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblphieudat = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtmaphietdat = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtkho = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtnguoidat = new javax.swing.JTextField();
        radXacNhan = new javax.swing.JRadioButton();
        radYeuCau = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCTPD = new javax.swing.JTable();
        btnxacnhan = new javax.swing.JButton();
        btnhoangtac = new javax.swing.JButton();
        BtnXong = new javax.swing.JButton();
        dtcThoiGian = new com.toedter.calendar.JDateChooser();
        menu1 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        jMenu9 = new javax.swing.JMenu();

        jScrollPane2.setViewportView(jTree1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PHIẾU ĐẶT");

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel6.setText("Trạng Thái");

        cboTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Yêu Cầu", "Xác Nhận" }));
        cboTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTrangThaiActionPerformed(evt);
            }
        });

        txttimkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttimkiemActionPerformed(evt);
            }
        });

        jLabel7.setText("Tìm Kiếm");

        btnxemchitiet.setText("Xem Chi Tiết");
        btnxemchitiet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxemchitietActionPerformed(evt);
            }
        });

        tblphieudat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã Phiếu Đặt", "Kho", "Thời Gian", "Trạng Thái"
            }
        ));
        tblphieudat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblphieudatMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblphieudatMouseReleased(evt);
            }
        });
        jScrollPane3.setViewportView(tblphieudat);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(txttimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(btnxemchitiet))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 692, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txttimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnxemchitiet))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(cboTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(21, 21, 21)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Phiếu Đặt", jPanel2);

        jLabel1.setText("Mã Phiếu Đặt");

        jLabel2.setText("Kho");

        jLabel3.setText("Trạng Thái");

        jLabel4.setText("Thời Gian");

        jLabel5.setText("Người Đặt");

        TrangThaiPD.add(radXacNhan);
        radXacNhan.setText("Xác Nhận");

        TrangThaiPD.add(radYeuCau);
        radYeuCau.setText("Yêu Cầu");

        tblCTPD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Loại Hàng", "Tên Sản Phẩm", "Số Lượng"
            }
        ));
        jScrollPane1.setViewportView(tblCTPD);

        btnxacnhan.setText("XÁC NHẬN");
        btnxacnhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxacnhanActionPerformed(evt);
            }
        });

        btnhoangtac.setText("HOÀN TÁC");
        btnhoangtac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhoangtacActionPerformed(evt);
            }
        });

        BtnXong.setText("XONG");
        BtnXong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnXongActionPerformed(evt);
            }
        });

        dtcThoiGian.setDateFormatString("yyyy-MM-dd");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtmaphietdat, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtkho, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(168, 168, 168)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(dtcThoiGian, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtnguoidat, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(radYeuCau, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(radXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(128, 128, 128)
                                .addComponent(btnxacnhan)
                                .addGap(18, 18, 18)
                                .addComponent(btnhoangtac)
                                .addGap(27, 27, 27)
                                .addComponent(BtnXong)))
                        .addGap(0, 36, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(txtmaphietdat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4))
                    .addComponent(dtcThoiGian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtkho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtnguoidat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(radXacNhan)
                    .addComponent(radYeuCau)
                    .addComponent(btnxacnhan)
                    .addComponent(btnhoangtac)
                    .addComponent(BtnXong))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Chi Tiết Phiếu Đặt", jPanel1);

        jMenu3.setText("Tổng Quan");
        menu1.add(jMenu3);

        jMenu4.setText("Danh Mục Sản Phẩm");
        menu1.add(jMenu4);

        jMenu5.setText("Nhập, Xuất");

        jMenuItem1.setText("Đặt Hàng");
        jMenu5.add(jMenuItem1);
        jMenu5.add(jSeparator1);

        jMenuItem2.setText("Phiếu Đặt");
        jMenu5.add(jMenuItem2);

        jMenuItem3.setText("Phiếu Xuất");
        jMenu5.add(jMenuItem3);

        jMenuItem4.setText("Phiếu Nhập");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem4);

        menu1.add(jMenu5);

        jMenu6.setText("Nhân Viên");

        jMenuItem5.setText("Quản Lý NV");
        jMenu6.add(jMenuItem5);

        jMenuItem6.setText("Chấm Công");
        jMenu6.add(jMenuItem6);

        jMenuItem7.setText("Lương");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem7);

        menu1.add(jMenu6);

        jMenu7.setText("Nhà Cung Cấp");
        menu1.add(jMenu7);

        jMenu9.setText("Thống Kê");
        menu1.add(jMenu9);

        setJMenuBar(menu1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txttimkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttimkiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttimkiemActionPerformed

    private void tblphieudatMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblphieudatMouseReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_tblphieudatMouseReleased

    private void btnxemchitietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxemchitietActionPerformed
        // TODO add your handling code here:      
        if (tblphieudat.getRowCount() >= 0) {
            ChiTietPD();
            loadtblCTPD();
        }
        
    }//GEN-LAST:event_btnxemchitietActionPerformed

    private void tblphieudatMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblphieudatMousePressed
        // TODO add your handling code here:
        if (tblphieudat.getRowCount() >= 0) {
            if (evt.getClickCount() == 2) {
                ChiTietPD();
                loadtblCTPD();
            }
        }

    }//GEN-LAST:event_tblphieudatMousePressed

    private void btnxacnhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxacnhanActionPerformed
        // TODO add your handling code here:
        int ret = JOptionPane.showConfirmDialog(this, "Xác nhận phiếu đặt này ?");
        if (ret == JOptionPane.YES_OPTION) {
            UpdateSoluong();
            UpdatePhieuXuat();
            PhieuXuat formpx = new PhieuXuat();
            this.setVisible(false);
            formpx.setVisible(true);
        }
    }//GEN-LAST:event_btnxacnhanActionPerformed

    private void btnhoangtacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhoangtacActionPerformed
        // TODO add your handling code here:
        int ret = JOptionPane.showConfirmDialog(this, "Xác nhận hủy đơn đặt hàng này ?");
        if (ret == JOptionPane.YES_OPTION) {
            if (txtmaphietdat.getText().trim().length() == 0) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn phiếu đặt", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                DeleteSPD();
                loaddatapd();
                loaddataSPD();
                formdh.loaddatahhkt();
            }
        }

    }//GEN-LAST:event_btnhoangtacActionPerformed

    public void filltotablPD() {

        int sel = cboTrangThai.getSelectedIndex();
        tblmdpd = (DefaultTableModel) tblphieudat.getModel();
        tblmdpd.setRowCount(0);
        Object row[];
        switch (sel) {
            case 0:
                for (ETTPhieuDat pd : listpd) {
                    String TrangThai = "";
                    if (pd.isPD_TrangThai() == false) {
                        TrangThai = "Yêu Cầu";
                    } else {
                        TrangThai = "Xác nhận";
                    }
                    row = new Object[]{pd.getMaPD(), pd.getMakho(), pd.getPD_ThoiGian(), TrangThai};
                    tblmdpd.addRow(row);
                }
                break;

            case 1:
                for (ETTPhieuDat pd : listpd) {
                    String TrangThai = "";
                    if (pd.isPD_TrangThai() == false) {
                        TrangThai = "Yêu Cầu";
                        row = new Object[]{pd.getMaPD(), pd.getMakho(), pd.getPD_ThoiGian(), TrangThai};
                        tblmdpd.addRow(row);
                    }
                }
                break;

            case 2:
                for (ETTPhieuDat pd : listpd) {
                    String TrangThai = "";
                    if (pd.isPD_TrangThai() == true) {
                        TrangThai = "Xác Nhận";
                        row = new Object[]{pd.getMaPD(), pd.getMakho(), pd.getPD_ThoiGian(), TrangThai};
                        tblmdpd.addRow(row);
                    }
                }
                break;
            default:
                throw new AssertionError();
        }
    }
    private void BtnXongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnXongActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_BtnXongActionPerformed

    private void cboTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTrangThaiActionPerformed
        // TODO add your handling code here:
        filltotablPD();
    }//GEN-LAST:event_cboTrangThaiActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem7ActionPerformed

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
            java.util.logging.Logger.getLogger(phieudat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(phieudat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(phieudat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(phieudat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new phieudat().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnXong;
    private javax.swing.ButtonGroup TrangThaiPD;
    private javax.swing.JButton btnhoangtac;
    private javax.swing.JButton btnxacnhan;
    private javax.swing.JButton btnxemchitiet;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboTrangThai;
    private com.toedter.calendar.JDateChooser dtcThoiGian;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTree jTree1;
    private javax.swing.JMenuBar menu1;
    private javax.swing.JRadioButton radXacNhan;
    private javax.swing.JRadioButton radYeuCau;
    public javax.swing.JTable tblCTPD;
    public javax.swing.JTable tblphieudat;
    private javax.swing.JTextField txtkho;
    private javax.swing.JTextField txtmaphietdat;
    private javax.swing.JTextField txtnguoidat;
    private javax.swing.JTextField txttimkiem;
    // End of variables declaration//GEN-END:variables
}
