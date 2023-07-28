import Controller.GameController;
import Model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        GameController gameController=new GameController();
        Game game;
        Scanner sc=new Scanner(System.in);
        System.out.println("==<<Hello TicTacToe game user>>==");
        System.out.println("===========================================================================================");
        System.out.println("General Instruction ");
        System.out.println("The game is played on a grid that's n squares by n squares.\n" +
                "You and your friend (or the computer if any) mark your symbol in that grid in rotation.\n" +
                "The first player to get n of his/her marks in a row (up, down, across, or diagonally) is the winner.\n" +
                "When all n*n squares are full, the game is over.");
        System.out.println("===========================================================================================");
        boolean flag=true;
        do
        {
            System.out.println("Lets setup the game settings ==>");
            System.out.println("Enter the board size n (ex : 3) :");
            int n=sc.nextInt();
            System.out.println("Enter the number of players (min 2  and max n-1):");
            int numOfPlayer=sc.nextInt();
            sc.nextLine();
            List<Player> listOfPlayers=new ArrayList<Player>();
            gameController.gameSettings(n,numOfPlayer,listOfPlayers);
            game=gameController.createGame(n,listOfPlayers);
            if(game!=null)
            {
                flag=false;
            }
        }while(flag);
        System.out.println("===========================================================================================");        System.out.println("We Have players as Follows");
        gameController.showPlayerList(game);
        System.out.println("===========================================================================================");
        while(game.getGameStatus().equals(GameStatus.INPROGRESS))
        {
            System.out.println("===========================================================================================");
            System.out.println("It's time for "+gameController.getCurrentTurnPlayer(game).getName()+" to provide input:");
            System.out.println("Do you want to undo (yes/no): ");
            char choice=sc.nextLine().toUpperCase().charAt(0);
            if(choice=='Y') {
                gameController.undo(game);
            }
            else {
                gameController.displayBoard(game);
                gameController.makeNextMove(game);
            }
            gameController.displayBoard(game);
            System.out.println("===========================================================================================");
        }
        if(game.getGameStatus().equals(GameStatus.WON)) {
            System.out.println("===========================================================================================");
            Player player=gameController.getWinner(game);
            System.out.println("It was good game and "+player.getName()+" has won the game");
            gameController.displayBoard(game);
            System.out.println("===========================================================================================");
        }
        else {
            System.out.println("===========================================================================================");
            System.out.println("It was good game but game is tied now");
            gameController.displayBoard(game);
            System.out.println("===========================================================================================");
        }
    }
}
