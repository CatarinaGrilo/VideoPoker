package poker;

/**
 * Simulation mode, the game is played automatically following
 * the strategy implemented in order to obtain long term statistics
 * 
 * @author Ana Catarina Grilo, Maragrida Fernandes, Mónica Gomez
 *
 */
public class Simulation extends Game {

    protected int bet;
    protected int nbdeals;
    protected Deck deck;

    public Simulation(int money, int bet, int nbdeals) {
        super(money);
        this.bet = bet;
        this.nbdeals = nbdeals;
        this.deck = new Deck();
        sumOfBets = bet * nbdeals;
    }

    /**
     * Performs a bet when the comand is the type b i,
     * subtracting the bet made to the player's credit,
     * this function is not used in simulation mode
     * 
     * @param betted Amount to bet
     * @return boolean true in success or false otherwise
     */
    @Override
    public boolean bet(int betted) { // For when the command b is given with the a
        throw new UnsupportedOperationException("Invalid method in Simulation mode.");
    }

    /**
     * Performs a bet,subtracting the bet made
     * to the player's credit
     * 
     * @return boolean true in success or false otherwise
     */
    public boolean bet() {
        if (this.bet > player.credit()) { // The class player is the one that has the credit!
            System.out.println("b: illegal bet, the player does not have enough credit\n" + "End of Game\n");
            return false;
        }
        player.bet(this.bet); // Calls a method from the class player to subtratct the bet from the credit
        return true;
    }

    /**
     * Shows the player's credit, it is not used
     */
    @Override
    public void credit() {
        throw new UnsupportedOperationException("Invalid method in Simulation mode.");
    }

    /**
     * Begins the game by retriving 5 cards from the deck and
     * initializes the hand of the player.
     * 
     */
    public void deal() {
        Card[] cards = new Card[5];

        for (int i = 0; i < 5; i++) {
            cards[i] = deck.dealCard();
            player.hand.cards[i].rank = cards[i].rank;
            player.hand.cards[i].suit = cards[i].suit;
        }
    }

    /**
     * Holds the cards at the specified positions, discards the others.
     * Gets cards from the deck to replace the discarded ones.
     * 
     * @param positions Positions of the cards that are to maintain
     */
    public void hold(int[] positions) {
        if (positions != null) {

            int i = 0;
            int[] ToDiscard = new int[5 - positions.length];
            Card card = new Card('-', '-');

            // Finds which cards shoul be discarded
            discard: for (int j = 0; j < 5; j++) {
                for (int k = 0; k < positions.length; k++)
                    if (positions[k] == j)
                        continue discard;
                ToDiscard[i] = j;
                i++;
            }
            // Eliminates and replaces the discarded cards from the hand
            for (int n : ToDiscard) {
                card = deck.dealCard();
                player.hand.cards[n].rank = card.rank;
                player.hand.cards[n].suit = card.suit;
            }

        }
        String name = type.nameOfHand(player.hand.cards);
        pay(name);
    }

    /**
     * Evaluates a given hand name and gives the player the payoff.
     * 
     * @param name Name of the hand
     */
    public void pay(String name) {

        int payoff = type.valueOfHand(name, bet);
        if (name.equals("Four Aces") || name.equals("Four 2-4") || name.equals("Four 5-K")) {
            name = "Four of a Kind";
        }
        stats.put(name, stats.get(name) + 1);
        player.prize(payoff);
        sumOfGains += payoff;
    }

    public void advice() {

        // necessito da parte da estrategia para fazer esta função!!
        // Sorry! :( Já façoooo
    }

}
