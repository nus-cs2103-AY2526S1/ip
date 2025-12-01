package siri;

import siri.exception.SiriException;
import siri.task.DeadlineTask;
import siri.task.EventTask;
import siri.task.Task;
import siri.task.ToDoTask;
import siri.util.ConsoleLogger;
import siri.util.Parser;
import siri.util.Storage;
import siri.util.TaskManager;

import java.util.List;
import java.util.Scanner;

/**
 * Main class for siri
 */
public class Siri {
    private final Storage storage;
    private final ConsoleLogger consoleLogger;
    private final TaskManager taskManager;
    private StringBuilder response;

    public Siri(String path) {
        this.storage = new Storage(path);
        this.taskManager = new TaskManager();
        this.consoleLogger = new ConsoleLogger(taskManager);
    }

    /**
     * The starting function of the app
     * @param args
     */
    public static void main(String[] args) {
        Siri siri = new Siri("./data/data.txt");
        //Generate from https://patorjk.com/software/taag/
        System.out.print(siri.getWelcome());
        siri.run();
    }

    /**
     * Method to run the CLI
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\n____________________________________________________________\n");
        consoleLogger.PrintGreet();
        System.out.print("____________________________________________________________\n");
        while (true) {
            String input = sc.nextLine().trim();
            if (input.isEmpty()) {
                continue;
            }
            Parser parser = new Parser(input);
            String keyword = parser.getKeyword();
            Command cmd = parser.getCommand();
            try{
                if (cmd == null) {
                    throw new SiriException("Unknown command " + keyword);
                }

                switch (cmd) {
                    case MARK: {
                        int n = parser.parseMark();
                        response = consoleLogger.mark(n);
                        storage.save(taskManager.getTasks());
                        break;
                    }

                    case UNMARK: {
                        int n = parser.parseUnMark();
                        response = consoleLogger.unmark(n);
                        storage.save(taskManager.getTasks());
                        break;
                    }

                    case DELETE: {
                        int n = parser.parseDelete();
                        response = consoleLogger.delete(n);
                        storage.save(taskManager.getTasks());
                    }

                    case TODO: {
                        String parse = parser.parseTodo();
                        ToDoTask todo = new ToDoTask(parse);
                        taskManager.addTask(todo);
                        response = consoleLogger.displayTask(todo);
                        storage.save(taskManager.getTasks());
                        break;
                    }

                    case DEADLINE: {
                        String[] parse = parser.parseDeadline();
                        DeadlineTask deadlineTask = new DeadlineTask(parse[0], parse[1]);
                        taskManager.addTask(deadlineTask);
                        response = consoleLogger.displayTask(deadlineTask);
                        storage.save(taskManager.getTasks());
                        break;
                    }

                    case EVENT: {
                        String[] parse = parser.parseEvent();;
                        EventTask event = new EventTask(parse[0], parse[1], parse[2]);
                        taskManager.addTask(event);
                        response = consoleLogger.displayTask(event);
                        storage.save(taskManager.getTasks());
                        break;
                    }

                    case LIST: {
                        response = consoleLogger.displayList();
                        break;
                    }

                    case FIND: {
                        String description = parser.parseFind();
                        List<Task> list = taskManager.findTask(description);
                        response = consoleLogger.displayFind(list);
                        break;
                    }

                    case BYE: {
                        response = consoleLogger.PrintExit();
                        sc.close();
                        return;
                    }
                }
            } catch (SiriException e) {
                ConsoleLogger.printLine("Error: " + e.getMessage());
            }
        }

    }

    /**
     * Handles user input, parses it into a command, dispatches to the appropriate handler,
     * and returns the formatted response. Persists task changes for mutating commands.
     *
     * @param input the raw user input string (may be null or contain leading/trailing spaces)
     * @return the formatted response string to display
     */
    public String getResponse(String input) {
        try {
            String trimmed = normalize(input);
            if (trimmed.isEmpty()) return "";

            Parser parser = new Parser(trimmed);
            Command cmd = requireCommand(parser);

            String message = dispatch(cmd, parser);

            if (isMutating(cmd)) {
                storage.save(taskManager.getTasks());
            }
            return message;

        } catch (SiriException e) {
            return ConsoleLogger.printLine("Error: " + e.getMessage()).toString();
        }
    }

    /**
     * Normalizes raw user input by trimming whitespace and replacing null with an empty string.
     *
     * @param input the raw user input (may be null)
     * @return a trimmed non-null string (empty if input was null or only whitespace)
     */
    private String normalize(String input) {
        return (input == null) ? "" : input.trim();
    }

    /**
     * Ensures a valid command is extracted from the parser.
     *
     * @param parser the parser initialized with user input
     * @return the parsed Command
     * @throws SiriException if no valid command could be identified
     */
    private Command requireCommand(Parser parser) throws SiriException {
        Command cmd = parser.getCommand();
        if (cmd == null) {
            throw new SiriException("Unknown command " + parser.getKeyword());
        }
        return cmd;
    }

