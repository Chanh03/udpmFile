/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entily;

/**
 *
 * @author Trong Phuc
 */
public class NguoiDungEntily {
    private String ChucVu;
    private String ID_NguoiDung;
    private String Matkhau;
    private String TenDangNhap;
    private String Ho;
    private String Ten;
    private String Trang_Thai;
    private String Email;
    private String DiaChi;
    private String ID_HopDong;

    public NguoiDungEntily() {
    }

    public NguoiDungEntily(String ChucVu, String ID_NguoiDung, String Matkhau, String TenDangNhap, String Ho, String Ten, String Trang_Thai, String Email, String DiaChi, String ID_HopDong) {
        this.ChucVu = ChucVu;
        this.ID_NguoiDung = ID_NguoiDung;
        this.Matkhau = Matkhau;
        this.TenDangNhap = TenDangNhap;
        this.Ho = Ho;
        this.Ten = Ten;
        this.Trang_Thai = Trang_Thai;
        this.Email = Email;
        this.DiaChi = DiaChi;
        this.ID_HopDong = ID_HopDong;
    }

    public String getChucVu() {
        return ChucVu;
    }

    public void setChucVu(String ChucVu) {
        this.ChucVu = ChucVu;
    }

    public String getID_NguoiDung() {
        return ID_NguoiDung;
    }

    public void setID_NguoiDung(String ID_NguoiDung) {
        this.ID_NguoiDung = ID_NguoiDung;
    }

    public String getMatkhau() {
        return Matkhau;
    }

    public void setMatkhau(String Matkhau) {
        this.Matkhau = Matkhau;
    }

    public String getTenDangNhap() {
        return TenDangNhap;
    }

    public void setTenDangNhap(String TenDangNhap) {
        this.TenDangNhap = TenDangNhap;
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

    public String getTrang_Thai() {
        return Trang_Thai;
    }

    public void setTrang_Thai(String Trang_Thai) {
        this.Trang_Thai = Trang_Thai;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public String getID_HopDong() {
        return ID_HopDong;
    }

    public void setID_HopDong(String ID_HopDong) {
        this.ID_HopDong = ID_HopDong;
    }
    
    
}
