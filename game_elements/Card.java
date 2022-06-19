package game_elements;

/**
 * Card element, where rank and suit are both a char
 * 
 * @author Ana Catarina Grilo, Margarida Fernandes, Mónica Gomez
 *
 */
public class Card {
	// Attributes
	char suit;
	char rank;

	/**
	 * Constructor of Card
	 * 
	 * @param r		rank of the card
	 * @param s		suit of the card
	 */
	public Card(char r, char s) {
		rank = r;
		suit = s;
	}

	/**
	 * Checks if Card attributes are valid
	 * in a regular 52 card deck
	 * 
	 * @return boolean true if valid or false otherwise
	 */
	public boolean checkCard() {
		boolean isRank = false;
		boolean isSuit = false;

		char[] ranks = { 'A', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K' };
		char[] suits = { 'H', 'S', 'C', 'D' };

		for (char c : suits) {
			if (c == suit)
				isSuit = true;
		}
		if (!isSuit)
			return false;

		for (char c : ranks) {
			if (c == rank)
				isRank = true;
		}
		if (!isRank)
			return false;

		return true;
	}

	/**
	 * Card setter
	 * 
	 * @param r		rank of the Card
	 * @param s		suit of the Card
	 */
	public void setCard(char r, char s){
		rank = r;
		suit = s;
	}

	/**
	 * Card rank getter
	 * 
	 * @return Card rank
	 */
	public char getRank(){
		return rank;
	}

	/**
	 * Card suit getter
	 * 
	 * @return Card suit
	 */
	public char getSuit(){
		return suit;
	}

	/**
	 * Override of the toString method
	 * 
	 * @return String in the format 'rank''suit'' '
	 */
	@Override
	public String toString() {
		// Must use valueOf() because char doesnt compute toString() directly
		return String.valueOf(rank) + String.valueOf(suit) + " ";
	}
}