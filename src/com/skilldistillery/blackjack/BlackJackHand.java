package com.skilldistillery.blackjack;

import com.skilldistillery.cards.Card;
import com.skilldistillery.player.Hand;

public class BlackJackHand extends Hand {

	private final int TWENTY_ONE = 21;

	public BlackJackHand() {

	}

	public int getHandValue() {
		int value = 0;

		for (Card card : cards) {
			value += card.getValue();
		}
		return value;
	}

	public boolean isBlackJack() {
		if (getHandValue() == TWENTY_ONE) {
			return true;
		}
		return false;
	}

	public boolean isTwentyOne() {
		if (getHandValue() == TWENTY_ONE) {
			return true;
		}
		return false;
	}

	public boolean isBust() {
		if (getHandValue() > TWENTY_ONE) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("").append(cards).append("");
		return builder.toString();
	}

}
