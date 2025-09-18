package charsiew.parser;

import charsiew.CharSiew;
import charsiew.command.ByeCommand;
import charsiew.command.Command;
import charsiew.command.DeadlineCommand;
import charsiew.command.DeleteCommand;
import charsiew.command.EventCommand;
import charsiew.command.FindCommand;
import charsiew.command.HelpCommand;
import charsiew.command.ListCommand;
import charsiew.command.MarkCommand;
import charsiew.command.TodoCommand;
import charsiew.command.UndoCommand;
import charsiew.command.UnmarkCommand;

/**
 * Parses user input into corresponding Command objects.
 * Acts as the bridge between raw user input and command execution.
 */
public class Parser {

    /**
     * Parses a raw input string and returns the appropriate Command.
     *
     * @param input Raw string entered by the user.
     * @return A Command object corresponding to the input.
     * @throws Exception If the input cannot be parsed into a valid command.
     */
    public static Command parse(String input, CharSiew charSiew) throws Exception {
        assert input != null : "Input string should not be null";
        assert !input.trim().isEmpty() : "Input string should not be empty";
        String[] words = input.trim().split(" ", 2);
        String commandWord = words[0];
        assert commandWord != null && !commandWord.isEmpty()
                : "Command word should not be null or empty";

        switch (commandWord) {
        case "list":
            return new ListCommand();
        case "mark":
            assert words.length > 1 : "Mark command requires a task index";
            return new MarkCommand(Integer.parseInt(words[1]));
        case "unmark":
            assert words.length > 1 : "Unmark command requires a task index";
            return new UnmarkCommand(Integer.parseInt(words[1]));
        case "todo":
            assert words.length > 1 && !words[1].trim().isEmpty()
                    : "Todo command requires a description";
            return new TodoCommand(words[1]);
        case "deadline":
            assert words.length > 1 : "Deadline command requires a description and date";
            return DeadlineCommand.fromInput(words[1]);
        case "event":
            assert words.length > 1 : "Event command requires a description and time range";
            return EventCommand.fromInput(words[1]);
        case "delete":
            assert words.length > 1 : "Delete command requires a task index";
            return new DeleteCommand(Integer.parseInt(words[1]));
        case "bye":
            return new ByeCommand();
        case "find":
            assert words.length > 1 && !words[1].trim().isEmpty()
                    : "Find command requires a search keyword";
            return new FindCommand(words[1].trim());
        case "undo":
            return new UndoCommand(charSiew.getLastCommand());
        case "help":
            return new HelpCommand();
        default:
            throw new Exception("I'm not too sure what that means...");
        }
    }
}
