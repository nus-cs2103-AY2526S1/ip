package talkgpt.parser;

import java.util.Objects;
import java.util.Set;

import talkgpt.TalkgptException;
import talkgpt.command.AddCommand;
import talkgpt.command.Command;
import talkgpt.command.DeleteCommand;
import talkgpt.command.FindCommand;
import talkgpt.command.GoodbyeCommand;
import talkgpt.command.ListCommand;
import talkgpt.command.MarkCommand;
import talkgpt.command.TagCommand;
import talkgpt.command.UnmarkCommand;
import talkgpt.task.Deadline;
import talkgpt.task.Event;
import talkgpt.task.ToDo;

/**
 * Parses user input and returns the corresponding Command object for execution.
 * Handles validation and extraction of command arguments for the TalkGPT application.
 */
public class Parser {
    public static final Set<String> VALIDCOMMANDS = Set.of("mark", "unmark", "todo", "deadline",
            "event", "list", "delete", "bye", "find", "tag");

    /**
     * Constructs a Parser instance.
     */
    public Parser() {

    }

    /**
     * Parses the user input string and returns the appropriate Command object.
     * Throws TalkGPTException if the input is invalid or required arguments are missing.
     *
     * @param input The user input string.
     * @return The corresponding Command object.
     * @throws TalkgptException If the input is invalid or incomplete.
     */
    public Command parse(String input) throws TalkgptException {
        if (!VALIDCOMMANDS.contains(input) && !input.contains(" ")) {
            throw new TalkgptException("Sorry, I don't recognize that command!");
        }

        if (Objects.equals(input, "list")) {
            return new ListCommand();
        }

        if (Objects.equals(input, "bye")) {
            return new GoodbyeCommand();
        }

        if (input == null) {
            throw new TalkgptException("The input cannot be empty.");
        }

        String[] parts = input.split(" ", 2);

        if (parts.length == 1) {
            throw new TalkgptException("The input cannot be a single word");
        }

        String command = parts[0];
        assert parts.length > 1 : "The input cannot be a single word";
        String message = parts[1];

        try {
            switch (command) {
            case "tag" -> {
                return new TagCommand(message);
            }
            case "mark" -> {
                return new MarkCommand(message);
            }
            case "unmark" -> {
                return new UnmarkCommand(message);
            }
            case "todo" -> {
                String[] components = message.split(" /tag ", 2);
                String task = components[0];
                String tag = components[1];
                return new AddCommand(new ToDo(task, tag));
            }
            case "find" -> {
                return new FindCommand(message);
            }
            case "deadline" -> {
                //deadline return book /by 3/12/2024 1800 /tag school
                String[] components = message.split(" /by ", 2);
                String task = components[0];
                String stringDate = components[1].split(" /tag ", 2)[0];
                String tag = components[1].split(" /tag ", 2)[1];

                return new AddCommand(new Deadline(task, stringDate, tag));
            }
            case "event" -> {
                //event project meeting /from 3/12/2024 1400 /to 3/12/2024 1600 /tag school
                String[] components = message.split(" /from ", 2);
                String task = components[0];

                //3/12/2024 1400 /to 3/12/2024 1600 /tag school
                String[] dates = components[1].split(" /tag ", 2)[0].split(" /to ", 2);

                String from = dates[0];
                String to = dates[1];

                String tag = components[1].split(" /tag ", 2)[1];

                return new AddCommand(new Event(task, from, to, tag));
            }
            case "delete" -> {
                return new DeleteCommand(message);
            }
            default -> {
                throw new TalkgptException("Invalid Command.");
            }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new TalkgptException("The description of a " + command + " is incomplete.");
        }
    }
}
