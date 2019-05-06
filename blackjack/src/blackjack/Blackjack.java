package blackjack;
import java.util.*;
//1. get user cards
//2. get comp cards, only display 1
//3. Ask player if they want to hit, hold, or fold (if player hit, repeat until player holds or busts)
//4. 

public class Blackjack {
	static int deck [] = {1,2,3,4,5,6,7,8,9,10,11};
	
	public static void main (String[] arg) {
		 System.out.println(hand());
		 int player1 = hand();
		 int comp = hand();
		 playGame(player1, comp);
	
	}
	
	public static int hand() {
		Random rndm = new Random();
		int randomHand = rndm.nextInt(10);
		return deck[randomHand];
	}

	//public static String prompt() {
		
//	}
	
//	public static void player1() {
//	 int playerHand [] = {hand(), hand()};
//	
	
	public static void playGame(int p1, int p2) {
		if (p1 > p2) {
			System.out.println("player 1 wins");
		} else {
			System.out.println("player 2 wins");
		}
	}
}

