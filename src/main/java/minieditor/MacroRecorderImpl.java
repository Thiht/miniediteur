package minieditor;

import minieditor.commands.RecordableCommand;

import java.util.LinkedHashMap;
import java.util.Map;

public class MacroRecorderImpl implements MacroRecorder {
    private boolean recording = false;
    private final Map<Memento, RecordableCommand> commandsToReplay = new LinkedHashMap<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public void startRecording() {
        if (recording) {
            System.err.println("Already recording");
            return;
        }

        recording = true;
        commandsToReplay.clear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stopRecording() {
        recording = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void record(RecordableCommand command) {
        if (recording) {
            commandsToReplay.put(command.getMemento(), command);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void replay() {
        if (recording) {
            System.err.println("Impossible to replay while recording");
            return;
        }

        for (Memento memento : commandsToReplay.keySet()) {
            RecordableCommand command = commandsToReplay.get(memento);
            command.setMemento(memento);
            command.execute();
        }
    }
}
