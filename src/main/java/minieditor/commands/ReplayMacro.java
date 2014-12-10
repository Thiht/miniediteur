package minieditor.commands;

import minieditor.MacroRecorder;

/**
 * A command allowing to replay a macro.
 */
public class ReplayMacro implements Command {

    private final MacroRecorder receiver;

    public ReplayMacro(MacroRecorder receiver) {
        this.receiver = receiver;
    }

    /**
     * Plays the currently saved macro.
     */
    @Override
    public void execute() {
        receiver.replay();
    }
}
