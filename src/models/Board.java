package models;

import java.util.HashSet;

import static utils.RaceCard.*;
import static utils.RaceCard.raceCardMaxMoveForCost;

//===================================================================================================================================================
//TODO: RACE CARD
/*
*   raceCardCostForMove(move)                      : Returns the cost (carrots) for the specified move (number of squares).
*   raceCardMaxMoveForCost(cost)                   : Returns the maximum move (number of squares) for the specified cost.
*/
//===================================================================================================================================================

public class Board
{
  public static final int length = 65;

  //TODO: Add public getter and setter
  public static Square[] board;
  private boolean validMoves;
  public int freeTortoise;
  public int playersAreFin = 0;
  public int backDistance;

  //probably don't need below. See if you can remove them
  private String validSymbol;
  private String spacing;
  int maxIndex = length - 1;
  //i might need to be local only each time, as the value should not be carried over
  //private int i = 0;

  public String boardWord;
  public int indexCheck;

  public Board()
  {
    board = new Square[length];

    board [0] = new Square(SquareType.START);

    board [1] = new Square(SquareType.HARE);
    board [2] = new Square(SquareType.CARROT);
    board [3] = new Square(SquareType.HARE);
    board [4] = new Square(SquareType.NUMBER3);
    board [5] = new Square(SquareType.CARROT);
    board [6] = new Square(SquareType.HARE);
    board [7] = new Square(SquareType.NUMBER156);
    board [8] = new Square(SquareType.NUMBER2);

    board [9] = new Square(SquareType.NUMBER4);
    board [10] = new Square(SquareType.LETTUCE);
    board [11] = new Square(SquareType.TORTOISE);
    board [12] = new Square(SquareType.NUMBER3);
    board [13] = new Square(SquareType.CARROT);
    board [14] = new Square(SquareType.HARE);
    board [15] = new Square(SquareType.TORTOISE);
    board [16] = new Square(SquareType.NUMBER156);

    board [17] = new Square(SquareType.NUMBER2);
    board [18] = new Square(SquareType.NUMBER4);
    board [19] = new Square(SquareType.TORTOISE);
    board [20] = new Square(SquareType.NUMBER3);
    board [21] = new Square(SquareType.CARROT);
    board [22] = new Square(SquareType.LETTUCE);
    board [23] = new Square(SquareType.NUMBER2);
    board [24] = new Square(SquareType.TORTOISE);

    board [25] = new Square(SquareType.HARE);
    board [26] = new Square(SquareType.CARROT);
    board [27] = new Square(SquareType.NUMBER4);
    board [28] = new Square(SquareType.NUMBER3);
    board [29] = new Square(SquareType.NUMBER2);
    board [30] = new Square(SquareType.TORTOISE);
    board [31] = new Square(SquareType.HARE);
    board [32] = new Square(SquareType.NUMBER156);

    board [33] = new Square(SquareType.CARROT);
    board [34] = new Square(SquareType.HARE);
    board [35] = new Square(SquareType.NUMBER2);
    board [36] = new Square(SquareType.NUMBER3);
    board [37] = new Square(SquareType.TORTOISE);
    board [38] = new Square(SquareType.CARROT);
    board [39] = new Square(SquareType.HARE);
    board [40] = new Square(SquareType.CARROT);

    board [41] = new Square(SquareType.NUMBER2);
    board [42] = new Square(SquareType.LETTUCE);
    board [43] = new Square(SquareType.TORTOISE);
    board [44] = new Square(SquareType.NUMBER3);
    board [45] = new Square(SquareType.NUMBER4);
    board [46] = new Square(SquareType.HARE);
    board [47] = new Square(SquareType.NUMBER2);
    board [48] = new Square(SquareType.NUMBER156);

    board [49] = new Square(SquareType.CARROT);
    board [50] = new Square(SquareType.TORTOISE);
    board [51] = new Square(SquareType.HARE);
    board [52] = new Square(SquareType.NUMBER3);
    board [53] = new Square(SquareType.NUMBER2);
    board [54] = new Square(SquareType.NUMBER4);
    board [55] = new Square(SquareType.CARROT);
    board [56] = new Square(SquareType.TORTOISE);

    board [57] = new Square(SquareType.LETTUCE);
    board [58] = new Square(SquareType.HARE);
    board [59] = new Square(SquareType.CARROT);
    board [60] = new Square(SquareType.NUMBER156);
    board [61] = new Square(SquareType.CARROT);
    board [62] = new Square(SquareType.HARE);
    board [63] = new Square(SquareType.CARROT);
    board [64] = new Square(SquareType.FINISH);
  }




