import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class p05_GeneratingCombinations {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] nums = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int k = Integer.parseInt(reader.readLine());

        genCombinations(nums, new int[k], 0, 0);
    }

    private static void genCombinations(int[] elements, int[] combination, int elIndex, int comIndex) {
        if (comIndex >= combination.length) {
            System.out.println(Arrays.toString(combination).replaceAll("[,\\[\\]]", ""));
            return;
        }
        for (int i = elIndex; i < elements.length; i++) {
            combination[comIndex] = elements[i];
            genCombinations(elements, combination, i + 1, comIndex + 1);
        }
    }
}
