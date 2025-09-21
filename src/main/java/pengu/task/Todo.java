package pengu.task;

import pengu.exception.InvalidFieldException;
import pengu.exception.PenguException;
import pengu.exception.SaveFileException;

/**
 * Class that specifies a task which is a todo.
 */
public class Todo extends Task {
    /**
     * Constructor for a Todo instance
     * @param description Description of task
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public void updateField(String fieldLabel, String value) throws InvalidFieldException {
        try {
            super.updateField(fieldLabel, value);
        } catch (PenguException e) {
            String errorMessage = "Invalid field label for todo!\n"
                    + "Please specify one of the following:\n"
                    + "  /desc.";
            throw new InvalidFieldException(errorMessage);
        }
    }

    /**
     * Returns a Todo object as represented in the line in the save file.
     * @param line The line in the save file.
     * @return A todo represented by the line.
     * @throws SaveFileException If the line is not valid Todo representation.
     */
    public static Todo fromSaveFileFormat(String line) throws SaveFileException {
        String[] fields = line.split(" \\| ");
        if (fields.length != 3) {
            String errorMessage = "Unknown task format found in save file:\n" + line;
            throw new SaveFileException(errorMessage);
        }

        boolean isDone = Task.fromIsDoneStr(fields[1]);
        String description = fields[2];

        return new Todo(description, isDone);
    }

    /**
     * @return String representation of the Todo task.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    @Override
    public String toSaveFileFormat() {
        return String.format("T | %s", super.toSaveFileFormat());
    }
}
