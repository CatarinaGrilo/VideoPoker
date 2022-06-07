package VideoPoker.poker;

public class Main {

	public static void main(String[] args) {
        Deck deck = new Deck("VideoPoker/Input/card-file.txt"); // reads from first folder in vs code

        System.out.println(deck.toString());

        Card card = deck.dealCard();

        System.out.println(card.toString());

        System.out.println(deck.toString());
	}

}
