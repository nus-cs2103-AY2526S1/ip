package friday.commands;

import friday.storage.FridayStorage;
import friday.tasklist.FridayTaskList;
import friday.exceptions.UnknownCommandFridayException;
import friday.ui.FridayUi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents the deadline command that can create a deadline task
 */
public class FridayDeadlineCommand extends FridayCommand {
    public String argument;

    public FridayDeadlineCommand (String argument) {
        this.argument = argument;
    }

    /**
     * Processes the argument String to check if it is valid.
     * @param arg is the command after the keyword.
     * @return a processed command.
     * @throws UnknownCommandFridayException if the argument does not fit the command description.
     */
    public String process(String arg) throws UnknownCommandFridayException {
        if(arg.trim().isEmpty()) {
            throw new UnknownCommandFridayException("Description of a Deadline Task cannot be empty!");
        }
        return arg;
    }

    /**
     * Executes the deadline command and creates a deadline task and updates the list.
     * @param taskList is the tasklist the bot is using.
     * @param ui is the ui that the bot is using.
     * @param storage is the storage the bot is using.
     * @throws UnknownCommandFridayException If the description does not fit the standard.
     */
    @Override
    public String execute(FridayTaskList taskList, FridayUi ui, FridayStorage storage)
            throws UnknownCommandFridayException {
        String input = process(this.argument);

        String[] parts = input.split(" /by ", 2);

        //catch if there is no deadline entered
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new UnknownCommandFridayException("Deadline of a Deadline Task cannot be empty! " +
                    "Please enter in the format: deadline <description> /by <deadline> #<tag>");
        }

        String description = parts[0];  //the description of the task
        String[] tagParts = parts[1].split(" #", 2);
        String deadline = tagParts[0]; //the deadline of the task

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        try {
            LocalDate.parse(deadline, formatter);
        } catch (DateTimeParseException e) {
            throw new UnknownCommandFridayException(
                    "Deadline must be in DD/MM/YYYY HHmm format (e.g., 01/01/2001 1230)."
            );
        }

        String tag = "";

        if(tagParts.length < 2 || parts[1].trim().isEmpty()) {
            tag = "";
        } else {
            tag = "#" + parts[1];
        }

        taskList.addDeadlineTask(description, deadline, tag);
        FridayStorage.writeListToFile(taskList.getList());
        return ui.showTaskHasBeenAdded(taskList.getTask(taskList.getNumberOfTasks()), taskList);
    }
}
