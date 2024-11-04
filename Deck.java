

import java.util.ArrayList;
import java.util.Collections;

public class Deck extends ArrayList<Card>{
	
	char[] suits = {'C', 'D', 'S', 'H'};

	Deck(){
		addCards();
		Collections.shuffle(this);
	}
	
	void newDeck(){
		this.clear();
		addCards();
		Collections.shuffle(this);
	}
	
	void addCards() {
		for(int j=0;j<4;j++) {
			for(int i=2;i<=14;i++) {
				Card newCard = new Card(suits[j], i);
				this.add(newCard);
			}
		}
	}
}
