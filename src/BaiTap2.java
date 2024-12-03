import jdk.jfr.Description;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

/**
 *      Viết chương trình tính tiền điện, cho phép nhập số điện đã dùng trong tháng,
 *      100 số đầu giá 1000, từ các số sau đến 150 giá 1500, các số còn lại giá 2000.
 *      Tham số lấy từ file config.
*/
public class BaiTap2 {
    private static final Logger bt2Logger = Logger.getLogger(BaiTap2.class);
    private int mocDien1, mocDien2, giaMuc1, giaMuc2, giaMuc3;
    public BaiTap2(){}

    @Description("Lấy các mức giá điện và mốc số điện từ file config")
    public void loadConfig(){
        try {
            mocDien1 = Integer.parseInt(ConfigUtil.get("mocDien1"));
            mocDien2 = Integer.parseInt(ConfigUtil.get("mocDien2"));
            giaMuc1 = Integer.parseInt(ConfigUtil.get("giaMuc1"));
            giaMuc2 = Integer.parseInt(ConfigUtil.get("giaMuc2"));
            giaMuc3 = Integer.parseInt(ConfigUtil.get("giaMuc3"));
            bt2Logger.info("Tai tham so thanh cong");
        } catch (Exception e){
            bt2Logger.error("Tham so dau vao khong hop le", e);
        }
    }

    @Description("Nhập số điện và tính thành tiền")
    public void calculate(){
        System.out.print("Nhap so dien: ");
        int soDien = new Scanner(System.in).nextInt();
        int tongTienDien = 0;
        //tính thành tiền
        if (soDien <= mocDien1){
            tongTienDien = soDien * giaMuc1;
        } else if (soDien <= mocDien2) {
            tongTienDien = mocDien1 * giaMuc1 + (soDien - mocDien1) * giaMuc2;
        } else {
            tongTienDien = mocDien1 * giaMuc1 + (mocDien2 - mocDien1) * giaMuc2 + (soDien - mocDien2) * giaMuc3;
        }
        System.out.println("\nTong tien dien la: " + tongTienDien);
    }
    public void run(){
        loadConfig();
        calculate();
    }
    public static void main(String[] args) {
        new BaiTap2().run();
    }
}
