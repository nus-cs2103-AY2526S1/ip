package bubbles;

import java.io.IOException;

/**
 * The {@code Parser} class is responsible for interpreting the user's input
 * and returning the required {@code Command} to be executed.
 */
public class Parser {
    /**
     * Parses the given user input and returns a corresponding {@code Command}.
     * The method identifies the correct command by comparing the input string and
     * instantiates an appropriate subclass of {@code Command}.
     *
     * @param command the user input
     * @return a {@code Command} object
     * @throws BubblesException if the input does not match any known command
     */

    public static Command parse(String command) throws BubblesException {
        assert command != null : "The command must not be null!";

        command = command.trim().toLowerCase();
        if (command.startsWith("todo")) {
            assert command.length() > 4 : "Todo command should have a description!";
            return new TodoCommand(command);
        } else if (command.startsWith("deadline")) {
            assert command.contains("/by") : "Deadline command must contain '/by'!";
            return new DeadlineCommand(command);
        } else if (command.startsWith("event")) {
            assert command.contains("/from") && command.contains("/to") : "Event command must contain '/from' and '/too'!";
            return new EventCommand(command);
        } else if (command.startsWith("mark")) {
            assert command.length() > 4: "Sample error";
            return new MarkCommand(command);
        } else if (command.startsWith("unmark")) {
            return new UnmarkCommand(command);
        } else if (command.startsWith("delete")) {
            return new DeleteCommand(command);
        } else if (command.equals("list")) {
            return new ListCommand(command);
        } else if (command.startsWith("find")){
            return new FindCommand(command);
        } else if (command.equals("bye")) {
            return new ByeCommand(command);
        } else if (command.equals("help")) {
            return new HelpCommand(command);
        } else {
            throw new BubblesException("Sawwryy... that one bounced right off my bubblehead! I don't understand it yet T.T");
        }


    }
}
