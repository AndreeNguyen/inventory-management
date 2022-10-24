/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view.NhapXuat;

import model.DatabaseHelper;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.time.*;
import javax.print.attribute.standard.PDLOverrideSupported;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import entity.product.HangHoaKhoTong;

/**
 *
 * @author ADMIN
 */
public class DATHANG extends javax.swing.JFrame {

    /**
     * Creates new form DATHANG
     */
    public DATHANG() {
        initComponents();
        loaddatahhkt();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        tblhhkt.isEditing();
    }

    private static ArrayList<HangHoaKhoTong> listhhkt = new ArrayList<>();

    public static ArrayList<HangHoaKhoTong> getlist() {
        return listhhkt;
    }

    DefaultTableModel tblmodelhhkt;
    DefaultTableModel tblmodelspdat;

    boolean check = true;

    //Load dữ liệu hàng hóa trong kho tổng
    public void loaddatahhkt() {
        listhhkt.clear();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DatabaseHelper.oppenConnection();
            String query = "select KhoHang"
                    + ".MaKho, LoaiHang.MaLoai, TenLoai, SanPham.MaSP, TenSP, SoLuong\n"
                    + "from KhoHang join SoLuongSP on KhoHang.MaKho = SoLuongSP.MaKho \n"
                    + "join SanPham on SoLuongSP.MaSP = SanPham.MaSP\n"
                    + "	join LoaiHang on SanPham.MaLoai = LoaiHang.MaLoai\n"
                    + "Where KhoHang.MaKho = 'KHTong'";

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(query);

//            PreparedStatement ps1 = con.prepareStatement(query);
//            ResultSet rs2 = ps1.executeQuery(query);
            while (rs.next()) {
                HangHoaKhoTong hh = new HangHoaKhoTong();
                hh.setMaKho(rs.getString(1));
                hh.setMaloai(rs.getString(2));
                hh.setTenLoai(rs.getString(3));
                hh.setMaSP(rs.getString(4));
                hh.setTenSP(rs.getString(5));
                hh.setSoLuong(rs.getInt(6));
                listhhkt.add(hh);
            }
            tblmodelhhkt = (DefaultTableModel) tblhhkt.getModel();
            tblmodelhhkt.setRowCount(0);
            Object row[];
            for (HangHoaKhoTong hh : listhhkt) {
                row = new Object[]{hh.getTenLoai(), hh.getTenSP(), hh.getSoLuong()};
                tblmodelhhkt.addRow(row);
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Chức năng đặt hàng
    public void dathang() {
        //updateSoluong();
        tblmodelhhkt = (DefaultTableModel) tblhhkt.getModel();
        int row = tblhhkt.getSelectedRow();
        int Slht = Integer.parseInt(tblmodelhhkt.getValueAt(row, 2).toString());
        int SoLuong = Integer.parseInt(txtsoluong.getText());
        tblmodelspdat = (DefaultTableModel) tblspdat.getModel();
        if (tblmodelspdat.getRowCount() > 0) {
            for (int i = 0; i < tblmodelspdat.getRowCount(); i++) {
                String MaSP = tblmodelspdat.getValueAt(i, 1).toString();
                if (MaSP.equalsIgnoreCase(txtsanpham.getText())) {
                    int sl = Integer.parseInt(tblmodelspdat.getValueAt(i, 2).toString()) + SoLuong;
                    if (sl > 50) {
                        JOptionPane.showMessageDialog(this, "Mỗi lần đặt một sản phẩm không được quá 50 thùng", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    } else {
                        tblmodelhhkt.setValueAt(Slht - SoLuong, row, 2);
                        tblmodelspdat.setValueAt(sl, i, 2);
                    }
                    return;
                }
            }
        }
        tblmodelhhkt.setValueAt(Slht - SoLuong, row, 2);
        tblmodelspdat.addRow(new Object[]{txtloaihang.getText(), txtsanpham.getText(), SoLuong});
    }

    // xóa hàng đang đặt
    public void delete() {

        tblmodelspdat = (DefaultTableModel) tblspdat.getModel();
        if (tblspdat.getSelectedRowCount() == 1) {
            int row = tblspdat.getSelectedRow();
            String TenSP = tblspdat.getValueAt(row, 1).toString();
            int sld = Integer.parseInt(tblspdat.getValueAt(row, 2).toString());
            for (int i = 0; i < tblhhkt.getRowCount(); i++) {
                if (tblhhkt.getValueAt(i, 1).toString().equalsIgnoreCase(TenSP)) {
                    int soluongkt = Integer.parseInt(tblhhkt.getValueAt(i, 2).toString()) + sld;
                    tblhhkt.setValueAt(soluongkt, i, 2);
                    break;
                }
            }
            tblmodelspdat.removeRow(row);
        } else {
            if (tblspdat.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "Không có sản phẩm nào được đặt");
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm cần xóa");
            }
        }

    }

    // xác nhận hàng đặt và chuyển form phiếu xuất chi tiết
    public void Xacnhan() {
        phieudat pd = new phieudat();
        PhieuXuat px = new PhieuXuat();
        PhieuNhap pn = new PhieuNhap();
        tblmodelspdat = (DefaultTableModel) tblspdat.getModel();
        if (tblmodelspdat.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Không có sản phẩm", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } else {
            // Lấy mã tự tăng của ba phiếu
            String key = pd.keyidentity();
            String keypx = px.keyidentityPX();
            String keypn = pn.keyidentityPN();
            String MaLoai = "", MaSP = "";

            int SoLuong = 0;
            try {

                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection con = DatabaseHelper.oppenConnection();

                String querypd = "Insert into PhieuDat Values(?, ?, ?, ?, ?, ?)";
                PreparedStatement pstpd = con.prepareStatement(querypd);
                pstpd.setString(1, key);
                pstpd.setString(2, LocalDate.now() + "");
                pstpd.setBoolean(3, false);
                pstpd.setString(4, "KH001");
                pstpd.setString(5, "NV001");
                pstpd.setBoolean(6, false);
                pstpd.execute();

                String querypx = "Insert into PhieuXuat Values(?, ?, ?, ?, ?, ?)";
                PreparedStatement pstpx = con.prepareStatement(querypx);
                pstpx.setString(1, keypx);
                pstpx.setString(2, "");
                pstpx.setInt(3, 0);
                pstpx.setString(4, "KH001");
                pstpx.setString(5, "");
                pstpx.setBoolean(6, false);
                pstpx.execute();

                String querypn = "Insert into PhieuNhap Values(?, ?, ?, ?, ?, ?)";
                PreparedStatement pstpn = con.prepareStatement(querypn);
                pstpn.setString(1, keypn);
                pstpn.setString(2, "");
                pstpn.setInt(3, 0);
                pstpn.setString(4, "KH001");
                pstpn.setString(5, "");
                pstpn.setBoolean(6, false);
                pstpn.execute();

                //Chuyển từ tên sang mã 
                for (int i = 0; i < tblmodelspdat.getRowCount(); i++) {
                    for (HangHoaKhoTong hh : listhhkt) {
                        if (tblmodelspdat.getValueAt(i, 0).toString().equalsIgnoreCase(hh.getTenLoai())) {
                            MaLoai = hh.getMaloai();
                        }
                        if (tblmodelspdat.getValueAt(i, 1).toString().equalsIgnoreCase(hh.getTenSP())) {
                            MaSP = hh.getMaSP();
                        }
                    }

                    SoLuong = Integer.parseInt(tblmodelspdat.getValueAt(i, 2).toString());
                    String query = "insert into SanPhamDat values(?, ?, ? , ?, ?, ?, ?)";
                    //System.out.println(SoLuong);
                    PreparedStatement pst = con.prepareStatement(query);
                    pst.setString(1, MaLoai);
                    pst.setString(2, MaSP);
                    pst.setInt(3, SoLuong);
                    pst.setString(4, key);
                    pst.setString(5, keypx);
                    pst.setString(6, keypn);
                    pst.setBoolean(7, false);
                    pst.execute();
                    //System.out.println("10");
                }
                JOptionPane.showConfirmDialog(this, "Đã thêm vào phiếu đặt, vui lòng kiểm tra");
                check = false;
                con.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        pd.loaddataSPD();
        pd.loaddatapd();
        pd.filltotablPD();
        pd.setVisible(true);
    }

    //chuyển từ kiểu Date sang kiểu String
    public String datecovert(Date dt) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String date = df.format(dt);
        return date;
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
        jLabel1 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblhhkt = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblspdat = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtloaihang = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtsanpham = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtsoluong = new javax.swing.JTextField();
        btndat = new javax.swing.JButton();
        btnxoa = new javax.swing.JButton();
        btnxacnhan = new javax.swing.JButton();
        btnhuy = new javax.swing.JButton();
        btnResert = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        mnbQLKH = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        jmnDatHang = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jmnPhieuDat = new javax.swing.JMenuItem();
        jmnPhieuXuat = new javax.swing.JMenuItem();
        jmnPhieuNhap = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jmnQLNV = new javax.swing.JMenuItem();
        jmnChamCong = new javax.swing.JMenuItem();
        jmnLuong = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        jMenu9 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ĐẶT HÀNG");

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setText("ĐẶT HÀNG");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(90, 90, 90))
        );

        tblhhkt.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Loại Hàng", "Sản Phẩm", "Số Lượng"
            }
        )
        {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        }
    );
    tblhhkt.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            tblhhktMouseClicked(evt);
        }
    });
    jScrollPane1.setViewportView(tblhhkt);

    tblspdat.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {
            "Loại Hàng ", "Sản Phảm", "Số Lượng"
        }
    ));
    tblspdat.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            tblspdatMouseClicked(evt);
        }
    });
    jScrollPane2.setViewportView(tblspdat);

    jLabel3.setText("Loại Hàng");

    txtloaihang.setEditable(false);

    jLabel4.setText("Sản Phẩm");

    txtsanpham.setEditable(false);

    jLabel5.setText("Số Lượng");

    btndat.setText("Đặt");
    btndat.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btndatActionPerformed(evt);
        }
    });

    btnxoa.setText("Xóa");
    btnxoa.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnxoaActionPerformed(evt);
        }
    });

    btnxacnhan.setText("Xác Nhận");
    btnxacnhan.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnxacnhanActionPerformed(evt);
        }
    });

    btnhuy.setText("Hủy");

    btnResert.setText("Làm Mới");
    btnResert.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnResertActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel2Layout.createSequentialGroup()
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(14, 14, 14)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(20, 20, 20))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtloaihang, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                        .addComponent(txtsanpham)
                        .addComponent(txtsoluong)))
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(38, 38, 38)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnxoa, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btndat, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 95, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnxacnhan, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                        .addComponent(btnhuy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(117, 117, 117)
                    .addComponent(btnResert, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addContainerGap(35, Short.MAX_VALUE))
    );
    jPanel2Layout.setVerticalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel2Layout.createSequentialGroup()
            .addGap(27, 27, 27)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel3)
                .addComponent(txtloaihang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(43, 43, 43)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txtsanpham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel4))
            .addGap(48, 48, 48)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txtsoluong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel5))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btndat, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnxacnhan, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(27, 27, 27)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnxoa, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnhuy, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(18, 18, 18)
            .addComponent(btnResert, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(14, 14, 14))
    );

    jLabel2.setText("Tìm Kiếm :");

    jMenu3.setText("Tổng Quan");
    mnbQLKH.add(jMenu3);

    jMenu4.setText("Danh Mục Sản Phẩm");
    mnbQLKH.add(jMenu4);

    jMenu5.setText("Nhập, Xuất");

    jmnDatHang.setText("Đặt Hàng");
    jmnDatHang.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jmnDatHangActionPerformed(evt);
        }
    });
    jMenu5.add(jmnDatHang);
    jMenu5.add(jSeparator1);

    jmnPhieuDat.setText("Phiếu Đặt");
    jmnPhieuDat.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jmnPhieuDatActionPerformed(evt);
        }
    });
    jMenu5.add(jmnPhieuDat);

    jmnPhieuXuat.setText("Phiếu Xuất");
    jmnPhieuXuat.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jmnPhieuXuatActionPerformed(evt);
        }
    });
    jMenu5.add(jmnPhieuXuat);

    jmnPhieuNhap.setText("Phiếu Nhập");
    jmnPhieuNhap.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jmnPhieuNhapActionPerformed(evt);
        }
    });
    jMenu5.add(jmnPhieuNhap);

    mnbQLKH.add(jMenu5);

    jMenu6.setText("Nhân Viên");

    jmnQLNV.setText("Quản Lý NV");
    jMenu6.add(jmnQLNV);

    jmnChamCong.setText("Chấm Công");
    jMenu6.add(jmnChamCong);

    jmnLuong.setText("Lương");
    jmnLuong.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jmnLuongActionPerformed(evt);
        }
    });
    jMenu6.add(jmnLuong);

    mnbQLKH.add(jMenu6);

    jMenu7.setText("Nhà Cung Cấp");
    mnbQLKH.add(jMenu7);

    jMenu9.setText("Thống Kê");
    mnbQLKH.add(jMenu9);

    setJMenuBar(mnbQLKH);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addGroup(layout.createSequentialGroup()
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGap(18, 18, 18)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap())
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addGap(0, 0, Short.MAX_VALUE)
            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(324, 324, 324))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel2))
            .addGap(27, 27, 27)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btndatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndatActionPerformed
        // TODO add your handling code here:
        if (check == false) {
            JOptionPane.showMessageDialog(this, "Vui làm mới để sử dụng chức năng");
            return;
        } else {
            if (Validate()) {
                dathang();
            }
        }

    }//GEN-LAST:event_btndatActionPerformed

    private void btnxoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoaActionPerformed
        // TODO add your handling code here:
        delete();

    }//GEN-LAST:event_btnxoaActionPerformed

    private void tblspdatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblspdatMouseClicked
        // TODO add your handling code here:int row = tblQuanlY.getSelectedRow();

    }//GEN-LAST:event_tblspdatMouseClicked

    private void btnxacnhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxacnhanActionPerformed
        // TODO add your handling code here:
        if (check == false) {
            JOptionPane.showMessageDialog(this, "Vui lòng làm mới để sử dụng chức năng");
        } else {
            Xacnhan();
            tblmodelspdat = (DefaultTableModel) tblspdat.getModel();
            tblmodelspdat.setRowCount(0);
        }

    }//GEN-LAST:event_btnxacnhanActionPerformed

    private void btnResertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResertActionPerformed
        // TODO add your handling code here:
        loaddatahhkt();
        txtloaihang.setText("");
        txtsanpham.setText("");
        txtsoluong.setText("");
        tblmodelspdat = (DefaultTableModel) tblspdat.getModel();
        tblmodelspdat.setRowCount(0);
        check = true;
    }//GEN-LAST:event_btnResertActionPerformed

    //Lấy sản phẩm truyền vào textfiled
    private void tblhhktMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblhhktMouseClicked
        // TODO add your handling code here:
         if (check == false) {
            JOptionPane.showMessageDialog(this, "Vui lòng làm mới lại dữ liệu để sử dụng chức năng");
            return;
        } else {
            int row = tblhhkt.getSelectedRow();
            int column = 2;
            for (int i = 0; i < column; i++) {
                //System.out.println(tblhhkt.getValueAt(row, i));
                txtloaihang.setText(tblhhkt.getValueAt(row, 0) + "");
                txtsanpham.setText(tblhhkt.getValueAt(row, 1) + "");
            }
        }
    }//GEN-LAST:event_tblhhktMouseClicked

    private void jmnPhieuNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmnPhieuNhapActionPerformed
        // TODO add your handling code here:
        PhieuNhap formpn = new PhieuNhap();
        formpn.setVisible(true);
    }//GEN-LAST:event_jmnPhieuNhapActionPerformed

    private void jmnLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmnLuongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jmnLuongActionPerformed

    private void jmnDatHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmnDatHangActionPerformed
        // TODO add your handling code here:
        DATHANG formdh = new DATHANG();
        formdh.setVisible(true);
    }//GEN-LAST:event_jmnDatHangActionPerformed

    private void jmnPhieuDatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmnPhieuDatActionPerformed
        // TODO add your handling code here:
        phieudat formpd = new phieudat();
        formpd.setVisible(true);
    }//GEN-LAST:event_jmnPhieuDatActionPerformed

    private void jmnPhieuXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmnPhieuXuatActionPerformed
        // TODO add your handling code here:
        PhieuXuat formpx = new PhieuXuat();
        formpx.setVisible(true);
    }//GEN-LAST:event_jmnPhieuXuatActionPerformed

    public boolean Validate() {
        if (txtloaihang.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn loại sản phẩm", "Erorr", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (txtsanpham.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (txtsoluong.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng điền số lượng cần đặt", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            try {
                Integer.parseInt(txtsoluong.getText());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Số lượng nhập vào là số nguyên", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            if (Integer.parseInt(txtsoluong.getText()) > 50) {
                JOptionPane.showMessageDialog(this, "Mỗi lần đặt chỉ tối đa 50 thùng", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }

        return true;
    }

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
            java.util.logging.Logger.getLogger(DATHANG.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DATHANG.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DATHANG.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DATHANG.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DATHANG().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnResert;
    private javax.swing.JButton btndat;
    private javax.swing.JButton btnhuy;
    private javax.swing.JButton btnxacnhan;
    private javax.swing.JButton btnxoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JMenuItem jmnChamCong;
    private javax.swing.JMenuItem jmnDatHang;
    private javax.swing.JMenuItem jmnLuong;
    private javax.swing.JMenuItem jmnPhieuDat;
    private javax.swing.JMenuItem jmnPhieuNhap;
    private javax.swing.JMenuItem jmnPhieuXuat;
    private javax.swing.JMenuItem jmnQLNV;
    private javax.swing.JMenuBar mnbQLKH;
    private javax.swing.JTable tblhhkt;
    private javax.swing.JTable tblspdat;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextField txtloaihang;
    private javax.swing.JTextField txtsanpham;
    private javax.swing.JTextField txtsoluong;
    // End of variables declaration//GEN-END:variables
}
