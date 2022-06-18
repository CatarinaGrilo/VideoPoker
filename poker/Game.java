package poker;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

/**
 * Represents a generic game of Poker. Abstract class referring the various
 * methods that can be done during the game.
 * 
 * @author Ana Catarina Grilo, Margarida Fernandes, Mónica Gomez
 *
 */
public abstract class Game {

	protected GameType type;
	protected Player player;
	protected int sumOfGains;
	protected int sumOfBets;
	protected HashMap<String, Integer> stats = createstats();

	/**
	 * Constructor of Game
	 * 
	 * @param money money of the player
	 */
	public Game(int money) {
		type = new Double710();
		player = new Player(money, 5);
		sumOfGains = 0;
		sumOfBets = 0;
	}

	public abstract boolean bet(int betted);

	public abstract void credit();

	public abstract void deal();

	public abstract boolean hold(int[] positions);

	public abstract void pay(String name);

	public abstract int[] advice();

	/**
	 * Prints the statistics of the game
	 * 
	 */
	public void stats() {
		String out = "Hand\t\t\tNb\n";
		int total = 0;

		out += "---------------------------\n";
		Iterator<Entry<String, Integer>> it = stats.entrySet().iterator();
		while (it.hasNext()) {
			HashMap.Entry<String, Integer> entry = (HashMap.Entry<String, Integer>) it.next();
			out += entry.getKey() + (entry.getKey().length() < 6 ? "\t\t\t" : "\t\t") + entry.getValue() + "\n";
			total += entry.getValue();
		}
		out += "--------------------------\n";
		out += "Total\t\t\t" + total + "\n";
		out += "--------------------------\n";
		out += "Credit\t\t   " + player.credit() + " (" + (sumOfGains * 100 / sumOfBets) + "%)\n";

		System.out.println(out);
	}

	/**
	 * Creates stats board
	 * 
	 * @return HashMap stats that contains the stats of the game
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
