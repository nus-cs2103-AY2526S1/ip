package chiikawa;

import chiikawa.command.AddDeadlineCommand;
import chiikawa.command.AddEventCommand;
import chiikawa.command.AddToDoCommand;
import chiikawa.command.Command;
import chiikawa.command.DeleteCommand;
import chiikawa.command.ExitCommand;
import chiikawa.command.FilterCommand;
import chiikawa.command.FindCommand;
import chiikawa.command.InvalidCommand;
import chiikawa.command.ListCommand;
import chiikawa.command.MarkCommand;
import chiikawa.command.UnmarkCommand;
import chiikawa.command.UpdatePriorityCommand;

/**
 * Parser makes sense of the user's commands. It will only interpret what the user wants to do and
 * carry out the corresponding command. The only check should be for invalid commands, all other checks
 * should be carried out in the command class itself.
 */
public class Parser {
    enum Commands {
        TODO, DEADLINE, EVENT, LIST, MARK, UNMARK, DELETE, BYE, FIND, PRIORITY, FILTER
    }

    /**
     * Takes in a String that represents the command users want to carry out
     * and then returns the corresponding Command class.
     * If the command given by the user is invalid, return the InvalidCommand
     * which represents an invalid command.
     *
     * @param fullCommand The command the user entered.
     * @return The corresponding command to what the user entered.
     */
    public static Command parse(String fullCommand) {
        fullCommand = fullCommand.strip();
        Commands command = null;
        String[] fullCommandArray = parseTaskInfo(fullCommand, " ", 2);

        try {
            command = Commands.valueOf(fullCommandArray[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            return new InvalidCommand();
        }

        String taskInfo = "";
        if (fullCommandArray.length == 2) {
            taskInfo = fullCommandArray[1];
        }

        // Assert that command cannot be null before running the switch statements.
        assert command != null : "Command must not be null! Something went wrong!";
        switch (command) {
        case BYE:
            return new ExitCommand();
        case LIST:
            return new ListCommand();
        case FIND:
            return new FindCommand(taskInfo);
        case MARK:
            return new MarkCommand(taskInfo);
        case UNMARK:
            return new UnmarkCommand(taskInfo);
        case DELETE:
            return new DeleteCommand(taskInfo);
        case PRIORITY:
            return new UpdatePriorityCommand(taskInfo);
        case FILTER:
            return new FilterCommand(taskInfo);
        case TODO:
            return new AddToDoCommand(taskInfo);
        case DEADLINE:
            return new AddDeadlineCommand(taskInfo);
        case EVENT:
            return new AddEventCommand(taskInfo);
        default:
            return new InvalidCommand();
        }
    }

    /**
     * Takes in a String taskInfo, strips it of whitespaces, and then splits it
     * according to the regex and limit specified by the user. Takes in the String
     * and converts it to an array of Strings, withholding the information provided by the user.
     *
     * @param taskInfo Text that the user entered after the command keyword.
     * @param regex The String that taskInfo will be split by.
     * @param limit The number of elements in the String array.
     * @return String array consisting of task information stripped and split.
     */
    public static String[] parseTaskInfo(String taskInfo, String regex, int limit) {
        String[] taskInfoArray = taskInfo.strip().split(regex, limit);

        for (int i = 0; i < taskInfoArray.length; i++) {
            taskInfoArray[i] = taskInfoArray[i].strip();
        }

        return taskInfoArray;
    }
}
