package dad.commands;

import dad.DadException;
import dad.TaskList;
import java.util.Stack;

public abstract class Command {

    public static Stack<Command> pushdown = new Stack<>();

    /**
     * A factory to generate the correct type of Command based on the input
     * 
     * @return a Command of the correct subtype based on the command
     *
     * @throws DadException if the input is malformed or invalid in any way
     */
    public static Command of(String[] commands, TaskList tasks) throws DadException {
        
        switch (commands[0].toLowerCase()) {
        case "list":
            return new ListCommand(tasks);
        case "find":
            return new FindCommand(commands, tasks);
        case "mark":
            return new MarkCommand(commands, tasks);
        case "unmark": 
            return new UnmarkCommand(commands, tasks);
        case "delete":
            return new DeleteCommand(commands, tasks);
        case "todo":
            return new TodoCommand(commands, tasks);
        case "deadline":
            return new DeadlineCommand(commands, tasks);
        case "event":
            return new EventCommand(commands, tasks);
        case "undo":
            return new UndoCommand();
        default:
            throw new DadException("I don't get it... (no such command " + commands[0] + ")");
        }
    }

    /**
     * Executes the task described by the Command subtype and returns the appropriate output
     *
     * @return the output String to be printed
     */
    abstract public String execute();

    /**
     * Executes the appropriate Undo action of the Command subtype, returning the appropriate output
     *
     * @return the output String to be printed
     */
    public String undo() {
        return "";
    }
}
