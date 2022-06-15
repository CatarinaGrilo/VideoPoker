package poker;

import java.util.ArrayList;
import java.util.Arrays;

public class Strategy {

    public Strategy() {

    }

    public int[] orderedCards(Card[] cards, int aceValue, int size) {

        int hand[] = new int[size];

        for (int i = 0; i < size; i++) {
            if (Character.isDigit(cards[i].rank)) {
                hand[i] = Integer.parseInt(Character.toString(cards[i].rank));
                ;
            } else if (cards[i].rank == 'T') {
                hand[i] = 10;
            } else if (cards[i].rank == 'J') {
                hand[i] = 11;
            } else if (cards[i].rank == 'Q') {
                hand[i] = 12;
            } else if (cards[i].rank == 'K') {
                hand[i] = 13;
            } else if (cards[i].rank == 'A') {
                hand[i] = aceValue;
            }
        }

        Arrays.sort(hand);

        return hand;
    }

    public boolean allSameSuit(Card[] cards) {
        int j = 1;
        for (int i = 1; i < 5; i++) {
            if (cards[0].suit == cards[i].suit) {
                j++;
            }
        }
        if (j == 5) {
            return true;
        }
        return false;
    }

    public boolean isHighCard(Card card) {

        char highcards[] = { 'J', 'Q', 'K', 'A' };

        for (int i = 0; i < highcards.length; i++) {
            if (card.rank == highcards[i])
                return true;
        }
        return false;
    }

    public boolean checkifConsecutive(Card[] cards) {

        int counter = 1;

        /* Lower value Ace */
        int handLower[] = orderedCards(cards, 1, cards.length);

        for (int i = 1; i < 5; i++) {
            // if(hand[i] == (hand[0]+i))
            if (handLower[i] == (handLower[i - 1] + 1))
                counter++;
        }
        if (counter == 5)
            return true;

        /* Higher value of Ace */

        int handHigher[] = orderedCards(cards, 14, cards.length);
        counter = 1;

        for (int i = 1; i < 5; i++) {
            // if(hand[i] == (hand[0]+i))
            if (handHigher[i] == (handHigher[i - 1] + 1))
                counter++;
        }
        if (counter == 5)
            return true;

        return false;
    }

    public boolean isXofakind(Card[] cards, int X, char kind) {

        int counter = 0;

        for (int i = 0; i < 5; i++) {
            if (cards[i].rank == kind)
                counter++;
        }

        if (counter == X) {
            return true;
        }
        return false;
    }

    public int[] isXofaKind(Card[] cards, int X, char kind) {

        int counter = 0, pos[] = new int[X];

        for (int i = 0; i < 5; i++) {
            if (cards[i].rank == kind) {
                pos[counter] = i;
                counter++;
            }
        }

        if (counter == X) {
            return pos;
        }

        return new int[0];
    }

    /* Functions that determine the name of the hand */

    public boolean isRoyalFlush(Card[] cards) {

        int i = 0, counter = 0, counter_T = 0, counter_J = 0, counter_Q = 0, counter_K = 0, counter_A = 0;

        // Check if cards are the same suit
        if (allSameSuit(cards)) {
            for (i = 0; i < 5; i++) {
                if (cards[i].rank == 'T' && counter_T == 0)
                    counter_T++;
                if (cards[i].rank == 'J' && counter_J == 0)
                    counter_J++;
                if (cards[i].rank == 'Q' && counter_Q == 0)
                    counter_Q++;
                if (cards[i].rank == 'K' && counter_K == 0)
                    counter_K++;
                if (cards[i].rank == 'A' && counter_A == 0)
                    counter_A++;
            }
            counter = counter_T + counter_J + counter_Q + counter_K + counter_A;
            if (counter == 5) {
                return true;
            }
        }
        return false;
    }

    public boolean isStraightFlush(Card[] cards) {

        // Check if cards are the same suit
        if (allSameSuit(cards)) {
            if (checkifConsecutive(cards))
                return true;
        }
        return false;
    }

    public boolean isFourAces(Card[] cards) {

        if (isXofakind(cards, 4, 'A'))
            return true;

        return false;
    }

    public boolean isFour2_4s(Card[] cards) {

        if (isXofakind(cards, 4, '2'))
            return true;
        if (isXofakind(cards, 4, '3'))
            return true;
        if (isXofakind(cards, 4, '4'))
            return true;

        return false;
    }

    public boolean isFour5_Ks(Card[] cards) {

        if (isXofakind(cards, 4, '5'))
            return true;
        if (isXofakind(cards, 4, '6'))
            return true;
        if (isXofakind(cards, 4, '7'))
            return true;
        if (isXofakind(cards, 4, '8'))
            return true;
        if (isXofakind(cards, 4, '9'))
            return true;
        if (isXofakind(cards, 4, 'T'))
            return true;
        if (isXofakind(cards, 4, 'J'))
            return true;
        if (isXofakind(cards, 4, 'Q'))
            return true;
        if (isXofakind(cards, 4, 'K'))
            return true;

        return false;
    }

