package models;

import java.util.Random;

/**
 * <pre>
 * This class is used within the application to represent the deck of Hare Cards.
 *
 * The deck is maintained internally as an array of 15 HareCard enumeration types
 * according to the following table:
 *
 *   Hare Card                    Quantity
 *   -------------------------------------
 *   HareCard.PLAYERSBEHIND              2
 *   HareCard.DRAWCARROTS                2
 *   HareCard.RESTORECARROTS             2
 *   HareCard.LOSEHALFCARROTS            2
 *   HareCard.FREERIDE                   2
 *   HareCard.SHOWCARROTS                2
 *   HareCard.GIVECARROTS                2
 *   HareCard.SHUFFLECARDS               1
 *   -------------------------------------
 *   Total Cards                        15
 *   -------------------------------------
 *
 * Apart from the default constructor which initializes the deck array and
 * getters and setters for the deck array and top card indicator, the class
 * exposes the following methods:
 *
 *   draw()                : Returns the top hare card from the deck
 *   shuffle()             : Shuffles the deck of hare cards
 *
 * See below for details of source materials referenced during development of this module.
 * </pre>
 *
 * @author Mark McDonald (20077698@mail.wit.ie)
 * @version 1.0 (06/04/2017)
 *
 * @see <a href="http://docs.oracle.com/javase/tutorial/java/javaOO/enum.html">Oracle Enum Tutorial</a>
 * @see <a href="http://javarevisited.blogspot.ie/2011/08/enum-in-java-example-tutorial.html">Enum in Java</a>
 * @see <a href="https://docs.oracle.com/javase/8/docs/api/">java.lang.Enum<E></a>
 * @see <a href="https://docs.oracle.com/javase/8/docs/api/">java.lang.IllegalArgumentException</a>
 * @see <a href="https://docs.oracle.com/javase/8/docs/api/">java.util.Random</a>
 */
public final class HareCards
{
  public static final int LENGTH = 15;

  private static final Random random = new Random();

  private HareCard[] deck = new HareCard[LENGTH];
  private int topCard = 0;

  /**
   * Default constructor for the class.
   *
   * Populate the deck with the hare cards as per the
   * following table then shuffle the deck.
   *
   *   Hare Card                    Quantity
   *   -------------------------------------
   *   HareCard.PLAYERSBEHIND              2
   *   HareCard.DRAWCARROTS                2
   *   HareCard.RESTORECARROTS             2
   *   HareCard.LOSEHALFCARROTS            2
   *   HareCard.FREERIDE                   2
   *   HareCard.SHOWCARROTS                2
   *   HareCard.GIVECARROTS                2
   *   HareCard.SHUFFLECARDS               1
   *   -------------------------------------
   *   Total Cards                        15
   *   -------------------------------------
   */
  public HareCards()
  {
    deck[0]  = HareCard.PLAYERSBEHIND;
    deck[1]  = HareCard.PLAYERSBEHIND;
    deck[2]  = HareCard.DRAWCARROTS;
    deck[3]  = HareCard.DRAWCARROTS;
    deck[4]  = HareCard.RESTORECARROTS;
    deck[5]  = HareCard.RESTORECARROTS;
    deck[6]  = HareCard.LOSEHALFCARROTS;
    deck[7]  = HareCard.LOSEHALFCARROTS;
    deck[8]  = HareCard.FREERIDE;
    deck[9]  = HareCard.FREERIDE;
    deck[10] = HareCard.SHOWCARROTS;
    deck[11] = HareCard.SHOWCARROTS;
    deck[12] = HareCard.GIVECARROTS;
    deck[13] = HareCard.GIVECARROTS;
    deck[14] = HareCard.SHUFFLECARDS;

    //Shuffle the deck
    shuffle();
  }

  /**
   * Gets the deck of hare cards.
   *
   * @return The deck of hare cards.
   */
  public HareCard[] getDeck()
  {
    return deck;
  }

  /**
   * Sets the deck of hare cards to the value passed as a parameter.
   * The new deck cannot be null or empty.
   * The top card indicator is reset to zero if necessary.
   *
   * @param deck The new deck of hare cards.
   */
  public void setDeck(HareCard[] deck) throws IllegalArgumentException
  {
    if ((deck == null) || (deck.length == 0))
    {
      throw new IllegalArgumentException("Deck cannot be an empty deck.");
    }
    this.deck = deck;
    //Keep topCard in line if the new deck is smaller
    setTopCard(topCard);
  }

  /**
   * Gets the top card indicator for the deck.
   *
   * @return The top card indicator for the deck.
   */
  public int getTopCard()
  {
    return topCard;
  }

  /**
   * Sets the top card indicator for the deck to the value passed as a parameter.
   * If the new value specified is not a valid index for the deck then the
   * top card indicator is reset to zero.
   *
   * @param topCard The new top card indicator for the deck.
   */
  public void setTopCard(int topCard)
  {
    if (validIndex(topCard))
    {
      this.topCard = topCard;
    }
    else
    {
      this.topCard = 0;
    }
  }

  /**
   * This method returns the top hare card from the deck i.e. the card
   * at the index specified by the top card indicator for the deck.
   *
   * @return The top hare card from the deck.
   */
  public HareCard draw()
  {
    //The SHUFFLECARDS hare card will always eventually be drawn after each shuffle thus
    //ensuring that an attempt to access cards[cards.length] never normally occurs.
    //But best to add the validation anyway ... just in case!
    if (!validIndex(topCard))
    {
      topCard = 0;
    }
    return deck[topCard++];
  }

  /**
   * This method shuffles the hare cards in the deck.
   * Cards are allocated to each of the array slots in turn by selecting
   * a card at random from the as yet unallocated cards and performing
   * a swap when necessary (i.e. when the selected card is not already
   * in the slot being allocated a card).
   */
  public void shuffle()
  {
    /*
     * deck.length = 15
     *
     *  i  size     r      j
     * -----------------------
     *  0   15    0-14    0-14
     *  1   14    0-13    1-14
     *  2   13    0-12    2-14
     *  3   12    0-11    3-14
     *  4   11    0-10    4-14
     *  5   10     0-9    5-14
     *  6    9     0-8    6-14
     *  7    8     0-7    7-14
     *  8    7     0-6    8-14
     *  9    6     0-5    9-14
     * 10    5     0-4   10-14
     * 11    4     0-3   11-14
     * 12    3     0-2   12-14
     * 13    2     0-1   13-14
     *
     * Stop when only two cards left to allocate (size = 2)
    */
    for (int i = 0; i < deck.length - 1; i++)
    {
      //size = deck.length - i   : size of cards remaining for selection
      //r = random.nextInt(size) : random number from 0 to size - 1 inclusive
      //j = i + r                : index of selected card for element i
      int j = i + random.nextInt(deck.length - i);

      //Swap the cards if different
      if (i != j)
      {
        HareCard card = deck[i];
        deck[i] = deck[j];
        deck[j] = card;
      }
    }
    //Reset the top card indicator
    topCard = 0;
  }

  /**
   * Checks if the specified index is valid.
   *
   * The index is valid only if it is greater than -1
   * (i.e. at least zero) and less than the length of
   * the deck array.
   *
   * @param index The index value to be validated.
   *
   * @return True if the index is valid, otherwise False.
   */
  private boolean validIndex(int index)
  {
    return ((-1 < index) && (index < deck.length));
  }
}
