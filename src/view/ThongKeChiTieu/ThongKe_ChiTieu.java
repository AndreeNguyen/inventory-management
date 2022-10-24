/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view.ThongKeChiTieu;


import view.ThongKeDoanhThu.ThongKe_CT;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import model.DatabaseHelper;

/**
 *
 * @author LENOVO
 */
public class ThongKe_ChiTieu extends javax.swing.JFrame {

    /**
     * Creates new form ThongKe_ChiTieu
     */
    public ThongKe_ChiTieu() {
        initComponents();
        setLocationRelativeTo(null);
        load_data();
    }
    
    ArrayList<ThongKe_CT> list = new ArrayList<>();
    DefaultTableModel tblModel = new DefaultTableModel();
    DefaultTableModel tblmodel;
    
    public void load_data() {
        try {
            list.clear();
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DatabaseHelper.oppenConnection();
            String query = "select * from BangChi";
            
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            list.clear();
            while (rs.next()) {
                ThongKe_CT gra = new ThongKe_CT();
                gra.setTenKhoanChi(rs.getString("TenKhoanChi"));
                gra.setKhoanChi(rs.getFloat("KhoanChi"));
                gra.setThoiGian(rs.getDate("ThoiGian"));
                gra.setHinhThuc(rs.getString("HinhThuc"));
                list.add(gra);
            }
            DefaultTableModel model = (DefaultTableModel) tblChiTieu.getModel();
            model.setRowCount(0);
            Object row[];
            for (ThongKe_CT gra : list) {
                row = new Object[]{gra.getTenKhoanChi(), gra.getKhoanChi(), gra.getThoiGian(), gra.getHinhThuc()};
                model.addRow(row);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void find() {
        
        tblmodel = (DefaultTableModel) tblChiTieu.getModel();
        tblmodel.setRowCount(0);
        int select;
        select = cbob.getSelectedIndex();
        
        boolean checkfind = false;
        switch (select) {
            case 0:
                for (ThongKe_CT b : list) {
                    if (txtTK.getText().equalsIgnoreCase(b.getTenKhoanChi())) {
                        checkfind = addrow(b);
                    }
                }
                break;
            case 1:
                for (ThongKe_CT b : list) {
                    if (txtTK.getText().equalsIgnoreCase(b.getHinhThuc())) {
                        checkfind = addrow(b);
                    }
                }
                break;
            default:
                throw new AssertionError();
        }
        if (checkfind == false) {
            JOptionPane.showMessageDialog(this, "Không có sách cần tìm");
        }
    }
    
    public boolean addrow(ThongKe_CT b) {
        boolean checkfind;
        Object row[];
        row = new Object[]{b.getTenKhoanChi(), b.getKhoanChi(), b.getThoiGian(), b.getHinhThuc()};
        tblmodel.addRow(row);
        checkfind = true;
        return checkfind;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblChiTieu = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtTK = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jToolBar1 = new javax.swing.JToolBar();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        btnThongKe = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cboThang = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        cbob = new javax.swing.JComboBox<>();
        btnLoad = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblChiTieu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên khoản chi", "Khoản chi", "Thời gian", "Hình thức"
            }
        ));
        jScrollPane1.setViewportView(tblChiTieu);

        jLabel1.setText("Chi tiêu");

        txtTK.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTKKeyReleased(evt);
            }
        });

        btnSearch.setText("Tìm kiếm");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        jToolBar1.setRollover(true);

        jButton1.setText("Tổng quan");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton1);

        jButton2.setText("Danh mục sản phẩm");
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton2);

        jButton3.setText("Hàng hóa");
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton3);

        jButton4.setText("Nhân viên");
        jButton4.setFocusable(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton4);

        jButton5.setText("Nhà cung cấp");
        jButton5.setFocusable(false);
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton5);

        btnThongKe.setText("Thống kê");
        btnThongKe.setFocusable(false);
        btnThongKe.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnThongKe.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btnThongKe);

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setText("Thời gian");

        cboThang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cả năm", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addComponent(cboThang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(cboThang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 8, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 178, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 182, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 193, Short.MAX_VALUE)
        );

        cbob.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tên KC", "Hình thức" }));

        btnLoad.setText("New");
        btnLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(62, 62, 62)
                                .addComponent(jLabel1)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtTK, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbob, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnSearch)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnLoad, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtTK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch)
                    .addComponent(cbob, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLoad))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(62, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        if (txtTK.getText().trim().length() == 0) {
            TKloaddata();
        } else {
            find();
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadActionPerformed
        // TODO add your handling code here:
        selecttime();
    }//GEN-LAST:event_btnLoadActionPerformed
    DefaultTableModel tableModel;
    
    private void findByAuto(String str) {
        
        tableModel = (DefaultTableModel) tblChiTieu.getModel();
        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(tableModel);
        tblChiTieu.setRowSorter(trs);
        trs.setRowFilter(RowFilter.regexFilter(str));
//        tblDMSP.setPreferredScrollableViewportSize(tblDMSP.getPreferredSize());
//        tblDMSP.setFillsViewportHeight(true);

    }

    private void txtTKKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTKKeyReleased
        findByAuto(txtTK.getText());
    }//GEN-LAST:event_txtTKKeyReleased

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
            java.util.logging.Logger.getLogger(ThongKe_ChiTieu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThongKe_ChiTieu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThongKe_ChiTieu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThongKe_ChiTieu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ThongKe_ChiTieu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLoad;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnThongKe;
    private javax.swing.JComboBox<String> cboThang;
    private javax.swing.JComboBox<String> cbob;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTable tblChiTieu;
    private javax.swing.JTextField txtTK;
    // End of variables declaration//GEN-END:variables

    public void TKloaddata() {
        list.clear();
        try {
            
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DatabaseHelper.oppenConnection();
            String query = "select * from BangChi";
            
            Statement st = con.createStatement();
            
            ResultSet rs = st.executeQuery(query);

//            PreparedStatement ps1 = con.prepareStatement(query);
//            ResultSet rs2 = ps1.executeQuery(query);
            while (rs.next()) {
                ThongKe_CT tk = new ThongKe_CT();
                tk.setTenKhoanChi(rs.getString(1));
                tk.setKhoanChi(rs.getFloat(2));
                tk.setThoiGian(rs.getDate(3));
                tk.setHinhThuc(rs.getString(4));
                list.add(tk);
            }
            tblmodel = (DefaultTableModel) tblChiTieu.getModel();
            tblmodel.setRowCount(0);
            Object row[];
            for (ThongKe_CT bk : list) {
                row = new Object[]{bk.getTenKhoanChi(), bk.getKhoanChi(), bk.getThoiGian(), bk.getHinhThuc()};
                tblmodel.addRow(row);
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void selecttime() {
        int sl = cboThang.getSelectedIndex();
        String query;
        switch (sl) {
            case 0:
                query = "select * from BangChi";
                break;
            
            case 1:
                query = "select * from BangChi where ThoiGian like '%-01-%'";
                break;
            
            case 2:
                query = "select * from BangChi where ThoiGian like '%-02-%'";
                break;
            
            case 3:
                query = "select * from BangChi where ThoiGian like '%-03-%'";
                break;
            
            case 4:
                query = "select * from BangChi where ThoiGian like '%-04-%'";
                break;
            
            case 5:
                query = "select * from BangChi where ThoiGian like '%-05-%'";
                break;
            
            case 6:
                query = "select * from BangChi where ThoiGian like '%-06-%'";
                break;
            
            case 7:
                query = "select * from BangChi where ThoiGian like '%-07-%'";
                break;
            
            case 8:
                query = "select * from BangChi where ThoiGian like '%-08-%'";
                break;
            
            case 9:
                query = "select * from BangChi where ThoiGian like '%-09-%'";
                break;
            
            case 10:
                query = "select * from BangChi where ThoiGian like '%-10-%'";
                break;
            case 11:
                query = "select * from BangChig where ThoiGian like '%-11-%'";
                break;
            
            case 12:
                query = "select * from BangChi where ThoiGian like '%-12-%'";
                break;
            default:
                throw new AssertionError();
        }
        
        try {
            list.clear();
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DatabaseHelper.oppenConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            list.clear();
            while (rs.next()) {
                ThongKe_CT gra = new ThongKe_CT();
                gra.setTenKhoanChi(rs.getString("TenKhoanChi"));
                gra.setKhoanChi(rs.getFloat("KhoanChi"));
                gra.setThoiGian(rs.getDate("ThoiGian"));
                gra.setHinhThuc(rs.getString("HinhThuc"));
                list.add(gra);
            }
            DefaultTableModel model = (DefaultTableModel) tblChiTieu.getModel();
            model.setRowCount(0);
            Object row[];
            for (ThongKe_CT gra : list) {
                row = new Object[]{gra.getTenKhoanChi(), gra.getKhoanChi(), gra.getThoiGian(), gra.getHinhThuc()};
                model.addRow(row);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
