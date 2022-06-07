package poker;



/**
 * Class that implements the Double Bonus 7/10 variant with the
 * use of interface GameType.
 * @author Ana Catarina Grilo, Margarida Fernandes, Mónica Gomez
 *
 */
public class Double710 implements GameType{


    public String evaluation(Card[] cards){
        return "";
    }
    public int valueOfHand(String whichhand, int bet){
        return 0;
    }
    public int[] advice(Card[] cards){
        return null;
    }

    
}
