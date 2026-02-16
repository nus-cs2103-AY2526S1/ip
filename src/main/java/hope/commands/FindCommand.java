package hope.commands;

import java.util.ArrayList;

import hope.storage.ToDoList;

/**
 * Represents a command to search for tasks in a ToDoList that contain a specified keyword
 * in their description. This command filters tasks from the provided ToDoList and
 * displays matching tasks or a message if no matches are found.
 */
public class FindCommand implements Command {
    private ToDoList toDoList;

    /**
     * Constructs a FindCommand with the specified ToDoList.
     *
     * @param toDoList the ToDoList to search through for matching tasks
     */
    public FindCommand(ToDoList toDoList) {
        this.toDoList = toDoList;
    }

    /**
     * Executes the find command by searching for tasks in the ToDoList whose descriptions
     * contain the specified input string. Prints matching tasks or a message if no tasks
     * are found.
     *
     * @param o the input object, expected to be a String representing the keyword to search for
     */
    @Override
    public String execute(Object o) {
        ToDoList tempToDoList = new ToDoList(new ArrayList<>());
        if (o instanceof String) {
            String input = (String) o;
            for (int i = 0; i < toDoList.size(); i++) {
                if (toDoList.get(i).getDescription().contains(input)) {
                    tempToDoList.add(toDoList.get(i));
                }
            }
            if (tempToDoList.size() == 0) {
                return "Hark! No quest aligns with thine inquiry, a barren landscape of endeavor doth "
                        + "meet thy gaze.";
            }
            return "Hark, these are the fruits of thy inquiry.\n"
                    + tempToDoList.toString();
        }
        return "Something went wrong :("; // should never come to this as parser ensures string input
    }
}
