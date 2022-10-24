/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view.Nhanvien;

import com.toedter.calendar.JDateChooser;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.DatabaseHelper;
import entity.product.Nhanvien;

/**
 *
 * @author AQ
 */
public class ChiTietNV extends javax.swing.JFrame {

    //private JDateChooser dateChooser;
    /**
     * Creates new form ChiTietNV
     */
    public ChiTietNV() {
        initComponents();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        radNam.setSelected(true);
        radNV.setSelected(true);
    }
    QuanLyNhanVien qlnv = new QuanLyNhanVien();
    ArrayList<Nhanvien> listnv = QuanLyNhanVien.getList();

    public void my_update(String str) throws ParseException {
        txtManv.setText(str);

        for (Nhanvien nv : listnv) {
            if (txtManv.getText().equalsIgnoreCase(nv.getManv())) {
                txtHoten.setText(nv.getHoten());
                String date = nv.getNgaysinh();
                Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
                dtcNgaysinh.setDate(date2);
                if (nv.getGioitinh().equalsIgnoreCase("nam")) {
                    radNam.setSelected(true);
                } else {
                    radNu.setSelected(true);
                }
                String kho = nv.getMakho();
                System.out.println(kho);
                switch (kho) {
                    case "KHTong":
                        cboKho.setSelectedIndex(0);
                        break;

                    case "KH001":
                        cboKho.setSelectedIndex(1);
                        break;

                    case "KH002":
                        cboKho.setSelectedIndex(2);
                        break;

                    case "KH003":
                        cboKho.setSelectedIndex(3);
                        break;

                    case "KH004":
                        cboKho.setSelectedIndex(4);
                        break;

                    default:
                        throw new AssertionError();
                }
                txtCCCD.setText(nv.getCCCD());
                txtSDT.setText(nv.getSDT());
                txtEmail.setText(nv.getEmail());
                txtDiachi.setText(nv.getDiachi());
                switch (nv.getChucVu()) {
                    case 1:
                        radGD.setSelected(true);
                        break;
                        
                    case 2:
                        radTP.setSelected(true);
                        break;
                        
                    case 3:
                        radNV.setSelected(true);
                        break;
                    default:
                        throw new AssertionError();
                }
            }
        }
    }

