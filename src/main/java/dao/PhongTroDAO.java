/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entily.PhongTroEntily;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.JdbcHelper;

/**
 *
 * @author Trong Phuc
 */
public class PhongTroDAO extends PhongTro_ChungDAO<PhongTroEntily, Object> {

    final String INSERT_SQL = "INSERT into PhongTro (ID_Phong, SoPhong, Dientich, SoGiuong, GiaThue, TrangThai) values (?,?,?,?,?,?)";
    final String UPDATE_SQL = "UPDATE PhongTro set SoLuong = ?, Dientich = ?, SoGiuong = ?, GiaThue = ?, TrangThai = ? where ID_Phong = ?";
    final String DELETE_SQL = "DELETE PhongTro where ID_Phong = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM NguoiDung";
    final String SELECT_BY_ID_ALL_SQL = "SELECT * FROM PhongTro where ID_Phong = ?";

    @Override
    public void insert(PhongTroEntily entity) {
        JdbcHelper.update(INSERT_SQL, entity.getID_Phong(), entity.getSoPhong(), entity.getDientich(), entity.getSoGiuong(), entity.getGiaThue(), entity.getTrangThai());
    }

    @Override
    public void update(PhongTroEntily entity) {
        JdbcHelper.update(UPDATE_SQL, entity.getSoPhong(), entity.getDientich(), entity.getSoGiuong(), entity.getGiaThue(), entity.getTrangThai(), entity.getID_Phong());
    }

    @Override
    public void delete(Object id) {
        JdbcHelper.update(INSERT_SQL, id);
    }

    @Override
    public List<PhongTroEntily> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public PhongTroEntily selectById(Object id) {
        List<PhongTroEntily> list = selectBySql(SELECT_BY_ID_ALL_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<PhongTroEntily> selectBySql(String sql, Object... args) {
        List<PhongTroEntily> list = new ArrayList<>();
        try {
            sql = "SELECT ROW_NUMBER() OVER (ORDER BY ID_Phong) AS SoThuTu, * FROM PhongTro";
            ResultSet rs = JdbcHelper.query(sql);
            while (rs.next()) {
                PhongTroEntily pt = new PhongTroEntily();
                pt.setSoThuTu(rs.getInt("SoThuTu"));
                pt.setID_Phong(rs.getString("ID_Phong"));
                pt.setSoPhong(rs.getInt("SoPhong"));
                pt.setDientich(rs.getInt("Dientich"));
                pt.setSoGiuong(rs.getInt("SoGiuong"));
                pt.setGiaThue(rs.getInt("GiaThue"));
                pt.setTrangThai(rs.getString("TrangThai"));
                list.add(pt);
            }
        } catch (Exception e) {
        }
        return list;
    }

}
