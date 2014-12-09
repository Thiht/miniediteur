package minieditor.commands;

import minieditor.EditorEngine;
import minieditor.Memento;

/**
 * A command allowing to paste the content of a clipboard.
 */
public class Paste implements RecordableCommand {

	private final EditorEngine receiver;

	public Paste(EditorEngine receiver) {
		this.receiver = receiver;
	}

	/**
	 * Paste the clipboard's content of the specified EditorEngine.
	 */
	@Override
	public void execute() {
		receiver.paste();
		receiver.record(this);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Memento getMemento() {
		return new Memento() {};
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setMemento(Memento memento) {
		// Do nothing
	}
}