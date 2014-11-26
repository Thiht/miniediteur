package minieditor.commands;

import minieditor.EditorEngine;
import minieditor.Memento;

/**
 * Concrete command of the Command design pattern.
 * Originator of the Memento design pattern.
 */
public class Paste implements RecordableCommand {

	private final EditorEngine receiver;

	public Paste(EditorEngine receiver) {
		this.receiver = receiver;
	}

	@Override
	public void execute() {
		receiver.save();
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