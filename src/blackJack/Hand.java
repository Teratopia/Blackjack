package blackJack;

import java.util.ArrayList;

public class Hand {
	
	public ArrayList<Card> cardsInHand = new ArrayList<Card>();
	
	public Hand(){}
	
	public Hand(Card c1, Card c2){
		
		this.cardsInHand.add(c1);
		this.cardsInHand.add(c2);
		
	}

	//Removes all cards from hand
	public void clearHand(){
		
		this.cardsInHand = new ArrayList<Card>();
		
	}

	//Adds card to cardsInHand
	public void addCardToHand(Card card){
		
		cardsInHand.add(card);
		
	}
	
	//Adds values of all cards in cardsInHand
	public int sumHandValues(){
		int sum = 0;
		
		for (Card card : cardsInHand) {
			sum += card.getValue();
		}
		
		return sum;
	}
}
