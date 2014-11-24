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
		receiver.paste();
		receiver.record(this);
	}

	@Override
	public Memento getMemento() {
		return new PasteMemento();
	}

	@Override
	public void setMemento(Memento memento) {
		// Do nothing
	}

	private class PasteMemento implements Memento {

	}
}