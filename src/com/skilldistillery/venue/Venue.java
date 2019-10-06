package com.skilldistillery.venue;

import java.util.Scanner;

import com.skilldistillery.blackjack.BlackJackTable;

public class Venue {

	private static BlackJackTable table;

	public static void main(String[] args) {
		Venue venue = new Venue();
		table = new BlackJackTable();
		venue.run();
	}

	private void run() {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Welcome to a garage full of table games.");
		venueMenu(scanner);
	}

	public void venueMenu(Scanner scanner) {
		System.out.println();
		System.out.println("Please choose from the options below.");
		System.out.println("1. Play BackJack.");
		System.out.println("2. Quit.");
		System.out.println("Enter choice here (1-2):");
		int selection = 0;

		try {
			selection = scanner.nextInt();

		} catch (Exception e) {
			System.err.println("Error: Unexpected value: " + selection);
			venueMenu(scanner);
		}

		switch (selection) {
		case 1:
			table.startBlackJack(scanner);
			break;
		case 2:
			System.out.println("Thanks, and have a great one!");
			System.exit(0);
			break;
		default:
			System.err.println("Error: Unexpected value: " + selection);
			venueMenu(scanner);
		}
	}

}
