package poker;

public class Main {

        public static void main(String[] args) {

                Debug game = new Debug(100, "VideoPoker/Input/test1_cmd.txt", "VideoPoker/Input/test1_deck.txt");

                System.out.println(game.commands.toString());

                // Process of accessing the commands in mode Debug
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
                                                System.out.println("-cmd b" + aux2);
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
                                        aux3[j] = Integer.parseInt(aux) - 1; // -1 to be position of array from 0 to 4
                                        j++;
                                        str += aux + " ";
                                }
                                System.out.println(str);
                                game.hold(aux3);
                                i = 0;
                                j = 0;
                        } else if (aux.equals("a")) {
                                System.out.println("-cmd a");
                                game.advice();
                        } else if (aux.equals("s")) {
                                System.out.println("-cmd s");
                                game.stats();
                        }
                }
        }
}
