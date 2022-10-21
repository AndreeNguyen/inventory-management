/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.awt.Image;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.ImageIcon;

/**
 *
 * @author My PC
 */


public class XImage {
    public static Image getAppIcon(){
        URL url = XImage.class.getResource("/com/edusys/icon/logo.png");
        return new ImageIcon(url).getImage();
    }
    //luu hinh anh vao file
    public static boolean save(File src){
        File x = new File("src\\com\\edusys\\icon",src.getName());
        if (!x.getParentFile().exists()) {
            x.getParentFile().mkdirs();//Taoj thu muc
        }
        try {
            Path from = Paths.get(src.getAbsolutePath());
            Path to = Paths.get(src.getAbsolutePath());
            Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    //Hien thi hinh anh len
    public static ImageIcon read(String fileName){
        File path = new File("src\\com\\edusys\\icon",fileName);
        return new ImageIcon(path.getAbsolutePath());
    }
}
