package utils;

import java.util.*;

/**
 * <pre>
 * This class is used within the application to represent the Race Card.
 *
 * The class maintains move and cost data for moves between 0 and 40.
 *
 * Since the RaceCard data does not vary from game to game, and so will not need to be
 * serialized with each Game instance, the RaceCard class has been implemented as a
 * utility class with static methods provided to facilitate both the move -> cost lookup
 * and the cost -> move lookup as well as the displaying of the RaceCard data.
 *
 * The move -> cost lookup is implemented using a Map<Integer, Integer> (Hashtable<>).
 * cost = move * (move + 1) / 2
 *
 * The cost -> move lookup is implemented using a NavigableMap<Integer, Integer> (TreeMap<>).
 * maxMove = (-1 + SQRT(1 + (8 * FLOOR(cost)))) / 2
 * where FLOOR(cost) is the maximum key in the NavigableMap less than or equal to cost.
 *
 * The RaceCard data may be displayed in two formats: basic and intelligent.
 * The basic display shows all the race card data for all the moves from 1 to 40.
 * The intelligent display shows all the moves from 1 to 40 but only displays the costs
 * for those moves that are valid moves for the player to make.
 *
 * The class makes use of a static initializer block to populate the lookup maps with
 * the required move and cost data.
 *
 * The class contains a private default constructor as it is not intended for this class
 * to be instantiated.
 *
 * The class is marked as final as it is not intended for this class to be extended.
 *
 * The class exposes the following public static methods:
 *
 *   raceCardInit()                                 : Called at application startup to ensure static initializer is executed.
 *   raceCardCostForMove(move)                      : Returns the cost (carrots) for the specified move (number of squares).
 *   raceCardMaxMoveForCost(cost)                   : Returns the maximum move (number of squares) for the specified cost.
 *   raceCardDisplayBasic()                         : Returns the basic String representation of the race card.
 *   raceCardDisplayBasic(indent)                   : Returns the basic String representation of the race card using indentation.
 *   raceCardDisplayIntelligent(validMoves)         : Returns the intelligent String representation of the race card.
 *   raceCardDisplayIntelligent(validMoves, indent) : Returns the intelligent String representation of the race card using indentation.
 *
 * See below for details of source materials referenced during development of this module.
 * </pre>
 *
 * @author Mark McDonald (20077698@mail.wit.ie)
 * @version 1.0 (06/04/2017)
 *
 * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html">Static Initialization Tutorial</a>
 * @see <a href="http://www.developer.com/java/other/article.php/2238491/The-Essence-of-OOP-using-Java-Static-Initializer-Blocks.htm">Static Initializer Blocks</a>
 * @see <a href="http://javarevisited.blogspot.ie/2013/01/what-is-navigablemap-in-java-6-example-submap-head-tail.html">NavigableMap in Java</a>
 * @see <a href="https://docs.oracle.com/javase/8/docs/api/">java.lang.IllegalArgumentException</a>
 * @see <a href="https://docs.oracle.com/javase/8/docs/api/">java.lang.String</a>
 * @see <a href="https://docs.oracle.com/javase/8/docs/api/">java.lang.StringBuilder</a>
 * @see <a href="https://docs.oracle.com/javase/8/docs/api/">java.util.Collection</a>
 * @see <a href="https://docs.oracle.com/javase/8/docs/api/">java.util.Formatter</a>
 * @see <a href="https://docs.oracle.com/javase/8/docs/api/">java.util.HashMap</a>
 * @see <a href="https://docs.oracle.com/javase/8/docs/api/">java.util.HashSet</a>
 * @see <a href="https://docs.oracle.com/javase/8/docs/api/">java.util.Hashtable</a>
 * @see <a href="https://docs.oracle.com/javase/8/docs/api/">java.util.Map</a>
 * @see <a href="https://docs.oracle.com/javase/8/docs/api/">java.util.NavigableMap</a>
 * @see <a href="https://docs.oracle.com/javase/8/docs/api/">java.util.TreeMap</a>
 */
