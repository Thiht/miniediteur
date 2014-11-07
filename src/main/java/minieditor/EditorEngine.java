package minieditor;

public interface EditorEngine {
	public void cut();
	public void copy();
	public void paste();
	public void insertText(String text);
	public void changeSelection(int beg, int end);
}