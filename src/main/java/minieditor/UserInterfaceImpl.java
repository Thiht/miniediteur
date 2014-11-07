package minieditor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

import minieditor.commands.Command;

public class UserInterfaceImpl implements UserInterface {

	private HashMap<String, Command> commands = new HashMap<>();
	private Scanner in;
	private boolean terminate = true;

	public UserInterfaceImpl() {
		this(System.in);
	}

	public UserInterfaceImpl(InputStream inputStream) {
		in = new Scanner(inputStream);
	}

	public UserInterfaceImpl(File file) throws FileNotFoundException {
		in = new Scanner(file);
	}

	@Override
	public void runInvokerLoop() {
		terminate = false;
		while (!terminate) {
			String userInput = null;
			System.out.print("Write a command to run (write nothing to stop): ");
			userInput = in.nextLine();
			if (userInput == null) {
				terminate = true;
			}
			else {
				Command toExecute = commands.get(userInput);
				if (toExecute != null) {
					toExecute.execute();
				}
			}
		}
		in.close();
	}

	@Override
	public String getTextToInsert() {
		return prompt("Write some text to insert: ");
	}

	@Override
	public int getSelectionStart() {
		return Integer.parseInt(prompt("Beginning of the selection: "));
	}

	@Override
	public int getSelectionEnd() {
		return Integer.parseInt(prompt("End of the selection: "));
	}

	private String prompt(String promptMessage) {
		if (terminate) {
			throw new IllegalStateException("You must call the runInvokerLoop() method before calling this function");
		}
		System.out.print(promptMessage);
		return in.nextLine();
	}

	@Override
	public void print(String text) {
		System.out.println(text);
	}

	@Override
	public void terminate() {
		terminate = true;
	}

	@Override
	public void addCommand(String keyword, Command command) {
		Objects.requireNonNull(keyword);
		Objects.requireNonNull(command);
		commands.put(keyword, command);
	}
}