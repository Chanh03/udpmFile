/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.DichVuEntity;
import entity.HopDongEntity;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import util.JdbcHelper;

/**
 *
 * @author HOANG HIEN
 */
public class HopDongDAO extends PhongTroChungDAO<HopDongEntity, Object> {

    final String INSERT_SQL = "INSERT into HopDong (NgayBatDauO,NgayKetThucO,ID_HopDong,TienCoc,TienThue,NgayKyHopDong,NgayHetHopDong,GhiChu,ID_Phong,ID_Khach) values (?,?,?,?,?,?,?,?,?,?)";
    final String UPDATE_SQL = "UPDATE HopDong set NgayBatDauO = ?, NgayKetThucO = ?, TienCoc = ?, TienThue = ?, NgayKyHopDong = ?, NgayHetHopDong = ?, GhiChu = ? , ID_Phong = ? , ID_Khach = ?  where ID_HopDong = ?";
    final String DELETE_SQL = "DELETE HopDong where ID_HopDong = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM HopDong";
    final String SELECT_BY_ID_ALL_SQL = "SELECT * FROM HopDong where ID_HopDong = ?";

    @Override
    public void insert(HopDongEntity entity) {
        JdbcHelper.update(INSERT_SQL, entity.getNgayBatDauO(), entity.getNgayKetThucO(), entity.getID_HopDong(), entity.getTienCoc(), entity.getTienThue(), entity.getNgayKyHopDong(), entity.getNgayHetHopDong(), entity.getGhiChu(), entity.getID_Phong(), entity.getID_Khach());
    }

    @Override
    public void update(HopDongEntity entity) {
        JdbcHelper.update(UPDATE_SQL, entity.getNgayBatDauO(), entity.getNgayKetThucO(), entity.getTienCoc(), entity.getTienThue(), entity.getNgayKyHopDong(), entity.getNgayHetHopDong(), entity.getGhiChu(), entity.getID_Phong(), entity.getID_Khach(), entity.getID_HopDong());
    }

    @Override
    public void delete(Object id) {
        JdbcHelper.update(DELETE_SQL, id);
    }

    @Override
    public List<HopDongEntity> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public HopDongEntity selectById(Object id) {
        List<HopDongEntity> list = selectBySql(SELECT_BY_ID_ALL_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<HopDongEntity> selectBySql(String sql, Object... args) {
        List<HopDongEntity> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            int i = 1;
            while (rs.next()) {
                HopDongEntity hd = new HopDongEntity();
                hd.setSoThuTu(i);
                hd.setNgayBatDauO(rs.getString("NgayBatDauO"));
                hd.setNgayKetThucO(rs.getString("NgayKetThucO"));
                hd.setID_HopDong(rs.getString("ID_HopDong"));
                hd.setTienCoc(rs.getString("TienCoc"));
                hd.setTienThue(rs.getString("TienThue"));
                hd.setNgayKyHopDong(rs.getString("NgayKyHopDong"));
                hd.setNgayHetHopDong(rs.getString("NgayHetHopDong"));
                hd.setGhiChu(rs.getString("GhiChu"));
                hd.setID_Phong(rs.getString("ID_Phong"));
                hd.setID_Khach(rs.getString("ID_Khach"));

                list.add(hd);
                i++;
            }
        } catch (Exception e) {
        }
        return list;
    }

}
