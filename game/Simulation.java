package game;

import java.util.Arrays;

import game_elements.Card;
import game_elements.Deck;

/**
 * Simulation mode, the game is played automatically following
 * the strategy in order to obtain long term statistics
 * 
 * @author Ana Catarina Grilo, Margarida Fernandes, Mónica Gomez
 *
 */
public class Simulation extends Game {

	protected int bet;
	protected int nbdeals;
	protected Deck deck;

	/**
	 * Constructor of Simulation
	 * 
	 * @param money   money of the player
	 * @param bet     the bet made in every single deal
	 * @param nbdeals number of deals that should be done
	 */
	public Simulation(int money, int bet, int nbdeals) {
		super(money);
		this.bet = bet;
		this.nbdeals = nbdeals;
		this.deck = new Deck();
	}

	/**
	 * This function is not used in simulation mode
	 */
	@Override
	public boolean bet(int betted) {
		throw new UnsupportedOperationException("Invalid method in Simulation mode.");
	}

	/**
	 * Performs a bet, subtracting the bet made
	 * to the player's credit
	 * 
	 * @return boolean true in success or false otherwise
	 */
	public boolean bet() {
		if (this.bet > player.credit()) {
			return false;
		}
		player.bet(this.bet);
		sumOfBets += bet;
		return true;
	}

	/**
	 * This method shows the player's credit
	 * This function is not used in simulation mode
	 */
	@Override
	public void credit() {
		throw new UnsupportedOperationException("Invalid method in Simulation mode.");
	}

	/**
	 * Begins the game by retriving 5 cards from the deck and
	 * initializes the hand of the player.
	 * 
	 */
	public void deal() {
		Card[] cards = new Card[5];

		for (int i = 0; i < 5; i++) {
			cards[i] = deck.dealCard();
			player.setCard(i, cards[i].getRank(), cards[i].getSuit());
		}
	}

	/**
	 * Holds the cards at the specified positions, discards the others.
	 * Gets cards from the deck to replace the discarded ones.
	 * 
	 * @param positions Positions of the cards that are to maintain
	 */
	public boolean hold(int[] positions) {
		if (positions != null) {

			int i = 0;
			int[] ToDiscard = new int[5 - positions.length];
			Card card = new Card('-', '-');

			// Finds which cards shoul be discarded
			discard: for (int j = 0; j < 5; j++) {
				for (int k = 0; k < positions.length; k++)
					if (positions[k] == j)
						continue discard;
				ToDiscard[i] = j;
				i++;
			}
			// Eliminates and replaces the discarded cards from the hand
			for (int n : ToDiscard) {
				card = deck.dealCard();
				player.setCard(n, card.getRank(), card.getSuit());
			}

		}
		String name = type.nameOfHand(player.getHand());
		pay(name);
		deck.join();
		return true;
	}

	/**
	 * Evaluates a given hand name and gives the player the payoff.
	 * 
	 * @param name Name of the hand
	 */
	public void pay(String name) {

		int payoff = type.valueOfHand(name, bet);
		if (name.equals("Four Aces") || name.equals("Four 2-4") || name.equals("Four 5-K")) {
			name = "Four of a Kind";
		}
		stats.put(name, stats.get(name) + 1);
		player.prize(payoff);
		sumOfGains += payoff;
	}

	/**
	 * Evaluates a given hand and tells which
	 * cards should be held.
	 * 
	 * @return int[] pos, array with positions to hold
	 */
	public int[] advice() {

		int[] pos = type.advice(player.getHand());

		if (pos.length != 0)
			Arrays.sort(pos);
		return pos;
	}

}
