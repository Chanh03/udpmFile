/*
 * Hello This Is Vanh
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Chanh Chanh
 */
public class QuenMatKhauEntity {
    private int maXacNhan;
    private int matKhauMoi;
    private int xacNhanMatKhauMoi;
    private String emailUser;

    public QuenMatKhauEntity() {
    }

    public QuenMatKhauEntity(int maXacNhan, int matKhauMoi, int xacNhanMatKhauMoi, String emailUser) {
        this.maXacNhan = maXacNhan;
        this.matKhauMoi = matKhauMoi;
        this.xacNhanMatKhauMoi = xacNhanMatKhauMoi;
        this.emailUser = emailUser;
    }

    public int getMaXacNhan() {
        return maXacNhan;
    }

    public void setMaXacNhan(int maXacNhan) {
        this.maXacNhan = maXacNhan;
    }

    public int getMatKhauMoi() {
        return matKhauMoi;
    }

    public void setMatKhauMoi(int matKhauMoi) {
        this.matKhauMoi = matKhauMoi;
    }

    public int getXacNhanMatKhauMoi() {
        return xacNhanMatKhauMoi;
    }

    public void setXacNhanMatKhauMoi(int xacNhanMatKhauMoi) {
        this.xacNhanMatKhauMoi = xacNhanMatKhauMoi;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }
    
    
}