    /**
     * Dispatches a command to its corresponding handler method.
     *
     * @param cmd    the parsed command
     * @param parser the parser containing user input
     * @return the response string generated by the handler
     * @throws SiriException if the command is unknown or input parsing fails
     */
    private String dispatch(Command cmd, Parser parser) throws SiriException {
        // One line per command; each calls a small, focused handler.
        return switch (cmd) {
            case LIST    -> handleList();
            case TODO    -> handleTodo(parser);
            case DEADLINE-> handleDeadline(parser);
            case EVENT   -> handleEvent(parser);
            case MARK    -> handleMark(parser);
            case UNMARK  -> handleUnmark(parser);
            case DELETE  -> handleDelete(parser);
            case FIND    -> handleFind(parser);
            case UNDO    -> handleUndo(parser);
            case BYE     -> handleBye();
            default      -> throw new SiriException("Unknown command " + parser.getKeyword());
        };
    }

    /**
     * Determines whether a command modifies persistent state and therefore
     * requires saving tasks to storage.
     *
     * @param cmd the command to check
     * @return true if the command mutates the task list, false otherwise
     */
    private boolean isMutating(Command cmd) {
        return switch (cmd) {
            case TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE, UNDO -> true;
            default -> false;
        };
    }

    /* ===================== Command Handlers (single-purpose) ===================== */

    /**
     * Handles the LIST command.
     *
     * @return the formatted list of all tasks
     */
    private String handleList() {
        return consoleLogger.displayList().toString();
    }

    /**
     * Handles the TODO command.
     *
     * @param parser the parser used to extract task description
     * @return the formatted response for adding a ToDoTask
     * @throws SiriException if parsing fails
     */
    private String handleTodo(Parser parser) throws SiriException {
        String desc = parser.parseTodo();
        ToDoTask todo = new ToDoTask(desc);
        taskManager.addTask(todo);
        return consoleLogger.displayTask(todo).toString();
    }

    /**
     * Handles the DEADLINE command.
     *
     * @param parser the parser used to extract description and deadline
     * @return the formatted response for adding a DeadlineTask
     * @throws SiriException if parsing fails
     */
    private String handleDeadline(Parser parser) throws SiriException {
        String[] p = parser.parseDeadline(); // [desc, by]
        DeadlineTask d = new DeadlineTask(p[0], p[1]);
        taskManager.addTask(d);
        return consoleLogger.displayTask(d).toString();
    }

    /**
     * Handles the EVENT command.
     *
     * @param parser the parser used to extract description, start, and end time
     * @return the formatted response for adding an EventTask
     * @throws SiriException if parsing fails
     */
    private String handleEvent(Parser parser) throws SiriException {
        String[] p = parser.parseEvent(); // [desc, from, to]
        EventTask e = new EventTask(p[0], p[1], p[2]);
        taskManager.addTask(e);
        return consoleLogger.displayTask(e).toString();
    }

    /**
     * Handles the MARK command.
     *
     * @param parser the parser used to extract task index
     * @return the formatted response for marking the task as done
     * @throws SiriException if parsing fails or index is invalid
     */
    private String handleMark(Parser parser) throws SiriException {
        int idx = parser.parseMark();
        return consoleLogger.mark(idx).toString();
    }

    /**
     * Handles the UNMARK command.
     *
     * @param parser the parser used to extract task index
     * @return the formatted response for unmarking the task
     * @throws SiriException if parsing fails or index is invalid
     */
    private String handleUnmark(Parser parser) throws SiriException {
        int idx = parser.parseUnMark();
        return consoleLogger.unmark(idx).toString();
    }

    /**
     * Handles the DELETE command.
     *
     * @param parser the parser used to extract task index
     * @return the formatted response for deleting the task
     * @throws SiriException if parsing fails or index is invalid
     */
    private String handleDelete(Parser parser) throws SiriException {
        int idx = parser.parseDelete();
        return consoleLogger.delete(idx).toString();
    }

    /**
     * Handles the FIND command.
     *
     * @param parser the parser used to extract search keyword
     * @return the formatted response listing matching tasks
     * @throws SiriException if parsing fails
     */
    private String handleFind(Parser parser) throws SiriException {
        String kw = parser.parseFind();
        List<Task> hits = taskManager.findTask(kw);
        return consoleLogger.displayFind(hits).toString();
    }

    /**
     * Handles the UNDO command.
     *
     * @param parser the parser used to extract undo index
     * @return the formatted response showing updated task list
     * @throws SiriException if parsing fails or undo fails
     */
    private String handleUndo(Parser parser) throws SiriException {
        int index = parser.parseUndo();
        if (index == 0) taskManager.undo();
        else taskManager.undoTask(index);
        return consoleLogger.displayList().toString();
    }

    /**
     * Handles the BYE command.
     *
     * @return the exit message string
     */
    private String handleBye() {
        return consoleLogger.PrintExit().toString();
    }


    /**
     * Get the welcome String with logo
     * @return String of welcome word and logo
     */
    public String getWelcome() {
        String siriLogo = "   _____ _      _ \n" +
                "  / ____(_)    (_)\n" +
                " | (___  _ _ __ _ \n" +
                "  \\___ \\| | '__| |\n" +
                "  ____) | | |  | |\n" +
                " |_____/|_|_|  |_|\n" +
                "                  \n" +
                "                  ";

        return "Hello from\n" + siriLogo;
    }
}
