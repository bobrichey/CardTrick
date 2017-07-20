# CardTrick
Performs the role of assistant in a card trick that requires two people

Written as an exercise in Java, this program plays the role of a magician's assistant when performing the card trick described in the linked paper below. A random five card hand is drawn from a deck of cards, and four of the five cards are displayed for the magician in such a way that the magician is able to correctly identify the hidden fifth card. A quick explanation of the trick is as follows:

The assistant picks two cards of the same suit from the five card hand, and selects the card that is six or less steps away from the other as the hidden card. For example, given the 2 and 8 of Clubs, the 8 of Clubs will be chosen as the hidden card.

The 2 of Clubs is revealed first, communicating to the magician that the hidden card must be a Club.

Next, the assistant reveals three more cards in an order (based on card rank) such that it communicates to the magician what number to add to the first card in order to deduce the hidden card. The scheme suggested in the paper is this:

Cards are revealed small, middle, large: add 1

Cards are revealed small, large, middle: add 2

Cards are revealed middle, small, large: add 3

Cards are revealed middle, large, small: add 4

Cards are revealed large, small, middle: add 5

Cards are revealed large, middle, small: add 6

In the example given above, the cards would be revealed in the order of large, middle, small (2 + 6 = 8 of Clubs).

As suggested in the paper, the program has been written so that the card that communicates the suit of the hidden card is revealed in the i%4 position, where i is the number of times the trick has been performed in front of a given audience. This eliminates the pattern of the hidden card and first card revealed being a suited pair.

Link to paper, originally found in a Stack Exchange thread: http://www.apprendre-en-ligne.net/crypto/magie/card.pdf

Aforementioned thread: https://mathoverflow.net/questions/9754/magic-trick-based-on-deep-mathematics?rq=1
