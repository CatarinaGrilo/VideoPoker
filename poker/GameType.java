package poker;

/**
 * Represents a generic variant of Poker. The specifications for which variant should be specified
 * by a class that implements this interface, thoses specifications are: evaluation of hand, the pay
 * for a hand and the advice.
 * @author Ana Catarina Grilo, Margarida Fernandes, Mónica Gomez
 *
 */
public interface GameType{

    public String evaluation(Card[] cards);
    public int valueOfHand(String whichhand, int bet);
    public int[] advice(Card[] cards);

}