/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view.Nhacungcap;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.DatabaseHelper;
import utils.MSGBox;
import validate.Validation;


/**
 *
 * @author doqua
 */
public class themnhacungcap extends javax.swing.JFrame {

    /**
     * Creates new form themsp
     */
    public themnhacungcap() {
        initComponents();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

       private void save() {
        StringBuilder sb = new StringBuilder();
        Validation.checkEmail(txtEmail, sb);
        Validation.checkEmpty(txtMaNCC, sb, "Mã nhà cung cấp không đuược rỗng!!");
        Validation.checkEmpty(txtTenNCC, sb, "Tên nhà cung cấp không đuược rỗng!!");
        Validation.checkEmpty(txtDienThoai, sb, "Điện thoại nhà cung cấp không đuược rỗng!!");
        Validation.checkEmpty(txtEmail, sb, "Email nhà cung cấp không đuược rỗng!!");
        Validation.checkEmpty(txtCongTy, sb, "Công ty nhà cung cấp không đuược rỗng!!");
        Validation.checkEmpty(txtMaSoThue, sb, "Mã số thuế nhà cung cấp không đuược rỗng!!");
        Validation.checkEmpty(txtNhomNCC, sb, "Nhóm nhà cung cấp không đuược rỗng!!");
        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(this, sb.toString(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {

            Connection con = DatabaseHelper.oppenConnection();
            String sql = "INSERT INTO NhaCungCap VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, txtMaNCC.getText());
            ps.setString(2, txtTenNCC.getText());
            ps.setString(3, txtDienThoai.getText());
            ps.setString(4, txtEmail.getText());
            ps.setString(5, txaDiaChi.getText());
            ps.setString(6, txtMaSoThue.getText());
            ps.setString(7, txtCongTy.getText());
            ps.setString(8, txtNhomNCC.getText());
            ps.setString(9, LocalDate.now() + "");
            
            ps.executeUpdate();
            MSGBox.alert(this, "Thêm thành công");
            con.close();
            new nhacungcap().setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            MSGBox.alert(this, "Thêm thất bại");
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

        jPanel1 = new javax.swing.JPanel();
        roundPanel1 = new com.raven.swing.RoundPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtTenNCC = new javax.swing.JTextField();
        txtMaNCC = new javax.swing.JTextField();
        txtDienThoai = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtCongTy = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtMaSoThue = new javax.swing.JTextField();
        txtNhomNCC = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        btnSave = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        txaDiaChi = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        txaGhiChu = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Mã nhà cung cấp");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Tên nhà cung cấp");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Điện thoại");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Địa chỉ");

        txtTenNCC.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtMaNCC.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtDienThoai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtEmail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel18.setText("Email");

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel19.setText("Công ty");

        txtCongTy.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel20.setText("Mã số thuế");

        txtMaSoThue.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMaSoThue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaSoThueActionPerformed(evt);
            }
        });

        txtNhomNCC.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel21.setText("Nhóm NCC");

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel22.setText("Ghi chú");

        btnSave.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSave.setText("Lưu");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnExit.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnExit.setText("Bỏ qua");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        txaDiaChi.setColumns(20);
        txaDiaChi.setRows(5);
        jScrollPane3.setViewportView(txaDiaChi);

        txaGhiChu.setColumns(20);
        txaGhiChu.setRows(5);
        jScrollPane4.setViewportView(txaGhiChu);

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtDienThoai, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                                .addComponent(txtTenNCC)
                                .addComponent(txtMaNCC))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22)))
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addComponent(btnSave)
                        .addGap(43, 43, 43)
                        .addComponent(btnExit))
                    .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtMaSoThue, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                        .addComponent(txtNhomNCC, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                        .addComponent(jScrollPane4))
                    .addComponent(txtCongTy, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55))
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtMaNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(txtCongTy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTenNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(txtMaSoThue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(txtNhomNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22))))
                .addGap(23, 23, 23)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave)
                    .addComponent(btnExit))
                .addContainerGap(45, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtMaSoThueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaSoThueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaSoThueActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        save();
        
    }//GEN-LAST:event_btnSaveActionPerformed

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
            java.util.logging.Logger.getLogger(themnhacungcap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(themnhacungcap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(themnhacungcap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(themnhacungcap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new themnhacungcap().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnSave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private com.raven.swing.RoundPanel roundPanel1;
    private javax.swing.JTextArea txaDiaChi;
    private javax.swing.JTextArea txaGhiChu;
    private javax.swing.JTextField txtCongTy;
    private javax.swing.JTextField txtDienThoai;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtMaNCC;
    private javax.swing.JTextField txtMaSoThue;
    private javax.swing.JTextField txtNhomNCC;
    private javax.swing.JTextField txtTenNCC;
    // End of variables declaration//GEN-END:variables
}
