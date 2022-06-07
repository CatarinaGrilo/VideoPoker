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
	 * @param variant Variant of Poker that fully specifies the details of its strategy and gains
	 */
	public Game(GameType variant, int money) {
		type = variant;
		player = new Player(money);
	}

	public abstract boolean bet(int betted);

	public abstract void credit();

	public abstract Card[] deal();

	public abstract void hold(int[] positions);

	public abstract void advice();

	public abstract void stats();

}
