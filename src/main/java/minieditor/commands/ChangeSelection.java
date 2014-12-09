package minieditor.commands;

import minieditor.EditorEngine;
import minieditor.UserInterface;

/**
 * A command allowing to change the selection in an EditorEngine.
 */
public class ChangeSelection implements Command {

	private EditorEngine receiver;
	private UserInterface invoker;

	public ChangeSelection(EditorEngine receiver, UserInterface invoker) {
		this.receiver = receiver;
		this.invoker  = invoker;
	}

	/**
	 * Make the user interface ask the user for the new selection, and change it in the EditorEngine.
	 */
	@Override
	public void execute() {
		int beg = invoker.promptSelectionStart();
		int end = invoker.promptSelectionEnd();
		receiver.changeSelection(beg, end);
	}
}