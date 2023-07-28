package Controller;

import Exceptions.GameBuildExceptions;
import Model.*;

import java.util.List;
import java.util.Scanner;

public class GameController {
    public void gameSettings(int size,int numOfPlayer,List<Player> listOfPlayers)
    {
        Scanner sc= new Scanner(System.in);
        for(int i=0;i<numOfPlayer;i++)
        {
            if(i==0)
            {
                System.out.println("Enter the name of player "+(i+1));
                String name=sc.nextLine();
                System.out.println("Enter the symbol for player "+name);
                char ch=sc.nextLine().charAt(0);
                listOfPlayers.add(new Player(name,ch));
            }
            else
            {
                System.out.println("Enter the name of player "+(i+1));
                String name=sc.nextLine();
                System.out.println("Enter the symbol for player "+name);
                char ch=sc.nextLine().charAt(0);
                System.out.println("Is the player "+name+" is Bot(true/false)");
                boolean isBot=sc.nextBoolean();
                sc.nextLine();
                if(isBot)
                {

                    System.out.println("Enter the difficulty level (Easy/medium/hard)");
                    String input=sc.nextLine().toUpperCase();
                    BotDifficultyLevel diff=BotDifficultyLevel.valueOf(input);
                    listOfPlayers.add(new Bot(name,ch,diff));
                }
                else {
                    listOfPlayers.add(new Player(name, ch));
                }
            }
        }
    }
    public Game createGame(int dimension, List<Player> players){
        try {
            return Game.getBuilder().setDimension(dimension).setPlayers(players).build();
        }
        catch(GameBuildExceptions e)
        {
            System.out.println("Encountered Game Build exception please provide proper input");
            return null;
        }
    }
    public void showPlayerList(Game game)
    {
        game.showPlayer();
    }
    public Player getCurrentTurnPlayer(Game game)
    {
        return game.getPlayers().get(game.getChance()%(game.getPlayers().size()));
    }
    public void makeNextMove(Game game)
    {
        if(game.getChance()==game.getBoard().getBoard().size()*game.getBoard().getBoard().size())
        {
            game.setGameStatus(GameStatus.DRAW);
            return;
        }
        game.nextMove();
    }
    public void displayBoard(Game game)
    {
        game.getBoard().displayBoard();
    }
    public void undo(Game game)
    {
        if(game.getHistory().size()==0)
        {
            System.out.println("Cannot undo as we are at very beginning of Game");
        }
        else
        {
            game.undoLastMove();
        }
    }
    public Player getWinner(Game game)
    {
        return game.getWinner();
    }

}
