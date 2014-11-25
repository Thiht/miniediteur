package minieditor.commands;

import minieditor.Originator;

/**
 * A command which can be replayed as a macro.
 */
public interface RecordableCommand extends Command, Originator {

}
