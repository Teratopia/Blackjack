
package blackJack;

import java.util.Scanner;

public class PlayBlackjack {

	public Shoe shoe;
	public int kitty;
	public Player player;
	public Player dealer;
	public boolean isPlaying = true;

	public void introMenu() {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Let's play Blackjack!\n");
		System.out.print("How many decks in the shoe?  ");
		int numDecks = scanner.nextInt();
		System.out.print("What's your name?  ");
		String name = scanner.next();
		System.out.print("How many chips would you like?  ");
		int chips = scanner.nextInt();
		System.out.println("\nGreat, let's play.");

		shoe.buildShoe(numDecks);
		shoe.shuffle();
		player = new Player(name, chips);
		dealer = new Player("Dealer", 0);

		scanner.close();
	}

	public void newRound() {

		dealer.hand.clearHand();
		player.hand.clearHand();

		player.hand.addCardToHand(shoe.getCard());
		dealer.hand.addCardToHand(shoe.getCard());
		player.hand.addCardToHand(shoe.getCard());
		dealer.hand.addCardToHand(shoe.getCard());

	}

	public void promptChoice() {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Would you like to 'bet', 'hit', or 'stay'?  ");
		String input = scanner.next().toUpperCase();
		if (input.startsWith("B")) {
			System.out.print("\nHow much?  ");
			int betAmount = scanner.nextInt();
			if (betAmount > 0) {
				kitty += betAmount;
				player.pushChips(betAmount);
				System.out.println("Hit or stay?  ");
				String input2 = scanner.next().toUpperCase();
				if (input2.startsWith("H")) {
					System.out.println(player.getName() + " hits.");
					player.hand.addCardToHand(shoe.getCard());
				} else if (input2.startsWith("S")) {
					System.out.println(player.getName() + " stays.");
				} else {
					System.out.println("I'm sorry, I don't understand.");
					promptChoice();
				}
			} else {
				System.out.println("THIEF! SECURITY!!");
				isPlaying = false;
			}
		} else if (input.startsWith("H")) {
			System.out.println(player.getName() + " hits.");
			player.hand.addCardToHand(shoe.getCard());
		} else if (input.startsWith("S")) {
			System.out.println(player.getName() + " stays.");
		} else {
			System.out.println("I'm sorry, I don't understand.");
			promptChoice();
		}

		scanner.close();
	}

	public void dealersChoice(){
		int dealerValues = dealer.hand.sumHandValues();
		
		if(dealerValues>16){
			System.out.println("Dealer stays.");
		}else if(dealerValues<=16){
			System.out.println("Dealer hits.");
			Card card = shoe.getCard();
			dealer.hand.addCardToHand(card);
		}
		
		
	}
	
	public void loopRound(){
		boolean hasBust = false;
		newRound();
		while(hasBust = false){
			promptChoice();
			
			
		}
		
	}
}
