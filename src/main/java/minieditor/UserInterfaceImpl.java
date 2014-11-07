package minieditor;

import java.io.File;
import java.io.InputStream;

import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

import minieditor.commands.Command;

public class UserInterfaceImpl implements UserInterface {

	private HashMap<String, Command> commands = new HashMap<>();
	private Scanner in;
	private boolean terminate = false;

	public UserInterfaceImpl() {
		this(System.in);
	}

	public UserInterfaceImpl(InputStream inputStream) {
		in = new Scanner(inputStream);
	}

	public UserInterfaceImpl(File file) {
		in = new Scanner(file);
	}

	@Override
	public void runInvokerLoop() {
		terminate = false;
		while (!terminate) {
			String userInput = null;
			System.out.print("Entrez une commande a executer : ");
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
		System.out.print("Saisissez le texte a inserer : ");
		in.nextLine();
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