/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view.Nhacungcap;

import controller.productDAO.NhaCungCapDao;
import entity.product.NhaCungCap;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import java.text.ParseException;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;
import utils.MSGBox;

/**
 *
 * @author doqua
 */
public class nhacungcap extends javax.swing.JFrame {

    NhaCungCapDao daoNCC = new NhaCungCapDao();
    public static List<NhaCungCap> listNCC = new ArrayList<>();

    public nhacungcap() {
        initComponents();
        setLocationRelativeTo(null);
        fillTable();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    public static List<NhaCungCap> getList() {
        return listNCC;
    }

    private void initTable(DefaultTableModel model) {
        Object[] columns = new Object[]{"Mã NCC", "Tên NCC", "SDT", "Email"};
        model.setColumnIdentifiers(columns);
        tblList_NCC.setModel(model);
    }

    private void fillTable() {
        DefaultTableModel model = (DefaultTableModel) tblList_NCC.getModel();
        initTable(model);
        model.setRowCount(0);
        try {
             listNCC = daoNCC.selectAll();
            for (NhaCungCap entity : listNCC) {
                Object[] rows = {entity.getMaNCC(), entity.getTenNCC(), entity.getSDT(), entity.getEmail()};
                model.addRow(rows);
            }
            model.fireTableDataChanged();
        } catch (Exception e) {
            MSGBox.alert(this, "Lỗi truy vấn dữ liệu!");
            

        }
    }

    private void findByAuto(String str) {
        DefaultTableModel model = (DefaultTableModel) tblList_NCC.getModel();
        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(model);
        tblList_NCC.setRowSorter(trs);
        trs.setRowFilter(RowFilter.regexFilter(str));
    }

    private void changeform() {
        int column = 0;
        int rowsl = tblList_NCC.getSelectedRow();
        String value = tblList_NCC.getValueAt(rowsl, column).toString();
        lichsunhap ls = new lichsunhap();// obj created for class Second()
        try {
            ls.getValueNCC(value);//Execute the method my_update to pass str
        } catch (ParseException ex) {

        }
        ls.setVisible(true); // Open the Second.java window
        //dispose(); // Close the First.java window
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        progress8 = new com.raven.swing.progress.Progress();
        jLabel6 = new javax.swing.JLabel();
        jPanelBackground = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        roundPanel2 = new com.raven.swing.RoundPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblList_NCC = new com.raven.swing.Table();
        roundPanel4 = new com.raven.swing.RoundPanel();
        cboNCC = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        roundPanel1 = new com.raven.swing.RoundPanel();
        roundPanel3 = new com.raven.swing.RoundPanel();
        jLabel1 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnThemNCC = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu7 = new javax.swing.JMenu();
        jMenu8 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();

        jPanel5.setOpaque(false);

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

        jPanelBackground.setBackground(new java.awt.Color(153, 153, 153));
        jPanelBackground.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 24, Short.MAX_VALUE)
        );

        roundPanel2.setBackground(new java.awt.Color(255, 255, 255));

        tblList_NCC.setForeground(new java.awt.Color(255, 255, 255));
        tblList_NCC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblList_NCC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblList_NCCMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblList_NCC);

        javax.swing.GroupLayout roundPanel2Layout = new javax.swing.GroupLayout(roundPanel2);
        roundPanel2.setLayout(roundPanel2Layout);
        roundPanel2Layout.setHorizontalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 656, Short.MAX_VALUE)
                .addContainerGap())
        );
        roundPanel2Layout.setVerticalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
                .addContainerGap())
        );

        roundPanel4.setBackground(new java.awt.Color(255, 255, 255));

        cboNCC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setText("Nhóm nhà cung cấp");

        javax.swing.GroupLayout roundPanel4Layout = new javax.swing.GroupLayout(roundPanel4);
        roundPanel4.setLayout(roundPanel4Layout);
        roundPanel4Layout.setHorizontalGroup(
            roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 63, Short.MAX_VALUE))
                    .addComponent(cboNCC, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        roundPanel4Layout.setVerticalGroup(
            roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 136, Short.MAX_VALUE)
        );

        roundPanel3.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout roundPanel3Layout = new javax.swing.GroupLayout(roundPanel3);
        roundPanel3.setLayout(roundPanel3Layout);
        roundPanel3Layout.setHorizontalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        roundPanel3Layout.setVerticalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 153, Short.MAX_VALUE)
        );

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Nhà cung cấp");

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        jLabel3.setText("Search");

        btnThemNCC.setText("Thêm nhà cung cấp");
        btnThemNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNCCActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelBackgroundLayout = new javax.swing.GroupLayout(jPanelBackground);
        jPanelBackground.setLayout(jPanelBackgroundLayout);
        jPanelBackgroundLayout.setHorizontalGroup(
            jPanelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanelBackgroundLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(roundPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(roundPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelBackgroundLayout.createSequentialGroup()
                        .addComponent(roundPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanelBackgroundLayout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(btnThemNCC)
                        .addGap(68, 68, 68))))
        );
        jPanelBackgroundLayout.setVerticalGroup(
            jPanelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBackgroundLayout.createSequentialGroup()
                .addGroup(jPanelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelBackgroundLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBackgroundLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(btnThemNCC))
                        .addGap(18, 18, 18)))
                .addGroup(jPanelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(roundPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelBackgroundLayout.createSequentialGroup()
                        .addComponent(roundPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jMenuBar1.setBorder(null);
        jMenuBar1.setForeground(new java.awt.Color(255, 255, 255));

        jMenu1.setText("Tổng quan");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Danh mục sản phẩm");
        jMenuBar1.add(jMenu2);

        jMenu3.setText("Hàng hóa");

        jMenu7.setText("Nhập hàng");
        jMenu3.add(jMenu7);

        jMenu8.setText("Xuất hàng");
        jMenu3.add(jMenu8);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Nhân viên");
        jMenuBar1.add(jMenu4);

        jMenu5.setText("Nhà cung cấp");
        jMenuBar1.add(jMenu5);

        jMenu6.setText("Thống kê");
        jMenuBar1.add(jMenu6);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelBackground, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNCCActionPerformed
        themnhacungcap th = new themnhacungcap();
        th.setVisible(true);

    }//GEN-LAST:event_btnThemNCCActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        findByAuto(txtSearch.getText());
    }//GEN-LAST:event_txtSearchKeyReleased

    private void tblList_NCCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblList_NCCMouseClicked
        changeform();
    }//GEN-LAST:event_tblList_NCCMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new nhacungcap().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnThemNCC;
    private javax.swing.JComboBox<String> cboNCC;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanelBackground;
    private javax.swing.JScrollPane jScrollPane1;
    private com.raven.swing.progress.Progress progress8;
    private com.raven.swing.RoundPanel roundPanel1;
    private com.raven.swing.RoundPanel roundPanel2;
    private com.raven.swing.RoundPanel roundPanel3;
    private com.raven.swing.RoundPanel roundPanel4;
    private com.raven.swing.Table tblList_NCC;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
