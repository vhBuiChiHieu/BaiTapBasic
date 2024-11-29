import jdk.jfr.Description;

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
    private int mocDien1, mocDien2, giaMuc1, giaMuc2, giaMuc3;
    public BaiTap2(){}
    @Description("Lấy các mức giá điện và mốc số điện từ file config")
    public void loadConfig(){
        Properties prt = new Properties();
        try (FileInputStream fis = new FileInputStream("src/config.properties")){
            prt.load(fis);
            //lấy các mốc số điện
            mocDien1 = Integer.parseInt(prt.getProperty("mocDien1"));
            mocDien2 = Integer.parseInt(prt.getProperty("mocDien2"));
            //lấy giá tiền điện các mức 1 2 3
            giaMuc1 = Integer.parseInt(prt.getProperty("giaMuc1"));
            giaMuc2 = Integer.parseInt(prt.getProperty("giaMuc2"));
            giaMuc3 = Integer.parseInt(prt.getProperty("giaMuc3"));
        } catch (IOException e){
            e.printStackTrace();
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
