package poker;

import java.util.HashMap;

/**
 * Class that implements the Double Bonus 7/10 variant with the
 * use of interface GameType.
 * @author Ana Catarina Grilo, Margarida Fernandes, Mónica Gomez
 *
 */
public class Double710 implements GameType{

    private static final HashMap<String, Integer[]> payoff = createPayoff();

    private static HashMap<String, Integer[]> createPayoff(){
        HashMap<String, Integer[]> payoff = new HashMap<String, Integer[]>();

        payoff.put("Royal Flush", new Integer[]{250,500,750,1000,4000});
        payoff.put("Straight Flush", new Integer[]{50,100,150,200,250});
        payoff.put("Four Aces", new Integer[]{160,320,480,640,800});
        payoff.put("Four 2-4", new Integer[]{80,160,240,320,400});
        payoff.put("Four 5-k", new Integer[]{50,100,150,200,250});
        payoff.put("Full House", new Integer[]{10,20,30,40,50});
        payoff.put("Flush", new Integer[]{7,14,21,28,35});
        payoff.put("Straight", new Integer[]{5,10,15,20,25});
        payoff.put("Three of a Kind", new Integer[]{3,6,9,12,15});
        payoff.put("Two Pair", new Integer[]{1,2,3,4,5});
        payoff.put("Jacks or Better", new Integer[]{1,2,3,4,5});
        payoff.put("None", new Integer[]{0,0,0,0,0});

        return payoff;
    }
    public String evaluation(Card[] cards){
        return "";
    }

    /**
	 * Gets the payoff for a given hand
	 * @param hand Name of hand
	 * @param bet bet
	 * @return int payoff for the given hand according to the bet made
	 */
    public int valueOfHand(String hand, int bet){
        return payoff.get(hand)[bet - 1];
	
    }
    public int[] advice(Card[] cards){
        return null;
    }

    
}
