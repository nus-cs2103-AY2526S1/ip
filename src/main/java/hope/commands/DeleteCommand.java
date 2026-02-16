package hope.commands;

import hope.storage.TaskStorage;
import hope.storage.ToDoList;

/**
 * A command that deletes a task from a to-do list and updates the storage.
 * This class implements the {@link Command} interface and processes user input to remove
 * a task identified by its index from the {@link ToDoList}. It validates the input,
 * removes the task, updates the {@link TaskStorage}, and displays appropriate messages.
 * If the input is invalid, error messages are printed, and no task is removed.
 */
public class DeleteCommand implements Command {

    /** The to-do list from which the task will be removed. */
    private ToDoList toDoList;

    /** The storage system for updating the persisted tasks. */
    private TaskStorage taskStorage;

    /**
     * Constructs a {@code DeleteCommand} with the specified to-do list and task storage.
     *
     * @param toDoList    the {@link ToDoList} from which the task will be removed
     * @param taskStorage the {@link TaskStorage} to be updated after task removal
     */
    public DeleteCommand(ToDoList toDoList, TaskStorage taskStorage) {
        this.toDoList = toDoList;
        this.taskStorage = taskStorage;
    }

    /**
     * Executes the command to delete a task based on the provided input.
     * The input is expected to be a {@code String} representing a numeric index (1-based)
     * of the task to delete. If the input is not a valid number, exceeds the list size,
     * or is less than 1, an error message is printed, and no task is removed. Otherwise,
     * the task at the specified index is removed from the {@link ToDoList}, the
     * {@link TaskStorage} is updated, and a confirmation message is displayed along with
     * the updated task count.
     *
     * @param o the input object, expected to be a {@code String} containing the 1-based index
     *          of the task to delete
     */
    @Override
    public String execute(Object o) {
        if (o instanceof String) {
            int input;
            try {
                input = Integer.parseInt((String) o);
            } catch (NumberFormatException e) {
                return """
                        Pray, employ the noble digits as thy guiding input!
                        (Please use numerics as input only)
                        """;
            }
            if (input > toDoList.size()) {
                return """
                        Thy request doth stray beyond the hallowed limits.
                        (The number you have input is greater than the number of tasks you currently have)
                        """;
            }
            if (input < 1) {
                return """
                        Doth this be a jest, good sir?
                        (0 and negative numbers are not accepted as input)
                        """;
            }

            String temporary = "Heed this decree! This noble quest hath been cast aside.\n\n"
                    + toDoList.get(input - 1).toString();
            toDoList.remove(input - 1);
            taskStorage.update(toDoList);
            return temporary + "\n\nLo! Thou art now bestowed with "
                    + toDoList.size()
                    + " noble quests upon thy parchment of duties.\n"
                    + "(You now have " + toDoList.size() + " tasks in the to do list)\n";
        }
        return "Something went wrong :("; // should never come to this as parser ensures string input
    }
}