public final class RaceCard
{
  public static final int MAX_MOVES = 40;

  private static final NavigableMap<Integer, Integer> costs = new TreeMap<>();
  private static final Map<Integer, Integer> moves = new Hashtable<>();

  private static boolean initialized = false;

  /**
   * Static initializer block for the RaceCard class.
   *
   * Will be executed only once immediately before the first static method is called.
   *
   * Populate the moves and costs maps with the race card data.
   */
  static
  {
    int cost = 0;
    for (int i = 0; i <= MAX_MOVES; i++)
    {
      //Cost for n moves is n(n+1)/2 but summing is faster
      cost += i;
      costs.put(cost, i);
      moves.put(i, cost);
    }
    //Class is now initialized
    initialized = true;
  }

  /**
   * Default constructor for the class.
   *
   * RaceCard class should not be instantiated.
   */
  private RaceCard()
  {
  }

  /**
   * This method should be called once in the application sub main() in order
   * to trigger the once-only execution of the static initializer block.
   * Otherwise the first call to a static method on this class will achieve
   * the same result, but better to perform the initialization on start up.
   *
   * @return The value of the initialized flag which should be True.
   */
  public static boolean raceCardInit()
  {
    return initialized;
  }

  /**
   * This method returns the cost for the specified move.
   * If the specified move is negative then zero is returned (backward move costs nothing).
   * If the specified move is between 0 and 40 inclusive then the cost for that move is returned.
   * If the specified move is greater than 40 then an IllegalArgumentException is thrown.
   *
   * @param move The move (number of squares) to get the cost (in carrots) for.
   *
   * @return The cost for the specified move.
   *
   * @throws IllegalArgumentException if specified move is greater than 40.
   */
  public static int raceCardCostForMove(int move) throws IllegalArgumentException
  {
    if (move < 0)
    {
      //Backward move costs nothing
      return 0;
    }
    if (moves.containsKey(move))
    {
      //Valid move between 0 and 40 inclusive so return the associated cost
      return moves.get(move);
    }
    //Move is greater than 40
    throw new IllegalArgumentException(String.format("Invalid move specified: %s. Maximum move is %s.", move, MAX_MOVES));
  }

  /**
   * This method returns the maximum moves (number of squares forward) for the specified cost.
   * If the specified cost is negative then an IllegalArgumentException is thrown.
   * Any cost greater than or equal to zero will return a maximum move up to
   * and including the maximum moves that can be made in the game (i.e. 40).
   *
   * @param cost The cost to get the maximum moves for.
   *
   * @return The maximum moves for the specified cost.
   *
   * @throws IllegalArgumentException if specified cost is negative.
   */
  public static int raceCardMaxMoveForCost(int cost) throws IllegalArgumentException
  {
    if (cost < 0)
    {
      //Cost must be greater than or equal to zero
      throw new IllegalArgumentException(String.format("Invalid cost specified: %s. Cost cannot be less than zero.", cost));
    }
    //NavigableMap costs caters for all costs greater than or equal to zero
    return costs.floorEntry(costs.floorKey(cost)).getValue();
  }

