package command;

import exception.TodoException;
import task.TaskList;
import ui.Ui;
import storage.Storage;

import task.ToDo;

import java.io.IOException;

/**
 * Represents the command to add a {@link ToDo} task to the task list.
 */
public class TodoCommand extends Command {
    /**
     * Constructs a {@link TodoCommand} with the relevant user input.
     *
     * @param input the raw user input that triggered the command
     */
    public TodoCommand(String input) {
        super(input);
    }

    /**
     * Executes the todo command: adds a {@link ToDo} to the task list,
     * displays a success/failure message and updates the save file accordingly.
     *
     * @param tasklist the {@link TaskList} where tasks are stored
     * @param ui       the {@link Ui} that the user interacts with
     * @param storage  the {@link Storage} that retrieves and updates the save file
     * @throws TodoException if there is an invalid input
     * @throws IOException if there is an error with updating the save file
     */
    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) throws TodoException, IOException {
        try {
            String desc = input.substring(5);
            if (desc.isEmpty()) throw new Exception();

            ToDo td = new ToDo(desc);
            tasklist.add(td);

            ui.chatbotPrint("Got it. I've added this task:\n      " + td);
            ui.chatbotPrint("Now you have " + tasklist.size() + (tasklist.size() == 1 ? " task" : " tasks") + " in the list.");
        } catch (Exception e) {
            throw new TodoException("To create a new to-do item, the command is: todo [name of to-do]");
        }

        storage.saveToFile(tasklist);
    }

    /**
     * Returns the help message associated with the todo command
     *
     * @return the corresponding help message
     */
    @Override
    public String getHelpMessage() {
        return """
                todo:
                adds a todo task to the task list.
                format: todo [title]""";
    }
}
