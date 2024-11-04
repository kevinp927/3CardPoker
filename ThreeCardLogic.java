

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ThreeCardLogic {
	
	public static int evalHand(ArrayList<Card> hand) {
		hand = sortList(hand);
		return handType(hand);
	}
	
	public static int evalPPWinnings(ArrayList<Card> hand, int bet) {
		hand = sortList(hand);
		int handType = handType(hand);
		
		if(handType == 5) {
			return bet * 2;
		}
		else if(handType == 4) {
			return ((bet * 3) + bet);
		}
		else if(handType == 3) {
			return ((bet * 6) + bet);
		}
		else if(handType == 2) {
			return ((bet * 30) + bet);
		}
		else if(handType == 1) {
			return ((bet * 40) + bet);
		}
				
		return 0;
	}

	public static int compareHands(ArrayList<Card> dealer, ArrayList<Card> player) {
		dealer = sortList(dealer);
		player = sortList(player);
		
		int dealerHandRank = handTypeInOrder(dealer);
		int playerHandRank = handTypeInOrder(player);
		
		if(dealerHandRank == 0) { // checks for dealer queen high
			if(dealer.get(2).value<12) {
				return 0; 
			}
		}
		if(dealerHandRank > playerHandRank) { // checks is dealer won
			return 1;
		}
		else if(dealerHandRank < playerHandRank) { // checks if player won
			return 2;
		}	
		else {
			return handleTies(dealer, player, dealerHandRank, playerHandRank); // handles the ties
		}
	}
	
	static ArrayList<Card> sortList(ArrayList<Card> hand){ // sorts the cars in order
		
		for(int i=0;i<hand.size();i++){
		    for(int j=i+1;j<hand.size();j++){
		
			    Card tempI = hand.get(i);
			    Card tempJ = hand.get(j);
			
			    if(tempI.value>tempJ.value){
				    Card tmp = hand.get(i);
				    hand.set(i,hand.get(j)) ;
				    hand.set(j,tmp);
			    }
		    }
		}
		return hand;
	}
	
	static boolean checkStraightFlush(ArrayList<Card> hand) { 
		if(checkFlush(hand) && checkStraight(hand)) { // checks for flush and straight
			return true;
		}
		return false;
	}
	
	static boolean checkThreeOfAKind(ArrayList<Card> hand) {
		if(hand.get(0).value == hand.get(1).value && hand.get(1).value == hand.get(2).value) { // checks if all the values are the same
			return true;
		}
		return false;
	}
	
	static boolean checkStraight(ArrayList<Card> hand) {
		if(hand.get(0).value + 1 == hand.get(1).value && hand.get(1).value + 1 == hand.get(2).value) { // checks if all the values are the same
			return true;
		}
		return false;
	}
	
	static boolean checkFlush(ArrayList<Card> hand) {
		if(hand.get(0).suit == hand.get(1).suit && hand.get(1).suit == hand.get(2).suit) { // checks if all the suits are the same
			return true;
		}
		return false;
	}
	
	static boolean checkPair(ArrayList<Card> hand) {
		if(hand.get(0).value == hand.get(1).value || hand.get(1).value == hand.get(2).value || hand.get(0).value == hand.get(2).value){ // checks for pair
			return true;
		}
		return false;
	}
	
	static int handType(ArrayList<Card> hand) { // returns the value associated with the hand
		if(checkStraightFlush(hand)) {
			return 1;
		}
		else if(checkThreeOfAKind(hand)) {
			return 2;
		}
		else if(checkStraight(hand)) {
			return 3;
		}
		else if(checkFlush(hand)) {
			return 4;
		}
		else if(checkPair(hand)) {
			return 5;
		}
	
		return 0;
	}
	
	static int handTypeInOrder(ArrayList<Card> hand) { // returns the value associated with the hand
		if(checkStraightFlush(hand)) {
			return 5;
		}
		else if(checkThreeOfAKind(hand)) {
			return 4;
		}
		else if(checkStraight(hand)) {
			return 3;
		}
		else if(checkFlush(hand)) {
			return 2;
		}
		else if(checkPair(hand)) {
			return 1;
		}
	
		return 0;
	}
	
	static int handleTies(ArrayList<Card> dealer, ArrayList<Card> player, int dealerHandRank, int playerHandRank) {
		// handles the ties for each rank
		if(dealerHandRank == 0) {
			if(dealer.get(2).value < 12) {
				return 0;
			}
			else {
				return compareAllCards(dealer, player);
			}
		}
		else if(dealerHandRank == 1) {
			if(dealer.get(1).value > player.get(1).value) {
				return 1;
			}
			else if(dealer.get(1).value < player.get(1).value) {
				return 2;
			}
			else {
				int valueOfPair = dealer.get(1).value;
				int dealerPostionNonPair = -1;
				if(valueOfPair != dealer.get(0).value) {
					dealerPostionNonPair = 0;
				}
				else if(valueOfPair != dealer.get(2).value) {
					dealerPostionNonPair = 2;
				}
				int playerPostionNonPair = -1;
				if(valueOfPair != player.get(0).value) {
					playerPostionNonPair = 0;
				}
				else if(valueOfPair != player.get(2).value) {
					playerPostionNonPair = 2;
				}
				if(dealer.get(dealerPostionNonPair).value > player.get(playerPostionNonPair).value) {
					return 1;
				}
				else if(dealer.get(dealerPostionNonPair).value < player.get(playerPostionNonPair).value) {
					return 2;
				}
				else {
					return 0;
				}
			}
		}
		else if(dealerHandRank == 2 || dealerHandRank == 3 || dealerHandRank == 4 || dealerHandRank == 5) {
			return compareAllCards(dealer, player);
		}
		
		return 0;
	}
	
	static int compareAllCards(ArrayList<Card> dealer, ArrayList<Card> player) {
		
		// compares the value of each card and return who won
		for(int i=2; i>=0; i--) {
			if(dealer.get(i).value > player.get(i).value) {
				return 1;
			}
			else if(dealer.get(i).value < player.get(i).value) {
				return 2;
			}
		}
		return 0;
		
	}
}


