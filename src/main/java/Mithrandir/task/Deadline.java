package Mithrandir.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import Mithrandir.MithrandirExceptions.InvalidArgumentException;

public class Deadline extends Task {
    private final LocalDateTime byTime;


    /**
     * Constructs a Deadline object from a string representation of the deadline.
     *
     * @param description the string representation of the deadline. It should be in the format
     *                    "task description /by date and time".
     * @throws DateTimeParseException if the date and time in the string cannot be parsed.
     */
    public Deadline(String description) throws DateTimeParseException, InvalidArgumentException {
        super(description.split("/by")[0].trim());
        try {
            this.byTime = LocalDateTime.parse(description.split("/by")[1].trim(), this.formatter);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidArgumentException("Deadline command need 3 parts: task description, '/by' and deadline, " +
                    "you are missing on something. Check your command!");
        } catch (DateTimeParseException e) {
            throw new InvalidArgumentException("Invalid date format. Please use the format: dd/MM/yyyy HH:mm.");
        }
    }

    @Override
    public String toFileString() {
        return "DEADLINE || " + super.toFileString() + String.format(" /by %s", this.byTime.format(this.formatter));
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.byTime.format(this.formatter));
    }
}
