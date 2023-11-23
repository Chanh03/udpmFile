/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.PhongTroEntity;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import util.JdbcHelper;

/**
 *
 * @author Trong Phuc
 */
public class PhongTroDAO extends PhongTroChungDAO<PhongTroEntity, Object> {

    final String INSERT_SQL = "INSERT into PhongTro (ID_Phong, SoPhong, Dientich, SoGiuong, GiaThue, TrangThai) values (?,?,?,?,?,?)";
    final String UPDATE_SQL = "UPDATE PhongTro set SoPhong = ?, Dientich = ?, SoGiuong = ?, GiaThue = ?, TrangThai = ? where ID_Phong = ?";
    final String DELETE_SQL = "DELETE PhongTro where ID_Phong = ?";
    final String SELECT_ALL_SQL = "SELECT ID_Phong, SoPhong, Dientich, SoGiuong, GiaThue, TrangThai FROM PhongTro";
    final String SELECT_BY_ID_ALL_SQL = "SELECT * FROM PhongTro where ID_Phong = ?";

    @Override
    public void insert(PhongTroEntity entity) {
        JdbcHelper.update(INSERT_SQL, entity.getID_Phong(), entity.getSoPhong(), entity.getDientich(), entity.getSoGiuong(), entity.getGiaThue(), entity.getTrangThai());
    }

    @Override
    public void update(PhongTroEntity entity) {
        JdbcHelper.update(UPDATE_SQL, entity.getSoPhong(), entity.getDientich(), entity.getSoGiuong(), entity.getGiaThue(), entity.getTrangThai(), entity.getID_Phong());
    }

    @Override
    public void delete(Object id) {
        JdbcHelper.update(DELETE_SQL, id);
    }

    @Override
    public List<PhongTroEntity> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public PhongTroEntity selectById(Object id) {
        List<PhongTroEntity> list = selectBySql(SELECT_BY_ID_ALL_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<PhongTroEntity> selectBySql(String sql, Object... args) {
        List<PhongTroEntity> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql);
            int i = 1;
            while (rs.next()) {
                PhongTroEntity pt = new PhongTroEntity();
                pt.setSoThuTu(i);
                pt.setID_Phong(rs.getString("ID_Phong"));
                pt.setSoPhong(rs.getInt("SoPhong"));
                pt.setDientich(rs.getInt("Dientich"));
                pt.setSoGiuong(rs.getInt("SoGiuong"));
                pt.setGiaThue(rs.getInt("GiaThue"));
                pt.setTrangThai(rs.getString("TrangThai"));
                list.add(pt);
                i++;
            }
        } catch (Exception e) {
        }
        return list;
    }

}
