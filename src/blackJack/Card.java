package blackJack;

import java.util.HashMap;

public class Card {
	
	
	private String number;
	private int value;
	private Suit suit;
	private String[] cardLines;
	
	public Card(String number, int value, Suit suit){
		
		this.number = number;
		this.value = value;
		this.suit = suit;
		
	}
	
	public void showBasicCard(){
		
		System.out.print(this.getNumber() + " of "+ this.getSuit());
		
	}

	
	public void buildCardLines(){
		String heartPip = "\u2665", spadePip = "\u2660", clubPip = "\u2663", diamondPip = "\u2666";
		
		String[][] pips = {{}, {"k"}, {"b", "t"}, {"b", "k", "t"}, {"a", "c", "s", "u"}, 
				{"a", "c", "k", "s", "u"}, {"a", "c", "j", "l", "s", "u"}, {"a", "c", "e", "j", "l", "s", "u"},
				{"a", "c", "e", "j", "l", "q", "s", "u"}, {"a", "c", "g", "i", "k", "m", "o", "s", "u"}, 
				{"a", "c", "e", "g", "i", "m", "o", "q", "s", "u"}};
		
		HashMap<String, String> pipMap = new HashMap<>(21);
		pipMap.put("a", " ");pipMap.put("b", " ");pipMap.put("c", " ");pipMap.put("d", " ");
		pipMap.put("e", " ");pipMap.put("f", " ");pipMap.put("g", " ");pipMap.put("h", " ");
		pipMap.put("i", " ");pipMap.put("j", " ");pipMap.put("k", " ");pipMap.put("l", " ");
		pipMap.put("m", " ");pipMap.put("n", " ");pipMap.put("o", " ");pipMap.put("p", " ");
		pipMap.put("q", " ");pipMap.put("r", " ");pipMap.put("s", " ");pipMap.put("t", " ");
		pipMap.put("u", " ");
		
		String number = "  ";
		String pip = "";
		int setPips = this.getValue();
		
		if(this.getSuit()==Suit.HEARTS){
			pip = heartPip;
		}else if(this.getSuit()==Suit.SPADES){
			pip = spadePip;
		}else if(this.getSuit()==Suit.DIAMONDS){
			pip = diamondPip;
		}else{
			pip = clubPip;
		}
		
		if(this.getValue()<10){
			number = this.getValue()+" ";
		}else if(this.getValue()==10){
			number = "10";
		}
		
		if(this.getNumber().equals("Ace")){
			setPips = 1;
			number = "A ";
		} else if(this.getNumber().equals("King")){
			number = "K ";
			pipMap.put("k", "K");
		}else if(this.getNumber().equals("Queen")){
			number = "Q ";
			pipMap.put("k", "Q");	
		}else if(this.getNumber().equals("Jack")){
			number = "J ";
			pipMap.put("k", "J");	
		}
		
		
		for(String s : pips[setPips]){
			pipMap.put(s, pip);
		}
		
		String horBorder = (" ----------- ");
		String leftNum = ("|"+number+"         |");
		String line1 = ("|  "+pipMap.get("a")+"  "+pipMap.get("b")+"  "+pipMap.get("c")+"  |");
		String line2 = ("|  "+pipMap.get("d")+"  "+pipMap.get("e")+"  "+pipMap.get("f")+"  |");
		String line3 = ("|  "+pipMap.get("g")+"  "+pipMap.get("h")+"  "+pipMap.get("i")+"  |");
		String line4 = ("|  "+pipMap.get("j")+"  "+pipMap.get("k")+"  "+pipMap.get("l")+"  |");
		String line5 = ("|  "+pipMap.get("m")+"  "+pipMap.get("n")+"  "+pipMap.get("o")+"  |");
		String line6 = ("|  "+pipMap.get("p")+"  "+pipMap.get("q")+"  "+pipMap.get("r")+"  |");
		String line7 = ("|  "+pipMap.get("s")+"  "+pipMap.get("t")+"  "+pipMap.get("u")+"  |");
		String rightNum = ("|         "+number+"|");
		
		String [] cardLines1 = {horBorder, leftNum, line1, line2, line3, line4, line5, line6, line7,
				rightNum, horBorder};
		
//		for (String string : cardLines) {
//			System.out.println(string);
//		}
		
		setCardLines(cardLines1);
		
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

	public String[] getCardLines() {
		return cardLines;
	}

	public void setCardLines(String[] cardLines) {
		this.cardLines = cardLines;
	}
	
	

}
