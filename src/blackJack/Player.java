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
		
		for (Card card : this.hand.cardsInHand) {
			card.showBasicCard();
		}
		
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
