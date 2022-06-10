package poker;

import java.util.Arrays;
public class Strategy {

    public Strategy(){

    }

    public int[] orderedCards(Card[] cards, int aceValue){

        int hand[] = new int[5];

        for(int i=0; i<5; i++){
            if( (int) cards[i].rank < 10){
                hand[i] = (int) cards[i].rank;
            }
            else if(cards[i].rank == 'T'){
                hand[i] = 10;
            }
            else if(cards[i].rank == 'J'){
                hand[i] = 11;
            }
            else if(cards[i].rank == 'Q'){
                hand[i] = 12;
            }
            else if(cards[i].rank == 'K'){
                hand[i] = 13;
            }
            else if(cards[i].rank == 'A'){
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

    public boolean checkifConsecutive(Card[] cards){
        
        int counter = 1;

        /* Lower value Ace */
        int handLower[] = orderedCards(cards, 1);
        
        for (int i=1; i<5; i++){ 
            //if(hand[i] == (hand[0]+i))
            if(handLower[i] == (handLower[i-1]+1))
                counter++;
        }
        if (counter == 5)
            return true;

        /* Higher value of Ace */

        int handHigher[] = orderedCards(cards, 14);
        counter = 1;

        for (int i=1; i<5; i++){ 
            //if(hand[i] == (hand[0]+i))
            if(handHigher[i] == (handHigher[i-1]+1))
                counter++;
        }
        if (counter == 5)
            return true;

        return false;        
    }

    public boolean isXofakind(Card[] cards, int X, char kind){

        int counter = 0;

        for(int i=0; i<5; i++){
            if(cards[i].rank == kind)
                counter++;
        }

        if(counter == X){
            return true;
        }
        return false;
    }


    /* Functions that determine the name of the hand */

    public boolean isRoyalFlush(Card[] cards){

        int i=0, counter=0, counter_T=0, counter_J=0, counter_Q=0, counter_K=0, counter_A=0;

        // Check if cards are the same suit
        if (allSameSuit(cards)){
            for (i = 0; i < 5; i++) {
                if(cards[i].rank == 'T' && counter_T==0)
                    counter_T++;
                if(cards[i].rank == 'J' && counter_J==0)
                    counter_J++;
                if(cards[i].rank == 'Q' && counter_Q==0)
                    counter_Q++;
                if(cards[i].rank == 'K' && counter_K==0)
                    counter_K++;
                if(cards[i].rank == 'A' && counter_A==0)
                    counter_A++;
            }
            counter = counter_T + counter_J + counter_Q + counter_K + counter_A;
            if (counter == 5){
                return true;
            }
        }
        return false;
    }

    public boolean isStraightFlush(Card[] cards){

        // Check if cards are the same suit
        if (allSameSuit(cards)){
            if(checkifConsecutive(cards))
                return true;
        }
        return false;
    }

    public boolean isFourAces(Card[] cards){

        if(isXofakind(cards, 4, 'A'))
            return true;        
        
        return false;
    }

    public boolean isFour2_4s(Card[] cards){

        if(isXofakind(cards, 4,  '2'))
            return true;  
        if(isXofakind(cards, 4, '3'))
            return true;
        if(isXofakind(cards, 4, '4'))
            return true;        
        
        return false;
    }

    public boolean isFour5_Ks(Card[] cards){

        if(isXofakind(cards, 4, '5'))
            return true;  
        if(isXofakind(cards, 4, '6'))
            return true;
        if(isXofakind(cards, 4, '7'))
            return true;        
        if(isXofakind(cards, 4, '8'))
            return true;  
        if(isXofakind(cards, 4, '9'))
            return true;
        if(isXofakind(cards, 4, 'T'))
            return true;        
        if(isXofakind(cards, 4, 'J'))
            return true;  
        if(isXofakind(cards, 4, 'Q'))
            return true;
        if(isXofakind(cards, 4, 'K'))
            return true;        
        
        return false;
    }

    public boolean isFullHouse(Card[] cards){

        char rank[] = {'1', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A'};
        int counter_3 = 0, counter_2 = 0;

        for(int i = 0; i<rank.length; i++){
            if(isXofakind(cards, 3, rank[i])){
                rank[i] = '-';
                counter_3 ++;
            }
        }

        for(int i = 0; i<rank.length; i++){
            if(isXofakind(cards, 2, rank[i])){
                counter_2 ++;
            }
        }

        if(counter_3 == 1 && counter_2 == 1)
            return true;

        return false;
    }

    public boolean isFlush(Card[] cards){

        if (allSameSuit(cards))
                return true;

        return false;
    }

    public boolean isStraight(Card[] cards){

        if (checkifConsecutive(cards))
                return true;

        return false;
    }

    public boolean is3ofaKind(Card[] cards){

        char rank[] = {'1', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A'};

        for(int i = 0; i < rank.length; i++){
            if(isXofakind(cards, 3,  rank[i]))
                return true;
        }

        return false;

    }

    public boolean is2pair(Card[] cards){

        char hand[] = {cards[0].rank, cards[1].rank, cards[2].rank, cards[3].rank, cards[4].rank};
        char seen[] = {'-', '_', '.', ':'};
        int no_pairs = 0, k = 0;

        
        for(int j = 0; j < 4; j++){
            for(int i = 0; i < 5; i++){
                if(i == j)
                    continue;
                if(hand[j] == hand[i]){
                    hand[j] = seen[k];
                    hand[i] = seen[k+1];
                    no_pairs++;
                    k=k+2;
                }
            }
        }

        if(no_pairs == 2)
            return true;

        return false;
    }

    public boolean isJacksorBetter(Card[] cards){

        if(isXofakind(cards, 2, 'J'))
            return true;

        if(isXofakind(cards, 2, 'Q'))
            return true;

        if(isXofakind(cards, 2, 'K'))
            return true;

        if(isXofakind(cards, 2, 'A'))
            return true;

        return false;
    }


    /**
	 * Gets the hand combination 
	 * @param cards Hand of the player
	 * @return String with the name of the hand combination 
	 */

    public String nameOfHand(Card[] cards){

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

        if (is3ofaKind(cards))
            return "Three of a Kind";    

        if (is2pair(cards))         
            return "Two Pair";    

        if(isJacksorBetter(cards))  
            return "Jacks or Better";

        return "None";
    }


}
