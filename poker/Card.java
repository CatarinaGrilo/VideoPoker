package poker;

public class Card {
    //Attributes
    char suit;
    char rank;

    //Constructor
    public Card(char r, char s) {
        rank = r;
        suit = s;
    }

    //Method
    //checks if it is a valid card
    public boolean checkCard(){
        boolean isRank = false;
        boolean isSuit = false;

        char[] ranks = { 'A', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K' };
        char[] suits = { 'H', 'S', 'C', 'D' };

        for(char c : suits){
            if (c == suit) isSuit = true;
        }
        if(!isSuit) return false;

        for(char c : ranks){
            if (c == rank) isRank = true;
        }
        if(!isRank) return false;

        return true;
    }

    @Override
    public String toString() {
        //Must use valueOf() because char doesnt compute toString() directly
        return String.valueOf(rank) + String.valueOf(suit) + " ";
    }
}