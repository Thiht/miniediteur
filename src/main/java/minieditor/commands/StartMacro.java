package minieditor.commands;

import minieditor.MacroRecorder;

/**
 * A command allowing to start the record a macro.
 */
public class StartMacro implements Command {

    private final MacroRecorder receiver;

    public StartMacro(MacroRecorder receiver) {
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
