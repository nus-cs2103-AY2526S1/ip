package hope.commands;

import hope.customexceptions.RedundantStateChangeException;
import hope.storage.TaskStorage;
import hope.storage.ToDoList;

/**
 * A command that marks a task as done in a to-do list and updates the storage.
 * This class implements the {@link Command} interface and processes user input to mark
 * a task identified by its index as completed in the {@link ToDoList}. It validates the
 * input, updates the task's status, and persists the changes to the {@link TaskStorage}.
 * If the input is invalid or the task is already marked, appropriate error messages are displayed.
 */
public class MarkCommand implements Command {

    /** The to-do list containing the task to be marked as done. */
    private ToDoList toDoList;

    /** The storage system for updating the persisted tasks. */
    private TaskStorage taskStorage;

    /**
     * Constructs a {@code MarkCommand} with the specified to-do list and task storage.
     *
     * @param toDoList    the {@link ToDoList} containing the task to be marked
     * @param taskStorage the {@link TaskStorage} to be updated after marking the task
     */
    public MarkCommand(ToDoList toDoList, TaskStorage taskStorage) {
        this.toDoList = toDoList;
        this.taskStorage = taskStorage;
    }

    /**
     * Executes the command to mark a task as done based on the provided input.
     * The input is expected to be a {@code String} representing a numeric index (1-based)
     * of the task to mark. If the input is not a valid number, exceeds the list size,
     * or is less than 1, an error message is printed, and no task is marked. If the task
     * is already marked as done, a {@link RedundantStateChangeException} is caught, and
     * an error message is displayed. Otherwise, the task is marked as done, the
     * {@link TaskStorage} is updated, and a confirmation message is printed along with
     * the task details.
     *
     * @param o the input object, expected to be a {@code String} containing the 1-based index
     *          of the task to mark as done
     */
    @Override
    public String execute(Object o) {
        if (o instanceof String) {
            try {
                int input = Integer.parseInt((String) o);
            } catch (NumberFormatException e) {
                return """
                        Pray, employ the noble digits as thy guiding input!
                        (Please use numerics as input only)
                        """;
            }
            int input = Integer.parseInt((String) o);
            if (input > toDoList.size()) {
                return """
                        Thy request doth stray beyond the hallowed limits.
                        (The number you have input is greater than the number of tasks you currently have)
                        """;
            }
            if (input < 1) {
                return """
                        Doth this be a jest, good sir?
                        (Negative numbers are not accepted as input)
                        """;
            }
            try {
                toDoList.get(input - 1).markAsDone();
            } catch (RedundantStateChangeException e) {
                return """
                        Verily, the noble quest was marked from the very outset; hath thy memory slipped\
                         away like a fleeting shadow?
                        (The task was already marked to begin with)
                        """;
            }
            taskStorage.update(toDoList);
            return "Hark, I declare the #" + input + " noble endeavor accomplished!\n"
                    + toDoList.get(input - 1) + "\n";
        }
        return "Something went wrong :("; // should never come to this as parser ensures string input
    }
}
