package minieditor.commands;

import minieditor.EditorEngine;
import minieditor.Memento;

/**
 * Concrete command of the Command design pattern.
 * Originator of the Memento design pattern.
 */
public class Cut implements RecordableCommand {

	private final EditorEngine receiver;

	public Cut(EditorEngine receiver) {
		this.receiver = receiver;
	}

	@Override
	public void execute() {
		receiver.cut();
		receiver.record(this);
	}

	@Override
	public Memento getMemento() {
		return new CutMemento();
	}

	@Override
	public void setMemento(Memento memento) {
		// Do nothing
	}

	private class CutMemento implements Memento {

	}
}