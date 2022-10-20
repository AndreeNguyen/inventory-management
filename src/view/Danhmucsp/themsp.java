/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view.Danhmucsp;

import DAO.KhoDao;
import DAO.LoaiHangDao;
import DAO.SanPhamDao;
import Model.Kho;
import Model.LoaiHang;
import Model.SanPham;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author doqua
 */
public class themsp extends javax.swing.JFrame {

    /**
     * Creates new form themsp
     */
    public themsp() {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        jpVisible1.setVisible(false);
        jpVisible2.setVisible(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        LoadcboLH();
        loadcboKho();
        loadtblLoaiHang();
        loadtblKho();
    }

    SanPhamDao daoSP = new SanPhamDao();
    LoaiHangDao daoLH = new LoaiHangDao();
    KhoDao daoKho = new KhoDao();

    private void loadtblLoaiHang() {
        try {
            DefaultTableModel model = (DefaultTableModel) tblLoaiHang.getModel();
            model.setRowCount(0);
            List<LoaiHang> list = daoLH.select();
            for (LoaiHang lh : list) {
                Object[] row = {
                    lh.getMaLH(),
                    lh.getTenLH(),
                    lh.getMaNcc()};
                model.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi truy vấn dữ liệu", "Lỗi ", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadtblKho() {
        try {
            DefaultTableModel model = (DefaultTableModel) tblKho.getModel();
            model.setRowCount(0);
            List<Kho> list = daoKho.select();
            for (Kho kh : list) {
                Object[] row = {
                    kh.getMaKho(),
                    kh.getTenKho(),
                    kh.getKhuVuc(),
                    kh.getManv()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi truy vấn dữ liệu", "Lỗi ", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void LoadcboLH() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboLSP.getModel();
        model.removeAllElements();
        try {
            List<LoaiHang> list = daoLH.select();
            for (LoaiHang lh : list) {
                cboLSP.addItem(lh.getTenLH());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void findbyLoaiHang(String str) {
        DefaultTableModel tableModel;
        tableModel = (DefaultTableModel) tblLoaiHang.getModel();
        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(tableModel);
        tblLoaiHang.setRowSorter(trs);
        trs.setRowFilter(RowFilter.regexFilter(str));
        tblLoaiHang.setPreferredScrollableViewportSize(tblLoaiHang.getPreferredSize());
        tblLoaiHang.setFillsViewportHeight(true);

    }

    private void findbyKho(String str) {
        DefaultTableModel tableModel;
        tableModel = (DefaultTableModel) tblKho.getModel();
        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(tableModel);
        tblKho.setRowSorter(trs);
        trs.setRowFilter(RowFilter.regexFilter(str));
        tblKho.setPreferredScrollableViewportSize(tblLoaiHang.getPreferredSize());
        tblKho.setFillsViewportHeight(true);

    }

    public String ChangeMaLH() {
        String MaLHChange;
        if (tblLoaiHang.getRowCount() > 0) {
            MaLHChange = String.valueOf(tblLoaiHang.getValueAt(0, 0));
        } else {
            MaLHChange = null;
        }
        return MaLHChange;
    }

    public String ChangeMaKho() {
        String MaKhoChange;
        if (tblKho.getRowCount() > 0) {
            MaKhoChange = String.valueOf(tblKho.getValueAt(0, 0));
        } else {
            MaKhoChange = null;
        }
        return MaKhoChange;
    }

    public String ChangeTenKho() {
        String TenKhoChange;
        if (tblKho.getRowCount() > 0) {
            TenKhoChange = String.valueOf(tblKho.getValueAt(1, 0));
        } else {
            TenKhoChange = null;
        }
        return TenKhoChange;
    }

    private void loadcboKho() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboKho.getModel();
        model.removeAllElements();
        try {
            List<Kho> list = daoKho.select();
            for (Kho kh : list) {
                cboKho.addItem(kh.getTenKho());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void setModel(SanPham model) throws ParseException {
        txtMaSP.setText(model.getMaSP());
        cboLSP.setSelectedItem(model.getLoaiSP());
        txtTenSP.setText(model.getTenSp());
        cboKLT.setSelectedItem(String.valueOf(model.getKhu()));
        txtGia.setText(String.valueOf(model.getGia()));
        txtSoLuong.setText(String.valueOf(model.getSoLuong()));
        cboDVT.setSelectedItem(String.valueOf(model.getDonVi()));
        Date sdf;
        dcsNSX.setDate(sdf = new SimpleDateFormat("yyyy-MM-dd").parse(model.getNSX()));
        dcsNHH.setDate(sdf = new SimpleDateFormat("yyyy-MM-dd").parse(model.getNHH()));
        cboKho.setSelectedItem("Kho " + (model.getMaKho()).charAt(model.getMaKho().length() - 1));

    }

    SanPham getModel() {
        SanPham model = new SanPham();
        model.setMaSP(txtMaSP.getText());
        model.setMaKho(ChangeMaKho());
        model.setLoaiSP(ChangeMaLH());
        model.setTenSp(txtTenSP.getText());
        model.setGia(Float.parseFloat(txtGia.getText()));
        model.setDonVi(String.valueOf(cboDVT.getSelectedItem()));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        model.setNSX(sdf.format(dcsNSX.getDate()));
        model.setNHH(sdf.format(dcsNHH.getDate()));
        model.setKhu(String.valueOf(cboKLT.getSelectedItem()));
        return model;
    }

    public void GetValueMa(String masp, String makho) {
        txtMaSP.setEnabled(false);
        try {
            SanPham model = daoSP.findByName(masp, makho);
            if (model != null) {
                this.setModel(model);
//                this.setStatus(false);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

//    public String GetTenKhotoset(String makho) {
//        String tenKho = "Kho " + makho.charAt(makho.length()-1);
//        return tenKho;
//    }
    boolean check() {
        if (txtMaSP.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập Mã Sản Phẩm", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
//        if(txtMaVach.getText().equals("")){
//            JOptionPane.showMessageDialog(this, "Bạn chưa nhập Mã Sản Phẩm", "Lỗi", JOptionPane.ERROR_MESSAGE);
//            return false;
//        }

        if (txtTenSP.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập Tên Sản Phẩm", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (txtGia.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập giá ", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        try {
            Double gia = Double.parseDouble(txtGia.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Bạn phải nhập giá là số ", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (txtSoLuong.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập số lượng ", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        try {
            Double soluong = Double.parseDouble(txtSoLuong.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Bạn phải số lượng là sô ", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (dcsNSX.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Ngày Sản Xuất phải đúng định dạng", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (dcsNHH.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Ngày Hết Hạn phải đúng định dạng", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (dcsNSX.getDate().after(dcsNHH.getDate())) {
            JOptionPane.showMessageDialog(this, "Ngày Sản Xuất không được sau Ngày Hết Hạn", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        
        return true;
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
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtTenSP = new javax.swing.JTextField();
        txtMaSP = new javax.swing.JTextField();
        txtSoLuong = new javax.swing.JTextField();
        cboDVT = new javax.swing.JComboBox<>();
        btnThemDVT = new javax.swing.JButton();
        roundPanel3 = new com.raven.swing.RoundPanel();
        roundPanel4 = new com.raven.swing.RoundPanel();
        roundPanel5 = new com.raven.swing.RoundPanel();
        roundPanel6 = new com.raven.swing.RoundPanel();
        roundPanel7 = new com.raven.swing.RoundPanel();
        roundPanel8 = new com.raven.swing.RoundPanel();
        dcsNSX = new com.toedter.calendar.JDateChooser();
        dcsNHH = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        txtGia = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cboLSP = new javax.swing.JComboBox<>();
        btnThemSP = new javax.swing.JButton();
        cboKho = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        cboKLT = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jpVisible2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblKho = new javax.swing.JTable();
        jpVisible1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblLoaiHang = new javax.swing.JTable();
        jComboBox1 = new javax.swing.JComboBox<>();
        roundPanel2 = new com.raven.swing.RoundPanel();
        btnSave = new javax.swing.JButton();
        btnCancle = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("Mã sản phẩm");

        jLabel3.setText("Tên sản phẩm");

        jLabel6.setText("Số lượng");

        jLabel7.setText("Ngày sản xuất");

        jLabel8.setText("Ngày hết hạn");

        jLabel10.setText("Đơn vị tính");

        cboDVT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hộp ", "Lon", "Bịch", "Thùng" }));

        btnThemDVT.setText("Thêm");
        btnThemDVT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemDVTActionPerformed(evt);
            }
        });

        roundPanel3.setBackground(new java.awt.Color(204, 255, 153));

        javax.swing.GroupLayout roundPanel4Layout = new javax.swing.GroupLayout(roundPanel4);
        roundPanel4.setLayout(roundPanel4Layout);
        roundPanel4Layout.setHorizontalGroup(
            roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        roundPanel4Layout.setVerticalGroup(
            roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout roundPanel5Layout = new javax.swing.GroupLayout(roundPanel5);
        roundPanel5.setLayout(roundPanel5Layout);
        roundPanel5Layout.setHorizontalGroup(
            roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        roundPanel5Layout.setVerticalGroup(
            roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout roundPanel6Layout = new javax.swing.GroupLayout(roundPanel6);
        roundPanel6.setLayout(roundPanel6Layout);
        roundPanel6Layout.setHorizontalGroup(
            roundPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        roundPanel6Layout.setVerticalGroup(
            roundPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout roundPanel7Layout = new javax.swing.GroupLayout(roundPanel7);
        roundPanel7.setLayout(roundPanel7Layout);
        roundPanel7Layout.setHorizontalGroup(
            roundPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        roundPanel7Layout.setVerticalGroup(
            roundPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout roundPanel8Layout = new javax.swing.GroupLayout(roundPanel8);
        roundPanel8.setLayout(roundPanel8Layout);
        roundPanel8Layout.setHorizontalGroup(
            roundPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        roundPanel8Layout.setVerticalGroup(
            roundPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout roundPanel3Layout = new javax.swing.GroupLayout(roundPanel3);
        roundPanel3.setLayout(roundPanel3Layout);
        roundPanel3Layout.setHorizontalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(roundPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(roundPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(roundPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(roundPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(roundPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        roundPanel3Layout.setVerticalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(roundPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        dcsNSX.setDateFormatString("yyyy-MM-dd");

        dcsNHH.setDateFormatString("yyyy-MM-dd");

        jLabel2.setText("Nhà cung cấp:");

        jLabel13.setText("Giá:");

        jLabel5.setText("Loại Sản Phẩm:");

        cboLSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboLSP.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboLSPItemStateChanged(evt);
            }
        });

        btnThemSP.setText("Thêm Loại SP");
        btnThemSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSPActionPerformed(evt);
            }
        });

        cboKho.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboKho.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboKhoItemStateChanged(evt);
            }
        });

        jLabel14.setText("Kho: ");

        cboKLT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Khu A", "Khu B", "Khu C", "Khu D", "Khu B" }));

        jLabel11.setText("Khu Lưu Trữ:");

        tblKho.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tblKho);

        javax.swing.GroupLayout jpVisible2Layout = new javax.swing.GroupLayout(jpVisible2);
        jpVisible2.setLayout(jpVisible2Layout);
        jpVisible2Layout.setHorizontalGroup(
            jpVisible2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpVisible2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jpVisible2Layout.setVerticalGroup(
            jpVisible2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpVisible2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );

        tblLoaiHang.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(tblLoaiHang);

        javax.swing.GroupLayout jpVisible1Layout = new javax.swing.GroupLayout(jpVisible1);
        jpVisible1.setLayout(jpVisible1Layout);
        jpVisible1Layout.setHorizontalGroup(
            jpVisible1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpVisible1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpVisible1Layout.setVerticalGroup(
            jpVisible1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpVisible1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(roundPanel1Layout.createSequentialGroup()
                                .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(79, 79, 79)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(roundPanel1Layout.createSequentialGroup()
                                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dcsNSX, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(roundPanel1Layout.createSequentialGroup()
                                .addGap(144, 144, 144)
                                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnThemSP, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                                    .addComponent(btnThemDVT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGap(314, 314, 314)
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(roundPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jpVisible1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(roundPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cboKLT, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cboKho, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cboLSP, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cboDVT, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dcsNHH, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(roundPanel1Layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(jpVisible2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                .addContainerGap(173, Short.MAX_VALUE)
                .addComponent(roundPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(170, 170, 170))
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1)
                                .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7))
                            .addComponent(dcsNSX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dcsNHH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel8)))
                        .addGap(18, 18, 18)
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(cboDVT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThemDVT)
                            .addComponent(jLabel6)
                            .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(cboLSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThemSP)
                            .addComponent(jLabel13)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel14))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(cboKho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(13, 13, 13)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboKLT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addComponent(jpVisible1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                        .addComponent(jpVisible2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(roundPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        roundPanel2.setBackground(new java.awt.Color(255, 255, 255));

        btnSave.setText("Lưu");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnCancle.setText("Bỏ qua");
        btnCancle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancleActionPerformed(evt);
            }
        });

        btnUpdate.setText("Cập nhật");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel2Layout = new javax.swing.GroupLayout(roundPanel2);
        roundPanel2.setLayout(roundPanel2Layout);
        roundPanel2Layout.setHorizontalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSave)
                .addGap(18, 18, 18)
                .addComponent(btnUpdate)
                .addGap(18, 18, 18)
                .addComponent(btnCancle)
                .addGap(335, 335, 335))
        );
        roundPanel2Layout.setVerticalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave)
                    .addComponent(btnCancle)
                    .addComponent(btnUpdate))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(roundPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(roundPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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

    private void insert() {
        SanPham model = getModel();
        try {
            daoSP.insert(model);
            JOptionPane.showMessageDialog(null, "Thêm Sản Phẩm thành công!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Thêm Sản Phẩm bị lỗi");
        }
    }

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        if (check()) {
            insert();
        }

    }//GEN-LAST:event_btnSaveActionPerformed

    private void update() {
        SanPham model = getModel();
        try {
            daoSP.Update(model);
            JOptionPane.showMessageDialog(null, "Cập Nhật Sản Phẩm thành công!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Cập Nhật Sản Phẩm bị lỗi");
        }
    }


    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        if (check()) {
            update();
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnCancleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancleActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnCancleActionPerformed

    private void cboKhoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboKhoItemStateChanged
        findbyKho(String.valueOf(cboKho.getSelectedItem()));
        ChangeMaKho();
    }//GEN-LAST:event_cboKhoItemStateChanged

    private void insertLH(){
        
    }
    
    private void btnThemSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSPActionPerformed

    }//GEN-LAST:event_btnThemSPActionPerformed

    private void cboLSPItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboLSPItemStateChanged
        findbyLoaiHang(String.valueOf(cboLSP.getSelectedItem()));
        ChangeMaLH();
    }//GEN-LAST:event_cboLSPItemStateChanged

    private void btnThemDVTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemDVTActionPerformed
        String DVT = JOptionPane.showInputDialog(this, "Mời bạn thêm đơn vị ");
        if (DVT.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bạn không được nhập kí tự rỗng");
            return;
        }
        cboDVT.addItem(DVT);
    }//GEN-LAST:event_btnThemDVTActionPerformed

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
            java.util.logging.Logger.getLogger(themsp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(themsp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(themsp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(themsp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new themsp().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancle;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnThemDVT;
    private javax.swing.JButton btnThemSP;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cboDVT;
    private javax.swing.JComboBox<String> cboKLT;
    private javax.swing.JComboBox<String> cboKho;
    private javax.swing.JComboBox<String> cboLSP;
    private com.toedter.calendar.JDateChooser dcsNHH;
    private com.toedter.calendar.JDateChooser dcsNSX;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel jpVisible1;
    private javax.swing.JPanel jpVisible2;
    private com.raven.swing.RoundPanel roundPanel1;
    private com.raven.swing.RoundPanel roundPanel2;
    private com.raven.swing.RoundPanel roundPanel3;
    private com.raven.swing.RoundPanel roundPanel4;
    private com.raven.swing.RoundPanel roundPanel5;
    private com.raven.swing.RoundPanel roundPanel6;
    private com.raven.swing.RoundPanel roundPanel7;
    private com.raven.swing.RoundPanel roundPanel8;
    private javax.swing.JTable tblKho;
    private javax.swing.JTable tblLoaiHang;
    private javax.swing.JTextField txtGia;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenSP;
    // End of variables declaration//GEN-END:variables
}
