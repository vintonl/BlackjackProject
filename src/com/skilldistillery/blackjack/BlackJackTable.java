package com.skilldistillery.blackjack;

import java.util.Scanner;

import com.skilldistillery.dealer.Dealer;
import com.skilldistillery.player.Player;
import com.skilldistillery.venue.Venue;

public class BlackJackTable {

	private Dealer dealer;
	private Player player;
	private Venue venue = new Venue();
	private final int MIN_DECK_SIZE = 13;
	private final int DEALER_HIT_MIN = 17;

	public void startBlackJack(Scanner scanner) {

		System.out.println("Welcome to BlackJack!");
		System.out.println();
		System.out.println("Items to know abou this game:");
		System.out.println("\t1. We will be playing with one deck of cards.");
		System.out.println("\t2. Aces will have a value of 11.");
		dealer = new Dealer();
		player = new Player();
		play(scanner);

	}

	private void play(Scanner scanner) {

		System.out.println();
		player.addCardPlayer(dealer.dealCards());
		System.out.println("\tPlayer's " + player);

		dealer.addCardPlayer(dealer.dealCards());
		dealer.firstCardDown();
		
		player.addCardPlayer(dealer.dealCards());
		System.out.println("\tPlayer's " + player);

		dealer.addCardPlayer(dealer.dealCards());
		dealer.firstCardDown();

		checkForBlackJack(scanner);
		hitOrStand(scanner);
	}

	private void hitOrStand(Scanner scanner) {
		printCurrentValue();
		
		System.out.println("Do you want to hit or stand?");
		String hs = scanner.next().toLowerCase();

		switch (hs) {
		case "h":
		case "hit":
			hit(scanner);
			break;
		case "s":
		case "stand":
		case "stay":
			stay(scanner);
			break;
		default:
			System.out.println("Input error: " + hs + "\nPlease try again.");
			hitOrStand(scanner);
			break;
		}
	}

	private void printCurrentValue() {
		System.out.println();
		System.out.println("\tPlayer's hand value: " + player.askHandValue());
		System.out.println();

	}

	private void hit(Scanner scanner) {
		player.addCardPlayer(dealer.dealCards());

		System.out.println("\tPlayer's cards: " + player);
		checkValues(scanner);
		dealerPlay(scanner);
		hitOrStand(scanner);
	}

	private void stay(Scanner scanner) {
		dealerPlayAfterStay(scanner);
	}

	private void dealerPlay(Scanner scanner) {

		if (dealer.askHandValue() < DEALER_HIT_MIN) {
			dealer.addCardPlayer(dealer.dealCards());
			dealer.firstCardDown();
		}
		checkValues(scanner);
		checkDraw(scanner);
	}

	private void dealerPlayAfterStay(Scanner scanner) {

		if (dealer.askHandValue() < DEALER_HIT_MIN) {
			dealer.addCardPlayer(dealer.dealCards());
			System.out.println("\tDealer's " + dealer);
		}
		checkValues(scanner);
		checkDraw(scanner);
		dealerPlay(scanner);
	}

	private void checkDraw(Scanner scanner) {
		if (player.askHandValue() == dealer.askHandValue()) {
			System.out.println("It is a tie. ");
			playAgain(scanner);
		}
		checkHighest(scanner);
	}

	private void checkHighest(Scanner scanner) {
		if (player.askHandValue() > dealer.askHandValue()) {
			System.out.println("You win with a higher hand value!");
			playAgain(scanner);
		} else {
			System.out.println("You loose with a lower hand value.");
			playAgain(scanner);
		}

	}

	private void checkValues(Scanner scanner) {
		if (player.isTwentyOne()) {
			System.out.println("You win with 21!");
			playAgain(scanner);
		} else if (dealer.isBust()) {
			System.out.println("Dealer hand is a bust, and you win!");
			playAgain(scanner);
		}

		if (dealer.isTwentyOne()) {
			System.out.println("You loose, and the dealer wins with 21.");
			playAgain(scanner);
		} else if (player.isBust()) {
			System.out.println("Your hand is a bust, and the dealer wins.");
			playAgain(scanner);
		}

	}

	private void checkForBlackJack(Scanner scanner) {

		if (player.isBlackJack() && dealer.isBlackJack()) {
			System.out.println("It's a draw! You and the dealer both have BlackJack.");
			playAgain(scanner);
		} else if (player.isBlackJack()) {
			System.out.println("You win with BlackJack!");
			playAgain(scanner);
		} else if (dealer.isBlackJack()) {
			System.out.println("The dealer wins with BlackJack.");
			playAgain(scanner);
		}

	}

	private void playAgain(Scanner scanner) {
		System.out.println("Do you want to play another hand of BlackJack?");
		String again = scanner.next().toLowerCase();
		switch (again) {
		case "yes":
		case "y":
			if (dealer.checkCurrentDeckSize() >= MIN_DECK_SIZE) {
				player.clear();
				dealer.clear();

				play(scanner);
			} else {
				player.clear();
				dealer.clear();
				dealer = new Dealer();
				System.out.println("*** Starting a new deck of cards. ****");
				play(scanner);
			}
			break;
		case "no":
		case "n":
			venue.venueMenu(scanner);
			break;
		default:
			System.out.println("Input error: " + again + "\nPlease try again.");
			playAgain(scanner);
			break;
		}
	}

}
