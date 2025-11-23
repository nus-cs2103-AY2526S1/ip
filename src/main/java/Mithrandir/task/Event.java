package Mithrandir.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import Mithrandir.MithrandirExceptions.DateTimeFormatException;
import Mithrandir.MithrandirExceptions.InvalidArgumentException;
import Mithrandir.MithrandirExceptions.MithrandirException;

public class Event extends Task {
    private final LocalDateTime fromTime;
    private final LocalDateTime toTime;

    /**
     * Constructs a new Event object from a given description.
     *
     * @param description A string containing the description of the event.
     *                    The description must be in the format:
     *                    task description /from start time /to end time.
     *                    The start time and end time must be in the format:
     *                    dd/MM/yyyy HH:mm.
     * @throws DateTimeParseException if the start time or end time in the description
     *                                cannot be parsed into a LocalDateTime object.
     */
    public Event(String description) throws MithrandirException {
        super(description.split("/from")[0].trim());
        String[] params = getStrings(description);
        try {
            this.fromTime = LocalDateTime.parse(params[0].trim(), this.formatter);
            this.toTime = LocalDateTime.parse(params[1].trim(), this.formatter);
        } catch (DateTimeParseException e) {
            throw new DateTimeFormatException("Invalid date format. Please use the format: dd/MM/yyyy HH:mm.");
        }
    }

    private static String[] getStrings(String description) throws InvalidArgumentException {
        String[] params = null;
        try {
            params = description.split("/from")[1].split("/to");
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidArgumentException("Invalid event format. Please use the format: " +
                    "task description /from start time /to end time.");
        }
        if (params.length != 2) {
            throw new InvalidArgumentException("Invalid event format. Please use the format: " +
                    "task description /from start time /to end time.");
        }
        return params;
    }

    @Override
    public String toFileString() {
        return "EVENT || " + super.toFileString() + String.format(" /from %s /to %s",
                this.fromTime.format(this.formatter), this.toTime.format(this.formatter));
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.fromTime.format(this.formatter),
                this.toTime.format(this.formatter));
    }
}
