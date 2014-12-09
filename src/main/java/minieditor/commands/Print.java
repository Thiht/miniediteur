package minieditor.commands;

import minieditor.EditorEngine;
import minieditor.Memento;
import minieditor.UserInterface;

/**
 * A command allowing to print the content of an EditorEngine.
 */
public class Print implements RecordableCommand {

	private final EditorEngine receiver;
	private final UserInterface invoker;
	private String toPrint;

	public Print(EditorEngine receiver, UserInterface invoker) {
		this.receiver = receiver;
		this.invoker  = invoker;
	}

	/**
	 * Retrieve the content of the specified EditorEngine and make the specified UserInterface print it.
	 */
	@Override
	public void execute() {
		toPrint = receiver.getContent();
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