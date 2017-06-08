package controllers;

import java.util.Scanner;

/**
 * Created by Niall Coady and Robert Alexander on 31/05/2017.
 */
public class Driver {
    private Scanner input;
    public int playerNumber;
    private Game game;

    public static void main(String[] args)
    {
        new Driver();
    }

    public Driver()
    {
        input = new Scanner(System.in);
        runMainMenu();
    }

    public int mainMenu()
    {
        //TODO Main menu display here
        return 1;
    }

    public void runMainMenu()
    {
        int option = mainMenu();
        while (option != 0)
        {
            //TODO Main menu functions here
        }
    }

    public int newGame()
    {
        //TODO new Game menu display here
        return 1;
    }

    public void runNewGame()
    {
        int option = newGame();
        while (option != 0)
        {
            //TODO new Game menu functions here
        }
    }

    public int play()
    {
        //TODO Main menu display here
        return 1;
    }

    public void runPlay()
    {
        int option = play();
        while (option != 0)
        {
            //TODO Main menu functions here
        }
    }

    /*public void load() throws Exception
    {
        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is = xstream.createObjectInputStream
                (new FileReader("game.xml"));
        game = (Game) is.readObject();
        is.close();
    }

    public void save() throws Exception
    {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream
                (new FileWriter("game.xml"));
        out.writeObject(game);
        out.close();
    }*/

}
