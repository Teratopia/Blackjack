package blackJack;

public class Player {
	
	public String name;
	public int chips;
	public Hand hand;
	
	public Player(String name, int chips){
		
		this.name = name;
		this.chips = chips;
		this.hand = new Hand();
		
	}
	
	public void showCards(){
		System.out.print(this.getName()+"'s cards: \n\t");
		
		for (Card card : this.hand.cardsInHand) {
			card.showBasicCard();
			if(this.hand.cardsInHand.indexOf(card)!=this.hand.cardsInHand.size()-1){
			System.out.print(", ");
			}
		}
		System.out.println();
		if(this.chips!=0){
			System.out.println("\tChips: "+this.getChips());
			System.out.println();
		}
		
	}
	
	public void showDealersCards(){
		System.out.print(this.getName()+"'s showing: \n\t");
		
		for(int i = 1 ; i < this.hand.cardsInHand.size() ; i++){
			this.hand.cardsInHand.get(i).showBasicCard();
			if(i!=this.hand.cardsInHand.size()-1){
				System.out.print(", ");
				}
		}
		System.out.println();
		System.out.println();
		
	}
	
	public void winChips(int numChips){
		
		this.setChips(this.getChips()+numChips);
		
	}
	
	public void pushChips(int numChips){
		
		this.setChips(this.getChips()-numChips);
		
	}
	
	public boolean checkBust(){
		
		int total = this.hand.sumHandValues();
		
		if(total>21){
			return true;
		}else{
			return false;
		}
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getChips() {
		return chips;
	}
	public void setChips(int chips) {
		this.chips = chips;
	}
	
	

}
