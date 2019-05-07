package blackjack;

import java.util.Random;
import java.util.Scanner;
import java.util.Vector;
//1. get user cards
//2. get comp cards, only display 1
//3. Ask player if they want to hit, hold, or fold (if player hit, repeat until player holds or busts)
//4. 

public class Blackjack {
	static int deck[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };

	public static void main(String[] arg) {
		// System.out.println(player1());
		compHands();
		player1();

//		 playGame(playerHand, compHand);
	}

	public static int hand() {
		Random rndm = new Random();
		int randomHand = rndm.nextInt(10);
		return deck[randomHand];
	}

	// public static String prompt() {

//	}

	public static void player1() {
		Vector p1Hands = new Vector();
		// starts game off with 2 hands for player 1
		while (p1Hands.size() < 2) {
			p1Hands.add(hand());
		}
		// prints out both hands for player 1;
		for (int i = 0; i < p1Hands.size(); i++) {
			System.out.println("Player One hand " + p1Hands.get(i));
		}
		// adds hand to player 1;
		addHands(p1Hands);
		// if player 1's hand is less than 21, ask player one if they want to hit, stay
		// or fold
		if (addHands(p1Hands) < 21) {
			Scanner scanner = new Scanner(System.in);
			System.out.println("Would you like to hit, stay or fold?");
			String userChoice = scanner.next();
			// if player one "hits", add hand to Vector, print out new hand and recheck;
			while (userChoice.equals("hit") && addHands(p1Hands) < 21) {
				p1Hands.add(hand());
				int currentHandSize = p1Hands.size();
				System.out.println("Player One hand " + p1Hands.get(currentHandSize - 1));
				System.out.println("Would you like to hit, stay or fold?");
				System.out.println("add hands is: " + addHands(p1Hands));
				userChoice = scanner.next();
			}
		}
		// return addHands(p1Hands);
	}

	public static int addHands(Vector vector) {
		int sum = 0;
		for (int i = 0; i < vector.size(); i++) {
			sum += (int) vector.get(i);
		}
		System.out.println("player hand total is: " + sum);
		return sum;
	}

	public static int compHands() {
		System.out.println("Dealer has " + hand());
		return hand();
	}

}
