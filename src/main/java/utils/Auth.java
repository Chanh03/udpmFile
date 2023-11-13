/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;


import entily.NguoiDungEntily;

/**
 *
 * @author Trong Phuc
 */
public class Auth {
    //đối tượng chứa thông tin cho người dùng đăng nhập vào
    public static NguoiDungEntily user = null;
    //xóa thông tin
    public static void clear() {
        Auth.user = null;
    }
    //kiểm tra xem có đăng nhập chưa
    public static boolean isLogin() {
        return Auth.user != null;
    }
    
    public static boolean isManager() {
        return Auth.isLogin();
    }
}
    