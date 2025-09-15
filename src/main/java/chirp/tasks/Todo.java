package chirp.tasks;

import chirp.exceptions.ChirpException;

/**
 * Represents todo Task Object
 */
public class Todo extends Task {
    public static final String TAG = "T";

    /**
     * Creates a task to be done
     *
     * @param description Task description
     * @throws ChirpException
     */
    public Todo(String description) throws ChirpException {
        super(description);
    }

    /**
     * Deserialises a data string to a Todo task object
     *
     * @param data Data string
     * @return The corresponding Todo task object
     * @throws ChirpException
     */
    public static Todo deserialise(String data) throws ChirpException {
        String[] fields = deserialiseFields(data, TAG, 3);
        Todo task = new Todo(fields[2]);
        task.setDone(fields[1]);
        return task;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String serialise() {
        return String.format("%s|%s|%s", TAG, isDone ? "X" : "O", description);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("[%s]%s", TAG, super.toString());
    }
}
