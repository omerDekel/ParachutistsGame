package Controller.commands;

import Controller.GameLogic;

import java.util.HashMap;

/**
 *Main command executor abstract class.
 */
public abstract class CommandExecutor {
	/**
	 *game logic object to perform commands with.
	 */
	protected GameLogic logic;

	/**
	 * Execute handler's actions on logic, according to event additionalInfo.
	 * @param additionalInfo additionalInfo of command.
	 * @throws Exception optional exception for notifying controller.
	 */
	public abstract void execute(HashMap<String, String> additionalInfo) throws Exception;
}