package minieditor.commands;

import minieditor.EditorEngine;

/**
 * A command allowing to undo the last action.
 */
public class Undo implements Command {

    private final EditorEngine receiver;

    public Undo(EditorEngine receiver) {
        this.receiver = receiver;
    }

    /**
     * Undo the last action.
     */
    @Override
    public void execute() {
        receiver.undo();
    }
}
