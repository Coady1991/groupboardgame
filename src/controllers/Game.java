package controllers;

import models.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;

import static utils.RaceCard.*;
import static utils.RaceCard.raceCardCostForMove;

//===================================================================================================================================================
//TODO: RACE CARD
/*
*   raceCardCostForMove(move)                      : Returns the cost (carrots) for the specified move (number of squares).
*   raceCardMaxMoveForCost(cost)                   : Returns the maximum move (number of squares) for the specified cost.
*/
//===================================================================================================================================================

/**
 * This class will be used to set up a object of Game.
 *
 * @author Niall Coady & Mark McDonald
 * version 18/04/2017
 */
public class Game
{
  //TODO: Added players
  private Hashtable<Integer, Player> players;
  public Board board;
  public HareCards hareCards;
  public int currentPlayerKey;
  public int numberOfPlayers;
  public int playerLastMove;
  public int playerLastCost;
  public int playersFinished;

  /**
   * Constructor for a new Game.
   *
   *
   */
  public Game(Hashtable<Integer, Player> players)
  {
    this.players = players;
    board = new Board();
    hareCards = new HareCards();
    currentPlayerKey = 1;
    numberOfPlayers = players.size();
    playerLastMove = 0;
    playerLastCost = 0;
    playersFinished = 0;
  }

  //*******************************************************************************************************************************
  // Getters for the game
  //*******************************************************************************************************************************
  /**
   * Returns the players in the HashTable.
   *
   * @return the players in the HashTable.
   */
  public Hashtable<Integer, Player> getPlayers()
  {
    return players;
  }

  /**
   * Returns the Board.
   *
   * @return the Board.
   */
  public Board getBoard()
  {
    return board;
  }

  /**
   * Returns the HareCards.
   *
   * @return the HareCards.
   */
  public HareCards getHareCards()
  {
    return hareCards;
  }

  /**
   * Returns the currentPlayerKey.
   *
   * @return the currentPlayerKey.
   */
  public int currentPlayerKey()
  {
    return currentPlayerKey;
  }

  /**
   * Returns the number of Players in the game.
   *
   * @return the number of players in the game.
   */
  public int numberOfPlayers()
  {
    return numberOfPlayers;
  }

  /**
   * Returns the players last move.
   *
   * @return the players last move.
   */
  public int playerLastMove()
  {
    return playerLastMove;
  }

  /**
   * Returns the carrot cost for the players last move.
   *
   * @return the carrot cost for the players last move.
   */
  public int playerLastCost()
  {
    return playerLastCost;
  }

  /**
   * Returns the players who are finished the game.
   *
   * @return the players who are finished the game.
   */
  public int playersFinished()
  {
    return playersFinished;
  }

  //*******************************************************************************************************************************
  // Setters for the game
  //*******************************************************************************************************************************

  /**
   * Sets the players in the HashTable.
   *
   * @param players Updates the players in the HashTable.
   */
  public void setPlayers(Hashtable<Integer, Player> players)
  {
    this.players = players;
  }

  /**
   * Updates the board.
   *
   * @param board
   */
  public void setBoard(Board board)
  {
    this.board = board;
  }

  /**
   * Updates the HareCards.
   *
   * @param hareCards
   */
  public void setHareCards(HareCards hareCards)
  {
    this.hareCards = hareCards;
  }

  /**
   * Updates the currentPlayerKey field.
   *
   * @param currentPlayerKey
   */
  public void currentPlayerKey(int currentPlayerKey)
  {
    this.currentPlayerKey = currentPlayerKey;
  }
  /**
   * Updates the numberOfPlayers field.
   *
   * @param numberOfPlayers
   */
  public void numberOfPlayers(int numberOfPlayers)
  {
    this.numberOfPlayers = numberOfPlayers;
  }

  /**
   * Updates the playerLastMove field.
   *
   * @param playerLastMove
   */
  public void playerlastMove(int playerLastMove)
  {
    this.playerLastMove = playerLastMove;
  }

  /**
   * Updates the playerlastCost field.
   *
   * @param playerLastCost
   */
  public void playerLastCost(int playerLastCost)
  {
    this.playerLastCost = playerLastCost;
  }

  /**
   * Updates the playersFinished field.
   *
   * @param playersFinished
   */
  public void playersFinished(int playersFinished)
  {
    this.playersFinished = playersFinished;
  }

  //*******************************************************************************************************************************
  // START PHASE
  //*******************************************************************************************************************************

  /**
   * Returns the currentPlayer
   *
   * @return the currentPlayer
   */
  public Player currentPlayer()
  {
    return players.get(currentPlayerKey);
  }

  //MMcD: Added this method to integrate the hare card processing in Driver
  public SquareType currentSquareType(Player player)
  {
    return board.currentSquareType(player);
  }

