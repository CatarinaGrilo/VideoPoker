package VideoPoker.poker;

public class Hand {
    //Attributes
    Card[] cards;

    //Constructor
    public Hand(Card[] c) {
        cards = c;
    }

    //Methods
    @Override
    public String toString() {
        String str_builder = "player's hand ";

        for(Card c : cards) {
            str_builder += c.toString() + " ";
        }
        str_builder += "\n";

        return str_builder;
    }
}
