import java.util.Arrays;
import java.util.Scanner;

public class BinarySearch {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] numbers = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

        Arrays.sort(numbers);

        int target = Integer.parseInt(scanner.nextLine());
        System.out.println(binarySearch(numbers, target));
    }

    public static int binarySearch(int[] numbers, int target) {
        int left = 0, right = numbers.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (numbers[mid] == target) return mid;

            if (numbers[mid] < target) left = mid + 1;
            else right = mid - 1;
        }

        return left;
    }
}