  /**
   * If the player reaches the finish square, this sets their finish flag to true.
   *
   * @param player sets finish flag to true.
   */
  public void isFinished(Player player)
  {
    if(player.getIndex() == 64)
    {
      player.setFinished(true);
      Turn();
    }
  }

  /**
   * If the player has been skipped on their previous turn,
   * this turns the flag false to allow the player play on their next turn
   *
   * @param player Skip flag is set to false to allow a move next turn.
   */
  public void isSkipTurn(Player player)
  {
    if(player.getSkip() == true)
    {
      player.setSkip(false);
      Turn();
    }
  }

  /**
   * This method allows the player to collect carrots depending on the number square they are on
   * and the current rank they hold within the game.
   * If the players rank and number square match, they receive 10 carrots*rank.
   *
   * @param player receives carrots depending if their rank matches the number square.
   */
  public void getCarrotsNumberSquare(Player player)
  {
    if(board.currentSquareType(player) == SquareType.NUMBER2 ||
            board.currentSquareType(player) == SquareType.NUMBER3 ||
            board.currentSquareType(player) == SquareType.NUMBER4 ||
            board.currentSquareType(player) == SquareType.NUMBER156)
    {
      int rank = calculateRank(player);
      String squareNumber = Square.squareTypeString(board.currentSquareType(player));
      if (squareNumber.contains(String.format("%s", rank)))
      {
        int currentCarrots = player.getCarrots();
        player.addCarrots(currentCarrots + rank*10);
      }
    }
  }

  /**
   * This method allows the player to receive carrots after they have discarded a lettuce.
   * The player will receive 10 carrots*rank in the game.
   *
   * @param player receives carrots after discarding a lettuce.
   */
  public void getLettuceSquareCarrots(Player player)
  {
    if(player.getChew() == true)
    {
      player.discardLettuce();
      int rank = calculateRank(player);
      int currentCarrots = player.getCarrots();
      player.addCarrots(currentCarrots + rank*10);
      player.setChew(false);
    }
  }

  /**
   * Returns the valid moves the player can make.
   *
   * @param player The player currently looking to move.
   *
   * @return the valid moves a player can make.
   */
  public HashSet<Integer> validMoves(Player player)
  {
    return board.validMoves(player);
  }

  //*******************************************************************************************************************************
  // MOVE PHASE
  //*******************************************************************************************************************************

  /**
   * This method removes the cost of the move the player made in carrots.
   *
   * @param player currently making a move
   *
   * @param moves Depending on how many moves the player made depends on how many carrots are spent.
   */
  public void movePlayerForward(Player player, int moves)
  {
    int cost = raceCardCostForMove(moves);
    player.removeCarrots(cost);
    board.movePlayerForward(player, moves);
    playerLastMove = moves;
    playerLastCost = cost;
  }

  /**
   * This method allows the player to receive carrots after moving backwards to a tortoise square, if that square is unoccupied..
   * The player receives 10 carrots*the number of squares they moved backwards to reach the tortoise square.
   *
   * @param player That is moving back to a tortoise.
   */
  public void getTortoiseSquareCarrots(Player player)
  {
    playerLastMove = board.movePlayerBack(player);
    playerLastCost = 0;
    player.addCarrots(playerLastMove * 10);
  }

  /**
   * If the player is on a carrot square they can stay put for their move and receive 10 carrots.
   *
   * @param player That is currently on the carrot square.
   */
  public void canStayPutReceive(Player player)
  {
    int currentCarrots = player.getCarrots();
    player.addCarrots(currentCarrots + 10);
  }

  /**
   * If the player is on a carrot square they can stay put and pay 10 carrots. But if they have less then 10 carrots and wish to pay 10 carrots,
   * they are forced to receive 10 carrots.
   *
   * @param player That is currently on the carrot square.
   */
  public void canStayPutPay(Player player)
  {
    if (player.getCarrots() < 10)
    {
      int currentCarrots = player.getCarrots();
      player.addCarrots(currentCarrots + 10);
    }
    else if (player.getCarrots() > 10)
    {
      int currentCarrots = player.getCarrots();
      player.addCarrots(currentCarrots - 10);
    }
  }

  /**
   * Returns the number of squares that have to be moved for the payer to finish from its current position.
   *
   * @param player The current player moving
   *
   * @return The number of squares needed to moved to reach the finish.
   */
  public int movesToFinish(Player player)
  {
    return board.movesToFinish(player);
  }