  // this is a gettor for the type of ENUM contained in that
  //square index of player. Can repeat for positions without
  //player in another getter if needed

  //probably done
  public SquareType currentSquareType(Player player)
  {
    //Return the SquareType of the square the specified player is on
    //NOTE: The player index will always be a valid index
    int i = player.getIndex();
    return board[i].type;
  }


  // May have to change title validMoves, beacuse am using as variable
  //public HashSet<Integer> validMoves(Player player, int maxMoves)
  //{
  // boolean occupied = false;




//		  for player occupied stuff.
//		  indexCheck = Player.getIndex();
//		  if (indexCheck == i)
//		 {
//			  occupied = true;
//		 }
//		  else
//		  {
//			  occupied = false;
//		  }
//	  }

  public String boardDisplay(Player player)
  {
    //This is where I will create the board itself. It will get info of available to move squares elsewhere.
    //start is 0. First space is 1. Last space is 63. Finish is 64(am I doing this?)
    for (int i = 0; i < maxIndex; i++)
    {
      //This gives me the Enum into a string. I still need to decide
      //on upper/lower case, and adjust length with .length
      String displaySquare = board[i].type.toString();
      displaySquare.toLowerCase();
      while (displaySquare.length() < 12)
      {
        displaySquare = displaySquare + " ";
      }

      if (validMove(player) == true)
      {
        validSymbol = "x";
      }
      else
      {
        validSymbol = "o";
      }
      if (i % 8 == 7)
      {
        spacing = "/n";
      }
      else
      {
        spacing = "/t";
      }

      //need to remove the tab, and replace with proper spacing using .length from Siobhan.
      //Figured that out, but can't figure
      //out how to convert Array to String type, to test length.
      return " " + i + displaySquare + validSymbol + spacing;
    }

    return "/n End of Board";
  }
  //every time a player finishes!!!
  //probably done
  private void setPlayerFinished()
  {
    //every time I see that a player moves on to the final square, I should send to here
    //and increment the number here.
    playersAreFin ++;

  }

  //probably done
  private int getPlayersFinished()
  {
    return playersAreFin;
  }

  public HashSet<Integer> validMoves(Player player)
  //add new parameter
  //  public HashSet<Integer> validMoves(Player player, int playersFinished)
  {
    HashSet<Integer> moves = new HashSet<Integer>();


    //call racecard maxmoves method. Give number of squares they can afford.
    int affordMoves;
    affordMoves = raceCardMaxMoveForCost(player.getCarrots());
    int p = player.getIndex();
    //String version
    //String toMove = new String();
    //toMove = " ";


    if (canPlayerMoveBack(player) == true)
    {
      moves.add(nearTortoise(player));
      // String version
      //toMove = toMove + nearTortoise(Player) + ", ";
    }

    if (board[p].type == SquareType.CARROT)
    {
      moves.add(player.getIndex());
      //String version
      //toMove = toMove + board[player] + ", ";
    }


    for(int i = 1; i == affordMoves; i++)
    {
      if (validMove(player) == true)
      {
        moves.add(i);
        //String version:
        //toMove = toMove + i + ", ";
      }

    }



    //for each affordable move, check if square is tortoise or occupied.
    //can iterate over with for loop, and send out to validMove each time.


    //add 0 to valid move set(array/hashset)
    //...I get maxMoves from racecard.
    // iterate over maxMoves, checking each one. make sure that
    //you do not check past the finish square on board
    //THEN
    //for TORTOISE and checking each one for isOccupied
    //for lettuce can only land if they have lettuce
    //for finish square can only land, if that leaves
    //them under correct number of carrots. if this is true, give 'canFinish' to true
    //this check includes 4 flags that it can send out to player
    //They should all be false at the start of every iteration of this method.
    //canFinish, canStayPut, canMove Forwards, canMoveback

    //only forward moves!!!
    //is player on carrot square, add 0
    //calculate what rank would be if they finish. if their carrots is > rank*10, then may not finish




    return moves;
  }


