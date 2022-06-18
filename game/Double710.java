package game;

import java.util.HashMap;

import game_elements.Card;

/**
 * Class that implements the Double Bonus 7/10 variant with the
 * use of interface GameType.
 * 
 * @author Ana Catarina Grilo, Margarida Fernandes, Mónica Gomez
 *
 */
public class Double710 implements GameType {

	protected Strategy strategy = new Strategy();
	private static final HashMap<String, Integer[]> payoff = createPayoff();

	public Double710() {

	}

	/**
	 * Gets the name of a given hand
	 * 
	 * @param cards cards of a given hand
	 * @return String with the name of the hand
	 */
	public String nameOfHand(Card[] cards) {
		return strategy.nameOfHand(cards);
	}

	/**
	 * Gets the payoff for a given hand
	 * 
	 * @param hand Name of hand
	 * @param bet  bet
	 * @return int payoff for the given hand according to the bet made
	 */
	public int valueOfHand(String hand, int bet) {
		return payoff.get(hand)[bet - 1]; // bet-1 to be index

	}

	/**
	 * Gets the advice for holding the hand
	 * 
	 * @param hand hand to be analized
	 * @return int[] Array with the positions to hold
	 */
	public int[] advice(Card[] hand) {

		return strategy.advice(hand);
	}

	/**
	 * Creates payoff board
	 * 
	 * @return HashMap payoff that contains the payoff for this variant
	 */
	private static HashMap<String, Integer[]> createPayoff() {
		HashMap<String, Integer[]> payoff = new HashMap<String, Integer[]>();

		payoff.put("Royal Flush", new Integer[] { 250, 500, 750, 1000, 4000 });
		payoff.put("Straight Flush", new Integer[] { 50, 100, 150, 200, 250 });
		payoff.put("Four Aces", new Integer[] { 160, 320, 480, 640, 800 });
		payoff.put("Four 2-4", new Integer[] { 80, 160, 240, 320, 400 });
		payoff.put("Four 5-K", new Integer[] { 50, 100, 150, 200, 250 });
		payoff.put("Full House", new Integer[] { 10, 20, 30, 40, 50 });
		payoff.put("Flush", new Integer[] { 7, 14, 21, 28, 35 });
		payoff.put("Straight", new Integer[] { 5, 10, 15, 20, 25 });
		payoff.put("Three of a Kind", new Integer[] { 3, 6, 9, 12, 15 });
		payoff.put("Two Pair", new Integer[] { 1, 2, 3, 4, 5 });
		payoff.put("Jacks or Better", new Integer[] { 1, 2, 3, 4, 5 });
		payoff.put("Other", new Integer[] { 0, 0, 0, 0, 0 });

		return payoff;
	}
}