    public void Save() {

        try {
            Connection con = DatabaseHelper.oppenConnection();
            String query2 = "INSERT INTO NhanVien (Manv, HoTen, NgaySinh, GioiTinh, MaKho, CCCD, SDT, Email, Diachi, anh, ChucVu)\n"
                    + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement ps2 = con.prepareStatement(query2);
            ps2.setString(1, txtManv.getText().trim());
            ps2.setString(2, txtHoten.getText().trim());
            ps2.setString(3, datecovert(dtcNgaysinh.getDate()));
            Boolean gt;
            if (radNam.isSelected()) {
                gt = true;
            } else {
                gt = false;
            }
            ps2.setBoolean(4, gt);
            String mk = "";
            int khosl = cboKho.getSelectedIndex();
            switch (khosl) {
                case 0:
                    mk = "KH000";
                    break;

                case 1:
                    mk = "KH001";
                    break;

                case 2:
                    mk = "KH002";
                    break;

                case 3:
                    mk = "KH003";
                    break;

                case 4:
                    mk = "KH004";
                    break;
                default:
                    throw new AssertionError();
            }
            ps2.setString(5, mk);
            ps2.setString(6, txtCCCD.getText());
            ps2.setString(7, txtSDT.getText());
            ps2.setString(8, txtEmail.getText());
            ps2.setString(9, txtDiachi.getText());
            ps2.setString(10, "");
            boolean cv;
            if(radTP.isSelected()){
                ps2.setBoolean(11, true);
            }else{
                 ps2.setBoolean(11, false);
            }
            ps2.executeUpdate();
            JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công ");
            qlnv.nhanvienloaddata();

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public String datecovert(Date dt) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String date = df.format(dt);
        return date;
    }

    public void Update() {
        //if (checkNull() == true) {
            try {

                Connection con = DatabaseHelper.oppenConnection();

                String query2 = "UPDATE NhanVien SET "
                        + "HoTen = ?, NgaySinh = ?, GioiTinh = ?, MaKho = ?, CCCD = ?, SDT = ?, Email = ?, Diachi = ?, anh = ?, ChucVu = ? WHERE MaNV = ?";

                PreparedStatement ps2 = con.prepareStatement(query2);

                
                ps2.setString(1, txtHoten.getText().trim());
                ps2.setString(2, datecovert(dtcNgaysinh.getDate()));
                Boolean gt;
                if (radNam.isSelected()) {
                    gt = true;
                } else {
                    gt = false;
                }
                ps2.setBoolean(3, gt);
                String mk = "";
                int khosl = cboKho.getSelectedIndex();
                switch (khosl) {
                    case 0:
                        mk = "KH000";
                        break;

                    case 1:
                        mk = "KH001";
                        break;

                    case 2:
                        mk = "KH002";
                        break;

                    case 3:
                        mk = "KH003";
                        break;

                    case 4:
                        mk = "KH004";
                        break;
                    default:
                        throw new AssertionError();
                }
                ps2.setString(4, mk);
                ps2.setString(5, txtCCCD.getText());
                ps2.setString(6, txtSDT.getText());
                ps2.setString(7, txtEmail.getText());
                ps2.setString(8, txtDiachi.getText());
                ps2.setString(9, "");
                if(radTP.isSelected()){
                ps2.setBoolean(10, true);
            }else{
                 ps2.setBoolean(10, false);
            }
                ps2.setString(11, txtManv.getText().trim());
                ps2.executeUpdate();
                JOptionPane.showMessageDialog(this, "Cập Nhật nhân viên thành công");
                qlnv.nhanvienloaddata();
            } catch (Exception e) {
                System.out.println(e);
            }
       // }

    }

    public void Detele() {
        int ret = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn xóa nhân viên này không ?");
        if (ret == JOptionPane.YES_OPTION) {
            try {

                Connection con = DatabaseHelper.oppenConnection();

                String query2 = "DELETE FROM NhanVien WHERE MaNV = ?";

                PreparedStatement ps2 = con.prepareStatement(query2);
                ps2.setString(1, txtManv.getText().trim());
                ps2.executeUpdate();
                JOptionPane.showMessageDialog(this, "Đã xóa nhân viên");
                qlnv.nhanvienloaddata();
            } catch (Exception e) {
                System.out.println(e);
                //JOptionPane.showMessageDialog(this, "Sách này đang được mượn, không thể xóa", "Error", JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    public boolean Validate() {
        if (txtManv.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã Nhân viên", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if(txtHoten.getText().trim().length() == 0){
            JOptionPane.showMessageDialog(this, "Vui lòng nhập họ tên Nhân viên", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if((dtcNgaysinh.getDate() == null )){
            JOptionPane.showMessageDialog(this, "Vui lòng kiểm tra lại ngày sinh", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if(txtCCCD.getText().trim().length() == 0 || txtCCCD.getText().trim().length() > 10){
            JOptionPane.showMessageDialog(this, "CCCD không được để trống hoặc quá 10 số", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }else{
            try {
                Long.parseLong(txtCCCD.getText());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "CCCD phải là số, không có ký tự", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        
        if(txtSDT.getText().trim().length() == 0 || txtSDT.getText().trim().length() > 10){
            JOptionPane.showMessageDialog(this, "SĐT không được để trống hoặc quá 10 số", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }else{
            try {
                Long.parseLong(txtSDT.getText());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "SĐT phải là số, không có ký tự", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
       
        if(txtEmail.getText().trim().length() == 0){
            JOptionPane.showMessageDialog(this, "Vui lòng nhập Email", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if(txtDiachi.getText().trim().length() == 0){
            JOptionPane.showMessageDialog(this, "Vui lòng nhập Địa chỉ", "Error", JOptionPane.ERROR_MESSAGE);
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

        jTextField1 = new javax.swing.JTextField();
        rgrSex = new javax.swing.ButtonGroup();
        rgrPosition = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        txtManv = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtHoten = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        radNam = new javax.swing.JRadioButton();
        radNu = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        cboKho = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        txtCCCD = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtDiachi = new javax.swing.JTextField();
        btnSave = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();
        btnupdate = new javax.swing.JButton();
        dtcNgaysinh = new com.toedter.calendar.JDateChooser();
        jLabel11 = new javax.swing.JLabel();
        radTP = new javax.swing.JRadioButton();
        radNV = new javax.swing.JRadioButton();
        radGD = new javax.swing.JRadioButton();

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Mã nhân viên :");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Họ tên :");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Ngày Sinh :");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Giới tính :");

        rgrSex.add(radNam);
        radNam.setText("Nam");

        rgrSex.add(radNu);
        radNu.setText("Nữ");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Kho :");

        cboKho.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Kho tổng", "Kho 1", "Kho 2", "Kho 3", "Kho 4" }));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("CCCD :");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("SĐT :");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Email :");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Địa chỉ :");

        btnSave.setText("Lưu");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnRemove.setText("Xóa");
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });

        btnupdate.setText("Câp nhật");
        btnupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnupdateActionPerformed(evt);
            }
        });

        dtcNgaysinh.setDateFormatString("yyyy-MM-dd");

        jLabel11.setText("Chức vụ :");

        rgrPosition.add(radTP);
        radTP.setText("Trưởng Phòng");

        rgrPosition.add(radNV);
        radNV.setText("Nhân Viên");

        rgrPosition.add(radGD);
        radGD.setText("Giám Đốc");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radNam, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radNu, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(139, 139, 139)
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(txtDiachi, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboKho, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(dtcNgaysinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtManv, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtHoten, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel7)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(jLabel8)
                                            .addGap(18, 18, 18)
                                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(radGD, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(radTP, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(radNV, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 48, Short.MAX_VALUE)))))
                .addGap(150, 150, 150))
            .addGroup(layout.createSequentialGroup()
                .addGap(216, 216, 216)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(btnupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(btnRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtManv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtHoten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel7)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel8)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(dtcNgaysinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(radNam)
                    .addComponent(radNu)
                    .addComponent(jLabel9)
                    .addComponent(txtDiachi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cboKho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(radNV)
                    .addComponent(radGD)
                    .addComponent(radTP))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        // TODO add your handling code here:
        if(checkmnv()){
            Detele();
        }else{            
            JOptionPane.showMessageDialog(this, "Mã nhân viên không tồn tại","Error", JOptionPane.ERROR_MESSAGE);           
        }
        
    }//GEN-LAST:event_btnRemoveActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        if(Validate()){
            if(checkmnv()){
                JOptionPane.showMessageDialog(this, "Mã nhân viên đã tồn tại", "Error", JOptionPane.ERROR_MESSAGE);
            }else{
               Save(); 
            }
            
        }
        
    }//GEN-LAST:event_btnSaveActionPerformed
    
    private boolean checkmnv(){
        boolean check = false;
        for (Nhanvien nv : listnv) {
                if(txtManv.getText().trim().equalsIgnoreCase(nv.getManv())){
                    check = true;
                    break;
                }
            }
        return check;
    }
    private void btnupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdateActionPerformed
        // TODO add your handling code here:boolean checkmnv;
        if(Validate()){       
            if(checkmnv()){
                Update();
            }else{
                JOptionPane.showMessageDialog(this, "Mã nhân viên không tồn tại", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        }
        
    }//GEN-LAST:event_btnupdateActionPerformed

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
            java.util.logging.Logger.getLogger(ChiTietNV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChiTietNV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChiTietNV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChiTietNV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChiTietNV().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnupdate;
    private javax.swing.JComboBox<String> cboKho;
    private com.toedter.calendar.JDateChooser dtcNgaysinh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JRadioButton radGD;
    private javax.swing.JRadioButton radNV;
    private javax.swing.JRadioButton radNam;
    private javax.swing.JRadioButton radNu;
    private javax.swing.JRadioButton radTP;
    private javax.swing.ButtonGroup rgrPosition;
    private javax.swing.ButtonGroup rgrSex;
    private javax.swing.JTextField txtCCCD;
    private javax.swing.JTextField txtDiachi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoten;
    private javax.swing.JTextField txtManv;
    private javax.swing.JTextField txtSDT;
    // End of variables declaration//GEN-END:variables
}
