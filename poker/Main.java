package poker;

public class Main {

	public static void main(String[] args) {
        Deck deck = new Deck("VideoPoker/Input/card-file.txt"); // reads from first folder in vs code

        System.out.println(deck.toString());

        Card card = deck.dealCard();

        System.out.println(card.toString());

        System.out.println(deck.toString());

        Debug game = new Debug(new Double710(), 1, "VideoPoker/Input/cmd-file.txt", "VideoPoker/Input/card-file.txt");

        System.out.println(game.commands.toString());

	}

}
