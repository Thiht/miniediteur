package minieditor.commands;

import minieditor.EditorEngine;
import minieditor.UserInterface;

public class InsertText implements Command {

	private EditorEngine receiver;
	private UserInterface invoker;

	public InsertText(EditorEngine receiver, UserInterface invoker) {
		this.receiver = receiver;
		this.invoker  = invoker;
	}

	@Override
	public void execute() {
		String toInsert = invoker.promptTextToInsert();
		receiver.insertText(toInsert);
	}
}