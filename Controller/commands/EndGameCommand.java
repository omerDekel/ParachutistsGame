package Controller.commands;

import Controller.GameLogic;

import java.util.HashMap;

/**
 * Created by Omer Dekel on 09/04/2020.
 */
public class EndGameCommand extends CommandExecutor {
    public EndGameCommand(GameLogic logic) {
        this.logic = logic;
    }

    @Override
    public void execute(HashMap<String, String> additionalInfo) throws Exception {
        this.logic.closeGame();
    }
}
