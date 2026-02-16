package shadow.commands;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * The {@code Parser} class is responsible for interpreting user input and deciding
 * whether it represents a command to be executed or a task to be created. This class
 * serves as the primary mechanism for converting user input into executable actions
 * or new tasks within the application.
 */
public class Parser {
    private static final Map<String, Function<String[], Command>> commands = new HashMap<>();

    static {
        commands.put("mark", MarkTask::of);
        commands.put("unmark", UnmarkTask::of);
        commands.put("list", ListTasks::of);
        commands.put("delete", DeleteTask::of);
        commands.put("find", FindTask::of);
        commands.put("todo", CreateToDo::of);
        commands.put("event", CreateEvent::of);
        commands.put("deadline", CreateDeadLine::of);
        commands.put("source", Source::of);
        commands.put("bye", parts -> new TerminateCommand());
    }

    /**
     * Parses the given string input to determine the appropriate command to execute.
     * The method analyzes the command keyword (the first word in the input) and
     * matches it with a predefined set of commands. If the command is found,
     * it applies the associated function to interpret the input and generate the appropriate Command object.
     * If the input is empty or unrecognized, it returns a singleton instance of {@code UnknownCommand}.
     *
     * @param demand the user input string representing the command and its arguments
     * @return a {@code Command} object corresponding to the parsed input;
     *         returns {@code UnknownCommand} if the command is empty or unrecognized
     */
    public static Command parse(String demand) {
        if (demand.trim().isEmpty()) {
            return UnknownCommand.getInstance();
        }
        String[] parts = demand.split(" ", 2);
        parts[0] = parts[0].toLowerCase();

        Function<String[], Command> command = commands.get(parts[0]);
        if (command == null) {
            return UnknownCommand.getInstance();
        }
        return command.apply(parts);
    }
}
