package Controller.commands;

import java.util.HashMap;

/**
 * command info class :name and additional info if needed.
 */
public class CommandInfo {
	/**
	 * command's string name.
	 */
	public String name;
	/**
	 *  additional Information if needed.
	 */
	public HashMap<String, String> additionalInfo;

	/**
	 * Constructor .
	 * @param name name of the command.
	 */
	public CommandInfo(String name) {
		this.name = name;
		this.additionalInfo = new HashMap<>();
	}
}