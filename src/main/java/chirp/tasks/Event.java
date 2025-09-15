package chirp.tasks;

import java.time.LocalDate;

import chirp.exceptions.ChirpException;
import chirp.exceptions.TaskEmptyAttributeException;
import chirp.io.Attribute;
import chirp.io.Parser;

/**
 * Represents event task object
 */
public class Event extends Task {
    public static final String TAG = "E";
    private LocalDate startTime;
    private LocalDate endTime;

    /**
     * Creates a task with a active period
     *
     * @param description Task description
     * @param startTime   Start date in yyyy-MM-dd format
     * @param endTime     End date in yyyy-MM-dd format
     * @throws ChirpException
     */
    public Event(String description, String startTime, String endTime) throws ChirpException {
        super(description);
        if (startTime.isEmpty()) {
            throw new TaskEmptyAttributeException("event", Attribute.FROM.getTag());
        }
        if (endTime.isEmpty()) {
            throw new TaskEmptyAttributeException("event", Attribute.TO.getTag());
        }
        this.startTime = Parser.convertDateAttr(startTime, Attribute.FROM.getTag());
        this.endTime = Parser.convertDateAttr(endTime, Attribute.TO.getTag());
    }

    /**
     * Deserialise a data string to a event task object
     *
     * @param data Data string
     * @return The corresponding event task object
     * @throws ChirpException
     */
    public static Event deserialise(String data) throws ChirpException {
        String[] fields = deserialiseFields(data, TAG, 5);
        Event task = new Event(fields[2], fields[3], fields[4]);
        task.setDone(fields[1]);
        return task;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String serialise() {
        return String.format("%s|%s|%s|%s|%s", TAG, isDone ? "X" : "O", description, startTime, endTime);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("[%s]%s (from: %s to: %s)", TAG, super.toString(), startTime, endTime);
    }

    /**
     * @param date Date filter
     * @return True if date filter lies in event period
     */
    @Override
    public boolean validForDate(LocalDate date) {
        if (date == null) {
            return true;
        }
        return (date.isAfter(startTime) && date.isBefore(endTime)) || date.isEqual(startTime) || date.isEqual(endTime);
    }
}
