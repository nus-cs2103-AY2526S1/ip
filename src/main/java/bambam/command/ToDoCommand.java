package bambam.command;

import java.io.IOException;

import bambam.BambamException;
import bambam.Messages;
import bambam.TaskList;
import bambam.TaskStorage;
import bambam.task.Task;
import bambam.task.ToDos;

/**
 * Represents the todo command which is a type of Command.
 */
public class ToDoCommand extends Command {
    private Task newToDo;
    private int taskListSize;
    private final String taskDescription;

    public ToDoCommand(String taskDescription) {
        super(false);
        this.taskDescription = taskDescription;
    }

    @Override
    public void execute(TaskStorage storage, Messages messages, TaskList taskList)
            throws BambamException, IOException {

        assert (taskDescription != null && !taskDescription.isEmpty()) :
                "Task Description cannot be null or empty";

        newToDo = new ToDos(taskDescription);
        taskList.addTaskToList(newToDo);

        taskListSize = taskList.getTaskSize();

        storage.saveTasks(taskList);
    }

    @Override
    public String getString() {
        return "Got it! Your task has been added 🌸\n" +
                "    " + newToDo.printTaskString() + "\n" +
                "Now you have " + taskListSize + " tasks in the list. \uD83D\uDC96\n";
    }
}
