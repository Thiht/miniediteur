package minieditor;

import minieditor.commands.*;

public class App {
	private UserInterface invoker;
	private EditorEngine receiver;

	public static void main(String[] args) {
		App app = new App();
		app.run();
	}

	private void run() {
		invoker  = new UserInterfaceImpl(System.in);
		receiver = new EditorEngineImpl();
		configureCommands();
		invoker.runInvokerLoop();
	}

	private void configureCommands() {
		invoker.addCommand("i", new InsertText(receiver, invoker));
		invoker.addCommand("x", new Cut(receiver));
		invoker.addCommand("c", new Copy(receiver));
		invoker.addCommand("p", new Paste(receiver));
		invoker.addCommand("q", new Command() {
			@Override
			public void execute() {
				invoker.terminate();
			}
		});
	}
}