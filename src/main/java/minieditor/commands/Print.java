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
	private PrintMemento memento;

	public Print(EditorEngine receiver, UserInterface invoker) {
		this.receiver = receiver;
		this.invoker  = invoker;
	}

	@Override
	public void execute() {
		if (memento != null) { // If we're replaying a record
			toPrint = memento.getTextToPrint();
			memento = null;
		}
		else {
			toPrint = receiver.getContent();
			receiver.record(this);
		}
		invoker.print(toPrint);
	}

	@Override
	public Memento getMemento() {
		return new PrintMemento(toPrint);
	}

	@Override
	public void setMemento(Memento memento) {
		this.memento = (PrintMemento) memento;
	}

	private class PrintMemento implements Memento {
		private final String textToPrint;

		public PrintMemento(String textToPrint) {
			this.textToPrint = textToPrint;
		}

		public String getTextToPrint() {
			return textToPrint;
		}
	}
}