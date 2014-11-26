package minieditor.commands;

import minieditor.EditorEngine;
import minieditor.Memento;
import minieditor.UserInterface;

/**
 * Concrete command of the Command design pattern.
 * Originator of the Memento design pattern.
 */
public class Print implements RecordableCommand {

	private final EditorEngine receiver;
	private final UserInterface invoker;

	public Print(EditorEngine receiver, UserInterface invoker) {
		this.receiver = receiver;
		this.invoker  = invoker;
	}

	@Override
	public void execute() {
		String toPrint = receiver.getContent();
		receiver.record(this);
		invoker.print(toPrint);
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