  //probably done
  //probably won't need-but do need for my array
  //to function in array's current state.
  public boolean validMove(Player player)
  {
    int i = player.getIndex();

    if(board[i].player != null)
    {
      return false;
    }
    if(board[i].type == SquareType.TORTOISE)
    {
      return false;
    }
    if(board[i].type == SquareType.LETTUCE && player.getLettuces() == 0)
    {
      return false;
    }
    else
    {
      return true;
    }
    //check if lettuce = 0 and square is lettuce square!
    //	  if ((squareType != TORTOISE) && (position != occupied))
    //	  {
    //		  return true;
    //	  }
    //	  else
    //	  {
    //		  return false;
    //	  }

    //check if squareType == tortoise

    //check if position == occupied

    //First check if player can move back to nearest tortoise and if so then
    //set the player canMoveBack flag to true. Don't add this move to the collection.
    //This tortoise square must exist and be unoccupied to be valid.



    /*
    HARE,
    TORTOISE,
    LETTUCE,
    CARROT,
    NUMBER2,
    NUMBER3,
    NUMBER4,
    NUMBER156,
    FINISH
    */

   /* HashSet<Integer> moves = new HashSet<Integer>();

    int maxMoves = 0;
	  maxMoves = MAX_MOVES;
	  for (i = 0; i <  maxMoves; i++)
	  {
		  //do this for each individual player
		  // check that position for availability

	  }
    //for each square, check availability(which is in validMoves)
    // then return that index number, and create a
    // moves.add(index);

    moves.add(2);
    moves.add(4);
    moves.add(6);
    if (moves.contains(4))
    {
      //valid move!
    }
    return false;*/
  }

  //probably done
  public void movePlayerForward(Player player, int moves)
  {
    //Move the player the specified number of moves forward
    //Set the player index to the index of the new square
    int spaceLanded;
    spaceLanded = player.getIndex() + moves;
    player.setIndex(spaceLanded);

  }
  //probably done
  public int movePlayerBack(Player player)
  {
    //Move the player back to the nearest tortoise square
    //Set the player index to the index of the new square
    //Return the number of squares moved back
    boolean moveBack = canPlayerMoveBack(player);
    if (moveBack = true)
    {
      backDistance = nearTortoise(player);
    }
    return backDistance;
  }

  //probably done
  public boolean canPlayerMoveBack(Player player)
  {
    //for checking which tortoise you can move to, you can do a backwards
    //for loop using i-- instead of i++
    int i = player.getIndex();
    boolean occupied = false;
    boolean isTortoise = false;

    while (!isTortoise)
    {
      i--;
      if (board[i].type == SquareType.TORTOISE)
      {
        isTortoise = true;
        //check for other player on index.
        //n == 6 should instead be the maximum player key.
        for (int n = 1; n == 6; n++)
        {
          //check in square
          if (board[i].player == null)
          {
            occupied = false;
          }

        }

      }


    }
    if(isTortoise == true && occupied == false)
    {
      freeTortoise = i;
      return true;
    }
    else
    {
      return false;
    }


    //for(int i = player.getIndex() - 1; i == 0; i-- )


    //if(currentPlayer.getIndex() > i)
    //while( i < currentPlayer.getIndex())
    // {
    //	  if(board[i].type.equals(TORTOISE))
    //	  {
    //		  moveTo = i;
    //	  }
    //	  i++
    //  }
  }

  //probably done
  public int nearTortoise(Player player)
  {

    return player.getIndex() - freeTortoise;
  }


  //probably done
  public int movesToFinish(Player player)
  {
    //Return the number of squares the player has to move to reach the finish
    int toFinish = 64 - player.getIndex();
    return toFinish;
  }

  //OLD COMMENT CODE for moving a player.
  //int previousPosition = 0;
  // previousPosition = player.index;
  // int distanceForward = 0;
  //Below line is only issue as we are not using integers. Must parse(change data type) it.
  // distanceForward = RaceCard.getMoves();
  // newIndex = previousPosition + distanceForward;

  //probably done
  public boolean occupiedSquare(Player player)
  {
    //Return true if the player field is not null
    //do a getter for the player. How? currentPosition
    // problem with this is that it returns one result, but in reality we are trying to check 64 different squares!
    // I need to have someone else iterate over the available moves(as calculated through race card, and my spaces info)
    // then I need to call this method for every time that they want to do this check. Which means that this does not have a loop
    // this only checks things once. which means this is far more complex than it needs to be.
    //It also means that this can be a boolean statement, but it is called multiple times a turn.
    int i = player.getIndex();

    if(board[i].player == null)
    {
      return false;
    }
    else
    {
      return true;
    }
  }
}





