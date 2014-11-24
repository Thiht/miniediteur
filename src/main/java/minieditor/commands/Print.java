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
	private String toPrint;

	public Print(EditorEngine receiver, UserInterface invoker) {
		this.receiver = receiver;
		this.invoker  = invoker;
	}

	@Override
	public void execute() {
		toPrint = receiver.getContent();
		receiver.record(this);
		invoker.print(toPrint);
	}

	@Override
	public Memento getMemento() {
		return new PrintMemento();
	}

	@Override
	public void setMemento(Memento memento) {
		// Do nothing
	}

	private class PrintMemento implements Memento {

	}
}