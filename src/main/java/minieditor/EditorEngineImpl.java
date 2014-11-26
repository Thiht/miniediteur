package minieditor;

import minieditor.commands.RecordableCommand;

import java.util.Stack;

public class EditorEngineImpl implements EditorEngine {

	private StringBuilder buffer = new StringBuilder();
	private String clipboard = "";
	private int selectionStart = 0; // inclusive
	private int selectionEnd   = 0; // exclusive
	private final MacroRecorder macroRecorder = new MacroRecorderImpl();
	private final Stack<Memento> undo = new Stack<>();
	private final Stack<Memento> redo = new Stack<>();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void cut() {
		copy();
		delete();
		setCursor(selectionStart);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void copy() {
		clipboard = buffer.substring(selectionStart, selectionEnd);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void paste() {
		insertText(clipboard);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void insertText(String text) {
		delete();
		buffer.insert(selectionStart, text);
		setCursor(selectionStart + text.length());
	}

	@Override
	public String getContent() {
		return buffer.toString();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getClipboard() {
		return clipboard;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getSelectionStart() {
		return selectionStart;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getSelectionEnd() {
		return selectionEnd;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void changeSelection(int start, int end) {
		// Swap start and end if start > end
		if (start > end) {
			int tmp = start;
			start   = end;
			end     = tmp;
		}

		if (start < 0) {
			start = 0;
		}

		if (end > buffer.length()) {
			end = buffer.length();
		}

		selectionStart = start;
		selectionEnd   = end;
	}

	@Override
	public void save() {
		Memento m = getMemento();
		undo.push(m);
		redo.clear();
	}

	@Override
	public void undo() {
		if (!undo.empty()) {
			Memento m = undo.pop();
			redo.push(m);
			setMemento(m);
		}
	}

	@Override
	public void redo() {
		if (!redo.empty()) {
			Memento m = redo.pop();
			undo.push(m);
			setMemento(m);
		}
	}

	/**
	 * Delete the selected text.
	 */
	private void delete() {
		buffer.delete(selectionStart, selectionEnd);
	}

	/**
	 * Set a cursor (selection of size 0) to <code>position</code>.
	 * @param position The position we want to set the cursor to
	 */
	private void setCursor(int position) {
		changeSelection(position, position);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void startRecording() {
		macroRecorder.startRecording();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void stopRecording() {
		macroRecorder.stopRecording();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void record(RecordableCommand command) {
		macroRecorder.record(command);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void replay() {
		macroRecorder.replay();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Memento getMemento() {
		return new EditorEngineImplMemento(buffer, clipboard, selectionStart, selectionEnd);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setMemento(Memento memento) {
		// TODO
	}

	private class EditorEngineImplMemento implements Memento {
		private final StringBuilder buffer;
		private final String clipboard;
		private final int selectionStart;
		private final int selectionEnd;

		public EditorEngineImplMemento(StringBuilder buffer, String clipboard, int selectionStart, int selectionEnd) {
			this.buffer         = new StringBuilder(buffer);
			this.clipboard      = clipboard; // No need to copy because Strings are immutable
			this.selectionStart = selectionStart;
			this.selectionEnd   = selectionEnd;
		}

		public StringBuilder getBuffer() {
			return buffer;
		}

		public String getClipboard() {
			return clipboard;
		}

		public int getSelectionStart() {
			return selectionStart;
		}

		public int getSelectionEnd() {
			return selectionEnd;
		}
	}
}