package poker;

import java.util.ArrayList;
import java.util.Arrays;
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
      System.out.println("b: illegal betted\n");
      return false;
    }
    player.bet(betted); // Calls a method from the class player to subtratct the bet from the credit!
    this.bet = betted;
    sumOfBets += betted;
    System.out.println("player is betting " + this.bet + "\n");
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
      System.out.println("b: illegal betted" + "\n");
      return false;
    }
    player.bet(this.bet); // Calls a method from the class player to subtratct the bet from the credit!
    sumOfBets += this.bet;
    System.out.println("player is betting " + this.bet + "\n");
    return true;
  }

  /**
   * Shows the player's credit
   */
  public void credit() {
    System.out.println("player's credit is " + player.credit() + "\n");
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
    System.out.println(player.hand.toString() + "\n");
  }

  /**
   * Holds the cards at the specified positions, discards the others.
   * Gets cards from the deck to replace the discarded ones.
   * 
   * @param positions Positions of the cards that are to maintain
   */
  public boolean hold(int[] positions) {
    if (positions != null) {

      int i = 0;
      int[] ToDiscard = new int[5 - positions.length];
      Card card = new Card('-', '-');

      // Finds which cards shoul be discarded
      discard: for (int j = 0; j < 5; j++) {
        for (int k = 0; k < positions.length; k++)
          if (positions[k] == j)
            continue discard;
        ToDiscard[i] = j;
        i++;
      }
      // Eliminates and replaces the discarded cards from the hand
      for (int n : ToDiscard) {
        card = deck.dealCard();
        if (card.rank != '-') {
          player.hand.cards[n].rank = card.rank;
          player.hand.cards[n].suit = card.suit;
        }else
          return false;
      }

    }
    System.out.println(player.hand.toString());
    String name = type.nameOfHand(player.hand.cards);
    pay(name);
    return true;
  }

  /**
   * Evaluates a given hand name and gives the player the payoff.
   * 
   * @param name Name of the hand
   */
  public void pay(String name) {

    int payoff = type.valueOfHand(name, bet);
    if (name.equals("Four Aces") || name.equals("Four 2-4") || name.equals("Four 5-K")) {
      name = "Four of a Kind";
    }
    stats.put(name, stats.get(name) + 1);
    player.prize(payoff);
    if (payoff == 0)
      System.out.println("player loses and his credit is " + player.credit() + '\n');
    else {
      System.out.println("player wins with a " + name + " and his credit is " + player.credit() + "\n");
      sumOfGains += payoff;
    }
  }

  public int[] advice() {

    String out = "player should hold cards ";
    int[] pos = type.advice(player.hand.cards);

    if (pos.length != 0) {
      Arrays.sort(pos);
      for (int i : pos) {
        out += String.valueOf(i + 1) + " ";
      }

    } else
      out = "player should not hold";

    System.out.println(out + "\n");
    return pos;
  }
}