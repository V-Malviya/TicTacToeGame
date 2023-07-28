package Model;

import java.util.Scanner;

public class Player {
    private String name;
    private char symbol;
    Player()
    {

    }
    public Player(String name, char ch)
    {
        this.name=name;
        this.symbol=ch;
    }
    public String getName() {
        return name;
    }
    public char getSymbol() {
        return symbol;
    }
    public void makeMove(Game game)
    {
        Scanner sc=new Scanner(System.in);
        boolean flag=true;
        int r,c;
        do {
            System.out.println("It's turn for "+name);
            System.out.println("Enter row number (0-based indexing):");
            r=sc.nextInt();
            System.out.println("Enter col number (0-based indexing):");
            c=sc.nextInt();
            if(isValidMove(game,r,c))
            {
                flag=false;
                game.getBoard().getBoard().get(r).get(c).setPlayer(this);
                game.getBoard().getBoard().get(r).get(c).setCellStatus(CellStatus.OCCUPIED);
                game.getWinnerStrategy().updateMaps(this.symbol,r,c);
                game.setChance(game.getChance()+1);
                game.getHistory().add(new Move(this,r,c));
            }
            else
            {
                System.out.println("Invalid move");
            }
        }
        while(flag);
    }
    boolean isValidMove(Game game,int row,int col)
    {
        Board board=game.getBoard();
        if(row>=board.getBoard().size() || row<0 || col>=board.getBoard().size() || col<0 )
        {
            return false;
        }
        else if(board.getBoard().get(row).get(col).getCellStatus().equals(CellStatus.EMPTY))
        {
            return true;
        }
        return false;
    }
}
