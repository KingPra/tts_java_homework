package rock_paper_scissors;
import java.util.*;

public class rockPaperScissors {

	static String [] hand = {"rock", "paper", "scissor"};
	
	public static void main(String[] arg) {
		playRound(userHand(), getHand());
	};
	// returns random hand
	public static String getHand() {
		Random rndm = new Random();
		int randomNum = rndm.nextInt(3);
		return hand[randomNum];
	}
		// returns the user's hand
	public static String userHand() {
		Scanner userChoice = new Scanner(System.in);
		System.out.println("Type rock, paper or scissor");
		String userHand = userChoice.next();
		userChoice.close();
		return userHand;
	}
	// logic for comparing 2 hands;
	public static void playRound(String p1, String p2) {
		System.out.println("You chose " + p1 + " and the computer chose " + p2);
		if (p1.equals(p2)) {
			System.out.println("Its a tie");
		} else if ((p1.equals("rock") && p2.equals("scissor")) || (p1.equals("scissor") && p2.equals("paper")) || p1.equals("paper") && p2.equals("rock")) {
			System.out.println("You win");
		} else {
			System.out.println("Computer wins");
		}
	}
}
