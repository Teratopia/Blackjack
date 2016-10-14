package blackJack;

public class Card {
	
	
	private String number;
	private int value;
	private Suit suit;
	
	public Card(String number, int value, Suit suit){
		
		this.number = number;
		this.value = value;
		this.suit = suit;
		
	}
	
	public void showBasicCard(){
		
		System.out.print(this.getNumber() + " of "+ this.getSuit());
		
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Suit getSuit() {
		return suit;
	}

	public void setSuit(Suit suit) {
		this.suit = suit;
	}
	
	

}
