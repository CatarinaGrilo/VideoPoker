package game;

import game.Game.States;

public class Main {

	public static void main(String[] args) {

		if (args == null || args.length != 4 || args[0].length() != 2 || args[0].charAt(0) != '-')
			instructions();

		if (args[0].equals("-d")) {

			if(Integer.parseInt(args[1]) <= 0){
				System.out.println("Invalid player credit. Please try again.");
				System.exit(-1);
			}

			Debug game = new Debug(Integer.parseInt(args[1]), args[2], args[3]);
			States state = States.Begin;
			// Process of accessing the commands in mode Debug
			String aux;
			int aux2;
			int i = 0;
			int j = 0;
			boolean flag = true;
			boolean betMade = true;

			while (game.commands.size() != 0 && flag == true) {
				aux = game.commands.get(0);
				game.commands.remove(0);

				if (aux.equals("b") && state == States.Begin) {
					if (game.commands.size() != 0) {
						aux = game.commands.get(0);
						if (Character.isDigit(aux.charAt(0))) {
							game.commands.remove(0);
							aux2 = Integer.parseInt(aux);
							System.out.println("-cmd b " + aux2);
							betMade = game.bet(aux2);
						} else {
							System.out.println("-cmd b");
							betMade = game.bet();
						}
						if (betMade == true) {
							state = States.BetMade;
						}
					}
				} else if (aux.equals("b") && state != States.Begin) {
					System.out.println("b: illegal command\n");
				} else if (aux.equals("d") && state == States.BetMade) {
					System.out.println("-cmd d");
					game.deal();
					state = States.DecideHand;
				} else if (aux.equals("d") && state != States.BetMade) {
					System.out.println("d: illegal command\n");
				} else if (aux.equals("$")) {
					System.out.println("-cmd $");
					game.credit();
				} else if (aux.equals("h") && state == States.DecideHand) {
					String str = "-cmd h ";
					while (game.commands.size() > i
							&& Character.isDigit(game.commands.get(i).charAt(0))) {
						i++;
					}
					int[] aux3 = new int[i];
					while (j != i) {
						aux = game.commands.get(0);
						game.commands.remove(0);
						aux3[j] = Integer.parseInt(aux) - 1; // -1 to be index
						j++;
						str += aux + " ";
					}
					System.out.println(str);
					flag = game.hold(aux3);
					if (flag == false)
						System.out.println("No more cards available in the deck\nGame ended");
					state = States.Begin;
					i = 0;
					j = 0;
				} else if (aux.equals("h") && state != States.DecideHand) {
					System.out.println("h: illegal command\n");
				} else if (aux.equals("a") && state == States.DecideHand) {
					System.out.println("-cmd a");
					game.advice();
				} else if (aux.equals("a") && state != States.DecideHand) {
					System.out.println("a: illegal command\n");
				} else if (aux.equals("s")) {
					System.out.println("-cmd s");
					game.stats();
				} else if (Character.isDigit(aux.charAt(0))) {
				} else
					System.out.println("command -" + aux + " not recognized\n");
			}
		} else if (args[0].equals("-s")) {

			int i = 0;
			boolean flag = true;
			Simulation game = new Simulation(Integer.parseInt(args[1]), Integer.parseInt(args[2]),
					Integer.parseInt(args[3]));

			while (i < game.nbdeals && flag == true) {
				flag = game.bet();
				if (flag == false)
					System.out.println("Round " + game.nbdeals + ". Player does not have enough credit, game ended\n");
				else {
					game.deal();
					game.hold(game.advice());
					i++;
				}
			}
			game.stats();
		}
	}

	/**
	 * Prints the instructions to run this program
	 */
	private static void instructions() {
		System.out.println("Instructions:\n\n"
				+ "For degub:\tjava -jar videopoker.jar -d c cmd-file card-file\n"
				+ "For simulation:\tjava -jar videopoker.jar -s c b n\n"
				+ "where:\n"
				+ "\tc is the inicial credit and c > 0;\n"
				+ "\tcmd-file is the name of the file containing the commands to debug\n"
				+ "\tcard-file is the name of the file containing the cards to debug\n"
				+ "\tb the amount of each bet in simulation mode\n"
				+ "\tn the number of deals in simulation mode\n");
		System.exit(-1);
	}
}
