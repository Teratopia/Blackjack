package blackJack;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	
	public ArrayList<Card> cards = new ArrayList<Card>();
	
	public void buildDeck(){
		Suit[] suits = {Suit.HEARTS, Suit.SPADES, Suit.CLUBS, Suit.DIAMONDS};
		
		for(Suit suit : suits){
			for(int i = 2 ; i <= 14 ; i++){
				String number;
				int value;
		
				switch (i){
					case 14:
						number = "Ace";
						value = 11;
						break;
					case 13:
						number = "King";
						value = 10;
						break;
					case 12:
						number = "Queen";
						value = 10;
						break;
					case 11:
						number = "Jack";
						value = 10;
						break;
					default:
						number = ""+i;
						value = i;
						break;
				}
				Card c = new Card(number, value, suit);
				cards.add(c);
			}
		}
		
	}
	public void shuffle(){
		
		Collections.shuffle(cards);
		
	}
	public void removeCard(){
		
		cards.remove(0);

	}
	

}
