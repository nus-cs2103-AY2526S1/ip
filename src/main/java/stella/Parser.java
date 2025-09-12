package stella;

import stella.exception.ExcessParameterException;
import stella.exception.IncompleteInstructionException;
import stella.exception.InsufficientParameterException;
import stella.exception.UnknownInstructionException;

/**
 * Responsible in making sense of the user command, for example to
 * identify the command type and also to reformat the date provided
 * by the user to a more human-readable format
 */
public class Parser {
    private TaskList tasks;

    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Craft Stella's response based on user's command
     *
     * @param description A String consisting of user's command
     * @return Stella's response
     * @throws IncompleteInstructionException If command contain insufficient information
     * @throws UnknownInstructionException If no
     * such command exists
     */
    public String findCommand(String description) throws IncompleteInstructionException,
            UnknownInstructionException, ExcessParameterException, InsufficientParameterException {
        if (description.equals(("list"))) {
            return this.tasks.printList();
        } else if (description.contains("find")) {
            return tasks.findItem(description);
        } else if (description.contains("delete")) {
            int index = findIndexForModification("delete", description);
            return tasks.deleteItem(index);
        } else if (description.contains("unmark")) {
            int index = findIndexForModification("unmark", description);
            return tasks.modifyItem(index, "unmark");
        } else if (description.contains("mark")) {
            int index = findIndexForModification("mark", description);
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

    private int findIndexForModification(String marker, String description)
            throws IncompleteInstructionException {
        int referencePoint = 0;
        if (marker == "delete" || marker == "unmark") {
            referencePoint = 7;
        } else if (marker == "mark") {
            referencePoint = 5;
        }

        if (description.length() <= referencePoint) {
            throw new IncompleteInstructionException(description);
        }

        return Integer.valueOf(description.substring(referencePoint)) - 1;
    }

    public static String formatTime(String time) {
        if (time.length() == 10) {
            return TimeConverter.convertDate(time);
        }
        if (time.length() == 15) {
            return TimeConverter.convertDateWithTime(time);
        }
        return time;
    }

    public static long countParameter(String command) {
        return command.chars().filter(c -> c == '/').count();
    }
}