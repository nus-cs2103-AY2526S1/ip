package com.neokortex.commands;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import com.neokortex.commands.factory.AddCommandFactory;
import com.neokortex.commands.factory.CommandFactory;
import com.neokortex.commands.factory.DeleteCommandFactory;
import com.neokortex.commands.factory.EchoCommandFactory;
import com.neokortex.commands.factory.FarewellCommandFactory;
import com.neokortex.commands.factory.FindCommandFactory;
import com.neokortex.commands.factory.GreetCommandFactory;
import com.neokortex.commands.factory.ListCommandFactory;
import com.neokortex.commands.factory.MarkCommandFactory;
import com.neokortex.commands.factory.SaveCommandFactory;
import com.neokortex.commands.factory.UnmarkCommandFactory;
import com.neokortex.commands.parsers.AddCommandParser;
import com.neokortex.commands.parsers.CommandParser;
import com.neokortex.commands.parsers.DeleteCommandParser;
import com.neokortex.commands.parsers.EchoCommandParser;
import com.neokortex.commands.parsers.FarewellCommandParser;
import com.neokortex.commands.parsers.FindCommandParser;
import com.neokortex.commands.parsers.GreetCommandParser;
import com.neokortex.commands.parsers.ListCommandParser;
import com.neokortex.commands.parsers.MarkCommandParser;
import com.neokortex.commands.parsers.SaveCommandParser;
import com.neokortex.commands.parsers.UnmarkCommandParser;
import com.neokortex.core.ApplicationContext;

/**
 * Represents all available {@code Command}s as an enum with support for multiple name aliases,
 * and links each enum to their respective {@link CommandParser} and {@link CommandFactory}
 *
 * <p>
 * The {@code CommandType} enum serves as a central {@code Command}, providing a unified interface
 * for complex handling of {@code Command}s.
 * </p>
 *
 * <p><b>Responsibilities:</b></p>
 * <ul>
 *     <li>Maps command keywords to respective Parser and Factory (may be refactored in the future)</li>
 *     <li>Handles case-insensitive command lookup</li>
 *     <li>Returns {@link #INVALID} for unrecognized commands</li>
 *     <li>Supports multiple aliases for each command type</li>
 * </ul>
 *
 * <p><b>Supported Commands:</b></p>
 * <ul>
 *   <li>{@code GREET} - Greets the user: hello/hi</li>
 *   <li>{@code ECHO} - Echoes text provided by user: echo {String text}</li>
 *   <li>{@code TODO} - Add {@code ToDoTask} to {@code TaskList}: todo {String taskDescription}</li>
 *   <li>{@code DEADLINE} - Add {@code DeadlineTask} to {@code TaskList}:
 *       deadline {String taskDescription} /by {String Deadline}</li>
 *   <li>{@code EVENT} - Add {@code EventTask} to {@code TaskList}:
 *       event {String taskDescription} /from {String startTime} /to /from {String endTime}</li>
 *   <li>{@code DELETE} - Delete {@code Task} from {@code TaskList}: delete/remove {int taskId}</li>
 *   <li>{@code LIST} - Displays (@code TaskList)s: list</li>
 *   <li>{@code SAVE} - Saves current {@code TaskList} to specified path: save {String path}</li>
 *   <li>{@code FIND} - Search for {@code Task}s with the specified keyword:
 *       find/search {}String keyword}</li>
 *   <li>{@code MARK} - Mark {@code Task} as complete: mark {int taskId}</li>
 *   <li>{@code UNMARK} - Unmark marked {@code Task}: unmark {int taskId}</li>
 *   <li>{@code FAREWELL} - Bid user farewell and quits the program: farewell/goodbye/exit/quit</li>
 *   <li>{@code INVALID} - Fallback for unrecognized commands</li>
 * </ul>
 *
 * <p><b>Credit: documentation was adapted from discussion with generative AI</b></p>
 *
 * @see CommandFactory
 * @see ApplicationContext
 */
public enum CommandType {
    GREET(Set.of("hello", "hi", "greet"), new GreetCommandParser(), new GreetCommandFactory()),
    ECHO(Set.of("echo"), new EchoCommandParser(), new EchoCommandFactory()),
    TODO(Set.of("todo"), new AddCommandParser(), new AddCommandFactory()),
    DEADLINE(Set.of("deadline"), new AddCommandParser(), new AddCommandFactory()),
    EVENT(Set.of("event"), new AddCommandParser(), new AddCommandFactory()),
    DELETE(Set.of("delete", "remove"), new DeleteCommandParser(), new DeleteCommandFactory()),
    LIST(Set.of("list"), new ListCommandParser(), new ListCommandFactory()),
    SAVE(Set.of("save"), new SaveCommandParser(), new SaveCommandFactory()),
    FIND(Set.of("find", "search"), new FindCommandParser(), new FindCommandFactory()),
    MARK(Set.of("mark"), new MarkCommandParser(), new MarkCommandFactory()),
    UNMARK(Set.of("unmark"), new UnmarkCommandParser(), new UnmarkCommandFactory()),
    FAREWELL(Set.of("bye", "farewell", "goodbye", "exit", "quit"),
            new FarewellCommandParser(), new FarewellCommandFactory()),
    INVALID(Set.of("invalid"), null, null);

    private final Set<String> alias;
    private final CommandParser parser;
    private final CommandFactory factory;

    CommandType(Set<String> alias, CommandParser parser, CommandFactory factory) {
        this.alias = alias;
        this.parser = parser;
        this.factory = factory;
    }

    public static CommandType getCommandType(String command) {
        String cleanCommand = command.toLowerCase().trim();

        return Arrays.stream(values())
                .filter(cmd -> cmd.alias.contains(cleanCommand))
                .findFirst()
                .orElse(INVALID);
    }

    public static Set<String> getAllAliases() {
        return Arrays.stream(values())
                .flatMap(cmd -> cmd.alias.stream())
                .collect(Collectors.toSet());
    }

    public static boolean isValidCommand(String command) {
        return getCommandType(command) != INVALID;
    }

    public CommandParser getParser() {
        return this.parser;
    }

    public CommandFactory getFactory() {
        return this.factory;
    }
}
