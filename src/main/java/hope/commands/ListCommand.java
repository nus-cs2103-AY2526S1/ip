package hope.commands;

import hope.storage.ToDoList;

/**
 * A command that displays all tasks in a to-do list.
 * This class implements the {@link Command} interface and is responsible for printing
 * the contents of the provided {@link ToDoList}. If the list is empty, an appropriate
 * message is displayed; otherwise, all tasks are listed.
 */
public class ListCommand implements Command {

    /** The to-do list containing the tasks to be displayed. */
    private static ToDoList toDoList;

    /**
     * Constructs a {@code ListCommand} with the specified to-do list.
     *
     * @param toDoList the {@link ToDoList} whose tasks will be displayed
     */
    public ListCommand(ToDoList toDoList) {
        this.toDoList = toDoList;
    }

    /**
     * Executes the command to display the tasks in the to-do list.
     * If the {@link ToDoList} is empty, a message indicating no tasks is printed.
     * Otherwise, a header message is displayed followed by the string representation
     * of the to-do list, which includes all tasks.
     *
     * @param o the input object (unused in this implementation)
     */
    @Override
    public String execute(Object o) {
        if (toDoList.size() == 0) {
            return """
                    Thou art unburdened by quests in this moment, dear knight.
                    (There are currently no tasks in your to do list)
                    """;
        }
        return "Hark! Behold the noble quests that grace thy scroll of undertakings!\n"
                + toDoList.toString();
    }
}
