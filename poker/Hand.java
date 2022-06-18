package poker;

/**
 * ..
 * 
 * @author Ana Catarina Grilo, Margarida Fernandes, Mónica Gomez
 *
 */
public class Hand {
	// Attributes
	Card[] cards;

	// Constructor
	public Hand(int size) {
		
		cards = new Card[size];
		
		for (int i = 0; i < size; i++) {
			cards[i] = new Card('-', '-');
		}
	}

	@Override
	public String toString() {
		String str_builder = "player's hand ";

		for (Card c : cards) {
			str_builder += c.toString() + " ";
		}

		return str_builder;
	}
}
