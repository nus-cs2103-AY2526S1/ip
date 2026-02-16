package hope.commands;

import hope.storage.TaskStorage;
import hope.storage.ToDoList;
import hope.tasks.ToDoTask;

/**
 * A command that adds a to-do task to a to-do list and persists it to storage.
 * This class implements the {@link Command} interface and processes user input to create
 * a {@link ToDoTask} with a description. It validates the input, adds the task to the
 * provided {@link ToDoList}, and appends it to the {@link TaskStorage}. If the input is
 * invalid, appropriate error messages are displayed.
 */
public class AddToDoTaskCommand implements Command {

    /** The to-do list to which the to-do task will be added. */
    private final ToDoList toDoList;

    /** The storage system for persisting the to-do task. */
    private final TaskStorage taskStorage;

    /**
     * Constructs an {@code AddToDoTaskCommand} with the specified to-do list and task storage.
     *
     * @param toDoList    the {@link ToDoList} to which the to-do task will be added
     * @param taskStorage the {@link TaskStorage} where the to-do task will be persisted
     */
    public AddToDoTaskCommand(ToDoList toDoList, TaskStorage taskStorage) {
        this.toDoList = toDoList;
        this.taskStorage = taskStorage;
    }

    /**
     * Executes the command to add a to-do task based on the provided input.
     * The input string is expected to contain a task description. If the input is empty
     * or equals "todo", an error message is printed, and no task is added. Otherwise, a
     * {@link ToDoTask} is created, added to the to-do list, persisted to storage, and a
     * confirmation message is displayed along with the updated task count.
     *
     * @param o the input object, expected to be a {@code String} containing the task description
     */
    @Override
    public String execute(Object o) {
        assert(o instanceof String);
        String input = (String) o;
        if (input.isEmpty() || input.equals("todo")) {
            return """
                    Thou hast overlooked the noble task of bestowing a worthy description upon this \
                    endeavor!
                    (Empty input detected, please try again)
                    """;
        }
        ToDoTask temp = new ToDoTask(input);
        toDoList.add(temp);
        taskStorage.append(temp);
        return "Behold, this quest hath been entrusted!\n\n"
                + temp.toString()
                + "\n\nLo! Thou art now bestowed with "
                + toDoList.size()
                + " noble quests upon thy parchment of duties.\n"
                + "(You now have " + toDoList.size() + " tasks in the list)\n";
    }
}

