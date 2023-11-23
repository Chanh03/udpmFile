/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.KhachThueEntity;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import util.JdbcHelper;

public class KhachThueDAO extends PhongTroChungDAO<KhachThueEntity, Object> {

    final String INSERT_SQL = "INSERT into KhachThue (ID_Khach, Ho, CCCD, GioiTinh, SoDienThoai, Email, Ten, HinhAnh, DiaChi) values (?,?,?,?,?,?,?,?,?)";
    final String UPDATE_SQL = "UPDATE KhachThue set Ho = ?, CCCD = ?, GioiTinh = ?, SoDienThoai = ?, Email = ?, Ten = ?, HinhAnh = ?, DiaChi = ? where ID_Khach = ?";
    final String DELETE_SQL = "DELETE KhachThue where ID_Khach = ?";
    final String SELECT_ALL_SQL = "SELECT ID_Khach, Ho, CCCD, GioiTinh, SoDienThoai, Email, Ten, HInhAnh, DiaChi FROM KhachThue";
    final String SELECT_BY_ID_ALL_SQL = "SELECT * FROM PhongTro where ID_Khach = ?";

    @Override
    public void insert(KhachThueEntity entity) {
        JdbcHelper.update(INSERT_SQL, entity.getID_khach(), entity.getHo(), entity.getCCCD(), entity.getGioiTinh(), entity.getSoDienThoai(), entity.getTen(), entity.getEmail(), entity.getDiaChi(), entity.getHinhAnh());
    }

    @Override
    public void update(KhachThueEntity entity) {
        JdbcHelper.update(UPDATE_SQL, entity.getHo(), entity.getCCCD(), entity.getGioiTinh(), entity.getSoDienThoai(), entity.getEmail(), entity.getTen(), entity.getHinhAnh(), entity.getDiaChi(), entity.getID_khach());
    }

    @Override
    public void delete(Object id) {
        JdbcHelper.update(DELETE_SQL, id);
    }

    @Override
    public List<KhachThueEntity> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public KhachThueEntity selectById(Object id) {
        List<KhachThueEntity> list = selectBySql(SELECT_BY_ID_ALL_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<KhachThueEntity> selectBySql(String sql, Object... args) {
        List<KhachThueEntity> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            int i = 1;
            while (rs.next()) {
                KhachThueEntity pt = new KhachThueEntity();
                pt.setSoThuThu(i);
                pt.setID_khach(rs.getString("ID_Khach"));
                pt.setHo(rs.getString("Ho"));
                pt.setCCCD(rs.getString("CCCD"));
                if (rs.getString("GioiTinh").equalsIgnoreCase("1")) {
                    pt.setGioiTinh("Nam");
                } else {
                    pt.setGioiTinh("Nữ");
                }
                pt.setSoDienThoai(rs.getString("SoDienThoai"));
                pt.setEmail(rs.getString("Email"));
                pt.setTen(rs.getString("Ten"));
                pt.setHinhAnh(rs.getString("HinhAnh"));
                pt.setDiaChi(rs.getString("DiaChi"));
                list.add(pt);
                i++;
            }
        } catch (Exception e) {
        }
        return list;
    }

}
