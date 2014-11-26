package minieditor.commands;

import minieditor.EditorEngine;

public class Redo implements Command {

    private final EditorEngine receiver;

    public Redo(EditorEngine receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.redo();
    }
}
