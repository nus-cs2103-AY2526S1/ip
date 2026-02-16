package hope.parser;

import java.util.HashMap;
import java.util.Set;

import hope.commands.AddDeadlineTaskCommand;
import hope.commands.AddEventTaskCommand;
import hope.commands.AddFixedDurationTaskCommand;
import hope.commands.AddToDoTaskCommand;
import hope.commands.Command;
import hope.commands.DeleteCommand;
import hope.commands.EndCommand;
import hope.commands.FindCommand;
import hope.commands.ListCommand;
import hope.commands.MarkCommand;
import hope.commands.UnmarkCommand;
import hope.storage.TaskStorage;
import hope.storage.ToDoList;


/**
 * A parser that interprets user input and dispatches corresponding commands for the Hope chatbot.
 * The {@code Parser} class processes input strings, identifies valid commands, and executes them
 * using the appropriate {@link Command} implementation. It maintains a set of supported commands
 * and a mapping of commands to their respective {@link Command} objects. If an invalid command
 * is provided, an error message is displayed, and the list command is executed by default.
 */
public class Parser {

    /** A set of valid command names recognized by the parser. */
    private static final Set<String> COMMANDS = Set.of(
            "bye",
            "list",
            "mark",
            "unmark",
            "todo",
            "deadline",
            "event",
            "delete",
            "find",
            "fdt"
    );

    /** A mapping of command names to their corresponding {@link Command} implementations. */
    private static HashMap<String, Command> GET_COMMAND = new HashMap<>();

    /** The to-do list used by commands to manage tasks. */
    private ToDoList toDoList;

    /** The storage system for persisting tasks. */
    private TaskStorage taskStorage;

    /**
     * Constructs a {@code Parser} with the specified task storage and to-do list.
     * Initializes the command mapping by calling {@link #initialize()}.
     *
     * @param taskStorage the {@link TaskStorage} for persisting tasks
     * @param toDoList    the {@link ToDoList} for managing tasks
     */
    public Parser(TaskStorage taskStorage, ToDoList toDoList) {
        this.taskStorage = taskStorage;
        this.toDoList = toDoList;
        this.initialize();
    }

    /**
     * Initializes the command mapping by associating command names with their respective
     * {@link Command} implementations.
     */
    private void initialize() {
        GET_COMMAND.put("bye", new EndCommand());
        GET_COMMAND.put("list", new ListCommand(toDoList));
        GET_COMMAND.put("mark", new MarkCommand(toDoList, taskStorage));
        GET_COMMAND.put("unmark", new UnmarkCommand(toDoList, taskStorage));
        GET_COMMAND.put("todo", new AddToDoTaskCommand(toDoList, taskStorage));
        GET_COMMAND.put("deadline", new AddDeadlineTaskCommand(toDoList, taskStorage));
        GET_COMMAND.put("event", new AddEventTaskCommand(toDoList, taskStorage));
        GET_COMMAND.put("delete", new DeleteCommand(toDoList, taskStorage));
        GET_COMMAND.put("find", new FindCommand(toDoList));
        GET_COMMAND.put("fdt", new AddFixedDurationTaskCommand(toDoList, taskStorage));
    }


    public String parse(String input) {
        String[] instructions = input.split(" ", 2);
        String inputCommand = instructions[0];
        String argument = instructions.length == 2 ? instructions[1] : input;
        if (COMMANDS.contains(inputCommand)) {
            return GET_COMMAND.get(inputCommand).execute(argument.trim());
        }
        return "Pardon me, noble companion, but what dost thou mean to convey?\n";
    }
}
