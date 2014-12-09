package minieditor.commands;

import minieditor.EditorEngine;
import minieditor.UserInterface;

/**
 * A command allowing to print the content of an EditorEngine.
 */
public class Print implements Command {

	private EditorEngine receiver;
	private UserInterface invoker;

	public Print(EditorEngine receiver, UserInterface invoker) {
		this.receiver = receiver;
		this.invoker  = invoker;
	}

	/**
	 * Retrieve the content of the specified EditorEngine and make the specified UserInterface print it.
	 */
	@Override
	public void execute() {
		String toPrint = receiver.getContent();
		invoker.print(toPrint);
	}
}