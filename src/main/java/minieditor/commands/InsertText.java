package minieditor.commands;

import minieditor.EditorEngine;
import minieditor.Memento;
import minieditor.UserInterface;

/**
 * Concrete command of the Command design pattern.
 * Originator of the Memento design pattern.
 */
public class InsertText implements RecordableCommand {

	private final EditorEngine receiver;
	private final UserInterface invoker;
	private String toInsert;
	private InsertTextMemento memento;

	public InsertText(EditorEngine receiver, UserInterface invoker) {
		this.receiver = receiver;
		this.invoker  = invoker;
	}

	@Override
	public void execute() {
		if (memento != null) { // If we're replaying a record
			toInsert = memento.getTextToInsert();
			memento  = null;
		}
		else {
			receiver.save();
			toInsert = invoker.promptTextToInsert();
			receiver.record(this);
		}
		receiver.insertText(toInsert);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Memento getMemento() {
		return new InsertTextMemento(toInsert);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setMemento(Memento memento) {
		this.memento = (InsertTextMemento) memento;
	}

	/**
	 * Concrete Memento of the Memento design pattern.
	 */
	private class InsertTextMemento implements Memento {
		private final String textToInsert;

		public InsertTextMemento(String textToInsert) {
			this.textToInsert = textToInsert;
		}

		public String getTextToInsert() {
			return textToInsert;
		}
	}
}