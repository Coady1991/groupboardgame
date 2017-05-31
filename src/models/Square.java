package models;

/**
 *
 * @author Robert Alexander and Mark McDonald
 * version 22/04/17
 *
 */
public class Square
{


  //When a player is on a square, that info needs to be stored here, and then when a player
  //leaves a square, the data must be removed.
  /**
   *
   * @param type
   * @return The type of square, and whether the square is occupied by a player.
   */
  public static String squareTypeString(SquareType type)
  {
    //Return string representation of the number square types
    //If NUMBER2 return "2", NUMBER3 return "3", NUMBER4 return "4"
    //and NUMBER156 return "156" otherwise return ""
    switch (type)
    {
      case NUMBER2:
        return "2";
      case NUMBER3:
        return "3";
      case NUMBER4:
        return "4";
      case NUMBER156:
        return "156";
      default:
        return "";
    }
  }

  public SquareType type;
  public Player player;

  public Square(SquareType type)
  {
    this.type = type;
    this.player = null;
  }

  //getter for type, with will retunr the type to the board, to build my layout
  //check which player. Get the index of square they are on. Use this to return squareType

  //special getter with the type formatted for printing...spacing sorted...



  //The below booleans take the player index, check it against this list of booleans to see which
  //type of square the player is on.

  /**
   *
   * @return Is this the starting square?
   */
  public boolean isStart()
  {
    //TODO: Implement .. this is just for stubbing
    int i = 0;
    if (i == player.getIndex())
    {
      return true;
    }
    else
    {
      return false;
    }
  }

  /**
   *
   * @return Is this a square of type Hare?
   */
  public boolean isHare()
  {
    //TODO: Implement .. this is just for stubbing
    //int playerPosition =0;
    //playerPosition = player.getIndex();
    //Board.Square[playerPosition] SquareType;
    //if (SquareType.squareType == HARE)
    int i = player.getIndex();
    if(Board.board[i].type == SquareType.HARE)
    {
      return true;
    }
    else
    {
      return false;
    }
  }

  /**
   *
   * @return Is this a square of type Tortoise?
   */
  public boolean isTortoise()
  {
    //TODO: Implement .. this is just for stubbing
    //return type.equals(SquareType.TORTOISE);
    int i = player.getIndex();
    if(Board.board[i].type == SquareType.TORTOISE)
    {
      return true;
    }
    else
    {
      return false;
    }
  }

  /**
   *
   * @return Is this a square of type Lettuce?
   */
  public boolean isLettuce()
  {
    //TODO: Implement .. this is just for stubbing
    int i = player.getIndex();
    if(Board.board[i].type == SquareType.LETTUCE)
    {
      return true;
    }
    else
    {
      return false;
    }
  }

  /**
   *
   * @return Is this a square of type Carrot?
   */
  public boolean isCarrot()
  {
    //TODO: Implement .. this is just for stubbing
    int i = player.getIndex();
    if(Board.board[i].type == SquareType.CARROT)
    {
      return true;
    }
    else
    {
      return false;
    }
  }

  /**
   *
   * @return Is this a square of any of the NUMBER types?
   */
  public boolean isNumber()
  {
    //TODO: Implement .. this is just for stubbing
    int i = player.getIndex();
    if((Board.board[i].type == SquareType.NUMBER2) ||
            (Board.board[i].type == SquareType.NUMBER3) ||
            (Board.board[i].type == SquareType.NUMBER4) ||
            (Board.board[i].type == SquareType.NUMBER156))
    {
      return true;
    }

    else
    {
      return false;
    }
  }

  /**
   *
   * @return Is this a square of type Finish?
   */
  public boolean isFinish()
  {
    //TODO: Implement .. this is just for stubbing
    int i = player.getIndex();
    if(Board.board[i].type == SquareType.FINISH)
    {
      return true;
    }
    else
    {
      return false;
    }
  }

  /**
   *
   * @return Is this square currently occupied by a player?
   */
  public boolean isOccupied()
  {

    if(this.player == null)
    {
      return false;
    }
    else
    {
      return true;
    }
    //Return true if the player field is not null
    //do a getter for the player. How? currentPosition
    // for (int i = 0; i < board.maxIndex; i++)


    // if (i == players.get(n).getIndex())

  }
}
