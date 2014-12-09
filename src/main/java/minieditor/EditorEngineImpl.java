package minieditor;

/**
 * Implementation of an EditorEngine.
 */
public class EditorEngineImpl implements EditorEngine {

	private StringBuilder buffer = new StringBuilder();
	private String clipboard     = "";
	private int selectionStart   = 0; // inclusive
	private int selectionEnd     = 0; // exclusive

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

		if (start > buffer.length()) {
			start = buffer.length();
		}

		if (end < 0) {
			end = 0;
		}

		if (end > buffer.length()) {
			end = buffer.length();
		}

		selectionStart = start;
		selectionEnd   = end;
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
}