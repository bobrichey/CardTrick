/**
 * @author robertrichey
 * An object that represents a playing card from a standard 52 card deck
 */
public class Card {
	private int rank;
	private String suit;
	private int value; // rank + suit
	private String name;
	private String abbrName;
	
	
	/**
	 * Creates a card object based on a given rank, suit, and value
	 * 
	 * @param rank  the rank of the card (2-13) in a standard deck
	 * @param suit  the suit the card (Clubs, Diamonds, Hearts or Spades)
	 * @param value the value of the card compared to others in the deck
	 */
	public Card(int rank, String suit, int value) {
		this.rank = rank;
		this.suit = suit;
		this.value = value;
		setName();
		setAbbrName();
	}
	
	/**
	 * Set card name based on rank and suit
	 */
	private void setName() {
		if (rank == 1) {
			name = "Ace";
		}
		else if (rank == 11) {
			name = "Jack";
		}
		else if (rank == 12) {
			name = "Queen";
		}
		else if (rank == 13) {
			name = "King";
		}
		else {
			name = Integer.toString(rank);
		}
		name = name + " of " + suit;
	}
	
	/**
	 * Sets an abbreviated name for a given card based on rank and suit (e.g., 
	 * Ace of Spades = AS, 2 of Hearts = 2H, 10 of Clubs = 10C)
	 */
	private void setAbbrName() {
		if (rank == 10) { // handle instance of a two digit rank, non-face card
			abbrName = Integer.toString(rank) + 
					Character.toString(suit.charAt(0));
		} 
		else {
			abbrName = Character.toString(name.charAt(0)) + 
					Character.toString(suit.charAt(0));
		}
	}
	
	public int getRank() {
		return rank;
	}
	
	public String getSuit() {
		return suit;
	}
	
	public int getValue() {
		return value;
	}
	
	public String getAbbrName() {
		return abbrName;
	}
	
	@Override
	public String toString() {
		return name;
	}	
}