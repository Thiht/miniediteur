package minieditor;

public class EditorEngineImpl implements EditorEngine {

	private StringBuilder buffer = new StringBuilder();
	private String clipboard     = "";
	private int selectionStart   = 0; // inclusive
	private int selectionEnd     = 0; // exclusive

	/**
		* Store the selected text to the clipboard, remove it and place
		* the cursor at the beginning of the former selection
	*/
	@Override
	public void cut() {
		copy();
		delete();
		setCursor(selectionStart);
	}

	/**
		* Store the selected text to the clipboard
	*/
	@Override
	public void copy() {
		clipboard = buffer.substring(selectionStart, selectionEnd);
	}

	/**
		* Insert the content of the clipboard instead of the selection and place
		* the cursor at the end of the insertion
	*/
	@Override
	public void paste() {
		insertText(clipboard);
	}

	/**
		* Insert text instead of the selection and place the cursor at
		* the end of the insertion
		* @param text The text to insert
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

	@Override
	public String getClipboard() {
		return clipboard;
	}

	@Override
	public int getSelectionStart() {
		return selectionStart;
	}

	@Override
	public int getSelectionEnd() {
		return selectionEnd;
	}

	/**
		* Selects the text from <code>start</code> (inclusive) to
		* <code>end</code> (exclusive)
		* @param start The beginning of the selection
		* @param end The end of the selection
	*/
	@Override
	public void changeSelection(int start, int end) {
		selectionStart = Math.min(start, end);
		selectionEnd   = Math.max(start, end);
	}

	/**
		* Delete the selected text
	*/
	private void delete() {
		buffer.delete(selectionStart, selectionEnd);
	}

	/**
		* Set a cursor (selection of size 0) to <code>position</code>
		* @param position The position we want to set the cursor to
	*/
	private void setCursor(int position) {
		changeSelection(position, position);
	}
}