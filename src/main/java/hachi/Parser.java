package hachi;

import handler.*;

/**
 * Enumeration of all available commands. Commands are parsed from user input
 * and mapped to one of these values.
 */
enum Command {
    LIST, BYE, MARK, UNMARK, TODO, DEADLINE, EVENT, UNKNOWN, DELETE, FIND, NOTE, DISPLAY;

    /**
     * Converts a string input into a corresponding Command.
     *
     * @param input the user command input as a string
     * @return the corresponding Command
     */
    static Command from(String input) {
        if (input == null || input.isBlank()) return UNKNOWN;
        String first = input.trim().split("\\s+", 2)[0].toUpperCase();
        try {
            return Command.valueOf(first);
        } catch (IllegalArgumentException e) {
            return UNKNOWN;
        }
    }
}

/**
 * The Parser class is responsible for interpreting and executing user commands.
 * It listens for user input and processes different types of commands such as
 * listing tasks, marking/unmarking tasks, adding new tasks, and deleting tasks.
 */
public class Parser {

    /**
     * Starts the command loop for interacting with the user.
     * The loop reads commands, executes them, and updates the task list accordingly.
     *
     * <p>This method is responsible for handling commands such as LIST, MARK, UNMARK, TODO,
     * DEADLINE, EVENT, DELETE, BYE and NOTE as well as providing appropriate feedback to the user.</p>
     *
     * @param tasks   the task list to manage
     * @param storage the storage object for saving tasks to a file
     */
    public static String parse(TaskList tasks, Storage storage, String input) {

        Ui ui = new Ui(tasks);
        Command command = Command.from(input);

        switch (command) {
        case LIST:
            ListHandler handler = new ListHandler();
            return handler.execute(tasks);

        case BYE:
            ByeHandler byeHandler = new ByeHandler(storage);
            return byeHandler.execute(tasks);


        case UNMARK:
            UnmarkHandler unmarkHandler = new UnmarkHandler(input, ui);
            return unmarkHandler.execute(tasks);

        case MARK:
            MarkHandler markHandler = new MarkHandler(input, ui);
            return markHandler.execute(tasks);


        case TODO:
            TodoHandler todoHandler = new TodoHandler(input, ui);
            return todoHandler.execute(tasks);


        case DEADLINE:
            DeadlineHandler deadlineHandler = new DeadlineHandler(input, ui);
            return deadlineHandler.execute(tasks);


        case EVENT:
            EventHandler eventHandler = new EventHandler(input, ui);
            return eventHandler.execute(tasks);


        case DELETE:
            DeleteHandler deleteHandler = new DeleteHandler(input, ui);
            return deleteHandler.execute(tasks);


        case FIND:
            FindHandler findHandler = new FindHandler(input, ui);
            return findHandler.execute(tasks);

        case NOTE:
            NoteHandler noteHandler = new NoteHandler(input, ui);
            return noteHandler.execute(tasks);

        case DISPLAY:
            DisplayHandler displayHandler = new DisplayHandler(input, ui);
            return displayHandler.execute(tasks);


        default:
            UnknownHandler unknownHandler = new UnknownHandler();
            return unknownHandler.execute(tasks);
        }
    }
}

