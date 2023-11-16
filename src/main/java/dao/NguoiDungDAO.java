/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entily.NguoiDungEntily;
import java.util.ArrayList;
import java.util.List;
import utils.JdbcHelper;
import java.sql.ResultSet;

/**
 *
 * @author Trong Phuc
 */
public class NguoiDungDAO extends PhongTro_ChungDAO<NguoiDungEntily, Object> {

    final String INSERT_SQL = "INSERT into NguoiDung (ChucVu,ID_NguoiDung,Matkhau,TenDangNhap,Ho,Ten,Trang_Thai,Email,DiaChi,ID_HopDong) values (?,?,?,?,?,?,?,?,?,?)";
    final String UPDATE_SQL = "UPDATE NguoiDung set ChucVu = ?, Matkhau = ?, TenDangNhap = ?, Ho = ?, Ten = ?, Trang_Thai = ?, Email = ?, DiaChi = ?, ID_HopDong = ? where ID_NguoiDung = ?";
    final String DELETE_SQL = "DELETE NguoiDung where ID_NguoiDung = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM NguoiDung";
    final String SELECT_BY_ID_ALL_SQL = "SELECT * FROM NguoiDung where TenDangNhap = ?";

    @Override
    public void insert(NguoiDungEntily entity) {
        JdbcHelper.update(INSERT_SQL, entity.getChucVu(), entity.getID_NguoiDung(), entity.getMatkhau(), entity.getTenDangNhap(), entity.getHo(), entity.getTen(), entity.getTrang_Thai(), entity.getEmail(), entity.getDiaChi(), entity.getID_HopDong());
    }

    @Override
    public void update(NguoiDungEntily entity) {
        JdbcHelper.update(UPDATE_SQL, entity.getChucVu(), entity.getMatkhau(), entity.getTenDangNhap(), entity.getHo(), entity.getTen(), entity.getTrang_Thai(), entity.getEmail(), entity.getDiaChi(), entity.getID_HopDong(), entity.getID_NguoiDung());
    }

    @Override
    public void delete(Object id) {
        JdbcHelper.update(INSERT_SQL, id);
    }

    @Override
    public List<NguoiDungEntily> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public NguoiDungEntily selectById(Object id) {
        List<NguoiDungEntily> list = selectBySql(SELECT_BY_ID_ALL_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<NguoiDungEntily> selectBySql(String sql, Object... args) {
        List<NguoiDungEntily> list = new ArrayList<>();
        int i = 1;
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                NguoiDungEntily nd = new NguoiDungEntily();
                nd.setSoThuTu(i);
                nd.setChucVu(rs.getString("ChucVu"));
                nd.setID_NguoiDung(rs.getString("ID_NguoiDung"));
                nd.setMatkhau(rs.getString("Matkhau"));
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
}
