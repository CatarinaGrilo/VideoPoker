package VideoPoker.poker;

public class Player {
    //Attributes
    Hand hand;
    int bet;
    int money;

    //Constructor
    public Player(int b, int m) {
        hand = new Hand(new Card[5]);
        bet = b;
        money = m;
    }

    //Methods
    
}
