
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
	public CardCounter cardCounter;
	public int pushCount;

	public PlayBlackjack(Scanner scanner) {

		this.scanner = scanner;

	}

	// Lists options, initiates new round, and sets values for initial chips,
	// number of decks in shoe, player constructor, dealer constructor, and card
	// counter constructor.
	public void introMenu(Scanner scanner) {

		System.out.println("Let's play Blackjack!\n");
		System.out.print("How many decks in the shoe?  ");
		int numDecks = scanner.nextInt();
		System.out.print("What's your name?  ");
		String name = scanner.next();
		System.out.print("How many chips would you like?  ");
		int chips = scanner.nextInt();
		System.out.println("\nGreat, let's play! Ante is " + chips / 100 + ".\n");

		this.setInitialChips(chips);
		shoe.buildShoe(numDecks);
		shoe.shuffle();
		player = new Player(name, chips);
		dealer = new Player("Dealer", 0);
		cardCounter = new CardCounter(numDecks);
		newRound();

	}

	// Bets ante for player then multiplies by two for dealers ante, deals two
	// cards to player and
	// dealer, passes values of both players cards and one of dealer's cards to
	// cardCounter, then
	// displays starting cards.
	public void newRound() {
		int ante = initialChips / 100;
		player.pushChips(ante);
		kitty = ante * 2;

		dealer.hand.clearHand();
		player.hand.clearHand();

		Card card = shoe.getCard();
		cardCounter.alterCount(card);
		player.hand.addCardToHand(card);

		card = shoe.getCard();
		dealer.hand.addCardToHand(card);

		card = shoe.getCard();
		cardCounter.alterCount(card);
		player.hand.addCardToHand(card);

		card = shoe.getCard();
		cardCounter.alterCount(card);
		dealer.hand.addCardToHand(card);
		showCards();

	}

	// Prompts user to bet, hit, stay, or guess count. If user input starts with
	// b, user is prompted
	// for bet amount, then whether to hit or stay (if a negative value is
	// entered the program calls
	// for security). Else if user input starts with H a new card is added to
	// the player's hand and
	// passed to cardCounter's alterCount method. If input starts with S a push
	// counter is incremented
	// to count the number of stays. If input starts with G cardCounter's
	// guessTrueCount method is called,
	// then promptChoice is called again.
	public void promptChoice(Scanner scanner) {

		System.out.print("Would you like to 'bet', 'hit', 'stay', or 'guess count'?  ");
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
					Card card = shoe.getCard();
					cardCounter.alterCount(card);
					player.hand.addCardToHand(card);
				} else if (input2.startsWith("S")) {
					System.out.println(player.getName() + " stays.");
					pushCount = 1;
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
			pushCount = 1;
		} else if (input.startsWith("G")) {

			cardCounter.guessTrueCount(scanner);
			promptChoice(scanner);

		} else {

			System.out.println("I'm sorry, I don't understand.");
			promptChoice(scanner);
		}

	}

	// Sums values for all dealer's cards. If sum is 21, dealer shows all cards
	// and the card previously
	// hidden is passed to cardCounter's alterCount method. Else if the value is
	// greater than 16 the
	// dealer stays and pushCounter is incremented again. If the sum is greater
	// than 16 and the sum of
	// the player's cards is greater than the dealer's cards then the player
	// wins, all cards are shown,
	// the dealer's hidden card is passed to cardCounter's alterCount method,
	// and the value of the kitty
	// is added to the player's chip total. If the dealer stays, the value of
	// both hands is equal, and
	// the pushCount is equal to two then the round is a push, and the player
	// get 1/2 the value of the
	// kitty. Game ending scenarios return false, otherwise the method returns
	// true.
	public boolean dealersChoice() {
		int dealerValues = dealer.hand.sumHandValues();

		if (dealerValues == 21) {
			System.out.println("Dealer has blackjack.");
			dealer.showComplexCards();
			cardCounter.alterCount(dealer.hand.cardsInHand.get(0));
			return false;
		} else if (dealerValues > 15) {
			System.out.println("Dealer stays.");
			pushCount++;
			if (player.hand.sumHandValues() > dealerValues && player.hand.sumHandValues() < 22) {
				System.out.println("\t\t...and " + player.getName() + " wins!");
				player.showComplexCards();
				dealer.showComplexCards();
				cardCounter.alterCount(dealer.hand.cardsInHand.get(0));
				player.winChips(kitty);
				return false;
			} else if (dealerValues == player.hand.sumHandValues() && pushCount == 2) {
				System.out.println("Push.");
				player.winChips(kitty / 2);
				kitty = 0;
				return false;
			}
			return true;
		} else if (dealerValues <= 15) {
			System.out.println("Dealer hits.");
			Card card = shoe.getCard();
			cardCounter.alterCount(card);
			dealer.hand.addCardToHand(card);
			return true;
		} else {
			System.err.println("nopenopenope");
			return false;
		}
	}

	// Starts loop while boolean isPlaying is true. Inside loop pushCount is
	// reset, then promptChoice is
	// called. If neither player busts as indicated by checkBusts then
	// dealersChoice is called. If a
	// game ending scenario does not result then checkBusts is called again,
	// then both players cards are
	// shown. When loop ends promptPlayAgain determines value of isPlaying. If
	// true, newRound is called
	// followed by loopRound, until promptPlayAgain returns false. If false, the
	// player is told their
	// winnings and loopRound returns false to break the loop in
	// TestBlackjack's main method.
	public boolean loopRound() {
		while (isPlaying = true) {
			pushCount = 0;
			promptChoice(scanner);
			if (checkBusts() == true) {
				break;
			}
			if (dealersChoice() == false) {
				break;
			}
			if (checkBusts() == true) {
				break;
			}
			showCards();
		}
		isPlaying = promptPlayAgain(scanner);
		if (isPlaying == true) {
			System.out.println();
			newRound();
			if (loopRound() == false) {
				return false;
			} else {
				return true;
			}
		} else {
			System.out.println("\nTotal winnings: " + (player.getChips() - getInitialChips()) + " chips.");
			System.out.println("  Thanks for playing!");
			return false;
		}
	}

	// Checks the value of both players' hands, if greater than 21 then the
	// method returns true, else it
	// returns false. If the dealer busts the dealer shows all cards, the hidden
	// card is passed to
	// cardCounter's alterCount method, and the player adds the value of the
	// kitty to their chips total.
	// If either player busts and is holding an ace, the value of the ace is set
	// to one and the method
	// returns false.
	public boolean checkBusts() {

		if (player.checkBust() == true) {
			for (Card card : player.hand.cardsInHand) {
				if (card.getValue() == 11) {
					card.setValue(1);
					return false;
				}
			}

			System.out.println("You bust! Dealer takes " + kitty / 2 + " chips!");
			showCards();
			kitty = 0;
			return true;
		}

		if (dealer.checkBust() == true) {
			for (Card card : dealer.hand.cardsInHand) {
				if (card.getValue() == 11) {
					card.setValue(1);
					return false;
				}
			}
			System.out.println("Dealer busts! You win " + kitty + " chips!");
			dealer.showComplexCards();
			cardCounter.alterCount(dealer.hand.cardsInHand.get(0));
			System.out.println();
			player.winChips(kitty);
			showCards();
			kitty = 0;
			return true;
		}

		return false;

	}

	// Prompts player to play again, if yes the method returns true, if no the
	// method returns false.
	public boolean promptPlayAgain(Scanner scanner) {
		System.out.print("Ante is " + initialChips / 100 + ", would you like to play again? ('yes'/'no')  ");
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

	// Shows the values of both players' cards, minus one for the dealer.
	public void showCards() {

		player.showComplexCards();
		// dealer.showCards();
		dealer.showComplexDealersCards();

	}

	public int getInitialChips() {
		return initialChips;
	}

	public void setInitialChips(int initialChips) {
		this.initialChips = initialChips;
	}
}
