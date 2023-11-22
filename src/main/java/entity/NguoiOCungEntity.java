/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author HOANG HIEN
 */
public class NguoiOCungEntity {

    private int SoThuTu;
    private String Ho;
    private String Ten;
    private String ID_NguoiOCung;
    private String Email;
    private String HinhAnh;
    private String SoDienThoai;
    private String GioiTinh;
    private String CCCD;
    private String DiaChi;
    private String ID_Khach;

    public NguoiOCungEntity(int SoThuTu, String Ho, String Ten, String ID_NguoiOCung, String Email, String HinhAnh, String SoDienThoai, String GioiTinh, String CCCD, String DiaChi, String ID_Khach) {
        this.SoThuTu = SoThuTu;
        this.Ho = Ho;
        this.Ten = Ten;
        this.ID_NguoiOCung = ID_NguoiOCung;
        this.Email = Email;
        this.HinhAnh = HinhAnh;
        this.SoDienThoai = SoDienThoai;
        this.GioiTinh = GioiTinh;
        this.CCCD = CCCD;
        this.DiaChi = DiaChi;
        this.ID_Khach = ID_Khach;
    }

    public NguoiOCungEntity() {
    }
    

    public int getSoThuTu() {
        return SoThuTu;
    }

    public void setSoThuTu(int SoThuTu) {
        this.SoThuTu = SoThuTu;
    }

    public String getHo() {
        return Ho;
    }

    public void setHo(String Ho) {
        this.Ho = Ho;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String Ten) {
        this.Ten = Ten;
    }

    public String getID_NguoiOCung() {
        return ID_NguoiOCung;
    }

    public void setID_NguoiOCung(String ID_NguoiOCung) {
        this.ID_NguoiOCung = ID_NguoiOCung;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String HinhAnh) {
        this.HinhAnh = HinhAnh;
    }

    public String getSoDienThoai() {
        return SoDienThoai;
    }

    public void setSoDienThoai(String SoDienThoai) {
        this.SoDienThoai = SoDienThoai;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public String getCCCD() {
        return CCCD;
    }

    public void setCCCD(String CCCD) {
        this.CCCD = CCCD;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public String getID_Khach() {
        return ID_Khach;
    }

    public void setID_Khach(String ID_Khach) {
        this.ID_Khach = ID_Khach;
    }

    
}
