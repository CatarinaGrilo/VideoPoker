package poker;

import java.util.HashMap;

/**
 * Represents a generic game of Poker. Abstract class referring the various
 * methods
 * that can be implemented by the classes that extend this one.
 * 
 * @author Ana Catarina Grilo, Maragrida Fernandes, Mónica Gomez
 *
 */
public abstract class Game {

	protected GameType type;
	protected Player player;
	protected int sumOfGains;
	protected int sumOfBets;
	protected HashMap<String, Integer> stats = createstats();

	/**
	 * @param money money of the player
	 */
	public Game(int money) {
		type = new Double710();
		player = new Player(money);
		sumOfGains = 0;
		sumOfBets = 0;
	}

	public abstract boolean bet(int betted);

	public abstract void credit();

	public abstract void deal();

	public abstract void hold(int[] positions);

	public abstract void advice();

	public abstract void stats();

	/**
	 * Creates stats board
	 * 
	 * @return HashMap stats that contains the stats of this variant
	 */
	private static HashMap<String, Integer> createstats() {
		HashMap<String, Integer> stats = new HashMap<String, Integer>();

		stats.put("Jacks or Better", 0);
		stats.put("Two Pair", 0);
		stats.put("Three of a Kind", 0);
		stats.put("Straight", 0);
		stats.put("Full House", 0);
		stats.put("Flush", 0);
		stats.put("Four of a Kind", 0);
		stats.put("Straight Flush", 0);
		stats.put("Royal Flush", 0);
		stats.put("Other", 0);

		return stats;
	}
}
