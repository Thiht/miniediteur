package minieditor.commands;

import minieditor.EditorEngine;

public class Undo implements Command {

    private final EditorEngine receiver;

    public Undo(EditorEngine receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.undo();
    }
}
