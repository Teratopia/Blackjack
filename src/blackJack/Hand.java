package blackJack;

import java.util.ArrayList;

public class Hand {
	
	public ArrayList<Card> cardsInHand = new ArrayList<Card>();
	
	public Hand(){}
	
	public Hand(Card c1, Card c2){
		
		this.cardsInHand.add(c1);
		this.cardsInHand.add(c2);
		
	}

	public void clearHand(){
		
		this.cardsInHand = new ArrayList<Card>();
		
	}

	public void addCardToHand(Card card){
		
		cardsInHand.add(card);
		
	}
	
	public int sumHandValues(){
		int sum = 0;
		
		for (Card card : cardsInHand) {
			sum += card.getValue();
		}
		
		return sum;
	}
}
