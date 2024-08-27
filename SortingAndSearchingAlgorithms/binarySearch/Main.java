package binarySearch;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		String input = scanner.nextLine();
		int key = Integer.parseInt(scanner.nextLine());
		scanner.close();
		
		if (input.isEmpty()) {
			return;
		}
		
		int[] arr = Arrays.stream(input.split(" ")).mapToInt(Integer::parseInt).toArray();
		int index = BinarySearch.indexOf(arr, key);
		
		System.out.println(index);
	}
}
