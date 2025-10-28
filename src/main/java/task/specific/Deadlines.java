package task.specific;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import exceptions.InvalidDateInput;
import exceptions.InvalidElementInList;
import task.Task;

/**
 * Creates Deadline objects that are then later added into the task list.
 * A deadline must always have a deadline since if not, it is not considered
 * to be a deadline.
 */
public class Deadlines extends Task {
    private final LocalDate deadline;

    /**
     * Creates a new Deadline object.
     *
     * @param description Description of a deadline task provided by the user.
     * @param deadline When the task must be done also specified by the user.
     * @throws InvalidElementInList If an element does not exist.
     * @throws InvalidDateInput If the user input the date wrongly.
     */
    public Deadlines(String description, String deadline) throws InvalidElementInList, InvalidDateInput {
        super(description);
        try {
            assert deadline != null;
            this.deadline = LocalDate.parse(deadline);
        } catch (Exception e) {
            throw new InvalidDateInput();
        }
    }

    /**
     * Create a new Deadline with a different constructor.
     * @param description Description of the deadline.
     * @param deadline When the task should be done specified by the user.
     * @param finishStatus Whether it has been completed or not specified by the user.
     * @throws InvalidElementInList If an element does not exist.
     * @throws InvalidDateInput If the user input the date wrongly.
     */
    public Deadlines(String description, String deadline, boolean finishStatus)
            throws InvalidElementInList, InvalidDateInput {
        super(description, finishStatus);
        try {
            assert deadline != null;
            this.deadline = LocalDate.parse(deadline);
        } catch (Exception e) {
            throw new InvalidDateInput();
        }
    }


    /**
     * Create a new Deadline with a different constructor.
     * @param description Description of the deadline.
     * @param deadline When the task should be done specified by the user.
     * @param finishStatus Whether it has been completed or not.
     * @throws InvalidElementInList If an element does not exist.
     * @throws InvalidDateInput If the user input the date wrongly.
     */
    public Deadlines(String description, String deadline, boolean finishStatus,
                     ArrayList<String> tags) throws InvalidElementInList, InvalidDateInput {
        super(description, finishStatus, tags);
        try {
            assert deadline != null;
            this.deadline = LocalDate.parse(deadline);
        } catch (Exception e) {
            throw new InvalidDateInput();
        }
    }

    /**
     * Return the String for a Deadline task.
     */
    public String toString() {
        String e = this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[Deadline]" + super.toString() + " (by: " + e + ") "
                + super.taggedToPrint();
    }

    /**
     * Stores the user list into the stated document as specified in storage.
     */
    @Override
    public String store() {
        return "[Deadline]\"\"" + super.store()
                + "\\by" + deadline.toString() + "\"\"" + super.taggedStrings();
    }

    /**
     * Returns the deadline in it's toString() format.
     */
    public String getDeadline() {
        return this.deadline.toString();
    }
}
