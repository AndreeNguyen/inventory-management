/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view.ThongKeChiTieu;


import entity.ThongKeDoanhThu.DoanhThu_tong;
import entity.ThongKeDoanhThu.ThongKe_TCH;
import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.DatabaseHelper;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.jdbc.JDBCCategoryDataset;
import view.Danhmucsp.danhmucsp;
import view.Nhacungcap.nhacungcap;
import view.Nhanvien.QuanLyNhanVien;
import view.Nhanvien.chamcong;
import view.Nhanvien.luong;
import view.NhapXuat.DATHANG;
import view.Tongquan.tongquan;

/**
 *
 * @author LENOVO
 */
public class DoanhThu_Tong extends javax.swing.JFrame {

    /**
     * Creates new form DoanhThu_Tong
     */
    public DoanhThu_Tong() {
        initComponents();
        setLocationRelativeTo(null);
    }

    ArrayList<DoanhThu_tong> list = new ArrayList<>();
    public void load_data(){
        try{
            list.clear();
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DatabaseHelper.oppenConnection();
            String query = "select * from DoanhThu_Tong";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            list.clear();
            while (rs.next()) {
                DoanhThu_tong gra = new DoanhThu_tong();
                gra.setThoiGian(rs.getDate("ThoiGian"));
                gra.setDoanhThu(rs.getFloat("DoanhThu"));
                list.add(gra);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
String query ;
    public void selecttime() {
        int sl = cboThang.getSelectedIndex();
        String query ;
        switch (sl) {
            case 0:
               query = "select ThoiGian, DoanhThu from DoanhThu_Tong";
                break;

            case 1:
                query = "select ThoiGian, DoanhThu from DoanhThu_Tong where ThoiGian like '%-01-%'";
                break;

            case 2:
                query = "select ThoiGian, DoanhThu from DoanhThu_Tong where ThoiGian like '%-02-%'";
                break;

            case 3:
                query = "select ThoiGian, DoanhThu from DoanhThu_Tong where ThoiGian like '%-03-%'";
                break;

            case 4:
                query = "select ThoiGian, DoanhThu from DoanhThu_Tong where ThoiGian like '%-04-%'";
                break;

            case 5:
                query = "select ThoiGian, DoanhThu from DoanhThu_Tong where ThoiGian like '%-05-%'";
                break;

            case 6:
                query = "select ThoiGian, DoanhThu from DoanhThu_Tong where ThoiGian like '%-06-%'";
                break;

            case 7:
                query = "select ThoiGian, DoanhThu from DoanhThu_Tong where ThoiGian like '%-07-%'";
                break;

            case 8:
                query = "select ThoiGian, DoanhThu from DoanhThu_Tong where ThoiGian like '%-08-%'";
                break;

            case 9:
                query = "select ThoiGian, DoanhThu from DoanhThu_Tong where ThoiGian like '%-09-%'";
                break;

            case 10:
                query = "select ThoiGian, DoanhThu from DoanhThu_Tong where ThoiGian like '%-10-%'";
                break;
            case 11:
                query = "select ThoiGian, DoanhThu from DoanhThu_Tong where ThoiGian like '%-11-%'";
                break;
                
            case 12:
                query = "select ThoiGian, DoanhThu from DoanhThu_Tong where ThoiGian like '%-12-%'";
                break;
            default:
                throw new AssertionError();
        }
        
        try{
            JDBCCategoryDataset dataset = new JDBCCategoryDataset(DatabaseHelper.oppenConnection(),query);
            JFreeChart chart = ChartFactory.createBarChart3D("SƠ ĐỒ: DOANH THU TỔNG", "ThoiGian", "DoanhThu", dataset, PlotOrientation.VERTICAL, false, true, true);
            chart.setBackgroundPaint(Color.YELLOW);
            chart.getTitle().setPaint(Color.BLACK);
            BarRenderer renderer = null;
            CategoryPlot plot = null;
            renderer = new BarRenderer();
            ChartFrame frame = new ChartFrame("SƠ ĐỒ: DOANH THU TỔNG", chart);
            frame.setSize(600, 650);
            
            ChartPanel barPanel = new ChartPanel(chart);
            jpnSoDoTong.removeAll();
            jpnSoDoTong.add(barPanel, BorderLayout.CENTER);
            jpnSoDoTong.validate();
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
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

        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jpnSoDoTong = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnXuatFile = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cboThang = new javax.swing.JComboBox<>();
        jMenuBar9 = new javax.swing.JMenuBar();
        mTongquan8 = new javax.swing.JMenu();
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

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 178, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 167, Short.MAX_VALUE)
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

        jpnSoDoTong.setBackground(new java.awt.Color(51, 51, 255));
        jpnSoDoTong.setLayout(new java.awt.BorderLayout());

        jLabel1.setText("Doanh thu tổng");

        btnXuatFile.setText("Xuất file");
        btnXuatFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatFileActionPerformed(evt);
            }
        });

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addComponent(cboThang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cboThang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 4, Short.MAX_VALUE))
        );

        jMenuBar9.setBorder(null);
        jMenuBar9.setForeground(new java.awt.Color(255, 255, 255));

        mTongquan8.setText("Tổng quan");
        mTongquan8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mTongquan8MouseClicked(evt);
            }
        });
        jMenuBar9.add(mTongquan8);

        mDanhmucsp.setText("Danh mục sản phẩm");
        mDanhmucsp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mDanhmucspMouseClicked(evt);
            }
        });
        jMenuBar9.add(mDanhmucsp);

        mhanghoa.setText("Nhập xuất hàng hoá");
        mhanghoa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mhanghoaMouseClicked(evt);
            }
        });
        jMenuBar9.add(mhanghoa);

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

        jMenuBar9.add(mNhanvien);

        mNhacungcap.setText("Nhà cung cấp");
        mNhacungcap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mNhacungcapMouseClicked(evt);
            }
        });
        jMenuBar9.add(mNhacungcap);

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

        jMenuBar9.add(mThongke);

        setJMenuBar(jMenuBar9);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jLabel1)
                        .addGap(431, 614, Short.MAX_VALUE)
                        .addComponent(btnXuatFile)
                        .addGap(47, 47, 47))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jpnSoDoTong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(btnXuatFile))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jpnSoDoTong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnXuatFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatFileActionPerformed
        // TODO add your handling code here:
        selecttime();
        
    }//GEN-LAST:event_btnXuatFileActionPerformed

    private void mTongquan8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mTongquan8MouseClicked
        new tongquan().setVisible(true);
        this.setVisible(true);
    }//GEN-LAST:event_mTongquan8MouseClicked

    private void mDanhmucspMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mDanhmucspMouseClicked
        new danhmucsp().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_mDanhmucspMouseClicked

    private void mhanghoaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mhanghoaMouseClicked
        new DATHANG().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_mhanghoaMouseClicked

    private void jMenuItem1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem1MouseClicked
        new QuanLyNhanVien().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jMenuItem1MouseClicked

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
            java.util.logging.Logger.getLogger(DoanhThu_Tong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DoanhThu_Tong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DoanhThu_Tong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DoanhThu_Tong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DoanhThu_Tong().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnXuatFile;
    private javax.swing.JComboBox<String> cboThang;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenuBar jMenuBar5;
    private javax.swing.JMenuBar jMenuBar6;
    private javax.swing.JMenuBar jMenuBar7;
    private javax.swing.JMenuBar jMenuBar9;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jpnSoDoTong;
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
    private javax.swing.JMenu mTongquan5;
    private javax.swing.JMenu mTongquan6;
    private javax.swing.JMenu mTongquan8;
    private javax.swing.JMenu mhanghoa;
    // End of variables declaration//GEN-END:variables
}
