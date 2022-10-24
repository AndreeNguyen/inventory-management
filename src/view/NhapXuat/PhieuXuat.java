/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view.NhapXuat;

import model.DatabaseHelper;
import controller.productDAO.PhieuXuatDao;
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
import java.awt.MenuBar;
import javax.swing.JMenuBar;

/**
 *
 * @author ADMIN
 */
public class PhieuXuat extends javax.swing.JFrame {

    /**
     * Creates new form PhieuXuat
     */
    public PhieuXuat() {
        initComponents();
        loaddatapx();
        formpd.loaddataSPD();
        setLocationRelativeTo(null);
        DATHANG formdh = new DATHANG();
        MenuBar mnb = formdh.getMenuBar();
        
        //System.out.println(keyidentityPX());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    private static ArrayList<ETTPhieuXuat> listpx = new ArrayList<>();

    public static ArrayList<ETTPhieuXuat> getlistpx() {
        return listpx;
    }

    phieudat formpd = new phieudat();
    ArrayList<SanPhamDat> listspd = formpd.getlistspd();
    DefaultTableModel tblmdpx;
    PhieuXuatDao dao = new PhieuXuatDao();

    public String keyidentityPX() {
        String MaPXid = "PX";
        String str = "";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DatabaseHelper.oppenConnection();
            String query = "select * from PhieuXuat";
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

        return MaPXid += str;
    }

    public void loaddatapx() {
        listpx.clear();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DatabaseHelper.oppenConnection();
            String query = "select * from PhieuXuat";

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(query);

//            PreparedStatement ps1 = con.prepareStatement(query);
//            ResultSet rs2 = ps1.executeQuery(query);
            while (rs.next()) {
                ETTPhieuXuat px = new ETTPhieuXuat();
                px.setMaPX(rs.getString(1));
                px.setPX_ThoiGian(rs.getString(2));
                px.setPX_TrangThai(rs.getInt(3));
                px.setMakho(rs.getString(4));
                px.setNguoiXuat(rs.getString(5));
                System.out.println(rs.getInt(3));
                listpx.add(px);
            }
            filltotablPX();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void ChiTietPX() {
        jTabbedPane1.setSelectedIndex(1);
        int index = tblphieuxuat.getSelectedRow();
        String MaPXfill = tblphieuxuat.getValueAt(index, 0).toString();
        txtMaPX.setText(MaPXfill);
        filltotblCTPX(MaPXfill);
    }

    public void filltotblCTPX(String MaPXfill) {

        for (ETTPhieuXuat px : listpx) {
            if (px.getMaPX().equalsIgnoreCase(MaPXfill)) {
                try {
                    String date = px.getPX_ThoiGian();
                    java.util.Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
                    dtcPxThoiGian.setDate(date2);
                    txtnguoixuat.setText(px.getNguoiXuat());
                    txtkhoxuat.setText(px.getMakho());
                    if (px.getPX_TrangThai() == 1) {
                        radPheduyet.setSelected(true);
                    } else if (px.getPX_TrangThai() == 2) {
                        radXuat.setSelected(true);
                        btnxuat.setEnabled(false);
                    } else {
                        radNhap.setSelected(true);
                        btnxuat.setEnabled(false);
                    }
                } catch (ParseException ex) {
                    System.out.println(ex);
                    Logger.getLogger(phieudat.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void UpdatePhieuXuat() {
        try {
            String MaPX = txtMaPX.getText();
            Connection con = DatabaseHelper.oppenConnection();

            String query2 = "UPDATE PhieuXuat SET PX_ThoiGian = ? WHERE MaPX = ?";
            PreparedStatement ps2 = con.prepareStatement(query2);
            ps2.setString(1, LocalDate.now() + "");
            ps2.setString(2, MaPX);
            ps2.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(phieudat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void xuatSP() {
        String MaPX = txtMaPX.getText();
        try {
            Connection con = DatabaseHelper.oppenConnection();
            String query2 = "UPDATE PhieuXuat SET PX_ThoiGian = ?, PX_TrangThai = ? WHERE MaPX = ?";
            System.out.println(1);
            PreparedStatement ps2 = con.prepareStatement(query2);
            System.out.println(2);
            ps2.setString(1, LocalDate.now() + "");
            ps2.setInt(2, 2);
            System.out.println(3);
            ps2.setString(3, MaPX);
            System.out.println(4);
            ps2.executeUpdate();
            JOptionPane.showMessageDialog(this, "Xuất sản phẩm thành công, vui lòng kiểm tra");
            loaddatapx();
            filltotablPX();
            filltotblCTPX(MaPX);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void updatePN() {
        try {
            String MaPN = "";
            Connection con = DatabaseHelper.oppenConnection();
            for (SanPhamDat spd : listspd) {
                if (spd.getMaPX().equalsIgnoreCase(txtMaPX.getText())) {
                    MaPN = spd.getMaPN();
                    break;
                }
            }

            String query2 = "UPDATE PhieuNhap SET PN_ThoiGian = ?, PN_TrangThai = ?, MaKho = ?, Nguoinhap = ? WHERE MaPN = ?";
            PreparedStatement ps2 = con.prepareStatement(query2);
            ps2.setString(1, "");
            ps2.setInt(2, 1);
            ps2.setString(3, "KH001");
            ps2.setString(4, "NV001");
            ps2.setString(5, MaPN);
            ps2.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(phieudat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void filltotablPX() {

        int sel = cboTrangThai.getSelectedIndex();
        tblmdpx = (DefaultTableModel) tblphieuxuat.getModel();
        tblmdpx.setRowCount(0);
        Object row[];
        switch (sel) {
            case 0:
                for (ETTPhieuXuat px : listpx) {
                    String TrangThai = "";
                    switch (px.getPX_TrangThai()) {
                        case 1 ->
                            TrangThai = "Phê Duyệt";
                        case 2 ->
                            TrangThai = "Đã Xuất";
                        case 3 ->
                            TrangThai = "Đã Nhập";
                        default -> {
                            continue;
                        }
                    }
                    row = new Object[]{px.getMaPX(), px.getMakho(), px.getPX_ThoiGian(), TrangThai};
                    tblmdpx.addRow(row);
                }
                break;

            case 1:
                for (ETTPhieuXuat px : listpx) {
                    String TrangThai = "";
                    if (px.getPX_TrangThai() == 1) {
                        TrangThai = "Phê Duyệt";
                        row = new Object[]{px.getMaPX(), px.getMakho(), px.getPX_ThoiGian(), TrangThai};
                        tblmdpx.addRow(row);
                    }
                }
                break;

            case 2:
                for (ETTPhieuXuat px : listpx) {
                    String TrangThai = "";
                    if (px.getPX_TrangThai() == 2) {
                        TrangThai = "Đã Xuất";
                        row = new Object[]{px.getMaPX(), px.getMakho(), px.getPX_ThoiGian(), TrangThai};
                        tblmdpx.addRow(row);
                    }
                }
                break;

            case 3:
                for (ETTPhieuXuat px : listpx) {
                    String TrangThai = "";
                    if (px.getPX_TrangThai() == 3) {
                        TrangThai = "Đã Nhập";
                        row = new Object[]{px.getMaPX(), px.getMakho(), px.getPX_ThoiGian(), TrangThai};
                        tblmdpx.addRow(row);
                    }
                }
                break;
            default:
                throw new AssertionError();
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
        tblphieuxuat = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtMaPX = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtnguoixuat = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtkhoxuat = new javax.swing.JTextField();
        btnxuat = new javax.swing.JButton();
        btnxong = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        radPheduyet = new javax.swing.JRadioButton();
        radXuat = new javax.swing.JRadioButton();
        radNhap = new javax.swing.JRadioButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblphieuxuatchitiet = new javax.swing.JTable();
        dtcPxThoiGian = new com.toedter.calendar.JDateChooser();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Xuất Hàng");

        txttimkiem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txttimkiem.setText("Search");

        jLabel2.setText("Tìm Kiếm");

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
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 28, Short.MAX_VALUE)
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel4.setText("Kho");

        jLabel5.setText("Trạng Thái");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Kho 1", "Kho 2", "Kho 3", "Kho 4" }));

        cboTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Phê Duyệt", "Đã Xuất", "Đã Nhập" }));
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
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(62, 62, 62)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(jLabel5)))
                        .addGap(0, 63, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboTrangThai, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cboTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 142, Short.MAX_VALUE)
        );

        tblphieuxuat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã Phiếu Xuất", "Kho ", "Thời Gian", "Trạng Thái"
            }
        ));
        tblphieuxuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblphieuxuatMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblphieuxuat);

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
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 613, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46))))
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
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 117, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Phiếu Xuất", jPanel1);

        jLabel6.setText("Mã Phiếu Xuất :");

        jLabel7.setText("Thời Gian");

        jLabel8.setText("Người Xuất");

        jLabel9.setText("Kho Xuất'");

        btnxuat.setText("XUẤT");
        btnxuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxuatActionPerformed(evt);
            }
        });

        btnxong.setText("XONG");

        jLabel10.setText("Trạng Thái");

        TrangThai.add(radPheduyet);
        radPheduyet.setText("Phê Duyệt");
        radPheduyet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radPheduyetActionPerformed(evt);
            }
        });

        TrangThai.add(radXuat);
        radXuat.setText("Đã Xuất");

        TrangThai.add(radNhap);
        radNhap.setText("Đã Nhập");

        tblphieuxuatchitiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Loại Sản Phẩm", "Tên Sản Phẩm", "Số Lượng"
            }
        ));
        jScrollPane2.setViewportView(tblphieuxuatchitiet);

        dtcPxThoiGian.setDateFormatString("yyyy-MM-dd");

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
                        .addComponent(radPheduyet, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(radXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(radNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(dtcPxThoiGian, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                        .addComponent(txtMaPX, javax.swing.GroupLayout.Alignment.LEADING)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtnguoixuat, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                            .addComponent(txtkhoxuat))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(btnxuat, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                        .addGap(48, 48, 48)
                        .addComponent(btnxong, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(101, 101, 101))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 725, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(txtnguoixuat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(txtMaPX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(jLabel9)
                        .addComponent(txtkhoxuat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(dtcPxThoiGian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnxong, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnxuat, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(radPheduyet)
                    .addComponent(radXuat)
                    .addComponent(radNhap))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Phiếu Xuất chi tiết", jPanel2);

        jButton2.setText("<<");

        jButton3.setText("<");

        jButton4.setText(">");

        jButton5.setText(">>");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 847, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addGap(310, 310, 310)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addGap(0, 59, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void radPheduyetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radPheduyetActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radPheduyetActionPerformed

    private void tblphieuxuatMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblphieuxuatMousePressed
        // TODO add your handling code here:
        if (tblphieuxuat.getRowCount() >= 0) {
            if (evt.getClickCount() == 2) {
                ChiTietPX();
                loadtblCTPX();
            }
        }
    }//GEN-LAST:event_tblphieuxuatMousePressed

    private void loadtblCTPX() {
        int column = 0;
        int row = tblphieuxuat.getSelectedRow();
        String mapd = tblphieuxuat.getModel().getValueAt(row, column).toString();
        DefaultTableModel tblmdctpd = (DefaultTableModel) tblphieuxuatchitiet.getModel();
        tblmdctpd.setRowCount(0);

        List<Object[]> list = dao.getBangCT(mapd);

        for (Object[] rows : list) {
            tblmdctpd.addRow(rows);
        }
    }

    private void btnXemChiTietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemChiTietActionPerformed
        // TODO add your handling code here:
        if (tblphieuxuat.getRowCount() < 0) {
            return;
        }else{
            ChiTietPX();
            loadtblCTPX();
        }
    }//GEN-LAST:event_btnXemChiTietActionPerformed

    private void cboTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTrangThaiActionPerformed
        // TODO add your handling code here:
        filltotablPX();
    }//GEN-LAST:event_cboTrangThaiActionPerformed

    private void btnxuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxuatActionPerformed
        // TODO add your handling code here:
        xuatSP();
        updatePN();
        loaddatapx();
        PhieuNhap formpn = new PhieuNhap();
        this.setVisible(false);
        formpn.setVisible(true);

    }//GEN-LAST:event_btnxuatActionPerformed

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
            java.util.logging.Logger.getLogger(PhieuXuat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PhieuXuat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PhieuXuat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PhieuXuat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PhieuXuat().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup TrangThai;
    private javax.swing.JButton btnXemChiTiet;
    private javax.swing.JButton btnxong;
    private javax.swing.JButton btnxuat;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboTrangThai;
    private com.toedter.calendar.JDateChooser dtcPxThoiGian;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JRadioButton radNhap;
    private javax.swing.JRadioButton radPheduyet;
    private javax.swing.JRadioButton radXuat;
    private javax.swing.JTable tblphieuxuat;
    private javax.swing.JTable tblphieuxuatchitiet;
    private javax.swing.JTextField txtMaPX;
    private javax.swing.JTextField txtkhoxuat;
    private javax.swing.JTextField txtnguoixuat;
    private javax.swing.JTextField txttimkiem;
    // End of variables declaration//GEN-END:variables
}
