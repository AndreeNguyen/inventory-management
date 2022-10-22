/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view.Danhmucsp;

import com.QLKH.controller.productDAO.KhoDao;
import com.QLKH.controller.productDAO.LoaiHangDao;
import com.QLKH.controller.productDAO.SanPhamDao;
import com.QLKH.entity.product.Kho;
import com.QLKH.entity.product.SanPham;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import com.QLKH.entity.product.LoaiHang;

public class danhmucsp extends javax.swing.JFrame {

    DefaultTableModel tableModel;

    SanPhamDao daoSP = new SanPhamDao();
    KhoDao daoKho = new KhoDao();
    LoaiHangDao daoLH = new LoaiHangDao();

    /**
     * Creates new form tongquan
     */
    public danhmucsp() {
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Danh mục sản phẩm");
        setLocationRelativeTo(null);
        initTable();
        tblDMSP.getColumn("Mã SP").setMinWidth(0);
        tblDMSP.getColumn("Mã SP").setMaxWidth(0);
        tblDMSP.getColumn("Mã SP").setWidth(0);

        tblDMSP.getColumn("Mã Kho").setMinWidth(0);
        tblDMSP.getColumn("Mã Kho").setMaxWidth(0);
        tblDMSP.getColumn("Mã Kho").setWidth(0);

        tblDMSP.getColumn("Tên NCC").setMinWidth(0);
        tblDMSP.getColumn("Tên NCC").setMaxWidth(0);
        tblDMSP.getColumn("Tên NCC").setWidth(0);

        loaddata();
        loadcboKho();
        loadcboLoaiHang();
    }

    private void initTable() {

        tableModel = new DefaultTableModel();
        Object[] Columns = new Object[]{"Mã SP", "Tên SP", "Loại SP", "Số lượng", "Giá", "ĐVT", "Khu", "Mã Kho", "Tên NCC"};
        tableModel.setColumnIdentifiers(Columns);
        tblDMSP.setModel(tableModel);

    }

