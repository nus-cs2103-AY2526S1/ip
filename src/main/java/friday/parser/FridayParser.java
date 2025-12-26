package friday.parser;

import friday.commands.FridayTodoCommand;
import friday.commands.FridayUnknownCommand;
import friday.commands.FridayUnmarkAsDoneCommand;
import friday.commands.*;

/**
 *
 */

public class FridayParser {
    /**
     * Parses the String of command the user input and return a
     * FridayCommand object according to the user input.
     * @param fullCommand is the command input from user.
     * @return FridayCommand
     */

    // Command word constants
    private static final String CMD_LIST = "list";
    private static final String CMD_TODO = "todo";
    private static final String CMD_DEADLINE = "deadline";
    private static final String CMD_EVENT = "event";
    private static final String CMD_DELETE = "delete";
    private static final String CMD_MARK = "mark";
    private static final String CMD_UNMARK = "unmark";
    private static final String CMD_FIND = "find";
    private static final String CMD_BYE = "bye";


    public static FridayCommand parse(String fullCommand) {
        String[] parts = fullCommand.trim().split(" ", 2);
        String commandWord = parts[0];
        String args = parts.length > 1 ? parts[1].trim() : " ";

        return switch (commandWord) {
            case CMD_LIST -> new FridayGetListCommand();
            case CMD_TODO -> new FridayTodoCommand(args);
            case CMD_DEADLINE -> new FridayDeadlineCommand(args);
            case CMD_EVENT -> new FridayEventCommand(args);
            case CMD_DELETE -> new FridayDeleteTaskCommand(args);
            case CMD_MARK -> new FridayMarkAsDoneCommand(args);
            case CMD_UNMARK -> new FridayUnmarkAsDoneCommand(args);
            case CMD_FIND -> new FridayFindCommand(args);
            case CMD_BYE -> new FridayExitCommand();
            default -> new FridayUnknownCommand();
        };
    }
}
