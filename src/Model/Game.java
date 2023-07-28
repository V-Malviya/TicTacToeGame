package Model;

import Exceptions.GameBuildExceptions;
import Strategy.GameWinning.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Game {
    private int chance;
    private Board board;
    private List<Player> players;
    private GameStatus gameStatus;
    private List<Move> history;
    private Player winner;
    private checkWinnerStrategy winnerStrategy;
    private Game()
    {

    }
    public static GameBuilder getBuilder()
    {
        return new GameBuilder();
    }

    public int getChance() {
        return chance;
    }

    public void setChance(int chance) {
        this.chance = chance;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public List<Move> getHistory() {
        return history;
    }

    public void setHistory(List<Move> history) {
        this.history = history;
    }

    public Player getWinner() {
        return winner;
    }
    public void setWinner(Player winner) {
        this.winner = winner;
    }
    public void showPlayer()
    {
        for(int i=0;i<players.size();i++)
        {
            System.out.println("Player name : "+players.get(i).getName()+"\t Symbol : "+players.get(i).getSymbol());
        }
    }
    public void nextMove()
    {
        Player currentPlayer=players.get(chance%(this.getPlayers().size()));
        currentPlayer.makeMove(this);
        if(winnerStrategy.winChecker(history.get(history.size()-1)))
        {
            this.setWinner(currentPlayer);
        }
        gameStatusUpdater();
    }
    public void gameStatusUpdater()
    {
        if(winner!=null)
        {
            setGameStatus(GameStatus.WON);
        }
        else if(chance==board.getBoard().size()*board.getBoard().size())
        {
            setGameStatus(GameStatus.DRAW);
        }
    }
    public void undoLastMove()
    {
        Move lastMove=history.get(history.size()-1);
        history.remove(history.size()-1);
        chance=chance-1;
        board.getBoard().get(lastMove.getRow()).get(lastMove.getCol()).setCellStatus(CellStatus.EMPTY);
        board.getBoard().get(lastMove.getRow()).get(lastMove.getCol()).setPlayer(null);
        winnerStrategy.updateMapUndo(lastMove.getPlayer().getSymbol(),lastMove.getCol(),lastMove.getCol());
    }
    public checkWinnerStrategy getWinnerStrategy() {
        return winnerStrategy;
    }

    public void setWinnerStrategy(checkWinnerStrategy winnerStrategy) {
        this.winnerStrategy = winnerStrategy;
    }

    public static class GameBuilder
    {
        private int dimension;
        private List<Player> players;
        public GameBuilder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }
        public GameBuilder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }
        public int getDimension() {
            return dimension;
        }
        public List<Player> getPlayers() {
            return players;
        }
        public boolean isValid() throws GameBuildExceptions {
                if (this.dimension < 3 || this.dimension <= this.players.size()|| this.players.size()==0) {
                    throw new GameBuildExceptions("Some issue with dimension of board or number of players");
                }
                HashSet<Character> symbols=new HashSet<>();
                for(int i=0;i<this.players.size();i++)
                {
                    if(symbols.contains(this.players.get(i).getSymbol()))
                    {
                        throw new GameBuildExceptions("Two players having same symbol"+this.players.get(i).getSymbol());
                    }
                    else
                    {
                        symbols.add(this.players.get(i).getSymbol());
                    }
                }
                return true;
        }
        public Game build() throws GameBuildExceptions {
            if(!this.isValid())
            {
                return null;
            }
            Game game=new Game();
            game.setGameStatus(GameStatus.INPROGRESS);
            game.setChance(0);
            game.setBoard(new Board(this.getDimension()));
            game.setHistory(new ArrayList<Move>());
            game.setWinner(null);
            game.setPlayers(this.getPlayers());
            game.winnerStrategy=new OderOneStrategyOfWinnerCheck(this.getDimension());
            return game;
        }
    }
}
