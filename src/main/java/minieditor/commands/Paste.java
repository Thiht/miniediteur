package minieditor.commands;

import minieditor.EditorEngine;

public class Paste implements Command {

	private EditorEngine receiver;

	public Paste(EditorEngine receiver) {
		this.receiver = receiver;
	}

	@Override
	public void execute() {
		receiver.paste();
	}
}