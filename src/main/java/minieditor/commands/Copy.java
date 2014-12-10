package minieditor.commands;

import minieditor.EditorEngine;
import minieditor.Memento;

/**
 * A command allowing to copy the selected content of an EditorEngine to its clipboard.
 */
public class Copy implements RecordableCommand {

	private final EditorEngine receiver;

	public Copy(EditorEngine receiver) {
		this.receiver = receiver;
	}

	/**
	 * Copy the selected text in the EditorEngine to its clipboard.
	 */
	@Override
	public void execute() {
		receiver.save();
		receiver.copy();
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