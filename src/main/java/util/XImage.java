package util;

import java.awt.Image;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.ImageIcon;

public class XImage {

    public static Image getAppIcon() {
        URL iconURL = XImage.class.getResource("/icon/iconsss122121.png");

        if (iconURL != null) {
            // Đường dẫn hợp lệ, sử dụng nó để tạo ImageIcon
            return new ImageIcon(iconURL).getImage();
        } else {
            // Đường dẫn không hợp lệ, xử lý lỗi hoặc thông báo
            System.err.println("Không thể tìm thấy file icon.");
            return null; // hoặc trả về ảnh mặc định khác nếu cần
        }

    }

//    public static boolean save(File src) {
//        File dst = new File("D:\\java\\DuAn1\\src\\main\\resources\\com\\mycompany\\icon", src.getName());
//        if (!dst.getParentFile().exists()) {//nếu không tồn tại
//            dst.getParentFile().mkdir();//tạo thư mục
//        }
//        try {
//            Path from = Paths.get(src.getAbsolutePath());
//            Path to = Paths.get(dst.getAbsolutePath());
//            Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
//            return true;
//            //nếu mà tồn tại thì sẽ ghi đè
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//    public static ImageIcon read(String fileName) {
//        File path = new File("D:\\java\\DuAn1\\src\\main\\resources\\com\\mycompany\\icon", fileName);
//        return new ImageIcon(path.getAbsolutePath());
//    }
}
