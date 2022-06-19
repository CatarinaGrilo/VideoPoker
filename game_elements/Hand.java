package game_elements;

/**
 * Hand element, which holds a group of cards
 * 
 * @author Ana Catarina Grilo, Margarida Fernandes, Mónica Gomez
 *
 */
public class Hand {
	// Attributes
	Card[] cards;

	/**
	 * Constructor of Hand
	 * 
	 * @param size	number of cards in Hand
	 */
	public Hand(int size) {
		
		cards = new Card[size];
		
		for (int i = 0; i < size; i++) {
			cards[i] = new Card('-', '-');
		}
	}

	/**
	 * Card setter.
	 * Sets card in index i
	 * 
	 * @param i		index of Hand to set
	 * @param rank	rank to set
	 * @param suit	suit to set
	 */
	public void setCard(int i, char rank, char suit){
		cards[i].setCard(rank, suit);
	}

	/**
	 * Override of the toString method
	 * 
	 * @return String in the format 'Card'' ''Card'' '(...)
	 */
	@Override
	public String toString() {
		String str_builder = "player's hand ";

		for (Card c : cards) {
			str_builder += c.toString();
		}

		return str_builder;
	}
}
