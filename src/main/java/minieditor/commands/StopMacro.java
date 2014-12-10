package minieditor.commands;

import minieditor.Recorder;

/**
 * A command allowing to stop the record a macro.
 */
public class StopMacro implements Command {

    private final Recorder receiver;

    public StopMacro(Recorder receiver) {
        this.receiver = receiver;
    }

    /**
     * Stop the recording of a macro.
     */
    @Override
    public void execute() {
        receiver.stopRecording();
    }
}
