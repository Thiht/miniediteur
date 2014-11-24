package minieditor.commands;

import minieditor.EditorEngine;
import minieditor.UserInterface;

public class ChangeSelection implements Command {

	private EditorEngine receiver;
	private UserInterface invoker;

	public ChangeSelection(EditorEngine receiver, UserInterface invoker) {
		this.receiver = receiver;
		this.invoker  = invoker;
	}

	@Override
	public void execute() {
		int beg = invoker.promptSelectionStart();
		int end = invoker.promptSelectionEnd();
		receiver.changeSelection(beg, end);
	}
}