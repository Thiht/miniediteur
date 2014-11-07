package minieditor;

import minieditor.commands.Command;

public interface UserInterface {
	public void runInvokerLoop();
	public void terminate();
	public String getTextToInsert();
	public void addCommand(String keyword, Command command);
}