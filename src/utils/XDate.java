/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author My PC
 */
public class XDate {

    static SimpleDateFormat format = new SimpleDateFormat();

    public static Date toDate(String date, String patern) {
        try {
            format.applyPattern(patern);
            return format.parse(date);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String toString(String date, String patern) {
        format.applyPattern(patern);
        return format.format(date);
    }
    
    public static Date addDays(Date date, long days) {
        date.setTime(date.getTime()+days*24*60*60*1000);
        return date;
    }
}
