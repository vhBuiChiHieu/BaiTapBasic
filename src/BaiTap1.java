//Viết chương trình nhập 1 số cho tới khi nhập đúng số cần nhập thì hiện thông báo thành công.
// Số lần nhập tối đa 5 lần nếu nhập sai thì sẽ dừng chương trình và hiện thông báo nhập lỗi.
//các tham số được lưu trữ trong các file cấu hình
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
        //Lấy tham số từ file config
        try (FileInputStream ips = new FileInputStream("src/config.properties")){
            Properties prt = new Properties();
            prt.load(ips);
            max_time = Integer.parseInt(prt.getProperty("max_time"));   //Số lần nhập tối đa
            correct_number = Integer.parseInt(prt.getProperty("correct_number"));   //số chính xác mong muốn
            if (max_time <= 0 )
                throw new IllegalArgumentException("Dữ liệu đầu vào không hợp lệ");
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public void checkNumber() {
        //thực hiện kiểm tra mỗi khi người dùng nhập
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < max_time; i++){
            System.out.print("Nhap so: ");
            //thông báo thành công nếu nhập đúng
            if (sc.nextInt() == correct_number){
                System.out.println("Thành Công!");
                return;
            }
        }
        //Quá số lần nhập sẽ báo lỗi
        System.out.println("Nhập Lỗi");
    }
    public void run(){
            loadConfig();
            checkNumber();
    }
    public static void main(String[] args) { new BaiTap1().run(); }
}
