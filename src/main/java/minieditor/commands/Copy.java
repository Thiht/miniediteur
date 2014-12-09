package minieditor.commands;

import minieditor.EditorEngine;

/**
 * A command allowing to copy the selected content of an EditorEngine to its clipboard.
 */
public class Copy implements Command {

	private EditorEngine receiver;

	public Copy(EditorEngine receiver) {
		this.receiver = receiver;
	}

	/**
	 * Copy the selected text in the EditorEngine to its clipboard.
	 */
	@Override
	public void execute() {
		receiver.copy();
	}
}