import jdk.jfr.Description;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

/**
 * Viết chương trình nhập 1 số cho tới khi nhập đúng số cần nhập thì hiện thông báo thành công.
 * Số lần nhập tối đa 5 lần nếu nhập sai thì sẽ dừng chương trình và hiện thông báo nhập lỗi.
 * Các tham số được lưu trữ trong các file cấu hình.
 */
public class BaiTap1 {
    private int max_time;
    private int correct_number;

    public BaiTap1(){

    }
    @Description("Lấy tham số từ file config")
    private void loadConfig(){
        max_time = Integer.parseInt(ConfigUtil.get("max_time"));
        correct_number = Integer.parseInt(ConfigUtil.get("correct_number"));
    }
    @Description("Kiểm tra số được nhập")
    public void checkNumber() {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < max_time; i++){
            System.out.print("Nhap so: ");
            //thông báo thành công nếu nhập đúng
            if (sc.nextInt() == correct_number){
                System.out.println("Thành Công!");
                return;
            }
        }
        System.out.println("Nhập Lỗi");
    }
    public void run(){
            loadConfig();
            checkNumber();
    }
    public static void main(String[] args) { new BaiTap1().run(); }
}
