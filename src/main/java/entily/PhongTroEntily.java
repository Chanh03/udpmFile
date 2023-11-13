/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entily;

/**
 *
 * @author Trong Phuc
 */
public class PhongTroEntily {

    private int SoThuTu;
    private String ID_Phong;
    private int SoPhong;
    private int Dientich;
    private int SoGiuong;
    private int GiaThue;
    private String TrangThai;

    public PhongTroEntily() {
    }

    public PhongTroEntily(int SoThuTu, String ID_Phong, int SoPhong, int Dientich, int SoGiuong, int GiaThue, String TrangThai) {
        this.SoThuTu = SoThuTu;
        this.ID_Phong = ID_Phong;
        this.SoPhong = SoPhong;
        this.Dientich = Dientich;
        this.SoGiuong = SoGiuong;
        this.GiaThue = GiaThue;
        this.TrangThai = TrangThai;
    }

    public int getSoThuTu() {
        return SoThuTu;
    }

    public void setSoThuTu(int SoThuTu) {
        this.SoThuTu = SoThuTu;
    }

    public String getID_Phong() {
        return ID_Phong;
    }

    public void setID_Phong(String ID_Phong) {
        this.ID_Phong = ID_Phong;
    }

    public int getSoPhong() {
        return SoPhong;
    }

    public void setSoPhong(int SoPhong) {
        this.SoPhong = SoPhong;
    }

    public int getDientich() {
        return Dientich;
    }

    public void setDientich(int Dientich) {
        this.Dientich = Dientich;
    }

    public int getSoGiuong() {
        return SoGiuong;
    }

    public void setSoGiuong(int SoGiuong) {
        this.SoGiuong = SoGiuong;
    }

    public int getGiaThue() {
        return GiaThue;
    }

    public void setGiaThue(int GiaThue) {
        this.GiaThue = GiaThue;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }

}
