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
		int beg = invoker.getSelectionStart();
		int end = invoker.getSelectionEnd();
		receiver.changeSelection(beg, end);
	}
}