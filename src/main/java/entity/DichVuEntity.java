/*
 * Hello This Is Vanh
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Date;

/**
 *
 * @author Chanh Chanh
 */
public class DichVuEntity {

    private int SoThuTu;
    private String ID_DichVu;
    private String TenDichVu;
    private String DonGia;
    private Date Ngay;
    private Date Nam;
    private String HinhAnh;
    private String TrangThai;
    private String ID_Phong;

    public DichVuEntity() {
    }

    public DichVuEntity(int SoThuTu, String ID_DichVu, String TenDichVu, String DonGia, Date Ngay, Date Nam, String HinhAnh, String TrangThai, String ID_Phong) {
        this.SoThuTu = SoThuTu;
        this.ID_DichVu = ID_DichVu;
        this.TenDichVu = TenDichVu;
        this.DonGia = DonGia;
        this.Ngay = Ngay;
        this.Nam = Nam;
        this.HinhAnh = HinhAnh;
        this.TrangThai = TrangThai;
        this.ID_Phong = ID_Phong;
    }

    public int getSoThuTu() {
        return SoThuTu;
    }

    public void setSoThuTu(int SoThuTu) {
        this.SoThuTu = SoThuTu;
    }

    public String getID_DichVu() {
        return ID_DichVu;
    }

    public void setID_DichVu(String ID_DichVu) {
        this.ID_DichVu = ID_DichVu;
    }

    public String getTenDichVu() {
        return TenDichVu;
    }

    public void setTenDichVu(String TenDichVu) {
        this.TenDichVu = TenDichVu;
    }

    public String getDonGia() {
        return DonGia;
    }

    public void setDonGia(String DonGia) {
        this.DonGia = DonGia;
    }

    public Date getNgay() {
        return Ngay;
    }

    public void setNgay(Date Ngay) {
        this.Ngay = Ngay;
    }

    public Date getNam() {
        return Nam;
    }

    public void setNam(Date Nam) {
        this.Nam = Nam;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String HinhAnh) {
        this.HinhAnh = HinhAnh;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }

    public String getID_Phong() {
        return ID_Phong;
    }

    public void setID_Phong(String ID_Phong) {
        this.ID_Phong = ID_Phong;
    }

}