    public boolean is4ofaKind(Card[] cards) {

        char rank[] = { '1', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A' };

        for (int i = 0; i < rank.length; i++) {
            if (isXofakind(cards, 4, rank[i]))
                return true;
        }

        return false;

    }

    public boolean isFullHouse(Card[] cards) {

        char rank[] = { '1', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A' };
        int counter_3 = 0, counter_2 = 0;

        for (int i = 0; i < rank.length; i++) {
            if (isXofakind(cards, 3, rank[i])) {
                rank[i] = '-';
                counter_3++;
            }
        }

        for (int i = 0; i < rank.length; i++) {
            if (isXofakind(cards, 2, rank[i])) {
                counter_2++;
            }
        }

        if (counter_3 == 1 && counter_2 == 1)
            return true;

        return false;
    }

    public boolean isFlush(Card[] cards) {

        if (allSameSuit(cards))
            return true;

        return false;
    }

    public boolean isStraight(Card[] cards) {

        if (checkifConsecutive(cards))
            return true;

        return false;
    }

    private int[] is3ofaKind(Card[] cards) {

        char rank[] = { '1', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K' };
        int pos[] = new int[0];

        for (int i = 0; i < rank.length; i++) {
            if (pos.length != 0)
                return pos;
        }

        return new int[0];
    }

    private int[] is2pair(Card[] cards) {

        char hand[] = { cards[0].rank, cards[1].rank, cards[2].rank, cards[3].rank, cards[4].rank };
        char seen[] = { '-', '_', '.', ':' };
        int no_pairs = 0, k = 0;
        int pos[] = { -1, -1, -1, -1 };

        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 5; i++) {
                if (i == j)
                    continue;
                if (hand[j] == hand[i]) {
                    hand[j] = seen[k];
                    hand[i] = seen[k + 1];
                    no_pairs++;
                    pos[k] = i;
                    pos[k + 1] = j;
                    k = k + 2;
                }
            }
        }

        if (no_pairs == 2)
            return pos;

        return new int[0];
    }

    public boolean isJacksorBetter(Card[] cards) {

        if (isXofakind(cards, 2, 'J'))
            return true;

        if (isXofakind(cards, 2, 'Q'))
            return true;

        if (isXofakind(cards, 2, 'K'))
            return true;

        if (isXofakind(cards, 2, 'A'))
            return true;

        return false;
    }

    /* Functions for strategy */

    /************************************************************************************** */

    private int[] is4toRoyalFlush(Card[] cards) {

        int counter[] = { 0, 0, 0, 0 }; // H D S C
        int pos[] = { -1, -1, -1, -1 };
        int counter_rank = 0;
        char suit = '-', suits[] = { 'H', 'D', 'S', 'C' };

        for (int i = 0; i < 5; i++) {
            // Check suits
            if (cards[i].suit == 'H')
                counter[0]++;
            else if (cards[i].suit == 'D')
                counter[1]++;
            else if (cards[i].suit == 'S')
                counter[2]++;
            else if (cards[i].suit == 'C')
                counter[3]++;
        }

        //System.out.println("CheckCounters: " + counter[0] + counter[1] + counter[2] + counter[3]);

        // Check if at least 4 cards have same suit
        if (!(counter[0] >= 4 || counter[1] >= 4 || counter[2] >= 4 || counter[3] >= 4))
            return new int[0];
        else {
            for (int i = 0; i < 4; i++) {
                if (counter[i] >= 4)
                    suit = suits[i];
            }
        }

        //System.out.println("whatsuit is : " + suit);

        for (int i = 0; i < 5; i++) {
            if (counter_rank == 4){
                //System.out.println("4 to flush is : " + pos);
                return pos;
            }
            // Check rank
            if (cards[i].rank == 'T' && cards[i].suit == suit) {
                pos[counter_rank] = i;
                counter_rank++;
            } else if (cards[i].rank == 'J' && cards[i].suit == suit) {
                pos[counter_rank] = i;
                counter_rank++;
            } else if (cards[i].rank == 'Q' && cards[i].suit == suit) {
                pos[counter_rank] = i;
                counter_rank++;
            } else if (cards[i].rank == 'K' && cards[i].suit == suit) {
                pos[counter_rank] = i;
                counter_rank++;
            } else if (cards[i].rank == 'A' && cards[i].suit == suit) {
                pos[counter_rank] = i;
                counter_rank++;
            }

            //System.out.println("Actual value of : " + Arrays.toString(pos) + "counter is" + counter_rank);
        }

        if (counter_rank == 4){
            return pos;
        }


        return new int[0];
    }

    private int[] is3toRoyalFlush(Card[] cards) {

        int counter[] = { 0, 0, 0, 0 }; // H D S C
        int pos[] = { -1, -1, -1 };
        int counter_rank = 0;
        char suit = '-', suits[] = { 'H', 'D', 'S', 'C' };

        for (int i = 0; i < 5; i++) {
            // Check suits
            if (cards[i].suit == 'H')
                counter[0]++;
            else if (cards[i].suit == 'D')
                counter[1]++;
            else if (cards[i].suit == 'S')
                counter[2]++;
            else if (cards[i].suit == 'C')
                counter[3]++;
        }

        // Check if at least 3 cards have same suit
        if (!(counter[0] >= 3 || counter[1] >= 3 || counter[2] >= 3 || counter[3] >= 3))
            return new int[0];
        else {
            for (int i = 0; i < 4; i++) {
                if (counter[i] >= 3)
                    suit = suits[i];
            }
        }

        for (int i = 0; i < 5; i++) {
            if (counter_rank == 3){
                return pos;
            }
            // Check rank
            if (cards[i].rank == 'T' && cards[i].suit == suit) {
                pos[i] = i;
                counter_rank++;
            } else if (cards[i].rank == 'J' && cards[i].suit == suit) {
                pos[counter_rank] = i;
                counter_rank++;
            } else if (cards[i].rank == 'Q' && cards[i].suit == suit) {
                pos[counter_rank] = i;
                counter_rank++;
            } else if (cards[i].rank == 'K' && cards[i].suit == suit) {
                pos[counter_rank] = i;
                counter_rank++;
            } else if (cards[i].rank == 'A' && cards[i].suit == suit) {
                pos[counter_rank] = i;
                counter_rank++;
            }
        }

        if (counter_rank == 3)
            return pos;

        return new int[0];
    }

    private char convertInttoChar(int card) {

        char hand = '-';
        String hand_num;

        if (card < 10) {
            hand_num = Integer.toString(card);
            hand = hand_num.charAt(0);
        } else if (card == 10)
            hand = 'T';
        else if (card == 11)
            hand = 'J';
        else if (card == 12)
            hand = 'Q';
        else if (card == 13)
            hand = 'K';
        else if (card == 14)
            hand = 'A';
        else if (card == 1)
            hand = 'A';

        return hand;
    }

    private int convertChartoInt(char card, int aceValue) {

        int hand = 0;

        for (int i = 0; i < 5; i++) {
            if ((int) card < 10) {
                hand = (int) card;
            } else if (card == 'T') {
                hand = 10;
            } else if (card == 'J') {
                hand = 11;
            } else if (card == 'Q') {
                hand = 12;
            } else if (card == 'K') {
                hand = 13;
            } else if (card == 'A') {
                hand = aceValue;
            }
        }

        return hand;
    }

    private int[] checkConsecutiveOneMissingToFive(ArrayList<Integer> positions, ArrayList<Card> cardsSameSuit,
            int AceValue) {

        int size = cardsSameSuit.size();
        Card[] eval = new Card[size];
        int counter_orde = 0;
        for (int i = 0; i < size; i++) {
            eval[i] = cardsSameSuit.get(0);
            cardsSameSuit.remove(0);
        }
        int orderedcards[] = orderedCards(eval, AceValue, eval.length);
        for (int j = 0; j < size - 1; j++) {
            if (orderedcards[j] == orderedcards[j + 1] - 1) {
                counter_orde++;
            } else if (orderedcards[j] == orderedcards[j + 1] - 2) {
                counter_orde++;
            } else {
                positions.remove(j);// Só pode acontecer uma vez por isso é okay
            }
        }
        if (counter_orde == 3) {
            int pos[] = new int[4];
            for (int i = 0; i < 4; i++) {
                pos[i] = positions.get(0);
                positions.remove(0);
            }
            return pos;
        }
        return new int[0];
    }

    private int[] is4toStraightFlush(Card[] cards) {

        int counter[] = { 0, 0, 0, 0 }; // H D S C
        ArrayList<Integer> pos_H = new ArrayList<Integer>();
        ArrayList<Integer> pos_D = new ArrayList<Integer>();
        ArrayList<Integer> pos_S = new ArrayList<Integer>();
        ArrayList<Integer> pos_C = new ArrayList<Integer>();
        ArrayList<Card> H = new ArrayList<Card>();
        ArrayList<Card> D = new ArrayList<Card>();
        ArrayList<Card> S = new ArrayList<Card>();
        ArrayList<Card> C = new ArrayList<Card>();

        for (int i = 0; i < 5; i++) {
            // Check suits
            if (cards[i].suit == 'H') {
                counter[0]++;
                H.add(cards[i]);
                pos_H.add(i);
            } else if (cards[i].suit == 'D') {
                counter[1]++;
                D.add(cards[i]);
                pos_D.add(i);
            } else if (cards[i].suit == 'S') {
                counter[2]++;
                S.add(cards[i]);
                pos_S.add(i);
            } else if (cards[i].suit == 'C') {
                counter[3]++;
                C.add(cards[i]);
                pos_C.add(i);
            }
        }

        int pos[] = new int[4];
        if (counter[0] >= 4) {
            pos = checkConsecutiveOneMissingToFive(pos_H, H, 1);
            if (pos.length != 0)
                return pos;
            else {
                pos = checkConsecutiveOneMissingToFive(pos_H, H, 14);
                if (pos.length != 0)
                    return pos;
            }

        } else if (counter[1] >= 4) {
            pos = checkConsecutiveOneMissingToFive(pos_D, D, 1);
            if (pos.length != 0)
                return pos;
            else {
                pos = checkConsecutiveOneMissingToFive(pos_D, D, 14);
                if (pos.length != 0)
                    return pos;
            }

        } else if (counter[2] >= 4) {
            pos = checkConsecutiveOneMissingToFive(pos_S, S, 1);
            if (pos.length != 0)
                return pos;
            else {
                pos = checkConsecutiveOneMissingToFive(pos_S, S, 14);
                if (pos.length != 0)
                    return pos;
            }

        } else if (counter[3] >= 4) {
            pos = checkConsecutiveOneMissingToFive(pos_C, C, 1);
            if (pos.length != 0)
                return pos;
            else {
                pos = checkConsecutiveOneMissingToFive(pos_C, C, 14);
                if (pos.length != 0)
                    return pos;
            }

        }
        return new int[0];
    }

    private int[] is4toOutsideStraight(Card[] cards) {

        int orderedcards[] = orderedCards(cards, 1, cards.length);// value of ace does not matter
        int missing = 0;
        int wheremissig = -1;
        char card;
        for (int i = 0; i < 4; i++) {
            if (!(orderedcards[i] == orderedcards[i + 1] - 1)) {
                missing++;
                wheremissig = i;
            }
            if (missing > 1)
                return new int[0];
        }
        int pos[] = new int[4];
        if (wheremissig == 0) {

            for (int j = 1; j < 5; j++) {
                card = convertInttoChar(orderedcards[j]);
                for (int i = 0; i < 5; i++) {
                    if (card == cards[i].rank) {
                        pos[j-1] = i;
                    }
                }
            }
            return pos;

        } else if (wheremissig == 3) {

            for (int j = 0; j < 4; j++) {
                card = convertInttoChar(orderedcards[j]);
                for (int i = 0; i < 5; i++) {
                    if (card == cards[i].suit) {
                        pos[j] = i;
                    }
                }
            }
            return pos;

        } else
            return new int[0];
    }

    private int[] checkGapHighCard(ArrayList<Integer> positions, ArrayList<Card> cardsSameSuit,
            int AceValue) {

        int size = cardsSameSuit.size();
        Card[] eval = new Card[size];
        int counter_gap = 0;
        int count_highCard = 0;
        char ace = 'A';
        for (int i = 0; i < size; i++) {
            eval[i] = cardsSameSuit.get(0);
            cardsSameSuit.remove(0);
            if (isHighCard(eval[i])) {
                count_highCard++;
                if (AceValue == 1 && eval[i].rank == ace)
                    count_highCard--;
            }
        }
        int orderedcards[] = orderedCards(eval, AceValue, eval.length);
        for (int j = 0; j < size - 1; j++) {
            if (orderedcards[j] == orderedcards[j + 1] - 2) { // 1 gap
                counter_gap++;
            } else if (orderedcards[j] == orderedcards[j + 1] - 3) { // 2 gaps
                counter_gap = counter_gap + 2;
            } else if (orderedcards[j] == orderedcards[j + 1] - 4) { // 3 gaps-> max of gaps that can appear
                counter_gap = counter_gap + 3;
            }
        }
        if (counter_gap == 0) {
            if (orderedcards[0] == 1 || orderedcards[0] == 2) // Low ace or 234 suited
                return new int[0];
        } else if (counter_gap > 0 && counter_gap <= 3) { // Low ace
            if (orderedcards[0] == 1)
                return new int[0];
        }
        if (count_highCard >= counter_gap && count_highCard <= 3) {
            int pos[] = new int[3];
            for (int i = 0; i < 3; i++) {
                pos[i] = positions.get(0);
                positions.remove(0);
            }
            return pos;
        }
        return new int[0];
    }

    private int[] is3toStraightFlush1(Card[] cards) {

        int counter[] = { 0, 0, 0, 0 }; // H D S C
        ArrayList<Integer> pos_H = new ArrayList<Integer>();
        ArrayList<Integer> pos_D = new ArrayList<Integer>();
        ArrayList<Integer> pos_S = new ArrayList<Integer>();
        ArrayList<Integer> pos_C = new ArrayList<Integer>();
        ArrayList<Card> H = new ArrayList<Card>();
        ArrayList<Card> D = new ArrayList<Card>();
        ArrayList<Card> S = new ArrayList<Card>();
        ArrayList<Card> C = new ArrayList<Card>();

        for (int i = 0; i < 5; i++) {
            // Check suits
            if (cards[i].suit == 'H') {
                counter[0]++;
                H.add(cards[i]);
                pos_H.add(i);
            } else if (cards[i].suit == 'D') {
                counter[1]++;
                D.add(cards[i]);
                pos_D.add(i);
            } else if (cards[i].suit == 'S') {
                counter[2]++;
                S.add(cards[i]);
                pos_S.add(i);
            } else if (cards[i].suit == 'C') {
                counter[3]++;
                C.add(cards[i]);
                pos_C.add(i);
            }
        }

        int pos[] = new int[4];
        if (counter[0] == 3) {
            pos = checkGapHighCard(pos_H, H, 1);
            if (pos.length != 0)
                return pos;
            else {
                pos = checkGapHighCard(pos_H, H, 14);
                if (pos.length != 0)
                    return pos;
            }

        } else if (counter[1] == 3) {
            pos = checkGapHighCard(pos_D, D, 1);
            if (pos.length != 0)
                return pos;
            else {
                pos = checkGapHighCard(pos_D, D, 14);
                if (pos.length != 0)
                    return pos;
            }

        } else if (counter[2] == 3) {
            pos = checkGapHighCard(pos_S, S, 1);
            if (pos.length != 0)
                return pos;
            else {
                pos = checkGapHighCard(pos_S, S, 14);
                if (pos.length != 0)
                    return pos;
            }

        } else if (counter[3] == 3) {
            pos = checkGapHighCard(pos_C, C, 1);
            if (pos.length != 0)
                return pos;
            else {
                pos = checkGapHighCard(pos_C, C, 14);
                if (pos.length != 0)
                    return pos;
            }

        }
        return new int[0];
    }

    private int[] checkGap(ArrayList<Integer> positions, ArrayList<Card> cardsSameSuit,
            int AceValue) {
        
        int size = cardsSameSuit.size();
        Card[] eval = new Card[size];
        int counter_gap = 0;
        int count_highCard = 0;
        boolean flag = false;
        char ace = 'A';

        for (int i = 0; i < size; i++) {
            eval[i] = cardsSameSuit.get(0);
            cardsSameSuit.remove(0);
            if (isHighCard(eval[i])) {
                count_highCard++;
                if (AceValue == 1 && eval[i].rank == ace)
                    count_highCard--;
            }
        }
        int orderedcards[] = orderedCards(eval, AceValue, eval.length);
        for (int j = 0; j < size - 1; j++) {
            if (orderedcards[j] == orderedcards[j + 1] - 2) { // 1 gap
                counter_gap++;
            } else if (orderedcards[j] == orderedcards[j + 1] - 3) { // 2 gaps
                counter_gap = counter_gap + 2;
            } else if (orderedcards[j] == orderedcards[j + 1] - 4) { // 3 gaps-> max of gaps that can appear
                counter_gap = counter_gap + 3;
            }
        }
        if (counter_gap == 0) {
            if (orderedcards[0] == 1 || orderedcards[0] == 2) // Low ace or 234 suited
                flag = true;
        } else if (counter_gap > 0 && counter_gap <= 3) { // Low ace
            if (orderedcards[0] == 1)
                flag = true;
        }
        if (counter_gap == 2 && count_highCard == 1) { // Low ace
            flag = true;
        }
        if (flag == true) {
            int pos[] = new int[3];
            for (int i = 0; i < 3; i++) {
                pos[i] = positions.get(0);
                positions.remove(0);
            }
            return pos;
        }
        return new int[0];
    }

    private int[] is3toStraightFlush2(Card[] cards) {

        int counter[] = { 0, 0, 0, 0 }; // H D S C
        ArrayList<Integer> pos_H = new ArrayList<Integer>();
        ArrayList<Integer> pos_D = new ArrayList<Integer>();
        ArrayList<Integer> pos_S = new ArrayList<Integer>();
        ArrayList<Integer> pos_C = new ArrayList<Integer>();
        ArrayList<Card> H = new ArrayList<Card>();
        ArrayList<Card> D = new ArrayList<Card>();
        ArrayList<Card> S = new ArrayList<Card>();
        ArrayList<Card> C = new ArrayList<Card>();

        for (int i = 0; i < 5; i++) {
            // Check suits
            if (cards[i].suit == 'H') {
                counter[0]++;
                H.add(cards[i]);
                pos_H.add(i);
            } else if (cards[i].suit == 'D') {
                counter[1]++;
                D.add(cards[i]);
                pos_D.add(i);
            } else if (cards[i].suit == 'S') {
                counter[2]++;
                S.add(cards[i]);
                pos_S.add(i);
            } else if (cards[i].suit == 'C') {
                counter[3]++;
                C.add(cards[i]);
                pos_C.add(i);
            }
        }

        int pos[] = new int[4];
        if (counter[0] == 3) {
            pos = checkGap(pos_H, H, 1);
            if (pos.length != 0)
                return pos;
            else {
                pos = checkGap(pos_H, H, 14);
                if (pos.length != 0)
                    return pos;
            }

        } else if (counter[1] == 3) {
            pos = checkGap(pos_D, D, 1);
            if (pos.length != 0)
                return pos;
            else {
                pos = checkGap(pos_D, D, 14);
                if (pos.length != 0)
                    return pos;
            }

        } else if (counter[2] == 3) {
            pos = checkGap(pos_S, S, 1);
            if (pos.length != 0)
                return pos;
            else {
                pos = checkGap(pos_S, S, 14);
                if (pos.length != 0)
                    return pos;
            }

        } else if (counter[3] == 3) {
            pos = checkGap(pos_C, C, 1);
            if (pos.length != 0)
                return pos;
            else {
                pos = checkGap(pos_C, C, 14);
                if (pos.length != 0)
                    return pos;
            }

        }
        return new int[0];
    }

    private int[] check2GapNoHighCars(ArrayList<Integer> positions, ArrayList<Card> cardsSameSuit,
            int AceValue) {

        int size = cardsSameSuit.size();
        Card[] eval = new Card[size];
        int counter_gap = 0;
        int count_highCard = 0;
        char ace = 'A';

        for (int i = 0; i < size; i++) {
            eval[i] = cardsSameSuit.get(0);
            cardsSameSuit.remove(0);
            if (isHighCard(eval[i])) {
                count_highCard++;
                if (AceValue == 1 && eval[i].rank == ace)
                    count_highCard--;
            }
        }
        int orderedcards[] = orderedCards(eval, AceValue, eval.length);
        for (int j = 0; j < size - 1; j++) {
            if (orderedcards[j] == orderedcards[j + 1] - 2) { // 1 gap
                counter_gap++;
            } else if (orderedcards[j] == orderedcards[j + 1] - 3) { // 2 gaps
                counter_gap = counter_gap + 2;
            }
        }
        if (counter_gap == 2 && count_highCard == 0) {
            int pos[] = new int[3];
            for (int i = 0; i < 3; i++) {
                pos[i] = positions.get(0);
                positions.remove(0);
            }
            return pos;
        }
        return new int[0];
    }

    private int[] is3toStraightFlush3(Card[] cards) {

        int counter[] = { 0, 0, 0, 0 }; // H D S C
        ArrayList<Integer> pos_H = new ArrayList<Integer>();
        ArrayList<Integer> pos_D = new ArrayList<Integer>();
        ArrayList<Integer> pos_S = new ArrayList<Integer>();
        ArrayList<Integer> pos_C = new ArrayList<Integer>();
        ArrayList<Card> H = new ArrayList<Card>();
        ArrayList<Card> D = new ArrayList<Card>();
        ArrayList<Card> S = new ArrayList<Card>();
        ArrayList<Card> C = new ArrayList<Card>();

        for (int i = 0; i < 5; i++) {
            // Check suits
            if (cards[i].suit == 'H') {
                counter[0]++;
                H.add(cards[i]);
                pos_H.add(i);
            } else if (cards[i].suit == 'D') {
                counter[1]++;
                D.add(cards[i]);
                pos_D.add(i);
            } else if (cards[i].suit == 'S') {
                counter[2]++;
                S.add(cards[i]);
                pos_S.add(i);
            } else if (cards[i].suit == 'C') {
                counter[3]++;
                C.add(cards[i]);
                pos_C.add(i);
            }
        }

        int pos[] = new int[4];
        if (counter[0] == 3) {
            pos = check2GapNoHighCars(pos_H, H, 1);
            if (pos.length != 0)
                return pos;

        } else if (counter[1] == 3) {
            pos = check2GapNoHighCars(pos_D, D, 1);
            if (pos.length != 0)
                return pos;

        } else if (counter[2] == 3) {
            pos = check2GapNoHighCars(pos_S, S, 1);
            if (pos.length != 0)
                return pos;

        } else if (counter[3] == 3) {
            pos = check2GapNoHighCars(pos_C, C, 1);
            if (pos.length != 0)
                return pos;

        }
        return new int[0];
    }

    private int[] is4toanIS_AKQJ(Card[] cards){

        char hand[] = { cards[0].rank, cards[1].rank, cards[2].rank, cards[3].rank, cards[4].rank};
        char seen[] = { '-', '_', '.', ':' };
        int pos[] = { -1, -1, -1, -1 };
        int i = 0, j = 0, counter = 0;

        // AKQJ
        char rank[] = {'A', 'K', 'Q', 'J'};
        for(i = 0; i < 5; i++){
            for(j = 0; j < rank.length; j++){
                if(counter == 4){
                    return pos;   
                }
                if(hand[i] == rank[j]){
                    hand[i] = seen[counter];
                    pos[counter] = i;
                    counter++;
                }
            }
        }

        return new int[0];
    }

    private int[] is4toanIS_generic(Card[] cards, int X){

        int pos[] = { -1, -1, -1, -1 };
        int i = 0, j = 0, k = 0, l = 0, counter = 0, nHC = 0;

        // Ace high
        char rank[] = {'A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3', '2'};
        int reference = -1;

        for(i = 0; i < rank.length; i++){
            reference = i;
            if(counter == 4 && nHC >= X)
                return pos;
            for (j = 0; j < 5; j++){
                if(cards[j].rank == rank[reference]){
                    pos[counter] = j;
                    counter = 1;
                    if(reference < 4)
                        nHC = 1;
                    else
                        nHC = 0;
                    //System.out.println("Referencia " + rank[reference]);
                    for(k = reference + 1; k < reference + 5; k++ ){
                        for (l = 0; l < 5; l++){
                            if(j == l){ // Not sure about this
                                //System.out.println("Sao iguais ");
                                continue;
                            }
                            else if(counter == 4)
                                return pos;
                            else if(cards[l].rank == rank[k]){
                                if(reference < 4)
                                    nHC++; 
                                //System.out.println("guardei este array " + rank[k] + " positions: "+ Arrays.toString(pos) ) ;
                                //System.out.println("Counter "+ counter + " --l " + l);
                                pos[counter] = l;
                                counter++;
                                //System.out.println("Erro ? ");
                                break;
                            }

                        }
                    }
                }
            }
        }

        // Ace low

        pos = new int[] { -1, -1, -1, -1 };
        rank= new char[] {'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3', '2', 'A'};
        i = 0; j = 0; k = 0; l = 0; counter = 0; reference = -1; nHC = 0;

        for(i = 0; i < rank.length; i++){
            reference = i;
            if(counter == 4 && nHC >= X)
                return pos;
            for (j = 0; j < 5; j++){
                if(cards[j].rank == rank[reference]){
                    pos[counter] = j;
                    counter = 1;
                    if(reference < 4)
                        nHC = 1;
                    else
                        nHC = 0;
                    //System.out.println("Referencia " + rank[reference]);
                    for(k = reference + 1; k < reference + 5; k++ ){
                        for (l = 0; l < 5; l++){
                            if(j == l){ // Not sure about this
                                //System.out.println("Sao iguais ");
                                continue;
                            }
                            else if(counter == 4)
                                return pos;
                            else if(cards[l].rank == rank[k]){
                                if(reference < 4)
                                    nHC++; 
                                //System.out.println("guardei este array " + rank[k] + " positions: "+ Arrays.toString(pos) ) ;
                                //System.out.println("Counter "+ counter + " --l " + l);
                                pos[counter] = l;
                                counter++;
                                //System.out.println("Erro ? ");
                                break;
                            }

                        }
                    }
                }
            }
        }


        return new int[0];
    }

    private int[] is4toanInsideStraight_withXHC(Card[] cards, int X){
        
        int pos[] = { -1, -1, -1, -1 };

        pos = is4toanIS_AKQJ(cards);
        if (pos.length != 0) {
            return pos;
        }

        pos = is4toanIS_generic(cards, 3);
        if (pos.length != 0) {
            System.out.println("Posicoes: " + Arrays.toString(pos));
            return pos;
        }


        return new int[0];
    }


    /*private int[] is4toanInsideStraight_withXHC_1(Card[] cards, int X) {

        int i = 0, counter = 0, j = 0, missing_value = 0, pos_outlier = -1, nHC = 0;

        // Low Ace 

        int orderedcards[] = orderedCards(cards, 1, cards.length);
        // char reference = convertInttoChar(orderedcards[0]);
        int reference = orderedcards[0];
        int pos[] = { -1, -1, -1, -1 };

        // Check if there is a missing value
        for (i = 1; i < 5; i++) {
            if (orderedcards[i] == orderedcards[i - 1] + 1) {
                counter++;
            } else if (orderedcards[i] == orderedcards[i - 1] + 2) {
                counter++;
                missing_value = orderedcards[i] - 1;
            } else if (missing_value != 0)
                pos_outlier = i;
        }
        System.out.println("Cheguei aqui : " + pos_outlier + counter);

        if (counter == 3)
            orderedcards[pos_outlier] = missing_value;
        counter = 0;

        // Double check if replacing by the missing value it is an Inside Straight
        for (i = 1; i < 5; i++) {
            if (orderedcards[i] == orderedcards[i - 1] + 1) {
                counter++;
            }
        }
        if (counter != 4)
            return new int[0];

        // Store positions
        for (i = 0; i < 5; i++) {
            if (cards[i].rank == convertInttoChar(reference)) {
                if (isHighCard(cards[i]))
                    nHC++;
                pos[j] = i;
                j++;
            } else if (convertChartoInt(cards[i].rank, 1) == reference + 1) {
                if (isHighCard(cards[i]))
                    nHC++;
                pos[j] = i;
                j++;
            } else if (convertChartoInt(cards[i].rank, 1) == reference + 2) {
                if (isHighCard(cards[i]))
                    nHC++;
                pos[j] = i;
                j++;
            } else if (convertChartoInt(cards[i].rank, 1) == reference + 3) {
                if (isHighCard(cards[i]))
                    nHC++;
                pos[j] = i;
                j++;
            } else if (convertChartoInt(cards[i].rank, 1) == reference + 4) {
                if (isHighCard(cards[i]))
                    nHC++;
                pos[j] = i;
                j++;
            }
        }
        if (j == 4 && nHC == X)
            return pos;

        // High Ace 
        i = 0;
        counter = 0;
        j = 0;
        missing_value = 0;
        pos_outlier = -1;
        nHC = 0;
        orderedcards = orderedCards(cards, 14, cards.length);
        reference = orderedcards[0];
        pos = new int[] { -1, -1, -1, -1 };

        // Check if there is a missing value
        for (i = 1; i < 5; i++) {
            if (orderedcards[i] == orderedcards[i - 1] + 1) {
                counter++;
            } else if (orderedcards[i] == orderedcards[i - 1] + 2) {
                counter++;
                missing_value = orderedcards[i] - 1;
            } else if (missing_value != 0)
                pos_outlier = i;
        }

        if (counter == 3)
            orderedcards[pos_outlier] = missing_value;
        counter = 0;

        // Double check if replacing by the missing value it is an Inside Straight
        for (i = 1; i < 5; i++) {
            if (orderedcards[i] == orderedcards[i - 1] + 1) {
                counter++;
            }
        }
        if (counter != 4)
            return new int[0];

        // Store positions
        for (i = 0; i < 5; i++) {
            if (cards[i].rank == convertInttoChar(reference)) {
                if (isHighCard(cards[i]))
                    nHC++;
                pos[j] = i;
                j++;
            } else if (convertChartoInt(cards[i].rank, 14) == reference + 1) {
                if (isHighCard(cards[i]))
                    nHC++;
                pos[j] = i;
                j++;
            } else if (convertChartoInt(cards[i].rank, 14) == reference + 2) {
                if (isHighCard(cards[i]))
                    nHC++;
                pos[j] = i;
                j++;
            } else if (convertChartoInt(cards[i].rank, 14) == reference + 3) {
                if (isHighCard(cards[i]))
                    nHC++;
                pos[j] = i;
                j++;
            } else if (convertChartoInt(cards[i].rank, 14) == reference + 4) {
                if (isHighCard(cards[i]))
                    nHC++;
                pos[j] = i;
                j++;
            }
        }
        if (j == 4 && nHC == X)
            return pos;

        return new int[0];
    }*/

    /*
    private int[] is4toanInsideStraight_withXHC(Card[] cards, int X) {

        int i = 0, counter = 0, j = 0, nHC = 0;

        int pos[] = { -1, -1, -1, -1 };
        char rank[] = { 'J', 'Q', 'K', 'A' };

        System.out.println("Entreiii : " + X);
        pos = is4toanInsideStraight_withXHC_1(cards, X);
        if (pos.length != 0){
            System.out.println("Saiiiiiii");
            return pos;
        }

        // JQKA 

        for (i = 0; i < 5; i++) {
            for (j = 0; j < rank.length; j++) {
                if (cards[i].rank == rank[j]) {
                    rank[j] = '-';
                    nHC++;
                    pos[j] = i;
                    counter++;
                }
            }
        }
        if (counter == 4 && nHC >= X)
            return pos;

        // A234 
        counter = 0;
        nHC = 0;
        pos = new int[] { -1, -1, -1, -1 };
        rank = new char[] { 'A', '2', '3', '4' };

        for (i = 0; i < 5; i++) {
            for (j = 0; j < rank.length; j++) {
                if (cards[i].rank == rank[j]) {
                    rank[j] = '-';
                    nHC++;
                    pos[j] = i;
                    counter++;
                }
            }
        }
        if (counter == 4 && nHC >= X)
            return pos;

        return new int[0];
    }*/

    private int[] isXtoaFlush_withHC(Card[] cards, int X, int nbHighCard) {

        int counter[] = { 0, 0, 0, 0 }; // H D S C
        int pos[] = new int[X], HC_counter = 0, i = 0, j = 0;
        char suit = '-', suits[] = { 'H', 'D', 'S', 'C' };

        for (i = 0; i < 5; i++) {
            // Check suits
            if (cards[i].suit == 'H')
                counter[0]++;
            else if (cards[i].suit == 'D')
                counter[1]++;
            else if (cards[i].suit == 'S')
                counter[2]++;
            else if (cards[i].suit == 'C')
                counter[3]++;
        }

        if (!(counter[0] == X || counter[1] == X || counter[2] == X || counter[3] == X))
            return new int[0];
        else {
            for (i = 0; i < 4; i++) {
                // Check what suit is repeated
                if (counter[i] == X)
                    suit = suits[i];
            }
        }

        // Search for same suit cards
        for (i = 0; i < 5; i++) {
            if (cards[i].suit == suit) {
                // Store position
                pos[j] = i;
                j++;
                // Check if is high card
                if (isHighCard(cards[i]))
                    HC_counter++;
            }
        }

        if (nbHighCard == 0)
            return pos;
        else if (nbHighCard > 0 && HC_counter == nbHighCard) {
            return pos;
        }
        return new int[0];
    }

    private int[] isHighPair(Card[] cards) {

        char rank[] = { 'A', 'K', 'Q', 'J' };
        int pos[] = { -1, -1 };

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < rank.length; j++) {
                if (cards[i].rank == rank[j]) {
                    for (int k = 0; k < 5; k++) {
                        if (i == k)
                            continue;
                        if (cards[i].rank == cards[k].rank) {
                            pos[0] = i;
                            pos[1] = k;
                            return pos;
                        }
                    }
                }
            }
        }

