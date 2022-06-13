package poker;

import java.util.Arrays;

public class Strategy {

    public Strategy() {

    }

    public int[] orderedCards(Card[] cards, int aceValue) {

        int hand[] = new int[5];

        for (int i = 0; i < 5; i++) {
            if ((int) cards[i].rank < 10) {
                hand[i] = (int) cards[i].rank;
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

    public boolean checkifConsecutive(Card[] cards) {

        int counter = 1;

        /* Lower value Ace */
        int handLower[] = orderedCards(cards, 1);

        for (int i = 1; i < 5; i++) {
            // if(hand[i] == (hand[0]+i))
            if (handLower[i] == (handLower[i - 1] + 1))
                counter++;
        }
        if (counter == 5)
            return true;

        /* Higher value of Ace */

        int handHigher[] = orderedCards(cards, 14);
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
            pos = isXofaKind(cards, 3, rank[i]);
            if (pos != new int[0])
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
                    pos[k+1] = j;
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

        // Check if at least 4 cards have same suit
        if (!(counter[0] == 4 || counter[1] == 4 || counter[2] == 4 || counter[3] == 4))
            return new int[0];
        else {
            for (int i = 0; i < 4; i++) {
                if (counter[i] == 4)
                    suit = suits[i];
            }
        }

        for (int i = 0; i < 5; i++) {
            // Check rank
            if (cards[i].rank == 'T' && cards[i].suit == suit) {
                pos[i] = i;
                counter_rank++;
            } else if (cards[i].rank == 'J' && cards[i].suit == suit) {
                pos[i] = i;
                counter_rank++;
            } else if (cards[i].rank == 'Q' && cards[i].suit == suit) {
                pos[i] = i;
                counter_rank++;
            } else if (cards[i].rank == 'K' && cards[i].suit == suit) {
                pos[i] = i;
                counter_rank++;
            } else if (cards[i].rank == 'A' && cards[i].suit == suit) {
                pos[i] = i;
                counter_rank++;
            }
        }

        if (counter_rank == 4)
            return pos;

        return new int[0];
    }

    private int[] is3toRoyalFlush(Card[] cards) {

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

        // Check if at least 3 cards have same suit
        if (!(counter[0] == 3 || counter[1] == 3 || counter[2] == 3 || counter[3] == 3))
            return new int[0];
        else {
            for (int i = 0; i < 4; i++) {
                if (counter[i] == 3)
                    suit = suits[i];
            }
        }

        for (int i = 0; i < 5; i++) {
            // Check rank
            if (cards[i].rank == 'T' && cards[i].suit == suit) {
                pos[i] = i;
                counter_rank++;
            } else if (cards[i].rank == 'J' && cards[i].suit == suit) {
                pos[i] = i;
                counter_rank++;
            } else if (cards[i].rank == 'Q' && cards[i].suit == suit) {
                pos[i] = i;
                counter_rank++;
            } else if (cards[i].rank == 'K' && cards[i].suit == suit) {
                pos[i] = i;
                counter_rank++;
            } else if (cards[i].rank == 'A' && cards[i].suit == suit) {
                pos[i] = i;
                counter_rank++;
            }
        }

        if (counter_rank == 3)
            return pos;

        return new int[0];
    }

    /*private int[] is4toStraightFlush(Card[] cards) {

        int counter[] = { 0, 0, 0, 0 }; // H D S C
        int pos[] = { -1, -1, -1, -1 };
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

        // Check if at least 4 cards have same suit
        if (!(counter[0] == 4 || counter[1] == 4 || counter[2] == 4 || counter[3] == 4))
            return new int[0];
        else {
            for (int i = 0; i < 4; i++) {
                if (counter[i] == 4)
                    suit = suits[i];
            }
        }

        // Ace low 

        int orderedcards[] = orderedCards(cards, 1);
        int reference = orderedcards[0];
        int counter_seq = 0, acevalue = 1;

        for (int i=1; i<5; i++){
            if(reference == orderedcards[i]-i)
                counter_seq++; 
        }
        if (!(counter_seq == 3))
            return new int[0]; 
        

        // Ace high 

        orderedcards = orderedCards(cards, 14);
        reference = orderedcards[0];
        counter_seq = 0; 

        for (int i=1; i<5; i++){
            if(reference == orderedcards[i]-i)
                counter_seq++; 
        }
        if (!(counter_seq == 3))
            return new int[0];     


        return new int[0];
    }
     */




    private int[] isHighPair(Card[] cards){

        char rank[] = { 'A', 'K', 'Q', 'J' };
        int pos[] = { -1, -1};

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < rank.length; j++){
                if(cards[i].rank == rank[j]){
                    for (int k=0; k<5;k++){
                        if(i == k)
                            continue;
                        if(cards[i].rank == cards[k].rank){
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

    private int[] isLowPair(Card[] cards){

        char rank[] = { '2', '3', '4', '5', '6', '7', '8', '9', 'T'};
        int pos[] = { -1, -1};

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < rank.length ; j++){
                if(cards[i].rank == rank[j]){
                    for (int k = 0; k < 5;k++){
                        if(i == k)
                            continue;
                        if(cards[i].rank == cards[j].rank)
                            pos[0] = i;
                            pos[1] = k;
                            return pos;
                    }
                }
            } 
        }

        return new int[0];
    }

    private int[] is4toAflush(Card[] cards){
    
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
        for (i = 0; i < 4; i++){
            if(counter[i] == 4){
                suit = suits[i];
            }
        }

        // Store positions
        for (i = 0; i < 5; i++) {
            if(cards[i].suit == suit)
                pos[i] = i;
        }

        return pos;
    }

    private int[] is2SuitedHighCards(Card[] cards){

        char rank[] = { 'A', 'K', 'Q', 'J' };
        int pos[] = { -1, -1};

        for (int i = 0; i < 5; i++) { // Cards
            for (int j = 0; j < rank.length; j++){ // Rank
                if(cards[i].rank == rank[j]){
                    for (int k = 0; k < 5; k++){ // Cards
                        for (int l = 0; l < rank.length; l++){ // Rank
                            if(i == k)
                                continue;
                            if(cards[k].rank == rank[l] && cards[i].suit == cards[k].suit){
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

    private int[] isAKQJunsuited(Card[] cards){

        int counter[] = { 0, 0, 0, 0 }, checksum;
        int pos[] ={-1, -1, -1, -1};
        char rank[] = {'A', 'K', 'Q', 'J'}; 


        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 4; j++){
                if (cards[i].rank == rank[j]){
                    counter[j]++;
                    pos[j] = i;
                }
            }
        }

        checksum = counter[0] + counter[1] + counter[2] + counter[3];
        if(checksum < 4)
            return new int[0];

        else if(counter[0] > 0 && counter[1] > 0 && counter[2] > 0 && counter[3] > 0)
            return pos;

        return new int[0];
    }

    private int[] isXYSuited(Card[] cards, char X, char Y) {

        int pos[] = {-1, -1};

        for(int i = 0; i < 5; i++){
            if(cards[i].rank == X){
                pos[0] = i;
                for (int j = 0; j < 5; j++){
                    if(i == j)
                        continue;
                    else{
                        if(cards[j].rank == Y && cards[i].suit == cards[j].suit){
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

        int pos[] = {-1, -1};

        for(int i = 0; i < 5; i++){
            if(cards[i].rank == X){
                pos[0] = i;
                for (int j = 0; j < 5; j++){
                    if(i == j)
                        continue;
                    else{
                        if(cards[j].rank == Y){
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

        int pos[] = {-1, -1, -1}, counter[]={0, 0, 0}, checksum = 0;

        for(int i = 0; i < 5; i++){
            if(cards[i].rank == 'K'){
                counter[0]++;
                pos[0] = i;
            }
            if(cards[i].rank == 'Q'){
                counter[1]++;
                pos[1] = i;
            }
            if(cards[i].rank == 'J'){
                counter[2]++;
                pos[2] = i;
            }
        }

        checksum = counter[0] + counter[1] + counter[2];
        if (checksum > 2){
            if (counter[0] > 0 && counter[1] > 0 && counter[2] > 0)
                return pos;
        }

        return new int[0];
    }

    private int[] isAce(Card[] cards) {

        int pos[] = new int[0];

        pos = isXofaKind(cards, 1, 'A');
        if (pos != new int[0])
            return pos;

        return new int[0];
    }

    private int[] isJQorK(Card[] cards) {

        char rank[] = { 'K', 'Q', 'J' };
        int pos[] = new int[0];

        for (int i = 0; i < rank.length; i++) {
            pos = isXofaKind(cards, 1, rank[i]);
            if (pos != new int[0])
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
            return "Full House";

        if (isStraight(cards))
            return "Straight";

        pos = is3ofaKind(cards);
        if (pos != new int[0])
            return "Three of a Kind";

        pos = is2pair(cards);
        if (pos != new int[0])
            return "Two Pair";

        if (isJacksorBetter(cards))
            return "Jacks or Better";

        return "Other";
    }


    public int[] advice(Card[] cards) {

        int pos[] = new int[0];

        /* 1 - Straight flush, four of a kind, royal flush */
        if (isRoyalFlush(cards) || isStraightFlush(cards) || is4ofaKind(cards))
            return new int[] { 0, 1, 2, 3, 4 };

        /* 2 - 4 to a Royal Flush */
        pos = is4toRoyalFlush(cards);
        if (pos != new int[0])
            return pos;

        /* 3 - Three Aces */
        pos = isXofaKind(cards, 3, 'A');
        if (pos != new int[0])
            return pos;

        /* 4 - Straight flush, four of a kind, royal flush */
        if (isStraight(cards) || isFlush(cards) || isFullHouse(cards))
            return new int[] { 0, 1, 2, 3, 4 };

        /* 5 - Three of a kind (except aces) */
        pos = is3ofaKind(cards);
        if (pos != new int[0])
            return pos;

        /* DUVIDAAAAAAAAA 6 - 4 to a Straight Flush */

        /* 7 - Two Pair */
        pos = is2pair(cards);
        if (pos != new int[0])
            return pos;

        /* 8 - High Pair */         // Not sure if most efficient
        pos = isHighPair(cards);  
        if (pos != new int[0])
            return pos;

        /* 9 - 4 to a Flush */
        pos = is4toAflush(cards);  
        if (pos != new int[0])
            return pos;

        /* 10 - 3 to a Royal Flush */
        pos = is3toRoyalFlush(cards);
        if (pos != new int[0])
            return pos;

        /* 11 - 4 to an outside straight */

        /* 12 - Low Pair */         // Not sure if most efficient
        pos = isLowPair(cards);  
        if (pos != new int[0])
            return pos;

        /* 13 - AKQJ unsuited */
        pos = isAKQJunsuited(cards);
        if (pos != new int[0])
            return pos;

        /* 14 - 3 to a straight flush (type 1) */

        /* 15 - 4 to an inside straight with 3 high cards */

        /* 16 - QJ suited */
        pos = isXYSuited(cards, 'Q', 'J');     
        if (pos != new int[0])
            return pos;

        /* 17 - 3 to a flush with 2 high cards */

        /* 18 - 2 suited high cards */
        pos = is2SuitedHighCards(cards);
        if( pos != new int[0] )
            return pos;

        /* 19 - 4 to an inside straight with 2 high cards */

        /* 20 - 3 to a straight flush (type 2) */

        /*21 - 4 to an inside straight with 1 high card */


        /* 22 - KQJ unsuited */
        pos = isKQJunsuited(cards);
        if( pos != new int[0] )
            return pos;


        /* 23 - JT suited */
        pos = isXYSuited(cards, 'J', 'T');
        if( pos != new int[0] )
            return pos;

        /* 24 - QJ unsuited*/
        pos = isXYUnsuited(cards, 'Q', 'J');
        if( pos != new int[0] )
            return pos;

        /* 25 - 3 to a flush with 1 high card */

        /* 26 - QT suited */
        pos = isXYSuited(cards, 'Q', 'T');
        if( pos != new int[0] )
            return pos;


        /* 27 - 3 to a straight flush (type 3) */



        /* 28 - KQ suited */
        pos = isXYSuited(cards, 'K', 'Q');
        if( pos != new int[0] )
            return pos;

        // KJ suited
        pos = isXYSuited(cards, 'K', 'J');
        if( pos != new int[0] )
            return pos;

        /* 29 - Ace */
        pos = isAce(cards);
        if (pos != new int[0])
            return pos;

        /* 30 - KT suited */
        pos = isXYSuited(cards, 'K', 'T');
        if( pos != new int[0] )
            return pos;

        /* 31 - Jack, Queen or King */
        pos = isJQorK(cards);
        if (pos != new int[0])
            return pos;

        /* 32 - 4 to an inside straight with no high cards */ // hold 4

        /* 33 - 3 to a flush with no high cards */
        // hold 3

        /* 34 - Discard everything */
        return new int[0];
    }

}