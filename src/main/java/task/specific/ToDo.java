package task.specific;

import java.util.ArrayList;

import exceptions.InvalidElementInList;
import task.Task;

/**
 * Represents a task that should be completed by the user.
 * A ToDo object does not have a deadline nor a starting and ending time.
 */
public class ToDo extends Task {

    /**
     * Creates a new ToDo task.
     *
     * @param description Description of the task given by the user.
     * @param finishType Whether it has been completed or not.
     * @throws InvalidElementInList Currently acting on an element that is invaalid.
     */
    public ToDo(String description, boolean finishType) throws InvalidElementInList {
        super(description, finishType);
    }

    /**
     * Creates a new ToDo task.
     *
     * @param description Description of the task given by the user.
     * @param finishType The status as to the user's progress on the task.
     * @param finishType Whether it has been completed or not.
     * @throws InvalidElementInList Currently acting on an element that is invalid.
     */
    public ToDo(String description, boolean finishType, ArrayList<String> tags)
            throws InvalidElementInList {
        super(description, finishType, tags);
    }

    @Override
    public String toString() {
        return "[ToDo]" + super.toString() + super.taggedToPrint();
    }

    @Override
    public String store() {
        return "[ToDo]\"\"" + super.store() + super.taggedStrings();
    }
}
