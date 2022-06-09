package poker;

/**
 * Represents a generic game of Poker. Abstract class referring the various methods 
 * that can be implemented by the classes that extend this one.
 * @author Ana Catarina Grilo, Maragrida Fernandes, Mónica Gomez
 *
 */
public abstract class Game {

    protected GameType type;
	protected Player player;
	
	/**
	 * @param money money of the player
	 */
	public Game(int money) {
		type = new Double710();
		player = new Player(money);
	}

	public abstract boolean bet(int betted);

	public abstract void credit();

	public abstract void deal();

	public abstract void hold(int[] positions);

	public abstract void advice();

	public abstract void stats();
}
