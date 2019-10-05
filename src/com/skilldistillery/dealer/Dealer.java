package com.skilldistillery.dealer;

import java.util.List;

import com.skilldistillery.blackjack.BlackJackHand;
import com.skilldistillery.blackjack.Hand;
import com.skilldistillery.cards.Card;
import com.skilldistillery.cards.Deck;
import com.skilldistillery.player.Player;

public class Dealer extends Player {

	private Deck deck;
	private Hand hand;

	public Dealer() {
		super();
		deck = new Deck();
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

	@Override
	public void addCardPlayer(Card card) {
		hand.addCard(card);
	}

	public void blindFirstCard() {
		List<Card> dealerCards = hand.getCards();
		
		System.out.print("\tDealer's hand: ");
		System.out.print("[First card is blind");
		for (int i = 1; i < dealerCards.size(); i++) {
			System.out.print(", " + dealerCards.get(i));
		}
		System.out.println("]");
	}

	public boolean isBust() {
		return hand.isBust();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Dealer [deck=").append(deck).append("]");
		return builder.toString();
	}

}
