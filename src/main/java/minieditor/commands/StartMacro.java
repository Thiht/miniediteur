package minieditor.commands;

import minieditor.Recorder;

public class StartMacro implements Command {

    private final Recorder receiver;

    public StartMacro(Recorder receiver) {
        this.receiver = receiver;
    }

    /**
     * Start the recording of a macro.
     */
    @Override
    public void execute() {
        receiver.startRecording();
    }
}
