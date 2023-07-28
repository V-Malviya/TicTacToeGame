package Model;

import Factory.BotMakeMoveFactory;
import Strategy.BotMove.BotMakeMoveStrategy;

public class Bot extends Player{
    BotDifficultyLevel difficultyLevel;
    BotMakeMoveStrategy moveStrategy;
    public Bot(String name, char sym, BotDifficultyLevel difficultyLevel)
    {
        super(name,sym);
        this.difficultyLevel=difficultyLevel;
        this.moveStrategy=BotMakeMoveFactory.getMakeMoveStrategy(difficultyLevel);
    }
    public void makeMove(Game game)
    {
        System.out.println("It's turn for "+this.getName());
        moveStrategy.makeMove(game,this);
    }
}
