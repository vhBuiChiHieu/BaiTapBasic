import jdk.jfr.Description;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

/**
 * Sắp xếp 1 mảng các số nguyên từ file input.txt bằng quick sort. các số cách nhau bằng dấu cách hoặc xuống dòng.
 */
public class BaiTap3 {
    @Description("Lấy mảng các số cần sắp sếp từ file input")
    private Integer[] getFileArray(){
        try {
            //đọc file chuyển vào mảng String
            BufferedReader reader = new BufferedReader(new FileReader("./src/input.txt"));
            String[] numbersString = reader.readLine().split(" ");
            //Chuyển thành mảng Integer
            Integer[] numbers = new Integer[numbersString.length];
            for (int i = 0; i < numbersString.length; i++){
                numbers[i] = Integer.parseInt(numbersString[i]);
            }
            return numbers;
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }
    @Description("Sắp xếp mảng bằng quick sort")
    private Integer[] quickSort(Integer[] numbers, int begin, int end){
        //dừng đệ quy nếu index begin >= end
        if (begin >= end)
            return numbers;
        //lấy index trục
        int pivot = partition(numbers, begin, end);
        //Đệ quy 2 phần của trục trung tâm
        quickSort(numbers, begin, pivot - 1);
        quickSort(numbers, pivot + 1, end);
        return numbers;
    }
    @Description("hàm phụ trợ của quick sort, giúp tìm ra vị trí trục trung tâm")
    private int partition(Integer[] numbers, int begin, int end){
        int pivot = numbers[end];
        int i = begin - 1;
        for (int j = begin; j <= end - 1; j++){
            if (numbers[j] < pivot){
                i++;
                int tmp = numbers[i];
                numbers[i] = numbers[j];
                numbers[j] = tmp;
            }
        }
        i++;
        int tmp = numbers[i];
        numbers[i] = numbers[end];
        numbers[end] = tmp;

        return i;
    }
    public void run(){
        Integer[] numbers = Objects.requireNonNull(getFileArray());
        //Hiển thị sau khi sắp xếp
        long thoiGianBatDau = System.currentTimeMillis();
        System.out.println(Arrays.stream(quickSort(numbers, 0, numbers.length -1)).toList());
        System.out.println("Tong thoi gian sap xep: " + (System.currentTimeMillis() - thoiGianBatDau) + " ms.");
    }

    public static void main(String[] args) {
        new BaiTap3().run();
    }
}
