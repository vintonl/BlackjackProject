package com.skilldistillery.blackjack;

import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.cards.Card;

public abstract class Hand {

	protected List<Card> cards;

	public Hand() {
		cards = new ArrayList<Card>();
	}

	public Hand(List<Card> cards) {
		super();

		this.cards = cards;
	}

	public abstract int getHandValue();

	public abstract boolean isTwentyOne();

	public abstract boolean isBust();

	public abstract boolean isBlackJack();

	public void addCard(Card card) {
		cards.add(card);
	}

	public void clear() {
		cards.clear();
	}

	public List<Card> getCards() {
		return cards;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Hand: ").append(cards).append(".");
		return builder.toString();
	}

}
