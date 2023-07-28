package Factory;

import Model.BotDifficultyLevel;
import Strategy.BotMove.BotMakeMoveStrategy;
import Strategy.BotMove.DifficultBotMakeMoveFactory;
import Strategy.BotMove.EasyBotMakeMoveFactory;
import Strategy.BotMove.MediumBotMakeMoveFactory;

public class BotMakeMoveFactory {
    public static BotMakeMoveStrategy getMakeMoveStrategy(BotDifficultyLevel dif)
    {
        if(dif.equals(BotDifficultyLevel.EASY))
        {
            return new EasyBotMakeMoveFactory();
        }
        else if(dif.equals(BotDifficultyLevel.MEDIUM))
        {
            return new MediumBotMakeMoveFactory();
        }
        else
        {
            return new DifficultBotMakeMoveFactory();
        }
    }

}
