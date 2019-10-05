package com.skilldistillery.blackjack;

import java.util.Scanner;

import com.skilldistillery.cards.Deck;
import com.skilldistillery.dealer.Dealer;
import com.skilldistillery.player.Player;
import com.skilldistillery.venue.Venue;

public class Table {

	private Dealer dealer;
	private Player player;
	private Scanner scanner = new Scanner(System.in);
	private Venue venue = new Venue();
	private Deck deck = new Deck();
	private final int MIN_DECK_SIZE = 15;

	public void startBlackJack() {

		System.out.println("Welcome to BlackJack!");
		play();

	}

	private void play() {
		dealer = new Dealer();
		player = new Player();

		player.addCardPlayer(dealer.dealCards());
		System.out.println("\tPlayer's " + player);

		dealer.addCardPlayer(dealer.dealCards());
		System.out.println("\tDealer's card delt, but not shown.");

		player.addCardPlayer(dealer.dealCards());
		System.out.println("\tPlayer's " + player);

		dealer.addCardPlayer(dealer.dealCards());
		dealer.blindFirstCard();

		checkForBlackJack();
		hitOrStay();
	}

	private void hitOrStay() {
		printCurrentValue();
		System.out.println("Do you want to hit or stay?");
		String hs = scanner.next().toLowerCase();

		switch (hs) {
		case "h":
		case "hit":
			hit();
			break;
		case "s":
		case "stay":
			stay();
			break;
		default:
			System.out.println("Input error: " + hs + "\nPlease try again.");
			hitOrStay();
			break;
		}
	}

	private void printCurrentValue() {
		System.out.println("Player's hand value: " + player.askHandValue());
	}

	private void hit() {
		player.addCardPlayer(dealer.dealCards());
		System.out.println("\tPlayer's cards: " + player);
		checkValues();
		dealerPlay();
		hitOrStay();
	}

	private void stay() {
		dealerPlayAfterStay();
	}

	private void dealerPlay() {
		if (dealer.askHandValue() <= 17) {
			dealer.addCardPlayer(dealer.dealCards());
			dealer.blindFirstCard();
		}
		checkValues();
		checkDraw();
	}

	private void dealerPlayAfterStay() {
		if (dealer.askHandValue() <= 17) {
			dealer.addCardPlayer(dealer.dealCards());
			dealer.blindFirstCard();
		}
		checkValues();
		checkDraw();
		dealerPlay();
	}

	private void checkDraw() {
		if (player.askHandValue() == dealer.askHandValue()) {
			System.out.println("It is a tie. ");
			playAgain();
		}
		checkHighest();
	}

	private void checkHighest() {
		if (player.askHandValue() > dealer.askHandValue()) {
			System.out.println("You win!");
			playAgain();
		} else {
			System.out.println("You have a lower hand value and loose.");
			playAgain();
		}

	}

	private void checkValues() {
		if (player.isTwentyOne() || dealer.isBust()) {
			System.out.println("You win!");
			playAgain();
		}

		if (dealer.isTwentyOne() || player.isBust()) {
			System.out.println("You loose, dealer wins.");
			playAgain();
		}

	}

	private void playAgain() {
		System.out.println("Do you want to play another hand of BlackJack?");
		String again = scanner.next().toLowerCase();

		switch (again) {
		case "yes":
		case "y":
			if (deck.checkDeckSize() >= MIN_DECK_SIZE) {
				player.clear();
				dealer.clear();
				play();
			} else {
				player.clear();
				dealer.clear();
				deck.newDeck();
				play();
			}
			break;
		case "no":
		case "n":
			venue.venueMenu(scanner);
			break;
		default:
			System.out.println("Input error: " + again + "\nPlease try again.");
			playAgain();
			break;
		}
	}

	private void checkForBlackJack() {

		if (player.isBlackJack()) {
			System.out.println("Players wins with BlackJack!");
			playAgain();
		}

		if (dealer.isBlackJack()) {
			System.out.println("You loose, dealer wins with BlackJack.");
			playAgain();
		}

	}

}
