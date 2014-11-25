package minieditor.commands;

import minieditor.MacroRecorder;

public class StopMacro implements Command {

    private final MacroRecorder receiver;

    public StopMacro(MacroRecorder receiver) {
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
