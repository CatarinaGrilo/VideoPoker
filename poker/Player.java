package poker;

public class Player {
    //Attributes
    Hand hand;
    int money;

    //Constructor
    public Player(int m) {
        hand = new Hand();
        money = m;
    }

    //Methods

    public int credit(){ //To give the crdit of the player at the moment, its needed for the game
        return money;
    }

    public void bet(int betted){
        money-=betted;
    }
    
}
