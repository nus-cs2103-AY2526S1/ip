package bambam.command;

import java.io.IOException;

import bambam.BambamException;
import bambam.Messages;
import bambam.TaskList;
import bambam.TaskStorage;
import bambam.task.Deadlines;
import bambam.task.Task;

/**
 * Represents the deadline command which is a type of Command.
 */
public class DeadlineCommand extends Command {
    private Task newDeadline;
    private int taskListSize;
    private final String taskDescription;

    public DeadlineCommand(String taskDescription) {
        super(false);
        this.taskDescription = taskDescription;
    }

    @Override
    public void execute(TaskStorage storage, Messages messages, TaskList taskList)
            throws BambamException, IOException {

        assert (taskDescription != null && !taskDescription.isEmpty()) :
                "Task Description cannot be null or empty";

        String[] deadlineDetails = taskDescription.split(" /by ", 2);

        if (deadlineDetails.length < 2) {
            throw new BambamException("Oopsies, time details of deadline can't be empty");
        }

        newDeadline = new Deadlines(deadlineDetails[0], deadlineDetails[1]);
        taskList.addTaskToList(newDeadline);

        taskListSize = taskList.getTaskSize();

        storage.saveTasks(taskList);
    }

    @Override
    public String getString() {
        return "Got it! Your task has been added 🌸\n" +
                "    " + newDeadline.printTaskString() + "\n" +
                "Now you have " + taskListSize + " tasks in the list. \uD83D\uDC96\n";
    }
}
