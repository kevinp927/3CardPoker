

import java.util.ArrayList;

public class Dealer {
	
	Deck theDeck;
	ArrayList<Card> dealersHand;

	Dealer(){
		theDeck = new Deck();
		dealersHand = new ArrayList<Card>();
	}
	
	public ArrayList<Card> dealHand(){
		ArrayList<Card> hand = new ArrayList<Card>();
		if(theDeck.size()<=34) {
			theDeck.newDeck();
		}
		for(int i=0;i<3;i++) {
			hand.add(theDeck.remove(0));
		}
		return hand;
	}
}
