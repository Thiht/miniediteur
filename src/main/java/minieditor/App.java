package minieditor;

import minieditor.commands.*;

public class App {
	private UserInterface invoker = new UserInterfaceImpl();
	private EditorEngine receiver = new EditorEngineImpl();

	public static void main(String[] args) {
		App app = new App();
		app.launch();
	}

	private void launch() {
		configureCommands();
		invoker.runInvokerLoop();
	}

	private void configureCommands() {
		invoker.addCommand("p", new Print(receiver, invoker));
		invoker.addCommand("i", new InsertText(receiver, invoker));
		invoker.addCommand("s", new ChangeSelection(receiver, invoker));
		invoker.addCommand("x", new Cut(receiver));
		invoker.addCommand("c", new Copy(receiver));
		invoker.addCommand("v", new Paste(receiver));
		invoker.addCommand("q", invoker::terminate);
	}
}