import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class BaiTap3 {

    private Integer[] getFileArray(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader("./src/input.txt"));
            String[] arrString = reader.readLine().split(" ");
            Integer[] arr = new Integer[arrString.length];
            for (int i = 0; i < arrString.length; i++){
                arr[i] = Integer.parseInt(arrString[i]);
            }
            return arr;
        } catch (IOException e){
            System.out.println("File dau vao khong lop le");
            return null;
        }
    }

    private Integer[] quickSort(Integer[] arr, int begin, int end){
        if (begin >= end)
            return arr;
        int pivot = partition(arr, begin, end);
        quickSort(arr, begin, pivot - 1);
        quickSort(arr, pivot + 1, end);
        return arr;
    }
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
        Integer[] arr = Objects.requireNonNull(getFileArray());
        System.out.println(Arrays.stream(quickSort(arr, 0, arr.length -1)).toList());
    }

    public static void main(String[] args) {
        new BaiTap3().run();
    }
}
