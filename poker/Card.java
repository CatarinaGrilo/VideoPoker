package VideoPoker.poker;

public class Card {
    //Attributes
    char suit;
    char rank;

    //Constructor
    public Card(char r, char s) {
        rank = r;
        suit = s;
    }

    //Methods
    @Override
    public String toString() {
        //Must use valueOf() because char doesnt compute toString() directly
        return String.valueOf(suit) + String.valueOf(rank);
    }
}