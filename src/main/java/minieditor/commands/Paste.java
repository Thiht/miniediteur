package minieditor.commands;

import minieditor.EditorEngine;

/**
 * A command allowing to paste the content of a clipboard.
 */
public class Paste implements Command {

	private EditorEngine receiver;

	public Paste(EditorEngine receiver) {
		this.receiver = receiver;
	}

	/**
	 * Paste the clipboard's content of the specified EditorEngine.
	 */
	@Override
	public void execute() {
		receiver.paste();
	}
}