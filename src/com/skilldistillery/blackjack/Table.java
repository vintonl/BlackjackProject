package com.skilldistillery.blackjack;

import java.util.Scanner;

import com.skilldistillery.cards.Deck;
import com.skilldistillery.dealer.Dealer;
import com.skilldistillery.player.Player;
import com.skilldistillery.venue.Venue;

public class Table {

	private Dealer dealer;
	private Player player;
	private Venue venue = new Venue();
	private Deck deck = new Deck();
	private final int MIN_DECK_SIZE = 13;
	private final int DEALER_HIT_MIN = 17;

	public void startBlackJack(Scanner scanner) {

		System.out.println("Welcome to BlackJack!");
		play(scanner);

	}

	private void play(Scanner scanner) {
		dealer = new Dealer();
		player = new Player();

		System.out.println();
		player.addCardPlayer(dealer.dealCards());
		System.out.println("\tPlayer's " + player);

		dealer.addCardPlayer(dealer.dealCards());
		System.out.println("\tDealer's card delt, but not shown.");

		player.addCardPlayer(dealer.dealCards());
		System.out.println("\tPlayer's " + player);

		dealer.addCardPlayer(dealer.dealCards());
		dealer.blindFirstCard();

		checkForBlackJack(scanner);
		hitOrStay(scanner);
	}

	private void hitOrStay(Scanner scanner) {
		printCurrentValue();
		System.out.println();
		System.out.println("Do you want to hit or stay?");
		String hs = scanner.next().toLowerCase();

		switch (hs) {
		case "h":
		case "hit":
			hit(scanner);
			break;
		case "s":
		case "stay":
			stay(scanner);
			break;
		default:
			System.out.println("Input error: " + hs + "\nPlease try again.");
			hitOrStay(scanner);
			break;
		}
	}

	private void printCurrentValue() {
		System.out.println();
		System.out.println("\tPlayer's hand value: " + player.askHandValue());
	}

	private void hit(Scanner scanner) {
		player.addCardPlayer(dealer.dealCards());
		System.out.println("\tPlayer's cards: " + player);
		checkValues(scanner);
		dealerPlay(scanner);
		hitOrStay(scanner);
	}

	private void stay(Scanner scanner) {
		dealerPlayAfterStay(scanner);
	}

	private void dealerPlay(Scanner scanner) {
		if (dealer.askHandValue() < DEALER_HIT_MIN) {
			dealer.addCardPlayer(dealer.dealCards());
			dealer.blindFirstCard();
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

		if (player.isBlackJack()) {
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
			if (deck.checkDeckSize() < MIN_DECK_SIZE) {
				player.clear();
				dealer.clear();
				play(scanner);
			} else {
				player.clear();
				dealer.clear();
				deck.newDeck();
				System.out.println("Starting a new deck of cards.");
				play(scanner);
			}
			break;
		case "no":
		case "n":
			player.clear();
			dealer.clear();
			venue.venueMenu(scanner);
			break;
		default:
			System.out.println("Input error: " + again + "\nPlease try again.");
			playAgain(scanner);
			break;
		}
	}


}
