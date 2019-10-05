package com.skilldistillery.player;

import com.skilldistillery.blackjack.BlackJackHand;
import com.skilldistillery.blackjack.Hand;
import com.skilldistillery.cards.Card;

public class Player {
	private Hand hand;

	public Player() {
		hand = new BlackJackHand();
	}

	public Player(Hand hand) {
		super();
		this.hand = hand;
	}

	public void addCardPlayer(Card card) {
		hand.addCard(card);
	}

	public int askHandValue() {
		return hand.getHandValue();
	}

	public boolean isTwentyOne() {
		return hand.isTwentyOne();
	}

	public boolean isBust() {
		return hand.isBust();
	}

	public void clear() {
		hand.clear();
	}

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
