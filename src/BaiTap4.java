//Viết chương trình console, thực hiện nhập thông tin n sinh viên (tên, giới tính, quê quán, tuổi)
//Sau khi nhập bấm enter để insert thông tin sinh vien vào DB, giả thiết tên không được trùng nhau, id tự tăng.
import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class BaiTap4 {
    Connection connection;
    Scanner sc;
    PreparedStatement stm;
    //hàm kết nối database với thông tin từ file config
    private void getConnection(){
        try (FileInputStream fis = new FileInputStream("./src/config.properties")) {
            Properties properties = new Properties();
            properties.load(fis);
            String url = properties.getProperty("db.url");
            String username = properties.getProperty("db.username");
            String password = properties.getProperty("db.password");
            //kết nối database
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    //Thêm 1 SinhVien vào bảng sinhvien
    private void insert(SinhVien sinhvien){
        try {
            String sql = "INSERT INTO sinhvien(ten,gioitinh,quequan,tuoi) VALUES (?,?,?,?)";
            stm = connection.prepareStatement(sql);
            stm.setString(1, sinhvien.getTen());
            stm.setString(2, sinhvien.getGioiTinh()+"");
            stm.setString(3, sinhvien.getQueQuan());
            stm.setInt(4, sinhvien.getTuoi());
            int i = stm.executeUpdate();
            //kiểm tra kết quả truy vấn
            if (i > 0){
                System.out.println("\nThem Thanh Cong!");
            } else
                System.out.println("\nThem That Bai!");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    //Kiểm tra điều kiện tên sinh viên, nếu chưa có tên trong bảng trả về true, nếu đã tồn tại trả về false
    private boolean checkName(String name){
        try {
            String sql = "SELECT id FROM sinhvien WHERE ten = ?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, name);
            ResultSet rs = stm.executeQuery();
            return !rs.next();
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    //Nhập Danh sách thông tin sinh viên, thêm vào bảng
    private void addListSinhVien(int n){
        SinhVien[] sinhvien = new SinhVien[n];
        for (int i = 0; i < n; i++){
            sinhvien[i] = new SinhVien();
        }

        for (int i = 0; i < n; i++){
            System.out.println("\nNhap thong tin sinh vien " + (i + 1));
            sinhvien[i].getInfo();
            System.out.print("Xac Nhan?[Enter] ");
            sc.nextLine();
            //Kiểm tra trùng tên trước khi thêm vào bảng
            if (checkName(sinhvien[i].getTen())){
                insert(sinhvien[i]);
            } else {
                System.out.println("\nTrung ten, hay nhap lai!");
                i--;
            }
        }
    }
    public void run(){
        getConnection();
        sc = new Scanner(System.in);
        System.out.print("Nhap N: ");
        int n = sc.nextInt();
        sc.nextLine();
        addListSinhVien(n);
    }
    public static void main(String[] args) {
        new BaiTap4().run();
    }
}
