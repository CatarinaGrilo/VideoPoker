package poker;

/**
 * ..
 * 
 * @author Ana Catarina Grilo, Margarida Fernandes, Mónica Gomez
 *
 */
public class Player {
	// Attributes
	Hand hand;
	int money;

	/**
	 * Constructor of Player
	 * 
	 * @param m money of the player
	 * @param handSize size of the hand
	 */
	public Player(int m, int handSize) {
		hand = new Hand(handSize);
		money = m;
	}

	// Methods

	/**
	 * This method give the current money of the player
	 * 
	 * @return int, money
	 */
	public int credit() {
		return money;
	}

	/**
	 * This method subtracts the bet made from the players money
	 * 
	 * @param betted, bet made 
	 */
	public void bet(int betted) {
		money -= betted;
	}

	/**
	 * This method adds the prize of a hand to the players money
	 * 
	 * @param payoff, payoff of a hand 
	 */
	public void prize(int payoff) {
		money += payoff;
	}

}