  /**
   * Moves the player to the finish square and sets the player finished to true.
   *
   * @param player Current player moved to finish square and setFinished to true.
   *
   */
  public void playerFinish(Player player, int moves)
  {
    int cost = raceCardCostForMove(moves);
    player.removeCarrots(cost);
    board.movePlayerForward(player, moves);
    playerLastMove = moves;
    playerLastCost = cost;
    player.setSkip(false);
    player.setChew(false);
    player.setCanStayPut(false);
    player.setPlaysAgain(false);
    player.setCanMoveBack(false);
    player.setCanMoveForward(false);
    player.setCanFinish(false);
    player.setFinished(true);
  }

  // **********************************************************************************************************************************
  // START: Hare Card Processing
  // ----------------------------------------------------------------------------------------------------------------------------------
  // @author Mark McDonald (20077698@mail.wit.ie)
  // @version 1.0 (06/04/2017)
  // **********************************************************************************************************************************
  /**
   * This method draws the top card from the hare card deck.
   *
   * @return The top card drawn from the hare card deck.
   */
  public HareCard drawHareCard()
  {
    return hareCards.draw();
  }

  /**
   * This method performs the processing for hare card type PLAYERSBEHIND:
   *
   *   "IF THERE ARE MORE PLAYERS BEHIND YOU THAN IN FRONT OF YOU, MISS A TURN. IF NOT, PLAY AGAIN."
   *   "If equal, of course play again."
   *
   * @param player The player who has drawn the hare card.
   */
  public void hareCardPlayersBehind(Player player)
  {
    int rank = calculateRank(player);
    int playersAhead = rank - 1;
    int playersBehind = numberOfPlayers - rank;

    if (playersBehind > playersAhead)
    {
      player.setSkip(true);
    }
    else
    {
      player.setPlaysAgain(true);
    }
  }

  /**
   * This method performs the processing for hare card type DRAWCARROTS:
   *
   *   "DRAW 10 CARROTS FOR EACH LETTUCE YOU STILL HOLD."
   *   "If you have none left, miss a turn."
   *
   * @param player The player who has drawn the hare card.
   */
  public void hareCardDrawCarrots(Player player)
  {
    int lettuces = player.getLettuces();

    if (lettuces > 0)
    {
      player.addCarrots(10 * lettuces);
    }
    else
    {
      player.setSkip(true);
    }
  }

  /**
   * This method performs the processing for hare card type RESTORECARROTS:
   *
   *   "RESTORE YOUR CARROT HOLDING TO EXACTLY 65."
   *   "If you have more than 65, pay extras to the carrot patch; if fewer, draw extras from the carrot patch."
   *
   * @param player The player who has drawn the hare card.
   */
  public void hareCardRestoreCarrots(Player player)
  {
    player.setCarrots(Player.INITIAL_CARROTS);
  }

  /**
   * This method performs the processing for hare card type LOSEHALFCARROTS:
   *
   *   "LOSE HALF YOUR CARROTS!"
   *   "If an odd number, keep the odd one."
   *
   * @param player The player who has drawn the hare card.
   */
  public void hareCardLoseHalfCarrots(Player player)
  {
    int carrots = player.getCarrots();

    if (carrots % 2 == 1)
    {
      player.setCarrots(1 + ((carrots - 1) / 2));
    }
    else
    {
      player.setCarrots(carrots / 2);
    }
  }

  /**
   * This method performs the processing for hare card type SHUFFLECARDS:
   *
   *   "SHUFFLE THE HARE CARDS AND RECEIVE FROM EACH PLAYER 1 CARROT FOR DOING SO."
   *   "Do not replace this card at the bottom of the pack but include it in the shuffle."
   *
   * @param player The player who has drawn the hare card.
   */
  public void hareCardShuffleCards(Player player)
  {
    hareCards.shuffle();

    for (Player p : players.values())
    {
      if ((!p.getFinished()) && (p.getKey() != player.getKey()) && (p.getCarrots() > 0))
      {
        p.removeCarrots(1);
        player.addCarrots(1);
      }
    }
  }

  /**
   * This method performs the processing for hare card type FREERIDE:
   *
   *   "FREE RIDE!"
   *   "Your last turn costs nothing; retrieve the carrots you paid to reach this square."
   *
   * @param player The player who has drawn the hare card.
   */
  public void hareCardFreeRide(Player player)
  {
    player.addCarrots(playerLastCost);
  }

  /**
   * This method performs part of the processing for hare card type GIVECARROTS:
   *
   *   "GIVE 10 CARROTS TO EACH PLAYER LYING BEHIND YOU IN THE RACE (IF ANY)."
   *   "If you haven't enough carrots, give them five each; if still not possible, one each."
   *   "A player who doesn't want extra carrots may discard them to the 'carrot patch'."
   *
   * This method returns a list of players who are behind the specified player
   * in the game (if any). This means all players who have an index less than
   * the index of the specified player.
   *
   * If there are no players behind this player then an empty list is returned.
   *
   * @param player The player who has drawn the hare card.
   *
   * @return The list of players who are behind the specified player
   * in the game (if any).
   */
  public List<Player> hareCardGiveCarrotsPlayersBehind(Player player)
  {
    List<Player> result = new ArrayList<>();

    for (Player p : players.values())
    {
      if (p.getIndex() < player.getIndex())
      {
        result.add(p);
      }
    }

    return result;
  }

