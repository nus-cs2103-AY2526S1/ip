package pengu.task;

import java.time.LocalDateTime;

import pengu.DateTimeParser;
import pengu.exception.InvalidFieldException;
import pengu.exception.PenguException;
import pengu.exception.SaveFileException;

/**
 * Class that specifies a task which has a deadline for completion.
 */
public class Deadline extends Task {
    private LocalDateTime by;

    /**
     * Constructor for a Deadline instance
     * @param description Description of task
     * @param by          When the deadline is due
     */
    public Deadline(String description, boolean isDone, LocalDateTime by) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public void updateField(String fieldLabel, String value) throws InvalidFieldException {
        try {
            if (fieldLabel.equals("/by")) {
                updateBy(value);
            } else {
                super.updateField(fieldLabel, value);
            }
        } catch (PenguException e) {
            String errorMessage = "Invalid field label for deadline!\n"
                    + "Please specify one of the following:\n"
                    + "  /desc, /by.";
            throw new InvalidFieldException(errorMessage);
        }
    }

    private void updateBy(String value) throws InvalidFieldException {
        by = DateTimeParser.fromDateTimeString(value);
    }


    /**
     * Returns a Deadline object as represented in the line in the save file.
     * @param line The line in the save file.
     * @return A deadline represented by the line.
     * @throws SaveFileException If the line is not a valid Deadline representation.
     */
    public static Deadline fromSaveFileFormat(String line) throws SaveFileException {
        String[] fields = line.split(" \\| ");
        if (fields.length != 4) {
            String errorMessage = "Unknown task format found in save file:\n" + line;
            throw new SaveFileException(errorMessage);
        }

        boolean isDone = Task.fromIsDoneStr(fields[1]);
        String description = fields[2];
        LocalDateTime by = Task.fromSaveFileDateTime(fields[3]);

        return new Deadline(description, isDone, by);
    }

    /**
     * @return String representation of the deadline task
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), DateTimeParser.toOutputFormatString(by));
    }

    @Override
    public String toSaveFileFormat() {
        return String.format("D | %s | %s", super.toSaveFileFormat(),
                DateTimeParser.toInputFormatString(by));
    }
}
