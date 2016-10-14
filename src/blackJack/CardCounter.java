package blackJack;

import java.util.Scanner;

public class CardCounter {
	
	private float decksRemaining;
	private float runningCount;
	
	public CardCounter(float dr){
		
		this.decksRemaining = dr;
		this.runningCount = 0;
		
	}
	
	public void alterCount(Card card){
		
		setDecksRemaining(getDecksRemaining()-(1/52));
		
		if(card.getValue()==10){
			setRunningCount(getRunningCount()+1);
		}else if(card.getValue()>6){
			
		}else{
			setRunningCount(getRunningCount()-1);
		}
		
		
	}
	
	public void guessTrueCount(Scanner scanner){
		System.out.print("Guess true count (+/-0.1): ");
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
