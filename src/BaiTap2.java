import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class BaiTap2 {
    private int max1, max2, p_1, p_2, p_3;
    public BaiTap2(){}
    public void run(){
        loadConfig();
        calculate();
    }
    public void loadConfig(){
        Properties prt = new Properties();
        try (FileInputStream fis = new FileInputStream("src/config.properties")){
            prt.load(fis);
            //lay cac moc dien
            max1 = Integer.parseInt(prt.getProperty("max1"));
            max2 = Integer.parseInt(prt.getProperty("max2"));
            //lay muc gia dien
            p_1 = Integer.parseInt(prt.getProperty("p_1"));
            p_2 = Integer.parseInt(prt.getProperty("p_2"));
            p_3 = Integer.parseInt(prt.getProperty("p_3"));
        } catch (IOException e){
            System.out.println("load file config that bai");
        }
    }
    public void calculate(){
        //max1 so dau gia p_1, cac so tu max1 -> max2 gia p_2, cac so con lai gia p_3
        System.out.print("Nhap so dien: ");
        int soDien = new Scanner(System.in).nextInt();
        int tongTienDien = 0;
        if (soDien <= max1){
            tongTienDien = soDien * p_1;
        } else if (soDien <= max2) {
            tongTienDien = max1 * p_1 + (soDien - max1) * p_2;
        } else {
            tongTienDien = max1 * p_1 + (max2 - max1) * p_2 + (soDien - max2) * p_3;
        }
        System.out.println("\nTong tien dien la: " + tongTienDien);
    }
    
    public static void main(String[] args) {
        new BaiTap2().run();
    }
}