  /**
   * Returns a basic String representation of the race card.
   * All costs are displayed regardless of the valid moves
   * the player can make.
   * No indentation of the display is catered for.
   *
   * @return A String version of the race card. The String
   * returned is similar to this structure:
   *
   * <pre>
   *                   RACE CARD
   *                   =========
   *  Cost of moving: Each square you move forward
   *  on the board costs you carrots as follows:
   *   -------------------- --------------------
   *  |  Squares  Cost in  |  Squares  Cost in  |
   *  |   moved   carrots  |   moved   carrots  |
   *   -------------------- --------------------
   *  |     1         1    |    21       231    |
   *  |     2         3    |    22       253    |
   *  |     3         6    |    23       276    |
   *  |     4        10    |    24       300    |
   *  |     5        15    |    25       325    |
   *  |     6        21    |    26       351    |
   *  |     7        28    |    27       378    |
   *  |     8        36    |    28       406    |
   *  |     9        45    |    29       435    |
   *  |    10        55    |    30       465    |
   *  |    11        66    |    31       496    |
   *  |    12        78    |    32       528    |
   *  |    13        91    |    33       561    |
   *  |    14       105    |    34       595    |
   *  |    15       120    |    35       630    |
   *  |    16       136    |    36       666    |
   *  |    17       153    |    37       703    |
   *  |    18       171    |    38       741    |
   *  |    19       190    |    39       780    |
   *  |    20       210    |    40       820    |
   *   -------------------- --------------------
   * </pre>
   */
  public static String raceCardDisplayBasic()
  {
    //No validMoves or indentation by default
    return raceCardDisplayIntelligent(null, null);
  }

  /**
   * Returns a basic String representation of the race card.
   * All costs are displayed regardless of the valid moves
   * the player can make.
   * Indentation of the display is catered for.
   *
   * @param indent The indentation to use for the display.
   * Can be any combination of space ' ' and/or tab '\t' characters.
   * If null or an empty string then no indentation is performed.
   *
   * @return A String version of the race card. The String
   * returned is similar to this structure:
   *
   * <pre>
   *                   RACE CARD
   *                   =========
   *  Cost of moving: Each square you move forward
   *  on the board costs you carrots as follows:
   *   -------------------- --------------------
   *  |  Squares  Cost in  |  Squares  Cost in  |
   *  |   moved   carrots  |   moved   carrots  |
   *   -------------------- --------------------
   *  |     1         1    |    21       231    |
   *  |     2         3    |    22       253    |
   *  |     3         6    |    23       276    |
   *  |     4        10    |    24       300    |
   *  |     5        15    |    25       325    |
   *  |     6        21    |    26       351    |
   *  |     7        28    |    27       378    |
   *  |     8        36    |    28       406    |
   *  |     9        45    |    29       435    |
   *  |    10        55    |    30       465    |
   *  |    11        66    |    31       496    |
   *  |    12        78    |    32       528    |
   *  |    13        91    |    33       561    |
   *  |    14       105    |    34       595    |
   *  |    15       120    |    35       630    |
   *  |    16       136    |    36       666    |
   *  |    17       153    |    37       703    |
   *  |    18       171    |    38       741    |
   *  |    19       190    |    39       780    |
   *  |    20       210    |    40       820    |
   *   -------------------- --------------------
   * </pre>
   */
  public static String raceCardDisplayBasic(String indent)
  {
    //No validMoves by default
    return raceCardDisplayIntelligent(null, indent);
  }

  /**
   * Returns the intelligent String representation of the race card.
   * Costs are only displayed for the valid moves the player can make.
   * No indentation of the display is catered for.
   *
   * @param validMoves The collection of valid moves the player can make
   * (i.e. a collection containing the numbers of squares forward that
   * the player can make that are valid moves).
   * If validMoves is null then the basic representation is returned.
   *
   * @return An intelligent String version of the race card. The String
   * returned is similar to this structure:
   *
   * <pre>
   *                   RACE CARD
   *                   =========
   *  Cost of moving: Each square you move forward
   *  on the board costs you carrots as follows:
   *   -------------------- --------------------
   *  |  Squares  Cost in  |  Squares  Cost in  |
   *  |   moved   carrots  |   moved   carrots  |
   *   -------------------- --------------------
   *  |     1         1    |    21       231    |
   *  |     2         3    |    22       253    |
   *  |     3         -    |    23       276    |
   *  |     4         -    |    24       300    |
   *  |     5        15    |    25       325    |
   *  |     6        21    |    26         -    |
   *  |     7        28    |    27         -    |
   *  |     8        36    |    28         -    |
   *  |     9        45    |    29         -    |
   *  |    10         -    |    30         -    |
   *  |    11        66    |    31         -    |
   *  |    12        78    |    32         -    |
   *  |    13        91    |    33         -    |
   *  |    14       105    |    34         -    |
   *  |    15       120    |    35         -    |
   *  |    16         -    |    36         -    |
   *  |    17         -    |    37         -    |
   *  |    18       171    |    38         -    |
   *  |    19       190    |    39         -    |
   *  |    20       210    |    40         -    |
   *   -------------------- --------------------
   * </pre>
   */
  public static String raceCardDisplayIntelligent(Collection<Integer> validMoves)
  {
    //No indentation by default
    return raceCardDisplayIntelligent(validMoves, null);
  }

