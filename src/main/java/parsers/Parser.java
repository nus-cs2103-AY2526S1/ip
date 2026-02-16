package parsers;

import exception.NicholasException;
import tasks.TaskList;
import tasks.ToDoTask;
import tasks.DeadlineTask;
import tasks.EventTask;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Parses user inputs into instructions
 * <p>
 * This class handles the inputs of users by processing and breaking the input down
 * for the rest of the chatbot application.
 */
public class Parser {
    private static final String LIST = "list";
    private static final String MARK = "mark";
    private static final String UNMARK = "unmark";
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";
    private static final String DELETE = "delete";
    private static final String FIND = "find";
    private static final String TAG = "tag";

    /* Parse user input */
    public String parseCommand(String input, TaskList tasks) throws NicholasException {

        String command = input.split(" ")[0];

        switch (command) {
        case LIST -> {
            return tasks.getList();
        }
        case MARK -> {
            int idx = prepareIndex(input);
            return tasks.markTaskAsDone(idx);
        }
        case UNMARK -> {
            int idx = prepareIndex(input);
            return tasks.markTaskAsUndone(idx);
        }
        case TODO -> {
            return tasks.addItem(prepareToDo(input));
        }
        case DEADLINE -> {
            try {
                return tasks.addItem(prepareDeadlineTask(input));
            } catch (ArrayIndexOutOfBoundsException e) {
                String arrayDeadlineIndexError = "Please enter a deadline. e.g. /by Sunday";

                return arrayDeadlineIndexError;
            }
        }
        case EVENT -> {
            try {
                return tasks.addItem(prepareEventTask(input));
            } catch (ArrayIndexOutOfBoundsException e) {
                String arrayEventIndexError = "Please enter a valid start and end time e.g. /from Mon 2pm /to 4pm";

                return arrayEventIndexError;
            }
        }
        case DELETE -> {
            int idx = prepareIndex(input);
            return tasks.deleteTask(idx);
        }
        case FIND -> {
            return tasks.findTask(prepareKeyword(input));
        }
        case TAG -> {
            int idx = prepareIndex(input.split(" #")[0]);
            return tasks.tagTask(idx, input.split(" ")[2]);
        }
        default -> {
            String emptyCommandError = "Please enter a valid task (todo, deadline, event)";

            throw new NicholasException(emptyCommandError);
        }
        }
    }

    /* Prepare and create todo tasks */
    public ToDoTask prepareToDo(String input) {
        String description = input.replace(TODO, "");
        return new ToDoTask(description);
    }

    /* prepare and create deadline tasks */
    public DeadlineTask prepareDeadlineTask(String input) {
        String description = input.replace(DEADLINE, "");
        String[] temp = description.split("/by ");
        return new DeadlineTask(validateDate(temp[1]), temp[0]);
    }

    /* prepare and create eventtasks */
    public EventTask prepareEventTask(String input) {
        String description = input.replace(EVENT, "");
        String[] task = description.split("/from ");
        String[] time = task[1].split("/to ");
        return new EventTask(task[0],validateDate(time[0]), validateDate(time[1]));
    }

    /* prepare index for tasks */
    public int prepareIndex(String input) throws NicholasException {
        int idx = Integer.parseInt(input.split(" ")[1]);
        return idx;
    }

    /* prepare keyword for find task */
    public String prepareKeyword(String input) throws NicholasException{
        String keyword = input.replace(FIND, "").trim();
        if (keyword.equals("")) {
            throw new NicholasException("Please enter a task to find:");
        }
        return keyword;
    }

    /* Validating date input */
    public LocalDate validateDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDate dateTime = LocalDate.parse(date.trim(), formatter);
        return dateTime;
    }
}
