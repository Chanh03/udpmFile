/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entily.KhachThueEntily;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.JdbcHelper;


public class KhachThueDAO extends PhongTro_ChungDAO<KhachThueEntily, Object>{

    final String INSERT_SQL = "INSERT into KhachThue (ID_Khach, Ho, CCCD, GioiTinh, SoDienThoai, Email, Ten, HinhAnh, DiaChi) values (?,?,?,?,?,?,?,?,?)";
    final String UPDATE_SQL = "UPDATE KhachThue set Ho = ?, CCCD = ?, GioiTinh = ?, SoDienThoai = ?, Email = ?, Ten = ?, HinhAnh = ?, DiaChi = ? where ID_Khach = ?";
    final String DELETE_SQL = "DELETE KhachThue where ID_Khach = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM PhongTro";
    final String SELECT_BY_ID_ALL_SQL = "SELECT * FROM PhongTro where ID_Phong = ?";
    
    @Override
    public void insert(KhachThueEntily entity) {
        JdbcHelper.update(INSERT_SQL, entity.getID_khach(), entity.getHo(),entity.getCCCD(),entity.getGioiTinh(),entity.getSoDienThoai(),entity.getTen(),entity.getEmail(),entity.getDiaChi(),entity.getHinhAnh());
    }

    @Override
    public void update(KhachThueEntily entity) {
        JdbcHelper.update(INSERT_SQL, entity.getID_khach(), entity.getHo(),entity.getCCCD(),entity.getGioiTinh(),entity.getSoDienThoai(),entity.getTen(),entity.getEmail(),entity.getDiaChi(),entity.getHinhAnh());
    }

    @Override
    public void delete(Object id) {
          JdbcHelper.update(INSERT_SQL, id);
    }

    @Override
    public List<KhachThueEntily> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public KhachThueEntily selectById(Object id) {
        List<KhachThueEntily> list = selectBySql(SELECT_BY_ID_ALL_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<KhachThueEntily> selectBySql(String sql, Object... args) {
        List<KhachThueEntily> list = new ArrayList<>();
        try {
            sql = "SELECT ROW_NUMBER() OVER (ORDER BY ID_Khach) AS SoThuTu,* FROM KhachThue";
            ResultSet rs = JdbcHelper.query(sql, args);
            while(rs.next()){
                KhachThueEntily pt = new KhachThueEntily();
                pt.setSoThuThu(rs.getInt("SoThuTu"));
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
            }
        } catch (Exception e) {
        }
        return list;
 }
    
}