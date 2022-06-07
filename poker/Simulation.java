package poker;


/**
 * Simulation mode, the game is played automatically following
 * the strategy implemented in order to obtain long term statistics
 * @author Ana Catarina Grilo, Maragrida Fernandes, Mónica Gomez
 *
 */
public class Simulation extends Game{

    /**
	 * Constructor of simulation mode
     * @param variant Variant of Poker
	 * @param credits Amount of credit that the player has initially
	 * @param bet Amount of each bet made
	 * @param nDeals Number of deals to make
	 */
	public Simulation(GameType variant, int credits, int bet, int nDeals) {
		super(variant, credits);
		
		for(int i = 0; i < nDeals; i++) {
			if(player.getCredit() < bet)
				break;
			
			bet(bet);
			deal();
			hold(advice());
		}
	}

	/**
	 * Implementation of bet that eliminates the verifications
	 * @see Game#bet(int) 
	 */
	public boolean bet(int amount) {
		Bet = player.bet(amount);
		
		return true;
	}

	/**
	 * Implementation of deal
	 * @see Game#deal()
	 */
	public Card[] deal() {
		deck.shuffel();
		player.receiveCards(super.deal());

		return new Card[0];
	}
	
	/**
	 * Implementation of hold
	 * @see Game#hold(int[])
	 */
	public String hold(int[] positions) {
		if(positions != null) {
			int[] cardsToDiscard = new int[5 - positions.length];
			
			int i = 0;
			discard: for(int j = 1; j <= 5; j++) {
				for(int k = 0; k < positions.length; k++)
					if(positions[k] == j)
						continue discard;
				
				cardsToDiscard[i] = j;
				i++;
			}
			
			discarded = player.retrieveCards(cardsToDiscard);
			
			Card[] newCards = retrieveCards(cardsToDiscard.length);
			player.receiveCards(newCards);
		}
		
		Card[] cards = player.retrieveCards(new int[]{0, 1, 2, 3, 4});
		String finalHand = evaluateHand(cards);
		
		int prize;
		if(finalHand != null){
			prize = getWins(finalHand);
		}else
			prize = 0;

		player.win(prize);
		
		receiveCards(cards);
		if(discarded != null)
			receiveCards(discarded);
		
		discarded = null;
		
		return null;
	}

	/** 
	 * Implementation of advice
	 * @return int[] Positions of the cards that the user should hold
	 */
	public int[] advice() {
		return advice(player.showHand());
	}
    
}
