package minieditor;

public interface EditorEngine {
	public void cut();
	public void copy();
	public void paste();
	public void insertText(String text);
	public String getContent();
	public String getClipboard();
	public int getSelectionStart();
	public int getSelectionEnd();
	public void changeSelection(int beg, int end);
}