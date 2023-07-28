package Strategy.BotMove;

import Model.*;

import java.util.List;

public class EasyBotMakeMoveFactory implements BotMakeMoveStrategy {
    @Override
    public void makeMove(Game game, Player player ) {
        List<List<Cell>> grid=game.getBoard().getBoard();
        for(int i=0;i<grid.size();i++)
        {
            for(int j=0;j<grid.size();j++)
            {
                if(grid.get(i).get(j).getCellStatus().equals(CellStatus.EMPTY))
                {
                    game.getBoard().getBoard().get(i).get(j).setPlayer(player);
                    game.getBoard().getBoard().get(i).get(j).setCellStatus(CellStatus.OCCUPIED);
                    game.getWinnerStrategy().updateMaps(player.getSymbol(),i,j);
                    game.setChance(game.getChance()+1);
                    game.getHistory().add(new Move(player,i,j));
                    return;
                }
            }
        }
    }
}
