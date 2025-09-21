package pengu.task;

import java.time.LocalDateTime;

import pengu.DateTimeParser;
import pengu.exception.InvalidFieldException;
import pengu.exception.PenguException;
import pengu.exception.SaveFileException;

/**
 * Class that specifies a task which is an Event.
 * Start and end of event are stored in the class.
 */
public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructor for an Event instance.
     *
     * @param description Description of task.
     * @param from        Start of event.
     * @param to          End of event.
     */
    public Event(String description, boolean isDone, LocalDateTime from, LocalDateTime to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public void updateField(String fieldLabel, String value) throws InvalidFieldException {
        try {
            switch (fieldLabel) {
            case "/from" -> updateFrom(value);
            case "/to" -> updateTo(value);
            default -> super.updateField(fieldLabel, value);
            }
        } catch (PenguException e) {
            String errorMessage = "Invalid field label for event!\n"
                    + "Please specify one of the following:\n"
                    + "  /desc, /from, /to.";
            throw new InvalidFieldException(errorMessage);
        }
    }

    private void updateFrom(String value) throws InvalidFieldException {
        from = DateTimeParser.fromDateTimeString(value);
    }

    private void updateTo(String value) throws InvalidFieldException {
        to = DateTimeParser.fromDateTimeString(value);
    }

    /**
     * Returns a Event object as represented in the line in the save file.
     *
     * @param line The line in the save file.
     * @return A event represented by the line.
     * @throws SaveFileException If the line is not a valid Event representation.
     */
    public static Event fromSaveFileFormat(String line) throws SaveFileException {
        String[] fields = line.split(" \\| ");
        if (fields.length != 5) {
            String errorMessage = "Unknown task format found in save file:\n" + line;
            throw new SaveFileException(errorMessage);
        }

        boolean isDone = Task.fromIsDoneStr(fields[1]);
        String description = fields[2];
        LocalDateTime from = Task.fromSaveFileDateTime(fields[3]);
        LocalDateTime to = Task.fromSaveFileDateTime(fields[4]);

        return new Event(description, isDone, from, to);
    }

    /**
     * @return String representation of the event task.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
                DateTimeParser.toOutputFormatString(from),
                DateTimeParser.toOutputFormatString(to));
    }

    @Override
    public String toSaveFileFormat() {
        return String.format("E | %s | %s | %s", super.toSaveFileFormat(),
                DateTimeParser.toInputFormatString(from),
                DateTimeParser.toInputFormatString(to));
    }
}
