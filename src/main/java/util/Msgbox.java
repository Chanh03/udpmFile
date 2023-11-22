/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.awt.Component;
import javax.swing.JOptionPane;



/**
 *
 * @author Trong Phuc
 */
public class Msgbox {
    //đưa ra thông báo
   public static void alert(Component parent, String message){
       JOptionPane.showMessageDialog(parent, message,
               "Hệ Thống Đào Tạo",JOptionPane.INFORMATION_MESSAGE);
   }
   //cho người dùng xác nhận
   public static boolean confirm(Component parent, String message){
       int result = JOptionPane.showConfirmDialog(parent, message,
               "Hệ Thống Đào Tạo", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
       return result == JOptionPane.YES_OPTION;
   }
   //yêu cầu nhập dữ liệu
   public static String prompt(Component parent, String message){
       return JOptionPane.showInputDialog(parent, message,
               "Hệ Thống Đào Tạo", JOptionPane.INFORMATION_MESSAGE);
   }
}
