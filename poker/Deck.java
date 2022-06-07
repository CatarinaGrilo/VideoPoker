package poker;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Deck {
    //Attributes
    ArrayList<Card> cards;
    ArrayList<Card> discarded;

    //Constructors

    //Constructor #1: from file
    public Deck(String filename) {

        cards = new ArrayList<Card>();
        discarded = new ArrayList<Card>();
        try {
            File myFile = new File(filename);
            Scanner myReader = new Scanner(myFile);
            while (myReader.hasNextLine()) {
              String data = myReader.nextLine();
              
              String[] tmp = data.split(" ");    //Split space

              for(String s: tmp)
                cards.add(new Card(s.charAt(0), s.charAt(1)));
            }
            myReader.close();
          } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
    }

    //Constructor #2: random 52 card deck
    public Deck() {

    }

    //Methods

    //deal card to player
    public Card dealCard() {
        Card card = cards.get(0);

        //remove from cards
        cards.remove(0);

        //add to discarded
        discarded.add(card);

        return card;
    }

    //prints deck like AH JS 3C, with \n at the end
    @Override
    public String toString() {
        String str_builder = "";

        for(Card c : cards) {
            str_builder += c.toString() + " ";
        }
        str_builder += "\n";
        return str_builder;
    }
}
