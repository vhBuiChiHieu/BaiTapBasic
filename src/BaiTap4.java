import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class BaiTap4 {
    Connection c;
    Scanner sc;
    PreparedStatement stm;
    private void getConnection(){
        try (FileInputStream fis = new FileInputStream("./src/config.properties")) {
            Properties properties = new Properties();
            properties.load(fis);
            String url = properties.getProperty("db.url");
            String username = properties.getProperty("db.username");
            String password = properties.getProperty("db.password");

            Class.forName("oracle.jdbc.driver.OracleDriver");
            c = DriverManager.getConnection(url, username, password);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    private void insert(SinhVien sv){
        try {
            String sql = "INSERT INTO sinhvien(ten,gioitinh,quequan,tuoi) VALUES (?,?,?,?)";
            stm = c.prepareStatement(sql);
            stm.setString(1, sv.getTen());
            stm.setString(2, sv.getGioiTinh()+"");
            stm.setString(3, sv.getQueQuan());
            stm.setInt(4, sv.getTuoi());
            int i = stm.executeUpdate();
            if (i > 0){
                System.out.println("\nThem Thanh Cong!");
            } else
                System.out.println("\nThem That Bai!");
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    private boolean kiemTraTen(String name){
        try {
            String sql = "SELECT id FROM sinhvien WHERE ten = ?";
            stm = c.prepareStatement(sql);
            stm.setString(1, name);
            ResultSet rs = stm.executeQuery();
            return !rs.next();
        } catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    private void themSinhVien(int n){
        SinhVien[] sv = new SinhVien[n];
        for (int i = 0; i < n; i++){
            sv[i] = new SinhVien();
        }

        for (int i = 0; i < n; i++){
            System.out.println("\nNhap thong tin sinh vien " + (i + 1));
            sv[i].getInfo();
            System.out.print("Xac Nhan?[Enter] ");
            sc.nextLine();
            if (kiemTraTen(sv[i].getTen())){
                insert(sv[i]);
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
        themSinhVien(n);
    }
    public static void main(String[] args) {
        new BaiTap4().run();
    }
}