    private void loaddata() {

        try {
            DefaultTableModel model = (DefaultTableModel) tblDMSP.getModel();
            model.setRowCount(0);
            List<SanPham> list = daoSP.select();
            for (SanPham sp : list) {
                Object[] row = {
                    sp.getMaSP(),
                    sp.getTenSp(),
                    sp.getLoaiSP(),
                    sp.getSoLuong(),
                    sp.getGia(),
                    sp.getDonVi(),
                    sp.getKhu(),
                    sp.getMaKho(),
                    sp.getNhaCC()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi truy vấn dữ liệu", "Lỗi ", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadcboKho() {
        cboKho.addItem("Kho Tổng");
        try {

            List<Kho> list = daoKho.select();
            for (Kho kh : list) {
                cboKho.addItem(kh.getTenKho());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadcboLoaiHang() {
        cboNhomHang.addItem("Tổng thể");
        try {
            List<LoaiHang> list = daoLH.select();
            for (LoaiHang lh : list) {
                cboNhomHang.addItem(lh.getTenLH());
            }
        } catch (Exception e) {
            e.printStackTrace();
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

        jPanel5 = new javax.swing.JPanel();
        progress8 = new com.raven.swing.progress.Progress();
        jLabel6 = new javax.swing.JLabel();
        jPanelBackground = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        roundPanel2 = new com.raven.swing.RoundPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDMSP = new javax.swing.JTable();
        roundPanel4 = new com.raven.swing.RoundPanel();
        jLabel2 = new javax.swing.JLabel();
        cboNhomHang = new javax.swing.JComboBox<>();
        roundPanel1 = new com.raven.swing.RoundPanel();
        roundPanel3 = new com.raven.swing.RoundPanel();
        jLabel1 = new javax.swing.JLabel();
        txtSreach = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cboKho = new javax.swing.JComboBox<>();
        btnThem = new javax.swing.JButton();
        btnXemCT = new javax.swing.JButton();
        Import = new javax.swing.JButton();
        jMenuBar = new javax.swing.JMenuBar();
        MTongquan = new javax.swing.JMenu();
        MDanhmucsp = new javax.swing.JMenu();
        MHanghoa = new javax.swing.JMenu();
        MNhaphang = new javax.swing.JMenu();
        MXuathang = new javax.swing.JMenu();
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

        tblDMSP.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblDMSP);

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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(160, Short.MAX_VALUE))
        );

        roundPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setText("Nhóm hàng");

        cboNhomHang.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboNhomHangItemStateChanged(evt);
            }
        });

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
                    .addComponent(cboNhomHang, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        roundPanel4Layout.setVerticalGroup(
            roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboNhomHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        jLabel1.setText("Danh mục sản phẩm");

        txtSreach.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSreachKeyReleased(evt);
            }
        });

        jLabel3.setText("Seach");

        cboKho.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboKhoItemStateChanged(evt);
            }
        });

        btnThem.setText("Thêm ");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnXemCT.setText("Xem chi tiết");
        btnXemCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemCTActionPerformed(evt);
            }
        });

        Import.setText("Import");
        Import.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ImportActionPerformed(evt);
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
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtSreach, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboKho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnThem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnXemCT)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Import))
                    .addComponent(roundPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                            .addComponent(txtSreach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(cboKho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThem)
                            .addComponent(btnXemCT)
                            .addComponent(Import))
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

        jMenuBar.setBorder(null);
        jMenuBar.setForeground(new java.awt.Color(255, 255, 255));

        MTongquan.setText("Tổng quan");
        jMenuBar.add(MTongquan);

        MDanhmucsp.setText("Danh mục sản phẩm");
        jMenuBar.add(MDanhmucsp);

        MHanghoa.setText("Hàng hóa");

        MNhaphang.setText("Nhập hàng");
        MHanghoa.add(MNhaphang);

        MXuathang.setText("Xuất hàng");
        MHanghoa.add(MXuathang);

        jMenuBar.add(MHanghoa);

        jMenu4.setText("Nhân viên");
        jMenuBar.add(jMenu4);

        jMenu5.setText("Nhà cung cấp");
        jMenuBar.add(jMenu5);

        jMenu6.setText("Thống kê");
        jMenuBar.add(jMenu6);

        setJMenuBar(jMenuBar);

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

    private void Import() {
        //Đang cập nhật
    }

    private void ImportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ImportActionPerformed
        Import();
    }//GEN-LAST:event_ImportActionPerformed

    private void cboNhomHangItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboNhomHangItemStateChanged

        if (cboNhomHang.getSelectedItem().equals("Tổng thể")) {
            loaddata();
            return;
        }
        String TenLH = String.valueOf(cboNhomHang.getSelectedItem());
        DefaultTableModel model = (DefaultTableModel) tblDMSP.getModel();
        model.setRowCount(0);

        List<Object[]> list = daoSP.getLoaiSPtoTable(TenLH);
        for (Object[] row : list) {
            model.addRow(row);
        }
    }//GEN-LAST:event_cboNhomHangItemStateChanged

    private void cboKhoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboKhoItemStateChanged
        if (cboKho.getSelectedItem().equals("Kho Tổng")) {
            loaddata();
            return;
        }
        String Kho = String.valueOf(cboKho.getSelectedItem());
        DefaultTableModel model = (DefaultTableModel) tblDMSP.getModel();
        model.setRowCount(0);

        List<Object[]> list = daoSP.getTenKhotoTable(Kho);
        for (Object[] row : list) {
            model.addRow(row);
        }
    }//GEN-LAST:event_cboKhoItemStateChanged

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        themsp them = new themsp();
        them.setTitle("Thêm Sản Phẩm");
        them.setVisible(true);

    }//GEN-LAST:event_btnThemActionPerformed

    public void changeForm() {
        int columnMaSP = 0;
        int columnMaKho = 7;
        int columntenNCC = 8;
        int rowsl = tblDMSP.getSelectedRow();
        if (rowsl == -1) {
            JOptionPane.showMessageDialog(null, "Mời bạn chọn 1 sản phẩm để xem chi tiết", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String masp = tblDMSP.getModel().getValueAt(rowsl, columnMaSP).toString();
        String makho = tblDMSP.getModel().getValueAt(rowsl, columnMaKho).toString();
        String tenncc = tblDMSP.getModel().getValueAt(rowsl, columntenNCC).toString();        
        themsp ct = new themsp();
        ct.GetValueMa(masp, makho, tenncc);
        ct.setTitle("Xem Chi Tiết");
        ct.setVisible(true);
    }

    private void btnXemCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemCTActionPerformed
        changeForm();
    }//GEN-LAST:event_btnXemCTActionPerformed

    private void findByAuto(String str) {
        tableModel = (DefaultTableModel) tblDMSP.getModel();
        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(tableModel);
        tblDMSP.setRowSorter(trs);
        trs.setRowFilter(RowFilter.regexFilter(str));
//        tblDMSP.setPreferredScrollableViewportSize(tblDMSP.getPreferredSize());
//        tblDMSP.setFillsViewportHeight(true);

    }

    private void txtSreachKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSreachKeyReleased
        findByAuto(txtSreach.getText());
    }//GEN-LAST:event_txtSreachKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new danhmucsp().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Import;
    private javax.swing.JMenu MDanhmucsp;
    private javax.swing.JMenu MHanghoa;
    private javax.swing.JMenu MNhaphang;
    private javax.swing.JMenu MTongquan;
    private javax.swing.JMenu MXuathang;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXemCT;
    private javax.swing.JComboBox<String> cboKho;
    private javax.swing.JComboBox<String> cboNhomHang;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanelBackground;
    private javax.swing.JScrollPane jScrollPane1;
    private com.raven.swing.progress.Progress progress8;
    private com.raven.swing.RoundPanel roundPanel1;
    private com.raven.swing.RoundPanel roundPanel2;
    private com.raven.swing.RoundPanel roundPanel3;
    private com.raven.swing.RoundPanel roundPanel4;
    private javax.swing.JTable tblDMSP;
    private javax.swing.JTextField txtSreach;
    // End of variables declaration//GEN-END:variables
}
