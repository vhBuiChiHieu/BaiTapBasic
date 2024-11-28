import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class BaiTap1 {
    private int max_time;
    private int correct_number;

    public BaiTap1(){

    }
    private void loadConfig(){
        try (FileInputStream ips = new FileInputStream("src/config.properties")){
            Properties prt = new Properties();
            prt.load(ips);
            max_time = Integer.parseInt(prt.getProperty("max_time"));
            correct_number = Integer.parseInt(prt.getProperty("correct_number"));
        } catch (IOException e){
            throw new IllegalArgumentException("Tải tệp cấu hình thất bại");
        }
    }
    public void checkNumber() {
        if (max_time <= 0 )
            throw new IllegalArgumentException("Dữ liệu đầu vào không hợp lệ");
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < max_time; i++){
            System.out.print("Nhap so: ");
            if (sc.nextInt() == correct_number){
                System.out.println("Thành Công!");
                return;
            }
        }
        System.out.println("Thất bại! Đã hết số lần nhập");
    }
    public void run(){
        try {
            loadConfig();
            checkNumber();
        } catch (Exception e){
            System.out.println("Lỗi: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new BaiTap1().run();
    }
}
