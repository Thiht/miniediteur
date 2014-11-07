package minieditor.commands;

import minieditor.EditorEngine;

public class Copy implements Command {

	private EditorEngine receiver;

	public Copy(EditorEngine receiver) {
		this.receiver = receiver;
	}

	@Override
	public void execute() {
		receiver.copy();
	}
}