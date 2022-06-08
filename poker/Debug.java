package poker;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Debug mode, the game is played according to given commands from a file
 * @author Ana Catarina Grilo, Maragrida Fernandes, Mónica Gomez
 *
 */
public class Debug extends Game{

    protected int bet;
    protected ArrayList<String> commands = new ArrayList<String>();
    protected Deck deck;

    public Debug(GameType variant, int money, String commandsFile, String cardsFile) {
        super(variant, money);
        this.bet = 5; //The default value!
        this.deck = new Deck(cardsFile);
        
        try {
            File myFile = new File(commandsFile);
            Scanner myReader = new Scanner(myFile);
            while (myReader.hasNextLine()) {
              String data = myReader.nextLine();
              
              String[] tmp = data.split(" ");

              for(String s: tmp)
                this.commands.add(s);
            }
            myReader.close();
          } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
    }
    /**
	 * Performs a bet when the comand is the type b i,
     * subtracting the bet made to the player's credit
	 * @param betted Amount to bet
	 * @return boolean true in success or false otherwise
	 */
    public boolean bet(int betted){ // For when the command b is given with the a
        if(betted < 1 || betted > 5 || betted > player.credit()) { //The class player is the one that has the credit!
			System.out.println("b: illegal betted");
			return false;
		}
		else
			player.bet(betted); //Calls a method from the class player to subtratct the bet from the credit!

		System.out.println("player is betting " + this.bet);
		return true;
    }

    /**
	 * Performs a bet when the comand is the type b,
     * subtracting the bet made to the player's credit
	 * @return boolean true in success or false otherwise
	 */
    public boolean bet(){ // For when the command b is given alone without 
		if(this.bet > player.credit()) { //The class player is the one that has the credit!
			System.out.println("b: illegal betted");
			return false;
		}
		else
			player.bet(this.bet); //Calls a method from the class player to subtratct the bet from the credit!

		System.out.println("player is betting " + this.bet);
		return true;
    }

    /**
	 * Shows the player's credit
	 */
    public void credit(){
		System.out.println("player's credit is " + player.credit());
	}

    /**
	 * Begins the game by retriving 5 cards from the deck
	 * @return Card[] Card the initial hand given
	 */
    public Card[] deal(){

        Card[] cards = new Card[5];

		for(int i = 0; i < 5; i++)
			cards[i] = deck.dealCard();
            
		String hand = cards.toString();
        System.out.println("player's hand " + hand);

		return cards;
    }

    public void hold(int[] positions){
        if(positions!=null){
            for(int i:positions){   //Isto está ao contrário tho! Temos de eliminar as que não estão no positions! Porque as do positions são para manter
                player.hand.cards[i].rank = '-';
                player.hand.cards[i].suit = '-';
            }

        }



    }

    public void advice(){



    }

	public void stats(){


    }

    
}