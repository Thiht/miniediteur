package minieditor;

import minieditor.commands.RecordableCommand;

import java.util.SortedMap;
import java.util.TreeMap;

public class RecorderImpl implements Recorder {
    private boolean recording = false;
    private final SortedMap<Memento, RecordableCommand> commandsToReplay = new TreeMap<>();

    @Override
    public void startRecording() {
        if (recording) {
            throw new IllegalStateException("Already recording");
        }

        recording = true;
        commandsToReplay.clear();
    }

    @Override
    public void stopRecording() {
        recording = false;
    }

    @Override
    public void record(RecordableCommand command) {
        if (recording) {
            commandsToReplay.put(command.getMemento(), command);
        }
    }

    @Override
    public void replay() {
        if (recording) {
            throw new IllegalStateException("Impossible to replay while recording");
        }

        for (Memento memento : commandsToReplay.keySet()) {
            RecordableCommand command = commandsToReplay.get(memento);
            command.setMemento(memento);
            command.execute();
        }
    }
}
