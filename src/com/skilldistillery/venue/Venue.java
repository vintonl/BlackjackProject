package com.skilldistillery.venue;

import java.util.Scanner;

import com.skilldistillery.blackjack.Table;

public class Venue {

	public static void main(String[] args) {
		Venue venue = new Venue();
		venue.run();
	}

	private void run() {
		Scanner scanner = new Scanner(System.in);

		boolean start = true;
		
		while (start) {
			start = venueMenu(scanner);
		}
		
		System.out.println("Thanks, and have a great one!");
	}

	public boolean venueMenu(Scanner scanner) {

		System.out.println("Welcome to a garage full of table games.");
		System.out.println();
		System.out.println("Please choose from the options below.");
		System.out.println("1. Play BackJack.");
		System.out.println("2. Quit.");
		System.out.println("Enter choice here (1-2):");
		int selection = 0;
		Table table = new Table();

		try {
			selection = scanner.nextInt();
		} catch (Exception e) {
			System.err.println("Error: Unexpected value: " + selection);
			return false;
		}

		switch (selection) {
		case 1:
			table.startBlackJack();
			break;
		case 2:
			return false;
		default:
			System.err.println("Error: Unexpected value: " + selection);
			return false;
		}

		return true;
	}
}
