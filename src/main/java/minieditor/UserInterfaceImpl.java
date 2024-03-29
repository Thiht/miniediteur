package minieditor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

import minieditor.commands.Command;

/**
 * Implementation of an UserInterface.
 */
public class UserInterfaceImpl implements UserInterface {

	private HashMap<String, Command> commands = new HashMap<>();
	private Scanner in;
	private PrintStream out;
	private boolean terminate = true;

	/**
	 * Use the {@link #UserInterfaceImpl(InputStream, PrintStream) UserInterfaceImpl} constructor
	 * with the values <code>System.in</code> and <code>System.out</code>.
	 */
	public UserInterfaceImpl() {
		this(System.in, System.out);
	}

	/**
	 * @param inputStream A stream used to read the commands to run and their arguments
	 * @param outputStream A stream used to display results or prompts
	 */
	public UserInterfaceImpl(InputStream inputStream, PrintStream outputStream) {
		in = new Scanner(inputStream);
		this.out = outputStream;
	}

	/**
	 * @param file A file containing the commands to run and their arguments
	 * @param outputStream A stream used to display results or prompts
	 * @throws FileNotFoundException If the provided file doesn't exist
	 */
	public UserInterfaceImpl(File file, PrintStream outputStream) throws FileNotFoundException {
		in = new Scanner(file);
		this.out = outputStream;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void runInvokerLoop() {
		terminate = false;
		while (!terminate) {
			String userInput;
			out.print("Write a command to run: ");
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String promptTextToInsert() {
		return promptString("Write some text to insert: ");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int promptSelectionStart() {
		return promptInt("Beginning of the selection: ");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int promptSelectionEnd() {
		return promptInt("End of the selection: ");
	}

	private void prompt(String promptMessage) {
		if (terminate) {
			throw new IllegalStateException("You must call the runInvokerLoop() method before calling this method");
		}

		out.print(promptMessage);
	}

	private String promptString(String promptMessage) {
		prompt(promptMessage);
		return in.nextLine();
	}

	private int promptInt(String promptMessage) {
		prompt(promptMessage);
		int ret = 0;
		boolean ok = false;
		while (!ok) {
			try {
				ret = in.nextInt();
				ok = true;
			} catch (InputMismatchException e) {
				prompt("Erreur : entrez un entier : ");
				in.nextLine();
			}
		}

		in.nextLine(); // Consume the \n character
		return ret;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void print(String text) {
		if (terminate) {
			throw new IllegalStateException("You must call the runInvokerLoop() method before calling this method");
		}

		out.println(text);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void terminate() {
		terminate = true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addCommand(String keyword, Command command) {
		Objects.requireNonNull(keyword);
		Objects.requireNonNull(command);
		commands.put(keyword, command);
	}
}