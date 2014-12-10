package minieditor;

/**
 * Originator of the memento design pattern.
 */
public interface Originator {
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
