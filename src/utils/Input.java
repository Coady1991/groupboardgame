package utils;

import java.util.Scanner;

/**
 * <pre>
 * This utility class is used within the application to obtain user input from the console.
 *
 * The class contains a private default constructor as it is not intended for this class
 * to be instantiated.
 *
 * The class is marked as final as it is not intended for this class to be extended.
 *
 * The methods in this class have been taken from the shop labs with minor adjustments.
 *
 * The class exposes the following public static methods:
 *
 *   validNextInt(prompt)  : Displays the prompt and returns a valid integer number entered by the user.
 *   validNextChar(prompt) : Displays the prompt and returns a valid character entered by the user.
 * </pre>
 *
 * @author Mark McDonald (20077698@mail.wit.ie)
 * @version 1.0 (06/04/2017)
 */
public final class Input
{
  //Constants to define error messages output from the validNextXXX methods
  private static final String ERROR_MESSAGE_CANNOT_BE_BLANK = "Entry cannot be blank";

  /**
   * Default constructor for the class.
   *
   * Input class should not be instantiated.
   */
  private Input()
  {
  }

  /**
   * This method displays the specified prompt on screen and returns a valid
   * integer number entered by the user.
   * If a valid integer number is not entered by the user then the prompting
   * continues until a valid integer number is entered.
   *
   * @param prompt The prompt text to be displayed on screen.
   *
   * @return The valid integer number entered by the user.
   */
  @SuppressWarnings("resource")
  public static int validNextInt(String prompt)
  {
    Scanner input = new Scanner(System.in);
    do
    {
      try
      {
        System.out.print(prompt);
        return Integer.parseInt(input.next());
      }
      catch (NumberFormatException e)
      {
        System.err.println("\tEnter a number please.");
      }
    }
    while (true);
  }

  /**
   * This method displays the specified prompt on screen and returns the
   * first character entered by the user.
   * If the user enters nothing then the prompting continues until the
   * user makes an entry that is not blank.
   * Once a non-blank entry is made by the user then the method returns
   * the first character of that entry.
   *
   * @param prompt The prompt text to be displayed on screen.
   *
   * @return The first character entered by the user.
   */
  @SuppressWarnings("resource")
  public static char validNextChar(String prompt)
  {
    Scanner input = new Scanner(System.in);
    do
    {
      try
      {
        System.out.print(prompt);
        String next = input.next();
        if (next == null)
        {
          throw new Exception(ERROR_MESSAGE_CANNOT_BE_BLANK);
        }
        else
        {
          next = next.trim();
          if (next.equals(""))
          {
            throw new Exception(ERROR_MESSAGE_CANNOT_BE_BLANK);
          }
        }
        return next.charAt(0);
      }
      catch (Exception e)
      {
        System.err.println(String.format("\t%s.", e.getMessage()));
      }
    }
    while (true);
  }
}
