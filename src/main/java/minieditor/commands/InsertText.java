package minieditor.commands;

import minieditor.EditorEngine;
import minieditor.UserInterface;

/**
 * A command allowing to insert some text to an EditorEngine.
 */
public class InsertText implements Command {

	private EditorEngine receiver;
	private UserInterface invoker;

	public InsertText(EditorEngine receiver, UserInterface invoker) {
		this.receiver = receiver;
		this.invoker  = invoker;
	}

	/**
	 * Make the specified UserInterface prompt the use for a text to insert, and insert it to the EditorEngine.
	 */
	@Override
	public void execute() {
		String toInsert = invoker.promptTextToInsert();
		receiver.insertText(toInsert);
	}
}