package Controller.commands;

import Controller.GameLogic;

import java.util.HashMap;

/**
 * executor of beginControl new game command.
 */
public class StartGameCommand extends CommandExecutor {
	/**
	 * constructor.
	 * @param logic logic for execute the command.
	 */
	public StartGameCommand(GameLogic logic) {
		this.logic = logic;
	}

	/**
	 * execute start game command
	 * @param additionalInfo .
	 */
	public void execute(HashMap<String, String> additionalInfo) throws NewGameException {
		logic.updateGame(0.0);
		logic.initNewGame();
		throw new NewGameException();
	}
}