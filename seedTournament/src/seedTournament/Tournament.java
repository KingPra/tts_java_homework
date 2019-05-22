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

	public static int menu() {
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
		System.out.println("4. Exit");
		System.out.println("5. Remove All Teams");
		System.out.println();

		// makes sure we have an integer
		while (!scanner.hasNextInt()) {
			System.out.println("Your entry is not valid. Please chose a number between 1-4.");
			menuCheck(menu());

		}
		int reply = scanner.nextInt();
		return reply;
	}

	// logic for checking menu
	public static void menuCheck(int reply) {
		if (reply == 1) {
			createTeams();
			menuCheck(menu());
		} else if (reply == 2) {
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

		} else if (reply == 3) {
			if (teamsArr.size() == 0) {
				createTeams();
				listTeams(teamsArr);
				menuCheck(menu());
			} else {
				teamMatch(teamsArr);
				menuCheck(menu());
			}
		} else if (reply == 4) {
			System.out.println();
			System.out.println("Goodbye");

		} else if (reply == 5) {
			clearAll(teamsArr);
			menuCheck(menu());

		} else {
			while (reply != 1 || reply != 2 || reply != 3 || reply != 4 || reply != 5) {
				System.out.println("Your option is invalid");
				System.out.println();
				menu();
			}

		}
	}

	// method for listing teams
	public static void listTeams(ArrayList arr) {
		for (int i = 0; i < arr.size(); i++) {
			System.out.println("Team: " + ((Team) arr.get(i)).getName() + " rank: " + ((Team) arr.get(i)).getSeed());
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
	public static void teamMatch(ArrayList arr) {
		ArrayList<Team> teamsCopy = new ArrayList<>(arr);
		// if the array is greater than 1 and the array is odd
		// remove from array and store in oddTeam
		Team oddTeam = isThisOdd(teamsCopy);
		Team winner = null;
		int counter = 0;
		System.out.println("copylist size is " + teamsCopy.size());
		if (teamsCopy.size() == 1 && oddTeam == null) {
			System.out.println("Team " + teamsCopy.get(0).getName()
					+ " wins by default. Yaay but really you should add more teams.");
		} else if (teamsCopy.size() == 2 && oddTeam == null) {
			winner = playRound(teamsCopy.get(0), teamsCopy.get(1));
			teamsCopy.clear();
			System.out.println("Winner of this tournament is " + winner.getName());
		} else {
			if (oddTeam != null) {
				teamsCopy.add(1, oddTeam);
				oddTeam = null;
			}
			while (teamsCopy.size() > 2) {
				counter++;
				Team lastTeam = teamsCopy.get(teamsCopy.size() - 1);
				int halfOfArr = Math.round(teamsCopy.size() / 2);
				winner = playRound(teamsCopy.get(0), lastTeam);
				teamsCopy.remove(0);
				teamsCopy.remove(lastTeam);
				teamsCopy.add(1, winner);
				System.out.println("winner of round " + counter + " is " + winner.getName() + "\n");
			}
			System.out.println("\nFinal Game for the championship!");
			winner = playRound(teamsCopy.get(0), winner);
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
			// return t1;
		} else if (t1.getScore() < t2.getScore()) {
			// System.out.println("Team " + t2.getName() + " wins");
			winner = t2;
			// return t2;
		} else {
			System.out.println(" Team " + t1.getName() + " vs Team " + t2.getName() + "\nTie Game");
			return winner;
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
