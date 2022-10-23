/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author My PC
 */
public class MSGBox {
    //thông báo
    public static void alert(Component parent , String message){
        JOptionPane.showMessageDialog(parent, message, "HỆ THỐNG QUẢN LÍ", JOptionPane.INFORMATION_MESSAGE);
    }
    //yêu cầu xác nhận
    public static boolean confirm(Component parent , String message){
        int result = JOptionPane.showConfirmDialog(parent, message, "HỆ THỐNG QUẢN LÍ",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        return result == JOptionPane.YES_OPTION;
    }
    //yêu cầu nhập
    public static String prompt(Component parent , String message){
        return JOptionPane.showInputDialog(parent, message, "HỆ THỐNG QUẢN LÍ", JOptionPane.INFORMATION_MESSAGE);
    }
}
