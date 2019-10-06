package com.skilldistillery.dealer;

import java.util.List;

import com.skilldistillery.blackjack.BlackJackHand;
import com.skilldistillery.cards.Card;
import com.skilldistillery.cards.Deck;
import com.skilldistillery.player.Hand;
import com.skilldistillery.player.Player;

public class Dealer extends Player {

	private Deck deck;
	private Hand hand;

	public Dealer() {
		super();
		deck = new Deck();
		deck.shuffle();
		hand = new BlackJackHand();
	}

	public Dealer(Hand hand) {
		super(hand);
	}

	public Card dealCards() {
		System.out.println("Dealer deals...");
		Card card = deck.dealCard();
		return card;
	}

	public void firstCardDown() {
		List<Card> dealerCards = hand.getCards();

		System.out.print("\tDealer's hand: [First card face down");
		for (int i = 1; i < dealerCards.size(); i++) {
			System.out.print(", " + dealerCards.get(i));
		}
		System.out.println("]");
	}

	public int checkCurrentDeckSize() {
		return deck.checkDeckSize();
	}

	public void callNewDeck() {
		deck.newDeck();
	}

	@Override
	public void addCardPlayer(Card card) {
		hand.addCard(card);
	}

	@Override
	public int askHandValue() {
		return hand.getHandValue();
	}

	@Override
	public boolean isTwentyOne() {
		return hand.isTwentyOne();
	}

	@Override
	public boolean isBust() {
		return hand.isBust();
	}

	@Override
	public void clear() {
		hand.clear();
	}

	@Override
	public boolean isBlackJack() {
		return hand.isBlackJack();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("hand: ").append(hand).append("");
		return builder.toString();
	}

}
