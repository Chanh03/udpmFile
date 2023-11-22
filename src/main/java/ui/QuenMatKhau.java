/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ui;

import dao.NguoiDungDAO;
import entity.NguoiDungEntity;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import util.XImage;

/**
 *
 * @author Trong Phuc
 */
public class QuenMatKhau extends javax.swing.JFrame {

    int maXacNhan;
    NguoiDungDAO nguoiDungDao = new NguoiDungDAO();
    Timer timer;

    /**
     * Creates new form QuenMatKhauUL
     */
    public QuenMatKhau() {
        initComponents();
        init();
        xacThucNguoiDungVaDoiMatKhau();
    }

    void init() {
        setLocationRelativeTo(null);
        setTitle("Quên Mật Khẩu");
        setIconImage(XImage.getAppIcon());
    }

    void updateCanhBao() {
        int i = 2000;
        timer = new Timer(i, new ActionListener() {
            int progress = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                // Dừng timer nếu đã hoàn thành
                lblDaGui.setText("");
                timer.stop();
            }
        });

        //chạy timer ở trên
        timer.start();
    }

    void updateCanhBaoKhongTonTai() {
        int i = 2000;
        timer = new Timer(i, new ActionListener() {
            int progress = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                // Dừng timer nếu đã hoàn thành
                lblDaGui.setText("Không Tồn Tại");
                timer.stop();
            }
        });

        //chạy timer ở trên
        timer.start();
    }

    void xacThucNguoiDungVaDoiMatKhau() {
        maXacNhan = (int) Math.round(Math.random() * 999999); // Làm tròn giá trị về số nguyên từ 0 đến 1000000

        btnGuiMa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtEmail.getText().equalsIgnoreCase("")) {
                    JOptionPane.showMessageDialog(null, "Nhập đầy đủ thông tin!");
                } else {
                    String emailUser = txtEmail.getText();
                    List<NguoiDungEntity> list = nguoiDungDao.selectByEmail(emailUser);
                    if (!list.isEmpty()) {
                        lblDaGui.setText("Đã Gửi");

                        String username = "anhnvps30934@fpt.edu.vn";
                        String password = "kavl rimm emhz tewg";

                        // Cài đặt thông số kết nối SMTP
                        Properties props = new Properties();
                        props.put("mail.smtp.auth", "true");
                        props.put("mail.smtp.starttls.enable", "true");
                        props.put("mail.smtp.host", "smtp.gmail.com");
                        props.put("mail.smtp.port", "587");
                        props.put("mail.smtp.ssl.protocols", "TLSv1.2"); // Thêm dòng này để chỉ định giao thức TLSv1.2 lỏ này fix khó vc

                        // Tạo phiên làm việc
                        Session session = Session.getInstance(props, new Authenticator() {
                            protected PasswordAuthentication getPasswordAuthentication() {
                                return new PasswordAuthentication(username, password);
                            }
                        });
                        String emailAdmin = "anhnvps30934@gmail.com";
                        String tenChuDe = "Mã xác nhận cho " + emailUser;
                        try {

                            // Tạo đối tượng MimeMessage
                            Message message = new MimeMessage(session);
                            // Đặt thông tin người gửi
                            message.setFrom(new InternetAddress(emailAdmin));
                            // Đặt thông tin người nhận
                            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailUser));
                            // Đặt chủ đề
                            message.setSubject(tenChuDe);
                            // Đặt nội dung tin nhắn
                            message.setText("Mã Xác Nhận : " + maXacNhan);
                            // Gửi tin nhắn
                            Transport.send(message);
                            System.out.println("Sended : " + tenChuDe);

                        } catch (MessagingException exception) {
                            exception.printStackTrace();
                        }

                    } else {
                        // Email không tồn tại trong cơ sở dữ liệu
                        updateCanhBaoKhongTonTai();
                    }
                }
            }
        });
        btnDoiMatKhau.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtMaXacNhan.getText().equalsIgnoreCase("")
                        || txtMatKhauMoi.getText().equalsIgnoreCase("")
                        || txtNhapLaiMatKhau.getText().equalsIgnoreCase("")) {
                    JOptionPane.showMessageDialog(null, "Nhập đầy đủ thông tin!");
                } else {
                    // Kiểm tra mã xác nhận & pass sau khi gửi
                    int nhapMaXacNhan = Integer.parseInt(txtMaXacNhan.getText());

                    if (nhapMaXacNhan == maXacNhan) {
                        // Mã xác nhận đúng
                        if (txtMatKhauMoi.getText().equalsIgnoreCase(txtNhapLaiMatKhau.getText())) {
                            String emailUser = txtEmail.getText();
                            //Chạy nguoidungdao phần cập nhật password bằng email đấy :D dễ hiểu lắm 
                            nguoiDungDao.updatePasswordByEmail(emailUser, txtMatKhauMoi.getText());
                            JOptionPane.showMessageDialog(null, "Đổi mật khẩu thành công !");
                        }

                    } else {
                        // Mã xác nhận sai
                        JOptionPane.showMessageDialog(null, "Xác nhận không thành công. Mã không đúng hoặc mật khẩu không trùng khớp!");
                    }
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtEmail = new javax.swing.JTextField();
        btnDoiMatKhau = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        lblCanhBao = new javax.swing.JLabel();
        lblDaGui = new javax.swing.JLabel();
        txtMatKhauMoi = new javax.swing.JTextField();
        txtNhapLaiMatKhau = new javax.swing.JTextField();
        txtMaXacNhan = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnGuiMa = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        txtEmail.setBackground(new java.awt.Color(239, 244, 255));
        txtEmail.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtEmail.setForeground(new java.awt.Color(138, 192, 240));
        txtEmail.setText("Nhập địa chỉ Gmail");
        txtEmail.setBorder(null);
        txtEmail.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtEmailFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtEmailFocusLost(evt);
            }
        });

        btnDoiMatKhau.setBackground(new java.awt.Color(138, 192, 240));
        btnDoiMatKhau.setForeground(new java.awt.Color(255, 255, 255));
        btnDoiMatKhau.setText("Đổi Mật Khẩu");
        btnDoiMatKhau.setBorder(null);
        btnDoiMatKhau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoiMatKhauActionPerformed(evt);
            }
        });

        btnExit.setText("Quay lại");
        btnExit.setBorder(null);
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setText("Quên mật khẩu");

        lblCanhBao.setForeground(new java.awt.Color(255, 0, 0));

        txtMatKhauMoi.setText("Nhập mật khẩu mới");
        txtMatKhauMoi.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtMatKhauMoiFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtMatKhauMoiFocusLost(evt);
            }
        });

        txtNhapLaiMatKhau.setText("Xác nhận mật khẩu mới");
        txtNhapLaiMatKhau.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNhapLaiMatKhauFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNhapLaiMatKhauFocusLost(evt);
            }
        });

        txtMaXacNhan.setText("Nhập mã xác nhận");
        txtMaXacNhan.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtMaXacNhanFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtMaXacNhanFocusLost(evt);
            }
        });

        jLabel2.setText("Mã Xác Nhận");

        btnGuiMa.setBackground(new java.awt.Color(138, 192, 240));
        btnGuiMa.setForeground(new java.awt.Color(255, 255, 255));
        btnGuiMa.setText("Gửi Mã");
        btnGuiMa.setBorder(null);
        btnGuiMa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuiMaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(lblCanhBao, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(btnDoiMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(74, 74, 74))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(lblDaGui, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnGuiMa, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(txtNhapLaiMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtMaXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtMatKhauMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 9, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDaGui, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuiMa, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMaXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(27, 27, 27))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(lblCanhBao, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtMatKhauMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNhapLaiMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDoiMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnExit)
                .addGap(76, 76, 76))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDoiMatKhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoiMatKhauActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDoiMatKhauActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        this.dispose();
        new DangNhapJFrame().setVisible(true);
     }//GEN-LAST:event_btnExitActionPerformed

    private void btnGuiMaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuiMaActionPerformed

    }//GEN-LAST:event_btnGuiMaActionPerformed

    private void txtMatKhauMoiFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMatKhauMoiFocusGained
        txtMatKhauMoi.setText("");
    }//GEN-LAST:event_txtMatKhauMoiFocusGained

    private void txtMatKhauMoiFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMatKhauMoiFocusLost
        txtMatKhauMoi.setText("Nhập mật khẩu mới");
    }//GEN-LAST:event_txtMatKhauMoiFocusLost

    private void txtNhapLaiMatKhauFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNhapLaiMatKhauFocusGained
        txtNhapLaiMatKhau.setText("");
    }//GEN-LAST:event_txtNhapLaiMatKhauFocusGained

    private void txtNhapLaiMatKhauFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNhapLaiMatKhauFocusLost
        txtNhapLaiMatKhau.setText("Xác nhận lại mật khẩu mới");
    }//GEN-LAST:event_txtNhapLaiMatKhauFocusLost

    private void txtEmailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEmailFocusGained
        txtEmail.setText("");
    }//GEN-LAST:event_txtEmailFocusGained

    private void txtEmailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEmailFocusLost
        txtEmail.setText("Nhập địa chỉ Gmail");
    }//GEN-LAST:event_txtEmailFocusLost

    private void txtMaXacNhanFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMaXacNhanFocusGained
        txtMaXacNhan.setText("");
    }//GEN-LAST:event_txtMaXacNhanFocusGained

    private void txtMaXacNhanFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMaXacNhanFocusLost
        txtMaXacNhan.setText("Nhập mã xác nhận");
    }//GEN-LAST:event_txtMaXacNhanFocusLost

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(QuenMatKhau.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuenMatKhau.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuenMatKhau.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuenMatKhau.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuenMatKhau().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDoiMatKhau;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnGuiMa;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblCanhBao;
    private javax.swing.JLabel lblDaGui;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtMaXacNhan;
    private javax.swing.JTextField txtMatKhauMoi;
    private javax.swing.JTextField txtNhapLaiMatKhau;
    // End of variables declaration//GEN-END:variables
}
