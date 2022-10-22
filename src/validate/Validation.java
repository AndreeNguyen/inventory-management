
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validate;

import java.awt.Color;
import javax.swing.JTextField;

/**
 *
 * @author My PC
 */
public class Validation {

    //kiểm tra rỗng
    public static boolean flag = true;

    public static boolean checkEmpty(JTextField txtField, StringBuilder sb, String message) {

        if (txtField.getText().equals("")) {
            sb.append(message);
            txtField.setBackground(Color.yellow);
            flag = false;
        } else {
            txtField.setBackground(Color.white);
        }
        return flag;
    }

    //kiểm tra Code ID
    public static boolean checkCodeID(JTextField txtField, StringBuilder sb) {
        if (!checkEmpty(txtField, sb, "Không được để trống Code ID\n")) {
            flag = false;
        }
        try {
            if (flag) {

            }
        } catch (Exception e) {
            sb.append("ID nhập sai dữ liệu\n");
            flag = false;
            txtField.setBackground(Color.yellow);
        }
        if (flag == true) {
            txtField.setBackground(Color.white);
        }
        return flag = true;
    }

    //Kiểm tra tuổi
    public static boolean checkAge(JTextField txtField, StringBuilder sb) {
        if (!checkEmpty(txtField, sb, "Không được để trống tuổi\n")) {
            flag = false;
        }
        try {
            int age = Integer.parseInt(txtField.getText());
            if (age < 18 || age > 65) {
                sb.append("Tuổi không hợp lệ(18-65 tuổi)\n");
                txtField.setBackground(Color.yellow);
                flag = false;
            }
        } catch (Exception e) {
            sb.append("Tuổi nhập sai dữ liệu(số nguyên)\n");
            flag = false;
            txtField.setBackground(Color.yellow);
        }
        if (flag == true) {
            txtField.setBackground(Color.white);
        }
        return flag = true;
    }

    public static boolean checkSalary(JTextField txtField, StringBuilder sb) {
        if (!checkEmpty(txtField, sb, "Không được để trống lương\n")) {
            flag = false;
        }
        try {
            double salary = Double.parseDouble(txtField.getText());
            if (salary < 5000000) {
                sb.append("Lương không hợp lệ (lớn hơn hoặc bằng 5.000.000)\n");
                flag = false;
                txtField.setBackground(Color.yellow);
            }
        } catch (Exception e) {
            sb.append("Lương nhập sai dữ liệu(số thực)\n");
            flag = false;
            txtField.setBackground(Color.yellow);
        }
        if (flag == true) {
            txtField.setBackground(Color.white);
        }
        return flag = true;
    }

    public void checkColor(JTextField txtField) {
        if (flag == false) {
            txtField.setBackground(Color.yellow);
        } else {
            txtField.setBackground(Color.white);
        }
    }

    public static boolean checkEmail(JTextField txtField, StringBuilder sb) {
        if (!checkEmpty(txtField, sb, "Không được để trống Email\n")) {
            flag = false;
        }
        try {
            String regex = ("\\w+@\\w+\\.\\w+");
            if (!txtField.getText().matches(regex)) {
                sb.append("Nhập sai cấu trúc Email\n");
                flag = false;
                txtField.setBackground(Color.yellow);
            }
        } catch (Exception e) {
            sb.append("Nhập sai dữ liệu\n");
            flag = false;
            txtField.setBackground(Color.yellow);
        }
        if (flag == true) {
            txtField.setBackground(Color.white);
        }
        return flag = true;
    }
    
    public static boolean checkScore(JTextField txtField, StringBuilder sb) {
        if (!checkEmpty(txtField, sb, "Không được để trống điểm\n")) {
            flag = false;
        }
        try {
            float score = Float.parseFloat(txtField.getText());

            if (score < 0 && score > 10) {
                sb.append("Điểm không hợp lệ \n");
                flag = false;
                txtField.setBackground(Color.yellow);
            }
        } catch (Exception e) {
            sb.append("Lương nhập sai dữ liệu(số thực)\n");
            flag = false;
            txtField.setBackground(Color.yellow);
        }
        if (flag == true) {
            txtField.setBackground(Color.white);
        }
        return flag = true;
    }
}
