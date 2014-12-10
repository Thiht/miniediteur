package minieditor.commands;

import minieditor.EditorEngine;
import minieditor.Memento;

/**
 * A command allowing to cut the selected content of an EditorEngine to its clipboard.
 */
public class Cut implements RecordableCommand {

	private final EditorEngine receiver;

	public Cut(EditorEngine receiver) {
		this.receiver = receiver;
	}

	/**
	 * Cut the selected text in the EditorEngine to its clipboard.
	 */
	@Override
	public void execute() {
		receiver.save();
		receiver.cut();
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