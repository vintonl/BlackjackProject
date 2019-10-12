package com.skilldistillery.venue;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.skilldistillery.blackjack.BlackJackTable;

public class Venue {

	public static void main(String[] args) {
		Venue venue = new Venue();
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
		} catch (InputMismatchException e) {
			
		}

		switch (selection) {
		case 1:
			BlackJackTable table = new BlackJackTable();
			table.startBlackJack(scanner);
			break;
		case 2:
			System.out.println("Thanks, and have a great one!");
			System.exit(0);
			break;
		default:
			System.err.println("Error: Unexpected value entered");
			run();
		}
	}
}
