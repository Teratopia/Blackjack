
package blackJack;

import java.util.Scanner;

public class PlayBlackjack {

	public Scanner scanner = new Scanner(System.in);
	public Shoe shoe = new Shoe();
	public int kitty = 0;
	public Player player;
	public Player dealer;
	public boolean isPlaying = true;
	public int initialChips = 0;

	public PlayBlackjack(Scanner scanner) {

		this.scanner = scanner;

	}

	public void introMenu(Scanner scanner) {

		System.out.println("Let's play Blackjack!\n");
		System.out.print("How many decks in the shoe?  ");
		int numDecks = scanner.nextInt();
		System.out.print("What's your name?  ");
		String name = scanner.next();
		System.out.print("How many chips would you like?  ");
		int chips = scanner.nextInt();
		System.out.println("\nGreat, let's play! Ante is "+chips/100+".\n");

		this.setInitialChips(chips);
		shoe.buildShoe(numDecks);
		shoe.shuffle();
		player = new Player(name, chips);
		dealer = new Player("Dealer", 0);
		newRound();

	}

	public void newRound() {
		int ante = initialChips/100;
		player.pushChips(ante);
		kitty = ante*2;
		
		dealer.hand.clearHand();
		player.hand.clearHand();

		player.hand.addCardToHand(shoe.getCard());
		dealer.hand.addCardToHand(shoe.getCard());
		player.hand.addCardToHand(shoe.getCard());
		dealer.hand.addCardToHand(shoe.getCard());
		showCards();

	}

	public void promptChoice(Scanner scanner) {

		System.out.print("Would you like to 'bet', 'hit', or 'stay'?  ");
		String input = scanner.next().toUpperCase();

		if (input.startsWith("B")) {
			System.out.print("\nHow much?  ");
			int betAmount = scanner.nextInt();
			if (betAmount > 0) {
				kitty += betAmount * 2;
				player.pushChips(betAmount);
				System.out.print("Hit or stay?  ");
				String input2 = scanner.next().toUpperCase();
				if (input2.startsWith("H")) {
					System.out.println(player.getName() + " hits.");
					player.hand.addCardToHand(shoe.getCard());
				} else if (input2.startsWith("S")) {
					System.out.println(player.getName() + " stays.");
				} else {
					System.out.println("I'm sorry, I don't understand.");
					promptChoice(scanner);
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
			promptChoice(scanner);
		}

	}

	public boolean dealersChoice() {
		int dealerValues = dealer.hand.sumHandValues();

		if (dealerValues == 21) {
			System.out.println("Dealer has blackjack.");
			return false;
		} else if (dealerValues >= 16) {
			System.out.println("Dealer stays.");
			if (player.hand.sumHandValues() > dealerValues) {
				System.out.println("\t\t...and " + player.getName() + " wins!");
				player.showCards();
				dealer.showCards();
				player.winChips(kitty);
				kitty = 0;
				return false;
			}else if(dealerValues == player.hand.sumHandValues()){
				System.out.println("Push.");
				player.winChips(kitty/2);
				kitty = 0;
				return false;
			}
			return true;
		} else if (dealerValues <= 16) {
			System.out.println("Dealer hits.");
			Card card = shoe.getCard();
			dealer.hand.addCardToHand(card);
			return true;
		} else {
			System.err.println("nopenopenope");
			return false;
		}
	}

	public boolean loopRound() {
		while (isPlaying = true) {
			promptChoice(scanner);
			if(checkBusts() == true){
				break;
			}
			if(dealersChoice()==false){
				break;
			}
			if(checkBusts() == true){
				break;
			}
			showCards();
		}
		isPlaying = promptPlayAgain(scanner);
		if (isPlaying == true) {
			System.out.println();
			newRound();
			loopRound();
			return true;
		}else{
			System.out.println("\nTotal winnings: "+(player.getChips()-getInitialChips())+" chips.");
			System.out.println("  Thanks for playing!");
			isPlaying = false;
			return false;
		}
	}

	public boolean checkBusts() {

		if (player.checkBust() == true) {
			System.out.println("You bust! Dealer takes " + kitty/2 + " chips!");
			showCards();
			kitty = 0;
			return true;
		}
		if (dealer.checkBust() == true) {
			System.out.println("Dealer busts! You win " + kitty + " chips!");
			dealer.showCards();
			System.out.println();
			player.winChips(kitty);
			showCards();
			kitty = 0;
			return true;
		}
		
		return false;

	}

	public boolean promptPlayAgain(Scanner scanner) {
		System.out.print("Ante is "+initialChips/100+", would you like to play again? ('yes'/'no')  ");
		String input = scanner.next().toUpperCase();

		if (input.startsWith("Y")) {
			return true;
		} else if (input.startsWith("N")) {
			return false;
		} else {
			System.out.println("I'm sorry, I don't understand.");
			promptPlayAgain(scanner);
		}

		return true;

	}

	public void showCards() {

		player.showCards();
		dealer.showDealersCards();

	}

	public int getInitialChips() {
		return initialChips;
	}

	public void setInitialChips(int initialChips) {
		this.initialChips = initialChips;
	}
}
