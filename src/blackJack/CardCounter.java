package blackJack;

import java.util.Scanner;

public class CardCounter {
	
	private float decksRemaining;
	private float runningCount;
	
	public CardCounter(float dr){
		
		this.decksRemaining = dr;
		this.runningCount = 0;
		
	}
	
	//Alters the value of decksRemaining and running total according to the value of the card passed.
	//If 10 or greater, running total adds one. If six or below runningCount is lowered by one. Every
	//time alterCount is called decksRemaining is lowered by 1/52, representing 1/52 of
	//one deck.
	public void alterCount(Card card){
		
		setDecksRemaining(getDecksRemaining()-(1/52));
		
		if(card.getValue()==10 || card.getValue()==11){
			setRunningCount(getRunningCount()+1);
		}else if(card.getValue()>6){
			
		}else{
			setRunningCount(getRunningCount()-1);
		}
		
		
	}
	
	//Prompts user for guess, then compares input with trueCount. If input is within .1 of trueCount
	//the user is correct and shown the count value. Otherwise they are told they are too low or too
	//high.
	public void guessTrueCount(Scanner scanner){
		System.out.print("Guess true count (+/- .1): ");
		float guess = scanner.nextFloat();
		float tc = getTrueCount();
		
		if(guess>=(.9*tc) && guess <=(1.1+tc)){
			System.out.println("CORRECT! Count is: "+tc+".");
		}else if(guess<(.9*tc)){
			System.out.println("Too low...");
		}else{
			System.out.println("Too high...");
		}
		
	}

	//Calculates and returns true count--running count / decks remaining.
	public float getTrueCount() {
		
		float tc = getRunningCount()/getDecksRemaining();
		
		return tc;
	}

	public float getDecksRemaining() {
		return decksRemaining;
	}

	public void setDecksRemaining(float decksRemaining) {
		this.decksRemaining = decksRemaining;
	}

	public float getRunningCount() {
		return runningCount;
	}

	public void setRunningCount(float runningCount) {
		this.runningCount = runningCount;
	}
	

}
