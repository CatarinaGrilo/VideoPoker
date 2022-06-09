package poker;

public class Main {

        public static void main(String[] args) {
                Deck deck = new Deck("VideoPoker/Input/card-file.txt"); // reads from first folder in vs code

                System.out.println(deck.toString());

                Card card = deck.dealCard();

                System.out.println(card.toString());

                System.out.println(deck.toString());

                Debug game = new Debug(100, "VideoPoker/Input/cmd-file.txt", "VideoPoker/Input/card-file.txt");

                System.out.println(game.commands.toString());

                // Process of accessing the command in mode Debug, still not sure will stay in the main
                // our if its better to create a function inside Debug.java
                String aux;
                int aux2;
                int i = 0;
                int j = 0;
                while (game.commands.size() != 0) {
                        aux = game.commands.get(0);
                        game.commands.remove(0);

                        if (aux.equals("b")) {
                                if (game.commands.size() != 0) {
                                        aux = game.commands.get(0);
                                        if (Character.isDigit(aux.charAt(0))) {
                                                game.commands.remove(0);
                                                aux2 = Integer.parseInt(aux);
                                                System.out.println("-cmd b"+aux2);
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
                                while (game.commands.size() > i && Character.isDigit(game.commands.get(i).charAt(0))) {
                                        i++;
                                }
                                int[] aux3 = new int[i];
                                while (j != i) {
                                        aux = game.commands.get(0);
                                        game.commands.remove(0);
                                        aux3[j] = Integer.parseInt(aux) - 1; // -1 to stay position of array from 0 to 4
                                        j++;
                                        str += aux +" ";
                                }
                                System.out.println(str);
                                game.hold(aux3);
                                i = 0;
                                j = 0;
                        } else if (aux.equals("a")) {
                                System.out.println("-cmd a");
                                System.out.println("advice not done yet\n");
                        } else if (aux.equals("s")){
                                System.out.println("-cmd s");
                                System.out.println("stats not done yet\n");
                        }
                }
        }
}
