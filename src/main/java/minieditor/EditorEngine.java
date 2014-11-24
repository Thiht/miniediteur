package minieditor;

public interface EditorEngine extends Recorder {
	/**
	 * Store the selected text to the clipboard, remove it and place
	 * the cursor at the beginning of the former selection.
	 */
	public void cut();

	/**
	 * Store the selected text to the clipboard.
	 */
	public void copy();

	/**
	 * Insert the content of the clipboard instead of the selection and place
	 * the cursor at the end of the insertion.
	 */
	public void paste();

	/**
	 * Insert text instead of the selection and place the cursor at
	 * the end of the insertion.
	 * @param text The text to insert
	 */
	public void insertText(String text);

	/**
	 * @return The current content of the editor
	 */
	public String getContent();

	/**
	 * @return The content of the clipboard
	 */
	public String getClipboard();

	/**
	 * @return The beginning index of the selection
	 */
	public int getSelectionStart();

	/**
	 * @return The ending index of the selection
	 */
	public int getSelectionEnd();

	/**
	 * Selects the text from <code>start</code> to <code>end</code> (exclusive).
	 * Indexing scheme:
	 * <pre>
	 * 0   1   2   3   4   5   6   7
	 * | m | y |   | t | e | x | t |
	 * </pre>
	 * If <code>start</code> &gt; <code>stop</code>, the two variables are swapped.
	 * If <code>start</code> &lt; 0, it's set to 0.
	 * If <code>end</code> &gt; the size of the content, it's set to the maximum
	 * possible size.
	 * If <code>start</code> = <code>end</code>, the selection is valid and results
	 * in a cursor.
	 * @param start The beginning of the selection
	 * @param end The end of the selection
	 */
	public void changeSelection(int start, int end);
}