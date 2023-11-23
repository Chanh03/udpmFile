/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.ThanhToanEntity;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import util.JdbcHelper;

/**
 *
 * @author HOANG HIEN
 */
public class ThanhToanDAO  extends PhongTroChungDAO<ThanhToanEntity, Object> {
    final String INSERT_SQL = "INSERT into ThanhToan (ID_ThanhToan,NgayThanhToan,TienPhong,PhuongThucThanhToan,ThangThanhToan,TienDien,TienNuoc,TienDichVu,ID_HopDong) values (?,?,?,?,?,?,?,?,?)";
    final String UPDATE_SQL = "UPDATE ThanhToan set NgayThanhToan = ?, TienPhong = ?, PhuongThucThanhToan = ?, ThangThanhToan = ?, TienDien = ?, TienNuoc = ?, TienDichVu = ?, ID_HopDong = ? where ID_ThanhToan = ?";
    final String DELETE_SQL = "DELETE ThanhToan where ID_ThanhToan = ?";
    final String SELECT_ALL_SQL = "SELECT ID_ThanhToan, NgayThanhToan, TienPhong, PhuongThucThanhToan, ThangThanhToan, TienDien, TienNuoc, TienDichVu, ID_HopDong FROM ThanhToan";
    final String SELECT_BY_ID_ALL_SQL = "SELECT * FROM ThanhToan where ID_ThanhToan = ?";

    @Override
    public void insert(ThanhToanEntity entity) {
        JdbcHelper.update(INSERT_SQL, entity.getID_ThanhToan(), entity.getNgayThanhToan(), entity.getTienPhong(), entity.getPhuongThucThanhToan(), entity.getThangThanhToan(), entity.getTienDien(), entity.getTienNuoc(), entity.getTienDichVu(), entity.getID_HopDong());
    }

    @Override
    public void update(ThanhToanEntity entity) {
        JdbcHelper.update(UPDATE_SQL, entity.getNgayThanhToan(), entity.getTienPhong(), entity.getPhuongThucThanhToan(), entity.getThangThanhToan(), entity.getTienDien(), entity.getTienNuoc(), entity.getTienDichVu(), entity.getID_HopDong(), entity.getID_ThanhToan());
    }

    @Override
    public void delete(Object id) {
        JdbcHelper.update(DELETE_SQL, id);
    }

    @Override
    public List<ThanhToanEntity> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public ThanhToanEntity selectById(Object id) {
List<ThanhToanEntity> list = selectBySql(SELECT_BY_ID_ALL_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);    }

    @Override
    public List<ThanhToanEntity> selectBySql(String sql, Object... args) {
 List<ThanhToanEntity> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            int i = 1;
            while (rs.next()) {           
                ThanhToanEntity tt = new ThanhToanEntity();
                tt.setSoThuTu(i);
                tt.setID_ThanhToan(rs.getString("ID_ThanhToan"));
                tt.setNgayThanhToan(rs.getDate("NgayThanhToan"));
                tt.setTienPhong(rs.getString("TienPhong"));
                tt.setPhuongThucThanhToan(rs.getString("PhuongThucThanhToan"));
                tt.setThangThanhToan(rs.getDate("ThangThanhToan"));
                tt.setTienDien(rs.getString("TienDien"));
                tt.setTienNuoc(rs.getString("TienNuoc"));
                tt.setTienDichVu(rs.getString("TienDichVu"));
                tt.setID_HopDong(rs.getString("ID_HopDong"));
               
                list.add(tt);
                i++;
            }
        } catch (Exception e) {
        }
        return list;
    }    }

