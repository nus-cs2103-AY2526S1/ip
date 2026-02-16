package mambo.parser;

import mambo.MamboException;
import mambo.command.ByeCommand;
import mambo.command.Command;
import mambo.command.DeadlineCommand;
import mambo.command.DeleteCommand;
import mambo.command.EventCommand;
import mambo.command.FindCommand;
import mambo.command.HelpCommand;
import mambo.command.ListCommand;
import mambo.command.MarkCommand;
import mambo.command.ToDoCommand;
import mambo.command.UnmarkCommand;
import mambo.task.DeadlineTask;
import mambo.task.EventTask;
import mambo.task.Task;
import mambo.task.ToDoTask;

/**
 * Represents a parser used to handle all operations which involve parsing an input
 *
 * @author kentalim2
 */
public class Parser {

    /**
     * Takes a line of input that is given to the chatbot and looks out for
     * specific commands that the chatbot offers functionality for
     * If user input is not any of the existing commands, it will throw an error
     *
     * @param input The line of input from user
     * @return The command that is given to the chatbot if any
     */
    public static Command parseCommand(String input) throws MamboException {
        if (input.equalsIgnoreCase("bye")) {
            return new ByeCommand("");
        } else if (input.equalsIgnoreCase("list")) {
            return new ListCommand("");
        } else if (input.toLowerCase().startsWith("mark")) {
            return new MarkCommand(input.substring(4).trim());
        } else if (input.toLowerCase().startsWith("unmark")) {
            return new UnmarkCommand(input.substring(6).trim());
        } else if (input.toLowerCase().startsWith("deadline")) {
            return new DeadlineCommand(input.substring(8).trim());
        } else if (input.toLowerCase().startsWith("event")) {
            return new EventCommand(input.substring(5).trim());
        } else if (input.toLowerCase().startsWith("todo")) {
            return new ToDoCommand(input.substring(4).trim());
        } else if (input.toLowerCase().startsWith("delete")) {
            return new DeleteCommand(input.substring(6).trim());
        } else if (input.toLowerCase().startsWith("find")) {
            return new FindCommand(input.substring(4).trim());
        } else if (input.equalsIgnoreCase("help")) {
            return new HelpCommand("");
        } else {
            throw new MamboException("ummm not sure what that's supposed to mean. "
                    + "try one of the commands listed!");
        }
    }

    /**
     * Returns a line of the TaskList data file as its represented Task.
     * Throws MamboException when a task that corresponds to the text input is not found.
     *
     * @param nextLine One line of the data file used to store the current tasklist
     * @return Task represented by one line of data file
     * @throws MamboException When no task is found that corresponds to text input
     */
    public static Task parseLineInFile(String nextLine) throws MamboException {
        try {
            String[] taskComponents = nextLine.split(" / ");

            switch (taskComponents[0]) {
            case "T":
                return new ToDoTask(taskComponents[2], Boolean.parseBoolean(taskComponents[1]));
            case "D":
                return new DeadlineTask(taskComponents[2], Boolean.parseBoolean(taskComponents[1]),
                        taskComponents[3]);
            case "E":
                return new EventTask(taskComponents[2], Boolean.parseBoolean(taskComponents[1]),
                        taskComponents[3], taskComponents[4]);
            default:
                throw new MamboException("task not found");
            }
        } catch (Exception e) {
            throw new MamboException("file is corrupted");
        }
    }
}
