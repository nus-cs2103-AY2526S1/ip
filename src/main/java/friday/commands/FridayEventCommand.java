package friday.commands;

import friday.storage.FridayStorage;
import friday.tasklist.FridayTaskList;
import friday.exceptions.UnknownCommandFridayException;
import friday.ui.FridayUi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class FridayEventCommand extends FridayCommand {
    public String argument;

    public FridayEventCommand(String argument) {
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
            throw new UnknownCommandFridayException("Description of an Event friday.tasks.Task cannot be empty!");
        }
        return arg;
    }

    public String execute(FridayTaskList taskList, FridayUi ui, FridayStorage storage)
            throws UnknownCommandFridayException {
        String processedArgument = process(this.argument);

        String[] parts = processedArgument.split(" /from ", 2);

        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new UnknownCommandFridayException("Start time of a Event Task cannot be empty!");
        }
        String description = parts[0];

        String[] timeParts = parts[1].split(" /to ", 2);

        //catch if there is no end time
        if (timeParts.length < 2 || timeParts[1].trim().isEmpty()) {
            throw new UnknownCommandFridayException("End time of a Event Task cannot be empty! " +
                    "Please enter in the format: event <description> /from <start time> /to <end time> #<tag>");
        }

        String from = timeParts[0];     // from

        DateTimeFormatter fromFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        try {
            LocalDate.parse(from, fromFormatter);
        } catch (DateTimeParseException e) {
            throw new UnknownCommandFridayException(
                    "Deadline must be in DD/MM/YYYY HHmm format (e.g., 01/01/2001 1230)."
            );
        }
        String toAndTag = timeParts[1];       // to and tag
        String[] tagParts = toAndTag.split(" #", 2);
        String to = tagParts[0];

        DateTimeFormatter toFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        try {
            LocalDate.parse(to, toFormatter);
        } catch (DateTimeParseException e) {
            throw new UnknownCommandFridayException(
                    "Deadline must be in DD/MM/YYYY HHmm format (e.g., 01/01/2001 1230)."
            );
        }

        String tag = "";

        if(tagParts.length < 2 || parts[1].trim().isEmpty()) {
            tag = "";
        } else {
            tag = "#" + tagParts[1];
        }



        taskList.addEventTask(description, from, to, tag);
        FridayStorage.writeListToFile(taskList.getList());
        return ui.showTaskHasBeenAdded(taskList.getTask(taskList.getNumberOfTasks()),taskList);
    }
}
