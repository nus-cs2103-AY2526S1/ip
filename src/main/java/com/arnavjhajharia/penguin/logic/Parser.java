package com.arnavjhajharia.penguin.logic;

import com.arnavjhajharia.penguin.common.exceptions.MissingArgumentException;
import com.arnavjhajharia.penguin.common.exceptions.UnknownCommandException;
import com.arnavjhajharia.penguin.logic.commands.AddCommand;
import com.arnavjhajharia.penguin.logic.commands.ByeCommand;
import com.arnavjhajharia.penguin.logic.commands.Command;
import com.arnavjhajharia.penguin.logic.commands.DeleteCommand;
import com.arnavjhajharia.penguin.logic.commands.FindCommand;
import com.arnavjhajharia.penguin.logic.commands.ListCommand;
import com.arnavjhajharia.penguin.logic.commands.MarkCommand;
import com.arnavjhajharia.penguin.model.TaskType;

/**
 * The {@code Parser} is responsible for translating raw user input
 * strings into {@link Command} objects that can be executed.
 * <p>
 * It performs:
 * <ul>
 *   <li>Trimming and validation of input (non-null, non-empty).</li>
 *   <li>Splitting into command keyword and argument string.</li>
 *   <li>Dispatching to the correct {@link Command} subclass based on the keyword.</li>
 *   <li>Enforcing required arguments for commands like {@code mark}, {@code unmark}, and {@code delete}.</li>
 * </ul>
 *
 * @since 1.0
 */
public class Parser {

    /**
     * Parses a raw user input string into a {@link Command}.
     * <p>
     * Supported commands:
     * <ul>
     *   <li>{@code list} – Show all tasks.</li>
     *   <li>{@code todo <desc>} – Add a new {@link com.arnavjhajharia.penguin.model.task.Todo}.</li>
     *   <li>{@code deadline <desc> /by <yyyy-MM-dd>} – Add a new {@link com.arnavjhajharia.penguin.model.task.Deadline}.</li>
     *   <li>{@code event <desc> /from <start> /to <end>} – Add a new {@link com.arnavjhajharia.penguin.model.task.Event}.</li>
     *   <li>{@code mark <index>} – Mark a task as done.</li>
     *   <li>{@code unmark <index>} – Mark a task as not done.</li>
     *   <li>{@code delete <index>} – Remove a task.</li>
     *   <li>{@code bye} – Exit the program.</li>
     * </ul>
     *
     * @param input raw user input
     * @return a corresponding {@link Command} object
     * @throws UnknownCommandException   if the command keyword is not recognized or if input is null/empty
     * @throws MissingArgumentException  if a required argument is missing for commands that need one
     */
    public Command parse(String input) throws UnknownCommandException, MissingArgumentException {
        if (input == null) throw new UnknownCommandException("(null)");

        String trimmed = input.trim();

        // Assertion: after trimming, input should not be null (String#trim never returns null).
        assert trimmed != null : "trimmed input unexpectedly null";

        if (trimmed.isEmpty()) throw new UnknownCommandException("(empty)");

        String[] parts = trimmed.split("\\s+", 2);

        // Assertion: split must always produce at least one element.
        assert parts.length >= 1 : "split on input produced empty parts array";

        String cmd = parts[0];
        String arg = (parts.length >= 2) ? parts[1] : "";

        // Assertion: command keyword must never be blank here.
        assert !cmd.isBlank() : "command keyword unexpectedly blank";

        return switch (cmd) {
            case "list"    -> new ListCommand();
            case "todo"    -> new AddCommand(arg, TaskType.TODO);
            case "deadline"-> new AddCommand(arg, TaskType.DEADLINE);
            case "event"   -> new AddCommand(arg, TaskType.EVENT);
            case "mark"    -> requireArgThen(new MarkCommand(arg, true), arg, "mark <index>");
            case "unmark"  -> requireArgThen(new MarkCommand(arg, false), arg, "unmark <index>");
            case "delete"  -> requireArgThen(new DeleteCommand(arg), arg, "delete <index>");
            case "find"    -> requireArgThen(new FindCommand(arg), arg, "find <substring>");
            case "bye"     -> new ByeCommand();
            default        -> throw new UnknownCommandException(cmd);
        };
    }




    /**
     * Ensures that an argument string is present for commands that require one.
     *
     * @param c        the command to return if argument is valid
     * @param arg      the argument string to check
     * @param expected usage string for error reporting
     * @return the provided command if argument is valid
     * @throws MissingArgumentException if the argument is {@code null} or blank
     */
    private Command requireArgThen(Command c, String arg, String expected) throws MissingArgumentException {
        // Assertion: 'expected' should never be null — it's a contract of this helper.
        assert expected != null : "expected usage string cannot be null";

        if (arg == null || arg.isBlank()) throw new MissingArgumentException(expected);

        // Assertion: if we reached here, arg must be non-null and non-blank.
        assert arg != null && !arg.isBlank() : "argument unexpectedly invalid after validation";

        return c;
    }

}
