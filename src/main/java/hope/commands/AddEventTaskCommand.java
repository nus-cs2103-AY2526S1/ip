package hope.commands;

import hope.storage.TaskStorage;
import hope.storage.ToDoList;
import hope.tasks.EventTask;

/**
 * A command that adds an event task to a to-do list and persists it to storage.
 * This class implements the {@link Command} interface and processes user input to create
 * an {@link EventTask} with a description, start time, and end time. It validates the input,
 * adds the task to the provided {@link ToDoList}, and appends it to the {@link TaskStorage}.
 * If the input is invalid, appropriate error messages are displayed.
 */
public class AddEventTaskCommand implements Command {

    /** The to-do list to which the event task will be added. */
    private ToDoList toDoList;

    /** The storage system for persisting the event task. */
    private TaskStorage taskStorage;

    /**
     * Constructs an {@code AddEventTaskCommand} with the specified to-do list and task storage.
     *
     * @param toDoList   the {@link ToDoList} to which the event task will be added
     * @param taskStorage the {@link TaskStorage} where the event task will be persisted
     */
    public AddEventTaskCommand(ToDoList toDoList, TaskStorage taskStorage) {
        this.toDoList = toDoList;
        this.taskStorage = taskStorage;
    }

    /**
     * Executes the command to add an event task based on the provided input.
     * The input string is expected to contain a task description, start time, and end time,
     * separated by "/from" and "/to" (e.g., "description /from start /to end").
     * If the input is invalid (e.g., missing description, start, or end time), an error message
     * is printed, and no task is added. Otherwise, an {@link EventTask} is created, added to
     * the to-do list, persisted to storage, and a confirmation message is displayed along with
     * the updated task count.
     *
     * @param o the input object, expected to be a {@code String} containing the task description,
     *          start time, and end time in the format "description /from start /to end"
     */
    @Override
    public String execute(Object o) {
        assert(o instanceof String);
        String input = (String) o;
        if (input.equals("event")) {
            return """
                    Thou hast overlooked the noble task of bestowing a worthy description upon this \
                    endeavor!
                    (Looks like you forgot to input description, from and to! Try again)
                    """;
        }
        String[] info = input.split("/from");
        if (info.length != 2 || info[0].isEmpty()) {
            return """
                    Alas, fair traveler, thy input bears a flaw; kindly make haste and attempt anew!
                    (Incorrect input, please try again)
                    """;
        }
        String[] toAndFrom = info[1].split("/to");
        if (toAndFrom.length != 2) {
            return """
                    Alas, fair traveler, thy input bears a flaw; kindly make haste and attempt anew!
                    (Incorrect input, please try again)
                    """;
        }
        EventTask temp = new EventTask(info[0].trim(), toAndFrom[0].trim(), toAndFrom[1].trim());
        toDoList.add(temp);
        taskStorage.append(temp);
        return "Behold, this quest hath been entrusted!:\n\n"
                + temp.toString()
                + "\n\nLo! Thou art now bestowed with "
                + toDoList.size()
                + " noble quests upon thy parchment of duties.\n"
                + "(You now have " + toDoList.size() + " tasks in the to do list)\n";
    }
}
