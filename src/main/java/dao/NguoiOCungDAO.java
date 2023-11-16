/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entily.NguoiOCungEntily;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.JdbcHelper;

/**
 *
 * @author HOANG HIEN
 */
public class NguoiOCungDAO extends PhongTro_ChungDAO<NguoiOCungEntily, Object> {

    final String INSERT_SQL = " INSERT into NguoiOCung (Ten,Ho,ID_NguoiOCung,Email,HinhAnh,SoDienThoai,GioiTinh,CCCD,DiaChi,ID_Khach) values (?,?,?,?,?,?,?,?,?,?)";
    final String UPDATE_SQL = "UPDATE NguoiOCung set Ten = ?, Ho = ?,  Email = ?, HinhAnh = ?, SoDienThoai = ?, GioiTinh = ?, CCCD = ?, DiaChi = ?, ID_Khach = ? where ID_NguoiOCung = ?";
    final String DELETE_SQL = "DELETE NguoiOCung where ID_NguoiOCung = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM NguoiOCung";
    final String SELECT_BY_ID_ALL_SQL = "SELECT * FROM NguoiOCung where ID_NguoiOCung = ?";

    @Override
    public void insert(NguoiOCungEntily entity) {
        JdbcHelper.update(INSERT_SQL, entity.getHo(), entity.getTen(), entity.getID_NguoiOCung(), entity.getEmail(), entity.getHinhAnh(), entity.getSoDienThoai(), entity.getGioiTinh(), entity.getCCCD(), entity.getDiaChi(), entity.getID_Khach());
    }

    @Override
    public void update(NguoiOCungEntily entity) {
        JdbcHelper.update(UPDATE_SQL, entity.getHo(), entity.getTen(), entity.getID_NguoiOCung(), entity.getEmail(), entity.getHinhAnh(), entity.getSoDienThoai(), entity.getGioiTinh(), entity.getCCCD(), entity.getDiaChi(), entity.getID_Khach());
    }

    public void delete(Object id) {
        JdbcHelper.update(INSERT_SQL, id);
    }

    public List<NguoiOCungEntily> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public NguoiOCungEntily selectById(Object id) {
        List<NguoiOCungEntily> list = selectBySql(SELECT_BY_ID_ALL_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    /**
     *
     * @param sql
     * @param args
     * @return
     */
    public List<NguoiOCungEntily> selectBySql(String sql, Object... args) {
        List<NguoiOCungEntily> list = new ArrayList<>();
        try {

            ResultSet rs = JdbcHelper.query(sql);
            int i = 1;
            while (rs.next()) {
                NguoiOCungEntily noc = new NguoiOCungEntily();
                noc.setSoThuTu(i);
                noc.setHo(rs.getString("Ho"));
                noc.setTen(rs.getString("Ten"));
                noc.setID_NguoiOCung(rs.getString("ID_NguoiOCung"));
                noc.setEmail(rs.getString("Email"));
                noc.setHinhAnh(rs.getString("HinhAnh"));
                noc.setSoDienThoai(rs.getString("SoDienThoai"));
                if (rs.getString("GioiTinh").equalsIgnoreCase("1")) {
                    noc.setGioiTinh("Nam");
                } else {
                    noc.setGioiTinh("Nữ");
                }
                noc.setCCCD(rs.getString("CCCD"));
                noc.setDiaChi(rs.getString("DiaChi"));
                noc.setID_Khach(rs.getString("ID_Khach"));
                list.add(noc);
                
                i++;
            }
        } catch (Exception e) {
            
        }
        return list;
    }
}
