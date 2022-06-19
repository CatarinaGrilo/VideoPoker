package game_elements;

/**
 * Player element, which holds a playing Hand and money to play
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

	/**
	 * This method gives the current money of the player
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

	/**
	 * Card setter
	 * Sets card in index i of Hand
	 * 
	 * @param i		index of the Hand to set
	 * @param r		rank to set
	 * @param s		suit to set
	 */
	public void setCard(int i, char rank, char suit){
		hand.setCard(i, rank, suit);
	}

	/**
	 * Hand getter
	 * 
	 * @return array of Cards in Hand
	 */
	public Card[] getHand(){
		return hand.cards;
	}

	/**
	 * Hand printer
	 * 
	 * @return String in the format 'Card'' ''Card'' '(...)
	 */
	public String printHand(){
		return hand.toString();
	}

}
