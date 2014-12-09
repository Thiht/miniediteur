package minieditor;

import minieditor.commands.Command;

/**
 * Operations of an user interface.
 */
public interface UserInterface {
	/**
	 * Ask the user for a command to run.
	 */
	public void runInvokerLoop();

	/**
	 * Terminate the invoker loop.
	 */
	public void terminate();

	/**
	 * Ask the user for a text to insert.
	 * @return The text the user inserted
	 */
	public String promptTextToInsert();

	/**
	 * Ask the user for a selection start.
	 * @return The starting index of the new selection
	 */
	public int promptSelectionStart();

	/**
	 * Ask the user for a selection end.
	 * @return The ending index of the new selection
	 */
	public int promptSelectionEnd();

	/**
	 * Print a text on the user interface.
	 * @param text The text to print
	 */
	public void print(String text);

	/**
	 * Add a command to the list of executable commands.
	 * @param keyword The alias name of the command
	 * @param command The command to run when called
	 */
	public void addCommand(String keyword, Command command);
}