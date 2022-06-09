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

        /* Falta completar esta*/
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





    public String nameOfHand(Card[] cards){

        /* Royal Flush */

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
            return "Full House"; //Ainda nao fiz esta
        
        if (isFlush(cards))
            return "Full House";

        if (isStraight(cards))
            return "Straight";

        // Falta Full House, 3 of a Kind, 2 pair, Jacks or better


        return "None";
    }


}
