package minieditor.commands;

import minieditor.Memento;

/**
 * A command which can be replayed as a macro.
 */
public interface RecordableCommand extends Command {

    /**
     * @return A memento representing the current state of this command.
     */
    public Memento getMemento();

    /**
     * Load a memento.
     * @param memento The memento to load
     */
    public void setMemento(Memento memento);
}
