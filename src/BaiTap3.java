//Sắp xếp 1 mảng các số nguyên từ file input.txt bằng quick sort. các số cách nhau bằng dấu cách hoặc xuống dòng.
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class BaiTap3 {
    private Integer[] getFileArray(){
        //Lấy mảng các số cần sắp sếp từ file input
        try {
            //đọc file chuyển vào mảng String
            BufferedReader reader = new BufferedReader(new FileReader("./src/input.txt"));
            String[] arrString = reader.readLine().split(" ");
            //Chuyển thành mảng Integer
            Integer[] arr = new Integer[arrString.length];
            for (int i = 0; i < arrString.length; i++){
                arr[i] = Integer.parseInt(arrString[i]);
            }
            return arr;
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    private Integer[] quickSort(Integer[] arr, int begin, int end){
        //dừng đệ quy nếu index begin >= end
        if (begin >= end)
            return arr;
        //lấy index trục
        int pivot = partition(arr, begin, end);
        //Đệ quy 2 phần của trục trung tâm
        quickSort(arr, begin, pivot - 1);
        quickSort(arr, pivot + 1, end);
        return arr;
    }
    //hàm phụ trợ của quick sort, giúp tìm ra vị trí trục trung tâm
    private int partition(Integer[] arr, int begin, int end){
        int pivot = arr[end];
        int i = begin - 1;
        for (int j = begin; j <= end - 1; j++){
            if (arr[j] < pivot){
                i++;
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
            }
        }
        i++;
        int tmp = arr[i];
        arr[i] = arr[end];
        arr[end] = tmp;

        return i;
    }
    public void run(){
        //lấy mảng đã được sắp xếp để hiển thị
        Integer[] arr = Objects.requireNonNull(getFileArray());
        System.out.println(Arrays.stream(quickSort(arr, 0, arr.length -1)).toList());
    }

    public static void main(String[] args) {
        new BaiTap3().run();
    }
}
