import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by vladix on 7/9/17.
 */
public class p01_quickSort {

    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static int[] array;

    public static void main(String[] args) throws IOException {
        array = readArray();
        quickSort(0, array.length - 1);

        StringBuilder result = new StringBuilder();
        for (int i : array) {
            result.append(i).append(" ");
        }

        System.out.println(result);
    }

    private static void quickSort(int left, int right) {
        if (left >= right) {
            return;
        }

        int pivot = array[(left + right) / 2];
        int index = partition(left, right, pivot);

        quickSort(left, index - 1);
        quickSort(index, right);
    }

    private static int partition(int left, int right, int pivot) {
        while (left <= right) {
            while (array[left] < pivot) {
                left++;
            }

            while (array[right] > pivot) {
                right--;
            }

            if (left <= right) {
                int current = array[left];
                array[left] = array[right];
                array[right] = current;

                left++;
                right--;
            }
        }

        return left;
    }

    private static int[] readArray() throws IOException {
        String[] elements = in.readLine().split("\\s+");
        int[] array = new int[elements.length];
        for (int i = 0; i < array.length; i++) {
            array[i] = Integer.parseInt(elements[i]);
        }

        return array;
    }
}