  /**
   * Returns the intelligent String representation of the race card.
   * Costs are only displayed for the valid moves the player can make.
   * Indentation of the display is catered for.
   *
   * @param validMoves The collection of valid moves the player can make
   * (i.e. a collection containing the numbers of squares forward that
   * the player can make that are valid moves).
   * If validMoves is null then the basic representation is returned.
   *
   * @param indent The indentation to use for the display.
   * Can be any combination of space ' ' and/or tab '\t' characters.
   * If null or an empty string then no indentation is performed.
   *
   * @return An intelligent String version of the race card. The String
   * returned is similar to this structure:
   *
   * <pre>
   *                   RACE CARD
   *                   =========
   *  Cost of moving: Each square you move forward
   *  on the board costs you carrots as follows:
   *   -------------------- --------------------
   *  |  Squares  Cost in  |  Squares  Cost in  |
   *  |   moved   carrots  |   moved   carrots  |
   *   -------------------- --------------------
   *  |     1         1    |    21       231    |
   *  |     2         3    |    22       253    |
   *  |     3         -    |    23       276    |
   *  |     4         -    |    24       300    |
   *  |     5        15    |    25       325    |
   *  |     6        21    |    26         -    |
   *  |     7        28    |    27         -    |
   *  |     8        36    |    28         -    |
   *  |     9        45    |    29         -    |
   *  |    10         -    |    30         -    |
   *  |    11        66    |    31         -    |
   *  |    12        78    |    32         -    |
   *  |    13        91    |    33         -    |
   *  |    14       105    |    34         -    |
   *  |    15       120    |    35         -    |
   *  |    16         -    |    36         -    |
   *  |    17         -    |    37         -    |
   *  |    18       171    |    38         -    |
   *  |    19       190    |    39         -    |
   *  |    20       210    |    40         -    |
   *   -------------------- --------------------
   * </pre>
   */
  public static String raceCardDisplayIntelligent(Collection<Integer> validMoves, String indent)
  {
    String header = "%s%s%n";
    String line = "%s|    %2s       %3s    |    %2s       %3s    |%n";
    String separator = " -------------------- --------------------";
    String hyphen = "-";

    if (indent == null)
    {
      indent = "";
    }

    StringBuilder sb = new StringBuilder(
            String.format(header, indent, "                 RACE CARD"));
    sb.append(String.format(header, indent, "                 ========="));
    sb.append(String.format(header, indent, "Cost of moving: Each square you move forward"));
    sb.append(String.format(header, indent, "on the board costs you carrots as follows:"));
    sb.append(String.format(header, indent, separator));
    sb.append(String.format(header, indent, "|  Squares  Cost in  |  Squares  Cost in  |"));
    sb.append(String.format(header, indent, "|   moved   carrots  |   moved   carrots  |"));
    sb.append(String.format(header, indent, separator));

    int offset = MAX_MOVES / 2;

    for (int i = 1; i <= offset; i++)
    {
      int j = i + offset;
      sb.append(String.format(line,
              indent,
              i,
              ((validMoves == null) || (validMoves.contains(i))) ? moves.get(i) : hyphen,
              j,
              ((validMoves == null) || (validMoves.contains(j))) ? moves.get(j) : hyphen
      ));
    }

    sb.append(String.format(header, indent, separator));

    return sb.toString();
  }
}
