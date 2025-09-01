package leo.functions.commands;

import java.io.IOException;
import java.time.DateTimeException;

import leo.display.Messages;
import leo.functions.task.Deadline;
import leo.functions.task.Event;
import leo.functions.task.TaskList;
import leo.functions.task.ToDo;

/**
 * Represents the function that is called when the user wants to add a task.
 */
public class AddTaskCommand implements Command {
    /**
     * Adds a ToDo task object to the list.
     *
     * @param userInput String containing the user input.
     * @param listItems List of tasks.
     * @throws ArrayIndexOutOfBoundsException If the user input is in the wrong format.
     * @throws IOException If the file storing data cannot be found.
     */
    //Solution adapted from https://www.perplexity.ai/search/catch-a-function-but-handle-it-prjjRGnZRsu8igx_P1RE7A
    public static void todo(String userInput, TaskList listItems) throws ArrayIndexOutOfBoundsException, IOException {
        String[] userInputList = userInput.split("todo");
        if (userInputList.length < 2) {
            throw new ArrayIndexOutOfBoundsException(
                    "The description of the task is missing!"
                    + "\n\tMake sure it is in this format:"
                    + "\n\t\ttodo <task description>"
            );
        }
        String taskDesc = userInputList[1].trim();
        ToDo toDoTask = new ToDo(taskDesc);

        listItems.addTask(toDoTask);
        Messages.addTask();

        System.out.println("\t" + toDoTask);
        Messages.taskCount(listItems.getSize());
    }

    /**
     * Adds a Deadline task object to the list.
     *
     * @param userInput String containing the user input.
     * @param listItems List of tasks.
     * @throws ArrayIndexOutOfBoundsException If the user input is in the wrong format.
     * @throws IOException If the file storing data cannot be found.
     * @throws DateTimeException If the date or time is given in the wrong format.
     */
    //Solution adapted from https://www.perplexity.ai/search/catch-a-function-but-handle-it-prjjRGnZRsu8igx_P1RE7A
    public static void deadline(String userInput, TaskList listItems)
            throws ArrayIndexOutOfBoundsException, IOException, DateTimeException {
        String[] userInputList = userInput.split("deadline | /by");
        if (userInputList.length < 3) {
            throw new ArrayIndexOutOfBoundsException(
                    "The description or deadline of the task is missing!"
                    + "\n\tMake sure it is in this format:"
                    + "\n\t\tdeadline <task description> /by <dd/MM/yyyy HHmm>"
            );
        }
        String taskDesc = userInputList[1].trim();
        String deadline = userInputList[2].trim();
        Deadline deadlineTask = new Deadline(taskDesc, deadline);

        listItems.addTask(deadlineTask);
        Messages.addTask();

        System.out.println("\t" + deadlineTask);
        Messages.taskCount(listItems.getSize());
    }

    /**
     * Adds an Event task object to the list.
     *
     * @param userInput String containing the user input.
     * @param listItems List of tasks.
     * @throws ArrayIndexOutOfBoundsException If the user input is in the wrong format.
     * @throws IOException If the file storing data cannot be found.
     * @throws DateTimeException If the date or time is given in the wrong format.
     */
    //Solution adapted from https://www.perplexity.ai/search/catch-a-function-but-handle-it-prjjRGnZRsu8igx_P1RE7A
    public static void event(String userInput, TaskList listItems)
            throws ArrayIndexOutOfBoundsException, IOException, DateTimeException {
        String[] userInputList = userInput.split("event | /from | /to");
        if (userInputList.length < 4) {
            throw new ArrayIndexOutOfBoundsException(
                    "The description or timing of the task is missing!"
                    + "\n\tMake sure it is in this format:"
                    + "\n\t\tevent <task description> /from <dd/MM/yyyy HHmm> /to <dd/MM/yyyy HHmm>"
            );
        }
        String taskDesc = userInputList[1].trim();
        String startDate = userInputList[2].trim();
        String endDate = userInputList[3].trim();
        Event eventTask = new Event(taskDesc, startDate, endDate);

        listItems.addTask(eventTask);
        Messages.addTask();

        System.out.println("\t" + eventTask);
        Messages.taskCount(listItems.getSize());
    }
}
