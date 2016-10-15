package blackJack;

import java.util.Scanner;

public class TestBlackjack {

	//Creates scanner, new blackjack game, boolean isPlaying. Blackjack game's introMenu method is
	//called once, then loopRound loops and determines the value of isPlaying. If the player quits,
	//isPlaying is set to false, the loop ends, and the scanner closes.
	public static void main(String[] args) {
		boolean isPlaying = true;
		Scanner scanner = new Scanner(System.in);
		PlayBlackjack game = new PlayBlackjack(scanner);
		
		game.introMenu(scanner);
		while(isPlaying){
		isPlaying = game.loopRound();
		}
		
		scanner.close();	
		
	}

}
