/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view.Nhanvien;

import java.awt.HeadlessException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import model.DatabaseHelper;
import entity.product.Nhanvien;
import view.Danhmucsp.danhmucsp;
import view.Nhacungcap.nhacungcap;
import view.NhapXuat.DATHANG;
import view.ThongKeChiTieu.DoanhThu_Tong;
import view.ThongKeChiTieu.ThongKe_ChiTieu;
import view.ThongKeChiTieu.ThongKe_DTTung_CH;
import view.Tongquan.tongquan;

/**
 *
 * @author AQ
 */
public class QuanLyNhanVien extends javax.swing.JFrame {

    /**
     * Creates new form QuanLyNhanVien
     */
    public QuanLyNhanVien() {
        initComponents();
        setLocationRelativeTo(null);
        innittblnhanvien();
        fixcolumstblnv();
        nhanvienloaddata();
        
        //SelectKho();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private static ArrayList<Nhanvien> listnv = new ArrayList<>();

    DefaultTableModel tblmodel;

    public static ArrayList<Nhanvien> getList(){
        return listnv;
    }
    
    public void innittblnhanvien() {
        tblmodel = new DefaultTableModel();
        Object[] columns = new Object[]{"Mã Nhân Viên", "Tên nhân viên", "Kho", "Email", "SDT"};
        tblmodel.setColumnIdentifiers(columns);
        tblnhanvien.setModel(tblmodel);
    }

    public void fixcolumstblnv(){
        tblnhanvien.getColumnModel().getColumn(0).setPreferredWidth(60);
        tblnhanvien.getColumnModel().getColumn(1).setPreferredWidth(170);
        tblnhanvien.getColumnModel().getColumn(2).setPreferredWidth(60);
        tblnhanvien.getColumnModel().getColumn(3).setPreferredWidth(100);
        tblnhanvien.setAutoResizeMode(tblnhanvien.AUTO_RESIZE_LAST_COLUMN);
    }

    
    public void nhanvienloaddata() {
        listnv.clear();
        try {
            Connection con = DatabaseHelper.oppenConnection();
            String query = "Select * from NhanVien";
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Nhanvien nv = new Nhanvien();
                nv.setManv(rs.getString(1));
                nv.setHoten(rs.getString(2));
                nv.setNgaysinh(rs.getString(3));
                if (rs.getInt(4) == 0) {
                    nv.setGioitinh("Nữ");
                } else {
                    nv.setGioitinh("Nam");
                }
                nv.setMakho(rs.getString(5));
                nv.setCCCD(rs.getString(6));
                nv.setSDT(rs.getString(7));
                nv.setEmail(rs.getString(8));
                nv.setDiachi(rs.getString(9));
                nv.setAnh(rs.getString(10));
                nv.setChucVu(rs.getInt(11));
                listnv.add(nv);

            }
//            tblmodel = (DefaultTableModel) tblnhanvien.getModel();
//            tblmodel.setRowCount(0);
//            Object row[];
//            for (Nhanvien nv : listnv) {
//                row = new Object[]{nv.getManv(), nv.getHoten(), nv.getMakho(), nv.getEmail(), nv.getSDT()};
//                tblmodel.addRow(row);
//            }
                SelectKho();
            con.close();
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public void SelectKho() {
        //System.out.println(cboKho.getSelectedIndex());
        int sl = cboKho.getSelectedIndex();
        tblmodel = (DefaultTableModel) tblnhanvien.getModel();
        tblmodel.setRowCount(0);
        Object row[];
        switch (sl) {
            case 0:
                for (Nhanvien nv : listnv) {
                    row = new Object[]{nv.getManv(), nv.getHoten(), nv.getMakho(), nv.getEmail(), nv.getSDT()};
                    tblmodel.addRow(row);
                }
                break;

            case 1:
                for (Nhanvien nv : listnv) {
                    if (nv.getMakho().equalsIgnoreCase("KH001")) {
                        row = new Object[]{nv.getManv(), nv.getHoten(), nv.getMakho(), nv.getEmail(), nv.getSDT()};
                        tblmodel.addRow(row);
                    }
                }
                break;

            case 2:
                for (Nhanvien nv : listnv) {
                    if (nv.getMakho().equalsIgnoreCase("KH002")) {
                        row = new Object[]{nv.getManv(), nv.getHoten(), nv.getMakho(), nv.getEmail(), nv.getSDT()};
                        tblmodel.addRow(row);
                    }
                }
                break;

            case 3:
                for (Nhanvien nv : listnv) {
                    if (nv.getMakho().equalsIgnoreCase("KH003")) {
                        row = new Object[]{nv.getManv(), nv.getHoten(), nv.getMakho(), nv.getEmail(), nv.getSDT()};
                        tblmodel.addRow(row);
                    }
                }
                break;

            case 4:
                for (Nhanvien nv : listnv) {
                    if (nv.getMakho().equalsIgnoreCase("KH004")) {
                        row = new Object[]{nv.getManv(), nv.getHoten(), nv.getMakho(), nv.getEmail(), nv.getSDT()};
                        tblmodel.addRow(row);
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

        jTextField1 = new javax.swing.JTextField();
        btnthemnv = new javax.swing.JButton();
        btnchitiet = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cboKho = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblnhanvien = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jMenuBar5 = new javax.swing.JMenuBar();
        mTongquan4 = new javax.swing.JMenu();
        mDanhmucsp = new javax.swing.JMenu();
        mhanghoa = new javax.swing.JMenu();
        mNhanvien = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        mChamcong = new javax.swing.JMenu();
        mLuong = new javax.swing.JMenu();
        mNhacungcap = new javax.swing.JMenu();
        mThongke = new javax.swing.JMenu();
        mDoanhthutong = new javax.swing.JMenu();
        mDoanhthutungcuahang = new javax.swing.JMenu();
        mChitieu = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        btnthemnv.setText("Thêm nhân viên");
        btnthemnv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemnvActionPerformed(evt);
            }
        });

        btnchitiet.setText("Xem chi tiết");
        btnchitiet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnchitietActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setText("Kho ");

        cboKho.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Kho 1", "Kho 2", "Kho 3", "Kho 4" }));
        cboKho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboKhoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboKho, 0, 131, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cboKho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 155, Short.MAX_VALUE)
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 165, Short.MAX_VALUE)
        );

