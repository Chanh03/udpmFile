/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ui;

import com.formdev.flatlaf.themes.FlatMacLightLaf;
import dao.DichVuDAO;
import dao.HopDongDAO;
import dao.KhachThueDAO;
import dao.NguoiDungDAO;
import dao.NguoiOCungDAO;
import dao.PhongTroDAO;
import dao.ThanhToanDAO;
import entity.DichVuEntity;
import entity.HopDongEntity;
import entity.KhachThueEntity;
import entity.NguoiDungEntity;
import entity.NguoiOCungEntity;
import entity.PhongTroEntity;
import entity.ThanhToanEntity;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import util.Auth;
import util.Msgbox;
import util.XImage;

/**
 *
 * @author Chanh Chanh
 */
public class PhongTroJFrame extends javax.swing.JFrame {

    PhongTroDAO phongTroDAO = new PhongTroDAO();
    NguoiDungDAO nguoiDungDAO = new NguoiDungDAO();
    NguoiOCungDAO nguoiOCungDAO = new NguoiOCungDAO();
    DichVuDAO dichVuDAO = new DichVuDAO();
    KhachThueDAO khachThueDAO = new KhachThueDAO();
    ThanhToanDAO thanhToanDAO = new ThanhToanDAO();
    HopDongDAO hopDongDAO = new HopDongDAO();

    /**
     * Creates new form PhongTroJFrame
     */
    public PhongTroJFrame() {
        initComponents();
        setupTables();
        init();
        startClock();
    }

    void init() {
        setTitle("Trang Chủ");
        setLocationRelativeTo(null);
        filldataPhong();
        fillTableNguoiDung();
        fillTableHopDong();
        fillTableNguoiOCung();
        fillTableDichVu();
        filldataKhachThue();
        fillTableThanhToan();
        setIconImage(XImage.getAppIcon());
    }

    void hienDangNhapJFrame() {
        DangNhapJFrame dangNhapJFrame = new DangNhapJFrame();
        dangNhapJFrame.setVisible(true);
    }

