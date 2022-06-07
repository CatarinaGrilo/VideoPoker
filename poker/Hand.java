package VideoPoker.poker;

public class Hand {
    //Attributes
    Card[] cards;

    //Constructor
    public Hand() {
        for (int i = 0; i < 5; i++)
            cards[i] = new Card('-', '-');
    }

    //Methods
    public int getFirstEmpty() {
        for (int i = 0; i < 5; i++) {
            if(cards[i].rank == '-') return i;
        }
        return -1;
    }

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
