import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class p04_GeneratingVectors {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());

        generateVectors(new int[n], 0);
    }

    public static void generateVectors(int[] vector, int index) {
        if (index >= vector.length) {
            System.out.println(Arrays.toString(vector).replaceAll("[, \\[\\]]", ""));
            return;
        }
        vector[index] = 0;
        generateVectors(vector, index + 1);

        vector[index] = 1;
        generateVectors(vector, index + 1);
    }
}
