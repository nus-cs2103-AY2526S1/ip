package stella;

import stella.exception.IncompleteInstructionException;
import stella.exception.StellaException;
import stella.exception.UnknownInstructionException;

import stella.task.Deadline;
import stella.task.Event;
import stella.task.ToDo;

/**
 * Interprets the user command,
 * for example to identify the command type,
 * and also to reformat the date provided by the user to a more human-readable format.
 */
public class Parser {
    private TaskList tasks;

    /**
     * Constructs a new Parser based on the specified tasks.
     *
     * @param tasks The tasks.
     */
    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Crafts Stella's response based on user's command.
     *
     * @param description A String consisting of user's command.
     * @return Stella's response.
     * @throws StellaException If the user enters an invalid command.
     */
    public String findCommand(String description) throws StellaException {
        assert description.length() > 0;

        if (description.equals("list")) {
            return this.tasks.printList();
        } else if (description.contains("find")) {
            return tasks.findItem(description);
        } else if (description.contains("delete")) {
            int index = findIndexForModification("delete ", description);
            return tasks.deleteItem(index);
        } else if (description.contains("unmark")) {
            int index = findIndexForModification("unmark ", description);
            return tasks.modifyItem(index, "unmark");
        } else if (description.contains("mark")) {
            int index = findIndexForModification("mark ", description);
            return tasks.modifyItem(index, "mark");
        } else if (description.contains("todo")) {
            ToDo temp = ToDo.createTask(description);
            return tasks.addItem(temp);
        } else if (description.contains("deadline")) {
            Deadline temp = Deadline.createTask(description);
            return tasks.addItem(temp);
        } else if (description.contains("event")) {
            Event temp = Event.createTask(description);
            return tasks.addItem(temp);
        } else {
            throw new UnknownInstructionException(description);
        }
    }

    private int findIndexForModification(
            String marker, String description) throws IncompleteInstructionException {
        if (description.length() <= marker.length()) {
            throw new IncompleteInstructionException(description);
        }

        String userInputValue = description.substring(marker.length());
        return Integer.valueOf(userInputValue) - 1;
    }

    /**
     * Returns the reformatted time if possible.
     * If it is not possible to reformat,
     * return the time in the same raw format provided.
     *
     * @param time Time in raw format.
     * @return Formatted time if possible. Else, return the time in raw format.
     */
    public static String formatTime(String time) {
        if (time.length() == TimeConverter.VALID_DATE.length()) {
            return TimeConverter.convertDate(time);
        }
        if (time.length() == TimeConverter.VALID_DATE_WITH_TIME.length()) {
            return TimeConverter.convertDateWithTime(time);
        }
        return time;
    }
}
