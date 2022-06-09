package poker;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Debug mode, the game is played according to given commands from a file
 * 
 * @author Ana Catarina Grilo, Maragrida Fernandes, Mónica Gomez
 *
 */
public class Debug extends Game {

  protected int bet;
  protected ArrayList<String> commands = new ArrayList<String>();
  protected Deck deck;

  public Debug(int money, String commandsFile, String cardsFile) {
    super(money);
    this.bet = 5; // The default value!
    this.deck = new Deck(cardsFile);

    try {
      File myFile = new File(commandsFile);
      Scanner myReader = new Scanner(myFile);
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();

        String[] tmp = data.split(" ");

        for (String s : tmp)
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
   * 
   * @param betted Amount to bet
   * @return boolean true in success or false otherwise
   */
  public boolean bet(int betted) { // For when the command b is given with the a
    if (betted < 1 || betted > 5 || betted > player.credit()) { // The class player is the one that has the credit!
      System.out.println("b: illegal betted");
      return false;
    } 
    player.bet(betted); // Calls a method from the class player to subtratct the bet from the credit!
    this.bet = betted;
    System.out.println("player is betting " + this.bet);
    return true;
  }

  /**
   * Performs a bet when the comand is the type b,
   * subtracting the bet made to the player's credit
   * 
   * @return boolean true in success or false otherwise
   */
  public boolean bet() { // For when the command b is given alone without
    if (this.bet > player.credit()) { // The class player is the one that has the credit!
      System.out.println("b: illegal betted");
      return false;
    } 
    player.bet(this.bet); // Calls a method from the class player to subtratct the bet from the credit!
    System.out.println("player is betting " + this.bet);
    return true;
  }

  /**
   * Shows the player's credit
   */
  public void credit() {
    System.out.println("player's credit is " + player.credit());
  }

  /**
   * Begins the game by retriving 5 cards from the deck and
   * initializes the hand of the player.
   * 
   */
  public void deal() {
    Card[] cards = new Card[5];

    for (int i = 0; i < 5; i++) {
      cards[i] = deck.dealCard();
      player.hand.cards[i].rank = cards[i].rank;
      player.hand.cards[i].suit = cards[i].suit;
    }
    String hand = cards.toString();
    System.out.println("player's hand " + hand);
  }

  /**
   * Holds the cards at the specified positions, discards the others.
   * Gets cards from the deck to replace the discarded ones, evaluates the final
   * hand and gives the player the payoff.
   * 
   * @param positions Positions of the cards that are to maintain
   */
  public void hold(int[] positions) {
    if (positions != null) {

      int i = 0;
      int pos = -1;
      int[] ToDiscard = new int[5 - positions.length];
      Card[] cards = new Card[5 - positions.length];

      // Finds which cards shoul be discarded
      discard: for (int j = 1; j < 6; j++) {
        for (int k = 0; k < positions.length; k++)
          if (positions[k] == j)
            continue discard;

        ToDiscard[i] = j;
        i++;
      }
      // Eliminates the discarded cards from the hand
      for (int n : ToDiscard) {
        player.hand.cards[n].rank = '-';
        player.hand.cards[n].suit = '-';
      }
      // Replaces the cards discarded
      for (int m = 0; m < positions.length; m++) {
        cards[m] = deck.dealCard();
        pos = player.hand.getFirstEmpty();
        player.hand.cards[pos].rank = cards[m].rank;
        player.hand.cards[pos].suit = cards[m].suit;
      }
    }
    player.hand.toString();

    // Falta ver que hand é atraves da funçao Double710.evaluation
    String name = type.nameOfHand(player.hand.cards);
    // verificar se a hand tem payoff ou não com a função Double710.valueOfHand
    int payoff = type.valueOfHand(name, bet);

    player.prize(payoff);

    if(payoff == 0)
			System.out.println("player loses and his credit is " + player.credit());
		else
			System.out.println("player wins with a " + name + " and his credit is " + player.credit());
  }

  public void advice() {

    //necessito da parte da estrategia para fazer esta função!!

  }

  public void stats() {

  }

}