    void filldataPhong() {
        DefaultTableModel model = (DefaultTableModel) tblPhong.getModel();
        model.setRowCount(0);
        try {
            List<PhongTroEntity> list = phongTroDAO.selectAll();
            for (PhongTroEntity pt : list) {
                Object[] row = {
                    pt.getSoThuTu(),
                    pt.getID_Phong(),
                    pt.getSoPhong(),
                    pt.getDientich(),
                    pt.getSoGiuong(),
                    pt.getGiaThue(),
                    pt.getTrangThai()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
        }
    }

    public void fillTableNguoiDung() {
        DefaultTableModel model = (DefaultTableModel) tblNguoiDung.getModel();
        model.setRowCount(0);

        try {
            List<NguoiDungEntity> list = nguoiDungDAO.selectAll();
            for (NguoiDungEntity nd : list) {
                String matKhau = nd.getMatkhau();
                String hiddenPassword = "*".repeat(matKhau.length());

                Object[] row = {
                    nd.getSoThuTu(),
                    nd.getID_NguoiDung(),
                    nd.getTenDangNhap(),
                    hiddenPassword,
                    nd.getChucVu(),
                    nd.getHo(),
                    nd.getTen(),
                    nd.getTrang_Thai(),
                    nd.getEmail(),
                    nd.getDiaChi(),
                    nd.getID_HopDong(),};
                model.addRow(row);
            }
        } catch (Exception e) {
            Msgbox.alert(this, "Loi Truy Van Nguoi Dung");
        }
    }

    void fillTableNguoiOCung() {
        DefaultTableModel model = (DefaultTableModel) tblNguoiOCung.getModel();
        model.setRowCount(0);

        try {
            List<NguoiOCungEntity> list = nguoiOCungDAO.selectAll();
            for (NguoiOCungEntity noc : list) {
                Object[] row = {
                    noc.getSoThuTu(),
                    noc.getID_NguoiOCung(),
                    noc.getTen(),
                    noc.getHo(),
                    noc.getEmail(),
                    noc.getDiaChi(),
                    noc.getSoDienThoai(),
                    noc.getGioiTinh(),
                    noc.getCCCD(),
                    noc.getHinhAnh(),
                    noc.getID_Khach(),};
                model.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace(); // In chi tiết lỗi ra console
            Msgbox.alert(this, "Có lỗi xảy ra: " + e.getMessage());
        }
    }

    void fillTableDichVu() {
        DefaultTableModel model = (DefaultTableModel) tblDichVu.getModel();
        model.setRowCount(0);
        try {
            List<DichVuEntity> list = dichVuDAO.selectAll();
            for (DichVuEntity dv : list) {
                Object[] row = {
                    dv.getSoThuTu(),
                    dv.getID_DichVu(),
                    dv.getTenDichVu(),
                    dv.getDonGia(),
                    dv.getNgay(),
                    dv.getNam(),
                    dv.getHinhAnh(),
                    dv.getTrangThai(),
                    dv.getID_Phong(),};
                model.addRow(row);

            }
        } catch (Exception e) {
            e.printStackTrace();
            Msgbox.alert(this, "Loi Truy Van");
        }
    }

    void filldataKhachThue() {
        DefaultTableModel model = (DefaultTableModel) tblKhach.getModel();
        model.setRowCount(0);
        try {
            List<KhachThueEntity> list = khachThueDAO.selectAll();
            for (KhachThueEntity pt : list) {
                Object[] row = {
                    pt.getSoThuThu(),
                    pt.getID_khach(),
                    pt.getHo(),
                    pt.getCCCD(),
                    pt.getDiaChi(),
                    pt.getEmail(),
                    pt.getSoDienThoai(),
                    pt.getTen(),
                    pt.getHinhAnh(),
                    pt.getGioiTinh()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
        }
    }

    void fillTableThanhToan() {
        DefaultTableModel model = (DefaultTableModel) tblThanhToan.getModel();
        model.setRowCount(0);
        try {
            List<ThanhToanEntity> list = thanhToanDAO.selectAll();
            for (ThanhToanEntity tt : list) {
                Object[] row = {
                    tt.getSoThuTu(),
                    tt.getID_ThanhToan(),
                    tt.getNgayThanhToan(),
                    tt.getTienPhong(),
                    tt.getPhuongThucThanhToan(),
                    tt.getThangThanhToan(),
                    tt.getTienDien(),
                    tt.getTienNuoc(),
                    tt.getTienDichVu(),
                    tt.getID_HopDong(),};
                model.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Msgbox.alert(this, "Loi Truy Van Thanh Toan");
        }
    }

    void fillTableHopDong() {
        DefaultTableModel model = (DefaultTableModel) tblHopDong.getModel();
        model.setRowCount(0);
        try {
            List<HopDongEntity> list = hopDongDAO.selectAll();
            for (HopDongEntity hd : list) {
                Object[] row = {
                    hd.getSoThuTu(),
                    hd.getNgayBatDauO(),
                    hd.getNgayKetThucO(),
                    hd.getID_HopDong(),
                    hd.getTienCoc(),
                    hd.getTienThue(),
                    hd.getNgayKyHopDong(),
                    hd.getNgayHetHopDong(),
                    hd.getGhiChu(),
                    hd.getID_Phong(),
                    hd.getID_Khach()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
//            JOptionPane.showMessageDialog(this, e);
            Msgbox.alert(this, "Loi Truy Van Hop Dong");
        }
    }

    void setTenDangNhapLabel(String tenDangNhap) {
        lblNguoiDung.setText(tenDangNhap);
    }

    void setChucVuLabel(String chucVu) {
        lblChucVu.setText("Chức vụ : " + chucVu);
    }

    void phanQuyenNguoiDung() {
        btnNguoiDung.setVisible(false);
    }

    void startClock() {
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateClock();
            }
        });
        timer.start();
    }

    void updateClock() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String formattedDate = sdf.format(new Date());
        lblThoiGian.setText(formattedDate);
    }

    void showUserInfo() {
        // Hiển thị thông tin người dùng tại đây
        if (Auth.user != null) {
            String chucVu = Auth.user.getChucVu();
            String email = Auth.user.getEmail();
            JOptionPane.showMessageDialog(this, "Chức vụ của bạn là: " + chucVu + "\n" + "Email: " + email, "Thông Tin Người Dùng", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Người dùng chưa đăng nhập.", "Thông Báo", JOptionPane.WARNING_MESSAGE);
        }
    }

    void tableDesign(JTable tbl) {
        Color tblBgColor = new Color(1, 152, 122);
        Color tblFgColor = new Color(255, 255, 255);

        tbl.getTableHeader().setBackground(tblBgColor);
        tbl.getTableHeader().setForeground(tblFgColor);
        tbl.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tbl.getTableHeader().setPreferredSize(new Dimension(100, 40));
        tbl.setRowHeight(30);

    }

    void setupTables() {
        tableDesign(tblDichVu);
        tableDesign(tblPhong);
        tableDesign(tblKhach);
        tableDesign(tblHopDong);
        tableDesign(tblThanhToan);
        tableDesign(tblNguoiDung);
        tableDesign(tblNguoiOCung);
    }

    void openFile(String filePath) {
        try {
            // Tạo một đối tượng File từ đường dẫn
            File file = new File(filePath);

            // Kiểm tra xem Desktop được hỗ trợ không
            if (Desktop.isDesktopSupported()) {
                // Lấy đối tượng Desktop
                Desktop desktop = Desktop.getDesktop();

                // Kiểm tra xem tệp tin tồn tại và có thể mở được không
                if (file.exists() && file.isFile()) {
                    // Mở tệp tin
                    desktop.open(file);
                } else {
                    System.out.println("Tệp tin không tồn tại hoặc không thể mở.");
                }
            } else {
                System.out.println("Desktop không được hỗ trợ trên hệ thống này.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void openFileChooser() {
        JFileChooser fileChooser = new JFileChooser();
        // Hiển thị hộp thoại chọn tệp tin
        int result = fileChooser.showOpenDialog(null);

        // Kiểm tra xem người dùng đã chọn tệp tin hay không
        if (result == JFileChooser.APPROVE_OPTION) {
            // Lấy đối tượng File từ tệp tin được chọn
            File selectedFile = fileChooser.getSelectedFile();

            // In đường dẫn đến tệp tin được chọn
            System.out.println("Đường dẫn đến tệp tin: " + selectedFile.getAbsolutePath());

            // Gọi phương thức mở tệp tin
            openFile(selectedFile.getAbsolutePath());
        } else {
            System.out.println("Người dùng đã hủy chọn tệp tin.");
        }
    }

    ////
    void currentTablePhong(int a) {
        tbpQuanLiPhong.setSelectedIndex(1);

        txtIDPhong.setText(String.valueOf(tblPhong.getValueAt(a, 1)));
        txtSoPhong.setText(String.valueOf(tblPhong.getValueAt(a, 2)));
        txtDientich.setText(String.valueOf(tblPhong.getValueAt(a, 3)));
        txtSoGiuong.setText(String.valueOf(tblPhong.getValueAt(a, 4))); // Sửa từ txtSoPhong thành txtSoGiuong
        txtGiaThue.setText(String.valueOf(tblPhong.getValueAt(a, 5)));
        txtTrangThaiPhong.setText(String.valueOf(tblPhong.getValueAt(a, 6)));
    }

    void setfromPhong(PhongTroEntity phongTroEntityModel) {
        txtIDPhong.setText(phongTroEntityModel.getID_Phong());
        txtSoPhong.setText(String.valueOf(phongTroEntityModel.getSoPhong()));
        txtDientich.setText(String.valueOf(phongTroEntityModel.getDientich()));
        txtSoGiuong.setText(String.valueOf(phongTroEntityModel.getSoGiuong()));
        txtGiaThue.setText(String.valueOf(phongTroEntityModel.getGiaThue()));
        txtTrangThaiPhong.setText(phongTroEntityModel.getTrangThai());
    }

    PhongTroEntity getfromPhong() {
        PhongTroEntity pt = new PhongTroEntity();
        pt.setID_Phong((txtIDPhong.getText()));
        pt.setSoPhong(Integer.parseInt(txtSoPhong.getText()));
        pt.setDientich(Integer.parseInt(txtDientich.getText()));
        pt.setSoGiuong(Integer.parseInt(txtSoGiuong.getText()));
        pt.setGiaThue(Integer.parseInt(txtGiaThue.getText()));
        pt.setTrangThai(txtTrangThaiPhong.getText());
        return pt;
    }

    void insertPhong() {
        PhongTroEntity phongTroEntity = getfromPhong();
        try {
            phongTroDAO.insert(phongTroEntity);
            this.filldataPhong();   // fill => load lai du lieu do len bang
            JOptionPane.showMessageDialog(this, "Thêm Thành Công " + phongTroEntity.getID_Phong());
            tbpQuanLiPhong.setSelectedIndex(0);
            tblPhong.getSelectedRow();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Thêm thất bại");
        }
    }

    void updatePhong() {
        PhongTroEntity phongTroEntity = getfromPhong();
        try {
            phongTroDAO.update(phongTroEntity);
            this.filldataPhong();
            JOptionPane.showMessageDialog(this, "Cập nhật thành công " + phongTroEntity.getID_Phong());
            tbpQuanLiPhong.setSelectedIndex(0);
            tblPhong.getSelectedRow();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Cập nhật thất bại");
        }
    }

    void deletePhong() {
        String id_phong = txtIDPhong.getText();
        try {
            phongTroDAO.delete(id_phong);
            this.filldataPhong();
        } catch (Exception e) {
        }
    }

    void currentTableDichVu(int a) {
        tbpQuanLiDichVu.setSelectedIndex(1);

        txtIdDichVu.setText(String.valueOf(tblDichVu.getValueAt(a, 1)));
        txtTenDichVu.setText(String.valueOf(tblDichVu.getValueAt(a, 2)));
        txtDonGia.setText(String.valueOf(tblDichVu.getValueAt(a, 3)));
        txtNgayDichVu.setText(String.valueOf(tblDichVu.getValueAt(a, 4)));
        txtNamDichVu.setText(String.valueOf(tblDichVu.getValueAt(a, 5)));
        txtTrangThaiDichVu.setText(String.valueOf(tblDichVu.getValueAt(a, 7)));
        txtIdPhongDichVu.setText(String.valueOf(tblDichVu.getValueAt(a, 8)));
        lblHinhAnhDichVu.setText(String.valueOf(tblDichVu.getValueAt(a, 6)));
    }

    void setfromDichVu(DichVuEntity dichVuEntityModel) {
        txtIdDichVu.setText(dichVuEntityModel.getID_DichVu());
        txtTenDichVu.setText(String.valueOf(dichVuEntityModel.getTenDichVu()));
        txtDonGia.setText(String.valueOf(dichVuEntityModel.getDonGia()));
        txtNgayDichVu.setText(String.valueOf(dichVuEntityModel.getNgay()));
        txtNamDichVu.setText(String.valueOf(dichVuEntityModel.getNam()));
        txtTrangThaiDichVu.setText(dichVuEntityModel.getTrangThai());
        txtIDPhong.setText(dichVuEntityModel.getID_Phong());
        lblHinhAnhDichVu.setText(dichVuEntityModel.getHinhAnh());
    }

    DichVuEntity getfromDichVu() {
        DichVuEntity dv = new DichVuEntity();
        dv.setID_DichVu(txtIdDichVu.getText());
        dv.setTenDichVu(txtTenDichVu.getText());
        dv.setDonGia(txtDonGia.getText());

        String ngayDichVuStr = txtNgayDichVu.getText();
        SimpleDateFormat ngayDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date ngayDichVu = ngayDateFormat.parse(ngayDichVuStr);
            dv.setNgay(ngayDichVu);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String namDichVuStr = txtNamDichVu.getText();
        SimpleDateFormat namDateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Change this format if needed
        try {
            Date namDichVu = namDateFormat.parse(namDichVuStr);
            dv.setNam(namDichVu); // Set the Date object in the model
        } catch (ParseException e) {
            e.printStackTrace();
        }

        dv.setTrangThai(txtTrangThaiDichVu.getText());
        dv.setID_Phong(txtIdPhongDichVu.getText());
        dv.setHinhAnh(lblHinhAnhDichVu.getText());
        return dv;
    }

    void insertDichVu() {
        DichVuEntity dichVuEntity = getfromDichVu();
        try {
            dichVuDAO.insert(dichVuEntity);
            this.fillTableDichVu();
            JOptionPane.showMessageDialog(this, "Thêm Thành Công " + dichVuEntity.getID_DichVu());
            tbpQuanLiDichVu.setSelectedIndex(0);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Thêm thất bại. Lỗi: " + e.getMessage());
            System.out.println(e);
        }
    }

    void updateDichVu() {
        DichVuEntity dichVuEntity = getfromDichVu();
        try {
            dichVuDAO.update(dichVuEntity);
            this.fillTableDichVu();
            JOptionPane.showMessageDialog(this, "Cập nhật thành công " + dichVuEntity.getID_DichVu());
            tbpQuanLiDichVu.setSelectedIndex(0);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Cập nhật thất bại. Lỗi: " + e.getMessage());
        }
    }

    void deleteDichVu() {
        String id_DichVu = txtIdDichVu.getText();
        try {
            dichVuDAO.delete(id_DichVu);
            this.fillTableDichVu();
            tbpQuanLiDichVu.setSelectedIndex(0);
            tblDichVu.getSelectedRow();
            JOptionPane.showMessageDialog(this, "Xóa thành công");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Xóa thất bại. Lỗi: " + e.getMessage());
        }
    }

    void currentTableKhach(int a) {
        tbpQuanLiKhach.setSelectedIndex(1);

        txtIdKhach.setText(String.valueOf(tblKhach.getValueAt(a, 1)));
        txtHoKhach.setText(String.valueOf(tblKhach.getValueAt(a, 2)));
        txtCCCDKhach.setText(String.valueOf(tblKhach.getValueAt(a, 3)));
        txtDiaChiKhach.setText(String.valueOf(tblKhach.getValueAt(a, 4)));
        txtEmailKhach.setText(String.valueOf(tblKhach.getValueAt(a, 5)));
        txtSDTKhach.setText(String.valueOf(tblKhach.getValueAt(a, 6)));
        txtTenKhach.setText(String.valueOf(tblKhach.getValueAt(a, 7)));
        lblHinhAnhKhach.setText(String.valueOf(tblKhach.getValueAt(a, 8)));
        cboGioiTinhKhach.setSelectedItem(String.valueOf(tblKhach.getValueAt(a, 9)));
    }

    void getfromKhach(KhachThueEntity khachThueModel) {
        txtIdKhach.setText(khachThueModel.getID_khach());
        txtCCCDKhach.setText(khachThueModel.getCCCD());
        txtDiaChiKhach.setText(khachThueModel.getDiaChi());
        if (khachThueModel.getGioiTinh().equalsIgnoreCase("Nam")) {
            cboGioiTinhKhach.setSelectedIndex(0);
        } else {
            cboGioiTinhKhach.setSelectedIndex(1);
        }

        lblHinhAnhKhach.setText(khachThueModel.getHinhAnh());
        txtHoKhach.setText(khachThueModel.getHo());
        txtSDTKhach.setText(khachThueModel.getSoDienThoai());
        txtTenKhach.setText(khachThueModel.getTen());
        txtEmailKhach.setText(khachThueModel.getEmail());
    }

    KhachThueEntity getfromKhach() {
        KhachThueEntity kt = new KhachThueEntity();
        kt.setID_khach(txtIdKhach.getText());
        kt.setCCCD(txtCCCDKhach.getText());
        kt.setDiaChi(txtDiaChiKhach.getText());

        if (cboGioiTinhKhach.getSelectedItem().equals("Nam")) {
            kt.setGioiTinh("1");  //nam : bit = 1
        } else {
            kt.setGioiTinh("0");
        }

        kt.setHinhAnh(lblHinhAnhKhach.getText());
        kt.setHo(txtHoKhach.getText());
        kt.setSoDienThoai(txtSDTKhach.getText());
        kt.setTen(txtTenKhach.getText());
        kt.setEmail(txtEmailKhach.getText());

        return kt;
    }

    void insertKhach() {
        KhachThueEntity khachThueEntity = getfromKhach();
        try {
            khachThueDAO.insert(khachThueEntity);
            this.filldataKhachThue();
            tbpQuanLiKhach.setSelectedIndex(0);
            tblKhach.getSelectedRow();
            JOptionPane.showMessageDialog(this, "Thêm Thành Công " + khachThueEntity.getID_khach());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Thêm Thất Bại");
            JOptionPane.showMessageDialog(this, e);
        }
    }

    void updateKhach() {
        KhachThueEntity khachThueEntity = getfromKhach();
        try {
            khachThueDAO.update(khachThueEntity);
            this.filldataKhachThue();
            tbpQuanLiKhach.setSelectedIndex(0);
            tblKhach.getSelectedRow();
            JOptionPane.showMessageDialog(this, "Cập Nhật Thành Công " + khachThueEntity.getID_khach());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "cap nhat that bai");
        }
    }

    void deleteKhach() {
        String IDKhach = txtIdKhach.getText();
        try {
            khachThueDAO.delete(IDKhach);
            this.filldataKhachThue();
            tbpQuanLiKhach.setSelectedIndex(0);
            tblKhach.getSelectedRow();
            JOptionPane.showMessageDialog(this, "Xóa thành công");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Xóa thất bại. Lỗi: " + e.getMessage());
        }
    }

    void currentTableThanhToan(int a) {
        tbpQuanLiThanhToan.setSelectedIndex(1);

        txtIdThanhToan.setText(String.valueOf(tblThanhToan.getValueAt(a, 1)));
        txtNgayThanhToan.setText(String.valueOf(tblThanhToan.getValueAt(a, 2)));
        txtTienPhong.setText(String.valueOf(tblThanhToan.getValueAt(a, 3)));
        txtPhuongThucThanhToan.setText(String.valueOf(tblThanhToan.getValueAt(a, 4)));
        txtThangThanhToan.setText(String.valueOf(tblThanhToan.getValueAt(a, 5)));
        txtTienDien.setText(String.valueOf(tblThanhToan.getValueAt(a, 6)));
        txtTienNuoc.setText(String.valueOf(tblThanhToan.getValueAt(a, 7)));
        txtTienDichVu.setText(String.valueOf(tblThanhToan.getValueAt(a, 8)));
        txtIdHopDongThanhToan.setText(String.valueOf(tblThanhToan.getValueAt(a, 9)));

    }

    void setfromThanhToan(ThanhToanEntity thanhToanEntityModel) {
        txtIdThanhToan.setText(thanhToanEntityModel.getID_ThanhToan());
        txtNgayThanhToan.setText(String.valueOf(thanhToanEntityModel.getNgayThanhToan()));
        txtTienPhong.setText(String.valueOf(thanhToanEntityModel.getTienPhong()));
        txtPhuongThucThanhToan.setText(thanhToanEntityModel.getPhuongThucThanhToan());
        txtThangThanhToan.setText(String.valueOf(thanhToanEntityModel.getThangThanhToan()));
        txtTienDien.setText(String.valueOf(thanhToanEntityModel.getTienDien()));
        txtTienNuoc.setText(String.valueOf(thanhToanEntityModel.getTienNuoc()));
        txtTenDichVu.setText(String.valueOf(thanhToanEntityModel.getTienDichVu()));
        txtIdHopDongThanhToan.setText(thanhToanEntityModel.getID_HopDong());

    }

    ThanhToanEntity getformThanhToan() {
        ThanhToanEntity thanhtoan = new ThanhToanEntity();

        thanhtoan.setID_ThanhToan(txtIdThanhToan.getText());
        thanhtoan.setTienPhong(txtTienPhong.getText());
        thanhtoan.setPhuongThucThanhToan(txtPhuongThucThanhToan.getText());
        thanhtoan.setTienDien(txtTienDien.getText());
        thanhtoan.setTienNuoc(txtTienNuoc.getText());
        thanhtoan.setTienDichVu(txtTienDichVu.getText());
        thanhtoan.setID_HopDong(txtIdHopDongThanhToan.getText());

        try {
            // Định dạng ngày tháng của chuỗi đầu vào
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            // Chuyển đổi chuỗi thành đối tượng Date
            Date ngayThanhToan = sdf.parse(txtNgayThanhToan.getText());

            // Đặt giá trị ngày thanh toán cho đối tượng ThanhToanEntily
            thanhtoan.setNgayThanhToan(ngayThanhToan);
        } catch (ParseException e) {
            // Xử lý nếu có lỗi chuyển đổi
            e.printStackTrace();
            // Hoặc thông báo lỗi cho người dùng nếu cần
        }

        try {
            // Định dạng tháng thanh toán của chuỗi đầu vào
            SimpleDateFormat sdfThang = new SimpleDateFormat("yyyy-MM");

            // Chuyển đổi chuỗi thành đối tượng Date
            Date thangThanhToan = sdfThang.parse(txtThangThanhToan.getText());

            // Đặt giá trị tháng thanh toán cho đối tượng ThanhToanEntily
            thanhtoan.setThangThanhToan(thangThanhToan);
        } catch (ParseException e) {
            // Xử lý nếu có lỗi chuyển đổi
            e.printStackTrace();
            // Hoặc thông báo lỗi cho người dùng nếu cần
        }

        return thanhtoan;
    }

    void insertThanhToan() {
        ThanhToanEntity thanhToanEntity = getformThanhToan();

        try {
            thanhToanDAO.insert(thanhToanEntity);
            this.fillTableThanhToan();
            tbpQuanLiThanhToan.setSelectedIndex(0);
            tblThanhToan.getSelectedRow();
            JOptionPane.showMessageDialog(this, "Thêm thành công " + thanhToanEntity.getID_ThanhToan());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Thêm thất bại" + e.getMessage());
        }
    }

    void updateThanhToan() {
        ThanhToanEntity thanhToanEntity = getformThanhToan();

        try {
            thanhToanDAO.update(thanhToanEntity);
            this.fillTableThanhToan();
            tbpQuanLiThanhToan.setSelectedIndex(0);
            tblThanhToan.getSelectedRow();
            JOptionPane.showMessageDialog(this, "Cập nhật thành công " + thanhToanEntity.getID_ThanhToan());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Cập nhật thành công " + e.getMessage());
        }
    }

    void deleteThanhToan() {
        String IDThanhToan = txtIdThanhToan.getText();
        try {
            thanhToanDAO.delete(IDThanhToan);
            this.fillTableThanhToan();
            tbpQuanLiThanhToan.setSelectedIndex(0);
            tblThanhToan.getSelectedRow();
            JOptionPane.showMessageDialog(this, "Xóa thành công");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Xóa thất bại. Lỗi: " + e.getMessage());
        }
    }

    void currentTableNguoiOCung(int a) {
        tbpQuanLiNguoiOCung.setSelectedIndex(1);

        txtIdNguoiOCung.setText(String.valueOf(tblNguoiOCung.getValueAt(a, 1)));
        txtTenNguoiOCung.setText(String.valueOf(tblNguoiOCung.getValueAt(a, 2)));
        txtHoNguoiOCung.setText(String.valueOf(tblNguoiOCung.getValueAt(a, 3)));
        txtEmailNguoiOCung.setText(String.valueOf(tblNguoiOCung.getValueAt(a, 4))); // Sửa từ txtSoPhong thành txtSoGiuong
        txtDiaChiNguoiOCung.setText(String.valueOf(tblNguoiOCung.getValueAt(a, 5)));
        txtSDTNguoiOCung.setText(String.valueOf(tblNguoiOCung.getValueAt(a, 6)));
        txtGioiTinhNguoiOCung.setText(String.valueOf(tblNguoiOCung.getValueAt(a, 7)));
        txtCCCDNguoiOCung.setText(String.valueOf(tblNguoiOCung.getValueAt(a, 8)));
        lblHinhAnhNguoiOCung.setText(String.valueOf(tblNguoiOCung.getValueAt(a, 9)));
        txtIdKhachNguoiOCung.setText(String.valueOf(tblNguoiOCung.getValueAt(a, 10)));

    }

    void setfromNguoiOcung(NguoiOCungEntity nguoiOCungEntityModel) {
        txtIdNguoiOCung.setText(nguoiOCungEntityModel.getID_NguoiOCung());
        txtHoNguoiOCung.setText(nguoiOCungEntityModel.getHo());
        txtTenNguoiOCung.setText(nguoiOCungEntityModel.getTen());
        txtCCCDNguoiOCung.setText(nguoiOCungEntityModel.getCCCD());
        txtSDTNguoiOCung.setText(nguoiOCungEntityModel.getSoDienThoai());
        txtEmailNguoiOCung.setText(nguoiOCungEntityModel.getEmail());
        txtDiaChiNguoiOCung.setText(nguoiOCungEntityModel.getDiaChi());
        txtGioiTinhNguoiOCung.setText(nguoiOCungEntityModel.getGioiTinh());
        txtIdKhachNguoiOCung.setText(nguoiOCungEntityModel.getID_Khach());
        lblHinhAnhNguoiOCung.setText(nguoiOCungEntityModel.getHinhAnh());
    }

    NguoiOCungEntity getfromNguoiOcung() {
        NguoiOCungEntity nguoiOCungEntity = new NguoiOCungEntity();
        nguoiOCungEntity.setID_NguoiOCung(txtIdNguoiOCung.getText());
        nguoiOCungEntity.setTen(txtTenNguoiOCung.getText());
        nguoiOCungEntity.setHo(txtHoNguoiOCung.getText());
        nguoiOCungEntity.setEmail(txtEmailNguoiOCung.getText());
        nguoiOCungEntity.setDiaChi(txtDiaChiNguoiOCung.getText());
        nguoiOCungEntity.setGioiTinh(txtGioiTinhNguoiOCung.getText());
        nguoiOCungEntity.setSoDienThoai(txtSDTNguoiOCung.getText());
        nguoiOCungEntity.setCCCD(txtCCCDNguoiOCung.getText());
        nguoiOCungEntity.setHinhAnh(lblHinhAnhNguoiOCung.getText());
        nguoiOCungEntity.setID_Khach(txtIdKhachNguoiOCung.getText());
        return nguoiOCungEntity;
    }

    void insertNguoiOCung() {
        NguoiOCungEntity nguoiOCungEntity = getfromNguoiOcung();

        try {
            nguoiOCungDAO.insert(nguoiOCungEntity);
            this.fillTableNguoiOCung();
            tbpQuanLiNguoiOCung.setSelectedIndex(0);
            tblNguoiOCung.getSelectedRow();
            JOptionPane.showMessageDialog(this, "Thêm thành công " + nguoiOCungEntity.getID_NguoiOCung());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Thêm thất bại" + e.getMessage());
        }
    }

    void updateNguoiOCung() {
        NguoiOCungEntity nguoiOCungEntity = getfromNguoiOcung();

        try {
            nguoiOCungDAO.update(nguoiOCungEntity);
            this.fillTableNguoiOCung();
            tbpQuanLiNguoiOCung.setSelectedIndex(0);
            tblNguoiOCung.getSelectedRow();
            JOptionPane.showMessageDialog(this, "Cập nhật thành công " + nguoiOCungEntity.getID_NguoiOCung());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Cập nhật thành công " + e.getMessage());
        }
    }

    void deleteNguoiOCung() {
        String IDNguoiOCung = txtIdNguoiOCung.getText();
        try {
            nguoiOCungDAO.delete(IDNguoiOCung);
            this.fillTableNguoiOCung();
            tbpQuanLiNguoiOCung.setSelectedIndex(0);
            tblNguoiOCung.getSelectedRow();
            JOptionPane.showMessageDialog(this, "Xóa thành công");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Xóa thất bại. Lỗi: " + e.getMessage());
        }
    }

    void currentTableHopDong(int a) {
        tbpQuanLiHopDong.setSelectedIndex(1);

        txtNgayBatDauO.setText(String.valueOf(tblHopDong.getValueAt(a, 1)));
        txtNgayKetThucO.setText(String.valueOf(tblHopDong.getValueAt(a, 2)));
        txtIdHopDong.setText(String.valueOf(tblHopDong.getValueAt(a, 3)));
        txtTienCoc.setText(String.valueOf(tblHopDong.getValueAt(a, 4))); // Sửa từ txtSoPhong thành txtSoGiuong
        txtTienThue.setText(String.valueOf(tblHopDong.getValueAt(a, 5)));
        txtNgayKyHopDong.setText(String.valueOf(tblHopDong.getValueAt(a, 6)));
        txtNgayHetHopDong.setText(String.valueOf(tblHopDong.getValueAt(a, 7)));
        txtGhiChu.setText(String.valueOf(tblHopDong.getValueAt(a, 8)));
        txtIdPhongHopDong.setText(String.valueOf(tblHopDong.getValueAt(a, 9)));
        txtIdKhachHopDong.setText(String.valueOf(tblHopDong.getValueAt(a, 10)));

    }

    void setfromHopDong(HopDongEntity hopDongEntityModel) {
        txtNgayBatDauO.setText(hopDongEntityModel.getNgayBatDauO());
        txtNgayKetThucO.setText(hopDongEntityModel.getNgayKetThucO());
        txtIdHopDong.setText(hopDongEntityModel.getID_HopDong());
        txtTienCoc.setText(hopDongEntityModel.getTienCoc());
        txtTienThue.setText(hopDongEntityModel.getTienThue());
        txtNgayKyHopDong.setText(hopDongEntityModel.getNgayKyHopDong());
        txtNgayHetHopDong.setText(hopDongEntityModel.getNgayHetHopDong());
        txtGhiChu.setText(hopDongEntityModel.getGhiChu());
        txtIdPhongHopDong.setText(hopDongEntityModel.getID_Phong());
        txtIdKhachHopDong.setText(hopDongEntityModel.getID_Khach());
    }

    HopDongEntity getfromHopDong() {
        HopDongEntity hopDongEntity = new HopDongEntity();
        hopDongEntity.setID_HopDong(txtIdHopDong.getText());
        hopDongEntity.setNgayBatDauO(txtNgayBatDauO.getText());
        hopDongEntity.setNgayKetThucO(txtNgayKetThucO.getText());
        hopDongEntity.setTienCoc(txtTienCoc.getText());
        hopDongEntity.setTienThue(txtTienThue.getText());
        hopDongEntity.setNgayKyHopDong(txtNgayKyHopDong.getText());
        hopDongEntity.setNgayHetHopDong(txtNgayHetHopDong.getText());
        hopDongEntity.setGhiChu(txtGhiChu.getText());
        hopDongEntity.setID_Phong(txtIdPhongHopDong.getText());
        hopDongEntity.setID_Khach(txtIdKhachHopDong.getText());
        return hopDongEntity;
    }

    void insertHopDong() {
        HopDongEntity hopDongEntity = getfromHopDong();

        try {
            hopDongDAO.insert(hopDongEntity);
            this.fillTableHopDong();
            tbpQuanLiHopDong.setSelectedIndex(0);
            tblHopDong.getSelectedRow();
            JOptionPane.showMessageDialog(this, "Thêm thành công " + hopDongEntity.getID_HopDong());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Thêm thất bại" + e.getMessage());
        }
    }

    void updateHopDong() {
        HopDongEntity hopDongEntity = getfromHopDong();

        try {
            hopDongDAO.update(hopDongEntity);
            this.fillTableHopDong();
            tbpQuanLiHopDong.setSelectedIndex(0);
            tblHopDong.getSelectedRow();
            JOptionPane.showMessageDialog(this, "Thêm thành công " + hopDongEntity.getID_HopDong());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Thêm thất bại" + e.getMessage());
        }
    }

    void deleteHopDong() {
        String IDHopDong = txtIdHopDong.getText();
        try {
            hopDongDAO.delete(IDHopDong);
            this.fillTableHopDong();
            tbpQuanLiHopDong.setSelectedIndex(0);
            tblHopDong.getSelectedRow();
            JOptionPane.showMessageDialog(this, "Xóa Thành Công");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Xóa thất bại. Lỗi: " + e.getMessage());
        }
    }

    void currentTableNguoiDung(int a) {
        tbpQuanLiNguoiDung.setSelectedIndex(1);

        txtIdNguoiDung.setText(String.valueOf(tblNguoiDung.getValueAt(a, 1)));
        txtTenDangNhapNguoiDung.setText(String.valueOf(tblNguoiDung.getValueAt(a, 2)));
        txtMatKhauNguoiDung.setText(String.valueOf(tblNguoiDung.getValueAt(a, 3)));
        txtChucVuNguoiDung.setText(String.valueOf(tblNguoiDung.getValueAt(a, 4))); // Sửa từ txtSoPhong thành txtSoGiuong
        txtHoNguoiDung.setText(String.valueOf(tblNguoiDung.getValueAt(a, 5)));
        txtTenNguoiDung.setText(String.valueOf(tblNguoiDung.getValueAt(a, 6)));
        txtTrangThaiNguoiDung.setText(String.valueOf(tblNguoiDung.getValueAt(a, 7)));
        txtEmailNguoiDung.setText(String.valueOf(tblNguoiDung.getValueAt(a, 8)));
        txtDiaChiNguoiDung.setText(String.valueOf(tblNguoiDung.getValueAt(a, 9)));
        txtIdHopDongNguoiDung.setText(String.valueOf(tblNguoiDung.getValueAt(a, 10)));

    }

    NguoiDungEntity getfromNguoiDung() {
        NguoiDungEntity nguoiDungEntity = new NguoiDungEntity();
        nguoiDungEntity.setID_NguoiDung(txtIdNguoiDung.getText());
        nguoiDungEntity.setTen(txtTenNguoiDung.getText());
        nguoiDungEntity.setHo(txtHoNguoiDung.getText());
        nguoiDungEntity.setTenDangNhap(txtTenDangNhapNguoiDung.getText());
        nguoiDungEntity.setMatkhau(txtMatKhauNguoiDung.getText());
        nguoiDungEntity.setEmail(txtEmailNguoiDung.getText());
        nguoiDungEntity.setDiaChi(txtDiaChiNguoiDung.getText());
        nguoiDungEntity.setTrang_Thai(txtTrangThaiNguoiDung.getText());
        nguoiDungEntity.setChucVu(txtChucVuNguoiDung.getText());
        nguoiDungEntity.setID_HopDong(txtIdHopDongNguoiDung.getText());

        return nguoiDungEntity;
    }

    void insertNguoiDung() {
        NguoiDungEntity nguoiDungEntity = getfromNguoiDung();

        try {
            nguoiDungDAO.insert(nguoiDungEntity);
            this.fillTableNguoiDung();
            tbpQuanLiNguoiDung.setSelectedIndex(0);
            tblNguoiDung.getSelectedRow();
            JOptionPane.showMessageDialog(this, "thêm thành công");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "thêm thất bại" + e.getMessage());
        }
    }

    void updateNguoiDung() {
        NguoiDungEntity nguoiDungEntity = getfromNguoiDung();

        try {
            nguoiDungDAO.update(nguoiDungEntity);
            this.fillTableNguoiDung();
            tbpQuanLiNguoiDung.setSelectedIndex(0);
            tblNguoiDung.getSelectedRow();
            JOptionPane.showMessageDialog(this, "Cập nhật thành công");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Cập nhật thất bại" + e.getMessage());
        }
    }

    void deleteNguoiDung() {
        String IDNguoiDung = txtIdNguoiDung.getText();

        try {
            nguoiDungDAO.delete(IDNguoiDung);
            this.fillTableNguoiDung();
            tbpQuanLiNguoiDung.setSelectedIndex(0);
            tblNguoiDung.getSelectedRow();
            JOptionPane.showMessageDialog(this, "Xóa thành công");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Xóa thất bại" + e.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        jPanel9 = new javax.swing.JPanel();
        pHeader = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblNguoiDung = new javax.swing.JLabel();
        lblChucVu = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tbpCHUNG = new javax.swing.JTabbedPane();
        tbpQuanLiPhong = new javax.swing.JTabbedPane();
        spXemDanhSachPhong = new javax.swing.JScrollPane();
        tblPhong = new javax.swing.JTable();
        spCapNhatPhong = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtTrangThaiPhong = new javax.swing.JTextField();
        txtGiaThue = new javax.swing.JTextField();
        txtSoPhong = new javax.swing.JTextField();
        txtSoGiuong = new javax.swing.JTextField();
        txtIDPhong = new javax.swing.JTextField();
        txtDientich = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        tbpQuanLiKhach = new javax.swing.JTabbedPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblKhach = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtIdKhach = new javax.swing.JTextField();
        txtCCCDKhach = new javax.swing.JTextField();
        txtHoKhach = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtDiaChiKhach = new javax.swing.JTextField();
        txtEmailKhach = new javax.swing.JTextField();
        txtTenKhach = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtSDTKhach = new javax.swing.JTextField();
        cboGioiTinhKhach = new javax.swing.JComboBox<String>();
        lblHinhAnhKhach = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        tbpQuanLiHopDong = new javax.swing.JTabbedPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblHopDong = new javax.swing.JTable();
        jScrollPane7 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextArea();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        txtIdHopDong = new javax.swing.JTextField();
        txtNgayBatDauO = new javax.swing.JTextField();
        txtTienThue = new javax.swing.JTextField();
        txtNgayKetThucO = new javax.swing.JTextField();
        txtNgayKyHopDong = new javax.swing.JTextField();
        txtNgayHetHopDong = new javax.swing.JTextField();
        txtIdKhachHopDong = new javax.swing.JTextField();
        txtIdPhongHopDong = new javax.swing.JTextField();
        txtTienCoc = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        tbpQuanLiThanhToan = new javax.swing.JTabbedPane();
        jScrollPane9 = new javax.swing.JScrollPane();
        tblThanhToan = new javax.swing.JTable();
        jScrollPane10 = new javax.swing.JScrollPane();
        jPanel5 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        txtPhuongThucThanhToan = new javax.swing.JTextField();
        txtThangThanhToan = new javax.swing.JTextField();
        txtNgayThanhToan = new javax.swing.JTextField();
        txtIdThanhToan = new javax.swing.JTextField();
        txtTienDichVu = new javax.swing.JTextField();
        txtIdHopDongThanhToan = new javax.swing.JTextField();
        txtTienNuoc = new javax.swing.JTextField();
        txtTienPhong = new javax.swing.JTextField();
        txtTienDien = new javax.swing.JTextField();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        tbpQuanLiNguoiDung = new javax.swing.JTabbedPane();
        jScrollPane11 = new javax.swing.JScrollPane();
        tblNguoiDung = new javax.swing.JTable();
        jScrollPane12 = new javax.swing.JScrollPane();
        jPanel6 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        txtIdNguoiDung = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        txtHoNguoiDung = new javax.swing.JTextField();
        txtMatKhauNguoiDung = new javax.swing.JTextField();
        txtChucVuNguoiDung = new javax.swing.JTextField();
        txtTenDangNhapNguoiDung = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        txtIdHopDongNguoiDung = new javax.swing.JTextField();
        txtTenNguoiDung = new javax.swing.JTextField();
        txtTrangThaiNguoiDung = new javax.swing.JTextField();
        txtEmailNguoiDung = new javax.swing.JTextField();
        txtDiaChiNguoiDung = new javax.swing.JTextField();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        tbpQuanLiDichVu = new javax.swing.JTabbedPane();
        dfgđgg = new javax.swing.JScrollPane();
        tblDichVu = new javax.swing.JTable();
        jScrollPane14 = new javax.swing.JScrollPane();
        jPanel7 = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        txtIdDichVu = new javax.swing.JTextField();
        txtTenDichVu = new javax.swing.JTextField();
        txtDonGia = new javax.swing.JTextField();
        txtNgayDichVu = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        txtNamDichVu = new javax.swing.JTextField();
        txtTrangThaiDichVu = new javax.swing.JTextField();
        txtIdPhongDichVu = new javax.swing.JTextField();
        lblHinhAnhDichVu = new javax.swing.JLabel();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        tbpQuanLiNguoiOCung = new javax.swing.JTabbedPane();
        jScrollPane15 = new javax.swing.JScrollPane();
        tblNguoiOCung = new javax.swing.JTable();
        jScrollPane16 = new javax.swing.JScrollPane();
        jPanel8 = new javax.swing.JPanel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        txtIdNguoiOCung = new javax.swing.JTextField();
        txtSDTNguoiOCung = new javax.swing.JTextField();
        txtHoNguoiOCung = new javax.swing.JTextField();
        txtGioiTinhNguoiOCung = new javax.swing.JTextField();
        txtTenNguoiOCung = new javax.swing.JTextField();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        txtDiaChiNguoiOCung = new javax.swing.JTextField();
        txtCCCDNguoiOCung = new javax.swing.JTextField();
        txtEmailNguoiOCung = new javax.swing.JTextField();
        txtIdKhachNguoiOCung = new javax.swing.JTextField();
        lblHinhAnhNguoiOCung = new javax.swing.JLabel();
        jButton19 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        tbpQuanLiThongKe = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        pMenu = new javax.swing.JPanel();
        btnPhong = new javax.swing.JButton();
        btnKhach = new javax.swing.JButton();
        btnHopDong = new javax.swing.JButton();
        btnThanhToan = new javax.swing.JButton();
        btnNguoiDung = new javax.swing.JButton();
        btnDichVu = new javax.swing.JButton();
        btnNguoiOCung = new javax.swing.JButton();
        jButton32 = new javax.swing.JButton();
        jButton33 = new javax.swing.JButton();
        lblThoiGian = new javax.swing.JLabel();

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel9.setBackground(new java.awt.Color(51, 51, 51));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pHeader.setBackground(new java.awt.Color(156, 0, 0));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Việt Anh Ngàn SAo.png"))); // NOI18N
        jLabel1.setToolTipText("");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel1MousePressed(evt);
            }
        });

        lblNguoiDung.setBackground(new java.awt.Color(255, 255, 255));
        lblNguoiDung.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblNguoiDung.setForeground(new java.awt.Color(255, 255, 255));
        lblNguoiDung.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Male User.png"))); // NOI18N
        lblNguoiDung.setText("adnajkd");
        lblNguoiDung.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblNguoiDung.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblNguoiDungMousePressed(evt);
            }
        });

        lblChucVu.setBackground(new java.awt.Color(255, 255, 255));
        lblChucVu.setForeground(new java.awt.Color(255, 255, 255));
        lblChucVu.setText("chucvu");

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Đăng Xuất");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pHeaderLayout = new javax.swing.GroupLayout(pHeader);
        pHeader.setLayout(pHeaderLayout);
        pHeaderLayout.setHorizontalGroup(
            pHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pHeaderLayout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 908, Short.MAX_VALUE)
                .addGroup(pHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pHeaderLayout.createSequentialGroup()
                        .addComponent(lblChucVu)
                        .addGap(104, 104, 104)
                        .addComponent(jLabel2))
                    .addComponent(lblNguoiDung, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(121, 121, 121))
        );
        pHeaderLayout.setVerticalGroup(
            pHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pHeaderLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(lblNguoiDung)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblChucVu)
                    .addComponent(jLabel2))
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel9.add(pHeader, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1490, 100));

        tbpCHUNG.setBackground(new java.awt.Color(255, 255, 255));
        tbpCHUNG.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        tbpCHUNG.setToolTipText("");
        tbpCHUNG.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        tbpQuanLiPhong.setBackground(new java.awt.Color(255, 255, 255));
        tbpQuanLiPhong.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        tblPhong.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tblPhong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "SoThuTu", "ID PHÒNG", "SỐ PHÒNG ", "DIỆN TÍCH", "SỐ GIƯỜNG", "GIÁ THUÊ", "TRẠNG THÁI"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPhong.setGridColor(new java.awt.Color(255, 255, 255));
        tblPhong.setSelectionBackground(new java.awt.Color(1, 152, 122));
        tblPhong.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tblPhong.setShowGrid(false);
        tblPhong.setShowHorizontalLines(true);
        tblPhong.setShowVerticalLines(true);
        tblPhong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblPhongMousePressed(evt);
            }
        });
        spXemDanhSachPhong.setViewportView(tblPhong);

        tbpQuanLiPhong.addTab("Xem danh sách phòng", spXemDanhSachPhong);

        jLabel3.setText("ID PHÒNG:");

        jLabel4.setText("SỐ PHÒNG");

        jLabel5.setText("DIỆN TÍCH");

        jLabel6.setText("SỐ GIƯỜNG");

        jLabel7.setText("GIÁ THUÊ:");

        jLabel9.setText("TRẠNG THÁI:");

        jButton1.setText("THÊM");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("XÓA");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("CẬP NHẬT");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton22.setText("<<");
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        jButton23.setText(">>");
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(210, 210, 210)
                                .addComponent(jButton1)
                                .addGap(18, 18, 18)
                                .addComponent(jButton2)
                                .addGap(18, 18, 18)
                                .addComponent(jButton3))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(txtSoPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(50, 50, 50)
                                                .addComponent(jLabel7))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(txtIDPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(45, 45, 45)
                                                .addComponent(jLabel6)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtGiaThue, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtSoGiuong, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(230, 230, 230)
                                        .addComponent(jLabel9)
                                        .addGap(29, 29, 29)
                                        .addComponent(txtTrangThaiPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jButton22)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton23))
                            .addComponent(txtDientich, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(723, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtIDPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtSoGiuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtSoPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtGiaThue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtDientich, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txtTrangThaiPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(69, 69, 69)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton22)
                    .addComponent(jButton23))
                .addContainerGap(398, Short.MAX_VALUE))
        );

        spCapNhatPhong.setViewportView(jPanel2);

        tbpQuanLiPhong.addTab("Cập nhật phòng", spCapNhatPhong);

        tbpCHUNG.addTab("", tbpQuanLiPhong);

        tbpQuanLiKhach.setBackground(new java.awt.Color(255, 255, 255));
        tbpQuanLiKhach.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        tblKhach.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tblKhach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "SoThuTu", "ID KHÁCH THUÊ", "HỌ", "CCCD", "ĐỊA CHỈ", "EMAIL", "SỐ ĐIỆN THOẠI", "TÊN", "HÌNH ẢNH", "GIỚI TÍNH"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblKhach.setSelectionBackground(new java.awt.Color(1, 152, 122));
        tblKhach.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tblKhach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblKhachMousePressed(evt);
            }
        });
        jScrollPane4.setViewportView(tblKhach);
        if (tblKhach.getColumnModel().getColumnCount() > 0) {
            tblKhach.getColumnModel().getColumn(0).setMaxWidth(100);
        }

        tbpQuanLiKhach.addTab("Xem danh sách khách thuê", jScrollPane4);

        jLabel8.setText("ID KHÁCH:");

        jLabel10.setText("HỌ:");

        jLabel11.setText("GIỚI TÍNH:");

        jLabel12.setText("CCCD:");

        txtIdKhach.setText(" ");

        txtCCCDKhach.setText(" ");

        txtHoKhach.setText(" ");

        jLabel13.setText("EMAIL:");

        jLabel14.setText("TÊN:");

        jLabel15.setText("HÌNH ẢNH:");

        txtDiaChiKhach.setText(" ");
        txtDiaChiKhach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiaChiKhachActionPerformed(evt);
            }
        });

        txtEmailKhach.setText(" ");

        txtTenKhach.setText(" ");

        jLabel16.setText("ĐỊA CHỈ:");

        jLabel17.setText("SỐ ĐIỆN THOẠI:");

        txtSDTKhach.setText(" ");

        cboGioiTinhKhach.setModel(new javax.swing.DefaultComboBoxModel<String>(new String[] { "Nam", "Nữ", "Khác" }));

        lblHinhAnhKhach.setText("jLabel18");
        lblHinhAnhKhach.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblHinhAnhKhach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHinhAnhKhachMouseClicked(evt);
            }
        });

        jButton4.setText("THÊM");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("CẬP NHẬT");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("XÓA");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel11))
                                .addGap(48, 48, 48)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cboGioiTinhKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtIdKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtHoKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCCCDKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(31, 31, 31)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel13)
                                            .addComponent(jLabel14)
                                            .addComponent(jLabel16))
                                        .addGap(34, 34, 34)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtEmailKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtTenKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtDiaChiKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addGap(38, 38, 38)
                                        .addComponent(lblHinhAnhKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addGap(18, 18, 18)
                                .addComponent(txtSDTKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(248, 248, 248)
                        .addComponent(jButton4)
                        .addGap(18, 18, 18)
                        .addComponent(jButton6)
                        .addGap(18, 18, 18)
                        .addComponent(jButton5)))
                .addContainerGap(783, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtIdKhach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(txtEmailKhach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtHoKhach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(txtTenKhach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtCCCDKhach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(txtDiaChiKhach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(cboGioiTinhKhach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(txtSDTKhach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lblHinhAnhKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(29, 29, 29)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton6)
                    .addComponent(jButton5))
                .addContainerGap(402, Short.MAX_VALUE))
        );

        jScrollPane6.setViewportView(jPanel3);

        tbpQuanLiKhach.addTab("Câp nhật khách thuê", jScrollPane6);

        tbpCHUNG.addTab("", tbpQuanLiKhach);

        tbpQuanLiHopDong.setBackground(new java.awt.Color(255, 255, 255));
        tbpQuanLiHopDong.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        tblHopDong.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tblHopDong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "SoThuTu", "Ngày Bắt Đầu Ở", "Ngày Kết Thúc Ở", "ID_Hợp Đồng", "Tiền cọc", "Tiền thuê", "Ngày ký hợp đồng", "Ngày hết hợp đồng", "Ghi chú", "ID phòng", "ID khách"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHopDong.setSelectionBackground(new java.awt.Color(1, 152, 122));
        tblHopDong.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tblHopDong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblHopDongMousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(tblHopDong);
        if (tblHopDong.getColumnModel().getColumnCount() > 0) {
            tblHopDong.getColumnModel().getColumn(0).setMaxWidth(100);
        }

        tbpQuanLiHopDong.addTab("Xem danh sách hợp đồng", jScrollPane3);

        jLabel19.setText("ID HỢP ĐỒNG:");

        jLabel20.setText("NGÀY BẮT ĐẦU Ở:");

        jLabel21.setText("NGÀY KẾT THÚC Ở:");

        jLabel22.setText("TIỀN CỌC:");

        jLabel23.setText("TIỀN THUÊ:");

        jLabel24.setText("NGÀY KÝ HỢP ĐỒNG:");

        jLabel25.setText("NGÀY HẾT HỢP ĐỒNG:");

        jLabel26.setText("GHI CHÚ");

        txtGhiChu.setColumns(20);
        txtGhiChu.setRows(5);
        jScrollPane8.setViewportView(txtGhiChu);

        jLabel27.setText("ID PHÒNG:");

        jLabel28.setText("ID KHÁCH:");

        txtTienThue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTienThueActionPerformed(evt);
            }
        });

        txtNgayKetThucO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNgayKetThucOActionPerformed(evt);
            }
        });

        txtNgayKyHopDong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNgayKyHopDongActionPerformed(evt);
            }
        });

        txtNgayHetHopDong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNgayHetHopDongActionPerformed(evt);
            }
        });

        txtIdKhachHopDong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdKhachHopDongActionPerformed(evt);
            }
        });

        txtIdPhongHopDong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdPhongHopDongActionPerformed(evt);
            }
        });

        txtTienCoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTienCocActionPerformed(evt);
            }
        });

        jButton7.setText("THÊM");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("XÓA");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setText("CẬP NHẬT");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addComponent(jLabel21)
                            .addComponent(jLabel22)
                            .addComponent(jLabel23)
                            .addComponent(jLabel20))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtIdHopDong, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtNgayBatDauO, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtNgayKetThucO, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtTienThue, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtTienCoc, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(55, 55, 55)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel24)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtNgayKyHopDong, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel25)
                                    .addComponent(jLabel27))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtIdKhachHopDong, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNgayHetHopDong, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtIdPhongHopDong, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel28)
                                    .addComponent(jLabel26))
                                .addGap(29, 29, 29)
                                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(jButton7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton9)))
                .addContainerGap(640, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jLabel24)
                    .addComponent(txtIdHopDong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNgayKyHopDong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel20)
                        .addComponent(txtNgayBatDauO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel25)
                        .addComponent(txtNgayHetHopDong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel21))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNgayKetThucO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27)
                            .addComponent(txtIdPhongHopDong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel28)
                                .addComponent(txtIdKhachHopDong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel26)
                                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(txtTienCoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtTienThue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(60, 60, 60)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel23)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 343, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton7)
                    .addComponent(jButton8)
                    .addComponent(jButton9))
                .addGap(79, 79, 79))
        );

        jScrollPane7.setViewportView(jPanel4);

        tbpQuanLiHopDong.addTab("Tạo hợp đồng", jScrollPane7);

        tbpCHUNG.addTab("", tbpQuanLiHopDong);

        tbpQuanLiThanhToan.setBackground(new java.awt.Color(255, 255, 255));
        tbpQuanLiThanhToan.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        tblThanhToan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tblThanhToan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "SoThuTu", "ID Thanh toán", "Ngày thanh toán ", "Tiền phòng", "Phương thức thanh toán", "Tháng thánh toán", "Tiền điện", "Tiền nước", "Tiền dịch vụ", "ID hợp đồng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblThanhToan.setSelectionBackground(new java.awt.Color(1, 152, 122));
        tblThanhToan.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tblThanhToan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblThanhToanMousePressed(evt);
            }
        });
        jScrollPane9.setViewportView(tblThanhToan);
        if (tblThanhToan.getColumnModel().getColumnCount() > 0) {
            tblThanhToan.getColumnModel().getColumn(0).setMaxWidth(100);
        }

        tbpQuanLiThanhToan.addTab("Xem danh sách thanh toán", jScrollPane9);

        jLabel29.setText("ID THANH TOÁN:");

        jLabel30.setText("NGÀY THANH TOÁN:");

        jLabel31.setText("TIỀN PHÒNG:");

        jLabel32.setText("PHƯƠNG THỨC THANH TOÁN");

        jLabel33.setText("THÁNG THANH TOÁN");

        jLabel34.setText("TIỀN ĐIỆN:");

        jLabel35.setText("TIỀN NƯỚC");

        jLabel36.setText("TIỀN DICH VỤ");

        jLabel37.setText("ID HỢP ĐỒNG:");

        txtThangThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtThangThanhToanActionPerformed(evt);
            }
        });

        txtNgayThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNgayThanhToanActionPerformed(evt);
            }
        });

        txtIdThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdThanhToanActionPerformed(evt);
            }
        });

        txtTienDichVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTienDichVuActionPerformed(evt);
            }
        });

        txtIdHopDongThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdHopDongThanhToanActionPerformed(evt);
            }
        });

        txtTienNuoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTienNuocActionPerformed(evt);
            }
        });

        txtTienPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTienPhongActionPerformed(evt);
            }
        });

        txtTienDien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTienDienActionPerformed(evt);
            }
        });

        jButton10.setText("THÊM");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setText("CẬP NHẬT");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setText("XÓA");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel29)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtIdThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel30)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtNgayThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel32)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtPhuongThucThanhToan))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel31)
                                        .addGap(97, 97, 97)
                                        .addComponent(txtTienPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(37, 37, 37)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel37)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtIdHopDongThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel36)
                                            .addComponent(jLabel35)
                                            .addComponent(jLabel34))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtTienDien, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtTienNuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtTienDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel33)
                                .addGap(51, 51, 51)
                                .addComponent(txtThangThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(jButton10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton11)))
                .addContainerGap(842, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTienDien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(jLabel35)
                    .addComponent(txtNgayThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTienNuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(jLabel36)
                    .addComponent(txtTienDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTienPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(jLabel37)
                    .addComponent(txtPhuongThucThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdHopDongThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(txtThangThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton10)
                    .addComponent(jButton11)
                    .addComponent(jButton12))
                .addContainerGap(420, Short.MAX_VALUE))
        );

        jScrollPane10.setViewportView(jPanel5);

        tbpQuanLiThanhToan.addTab("Cập nhật thanh toán", jScrollPane10);

        tbpCHUNG.addTab("", tbpQuanLiThanhToan);

        tbpQuanLiNguoiDung.setBackground(new java.awt.Color(255, 255, 255));
        tbpQuanLiNguoiDung.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        tblNguoiDung.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tblNguoiDung.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "SoThuTu", "ID Người dùng", "Tên đăng nhập", "Mật khẩu", "Chức vụ", "Họ ", "Tên", "Trạng thái", "Email", "Địa chỉ", "ID hợp đồng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNguoiDung.setSelectionBackground(new java.awt.Color(1, 152, 122));
        tblNguoiDung.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tblNguoiDung.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblNguoiDungMousePressed(evt);
            }
        });
        jScrollPane11.setViewportView(tblNguoiDung);
        if (tblNguoiDung.getColumnModel().getColumnCount() > 0) {
            tblNguoiDung.getColumnModel().getColumn(0).setMaxWidth(100);
        }

        tbpQuanLiNguoiDung.addTab("Xem danh sách người dùng", jScrollPane11);

        jLabel38.setText("ID Người dùng:");

        jLabel39.setText("Tên đăng nhập:");

        jLabel40.setText("Mật khẩu");

        jLabel41.setText("Chức vụ:");

        jLabel42.setText("Họ:");

        txtChucVuNguoiDung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtChucVuNguoiDungActionPerformed(evt);
            }
        });

        jLabel43.setText("Tên:");

        jLabel44.setText("Trạng thái:");

        jLabel45.setText("Email:");

        jLabel46.setText("Địa chỉ:");

        jLabel47.setText("ID hợp đồng");

        jButton13.setText("THÊM");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton14.setText("XÓA");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton15.setText("CẬP NHẬT");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel38)
                            .addComponent(jLabel39)
                            .addComponent(jLabel41)
                            .addComponent(jLabel40)
                            .addComponent(jLabel42))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(txtChucVuNguoiDung, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel46))
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                    .addComponent(txtMatKhauNguoiDung, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel45)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtEmailNguoiDung, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                    .addComponent(txtTenDangNhapNguoiDung, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel44)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtTrangThaiNguoiDung, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel6Layout.createSequentialGroup()
                                            .addComponent(txtHoNguoiDung, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jLabel47))
                                        .addGroup(jPanel6Layout.createSequentialGroup()
                                            .addComponent(txtIdNguoiDung, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jLabel43)))
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel6Layout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txtDiaChiNguoiDung, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtIdHopDongNguoiDung, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtTenNguoiDung, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(200, 200, 200)
                        .addComponent(jButton13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton15)))
                .addContainerGap(746, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(txtIdNguoiDung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel43)
                    .addComponent(txtTenNguoiDung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenDangNhapNguoiDung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel39)
                    .addComponent(jLabel44)
                    .addComponent(txtTrangThaiNguoiDung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMatKhauNguoiDung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel40)
                    .addComponent(jLabel45)
                    .addComponent(txtEmailNguoiDung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtChucVuNguoiDung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel41)
                    .addComponent(jLabel46)
                    .addComponent(txtDiaChiNguoiDung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42)
                    .addComponent(txtHoNguoiDung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel47)
                    .addComponent(txtIdHopDongNguoiDung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 393, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton13)
                    .addComponent(jButton14)
                    .addComponent(jButton15))
                .addGap(55, 55, 55))
        );

        jScrollPane12.setViewportView(jPanel6);

        tbpQuanLiNguoiDung.addTab("Cập nhật người dùng", jScrollPane12);

        tbpCHUNG.addTab("", tbpQuanLiNguoiDung);

        tbpQuanLiDichVu.setBackground(new java.awt.Color(255, 255, 255));
        tbpQuanLiDichVu.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        dfgđgg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                dfgđggMousePressed(evt);
            }
        });

        tblDichVu.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tblDichVu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "SoThuTu", "ID Dịch vụ", "Tên dịch vụ", "Đơn giá", "Ngày ", "Năm", "Hình ảnh", "Trạng thái", "ID phòng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDichVu.setSelectionBackground(new java.awt.Color(1, 152, 122));
        tblDichVu.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tblDichVu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblDichVuMousePressed(evt);
            }
        });
        dfgđgg.setViewportView(tblDichVu);
        if (tblDichVu.getColumnModel().getColumnCount() > 0) {
            tblDichVu.getColumnModel().getColumn(0).setMaxWidth(100);
        }

        tbpQuanLiDichVu.addTab("Lịch sử dịch vụ", dfgđgg);

        jLabel48.setText("ID Dịch vu:");

        jLabel49.setText("Tên dịch vụ:");

        jLabel50.setText("Đơn giá");

        jLabel51.setText("Ngày:");

        jLabel52.setText("Năm:");

        jLabel53.setText("Trạng thái:");

        jLabel54.setText("ID phòng:");

        jLabel55.setText("Hình ảnh:");

        lblHinhAnhDichVu.setText("jLabel56");
        lblHinhAnhDichVu.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton16.setText("THÊM");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jButton17.setText("XÓA");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jButton18.setText("CẬP NHẬT");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel51)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtNgayDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel50)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                        .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel49)
                                .addGap(18, 18, 18))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel48)
                                .addGap(24, 24, 24)))
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtIdDichVu, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                            .addComponent(txtTenDichVu))))
                .addGap(45, 45, 45)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel52)
                        .addGap(38, 38, 38)
                        .addComponent(txtNamDichVu, javax.swing.GroupLayout.DEFAULT_SIZE, 754, Short.MAX_VALUE)
                        .addGap(165, 165, 165))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel53)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTrangThaiDichVu, javax.swing.GroupLayout.DEFAULT_SIZE, 754, Short.MAX_VALUE)
                        .addGap(164, 164, 164))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel54)
                            .addComponent(jLabel55))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(txtIdPhongDichVu, javax.swing.GroupLayout.DEFAULT_SIZE, 756, Short.MAX_VALUE)
                                .addGap(160, 160, 160))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(lblHinhAnhDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(132, 132, 132)
                .addComponent(jButton16)
                .addGap(18, 18, 18)
                .addComponent(jButton17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton18)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel48)
                    .addComponent(txtIdDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel52)
                    .addComponent(txtNamDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel49)
                    .addComponent(txtTenDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel53)
                    .addComponent(txtTrangThaiDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel50)
                    .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel54)
                    .addComponent(txtIdPhongDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel51)
                    .addComponent(txtNgayDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel55)
                    .addComponent(lblHinhAnhDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton16)
                    .addComponent(jButton17)
                    .addComponent(jButton18))
                .addContainerGap(386, Short.MAX_VALUE))
        );

        jScrollPane14.setViewportView(jPanel7);

        tbpQuanLiDichVu.addTab("Cập nhật dịch vụ", jScrollPane14);

        tbpCHUNG.addTab("", tbpQuanLiDichVu);

        tbpQuanLiNguoiOCung.setBackground(new java.awt.Color(255, 255, 255));
        tbpQuanLiNguoiOCung.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        tblNguoiOCung.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tblNguoiOCung.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "SoThuTu", "ID người ở cùng", "Tên", "Họ", "Email", "Địa chỉ", "Số điện thoại", "Giới tính", "CCCD", "Hình ảnh", "ID khách"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNguoiOCung.setSelectionBackground(new java.awt.Color(1, 152, 122));
        tblNguoiOCung.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tblNguoiOCung.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblNguoiOCungMousePressed(evt);
            }
        });
        jScrollPane15.setViewportView(tblNguoiOCung);
        if (tblNguoiOCung.getColumnModel().getColumnCount() > 0) {
            tblNguoiOCung.getColumnModel().getColumn(0).setMaxWidth(100);
        }

        tbpQuanLiNguoiOCung.addTab("Danh sách người ở cùng", jScrollPane15);

        jScrollPane16.setBackground(new java.awt.Color(255, 255, 255));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jLabel57.setText("ID người ở cùng:");

        jLabel58.setText("Họ");

        jLabel59.setText("Tên");

        jLabel60.setText("Giới tính:");

        jLabel61.setText("Số điện thoại:");

        jLabel62.setText("CCCD:");

        jLabel63.setText("Địa chỉ:");

        jLabel64.setText("Email:");

        jLabel65.setText("ID khách:");

        jLabel66.setText("Hình ảnh:");

        lblHinhAnhNguoiOCung.setText("jLabel67");
        lblHinhAnhNguoiOCung.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton19.setText("THÊM");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        jButton20.setText("XÓA");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        jButton21.setText("CẬP NHẬT");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel57)
                            .addComponent(jLabel58)
                            .addComponent(jLabel60)
                            .addComponent(jLabel59))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtIdNguoiOCung, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                            .addComponent(txtHoNguoiOCung)
                            .addComponent(txtTenNguoiOCung)
                            .addComponent(txtGioiTinhNguoiOCung)
                            .addComponent(txtSDTNguoiOCung)))
                    .addComponent(jLabel61))
                .addGap(35, 35, 35)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel62)
                        .addGap(48, 48, 48)
                        .addComponent(txtCCCDNguoiOCung, javax.swing.GroupLayout.DEFAULT_SIZE, 666, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel63)
                        .addGap(43, 43, 43)
                        .addComponent(txtDiaChiNguoiOCung, javax.swing.GroupLayout.DEFAULT_SIZE, 667, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel64)
                        .addGap(50, 50, 50)
                        .addComponent(txtEmailNguoiOCung, javax.swing.GroupLayout.DEFAULT_SIZE, 667, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel65)
                            .addComponent(jLabel66))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtIdKhachNguoiOCung, javax.swing.GroupLayout.DEFAULT_SIZE, 666, Short.MAX_VALUE)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(lblHinhAnhNguoiOCung, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 586, Short.MAX_VALUE)))))
                .addGap(247, 247, 247))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(106, 106, 106)
                .addComponent(jButton19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton21)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel57)
                    .addComponent(txtIdNguoiOCung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel62)
                    .addComponent(txtCCCDNguoiOCung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel58)
                    .addComponent(txtHoNguoiOCung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel63)
                    .addComponent(txtDiaChiNguoiOCung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel59)
                    .addComponent(txtTenNguoiOCung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel64)
                    .addComponent(txtEmailNguoiOCung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel60)
                    .addComponent(txtGioiTinhNguoiOCung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel65)
                    .addComponent(txtIdKhachNguoiOCung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel61)
                    .addComponent(txtSDTNguoiOCung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel66)
                    .addComponent(lblHinhAnhNguoiOCung, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton19)
                    .addComponent(jButton20)
                    .addComponent(jButton21))
                .addContainerGap(368, Short.MAX_VALUE))
        );

        jScrollPane16.setViewportView(jPanel8);

        tbpQuanLiNguoiOCung.addTab("Cập nhật người ở cùng", jScrollPane16);

        tbpCHUNG.addTab("", tbpQuanLiNguoiOCung);

        tbpQuanLiThongKe.addTab("Thống Kê", jScrollPane1);

        tbpCHUNG.addTab("tab8", tbpQuanLiThongKe);

        jPanel9.add(tbpCHUNG, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, 1240, 770));

        pMenu.setBackground(new java.awt.Color(51, 51, 51));

        btnPhong.setBackground(new java.awt.Color(51, 51, 51));
        btnPhong.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        btnPhong.setForeground(new java.awt.Color(255, 255, 255));
        btnPhong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Home.png"))); // NOI18N
        btnPhong.setText("Quản Lí Phòng");
        btnPhong.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnPhong.setDisabledSelectedIcon(null);
        btnPhong.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnPhong.setPreferredSize(new java.awt.Dimension(170, 60));
        btnPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPhongActionPerformed(evt);
            }
        });
        pMenu.add(btnPhong);

        btnKhach.setBackground(new java.awt.Color(51, 51, 51));
        btnKhach.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        btnKhach.setForeground(new java.awt.Color(255, 255, 255));
        btnKhach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Users.png"))); // NOI18N
        btnKhach.setText("Quản Lí Khách");
        btnKhach.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnKhach.setDisabledSelectedIcon(null);
        btnKhach.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnKhach.setPreferredSize(new java.awt.Dimension(170, 60));
        btnKhach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhachActionPerformed(evt);
            }
        });
        pMenu.add(btnKhach);

        btnHopDong.setBackground(new java.awt.Color(51, 51, 51));
        btnHopDong.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        btnHopDong.setForeground(new java.awt.Color(255, 255, 255));
        btnHopDong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Conclusion Contract.png"))); // NOI18N
        btnHopDong.setText("Quản Lí Hợp Đồng");
        btnHopDong.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnHopDong.setDisabledSelectedIcon(null);
        btnHopDong.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnHopDong.setPreferredSize(new java.awt.Dimension(170, 60));
        btnHopDong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHopDongActionPerformed(evt);
            }
        });
        pMenu.add(btnHopDong);

        btnThanhToan.setBackground(new java.awt.Color(51, 51, 51));
        btnThanhToan.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        btnThanhToan.setForeground(new java.awt.Color(255, 255, 255));
        btnThanhToan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Magnetic Card.png"))); // NOI18N
        btnThanhToan.setText("Quản Lí Thanh Toán");
        btnThanhToan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnThanhToan.setDisabledSelectedIcon(null);
        btnThanhToan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnThanhToan.setPreferredSize(new java.awt.Dimension(170, 60));
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });
        pMenu.add(btnThanhToan);

        btnNguoiDung.setBackground(new java.awt.Color(51, 51, 51));
        btnNguoiDung.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        btnNguoiDung.setForeground(new java.awt.Color(255, 255, 255));
        btnNguoiDung.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Male User.png"))); // NOI18N
        btnNguoiDung.setText("Quản Lí Người Dùng");
        btnNguoiDung.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnNguoiDung.setDisabledSelectedIcon(null);
        btnNguoiDung.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnNguoiDung.setPreferredSize(new java.awt.Dimension(170, 60));
        btnNguoiDung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNguoiDungActionPerformed(evt);
            }
        });
        pMenu.add(btnNguoiDung);

        btnDichVu.setBackground(new java.awt.Color(51, 51, 51));
        btnDichVu.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        btnDichVu.setForeground(new java.awt.Color(255, 255, 255));
        btnDichVu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Service.png"))); // NOI18N
        btnDichVu.setText("Quản Lí Dịch Vụ");
        btnDichVu.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnDichVu.setDisabledSelectedIcon(null);
        btnDichVu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnDichVu.setPreferredSize(new java.awt.Dimension(170, 60));
        btnDichVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDichVuActionPerformed(evt);
            }
        });
        pMenu.add(btnDichVu);

        btnNguoiOCung.setBackground(new java.awt.Color(51, 51, 51));
        btnNguoiOCung.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        btnNguoiOCung.setForeground(new java.awt.Color(255, 255, 255));
        btnNguoiOCung.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Person.png"))); // NOI18N
        btnNguoiOCung.setText("Quản Lí Người Ở Cùng");
        btnNguoiOCung.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnNguoiOCung.setDisabledSelectedIcon(null);
        btnNguoiOCung.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnNguoiOCung.setPreferredSize(new java.awt.Dimension(170, 60));
        btnNguoiOCung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNguoiOCungActionPerformed(evt);
            }
        });
        pMenu.add(btnNguoiOCung);

        jButton32.setBackground(new java.awt.Color(51, 51, 51));
        jButton32.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jButton32.setForeground(new java.awt.Color(255, 255, 255));
        jButton32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Analytics.png"))); // NOI18N
        jButton32.setText("Quản Lí Thống Kê");
        jButton32.setToolTipText("");
        jButton32.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton32.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton32.setMargin(new java.awt.Insets(10, 14, 3, 14));
        jButton32.setPreferredSize(new java.awt.Dimension(170, 60));
        jButton32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton32ActionPerformed(evt);
            }
        });
        pMenu.add(jButton32);

        jButton33.setBackground(new java.awt.Color(51, 51, 51));
        jButton33.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jButton33.setForeground(new java.awt.Color(255, 255, 255));
        jButton33.setText("In-dev");
        jButton33.setToolTipText("");
        jButton33.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton33.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton33.setPreferredSize(new java.awt.Dimension(170, 60));
        pMenu.add(jButton33);

        jPanel9.add(pMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(-30, 120, 270, 620));

        lblThoiGian.setForeground(new java.awt.Color(255, 255, 255));
        lblThoiGian.setText("thoigian");
        lblThoiGian.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblThoiGian.setInheritsPopupMenu(false);
        jPanel9.add(lblThoiGian, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 800, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 1432, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        updateKhach();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        insertKhach();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void txtDiaChiKhachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiaChiKhachActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiaChiKhachActionPerformed

    private void txtTienThueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTienThueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTienThueActionPerformed

    private void txtNgayKetThucOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNgayKetThucOActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNgayKetThucOActionPerformed

    private void txtNgayKyHopDongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNgayKyHopDongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNgayKyHopDongActionPerformed

    private void txtNgayHetHopDongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNgayHetHopDongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNgayHetHopDongActionPerformed

    private void txtIdKhachHopDongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdKhachHopDongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdKhachHopDongActionPerformed

    private void txtIdPhongHopDongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdPhongHopDongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdPhongHopDongActionPerformed

    private void txtTienCocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTienCocActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTienCocActionPerformed

    private void txtThangThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtThangThanhToanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtThangThanhToanActionPerformed

    private void txtNgayThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNgayThanhToanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNgayThanhToanActionPerformed

    private void txtIdThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdThanhToanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdThanhToanActionPerformed

    private void txtTienDichVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTienDichVuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTienDichVuActionPerformed

    private void txtIdHopDongThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdHopDongThanhToanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdHopDongThanhToanActionPerformed

    private void txtTienNuocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTienNuocActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTienNuocActionPerformed

    private void txtTienPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTienPhongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTienPhongActionPerformed

    private void txtTienDienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTienDienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTienDienActionPerformed

    private void txtChucVuNguoiDungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtChucVuNguoiDungActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtChucVuNguoiDungActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
        deleteNguoiDung();
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
        updateNguoiDung();
    }//GEN-LAST:event_jButton15ActionPerformed

    private void tblPhongMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPhongMousePressed
        // TODO add your handling code here:
        int current = tblPhong.getSelectedRow();
        currentTablePhong(current);

    }//GEN-LAST:event_tblPhongMousePressed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jButton22ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jButton23ActionPerformed

    private void lblNguoiDungMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNguoiDungMousePressed
        // TODO add your handling code here:
        showUserInfo();
    }//GEN-LAST:event_lblNguoiDungMousePressed

    private void jLabel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MousePressed
        // TODO add your handling code here:
        ThongTinUngDung thongTinUngDung = new ThongTinUngDung();
        thongTinUngDung.setVisible(true);
    }//GEN-LAST:event_jLabel1MousePressed

    private void btnNguoiDungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNguoiDungActionPerformed
        // TODO add your handling code here:
        tbpCHUNG.setSelectedIndex(4);

    }//GEN-LAST:event_btnNguoiDungActionPerformed

    private void btnDichVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDichVuActionPerformed
        // TODO add your handling code here:
        tbpCHUNG.setSelectedIndex(5);

    }//GEN-LAST:event_btnDichVuActionPerformed

    private void btnNguoiOCungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNguoiOCungActionPerformed
        // TODO add your handling code here:
        tbpCHUNG.setSelectedIndex(6);

    }//GEN-LAST:event_btnNguoiOCungActionPerformed

    private void btnPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPhongActionPerformed
        // TODO add your handling code here:
        tbpCHUNG.setSelectedIndex(0);

    }//GEN-LAST:event_btnPhongActionPerformed

    private void btnKhachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhachActionPerformed
        // TODO add your handling code here:
        tbpCHUNG.setSelectedIndex(1);

    }//GEN-LAST:event_btnKhachActionPerformed

    private void btnHopDongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHopDongActionPerformed
        // TODO add your handling code here:
        tbpCHUNG.setSelectedIndex(2);

    }//GEN-LAST:event_btnHopDongActionPerformed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        // TODO add your handling code here:
        tbpCHUNG.setSelectedIndex(3);

    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void tblKhachMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhachMousePressed
        // TODO add your handling code here:
        int current = tblKhach.getSelectedRow();
        currentTableKhach(current);
    }//GEN-LAST:event_tblKhachMousePressed

    private void lblHinhAnhKhachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHinhAnhKhachMouseClicked
        // TODO add your handling code here:
        openFileChooser();
    }//GEN-LAST:event_lblHinhAnhKhachMouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        int yesNo = JOptionPane.showConfirmDialog(this, "Đăng Xuất ?", "Thông báo", JOptionPane.YES_NO_OPTION);
        if (yesNo == JOptionPane.YES_OPTION) {
            dispose();
            hienDangNhapJFrame();
        }
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        insertPhong();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        deletePhong();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        updatePhong();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton32ActionPerformed
        // TODO add your handling code here:
        tbpQuanLiThongKe.setSelectedIndex(0);
    }//GEN-LAST:event_jButton32ActionPerformed

    private void dfgđggMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dfgđggMousePressed
        // TODO add your handling code here:

    }//GEN-LAST:event_dfgđggMousePressed

    private void tblDichVuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDichVuMousePressed
        // TODO add your handling code here:
        int current = tblDichVu.getSelectedRow();
        currentTableDichVu(current);
    }//GEN-LAST:event_tblDichVuMousePressed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
        insertDichVu();
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        // TODO add your handling code here:
        deleteDichVu();
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        // TODO add your handling code here:
        updateDichVu();
    }//GEN-LAST:event_jButton18ActionPerformed

    private void tblThanhToanMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblThanhToanMousePressed
        // TODO add your handling code here:
        int current = tblThanhToan.getSelectedRow();
        currentTableThanhToan(current);

    }//GEN-LAST:event_tblThanhToanMousePressed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        updateThanhToan();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        insertThanhToan();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        deleteThanhToan();
    }//GEN-LAST:event_jButton12ActionPerformed

    private void tblNguoiOCungMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNguoiOCungMousePressed
        // TODO add your handling code here:
        int current = tblNguoiOCung.getSelectedRow();
        currentTableNguoiOCung(current);
    }//GEN-LAST:event_tblNguoiOCungMousePressed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        // TODO add your handling code here:
        insertNguoiOCung();
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        // TODO add your handling code here:
        deleteNguoiOCung();
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        // TODO add your handling code here:
        updateNguoiOCung();
    }//GEN-LAST:event_jButton21ActionPerformed

    private void tblHopDongMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHopDongMousePressed
        // TODO add your handling code here:
        int current = tblHopDong.getSelectedRow();
        currentTableHopDong(current);
    }//GEN-LAST:event_tblHopDongMousePressed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        insertHopDong();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        deleteHopDong();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        updateHopDong();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void tblNguoiDungMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNguoiDungMousePressed
        int current = tblNguoiDung.getSelectedRow();
        currentTableNguoiDung(current);
    }//GEN-LAST:event_tblNguoiDungMousePressed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        insertNguoiDung();
    }//GEN-LAST:event_jButton13ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            UIManager.setLookAndFeel(new FlatMacLightLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PhongTroJFrame().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDichVu;
    private javax.swing.JButton btnHopDong;
    private javax.swing.JButton btnKhach;
    private javax.swing.JButton btnNguoiDung;
    private javax.swing.JButton btnNguoiOCung;
    private javax.swing.JButton btnPhong;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JComboBox<String> cboGioiTinhKhach;
    private javax.swing.JScrollPane dfgđgg;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLabel lblChucVu;
    private javax.swing.JLabel lblHinhAnhDichVu;
    private javax.swing.JLabel lblHinhAnhKhach;
    private javax.swing.JLabel lblHinhAnhNguoiOCung;
    private javax.swing.JLabel lblNguoiDung;
    private javax.swing.JLabel lblThoiGian;
    private javax.swing.JPanel pHeader;
    private javax.swing.JPanel pMenu;
    private javax.swing.JScrollPane spCapNhatPhong;
    private javax.swing.JScrollPane spXemDanhSachPhong;
    private javax.swing.JTable tblDichVu;
    private javax.swing.JTable tblHopDong;
    private javax.swing.JTable tblKhach;
    private javax.swing.JTable tblNguoiDung;
    private javax.swing.JTable tblNguoiOCung;
    private javax.swing.JTable tblPhong;
    private javax.swing.JTable tblThanhToan;
    private javax.swing.JTabbedPane tbpCHUNG;
    private javax.swing.JTabbedPane tbpQuanLiDichVu;
    private javax.swing.JTabbedPane tbpQuanLiHopDong;
    private javax.swing.JTabbedPane tbpQuanLiKhach;
    private javax.swing.JTabbedPane tbpQuanLiNguoiDung;
    private javax.swing.JTabbedPane tbpQuanLiNguoiOCung;
    private javax.swing.JTabbedPane tbpQuanLiPhong;
    private javax.swing.JTabbedPane tbpQuanLiThanhToan;
    private javax.swing.JTabbedPane tbpQuanLiThongKe;
    private javax.swing.JTextField txtCCCDKhach;
    private javax.swing.JTextField txtCCCDNguoiOCung;
    private javax.swing.JTextField txtChucVuNguoiDung;
    private javax.swing.JTextField txtDiaChiKhach;
    private javax.swing.JTextField txtDiaChiNguoiDung;
    private javax.swing.JTextField txtDiaChiNguoiOCung;
    private javax.swing.JTextField txtDientich;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtEmailKhach;
    private javax.swing.JTextField txtEmailNguoiDung;
    private javax.swing.JTextField txtEmailNguoiOCung;
    private javax.swing.JTextArea txtGhiChu;
    private javax.swing.JTextField txtGiaThue;
    private javax.swing.JTextField txtGioiTinhNguoiOCung;
    private javax.swing.JTextField txtHoKhach;
    private javax.swing.JTextField txtHoNguoiDung;
    private javax.swing.JTextField txtHoNguoiOCung;
    private javax.swing.JTextField txtIDPhong;
    private javax.swing.JTextField txtIdDichVu;
    private javax.swing.JTextField txtIdHopDong;
    private javax.swing.JTextField txtIdHopDongNguoiDung;
    private javax.swing.JTextField txtIdHopDongThanhToan;
    private javax.swing.JTextField txtIdKhach;
    private javax.swing.JTextField txtIdKhachHopDong;
    private javax.swing.JTextField txtIdKhachNguoiOCung;
    private javax.swing.JTextField txtIdNguoiDung;
    private javax.swing.JTextField txtIdNguoiOCung;
    private javax.swing.JTextField txtIdPhongDichVu;
    private javax.swing.JTextField txtIdPhongHopDong;
    private javax.swing.JTextField txtIdThanhToan;
    private javax.swing.JTextField txtMatKhauNguoiDung;
    private javax.swing.JTextField txtNamDichVu;
    private javax.swing.JTextField txtNgayBatDauO;
    private javax.swing.JTextField txtNgayDichVu;
    private javax.swing.JTextField txtNgayHetHopDong;
    private javax.swing.JTextField txtNgayKetThucO;
    private javax.swing.JTextField txtNgayKyHopDong;
    private javax.swing.JTextField txtNgayThanhToan;
    private javax.swing.JTextField txtPhuongThucThanhToan;
    private javax.swing.JTextField txtSDTKhach;
    private javax.swing.JTextField txtSDTNguoiOCung;
    private javax.swing.JTextField txtSoGiuong;
    private javax.swing.JTextField txtSoPhong;
    private javax.swing.JTextField txtTenDangNhapNguoiDung;
    private javax.swing.JTextField txtTenDichVu;
    private javax.swing.JTextField txtTenKhach;
    private javax.swing.JTextField txtTenNguoiDung;
    private javax.swing.JTextField txtTenNguoiOCung;
    private javax.swing.JTextField txtThangThanhToan;
    private javax.swing.JTextField txtTienCoc;
    private javax.swing.JTextField txtTienDichVu;
    private javax.swing.JTextField txtTienDien;
    private javax.swing.JTextField txtTienNuoc;
    private javax.swing.JTextField txtTienPhong;
    private javax.swing.JTextField txtTienThue;
    private javax.swing.JTextField txtTrangThaiDichVu;
    private javax.swing.JTextField txtTrangThaiNguoiDung;
    private javax.swing.JTextField txtTrangThaiPhong;
    // End of variables declaration//GEN-END:variables
}
