/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.NguoiDungEntity;
import java.util.ArrayList;
import java.util.List;
import util.JdbcHelper;
import java.sql.ResultSet;

/**
 *
 * @author Trong Phuc
 */
public class NguoiDungDAO extends PhongTroChungDAO<NguoiDungEntity, Object> {

    final String INSERT_SQL = "INSERT into NguoiDung (ChucVu,ID_NguoiDung,Matkhau,TenDangNhap,Ho,Ten,Trang_Thai,Email,DiaChi,ID_HopDong) values (?,?,?,?,?,?,?,?,?,?)";
    final String UPDATE_SQL = "UPDATE NguoiDung set ChucVu = ?, Matkhau = ?, TenDangNhap = ?, Ho = ?, Ten = ?, Trang_Thai = ?, Email = ?, DiaChi = ?, ID_HopDong = ? where ID_NguoiDung = ?";
    final String DELETE_SQL = "DELETE NguoiDung where ID_NguoiDung = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM NguoiDung";
    final String SELECT_BY_ID_ALL_SQL = "SELECT * FROM NguoiDung where TenDangNhap = ?";
    final String SELECT_BY_GMAIL_SQL = "SELECT * FROM NguoiDung WHERE Email = ?";
    final String UPDATE_PASSWORD_SQL = "UPDATE NguoiDung SET Matkhau = ? WHERE Email = ?";

    @Override
    public void insert(NguoiDungEntity entity) {
        JdbcHelper.update(INSERT_SQL, entity.getChucVu(), entity.getID_NguoiDung(), entity.getMatkhau(), entity.getTenDangNhap(), entity.getHo(), entity.getTen(), entity.getTrang_Thai(), entity.getEmail(), entity.getDiaChi(), entity.getID_HopDong());
    }

    @Override
    public void update(NguoiDungEntity entity) {
        JdbcHelper.update(UPDATE_SQL, entity.getChucVu(), entity.getMatkhau(), entity.getTenDangNhap(), entity.getHo(), entity.getTen(), entity.getTrang_Thai(), entity.getEmail(), entity.getDiaChi(), entity.getID_HopDong(), entity.getID_NguoiDung());
    }

    @Override
    public void delete(Object id) {
        JdbcHelper.update(DELETE_SQL, id);
    }

    @Override
    public List<NguoiDungEntity> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public NguoiDungEntity selectById(Object id) {
        List<NguoiDungEntity> list = selectBySql(SELECT_BY_ID_ALL_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<NguoiDungEntity> selectBySql(String sql, Object... args) {
        List<NguoiDungEntity> list = new ArrayList<>();
        int i = 1;
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                NguoiDungEntity nd = new NguoiDungEntity();
                nd.setSoThuTu(i);
                nd.setChucVu(rs.getString("ChucVu"));
                nd.setID_NguoiDung(rs.getString("ID_NguoiDung"));
                nd.setMatkhau(rs.getString("MatKhau"));
                nd.setTenDangNhap(rs.getString("TenDangNhap"));
                nd.setHo(rs.getString("Ho"));
                nd.setTen(rs.getString("Ten"));
                nd.setTrang_Thai(rs.getString("Trang_Thai"));
                nd.setEmail(rs.getString("Email"));
                nd.setDiaChi(rs.getString("DiaChi"));
                nd.setID_HopDong(rs.getString("ID_HopDong"));
                list.add(nd);
                i++;
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<NguoiDungEntity> selectByEmail(String email) {
        return selectBySql(SELECT_BY_GMAIL_SQL, email);
    }

    public void updatePasswordByEmail(String email, String matkhau) {
        JdbcHelper.update(UPDATE_PASSWORD_SQL, matkhau, email);
    }
}
