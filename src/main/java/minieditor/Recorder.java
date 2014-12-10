package minieditor;

import minieditor.commands.RecordableCommand;

/**
 * Caretaker of the Memento pattern.
 * Allow to record and replay a sequence of recordable commands.
 */
public interface Recorder {
    /**
     * Start the recording of a macro. If a macro is already saved, it'll be erased.
     */
    public void startRecording();

    /**
     * Stop the recording of a macro.
     */
    public void stopRecording();

    /**
     * Record a command to be replayed later as part of a macro.
     * @param command A command to record
     */
    public void record(RecordableCommand command);

    /**
     * Replay the currently saved macro.
     */
    public void replay();
}
