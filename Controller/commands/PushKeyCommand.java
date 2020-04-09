package Controller.commands;

import Controller.GameLogic;

import java.util.HashMap;

public class PushKeyCommand extends CommandExecutor {
	/**
	 * PushKeyCommand class constructor.
	 *
	 * @param model for executing command.
	 */
	public PushKeyCommand(GameLogic model) {
		this.logic = model;
	}

	/**
	 * execute push key command by notifying the logic accordingly.
	 *
	 * @param additionalInfo additionalInfo of command (Integer number of key).
	 */
	public void execute(HashMap<String, String> additionalInfo) {
		int key = Integer.parseInt(additionalInfo.get("keyNumber"));
		//avoiding irrelevant keys
		if (!logic.IsKeyPressesd(key)&& key <= 255) {
				logic.setKeyState(key,true);
		}
	}
}