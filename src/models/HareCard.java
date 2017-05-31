package models;

/**
 * <pre>
 * This enumeration type is used within the application to represent
 * the eight types of hare card as follows:
 *
 *    PLAYERSBEHIND     "IF THERE ARE MORE PLAYERS BEHIND YOU THAN IN FRONT OF YOU, MISS A TURN. IF NOT, PLAY AGAIN."
 *    DRAWCARROTS       "DRAW 10 CARROTS FOR EACH LETTUCE YOU STILL HOLD."
 *    RESTORECARROTS    "RESTORE YOUR CARROT HOLDING TO EXACTLY 65."
 *    LOSEHALFCARROTS   "LOSE HALF YOUR CARROTS!"
 *    FREERIDE          "FREE RIDE!"
 *    SHOWCARROTS       "SHOW US YOUR CARROTS!"
 *    GIVECARROTS       "GIVE 10 CARROTS TO EACH PLAYER LYING BEHIND YOU IN THE RACE (IF ANY)."
 *    SHUFFLECARDS      "SHUFFLE THE HARE CARDS AND RECEIVE FROM EACH PLAYER 1 CARROT FOR DOING SO."
 *
 * The details stored for a hare card are the main caption on the card together with either
 * one or two lines of additional detail text.
 * The main caption is always the first element in the text array.
 *
 * The hare card enumeration has a private constructor with a String[] parameter for
 * the text, public getters for the caption and text and an overloaded toString(indent)
 * method that returns a String representation of the hare card using the hare card
 * text and catering for caller-specified indentation for display purposes
 * within the game.
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
 * @see <a href="https://docs.oracle.com/javase/8/docs/api/">java.lang.StringBuilder</a>
 */
public enum HareCard
{
  PLAYERSBEHIND (
          new String[] {
                  "IF THERE ARE MORE PLAYERS BEHIND YOU THAN IN FRONT OF YOU, MISS A TURN. IF NOT, PLAY AGAIN.",
                  "If equal, of course play again."
          }
  ),
  DRAWCARROTS (
          new String[] {
                  "DRAW 10 CARROTS FOR EACH LETTUCE YOU STILL HOLD.",
                  "If you have none left, miss a turn."
          }
  ),
  RESTORECARROTS (
          new String[] {
                  "RESTORE YOUR CARROT HOLDING TO EXACTLY 65.",
                  "If you have more than 65, pay extras to the carrot patch; if fewer, draw extras from the carrot patch."
          }
  ),
  LOSEHALFCARROTS (
          new String[] {
                  "LOSE HALF YOUR CARROTS!",
                  "If an odd number, keep the odd one."
          }
  ),
  FREERIDE (
          new String[] {
                  "FREE RIDE!",
                  "Your last turn costs nothing; retrieve the carrots you paid to reach this square."
          }
  ),
  SHOWCARROTS (
          new String[] {
                  "SHOW US YOUR CARROTS!",
                  "Count your carrot cards face up to the table so that everyone will know how many you have left."
          }
  ),
  GIVECARROTS (
          new String[] {
                  "GIVE 10 CARROTS TO EACH PLAYER LYING BEHIND YOU IN THE RACE (IF ANY).",
                  "If you haven't enough carrots, give them five each; if still not possible, one each.",
                  "A player who doesn't want extra carrots may discard them to the 'carrot patch'."
          }
  ),
  SHUFFLECARDS (
          new String[] {
                  "SHUFFLE THE HARE CARDS AND RECEIVE FROM EACH PLAYER 1 CARROT FOR DOING SO.",
                  "Do not replace this card at the bottom of the pack but include it in the shuffle."
          }
  );

  private final String[] text;

  /**
   * Constructor for elements of the HareCard enumeration.
   *
   * @param text The array of text for the hare card.
   */
  private HareCard(String[] text)
  {
    this.text = text;
  }

  /**
   * Returns the main caption for the hare card.
   * This is always the first element in the text array.
   *
   * @return The hare card main caption.
   */
  public String getCaption()
  {
    return text[0];
  }

  /**
   * Returns the array of text for the hare card.
   *
   * @return The hare card text array.
   */
  public String[] getText()
  {
    return text;
  }

  /**
   * Returns a String representation of the hare card using the hare card text array.
   * Indentation of the display is catered for.
   *
   * @param indent The indentation to use for the display.
   * Can be any combination of space ' ' and/or tab '\t' characters.
   * If null or an empty string then no indentation is performed.
   *
   * @return A String version of the hare card using the hare card text.
   * The String returned is similar to this structure:
   *
   * <pre>
   *    GIVE 10 CARROTS TO EACH PLAYER LYING BEHIND YOU IN THE RACE (IF ANY).
   *    If you haven't enough carrots, give them five each; if still not possible, one each.
   *    A player who doesn't want extra carrots may discard them to the 'carrot patch'.
   * </pre>
   */
  public String toString(String indent)
  {
    if (indent == null)
    {
      indent = "";
    }

    StringBuilder sb = new StringBuilder();
    sb.append(String.format("%s%s", indent, text[0]));
    for (int i = 1; i < text.length; i++)
    {
      sb.append(String.format("%n%s%s", indent, text[i]));
    }

    return sb.toString();
  }
}
