package minieditor.commands;

import minieditor.EditorEngine;

public class Cut implements Command {

	private EditorEngine receiver;

	public Cut(EditorEngine receiver) {
		this.receiver = receiver;
	}

	@Override
	public void execute() {
		receiver.cut();
	}
}