/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entily.DichVuEntily;
import entily.HopDongEntily;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.JdbcHelper;

/**
 *
 * @author HOANG HIEN
 */
public class HopDongDAO extends PhongTro_ChungDAO<HopDongEntily, Object> {

    final String INSERT_SQL = "INSERT into HopDong (NgayBatDauO,NgayKetThucO,ID_HopDong,TienCoc,TienThue,NgayKyHopDong,NgayHetHopDong,GhiChu,ID_Phong,ID_Khach) values (?,?,?,?,?,?,?,?,?,?)";
    final String UPDATE_SQL = "UPDATE HopDong set NgayBatDauO = ?, NgayKetThucO = ?, TienCoc = ?, TienThue = ?, NgayKyHopDong = ?, NgayHetHopDong = ?, GhiChu = ? , ID_Phong = ? , ID_Khach = ?  where ID_HopDong = ?";
    final String DELETE_SQL = "DELETE HopDong where ID_HopDong = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM HopDong";
    final String SELECT_BY_ID_ALL_SQL = "SELECT * FROM HopDong where ID_HopDong = ?";

    @Override
    public void insert(HopDongEntily entity) {
        JdbcHelper.update(INSERT_SQL, entity.getNgayBatDauO(), entity.getNgayKetThucO(), entity.getID_HopDong(), entity.getTienCoc(), entity.getTienThue(), entity.getNgayKyHopDong(), entity.getNgayHetHopDong(), entity.getGhiChu(), entity.getID_Phong(), entity.getID_Khach());
    }

    @Override
    public void update(HopDongEntily entity) {
        JdbcHelper.update(UPDATE_SQL, entity.getNgayBatDauO(), entity.getNgayKetThucO(), entity.getTienCoc(), entity.getTienThue(), entity.getNgayKyHopDong(), entity.getNgayHetHopDong(), entity.getGhiChu(), entity.getID_Phong(), entity.getID_Khach(), entity.getID_HopDong());
    }

    @Override
    public void delete(Object id) {
        JdbcHelper.update(DELETE_SQL, id);
    }

    @Override
    public List<HopDongEntily> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public HopDongEntily selectById(Object id) {
        List<HopDongEntily> list = selectBySql(SELECT_BY_ID_ALL_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<HopDongEntily> selectBySql(String sql, Object... args) {
        List<HopDongEntily> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            int i = 1;
            while (rs.next()) {
                HopDongEntily hd = new HopDongEntily();
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
