package blackJack;

import java.util.Scanner;

public class TestBlackjack {

	public static void main(String[] args) {
		boolean isPlaying = true;
		Scanner scanner = new Scanner(System.in);
		PlayBlackjack game = new PlayBlackjack(scanner);
		
		game.introMenu(scanner);
		while(isPlaying){
		isPlaying = game.loopRound();
		}
		
		scanner.close();
		
//		Deck d = new Deck();
//		d.buildDeck();
//		for (Card card : d.cards) {
//			card.showBasicCard();
//		}
		
//		Shoe s = new Shoe();
//		s.buildShoe(4);
//		
//		for (Card card : s.shoe) {
//			card.showBasicCard();
//			System.out.print(" ");
//		}
		
		
	}

}
