package minieditor.commands;

import minieditor.Recorder;

public class ReplayMacro implements Command {

    private final Recorder receiver;

    public ReplayMacro(Recorder receiver) {
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