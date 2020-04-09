package Controller.commands;

import Controller.GameLogic;

import java.util.HashMap;

/**
 * Command executer class for released key command.
 */
public class ReleaseKeyCommand extends CommandExecutor {
	/**
	 * ReleaseKeyCommand class constructor.
	 * @param model .
	 */
	public ReleaseKeyCommand(GameLogic model) {
		this.logic = model;
	}

	/**
	 * @param  additionalInfo for the command, the number of the key.
	 */
	public void execute(HashMap<String, String> additionalInfo) {
		int keyNumber = Integer.parseInt(additionalInfo.get("keyNumber"));
		if (keyNumber <= 255 && logic.IsKeyPressesd(keyNumber)) {
				logic.setKeyState(keyNumber, false);
		}
	}
}