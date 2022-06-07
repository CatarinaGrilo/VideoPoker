package VideoPoker.poker;


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
			
			bet(bet);
		}
	}

	/**
	 * Implementation of bet that eliminates the verifications
	 * @see Game#bet(int) 
	 */
	public boolean bet(int amount) {		
		return true;
	}
    
}
