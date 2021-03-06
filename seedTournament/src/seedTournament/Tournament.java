package seedTournament;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Tournament extends Team {
	static ArrayList<Team> teamsArr = new ArrayList<>();

	public Tournament(String name, int seed) {
		super(name, seed);
		this.setName(name);
		this.setSeed(seed);
	}

	public static boolean tracker = false;

	public static String menu() {
		while (tracker) {
			tracker = true;
			System.out.println("Welcome to the tournament. Please Select from the following options: ");
		}

		Scanner scanner = new Scanner(System.in);
		System.out.println();
		System.out.println("Enter a number to choose an option:");
		System.out.println("1. Create Teams");
		System.out.println("2. List Teams");
		System.out.println("3. See Game");
		System.out.println("4. Remove All Teams");
		System.out.println("5. Exit");
		System.out.println();
		String reply = scanner.nextLine();
		return reply;
	}

	// logic for checking menu
	public static void menuCheck(String reply) {
		if (reply.equals("1")) {
			createTeams();
			menuCheck(menu());
		} else if (reply.equals("2")) {
			if (teamsArr.size() == 0) {
				Scanner scanner = new Scanner(System.in);
				System.out
						.println("You do not have any teams to list. Would you like to add teams? \n 1. Yes \n 2. No");
				String input = scanner.nextLine();
				if (input.equals("1") || input.equalsIgnoreCase("yes")) {
					createTeams();
					listTeams(teamsArr);
					menuCheck(menu());
				} else {
					menuCheck(menu());
				}

				;
			} else {
				listTeams(teamsArr);
				menuCheck(menu());
			}

		} else if (reply.equals("3")) {
			if (teamsArr.size() == 0) {
				createTeams();
				listTeams(teamsArr);
				menuCheck(menu());
			} else {
				playTournament(teamsArr);
				menuCheck(menu());
			}
		} else if (reply.equals("4")) {
			clearAll(teamsArr);
			menuCheck(menu());

		} else if (reply.equals("5")) {
			System.out.println("\nGoodbye\n");

		} else {
			while (reply.equals("1") || reply.equals("2") || reply.equals("3") || reply.equals("4")
					|| reply.equals("5")) {
				System.out.println("Your option is invalid");
				System.out.println();
				menu();
			}

		}
	}

	// method for listing teams
	public static void listTeams(ArrayList arr) {
		for (int i = 0; i < arr.size(); i++) {
			System.out.println("Team: " + ((Team) arr.get(i)).getName() + " rank: " + ((Team) arr.get(i)).getSeed()
					+ "\nTotal wins overall : " + ((Team) arr.get(i)).getWins());
		}
	}

	// checks for an odd number of teams
	public static Team isThisOdd(ArrayList arr) {
		Team oddTeam = null;
		if (arr.size() > 1 && arr.size() % 2 != 0) {
			oddTeam = (Team) arr.get(0);
			arr.remove(0);
			// System.out.println('\n');
			// System.out.println("Since there is an odd number of teams, Team " +
			// oddTeam.getName() + " gets by week");
		}
		return oddTeam;
	}

	// this will play a whole tournament
	// calls on isThisOdd method to check for an odd number
	// calls on playRound method to play round
	// takes the main team arrayList as an arguement
	public static void playTournament(ArrayList arr) {
		ArrayList<Team> arrOfTeams = new ArrayList<>(arr);
		// if the array is greater than 1 and the array is odd
		// remove from array and store in oddTeam
		Team oddTeam = isThisOdd(arrOfTeams);
		Team winner = null;
		int counter = 0;
		if (arrOfTeams.size() == 1 && oddTeam == null) {
			System.out.println("Team " + arrOfTeams.get(0).getName()
					+ " wins by default. Yaay but really you should add more teams.");
		} else if (arrOfTeams.size() == 2 && oddTeam == null) {
			winner = playRound(arrOfTeams.get(0), arrOfTeams.get(1));
			arrOfTeams.clear();
			System.out.println("Winner of this tournament is " + winner.getName());
		} else {
			if (oddTeam != null) {
				arrOfTeams.add(1, oddTeam);
				oddTeam = null;
			}
			while (arrOfTeams.size() > 2) {
				counter++;
				// this is logic for where to place the winner of the last round
				// back into the arr, preferably in the middle.
				int halfway = arrOfTeams.size();
				if (arrOfTeams.size() > 3) {
					halfway = Math.round((arrOfTeams.size() / 2) - 1);
				} else {
					halfway = Math.round(arrOfTeams.size() / 2);
				}

				Team lastTeam = arrOfTeams.get(arrOfTeams.size() - 1);
				winner = playRound(arrOfTeams.get(0), lastTeam);
				arrOfTeams.remove(0);
				arrOfTeams.remove(lastTeam);
				arrOfTeams.add(halfway, winner);
				System.out.println("winner of round " + counter + " is " + winner.getName() + "\n");
			}
			System.out.println("\nFinal Game for the championship!");
			winner = playRound(arrOfTeams.get(0), winner);
			System.out.println("Champion of the tournament is Team " + winner.getName());
			counter = 0;
		}

	}

// dynamically allows the user to create an arrayList of team instances and assign names and ranking.
	public static void createTeams() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("How many teams would you like to create? ");
		System.out.println();

		while (!scanner.hasNextInt()) {
			System.out.println("Invalid character. Please enter numeric values only");
			createTeams();
		}
		int amount = scanner.nextInt();
		int loopCounter = 0;

		for (int i = 0; i < amount; i++) {
			loopCounter++;
			Scanner scanner2 = new Scanner(System.in);
			System.out.println("Type in team # " + loopCounter + ",  name: ");
			System.out.println();
			String teamName = scanner2.nextLine();
//////////////////////start test/////////////////////
			int rank = 0;
			boolean isNum = false;
			while (!isNum)
				try {
					System.out.println("2.Set team # " + loopCounter + "'s rank: ");
					rank = scanner2.nextInt();
					scanner2.nextLine();
					isNum = true;
					// numeric value entered, so break the while loop
				} catch (Exception mychecker) {
					// Display Error message
					System.out.println("Invalid character found,Please enter numeric values only !!");
					scanner2.nextLine();// Advance the scanner
				}
//////////////////////////end test//////////////////
			teamsArr.add(new Team(teamName, rank));
			Collections.sort(teamsArr, new Comparator<Team>() {
				public int compare(Team t1, Team t2) {
					return Integer.valueOf(t1.getSeed()).compareTo(t2.getSeed());
				}
			});
		}
	}

	// gives two teams a random score and returns the winner
	// gets called by teamMatch method
	public static Team playRound(Team t1, Team t2) {
		((Team) t1).setScore(randomNum());
		((Team) t2).setScore(randomNum());
		Team winner = null;
		if (t1.getScore() > t2.getScore()) {
			winner = t1;
			winner.setWins(winner.getWins() + 1);

		} else if (t1.getScore() < t2.getScore()) {
			winner = t2;
			winner.setWins(winner.getWins() + 1);
		} else {
			System.out.println(" Team " + t1.getName() + " vs Team " + t2.getName() + "\nTie Game");

		}
		System.out.println(" Team " + t1.getName() + " vs Team " + t2.getName());
		System.out.println("Team " + t1.getName() + " scored " + t1.getScore() + "\nTeam " + t2.getName() + " scored "
				+ t2.getScore());
		return winner;

	}

	// clear array
	public static void clearAll(List arr) {
		arr.clear();
		System.out.println(" \n All teams are cleared");
	}

	public static void main(String[] arg) {
		menuCheck(menu());
	}
}
