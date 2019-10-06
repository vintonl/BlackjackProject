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
		System.out.println("Game table information to know:");
		System.out.println("\t1. Card point values:");
		System.out.println("\t\ta. Aces will have a value of 11.");
		System.out.println("\t\tb. Jacks, Queens, and Kings are each worth 10 points.");
		System.out.println("\t\tc. All other cards are face value.");
		System.out.println("\t2. Goal is to get 21 points or be the closest to 21 without going over.");
		System.out.println("\t3. We will be playing with one deck of 52 cards.");
		System.out.println("\t4. Dealer will let you know when starting a new deck.");
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
		printPlayerCurrentValue();

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
			stand(scanner);
			break;
		default:
			System.out.println("Input error: " + hs + "\nPlease try again.");
			hitOrStand(scanner);
			break;
		}
	}

	private void printPlayerCurrentValue() {
		System.out.println();
		System.out.println("\tPlayer's hand value: " + player.askHandValue());
		System.out.println();

	}

	private void printDealerCurrentValue() {
		System.out.println();
		System.out.println("\tDealer's hand value: " + dealer.askHandValue());
		System.out.println();

	}

	private void hit(Scanner scanner) {
		player.addCardPlayer(dealer.dealCards());
		System.out.println("\tPlayer's " + player);
		printPlayerCurrentValue();

		checkValues(scanner);
		dealerPlay(scanner);
		hitOrStand(scanner);
	}

	private void stand(Scanner scanner) {
		dealerPlayAfterStay(scanner);
	}

	private void dealerPlay(Scanner scanner) {
		if (dealer.askHandValue() < DEALER_HIT_MIN) {
			dealer.addCardPlayer(dealer.dealCards());
			dealer.firstCardDown();
		}

		playersTurn(scanner);
	}

	private void playersTurn(Scanner scanner) {
		if (player.askHandValue() < 21) {
			hitOrStand(scanner);
		} else {
			checkValues(scanner);
			checkDraw(scanner);
		}
		
	}

	private void dealerPlayAfterStay(Scanner scanner) {
		if (dealer.askHandValue() < DEALER_HIT_MIN) {
			dealer.addCardPlayer(dealer.dealCards());
			dealer.firstCardDown();
		} else {
			checkValues(scanner);
			checkDraw(scanner);
		}
		dealerPlayAfterStay(scanner);
	}

	private void checkDraw(Scanner scanner) {
		if (player.askHandValue() == dealer.askHandValue()) {
			System.out.println("It is a tie. ");
			playAgain(scanner);
		}
		checkHighest(scanner);
	}

	private void checkHighest(Scanner scanner) {
		dealerShowsAllCards();

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
			dealerShowsAllCards();
			System.out.println("You win with 21!");
			playAgain(scanner);
		} else if (dealer.isBust()) {
			dealerShowsAllCards();
			System.out.println("Dealer hand is a bust, and you win!");
			playAgain(scanner);
		} else if (dealer.isTwentyOne()) {
			dealerShowsAllCards();
			System.out.println("You loose, and the dealer wins with 21.");
			playAgain(scanner);
		} else if (player.isBust()) {
			System.out.println("Your hand is a bust, and the dealer wins.");
			playAgain(scanner);
		}

	}

	private void checkForBlackJack(Scanner scanner) {

		if (player.isBlackJack() && dealer.isBlackJack()) {
			dealerShowsAllCards();
			System.out.println("It's a draw! You and the dealer both have BlackJack.");
			playAgain(scanner);
		} else if (player.isBlackJack()) {
			dealerShowsAllCards();
			System.out.println("You win with BlackJack!");
			playAgain(scanner);
		} else if (dealer.isBlackJack()) {
			dealerShowsAllCards();
			System.out.println("The dealer wins with BlackJack.");
			playAgain(scanner);
		}

	}

	private void dealerShowsAllCards() {
		System.out.println("Dealer turns over first card...");
		System.out.println("\tDealer's " + dealer);
		printDealerCurrentValue();
	}

	private void playAgain(Scanner scanner) {
		System.out.println();
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
				System.out.println();
				System.out.println("******** Starting a new deck of cards. ********");
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
