/*
 * Hello This Is Vanh
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Chanh Chanh
 */
public class ThanhToanEntity {
    private int SoThuTu;
    private String ID_ThanhToan;
    private String NgayThanhToan;
    private String TienPhong;
    private String PhuongThucThanhToan;
    private String ThangThanhToan;
    private String TienDien;
    private String TienNuoc;
    private String TienDichVu;
    private String ID_HopDong;

    public ThanhToanEntity() {
    }

    public ThanhToanEntity(int SoThuTu, String ID_ThanhToan, String NgayThanhToan, String TienPhong, String PhuongThucThanhToan, String ThangThanhToan, String TienDien, String TienNuoc, String TienDichVu, String ID_HopDong) {
        this.SoThuTu = SoThuTu;
        this.ID_ThanhToan = ID_ThanhToan;
        this.NgayThanhToan = NgayThanhToan;
        this.TienPhong = TienPhong;
        this.PhuongThucThanhToan = PhuongThucThanhToan;
        this.ThangThanhToan = ThangThanhToan;
        this.TienDien = TienDien;
        this.TienNuoc = TienNuoc;
        this.TienDichVu = TienDichVu;
        this.ID_HopDong = ID_HopDong;
    }

    public int getSoThuTu() {
        return SoThuTu;
    }

    public void setSoThuTu(int SoThuTu) {
        this.SoThuTu = SoThuTu;
    }

    public String getID_ThanhToan() {
        return ID_ThanhToan;
    }

    public void setID_ThanhToan(String ID_ThanhToan) {
        this.ID_ThanhToan = ID_ThanhToan;
    }

    public String getNgayThanhToan() {
        return NgayThanhToan;
    }

    public void setNgayThanhToan(String NgayThanhToan) {
        this.NgayThanhToan = NgayThanhToan;
    }

    public String getTienPhong() {
        return TienPhong;
    }

    public void setTienPhong(String TienPhong) {
        this.TienPhong = TienPhong;
    }

    public String getPhuongThucThanhToan() {
        return PhuongThucThanhToan;
    }

    public void setPhuongThucThanhToan(String PhuongThucThanhToan) {
        this.PhuongThucThanhToan = PhuongThucThanhToan;
    }

    public String getThangThanhToan() {
        return ThangThanhToan;
    }

    public void setThangThanhToan(String ThangThanhToan) {
        this.ThangThanhToan = ThangThanhToan;
    }

    public String getTienDien() {
        return TienDien;
    }

    public void setTienDien(String TienDien) {
        this.TienDien = TienDien;
    }

    public String getTienNuoc() {
        return TienNuoc;
    }

    public void setTienNuoc(String TienNuoc) {
        this.TienNuoc = TienNuoc;
    }

    public String getTienDichVu() {
        return TienDichVu;
    }

    public void setTienDichVu(String TienDichVu) {
        this.TienDichVu = TienDichVu;
    }

    public String getID_HopDong() {
        return ID_HopDong;
    }

    public void setID_HopDong(String ID_HopDong) {
        this.ID_HopDong = ID_HopDong;
    }
    
}