        tblnhanvien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã nhân viên", "Tên nhân viên", "Kho", "Email", "SĐT"
            }
        ));
        tblnhanvien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblnhanvienMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblnhanvienMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblnhanvien);

        jLabel3.setText("Tìm Kiếm");

        jMenuBar5.setBorder(null);
        jMenuBar5.setForeground(new java.awt.Color(255, 255, 255));

        mTongquan4.setText("Tổng quan");
        mTongquan4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mTongquan4MouseClicked(evt);
            }
        });
        jMenuBar5.add(mTongquan4);

        mDanhmucsp.setText("Danh mục sản phẩm");
        mDanhmucsp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mDanhmucspMouseClicked(evt);
            }
        });
        jMenuBar5.add(mDanhmucsp);

        mhanghoa.setText("Nhập xuất hàng hoá");
        mhanghoa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mhanghoaMouseClicked(evt);
            }
        });
        jMenuBar5.add(mhanghoa);

        mNhanvien.setText("Nhân viên");

        jMenuItem1.setText("Quản lí nhân viên");
        jMenuItem1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem1MouseClicked(evt);
            }
        });
        mNhanvien.add(jMenuItem1);

        mChamcong.setText("Chấm công");
        mChamcong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mChamcongMouseClicked(evt);
            }
        });
        mNhanvien.add(mChamcong);

        mLuong.setText("Lương");
        mLuong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mLuongMouseClicked(evt);
            }
        });
        mNhanvien.add(mLuong);

        jMenuBar5.add(mNhanvien);

        mNhacungcap.setText("Nhà cung cấp");
        mNhacungcap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mNhacungcapMouseClicked(evt);
            }
        });
        jMenuBar5.add(mNhacungcap);

        mThongke.setText("Thống kê");

        mDoanhthutong.setText("Doanh thu tổng");
        mDoanhthutong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mDoanhthutongMouseClicked(evt);
            }
        });
        mThongke.add(mDoanhthutong);

        mDoanhthutungcuahang.setText("Doanh thu từng cửa hàng");
        mDoanhthutungcuahang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mDoanhthutungcuahangMouseClicked(evt);
            }
        });
        mThongke.add(mDoanhthutungcuahang);

        mChitieu.setText("Chi tiêu");
        mChitieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mChitieuMouseClicked(evt);
            }
        });
        mThongke.add(mChitieu);

        jMenuBar5.add(mThongke);

        setJMenuBar(jMenuBar5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextField1)
                        .addGap(61, 61, 61)
                        .addComponent(btnthemnv, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnchitiet, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 578, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(12, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnthemnv, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnchitiet, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(13, 13, 13))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnthemnvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemnvActionPerformed
        // TODO add your handling code here:
        ChiTietNV ctnv = new ChiTietNV();
        ctnv.setVisible(true);
        
    }//GEN-LAST:event_btnthemnvActionPerformed

    public void changeform(){
        int column = 0;
        int rowsl = tblnhanvien.getSelectedRow();        
        String value = tblnhanvien.getModel().getValueAt(rowsl, column).toString();
        System.out.println(value);
        String str = value; // read the JTextFeild t1 data
        ChiTietNV obj = new ChiTietNV();// obj created for class Second()
        try {
            obj.my_update(str);//Execute the method my_update to pass str
        } catch (ParseException ex) {
            Logger.getLogger(QuanLyNhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }
        obj.setVisible(true); // Open the Second.java window
        //dispose(); // Close the First.java window
    }
    private void btnchitietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnchitietActionPerformed
        // TODO add your handling code here:
        changeform();
    }//GEN-LAST:event_btnchitietActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void cboKhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboKhoActionPerformed
        // TODO add your handling code here:
        SelectKho();
    }//GEN-LAST:event_cboKhoActionPerformed
    
    public QuanLyNhanVien(DefaultTableModel tblmodel, JButton btnchitiet, JButton btnthemnv, JComboBox<String> cboKho, JLabel jLabel1, JLabel jLabel2, JLabel jLabel3, JMenu jMenu1, JMenu jMenu2, JMenuBar jMenuBar1, JMenuItem jMenuItem1, JMenuItem jMenuItem2, JMenuItem jMenuItem3, JPanel jPanel1, JPanel jPanel2, JPanel jPanel3, JScrollPane jScrollPane1, JTextField jTextField1, JTable tblnhanvien) throws HeadlessException {
        this.tblmodel = tblmodel;
        this.btnchitiet = btnchitiet;
        this.btnthemnv = btnthemnv;
        this.cboKho = cboKho;
        this.jLabel1 = jLabel1;       
//        this.jMenu1 = jMenu1;
//        this.jMenu2 = jMenu2;
//        this.jMenuBar1 = jMenuBar1;
//        this.jMenuItem1 = jMenuItem1;
//        this.jMenuItem2 = jMenuItem2;
//        this.jMenuItem3 = jMenuItem3;
        this.jPanel1 = jPanel1;
        this.jPanel2 = jPanel2;
        this.jPanel3 = jPanel3;
        this.jScrollPane1 = jScrollPane1;
        this.jTextField1 = jTextField1;
        this.tblnhanvien = tblnhanvien;
    }

    private void tblnhanvienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblnhanvienMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_tblnhanvienMouseClicked

    private void tblnhanvienMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblnhanvienMousePressed
        // TODO add your handling code here:
        if(evt.getClickCount() == 2){
            changeform();
         }
    }//GEN-LAST:event_tblnhanvienMousePressed

    private void mTongquan4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mTongquan4MouseClicked
        new tongquan().setVisible(true);
        this.setVisible(true);
    }//GEN-LAST:event_mTongquan4MouseClicked

    private void mDanhmucspMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mDanhmucspMouseClicked
        new danhmucsp().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_mDanhmucspMouseClicked

    private void mhanghoaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mhanghoaMouseClicked
        new DATHANG().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_mhanghoaMouseClicked

    private void mChamcongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mChamcongMouseClicked
        new chamcong().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_mChamcongMouseClicked

    private void mLuongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mLuongMouseClicked
        new luong().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_mLuongMouseClicked

    private void mNhacungcapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mNhacungcapMouseClicked
        new nhacungcap().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_mNhacungcapMouseClicked

    private void mDoanhthutongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mDoanhthutongMouseClicked
        new DoanhThu_Tong().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_mDoanhthutongMouseClicked

    private void mDoanhthutungcuahangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mDoanhthutungcuahangMouseClicked
        new ThongKe_ChiTieu().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_mDoanhthutungcuahangMouseClicked

    private void mChitieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mChitieuMouseClicked
        new ThongKe_DTTung_CH().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_mChitieuMouseClicked

    private void jMenuItem1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem1MouseClicked
        new QuanLyNhanVien().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jMenuItem1MouseClicked

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
            java.util.logging.Logger.getLogger(QuanLyNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLyNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLyNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLyNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLyNhanVien().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnchitiet;
    private javax.swing.JButton btnthemnv;
    private javax.swing.JComboBox<String> cboKho;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenuBar jMenuBar5;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JMenu mChamcong;
    private javax.swing.JMenu mChitieu;
    private javax.swing.JMenu mDanhmucsp;
    private javax.swing.JMenu mDoanhthutong;
    private javax.swing.JMenu mDoanhthutungcuahang;
    private javax.swing.JMenu mLuong;
    private javax.swing.JMenu mNhacungcap;
    private javax.swing.JMenu mNhanvien;
    private javax.swing.JMenu mThongke;
    private javax.swing.JMenu mTongquan4;
    private javax.swing.JMenu mhanghoa;
    private javax.swing.JTable tblnhanvien;
    // End of variables declaration//GEN-END:variables
}
