package game_elements;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

/**
 * Deck element, where there is a list of playable cards and another
 * with already played cards
 * 
 * @author Ana Catarina Grilo, Margarida Fernandes, Mónica Gomez
 *
 */
public class Deck {
    // Attributes
    ArrayList<Card> cards;
    ArrayList<Card> discarded;

    /**
	 * Constructor #1 of Deck.
     * gets Deck from a file of cards
	 * 
	 * @param filename  name of file to read
	 */
    public Deck(String filename) {

        cards = new ArrayList<Card>();
        discarded = new ArrayList<Card>();
        String upperS;

        try {
            File myFile = new File(filename);
            Scanner myReader = new Scanner(myFile);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();

                String[] tmp = data.split("[ \t\n]"); // Split space, tab or enter

                //for all Strings in the line read
                for (String s : tmp) {

                    //Check if empty and valid length
                    if (!s.isEmpty() && s.length() < 3) {

                        upperS = s.toUpperCase();
                        Card newCard = new Card(upperS.charAt(0), upperS.charAt(1));

                        // if card rank and suit are valid, add to deck
                        if (newCard.checkCard())
                            cards.add(newCard);
                        else
                            System.out.println("Card " + newCard.toString() + "was removed because it was invalid.\n");
                    }
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            System.exit(-1);
        }

        //if deck is not enough to start game
        if (cards.size() < 5) {
            System.out.println("Game will terminate due to lack of cards in deck.\n");
            System.exit(-1);
        }
    }

    /**
	 * Constructor #2 of Deck.
     * creates regular shuffled 52 card Deck
	 */
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

    /**
	 * Deals a Card from top of the Deck
     * If no cards on Deck, will return empty Card
	 * 
	 * @return dealt Card
	 */
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

    /**
	 * Shuffles cards on Deck
	 */
    public void shuffle() {
        Collections.shuffle(cards);
    }

    /**
	 * Joins played cards to playable Deck cards
     * Results in shuffled full Deck to play
	 */
    public void join() {
        int size = discarded.size();
        for (int i = 0; i < size; i++) {
            cards.add(discarded.get(0));
            discarded.remove(0);
        }

        Collections.shuffle(cards);
    }

    /**
	 * Deck size getter
     * 
     * @return Deck size int
	 */
    public int getSize(){
        return cards.size();
    }

    /**
	 * Override of the toString method
	 * 
	 * @return String in the format 'Card'' ''Card'' '(...)
	 */
    @Override
    public String toString() {
        String str_builder = "";

        for (Card c : cards) {
            str_builder += c.toString();
        }
        return str_builder;
    }
}
