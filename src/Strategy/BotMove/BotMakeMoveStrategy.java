package Strategy.BotMove;

import Model.Game;
import Model.Player;

public interface BotMakeMoveStrategy {
    public void makeMove(Game game, Player player);
}
