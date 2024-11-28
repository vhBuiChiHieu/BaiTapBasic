import java.util.Scanner;

public class SinhVien {
    private String ten;
    private char gioiTinh;
    private String queQuan;
    private int tuoi;
    public SinhVien(){};
    public void getInfo(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Ten: ");
        ten = sc.nextLine();
        System.out.print("Tuoi: ");
        tuoi = sc.nextInt();
        sc.nextLine();
        System.out.print("Gioi tinh (M/F): ");
        gioiTinh = sc.nextLine().charAt(0);
        System.out.print("Que quan: ");
        queQuan = sc.nextLine();
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public char getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(char gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getQueQuan() {
        return queQuan;
    }

    public void setQueQuan(String queQuan) {
        this.queQuan = queQuan;
    }

    public int getTuoi() {
        return tuoi;
    }

    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }
}
