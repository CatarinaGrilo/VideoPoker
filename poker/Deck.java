package VideoPoker.poker;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Deck {
    //Attributes
    int card_iter; //card vector iterator
    ArrayList<Card> cards;

    //Constructors

    //Constructor #1: from file
    public Deck(String filename) {
        try {
            File myFile = new File(filename);
            Scanner myReader = new Scanner(myFile);
            while (myReader.hasNextLine()) {
              String data = myReader.nextLine();
              System.out.println(data);     //Change so that it stores cards in array
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
        Card card = new Card('2', 'H');     //need to read from array and return that card
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