  /**
   * This method performs part of the processing for hare card type GIVECARROTS:
   *
   *   "GIVE 10 CARROTS TO EACH PLAYER LYING BEHIND YOU IN THE RACE (IF ANY)."
   *   "If you haven't enough carrots, give them five each; if still not possible, one each."
   *   "A player who doesn't want extra carrots may discard them to the 'carrot patch'."
   *
   * This method calculates what payment the player can afford to make to each of the
   * players behind. This will be either 10, 5, 1 or 0.
   *
   * If 0, then the player can't afford any payment to any of the players behind and
   * so the carrot holding for the player is set to zero so that (subject to no change
   * in circumstances) the player will be sent back to the start on their next turn.
   * (Ref. as per Siobhan on 27/03/2017, player is returned to start in this case.)
   *
   * If the payment the player can afford to make is greater than zero (10, 5 or 1)
   * then the carrot holding for the player is reduced by that amount for each of
   * the players behind that player.
   *
   * @param player The player who has drawn the hare card.
   *
   * @param count The number of players behind the specified player in the game.
   * This value will always be greater than zero as further processing of this
   * hare card type is only carried out in that case.
   *
   * @return The payment the player can afford to make to each of the players behind.
   * This will be either 10, 5, 1 or 0.
   */
  public int hareCardGiveCarrotsPayment(Player player, int count)
  {
    int carrots = player.getCarrots();
    int payment = 10;

    if (payment * count > carrots)
    {
      payment = 5;
      if (payment * count > carrots)
      {
        payment = 1;
        if (count > carrots)
        {
          payment = 0;
        }
      }
    }

    if (payment == 0)
    {
      //Insufficient carrots so send back to the start on next turn
      player.setCarrots(0);
    }
    else
    {
      //Pay the total carrots to be paid to each player behind
      player.removeCarrots(payment * count);
    }

    return payment;
  }

  /**
   * This method performs the last part of the processing for hare card type GIVECARROTS:
   *
   *   "GIVE 10 CARROTS TO EACH PLAYER LYING BEHIND YOU IN THE RACE (IF ANY)."
   *   "If you haven't enough carrots, give them five each; if still not possible, one each."
   *   "A player who doesn't want extra carrots may discard them to the 'carrot patch'."
   *
   * This method is called for each player behind the current player in the game who
   * accepts receipt of the payment of carrots by the current player to each of the
   * players behind.
   *
   * The method simply adds the carrot payment to the carrot holding of the specified
   * player behind.
   *
   * @param player The player behind who has accepted the offered payment of carrots.
   *
   * @param payment The number of carrots in payment that the player behind has accepted
   * from the current player.
   */
  public void hareCardGiveCarrotsAccept(Player player, int payment)
  {
    player.addCarrots(payment);
  }

  // **********************************************************************************************************************************
  // END: Hare Card Processing
  // **********************************************************************************************************************************

  /**
   * Controls the player turn loop.
   *
   */
  public void Turn()
  {
    currentPlayerKey++;
    if (currentPlayerKey > numberOfPlayers)
    {
      currentPlayerKey = 1;
    }
  }

  /**
   * Returns the players rank on the board 1st, 2nd, 3rd etc.
   *
   * @param player The current players ranking in game.
   *
   * @return the players rank in the game.
   */
  private int calculateRank(Player player)
  {
    //Return the current rank for the passed in player
    //check players forward, plus check players finished. Add them, and current player
    //is
    int playerRanking = 1;
    //checking indexes of all other players against this player.
    for (Integer key : players.keySet())
    {
      Player px = players.get(key);

      //MMcD: Fixed bug
      //if(px.getIndex()<player.getIndex())
      if (px.getIndex() > player.getIndex())
      {
        playerRanking += 1;
      }
    }
    return playerRanking;
  }

  /**
   * If there is no valid move available this method returns the player
   * to the start of the game, setting carrots back to 65,
   * and player keeps the same amount of lettuce. The immediately get to play again.
   *
   * @param player is returned to the start square of the game and allowed to move.
   */
  public void restartPlayer(Player player)
  {
    player.setIndex(Player.INITIAL_INDEX);//returned to starting number of 65
    player.setCarrots(Player.INITIAL_CARROTS);//returned to square index 0
    player.setPlaysAgain(true);
  }
}
