package poker;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;

public class Deck {
    // Attributes
    ArrayList<Card> cards;
    ArrayList<Card> discarded;

    // Constructors

    // Constructor #1: from file
    public Deck(String filename) {

        cards = new ArrayList<Card>();
        discarded = new ArrayList<Card>();

        try {
            File myFile = new File(filename);
            Scanner myReader = new Scanner(myFile);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();

                String[] tmp = data.split("\\W+"); // Split space

                for (String s : tmp){
                    if(!s.isEmpty()){
                        Card newCard = new Card(s.charAt(0), s.charAt(1));

                        //if card is valid, add to deck
                        if(newCard.checkCard()) cards.add(newCard);
                        else System.out.println("Card " + newCard.toString() + "was removed because it was invalid.\n");
                    }
                }    
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        if(cards.size() < 5){
            System.out.println("Game will terminate due to lack of cards in deck.\n");
            System.exit(-1);
        }
    }

    // Constructor #2: random 52 card deck
    public Deck() {
        cards = new ArrayList<Card>();
        discarded = new ArrayList<Card>();
        char[] ranks = { 'A', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K' };
        char[] suits = { 'H', 'S', 'C', 'D' };

        for (char s : suits) {
            for (char r : ranks) {
                cards.add(new Card(r, s));
            }
        }

        Collections.shuffle(cards);

    }

    // Methods

    // deal card to player
    public Card dealCard() {

        if (cards.size() != 0) {
            Card card = cards.get(0);

            // remove from cards
            cards.remove(0);

            // add to discarded
            discarded.add(card);

            return card;
        }
        Card noCard = new Card('-', '-');
        return noCard;
    }

    // shuffle cards in deck
    public void shuffle() {
        Collections.shuffle(cards);
    }

    // join discarded cards to playable deck
    public void join() {
        int size = discarded.size();
        for (int i=0; i<size; i++) {
            cards.add(discarded.get(0));
            discarded.remove(0);
        }

        Collections.shuffle(cards);
    }

    // prints deck like AH JS 3C, with \n at the end
    @Override
    public String toString() {
        String str_builder = "";

        for (Card c : cards) {
            str_builder += c.toString() + " ";
        }
        str_builder += "\n";
        return str_builder;
    }
}
