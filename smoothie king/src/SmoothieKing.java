import java.util.Scanner;

public class SmoothieKing {

	public static void main(String[] arg) {
		smoothie();
	}

	public static void smoothie() {
		Scanner in = new Scanner(System.in);
		System.out.println("Choose your flavor");
		String flavor = in.nextLine();
		System.out.println("Choose your size");
		String size = in.nextLine();
		in.close();
		System.out.println(size + " is a whole lotta " + flavor);
		makeSmoothie(flavor, size);
	}

	public static void makeSmoothie(String flavor, String size) {
		System.out.println("here is your " + size + " " + flavor + " smoothie");
	}
}
