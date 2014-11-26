package minieditor.commands;

import minieditor.EditorEngine;
import minieditor.Memento;
import minieditor.UserInterface;

public class ChangeSelection implements RecordableCommand {

	private final EditorEngine receiver;
	private final UserInterface invoker;
	private int selectionStart;
	private int selectionEnd;
	private ChangeSelectionMemento memento;

	public ChangeSelection(EditorEngine receiver, UserInterface invoker) {
		this.receiver = receiver;
		this.invoker  = invoker;
	}

	@Override
	public void execute() {
		if (memento != null) { // If we're replaying a record
			selectionStart = memento.getSelectionStart();
			selectionEnd   = memento.getSelectionEnd();
			memento = null;
		}
		else {
			receiver.save();
			selectionStart = invoker.promptSelectionStart();
			selectionEnd   = invoker.promptSelectionEnd();
			receiver.record(this);
		}
		receiver.changeSelection(selectionStart, selectionEnd);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Memento getMemento() {
		return new ChangeSelectionMemento(selectionStart, selectionEnd);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setMemento(Memento memento) {
		this.memento = (ChangeSelectionMemento) memento;
	}

	/**
	 * Concrete Memento of the Memento design pattern.
	 */
	private class ChangeSelectionMemento implements Memento {
		private final int selectionStart;
		private final int selectionEnd;

		public ChangeSelectionMemento(int selectionStart, int selectionEnd) {
			this.selectionStart = selectionStart;
			this.selectionEnd   = selectionEnd;
		}

		public int getSelectionStart() {
			return selectionStart;
		}

		public int getSelectionEnd() {
			return selectionEnd;
		}
	}
}