package Model;

import java.util.ArrayList;
import java.util.List;

public class Board {
    List<List<Cell>> board;
    Board(int n)
    {
        this.board=new ArrayList<>();
        for(int i=0;i<n;i++)
        {
            ArrayList<Cell> temp=new ArrayList<Cell>();
            for(int j=0;j<n;j++)
            {
                temp.add(new Cell(i,j));
            }
            board.add(temp);
        }
    }
    public void displayBoard()
    {
        System.out.print("   ");
        for(int i=0;i<board.size();i++)
        {
            System.out.print("  "+i+"  ");
        }
        System.out.println();
        for(int i=0;i<board.size();i++)
        {
            System.out.print(" "+i+" ");
            for(int j=0;j<board.get(0).size();j++)
            {
                if(board.get(i).get(j).getCellStatus().equals(CellStatus.OCCUPIED))
                {
                    System.out.print("| "+board.get(i).get(j).getPlayer().getSymbol()+" |");
                }
                else
                {
                    System.out.print("|   |");
                }
            }
            System.out.println();
        }
    }

    public List<List<Cell>> getBoard() {
        return board;
    }

    public void setBoard(List<List<Cell>> board) {
        this.board = board;
    }
}
