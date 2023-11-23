package dao;

import entity.DichVuEntity;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import util.JdbcHelper;

/**
 *
 * @author HOANG HIEN
 */
public class DichVuDAO extends PhongTroChungDAO<DichVuEntity, Object> {

    final String INSERT_SQL = "INSERT into DichVu (ID_DichVu,TenDichVu,DonGia,Ngay,Nam,HinhAnh,TrangThai,ID_Phong) values (?,?,?,?,?,?,?,?)";
    final String UPDATE_SQL = "UPDATE DichVu set TenDichVu = ?, DonGia = ?, Ngay = ?, Nam = ?, TrangThai = ?, ID_Phong = ? where ID_DichVu = ?";
    final String DELETE_SQL = "DELETE NguoiDung where ID_DichVu = ?";
    final String SELECT_ALL_SQL = "select ID_DichVu,TenDichVu,DonGia,Ngay,Nam,HinhAnh,TrangThai,ID_Phong from DichVu";
    final String SELECT_BY_ID_ALL_SQL = "SELECT * FROM DichVu where ID_DichVu = ?";

    @Override
    public void insert(DichVuEntity entity) {
        JdbcHelper.update(INSERT_SQL, entity.getID_DichVu(), entity.getTenDichVu(), entity.getDonGia(), entity.getNgay(), entity.getNam(), entity.getHinhAnh(), entity.getTrangThai(), entity.getID_Phong());
    }

    @Override
    public void update(DichVuEntity entity) {
        JdbcHelper.update(UPDATE_SQL, entity.getTenDichVu(), entity.getDonGia(), entity.getNgay(), entity.getNam(), entity.getTrangThai(), entity.getID_Phong(), entity.getID_DichVu());
    }

    @Override
    public void delete(Object id) {
        JdbcHelper.update(DELETE_SQL, id);
    }

    @Override
    public List<DichVuEntity> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    public DichVuEntity selectById(Object id) {
        List<DichVuEntity> list = selectBySql(SELECT_BY_ID_ALL_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<DichVuEntity> selectBySql(String sql, Object... args) {
        List<DichVuEntity> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            int i = 1;
            while (rs.next()) {
                DichVuEntity dv = new DichVuEntity();
                dv.setSoThuTu(i);
                dv.setID_DichVu(rs.getString("ID_DichVu"));
                dv.setTenDichVu(rs.getString("TenDichVu"));
                dv.setDonGia(rs.getString("DonGia"));
                dv.setNgay(rs.getDate("Ngay"));
                dv.setNam(rs.getDate("Nam"));
                dv.setHinhAnh(rs.getString("HinhAnh"));
                dv.setTrangThai(rs.getString("TrangThai"));
                dv.setID_Phong(rs.getString("ID_Phong"));

                list.add(dv);
                i++;
            }
        } catch (Exception e) {
        }
        return list;
    }

}
