package poker;

public class Main {

        public static void main(String[] args) {

                // char mode = 's'; // Stores wich mode it is to run
                // int money = 10; // Stores the inicial money of the player

                if (args[0].equals("-d")) {

                        // String cmds = "VideoPoker/Input/test1_cmd.txt";
                        // String deckInput = "VideoPoker/Input/test1_deck.txt";
                        Debug game = new Debug(Integer.parseInt(args[1]), args[2], args[3]);

                        // Process of accessing the commands in mode Debug
                        String aux;
                        int aux2;
                        int i = 0;
                        int j = 0;
                        boolean flag = true;
                        while (game.commands.size() != 0 && flag == true) {
                                aux = game.commands.get(0);
                                game.commands.remove(0);

                                if (aux.equals("b")) {
                                        if (game.commands.size() != 0) {
                                                aux = game.commands.get(0);
                                                if (Character.isDigit(aux.charAt(0))) {
                                                        game.commands.remove(0);
                                                        aux2 = Integer.parseInt(aux);
                                                        System.out.println("-cmd b " + aux2);
                                                        game.bet(aux2);
                                                } else {
                                                        System.out.println("-cmd b");
                                                        game.bet();
                                                }
                                        }
                                } else if (aux.equals("d")) {
                                        System.out.println("-cmd d");
                                        game.deal();
                                } else if (aux.equals("$")) {
                                        System.out.println("-cmd $");
                                        game.credit();
                                } else if (aux.equals("h")) {
                                        String str = "-cmd h ";
                                        while (game.commands.size() > i
                                                        && Character.isDigit(game.commands.get(i).charAt(0))) {
                                                i++;
                                        }
                                        int[] aux3 = new int[i];
                                        while (j != i) {
                                                aux = game.commands.get(0);
                                                game.commands.remove(0);
                                                aux3[j] = Integer.parseInt(aux) - 1; // -1 to be position of array from
                                                                                     // 0 to 4
                                                j++;
                                                str += aux + " ";
                                        }
                                        System.out.println(str);
                                        flag = game.hold(aux3);
                                        if (flag == false)
                                                System.out.println("No more cards available in the deck\nGame ended");
                                        i = 0;
                                        j = 0;
                                } else if (aux.equals("a")) {
                                        System.out.println("-cmd a");
                                        game.advice();
                                } else if (aux.equals("s")) {
                                        System.out.println("-cmd s");
                                        game.stats();
                                } else
                                        System.out.println("command -" + aux + " not recognized\n");
                        }
                } else if (args[0].equals("-s")) {

                        // int playerBet = 3;
                        int nbdeals = 10;
                        int i = 0;
                        boolean flag = true;
                        Simulation game = new Simulation(Integer.parseInt(args[1]), Integer.parseInt(args[2]),
                                        nbdeals = Integer.parseInt(args[3]));

                        while (i < nbdeals && flag == true) {
                                System.out.print("nbdeal:" + i + "\n");
                                flag = game.bet();
                                if (flag == false)
                                        System.out.println("Player does not have enough credit, game ended\n");
                                else {
                                        game.deal();
                                        game.hold(game.advice());
                                        i++;
                                }
                        }
                        game.stats();
                }
        }
}