        return new int[0];
    }

    private int[] isLowPair(Card[] cards) {

        char rank[] = { '2', '3', '4', '5', '6', '7', '8', '9', 'T' };
        int pos[] = { -1, -1 };
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < rank.length; j++) {
                if (cards[i].rank == rank[j]) {
                    for (int k = 0; k < 5; k++) {
                        if (i == k)
                            continue;
                        if (cards[i].rank == cards[k].rank) {
                            pos[0] = i;
                            pos[1] = k;
                            return pos;
                        }
                    }
                }
            }
        }

        return new int[0];
    }

    private int[] is4toAflush(Card[] cards) {

        int counter[] = { 0, 0, 0, 0 }; // H D S C
        int pos[] = { -1, -1, -1, -1 }, i = 0;
        char suit = '-', suits[] = { 'H', 'D', 'S', 'C' };

        // Check suits
        for (i = 0; i < 5; i++) {
            if (cards[i].suit == 'H')
                counter[0]++;
            else if (cards[i].suit == 'D')
                counter[1]++;
            else if (cards[i].suit == 'S')
                counter[2]++;
            else if (cards[i].suit == 'C')
                counter[3]++;
        }

        // Check if at least 4 cards have same suit
        if (!(counter[0] == 4 || counter[1] == 4 || counter[2] == 4 || counter[3] == 4))
            return new int[0];

        // Check what type of suit is repeated
        for (i = 0; i < 4; i++) {
            if (counter[i] == 4) {
                suit = suits[i];
            }
        }

        // Store positions
        for (i = 0; i < 5; i++) {
            if (cards[i].suit == suit)
                pos[i] = i;
        }

        return pos;
    }

    private int[] is2SuitedHighCards(Card[] cards) {

        char rank[] = { 'A', 'K', 'Q', 'J' };
        int pos[] = { -1, -1 };

        for (int i = 0; i < 5; i++) { // Cards
            for (int j = 0; j < rank.length; j++) { // Rank
                if (cards[i].rank == rank[j]) {
                    for (int k = 0; k < 5; k++) { // Cards
                        for (int l = 0; l < rank.length; l++) { // Rank
                            if (i == k)
                                continue;
                            if (cards[k].rank == rank[l] && cards[i].suit == cards[k].suit) {
                                pos[0] = i;
                                pos[1] = k;
                                return pos;
                            }
                        }
                    }
                }
            }
        }

        return new int[0];
    }

    private int[] isAKQJunsuited(Card[] cards) {

        int counter[] = { 0, 0, 0, 0 }, checksum;
        int pos[] = { -1, -1, -1, -1 };
        char rank[] = { 'A', 'K', 'Q', 'J' };

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                if (cards[i].rank == rank[j]) {
                    counter[j]++;
                    pos[j] = i;
                }
            }
        }

        checksum = counter[0] + counter[1] + counter[2] + counter[3];
        if (checksum < 4)
            return new int[0];

        else if (counter[0] > 0 && counter[1] > 0 && counter[2] > 0 && counter[3] > 0)
            return pos;

        return new int[0];
    }

    private int[] isXYSuited(Card[] cards, char X, char Y) {

        int pos[] = { -1, -1 };

        for (int i = 0; i < 5; i++) {
            if (cards[i].rank == X) {
                pos[0] = i;
                for (int j = 0; j < 5; j++) {
                    if (i == j)
                        continue;
                    else {
                        if (cards[j].rank == Y && cards[i].suit == cards[j].suit) {
                            pos[1] = j;
                            return pos;
                        }
                    }
                }
            }
        }

        return new int[0];
    }

    private int[] isXYUnsuited(Card[] cards, char X, char Y) {

        int pos[] = { -1, -1 };

        for (int i = 0; i < 5; i++) {
            if (cards[i].rank == X) {
                pos[0] = i;
                for (int j = 0; j < 5; j++) {
                    if (i == j)
                        continue;
                    else {
                        if (cards[j].rank == Y) {
                            pos[1] = j;
                            return pos;
                        }
                    }
                }
            }
        }

        return new int[0];
    }

    private int[] isKQJunsuited(Card[] cards) {

        int pos[] = { -1, -1, -1 }, counter[] = { 0, 0, 0 }, checksum = 0;

        for (int i = 0; i < 5; i++) {
            if (cards[i].rank == 'K') {
                counter[0]++;
                pos[0] = i;
            }
            if (cards[i].rank == 'Q') {
                counter[1]++;
                pos[1] = i;
            }
            if (cards[i].rank == 'J') {
                counter[2]++;
                pos[2] = i;
            }
        }

        checksum = counter[0] + counter[1] + counter[2];
        if (checksum > 2) {
            if (counter[0] > 0 && counter[1] > 0 && counter[2] > 0)
                return pos;
        }

        return new int[0];
    }

    private int[] isAce(Card[] cards) {

        int pos[] = new int[0];

        pos = isXofaKind(cards, 1, 'A');
        if (pos.length != 0)
            return pos;

        return new int[0];
    }

    private int[] isJQorK(Card[] cards) {

        char rank[] = { 'K', 'Q', 'J' };
        int pos[] = new int[0];

        for (int i = 0; i < rank.length; i++) {
            pos = isXofaKind(cards, 1, rank[i]);
            if (pos.length != 0)
                return pos;
        }

        return new int[0];
    }

    /**
     * Gets the hand combination
     * 
     * @param cards Hand of the player
     * @return String with the name of the hand combination
     */

    public String nameOfHand(Card[] cards) {

        int pos[] = new int[0];

        if (isRoyalFlush(cards))
            return "Royal Flush";

        if (isStraightFlush(cards))
            return "Straight Flush";

        if (isFourAces(cards))
            return "Four Aces";

        if (isFour2_4s(cards))
            return "Four 2-4";

        if (isFour5_Ks(cards))
            return "Four 5-K";

        if (isFullHouse(cards))
            return "Full House";

        if (isFlush(cards))
            return "Flush";

        if (isStraight(cards))
            return "Straight";

        pos = is3ofaKind(cards);
        if (pos.length != 0)
            return "Three of a Kind";

        pos = is2pair(cards);
        if (pos.length != 0)
            return "Two Pair";

        if (isJacksorBetter(cards))
            return "Jacks or Better";

        return "Other";
    }

    public int[] advice(Card[] cards) {

        int pos[] = new int[0];
        System.out.println("ADVICE: ");
        /* 1 - Straight flush, four of a kind, royal flush */
        if (isRoyalFlush(cards) || isStraightFlush(cards) || is4ofaKind(cards)) {
            System.out.println("Regra: " + 1);
            return new int[] { 0, 1, 2, 3, 4 };
        }
        /* 2 - 4 to a Royal Flush */
        pos = is4toRoyalFlush(cards);
        if (pos.length != 0) {
            System.out.println("Regra: " + 2);
            return pos;
        }
        /* 3 - Three Aces */
        pos = isXofaKind(cards, 3, 'A');
        if (pos.length != 0) {
            System.out.println("Regra: " + 3);
            return pos;
        }
        /* 4 - Straight flush, four of a kind, royal flush */
        if (isStraight(cards) || isFlush(cards) || isFullHouse(cards)) {
            System.out.println("Regra: " + 4);
            return new int[] { 0, 1, 2, 3, 4 };
        }
        /* 5 - Three of a kind (except aces) */
        pos = is3ofaKind(cards);
        if (pos.length != 0) {
            System.out.println("Regra: " + 5);
            return pos;
        }
        /* 6 - 4 to a Straight Flush */
        pos = is4toStraightFlush(cards);
        if (pos.length != 0) {
            System.out.println("Regra: " + 6);
            return pos;
        }
        /* 7 - Two Pair */
        pos = is2pair(cards);
        if (pos.length != 0) {
            System.out.println("Regra: " + 7);
            return pos;
        }
        /* 8 - High Pair */
        pos = isHighPair(cards);
        if (pos.length != 0) {
            System.out.println("Regra: " + 8);
            return pos;
        }
        /* 9 - 4 to a Flush */
        pos = is4toAflush(cards);
        if (pos.length != 0) {
            System.out.println("Regra: " + 9);
            return pos;
        }
        /* 10 - 3 to a Royal Flush */
        pos = is3toRoyalFlush(cards);
        if (pos.length != 0) {
            System.out.println("Regra: " + 10);
            return pos;
        }
        /* 11 - 4 to an outside straight */
        pos = is4toOutsideStraight(cards);
        if (pos.length != 0) {
            System.out.println("Regra: " + 11);
            return pos;
        }

        /* 12 - Low Pair */
        pos = isLowPair(cards);
        if (pos.length != 0) {
            System.out.println("Regra: " + 12);
            return pos;
        }

        /* 13 - AKQJ unsuited */
        pos = isAKQJunsuited(cards);
        if (pos.length != 0) {
            System.out.println("Regra: " + 13);
            return pos;
        }
        /* 14 - 3 to a straight flush (type 1) */
        pos = is3toStraightFlush1(cards);
        if (pos.length != 0) {
            System.out.println("Regra: " + 14);
            return pos;
        }
        /* 15 - 4 to an inside straight with 3 high cards */
        pos = is4toanInsideStraight_withXHC(cards, 3);
        if (pos.length != 0) {
            System.out.println("Regra: " + 15);
            return pos;
        }
        /* 16 - QJ suited */
        pos = isXYSuited(cards, 'Q', 'J');
        if (pos.length != 0) {
            System.out.println("Regra: " + 16);
            return pos;
        }
        /* 17 - 3 to a flush with 2 high cards */
        pos = isXtoaFlush_withHC(cards, 3, 2);
        if (pos.length != 0) {
            System.out.println("Regra: " + 17);
            return pos;
        }
        /* 18 - 2 suited high cards */
        pos = is2SuitedHighCards(cards);
        if (pos.length != 0) {
            System.out.println("Regra: " + 18);
            return pos;
        }
        /* 19 - 4 to an inside straight with 2 high cards */
        pos = is4toanInsideStraight_withXHC(cards, 2);
        if (pos.length != 0) {
            System.out.println("Regra: " + 19);
            return pos;
        }
        /* 20 - 3 to a straight flush (type 2) */
        pos = is3toStraightFlush2(cards);
        if (pos.length != 0) {
            System.out.println("Regra: " + 20);
            return pos;
        }
        /* 21 - 4 to an inside straight with 1 high card */
        pos = is4toanInsideStraight_withXHC(cards, 1);
        if (pos.length != 0) {
            System.out.println("Regra: " + 21);
            return pos;
        }
        /* 22 - KQJ unsuited */
        pos = isKQJunsuited(cards);
        if (pos.length != 0) {
            System.out.println("Regra: " + 22);
            return pos;
        }
        /* 23 - JT suited */
        pos = isXYSuited(cards, 'J', 'T');
        if (pos.length != 0) {
            System.out.println("Regra: " + 23);
            return pos;
        }
        /* 24 - QJ unsuited */
        pos = isXYUnsuited(cards, 'Q', 'J');
        if (pos.length != 0) {
            System.out.println("Regra: " + 24);
            return pos;
        }
        /* 25 - 3 to a flush with 1 high card */
        pos = isXtoaFlush_withHC(cards, 3, 1);
        if (pos.length != 0) {
            System.out.println("Regra: " + 25);
            return pos;
        }
        /* 26 - QT suited */
        pos = isXYSuited(cards, 'Q', 'T');
        if (pos.length != 0) {
            System.out.println("Regra: " + 26);
            return pos;
        }
        /* 27 - 3 to a straight flush (type 3) */
        pos = is3toStraightFlush3(cards);
        if (pos.length != 0) {
            System.out.println("Regra: " + 27);
            return pos;
        }
        /* 28 - KQ suited */
        pos = isXYSuited(cards, 'K', 'Q');
        if (pos.length != 0) {
            System.out.println("Regra: " + 28.1);
            return pos;
        }
        // KJ suited
        pos = isXYSuited(cards, 'K', 'J');
        if (pos.length != 0) {
            System.out.println("Regra: " + 28.2);
            return pos;
        }
        /* 29 - Ace */
        pos = isAce(cards);
        if (pos.length != 0) {
            System.out.println("Regra: " + 29);
            return pos;
        }
        /* 30 - KT suited */
        pos = isXYSuited(cards, 'K', 'T');
        if (pos.length != 0) {
            System.out.println("Regra: " + 30);
            return pos;
        }
        /* 31 - Jack, Queen or King */
        pos = isJQorK(cards);
        if (pos.length != 0) {
            System.out.println("Regra: " + 31);
            return pos;
        }
        /* 32 - 4 to an inside straight with no high cards */
        pos = is4toanInsideStraight_withXHC(cards, 0);
        if (pos.length != 0) {
            System.out.println("Regra: " + 32);
            return pos;
        }
        /* 33 - 3 to a flush with no high cards */
        pos = isXtoaFlush_withHC(cards, 3, 0);
        if (pos.length != 0) {
            System.out.println("Regra: " + 33);
            return pos;
        }
        /* 34 - Discard everything */
        System.out.println("FIM ");
        return new int[0];
    }

}