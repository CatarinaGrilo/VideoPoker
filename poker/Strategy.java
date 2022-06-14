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

    public boolean isHighCard(Card card){

        char highcards[] = {'J','Q','K','A'};

        for (int i = 0; i < highcards.length; i++ ){
            if (card.rank == highcards[i])
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
                pos[counter] = i+1;
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
                    pos[k] = i+1;
                    pos[k+1] = j+1;
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
                pos[i] = i+1;
                counter_rank++;
            } else if (cards[i].rank == 'J' && cards[i].suit == suit) {
                pos[i] = i+1;
                counter_rank++;
            } else if (cards[i].rank == 'Q' && cards[i].suit == suit) {
                pos[i] = i+1;
                counter_rank++;
            } else if (cards[i].rank == 'K' && cards[i].suit == suit) {
                pos[i] = i+1;
                counter_rank++;
            } else if (cards[i].rank == 'A' && cards[i].suit == suit) {
                pos[i] = i+1;
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
                pos[i] = i+1;
                counter_rank++;
            } else if (cards[i].rank == 'J' && cards[i].suit == suit) {
                pos[i] = i+1;
                counter_rank++;
            } else if (cards[i].rank == 'Q' && cards[i].suit == suit) {
                pos[i] = i+1;
                counter_rank++;
            } else if (cards[i].rank == 'K' && cards[i].suit == suit) {
                pos[i] = i+1;
                counter_rank++;
            } else if (cards[i].rank == 'A' && cards[i].suit == suit) {
                pos[i] = i+1;
                counter_rank++;
            }
        }

        if (counter_rank == 3)
            return pos;

        return new int[0];
    }

    private char convertInttoChar(int card){

        char hand = '-';

        for (int i = 0; i < 5; i++) {
            if (card < 10)
                hand = (char) card;
            else if (card == 10)
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
        }

        return hand;
    }

    private int convertChartoInt(char card, int aceValue){
        
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

    /*
    private int[] is4toStraightFlush(Card[] cards) {

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
                // Check what suit is repeated
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

    private int[] is4toanInsideStraight_withXHC_1(Card[] cards, int X){

        int i = 0, counter = 0, j = 0, missing_value = 0, pos_outlier = -1, nHC=0;

        /* Low Ace */

        int orderedcards[] = orderedCards(cards, 1);
        //char reference = convertInttoChar(orderedcards[0]);
        int reference = orderedcards[0];
        int pos[] = { -1, -1, -1, -1 };

        // Check if there is a missing value
        for(i = 1; i < 5; i++){
            if(orderedcards[i] == orderedcards[i-1] + 1 ){
                counter++;
            }
            else if(orderedcards[i] == orderedcards[i-1] + 2){
                counter++;
                missing_value = orderedcards[i]-1;
            }
            else if(missing_value!=0)
                pos_outlier = i;
        }

        if(counter == 3)
            orderedcards[pos_outlier] = missing_value;
        counter = 0;
        
        // Double check if replacing by the missing value it is an Inside Straight
        for(i = 1; i < 5; i++){
            if(orderedcards[i] == orderedcards[i-1] + 1 ){
                counter++;
            }
        }
        if (counter != 4)
            return new int[0];

        // Store positions
        for(i = 0; i < 5; i++){
            if(cards[i].rank == convertInttoChar(reference)){
                if(isHighCard(cards[i]))
                    nHC++;
                pos[j] = i + 1;
                j++;
            }
            else if(convertChartoInt(cards[i].rank, 1) == reference + 1){
                if(isHighCard(cards[i]))
                    nHC++;
                pos[j] = i + 1;
                j++;
            }
            else if(convertChartoInt(cards[i].rank, 1) == reference + 2){
                if(isHighCard(cards[i]))
                    nHC++;
                pos[j] = i + 1;
                j++;
            }
            else if(convertChartoInt(cards[i].rank, 1) == reference + 3){
                if(isHighCard(cards[i]))
                    nHC++;
                pos[j] = i + 1;
                j++;
            }
            else if(convertChartoInt(cards[i].rank,1 ) == reference + 4){
                if(isHighCard(cards[i]))
                    nHC++;
                pos[j] = i + 1;
                j++;
            }
        }
        if (j == 4 && nHC == X)
            return pos;

        /* High Ace */
        i = 0; counter = 0; j = 0; missing_value = 0; pos_outlier = -1; nHC=0;
        orderedcards = orderedCards(cards, 14);
        reference = orderedcards[0];
        pos = new int[]{-1, -1, -1, -1 };

        // Check if there is a missing value
        for(i = 1; i < 5; i++){
            if(orderedcards[i] == orderedcards[i-1] + 1 ){
                counter++;
            }
            else if(orderedcards[i] == orderedcards[i-1] + 2){
                counter++;
                missing_value = orderedcards[i]-1;
            }
            else if(missing_value!=0)
                pos_outlier = i;
        }

        if(counter == 3)
            orderedcards[pos_outlier] = missing_value;
        counter = 0;
        
        // Double check if replacing by the missing value it is an Inside Straight
        for(i = 1; i < 5; i++){
            if(orderedcards[i] == orderedcards[i-1] + 1 ){
                counter++;
            }
        }
        if (counter != 4)
            return new int[0];

        // Store positions
        for(i = 0; i < 5; i++){
            if(cards[i].rank == convertInttoChar(reference)){
                if(isHighCard(cards[i]))
                    nHC++;
                pos[j] = i + 1;
                j++;
            }
            else if(convertChartoInt(cards[i].rank, 14) == reference + 1){
                if(isHighCard(cards[i]))
                    nHC++;
                pos[j] = i + 1;
                j++;
            }
            else if(convertChartoInt(cards[i].rank, 14) == reference + 2){
                if(isHighCard(cards[i]))
                    nHC++;
                pos[j] = i + 1;
                j++;
            }
            else if(convertChartoInt(cards[i].rank, 14) == reference + 3){
                if(isHighCard(cards[i]))
                    nHC++;
                pos[j] = i + 1;
                j++;
            }
            else if(convertChartoInt(cards[i].rank,14) == reference + 4){
                if(isHighCard(cards[i]))
                    nHC++;
                pos[j] = i + 1;
                j++;
            }
        }
        if (j == 4 && nHC == X)
            return pos;    

        return new int[0];
    }

    private int[] is4toanInsideStraight_withXHC(Card[] cards, int X){

        int i = 0, counter = 0, j = 0, nHC = 0;

        int pos[] = { -1, -1, -1, -1 };
        char rank[] = {'J','Q','K','A'};

        pos = is4toanInsideStraight_withXHC_1(cards, X);
        if (pos != new int[0])
            return pos;

       
        /* JQKA */

        for(i = 0; i < 5; i++){
            for (j=0; j<rank.length; j++){
                if(cards[i].rank == rank[j]){
                    rank[j] = '-';
                    nHC++;
                    pos[j] = i + 1;
                    counter++;
                }
            }
        }   
        if( counter == 4 && nHC >= X)      
            return pos;

        /* A234 */
        counter = 0; nHC = 0;
        pos = new int[]{-1, -1, -1, -1 };
        rank = new char[] {'A','2','3','4'};

        for(i = 0; i < 5; i++){
            for (j=0; j<rank.length; j++){
                if(cards[i].rank == rank[j]){
                    rank[j] = '-';
                    nHC++;
                    pos[j] = i + 1;
                    counter++;
                }
            }
        }   
        if( counter == 4 && nHC >= X)      
            return pos;


        return new int[0];
    }

    private int[] isXtoaFlush_withHC(Card[] cards, int X, int nbHighCard){
      
        int counter[] = { 0, 0, 0, 0 }; // H D S C
        int pos[] = new int[X], HC_counter = 0, i = 0, j=0;
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
        for (i=0; i<5; i++){
            if(cards[i].suit == suit){
                // Store position
                pos[j]=i+1;
                j++;
                //Check if is high card
                if(isHighCard(cards[i]))
                    HC_counter++;
            }
        }

        if(nbHighCard == 0)
            return pos;
        else if(nbHighCard > 0 && HC_counter == nbHighCard){
            return pos;
        }
        return new int[0];
    }

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
                            pos[0] = i+1;
                            pos[1] = k+1;
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
                            pos[0] = i+1;
                            pos[1] = k+1;
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
                pos[i] = i+1;
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
                                pos[0] = i+1;
                                pos[1] = k+1;
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
                    pos[j] = i+1;
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
                pos[0] = i+1;
                for (int j = 0; j < 5; j++){
                    if(i == j)
                        continue;
                    else{
                        if(cards[j].rank == Y && cards[i].suit == cards[j].suit){
                            pos[1] = j+1;
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
                pos[0] = i+1;
                for (int j = 0; j < 5; j++){
                    if(i == j)
                        continue;
                    else{
                        if(cards[j].rank == Y){
                            pos[1] = j+1;
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
                pos[0] = i+1;
            }
            if(cards[i].rank == 'Q'){
                counter[1]++;
                pos[1] = i+1;
            }
            if(cards[i].rank == 'J'){
                counter[2]++;
                pos[2] = i+1;
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
        //pos = is4toStraightFlush(cards);
        if (pos != new int[0])
            return pos;

        /* 7 - Two Pair */
        pos = is2pair(cards);
        if (pos != new int[0])
            return pos;

        /* 8 - High Pair */        
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

        /* 12 - Low Pair */        
        pos = isLowPair(cards);  
        if (pos != new int[0])
            return pos;

        /* 13 - AKQJ unsuited */
        pos = isAKQJunsuited(cards);
        if (pos != new int[0])
            return pos;

        /* 14 - 3 to a straight flush (type 1) */

        /* 15 - 4 to an inside straight with 3 high cards */
        pos = is4toanInsideStraight_withXHC(cards, 3);
        if (pos != new int[0])
            return pos;

        /* 16 - QJ suited */
        pos = isXYSuited(cards, 'Q', 'J');     
        if (pos != new int[0])
            return pos;

        /* 17 - 3 to a flush with 2 high cards */
        pos = isXtoaFlush_withHC(cards, 3, 2);
        if (pos != new int[0])
            return pos;

        /* 18 - 2 suited high cards */
        pos = is2SuitedHighCards(cards);
        if( pos != new int[0] )
            return pos;

        /* 19 - 4 to an inside straight with 2 high cards */
        pos = is4toanInsideStraight_withXHC(cards, 2);
        if (pos != new int[0])
            return pos;

        /* 20 - 3 to a straight flush (type 2) */

        /* 21 - 4 to an inside straight with 1 high card */
        pos = is4toanInsideStraight_withXHC(cards, 1);
        if (pos != new int[0])
            return pos;

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
        pos = isXtoaFlush_withHC(cards, 3, 1);
        if (pos != new int[0])
            return pos;


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

        /* 32 - 4 to an inside straight with no high cards */ 
        pos = is4toanInsideStraight_withXHC(cards, 0);
        if (pos != new int[0])
            return pos;

        /* 33 - 3 to a flush with no high cards */
        pos = isXtoaFlush_withHC(cards, 3, 0);
        if (pos != new int[0])
            return pos;


        /* 34 - Discard everything */
        return new int[0];
    }

}