package Controller.commands;

import Controller.GameLogic;

import java.util.HashMap;

/**
 * Command executer class for released key command.
 */
public class ReleaseKeyCommand extends CommandExecutor {
	/**
	 * ReleaseKeyCommand class constructor.
	 * @param logic .
	 */
	public ReleaseKeyCommand(GameLogic logic) {
		this.logic = logic;
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