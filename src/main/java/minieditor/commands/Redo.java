package minieditor.commands;

import minieditor.EditorEngine;

/**
 * A command allowing to redo the lact action undone.
 */
public class Redo implements Command {

    private final EditorEngine receiver;

    public Redo(EditorEngine receiver) {
        this.receiver = receiver;
    }

    /**
     * Redo the last cancelled action.
     */
    @Override
    public void execute() {
        receiver.redo();
    }
}
