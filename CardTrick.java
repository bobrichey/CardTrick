import java.util.Random;
import java.util.Scanner;

/**
 * @author robertrichey
 * 
 * DATE: 6/25/2017
 * 
 * DESCRIPTION: Displays four of five cards of a randomly drawn hand. The user
 * is challenged to guess the hidden card based on the ones that have been 
 * revealed. A count of consecutive correct guesses is maintained.
 */
public class CardTrick {
	public static void main(String[] args) {
		Random rand = new Random();
		int numberOfTurns = 0;
		int correctGuesses = 0;
		String s;
		Scanner input = new Scanner(System.in);
		
		System.out.println("Below are four cards of a random five card hand."
				+ "\nEnter the abbreviated name of the fifth card (e.g., AS for"
				+ " Ace of Spades, 2C for 2 of Clubs):");
		System.out.println();
		
		do {
			Card[] hand = makeHand(rand);
			pick2(hand);
			
			int stepsToCard;
			Card hiddenCard;
			
			// determine the hidden card and the number of steps from its neighbor
			if (hand[3].getRank() - hand[4].getRank() > 6) { // wraps around 13/Ace
				hiddenCard = hand[4];
				stepsToCard = 13 - (hand[3].getRank() - hand[4].getRank());
			} 
			else { // does not wrap
				hiddenCard = hand[3];
				stepsToCard = hand[3].getRank() - hand[4].getRank();
			}
			orderHand(hand, stepsToCard, hiddenCard, numberOfTurns++);
			
			// print first four cards
			for (int i = 0; i < hand.length - 1; i++) {
				System.out.println(hand[i]);
			}
			System.out.println();
			
			// Prompt guess of hidden card
			System.out.print("Hidden card: ");
			String guess = input.nextLine();
			
			// Check for correctness and reveal
			if (guess.equals(hiddenCard.getAbbrName())) {
				correctGuesses++;
				System.out.print("Correct: ");
			}
			else {
				correctGuesses = 0;
				System.out.print("Incorrect: ");
			}
			System.out.println(hiddenCard);
			System.out.println();
			
			// Print stats and prompt further play
			System.out.println("Consecutive correct guesses: " + correctGuesses);
			System.out.println();
			
			System.out.print("Press Enter to play again. Enter \"q\" to quit: ");
			s = input.nextLine();
			System.out.println();
		} while (!s.equals("q"));
		input.close();
	}
	
	/**
	 * Orders a hand of cards so they may be revealed in an order that allows
	 * the magician/user to correctly guess the hidden card
	 * 
	 * @param hand          a Card array to be ordered
	 * @param stepsToCard   number of steps between the revealed and hidden card
	 * @param hiddenCard    the card to be guessed by the magician/user
	 * @param numberOfTurns number of times the trick has been performed 
	 */
	public static void orderHand(Card[] hand, int stepsToCard, Card hiddenCard,
			int numberOfTurns) {
		// order first three cards based on steps to hidden card
		switch (stepsToCard) {
		case 1: // s-m-l
			orderCards(hand, 0, 1, 2);
			break;
		case 2: // s-l-m
			orderCards(hand, 0, 2, 1);
			break;
		case 3: // m-s-l
			orderCards(hand, 1, 0, 2);
			break;
		case 4: // m-l-s
			orderCards(hand, 2, 0, 1);
			break;
		case 5: // l-s-m
			orderCards(hand, 1, 2, 0);
			break;
		case 6: // l-m-s
			orderCards(hand, 2, 1, 0);
			break;
		default:
			System.out.println("ERROR: " + stepsToCard);
			break;
		}
		
		// place hidden card last in hand
		if (hand[4] != hiddenCard) {
			swap(hand, 4, 3);
		}
		
		// place 4th card amongst first 3
		int index = numberOfTurns % 4;
		Card temp = hand[3];
		
		for (int i = 3; i > index; i--) {
			hand[i] = hand[i - 1];
		}
		hand[index] = temp;
	}
	
	/**
	 * Orders three Card objects based on their value
	 * 
	 * @param hand   the array of Cards to be sorted
	 * @param left   index of first card
	 * @param center index of second card
	 * @param right  index of third card
	 */
	private static void orderCards(Card[] hand, int left, int center, int right) {
		if (isGreater(hand[left], hand[center])) {
			swap(hand, left, center);
		}
		if (isGreater(hand[left], hand[right])) {
			swap(hand, left, right);
		}
		if (isGreater(hand[center], hand[right])) {
			swap(hand, center, right);
		}
	}
	
	/**
	 * Compares two Cards based on their value
	 * 
	 * @param a the first Card in the comparison
	 * @param b the second Card in the comparison
	 * @return  true if Card a is greater than Card b
	 */
	private static boolean isGreater(Card a, Card b) {
		if (a.getRank() == b.getRank()) {
			return a.getValue() > b.getValue();
		} 
		else {
			return a.getRank() > b.getRank();
		}
	}
	
	/**
	 * Swaps two Card objects in an array
	 * 
	 * @param arr an array containing Cards to be swapped
	 * @param a   first Card to be swapped 
	 * @param b   second Card to be swapped
	 */
	private static void swap(Card[] arr, int a, int b) {
		Card temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}

	/**
	 * Searches a hand to a find pair of Cards of the same suit. They are 
	 * then placed at end of hand with higher rank first.
	 * 
	 * @param hand an array of Cards to be searched
	 */
	public static void pick2(Card[] hand) {
		for (int i = 0; i < hand.length - 1; i++) {
			for (int j = i + 1; j < hand.length; j++) {
				if (hand[i].getSuit().equals(hand[j].getSuit())) {
					// swap j (higher index) first, else i could could swapped w/ j
					swap(hand, 4, j);
					swap(hand, 3, i);
					if (hand[3].getRank() < hand[4].getRank()) {
						swap(hand, 3, 4);
					}
				}
			}
		}
	}
	
	/**
	 * Creates a random five card hand
	 * 
	 * @param rand a Random object used for Card selection
	 * @return     an array of five randomly selected Cards
	 */
	public static Card[] makeHand(Random rand) {
		Card[] deck = makeDeck();
		
		int handSize = 5;
		Card[] hand = new Card[handSize];
		int cardsInHand = 0;
		
		while (cardsInHand < handSize) {
			int pick = rand.nextInt(deck.length);
			Card c = deck[pick];
			
			if (c != null) {
				hand[cardsInHand++] = c;
				deck[pick] = null;
			}
		}
		return hand;
	}
	
	/**
	 * Creates a standard 52 card deck of playing cards, assigning value from 
	 * least to greatest by card rank and suit (alphabetically)
	 * 
	 * @return an array containing 52 Card objects
	 */
	private static Card[] makeDeck() {
		final int DECK_SIZE = 52;
		Card[] deck = new Card[DECK_SIZE];
		String[] suits = {"Clubs", "Diamonds", "Hearts", "Spades"};
		int cardsPerSuit = deck.length / suits.length; // 13 in typical deck
		
		for (int i = 0; i < suits.length; i++) {
			for (int j = 0; j < cardsPerSuit; j++) {
				int index = j + i * cardsPerSuit; // 0-51
				// add 1 to get ranks 1-13
				deck[index] = new Card(j + 1, suits[i], index);
			}
		}
		return deck;
	}
}