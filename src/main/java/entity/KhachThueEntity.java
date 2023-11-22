/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.List;

 
public class KhachThueEntity {
    private int SoThuThu;
    private String ID_khach;
    private String Ho;
    private String CCCD;
    private String GioiTinh;
    private String SoDienThoai;
    private String Email;
    private String Ten;
    private String HinhAnh;
    private String DiaChi;

    public KhachThueEntity() {
    }

    public KhachThueEntity(int SoThuThu, String ID_khach, String Ho, String CCCD, String GioiTinh, String SoDienThoai, String Email, String Ten, String HinhAnh, String DiaChi) {
        this.SoThuThu = SoThuThu;
        this.ID_khach = ID_khach;
        this.Ho = Ho;
        this.CCCD = CCCD;
        this.GioiTinh = GioiTinh;
        this.SoDienThoai = SoDienThoai;
        this.Email = Email;
        this.Ten = Ten;
        this.HinhAnh = HinhAnh;
        this.DiaChi = DiaChi;
    }

    public int getSoThuThu() {
        return SoThuThu;
    }

    public void setSoThuThu(int SoThuThu) {
        this.SoThuThu = SoThuThu;
    }

    public String getID_khach() {
        return ID_khach;
    }

    public void setID_khach(String ID_khach) {
        this.ID_khach = ID_khach;
    }

    public String getHo() {
        return Ho;
    }

    public void setHo(String Ho) {
        this.Ho = Ho;
    }

    public String getCCCD() {
        return CCCD;
    }

    public void setCCCD(String CCCD) {
        this.CCCD = CCCD;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public String getSoDienThoai() {
        return SoDienThoai;
    }

    public void setSoDienThoai(String SoDienThoai) {
        this.SoDienThoai = SoDienThoai;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String Ten) {
        this.Ten = Ten;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String HinhAnh) {
        this.HinhAnh = HinhAnh;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    
}