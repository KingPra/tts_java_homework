package seedTournament;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Tournament extends Team {
	static ArrayList<Team> teamsArr = new ArrayList<>();

	public Tournament(String name, int seed) {
		super(name, seed);
		this.setName(name);
		this.setSeed(seed);
	}

	// menu
	// create teams, list teams see match up, exit
	public static int counter = 0;

	public static int menu() {
		while (counter == 0) {
			counter++;
			System.out.println("Welcome to the tournament. Please Select from the following options: ");
		}

		Scanner scanner = new Scanner(System.in);
		System.out.println();
		System.out.println("Choose a number:");
		System.out.println("1. Create Teams");
		System.out.println("2. List Teams");
		System.out.println("3. See Game");
		System.out.println("4. Exit");
		System.out.println();
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
				createTeams();
				listTeams(teamsArr);
				menuCheck(menu());
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

		} else {
			while (reply != 1 || reply != 2 || reply != 3 || reply != 4) {
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
		if (arr.size() % 2 != 0) {
			oddTeam = (Team) arr.get(0);
			arr.remove(0);
			System.out.println('\n');
			System.out.println("Since there is an odd number of teams, Team " + oddTeam.getName() + " gets by week");
		}
		return oddTeam;
	}

	// method for matching teams, best to worst, and 2 to 3;
	public static void teamMatch(ArrayList arr) {
		ArrayList<Team> winners = new ArrayList<>();

		// checks for odd numbers
		Team oddTeam = isThisOdd(arr);

		// plays first and last teams and returns winner
		// for (int i = 0; arr.size() > 0; i++)
		int loopCounter = 0;
		while (arr.size() > 0) {

			Team t1 = (Team) arr.get(0);
			Team t2 = (Team) arr.get(arr.size() - 1);
			// testing start
			System.out.println("iterator is : " + loopCounter);
			System.out.println("team arrray size  is : " + arr.size());
			System.out.println("winnter arrray size  is : " + winners.size());
			System.out.println("t1 and t2 is" + t1.getName() + " " + t2.getName());
			// testing end
			winners.add(playRound(t1, t2));
			// prints out the round, shows team vs team and the outcome of that gaem
			System.out.println("round " + (loopCounter + 1));
			System.out.println("Team " + t1.getName() + " vs Team " + t2.getName());
			System.out.println("And the  winner is : " + winners.get((winners.size() - 1)).getName() + '\n');
			arr.remove(0);
			arr.remove(arr.get(arr.size() - 1));
			System.out.println("this is the end of the for loop,  array size is now: " + arr.size());
			// this is a test
			loopCounter++;
			if (oddTeam != null) {
				// --i;
				System.out.println("the odd team if statement: " + oddTeam.getName());
				System.out.println();
				winners.add(winners.size() - 1, oddTeam);
				oddTeam = null;

			}
			// this is the end of test
		}

		int i = 0;
		Team champ = null;
		while (i < winners.size() && winners.size() > 2) {
			i++;
			champ = playRound(winners.get(0), winners.get(winners.size() - 1));
			winners.remove(0);
			winners.remove(winners.size() - 1);
			System.out.println("the winner is " + champ.getName() + " and the array size is " + winners.size());
			System.out.println();
		}
//		if (oddTeam != null) {
//			System.out.println("the odd team if statement: " + oddTeam.getName());
//			System.out.println();
//			winners.add(oddTeam);
//			winners.add(champ);
//			Team newChamp = playRound(winners.get(0), winners.get(1));
//			System.out.println("the odd winner is " + newChamp.getName() + " and the array size is " + winners.size());
//			System.out.println();
//			teamsArr.clear();
//		}
		System.out.println(
				"Team " + winners.get(0).getName() + " vs " + " Team " + winners.get(winners.size() - 1).getName());

		System.out.println("The tournament champion is " + winners.get(0).getName());
		System.out.println();
		teamsArr.clear();
	}

// dynamically allows the user to create an arrayList of team instances and assign names and ranking.
	public static void createTeams() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("How many teams would you like to create? ");
		System.out.println();
		int amount = scanner.nextInt();
		int loopCounter = 0;
		for (int i = 0; i < amount; i++) {
			loopCounter++;
			Scanner scanner2 = new Scanner(System.in);
			System.out.println("Type in team # " + loopCounter + ",  name: ");
			System.out.println();
			String teamName = scanner2.nextLine();
			System.out.println("Set team # " + loopCounter + "'s rank: ");
			int rank = scanner2.nextInt();
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
		if (t1.getScore() > t2.getScore()) {
			t1.setWins(t1.getWins() + 1);
			return t1;
		} else {
			t2.setWins(t2.getWins() + 1);
			return t2;
		}
	}

	public static void main(String[] arg) {
		menuCheck(menu());
	}
}
