package models;

/**
 * Represents a Player. Stores the Player name, Carrots, Lettuce's, Skip, Chew, Index, canStayPut, playsAgain, canMoveBack,
 * canMoveForward, canFinish, finished, Key, finalRank, Initial Carrots, Initial Lettuces, Initial Index,
 * @author Iain McCarthy
 * @version 01/04/2017
 */
public class Player
{
  public static final int INITIAL_CARROTS = 65;
  public static final int INITIAL_LETTUCES = 3;
  public static final int INITIAL_INDEX = 0;

  private String name;
  private int carrots;
  public int lettuces;
  private int index;
  private boolean skip;
  private boolean chew;
  private boolean canStayPut;
  private boolean playsAgain;
  private boolean canMoveBack;
  private boolean canMoveForward;
  private boolean canFinish;
  private boolean finished;
  private int key;
  private int finalRank;

  /**
   * Constructor for objects of class Player
   *
   * @param name Name of the Player
   * @param carrots
   * @param lettuces
   * @param skip
   * @param chew
   * @param index
   * @param canStayPut
   * @param playsAgain
   * @param canMoveBack
   * @param canMoveForward
   * @param canFinish
   * @param finished
   * @param key
   * @param finalRank
   */

  public Player(String name, int key)
  {
    this.name = name;
    this.carrots = INITIAL_CARROTS;
    this.lettuces = INITIAL_LETTUCES;
    this.index = INITIAL_INDEX;
    this.skip = false;
    this.chew = false;
    this.canStayPut = false;
    this.playsAgain = false;
    this.canMoveBack = false;
    this.canMoveForward = false;
    this.canFinish = false;
    this.finished = false;
    this.key = key;
    this.finalRank = 0;
  }

  /**
   * Adds carrots to the player
   * @param addCarrots
   */
  public void addCarrots(int carrots)
  {
    this.carrots += carrots;
  }

  /**
   * Removes carrots from the player
   * @param removeCarrots
   */
  public void removeCarrots(int carrots)
  {
    if (carrots > this.carrots)
      this.carrots = 0;
    else
      this.carrots -= carrots;
  }

  /**
   * The player discards a lettuce
   */
  public void discardLettuce()
  {
    if (lettuces > 0)
      this.lettuces--;
  }


  //GETTERS AND SETTERS
  /**
   * Returns the key
   * @return the key
   */
  public int getKey()
  {
    return key;
  }

  /**
   * Updates the key field
   * @param key
   */
  public void setKey(int key)
  {
    this.key = key;
  }

  /**
   * Returns the final rank
   * @return the final rank
   */
  public int getFinalRank()
  {
    return finalRank;
  }

  /**
   * Updates the final rank field
   * @param finalRank
   */
  public void setFinalRank(int finalRank)
  {
    this.finalRank = finalRank;
  }

  /**
   * Returns the players name
   * @return the players name
   */
  public String getName()
  {
    return name;
  }

  /**
   * Updates the players name field
   * @param name
   */
  public void setName(String name)
  {
    this.name = name;
  }

  /**
   * Returns the carrots
   * @return carrots
   */
  public int getCarrots()
  {
    return carrots;
  }

  /**
   * Updates the carrots field
   * @param carrots
   */
  public void setCarrots(int carrots)
  {
    this.carrots = carrots;
  }

  /**
   * Returns the lettuces
   * @return lettuces
   */
  public int getLettuces()
  {
    return lettuces;
  }

  /**
   * Updates the lettuces field
   * @param lettuces
   */
  public void setLettuces(int lettuces)
  {
    this.lettuces = lettuces;
  }

  /**
   * Returns the index
   * @return index
   */
  public int getIndex()
  {
    return index;
  }

  /**
   * Updates the index field
   * @param index
   */
  public void setIndex(int index)
  {
    this.index = index;
  }

  /**
   * Return the skip
   * @return skip
   */
  public boolean getSkip()
  {
    return skip;
  }

  /**
   * Updates the skip field
   * @param skip
   */
  public void setSkip(boolean skip)
  {
    this.skip = skip;
  }

  /**
   * Returns chew
   * @return chew
   */
  public boolean getChew()
  {
    return chew;
  }

  /**
   * Updates the chew field
   * @param chew
   */
  public void setChew(boolean chew)
  {
    this.chew = chew;
  }

  /**
   * Returns can stay put
   * @return canStayPut
   */
  public boolean getCanStayPut()
  {
    return canStayPut;
  }

  /**
   * Updates the can stay put field
   * @param canStayPut
   */
  public void setCanStayPut(boolean canStayPut)
  {
    this.canStayPut = canStayPut;
  }

  /**
   * Returns plays again
   * @return playsAgain
   */
  public boolean getPlaysAgain()
  {
    return playsAgain;
  }

  /**
   * Updates the plays again field
   * @param playsAgain
   */
  public void setPlaysAgain(boolean playsAgain)
  {
    this.playsAgain = playsAgain;
  }

  /**
   * Returns can move back
   * @return canMoveBack
   */
  public boolean getCanMoveBack()
  {
    return canMoveBack;
  }

  /**
   * Updates the can move back field
   * @param canMoveBack
   */
  public void setCanMoveBack(boolean canMoveBack)
  {
    this.canMoveBack = canMoveBack;
  }

  /**
   * Returns can move forward
   * @return canMoveForward
   */
  public boolean getCanMoveForward()
  {
    return canMoveForward;
  }

  /**
   * Updates the can move forward field
   * @param canMoveForward
   */
  public void setCanMoveForward(boolean canMoveForward)
  {
    this.canMoveForward = canMoveForward;
  }

  /**
   * Returns can finish
   * @return canFinish
   */
  public boolean getCanFinish()
  {
    return canFinish;
  }

  /**
   * Updates the can finish field
   * @param canFinish
   */
  public void setCanFinish(boolean canFinish)
  {
    this.canFinish = canFinish;
  }

  /**
   * Returns finished
   * @return finished
   */
  public boolean getFinished()
  {
    return finished;
  }

  /**
   * Updates the finished field
   * @param finished
   */
  public void setFinished(boolean finished)
  {
    this.finished = finished;
  }

}














