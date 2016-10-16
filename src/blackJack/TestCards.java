package blackJack;

public class TestCards {
	
	public static void main(String[] args) {
		
		Deck d = new Deck();
		
		d.buildDeck();
		
		for (Card card : d.cards) {
			card.buildCardLines();
		}
		
		for(int i = 0; i < 11 ; i++){
			for(Card card : d.cards){
				System.out.print(card.getCardLines()[i]);
			}
			System.out.println();
		}
		
	}

}
