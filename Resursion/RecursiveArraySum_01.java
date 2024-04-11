import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class p01_RecursiveArraySum {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] array = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int totalSum = reverseArray(array, array.length);
        System.out.println(totalSum);

    }

    private static int reverseArray(int[] array, int n) {
        if (n <= 0)
            return 0;
        return (reverseArray(array, n - 1) + array[n - 1]);
    }
}
