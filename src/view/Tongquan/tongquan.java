/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view.Tongquan;

import model.DatabaseHelper;
import com.raven.chart.ModelChart;
import java.awt.Color;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.time.LocalDate;
import java.time.Month;

/**
 *
 * @author doqua
 */
public class tongquan extends javax.swing.JFrame {

    ArrayList<kho> list = new ArrayList<>();
    int index = 0;
    /**
     * Creates new form tongquan
     */
    public tongquan() {
        list.clear();
        initComponents();
        setLocationRelativeTo(null);
        init();
        LoadData();
        loadataLSNX();
    }

    ArrayList<LichSuNX> listlsnx = new ArrayList<>();

    public String getMaKho() {
        String MaKho = "";
        int index = cboKho.getSelectedIndex();
        switch (index) {
            case 0:
                MaKho = "KHTong";
                break;

            case 1:
                MaKho = "KH001";
                break;

            case 2:
                MaKho = "KH002";
                break;

            case 3:
                MaKho = "KH003";
                break;

            case 4:
                MaKho = "KH004";
                break;
            default:
                throw new AssertionError();
        }
        return MaKho;
    }

    private void LoadData() {
        float Succhua = 0;
        try {
            list.clear();
            Connection con = DatabaseHelper.oppenConnection();

            CallableStatement cs = con.prepareCall("{CALL Luutrukho(?)}");
            cs.setString(1, getMaKho());
            cs.execute();
            ResultSet rs2 = cs.getResultSet();
            if (rs2.next()) {
                Succhua = rs2.getFloat(1);

                //System.out.println(Math.round(Succhua));
                Chart1.setValue(Math.round(Succhua));
                Chart1.start();
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void loadataLSNX() {
        listlsnx.clear();
        try {
            Connection con = DatabaseHelper.oppenConnection();
            String query = "Select * From LichSuNX";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            //PreparedStatement ps = con.prepareStatement(query);
            while (rs.next()) {
                LichSuNX ls = new LichSuNX();
                ls.setDatenn(rs.getString(2));
                ls.setSlNhap(rs.getInt(3));
                ls.setSlXuat(rs.getInt(4));
                listlsnx.add(ls);
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void SLDate() throws ParseException {
        int sl = cboSLDate.getSelectedIndex();
        LocalDate datec = LocalDate.now();
        Chart.clear();
        switch (sl) {

            case 0:
                for (LichSuNX ls : listlsnx) {
                    String date1 = ls.getDatenn().substring(8);
//                    Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(date1);
//                    date2.get
                    if (date1.equalsIgnoreCase(datec.getDayOfMonth() + "")) {                       
                        Chart.addData(new ModelChart(datec + "", new double[]{ls.getSlNhap(), ls.getSlXuat()}));
                        Chart.start();

                    }

                }

                break;

            case 1:
                   
                    
                    Chart.addData(new ModelChart("24/10", new double[]{102, 150}));
                    Chart.addData(new ModelChart("25/10", new double[]{132, 98}));                   
                    Chart.start();

                break;

            case 2:
                
                    
                    
                    Chart.addData(new ModelChart("05/09", new double[]{203, 230}));
                    Chart.addData(new ModelChart("12/09", new double[]{200, 150}));
                    Chart.addData(new ModelChart("17/09", new double[]{152, 200}));
                    Chart.addData(new ModelChart("24/09", new double[]{123, 150}));
                    Chart.addData(new ModelChart("28/09", new double[]{201, 245}));
                    Chart.start();
                
                break;

            default:
                throw new AssertionError();
        }
    }

    private void init() {
        Chart.addLegend("Hàng nhập", Color.yellow, Color.red);
        Chart.addLegend("Hàng xuất", Color.blue, Color.green);
        

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        progress8 = new com.raven.swing.progress.Progress();
        jLabel6 = new javax.swing.JLabel();
        pnlBackground = new javax.swing.JPanel();
        pnlFooter = new javax.swing.JPanel();
        pnlBanhang = new com.raven.swing.RoundPanel();
        lblBieudobanhang = new javax.swing.JLabel();
        cboSLDate = new javax.swing.JComboBox<>();
        Chart = new com.raven.chart.Chart();
        roundPanel2 = new com.raven.swing.RoundPanel();
        pnlSucchuakho = new com.raven.swing.RoundPanel();
        lblBieudosucchuakho = new javax.swing.JLabel();
        pnlChart1 = new javax.swing.JPanel();
        Chart1 = new com.raven.swing.progress.Progress();
        lblChart1 = new javax.swing.JLabel();
        cboKho = new javax.swing.JComboBox<>();
        jMenuBar1 = new javax.swing.JMenuBar();
        mTongquan = new javax.swing.JMenu();
        mDanhmucsp = new javax.swing.JMenu();
        mhanghoa = new javax.swing.JMenu();
        mNhanvien = new javax.swing.JMenu();
        mChamcong = new javax.swing.JMenu();
        mLuong = new javax.swing.JMenu();
        mNhacungcap = new javax.swing.JMenu();
        mLichsunhap = new javax.swing.JMenu();
        mNonhacungcap = new javax.swing.JMenu();
        mThongke = new javax.swing.JMenu();
        mDoanhthutong = new javax.swing.JMenu();
        mDoanhthutungcuahang = new javax.swing.JMenu();
        mChitieu = new javax.swing.JMenu();

        jPanel5.setOpaque(false);

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Kho tổng");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(progress8, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(9, 9, 9)
                .addComponent(progress8, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlBackground.setBackground(new java.awt.Color(153, 153, 153));
        pnlBackground.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlFooterLayout = new javax.swing.GroupLayout(pnlFooter);
        pnlFooter.setLayout(pnlFooterLayout);
        pnlFooterLayout.setHorizontalGroup(
            pnlFooterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlFooterLayout.setVerticalGroup(
            pnlFooterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 24, Short.MAX_VALUE)
        );

        pnlBanhang.setBackground(new java.awt.Color(255, 255, 255));

        lblBieudobanhang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblBieudobanhang.setForeground(new java.awt.Color(0, 0, 0));
        lblBieudobanhang.setText("Biểu đồ bán hàng");

        cboSLDate.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hôm nay", "Tuần trước", "Tháng trước" }));
        cboSLDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboSLDateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlBanhangLayout = new javax.swing.GroupLayout(pnlBanhang);
        pnlBanhang.setLayout(pnlBanhangLayout);
        pnlBanhangLayout.setHorizontalGroup(
            pnlBanhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBanhangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBanhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Chart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlBanhangLayout.createSequentialGroup()
                        .addComponent(lblBieudobanhang)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 317, Short.MAX_VALUE)
                        .addComponent(cboSLDate, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlBanhangLayout.setVerticalGroup(
            pnlBanhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBanhangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBanhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBieudobanhang)
                    .addComponent(cboSLDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Chart, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        roundPanel2.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout roundPanel2Layout = new javax.swing.GroupLayout(roundPanel2);
        roundPanel2.setLayout(roundPanel2Layout);
        roundPanel2Layout.setHorizontalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        roundPanel2Layout.setVerticalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 76, Short.MAX_VALUE)
        );

        pnlSucchuakho.setBackground(new java.awt.Color(255, 255, 255));
        pnlSucchuakho.setForeground(new java.awt.Color(0, 0, 0));

        lblBieudosucchuakho.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblBieudosucchuakho.setForeground(new java.awt.Color(0, 0, 0));
        lblBieudosucchuakho.setText("Biểu đồ sức chứa kho");

        pnlChart1.setOpaque(false);

        Chart1.setBackground(new java.awt.Color(51, 51, 255));
        Chart1.setForeground(new java.awt.Color(255, 0, 51));
        Chart1.setValue(60);

        lblChart1.setForeground(new java.awt.Color(0, 0, 0));
        lblChart1.setText("Kho tổng");

        javax.swing.GroupLayout pnlChart1Layout = new javax.swing.GroupLayout(pnlChart1);
        pnlChart1.setLayout(pnlChart1Layout);
        pnlChart1Layout.setHorizontalGroup(
            pnlChart1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlChart1Layout.createSequentialGroup()
                .addGroup(pnlChart1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlChart1Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(lblChart1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlChart1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Chart1, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlChart1Layout.setVerticalGroup(
            pnlChart1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlChart1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblChart1)
                .addGap(9, 9, 9)
                .addComponent(Chart1, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cboKho.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Kho tổng", "Kho 1", "Kho 2", "Kho 3", "Kho 4" }));
        cboKho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboKhoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlSucchuakhoLayout = new javax.swing.GroupLayout(pnlSucchuakho);
        pnlSucchuakho.setLayout(pnlSucchuakhoLayout);
        pnlSucchuakhoLayout.setHorizontalGroup(
            pnlSucchuakhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSucchuakhoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlSucchuakhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSucchuakhoLayout.createSequentialGroup()
                        .addComponent(lblBieudosucchuakho)
                        .addGap(18, 18, 18)
                        .addComponent(cboKho, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnlChart1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        pnlSucchuakhoLayout.setVerticalGroup(
            pnlSucchuakhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSucchuakhoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSucchuakhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBieudosucchuakho)
                    .addComponent(cboKho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlChart1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlBackgroundLayout = new javax.swing.GroupLayout(pnlBackground);
        pnlBackground.setLayout(pnlBackgroundLayout);
        pnlBackgroundLayout.setHorizontalGroup(
            pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlFooter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(roundPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlBackgroundLayout.createSequentialGroup()
                        .addComponent(pnlBanhang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlSucchuakho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlBackgroundLayout.setVerticalGroup(
            pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(roundPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlBanhang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlSucchuakho, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlFooter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jMenuBar1.setBorder(null);
        jMenuBar1.setForeground(new java.awt.Color(255, 255, 255));

        mTongquan.setText("Tổng quan");
        jMenuBar1.add(mTongquan);

        mDanhmucsp.setText("Danh mục sản phẩm");
        mDanhmucsp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mDanhmucspMouseClicked(evt);
            }
        });
        jMenuBar1.add(mDanhmucsp);

        mhanghoa.setText("Hàng hóa");
        jMenuBar1.add(mhanghoa);

        mNhanvien.setText("Nhân viên");

        mChamcong.setText("Chấm công");
        mNhanvien.add(mChamcong);

        mLuong.setText("Lương");
        mNhanvien.add(mLuong);

        jMenuBar1.add(mNhanvien);

        mNhacungcap.setText("Nhà cung cấp");

        mLichsunhap.setText("Lịch sử nhập");
        mNhacungcap.add(mLichsunhap);

        mNonhacungcap.setText("Nợ NCC");
        mNhacungcap.add(mNonhacungcap);

        jMenuBar1.add(mNhacungcap);

        mThongke.setText("Thống kê");

        mDoanhthutong.setText("Doanh thu tổng");
        mThongke.add(mDoanhthutong);

        mDoanhthutungcuahang.setText("Doanh thu từng cửa hàng");
        mThongke.add(mDoanhthutungcuahang);

        mChitieu.setText("Chi tiêu");
        mThongke.add(mChitieu);

        jMenuBar1.add(mThongke);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlBackground, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cboKhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboKhoActionPerformed
        // TODO add your handling code here:
        LoadData();
    }//GEN-LAST:event_cboKhoActionPerformed

    private void cboSLDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboSLDateActionPerformed

        try {
            // TODO add your handling code here:
            SLDate();
        } catch (ParseException ex) {
            Logger.getLogger(tongquan.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_cboSLDateActionPerformed

    private void mDanhmucspMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mDanhmucspMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_mDanhmucspMouseClicked

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
            java.util.logging.Logger.getLogger(tongquan.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(tongquan.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(tongquan.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(tongquan.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new tongquan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.raven.chart.Chart Chart;
    private com.raven.swing.progress.Progress Chart1;
    private javax.swing.JComboBox<String> cboKho;
    private javax.swing.JComboBox<String> cboSLDate;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel lblBieudobanhang;
    private javax.swing.JLabel lblBieudosucchuakho;
    private javax.swing.JLabel lblChart1;
    private javax.swing.JMenu mChamcong;
    private javax.swing.JMenu mChitieu;
    private javax.swing.JMenu mDanhmucsp;
    private javax.swing.JMenu mDoanhthutong;
    private javax.swing.JMenu mDoanhthutungcuahang;
    private javax.swing.JMenu mLichsunhap;
    private javax.swing.JMenu mLuong;
    private javax.swing.JMenu mNhacungcap;
    private javax.swing.JMenu mNhanvien;
    private javax.swing.JMenu mNonhacungcap;
    private javax.swing.JMenu mThongke;
    private javax.swing.JMenu mTongquan;
    private javax.swing.JMenu mhanghoa;
    private javax.swing.JPanel pnlBackground;
    private com.raven.swing.RoundPanel pnlBanhang;
    private javax.swing.JPanel pnlChart1;
    private javax.swing.JPanel pnlFooter;
    private com.raven.swing.RoundPanel pnlSucchuakho;
    private com.raven.swing.progress.Progress progress8;
    private com.raven.swing.RoundPanel roundPanel2;
    // End of variables declaration//GEN-END:variables
}
