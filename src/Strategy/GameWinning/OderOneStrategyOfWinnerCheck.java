package Strategy.GameWinning;

import Model.Game;
import Model.Move;
import Model.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OderOneStrategyOfWinnerCheck implements checkWinnerStrategy{
    List<HashMap<Character,Integer>> rowCount;
    List<HashMap<Character,Integer>> colCount;
    HashMap<Character,Integer> firstDiaCount;
    HashMap<Character,Integer> secondDiaCount;
    public OderOneStrategyOfWinnerCheck(int size)
    {
        rowCount=new ArrayList<>();
        colCount=new ArrayList<>();
        firstDiaCount=new HashMap<>();
        secondDiaCount=new HashMap<>();
        for(int i=0;i<size;i++)
        {
            rowCount.add(new HashMap<>());
            colCount.add(new HashMap<>());
        }
    }

    @Override
    public boolean winChecker(Move move) {
        Player player=move.getPlayer();
        int r=move.getRow();
        int c=move.getCol();
        if(rowCount.get(r).get(player.getSymbol())==rowCount.size())
        {
            return true;
        }
        if(colCount.get(c).get(player.getSymbol())==colCount.size())
        {
            return true;
        }
        if(r==c)
        {
            if(firstDiaCount.get(player.getSymbol())==colCount.size())
            {
                return true;
            }
        }
        if(r+c==colCount.size()-1)
        {
            if(secondDiaCount.get(player.getSymbol())==colCount.size())
            {
                return true;
            }
        }
        return false;
    }
    public void updateMaps(char ch,int i,int j)
    {
        if(rowCount.get(i).containsKey(ch))
        {
           rowCount.get(i).put(ch,rowCount.get(i).get(ch)+1);
        }
        else
        {
            rowCount.get(i).put(ch,1);
        }
        if(colCount.get(j).containsKey(ch))
        {
            colCount.get(j).put(ch,colCount.get(j).get(ch)+1);
        }
        else
        {
            colCount.get(j).put(ch,1);
        }
        if(i==j)
        {
            if(firstDiaCount.containsKey(ch))
            {
                firstDiaCount.put(ch,firstDiaCount.get(ch)+1);
            }
            else
            {
                firstDiaCount.put(ch,1);
            }
        }
        if(i+j==colCount.size()-1)
        {
            if(secondDiaCount.containsKey(ch))
            {
                secondDiaCount.put(ch,secondDiaCount.get(ch)+1);
            }
            else
            {
                secondDiaCount.put(ch,1);
            }
        }
    }
    public void updateMapUndo(char ch,int r,int c)
    {
        rowCount.get(r).put(ch,rowCount.get(r).get(ch)-1);
        colCount.get(c).put(ch,colCount.get(c).get(ch)-1);
        if(r==c)
        {
            firstDiaCount.put(ch,firstDiaCount.get(ch)-1);
        }
        if(r+c==colCount.size()-1)
        {
            secondDiaCount.put(ch,secondDiaCount.get(ch)-1);
        }
    }
}
