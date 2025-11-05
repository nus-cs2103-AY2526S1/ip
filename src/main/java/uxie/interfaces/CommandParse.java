package uxie.interfaces;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import uxie.commands.Command;
import uxie.commands.DeadlineCommand;
import uxie.commands.DeleteCommand;
import uxie.commands.EventCommand;
import uxie.commands.ExitCommand;
import uxie.commands.FindCommand;
import uxie.commands.ListCommand;
import uxie.commands.MarkCommand;
import uxie.commands.TagCommand;
import uxie.commands.TodoCommand;
import uxie.commands.UnmarkCommand;
import uxie.exceptions.UxieSyntaxException;

/**
 * Parses commands from Ui.
 *
 * @author junyan-k
 */
public class CommandParse {

    /**
     * Parses input String and returns corresponding Command.
     *
     * @param userCommand input String from user
     * @return resulting Command object of specific type
     * @throws UxieSyntaxException when userCommand is in incorrect format (e.g. missing arguments, unknown command)
     * @see uxie.commands
     */
    public static Command parse(String userCommand) throws UxieSyntaxException {
        List<String> splitCommand = new ArrayList<>(List.of(userCommand.split(" ")));

        // verify splitCommand is not empty
        assert !splitCommand.isEmpty() : "CommandParse#parse: splitCommand is empty";

        switch (splitCommand.get(0)) {
        case "list": // output contents of list
            return new ListCommand();

        case "mark": // mark task <n> as completed
            return parseMarkCommand(splitCommand);

        case "unmark": // mark task <n> as incomplete
            return parseUnmarkCommand(splitCommand);

        case "delete": // delete task from tasks
            return parseDeleteCommand(splitCommand);

        case "find": // find tasks containing string
            return parseFindCommand(splitCommand);

        case "tag":
            return parseTagCommand(splitCommand);

        case "todo": // add task as a todos
            return parseTodoCommand(splitCommand);

        case "deadline": // add task as a deadline
            return parseDeadlineCommand(splitCommand);

        case "event": // add task as an event
            return parseEventCommand(splitCommand);

        case "goodbye":
        case "bye": // exits program
            return new ExitCommand();

        default: // unrecognized command
            throw new UxieSyntaxException("Consider checking your spelling.");
        }
    }


    /**
     * Parses arguments in list into a MarkCommand.
     *
     * @param splitCommand separated arguments in command.
     * @return generated MarkCommand
     * @throws UxieSyntaxException If format is incorrect.
     */
    private static MarkCommand parseMarkCommand(List<String> splitCommand) throws UxieSyntaxException {
        try {
            return new MarkCommand(Integer.parseInt(splitCommand.get(1)) - 1);
        } catch (NumberFormatException e) {
            throw new UxieSyntaxException("That index doesn't seem right.");
        }
    }

    /**
     * Parses arguments in list into an UnmarkCommand.
     *
     * @param splitCommand separated arguments in command.
     * @return generated UnmarkCommand
     * @throws UxieSyntaxException If format is incorrect.
     */
    private static UnmarkCommand parseUnmarkCommand(List<String> splitCommand) throws UxieSyntaxException {
        try {
            return new UnmarkCommand(Integer.parseInt(splitCommand.get(1)) - 1);
        } catch (NumberFormatException e) {
            throw new UxieSyntaxException("That index doesn't seem right.");
        }
    }

    /**
     * Parses arguments in list into a DeleteCommand.
     *
     * @param splitCommand separated arguments in command.
     * @return generated DeleteCommand
     * @throws UxieSyntaxException If format is incorrect.
     */
    private static DeleteCommand parseDeleteCommand(List<String> splitCommand) throws UxieSyntaxException {
        try {
            return new DeleteCommand(Integer.parseInt(splitCommand.get(1)) - 1);
        } catch (NumberFormatException e) {
            throw new UxieSyntaxException("That index doesn't seem right.");
        }
    }

    /**
     * Parses arguments in list into a FindCommand.
     *
     * @param splitCommand separated arguments in command.
     * @return generated FindCommand
     * @throws UxieSyntaxException If format is incorrect.
     */
    private static FindCommand parseFindCommand(List<String> splitCommand) throws UxieSyntaxException {
        splitCommand.remove(0);
        String searchString = String.join(" ", splitCommand);
        if (searchString.isBlank()) {
            // search is empty
            throw new UxieSyntaxException("Your search string can't be empty.");
        }
        return new FindCommand(searchString);
    }

