package minieditor.commands;

import minieditor.EditorEngine;
import minieditor.UserInterface;

public class Print implements Command {

	private EditorEngine receiver;
	private UserInterface invoker;

	public Print(EditorEngine receiver, UserInterface invoker) {
		this.receiver = receiver;
		this.invoker  = invoker;
	}

	@Override
	public void execute() {
		String toPrint = receiver.getContent();
		invoker.print(toPrint);
	}
}