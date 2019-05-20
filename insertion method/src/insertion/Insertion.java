package insertion;

import java.util.ArrayList;
import java.util.Arrays;

public class Insertion {
	public static void main(String[] arg) {

		smallestToGreatest(arr);
		greatestToSmallest(arr);
		smallestToGreatest2(arr);
		replaceZeros(arr);
		replaceZerosBonus(arr);

	}

	static int[] arr = { 55, 34, 76, 1111, 21, 23, 320204 };

	static long num = 1045607890;

	public static void replaceZerosBonus(int[] arr) {
		String converted = Arrays.toString(arr).replace('0', '1');
		for (int i = 0; i < converted.length(); i++) {
			// might need to convert each string in arr to int;
		}
		System.out.println(converted);
		int convertedBack = Integer.parseInt(converted);
		System.out.println(convertedBack);
	}

	public static void replaceZeros(int[] arr) {
		ArrayList<String> numToStrings = new ArrayList<>();
		String s = String.valueOf(num);
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			numToStrings.add(s);
			if (ch == '0') {
			}
			System.out.println(num);
			// numToStrings.add(Integer.toString(arr[i]));

		}
		System.out.println(Arrays.toString(arr));
	}

	public static void smallestToGreatest2(int[] arr) {
		for (int i = 0; i < (arr.length - 1); i++) {
			for (int j = 1; j < arr.length; j++) {
				if (arr[i] > arr[j] && j < arr.length) {
					int bigNum = arr[i];
					arr[i] = arr[j + 1];
					arr[j + 1] = bigNum;
				}
			}
		}
		System.out.println("sorted: " + Arrays.toString(arr));
	}

	public static void smallestToGreatest(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			int next;
			int j = i + 1;
			while (arr[i] < arr[j]) {
				next = arr[i];
				arr[i] = arr[j];
				arr[j] = next;
				j--;
				i = 0;
			}
		}
		System.out.println("sorted: " + Arrays.toString(arr));
	}

	public static void greatestToSmallest(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			int next;
			int j = i + 1;
			while (arr[i] > arr[j]) {
				next = arr[i];
				arr[i] = arr[j];
				arr[j] = next;
				j--;
				i = 0;
			}
		}
		System.out.println("sorted: " + Arrays.toString(arr));
	}

}