    /**
     * Parses arguments in list into a TagCommand.
     *
     * @param splitCommand separated arguments in command.
     * @return generated TagCommand.
     * @throws UxieSyntaxException if format is incorrect.
     */
    private static TagCommand parseTagCommand(List<String> splitCommand) throws UxieSyntaxException {
        if (splitCommand.size() <= 1) {
            // missing taskIndex
            throw new UxieSyntaxException("You're missing your task index.");
        }
        try {
            int taskIndex = Integer.parseInt(splitCommand.get(1));
            String tag = String.join(" ", splitCommand.subList(2, splitCommand.size()));
            return new TagCommand(taskIndex - 1, tag);
        } catch (NumberFormatException e) {
            throw new UxieSyntaxException("That index doesn't seem right.");
        }
    }

    /**
     * Parses arguments in list into a TodoCommand.
     *
     * @param splitCommand separated arguments in command.
     * @return generated TodoCommand
     * @throws UxieSyntaxException If format is incorrect.
     */
    private static TodoCommand parseTodoCommand(List<String> splitCommand) throws UxieSyntaxException {
        splitCommand.remove(0); // remove command word
        String todoDesc = String.join(" ", splitCommand);
        if (todoDesc.isBlank()) {
            // desc is empty
            throw new UxieSyntaxException("Your task description can't be empty.");
        }
        return new TodoCommand(todoDesc);
    }

    /**
     * Parses arguments in list into a DeadlineCommand.
     *
     * @param splitCommand separated arguments in command.
     * @return generated DeadlineCommand
     * @throws UxieSyntaxException If format is incorrect.
     */
    private static DeadlineCommand parseDeadlineCommand(List<String> splitCommand) throws UxieSyntaxException {
        splitCommand.remove(0); // remove command word
        if (splitCommand.isEmpty() || splitCommand.get(0).matches("\s*|/.*")) {
            // desc is empty
            throw new UxieSyntaxException("Your task description can't be empty.");
        }
        int byIndex = -1;
        for (int i = 1; i < splitCommand.size(); i++) { // skip first as task desc cannot be empty
            // search for "/by"
            if (splitCommand.get(i).equals("/by")) {
                byIndex = i;
                break;
            }
        }
        if (byIndex == -1) {
            // "/by" not found
            throw new UxieSyntaxException("A deadline needs a... deadline. ('/by')");
        }

        String dlDesc = String.join(" ", splitCommand.subList(0, byIndex));
        LocalDateTime dlBy = DateTimeParse.parseInput(
                String.join(" ", splitCommand.subList(byIndex + 1, splitCommand.size())));
        return new DeadlineCommand(dlDesc, dlBy);
    }

    /**
     * Parses arguments in list into an EventCommand.
     *
     * @param splitCommand separated arguments in command.
     * @return generated EventCommand
     * @throws UxieSyntaxException If format is incorrect.
     */
    private static EventCommand parseEventCommand(List<String> splitCommand) throws UxieSyntaxException {
        splitCommand.remove(0); // remove command word
        if (splitCommand.isEmpty() || splitCommand.get(0).matches("\s*|/.*")) {
            // desc is empty
            throw new UxieSyntaxException("Your task description can't be empty.");
        }
        int fromIndex = -1;
        int toIndex = -1;
        for (int i = 1; i < splitCommand.size(); i++) { // skip first as task desc cannot be empty
            // search for "/from"
            if (splitCommand.get(i).equals("/from")) {
                fromIndex = i;
            }
            // search for "/to"
            if (splitCommand.get(i).equals("/to")) {
                if (fromIndex == -1) {
                    // "/to" before "/from"
                    throw new UxieSyntaxException("/from should be before /to.");
                }
                toIndex = i;
                break;
            }
        }

        if (fromIndex == -1 || toIndex == -1) {
            // argument missing
            throw new UxieSyntaxException("You're missing an argument there. ('/from', '/to')");
        }

        String eDesc = String.join(" ", splitCommand.subList(0, fromIndex));
        LocalDateTime eFrom = DateTimeParse.parseInput(
                String.join(" ", splitCommand.subList(fromIndex + 1, toIndex)));
        LocalDateTime eTo = DateTimeParse.parseInput(
                String.join(" ", splitCommand.subList(toIndex + 1, splitCommand.size())));
        return new EventCommand(eDesc, eFrom, eTo);
    }

    /**
     * Parses arguments in list into an ExitCommand.
     *
     * @param splitCommand separated arguments in command.
     * @return generated ExitCommand
     * @throws UxieSyntaxException If format is incorrect.
     */
    private static ExitCommand parseExitCommand(List<String> splitCommand) throws UxieSyntaxException {
        return new ExitCommand();
    }

}
