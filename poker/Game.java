package VideoPoker.poker;

/**
 * Represents a generic game of Poker. The specifications for a given variant are specified
 * by the attributes and methods of its attribute variant
 * @author Ana Catarina Grilo, Maragrida Fernandes, Mónica Gomez
 *
 */
public class Game {

    GameType type;
	Deck deck;
	int InitCredit;
	int Bet;

    
	/**
	 * Constructor of Game in which a list of cards is supplied -> Debug
	 * @param variant Variant of Poker that fully specifies the details of its strategy and gains
	 * @param cards List of cards to initialize the deck
	 */
	public Game(GameType variant, String filename, int credit) {
		type = variant;
		deck = new Deck(filename);
		InitCredit = credit;
		Bet = 0;
	}

    /**
	 * Constructor of Game in which deck is initialized as default -> Simulation
	 * @param variant Variant of Poker that fully specifies the details of its strategy and gains
	 */
	public Game(GameType variant, int credit) {
		type = variant;
		deck = new Deck();
		InitCredit = credit;
		Bet = 0;
		
	}

	/**
	 * Performs a bet, the amount specified is subtracted from player's account
	 * @param amount is the bet
	 * @return boolean true in success or false otherwise
	 */
	

	/**
	 * Holds the cards at the specified positions, discards those that are not to hold, retrieves
	 * more from the game to replace them, retrieves and evaluates the final hand and gives
	 * the player its prize (even if zero) and finally, gives back to game all the cards.
	 * After this the round is over
	 * @param positions Positions of the cards that are to maintain
	 * @return String Final hand of the player in this round
	 */
	public String hold(int[] positions) {
		
		return null;
	}

    /**
	 * For a given hand, keeps the cards that should be held in order 
	 * to achieve the maximum gain
	 * @param cards Cards to be considered
	 * @return int[] Positions that should be held according to the strategy
	 */
	public int[] advice(Card[] cards) {
		return type.advice(cards);
	}

	/**
	 * Shows the player's actual credit
	 * @return int Player's actual credit
	 */
	public int statistics() {
		
		return 0;
	}

	/**
	 * Retrieves the prize for a given awarded hand by asking it to its variant
	 * @param nameOfHand Names the awarded hand
	 * @return int Prize for this hand and this number of credits
	 */
	public int getWins(String nameOfHand) {
		return type.valueOfHand(nameOfHand, Bet);
	}

	/**
	 * Returns the name of the awarded hand by asking what is it to its variant,
	 * if it exists, otherwise returns null
	 * @param cards Vector of cards (in number of 5) that are to be considered
	 * @return String name of awarded hand if it exists; null otherwise
	 */
	public String evaluateHand(Card[] cards) {
		return type.evaluation(cards);
	}
}
