package minieditor.commands;

import minieditor.EditorEngine;

/**
 * A command allowing to cut the selected content of an EditorEngine to its clipboard.
 */
public class Cut implements Command {

	private EditorEngine receiver;

	public Cut(EditorEngine receiver) {
		this.receiver = receiver;
	}

	/**
	 * Cut the selected text in the EditorEngine to its clipboard.
	 */
	@Override
	public void execute() {
		receiver.cut();
	}
}