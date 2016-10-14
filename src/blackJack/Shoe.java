package blackJack;

import java.util.ArrayList;
import java.util.Collections;

public class Shoe {
	
	public ArrayList<Card> shoe = new ArrayList<Card>();
	
	public void buildShoe(int numDecks){
		
		Deck[] decks = new Deck[numDecks];
		
		for(int i = 0; i < decks.length ; i++){
			
			decks[i] = new Deck();
			
		}
		
		
		for (Deck deck : decks) {
			deck.buildDeck();
			for(Card card : deck.cards){
				shoe.add(card);
			}
		}
	}
	
	public void shuffle(){
		Collections.shuffle(shoe);
	}
	
	public Card getCard(){
		Card c = shoe.get(0);
		shoe.remove(0);
		return c;
	}
	
}
