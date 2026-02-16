package hope.commands;

import hope.storage.TaskStorage;
import hope.storage.ToDoList;
import hope.tasks.DeadlineTask;

/**
 *  Adds a new {@link DeadlineTask} to storage.
 *  This class implements the {@link Command} interface and processes user input to create a {@link DeadlineTask}
 *  with a description and a deadline. It validates the input and adds the task to the provided {@link ToDoList} and
 *  updates the {@link TaskStorage}. Invalid inputs are handled within the class.
 */
public class AddDeadlineTaskCommand implements Command {

    private ToDoList toDoList;
    private TaskStorage taskStorage;

    /**
     * Creates a {@link AddDeadlineTaskCommand} with the specified to-do list and task storage
     *
     * @param toDoList the {@link ToDoList} that {@link DeadlineTask} will be added to
     * @param taskStorage the {@link TaskStorage} that will be updated
     */
    public AddDeadlineTaskCommand(ToDoList toDoList, TaskStorage taskStorage) {
        this.toDoList = toDoList;
        this.taskStorage = taskStorage;
    }

    /**
     * Executes the command to add a deadline task based on the provided input and return a string.
     * The input string is expected to contain a task description and a deadline, separated by "/by".
     * If the input is invalid (e.g., missing description or deadline), an error message is printed,
     * and no task is added. Otherwise, a {@link DeadlineTask} is created, added to the to-do list,
     * storage updated, and a confirmation message printed along with the updated task count.
     *
     * @param o the input expected to be a {@code String} containing the task description
     *          and deadline in the format "description /by deadline"
     */
    @Override
    public String execute(Object o) {
        String input = (String) o;
        if (input.isEmpty()) {
            return ("Thou hast overlooked the noble task of bestowing a worthy description upon this "
                    + "endeavor!")
                    + ("(Looks like you forgot to input a description and a deadline! Try again)\n");
        }
        String[] info = input.split("/by");
        if (info.length != 2 || info[0].isEmpty()) {
            return "Verily, thou hast erred in thy response; endeavor once more, brave soul!"
                    + "(Incorrect input, please try again)\n";
        }
        DeadlineTask temp = new DeadlineTask(info[0].trim(), info[1].trim());
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

