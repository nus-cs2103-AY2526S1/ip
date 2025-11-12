package chatbot;

import chatbot.command.Command;
import chatbot.exception.EmptyArgumentException;
import chatbot.exception.InvalidCommandException;
import chatbot.parser.Parser;
import chatbot.response.Response;
import chatbot.storage.Storage;
import chatbot.tasklist.TaskList;
import chatbot.ui.Ui;


/**
 * The main class for Chatbot9000, a task management chatbot.
 * Handles reading user input, parsing commands, executing commands, and interacting with the user.
 */
public class Chatbot9000 {
    private static final String PARENT_FOLDER = "data";
    private static final String FILE_PATH = "data/chatbot.Chatbot9000.txt";

    private final Storage storage;
    private TaskList taskList;
    private final Ui ui;

    /**
     * Constructs a Chatbot9000 instance and initializes storage, UI, and task list.
     */
    public Chatbot9000() {
        this.storage = new Storage(PARENT_FOLDER, FILE_PATH);
        this.ui = new Ui();
        this.taskList = new TaskList(storage.loadTasks());
    }

    public Response getGreeting() {
        String responseMessage = "BEEP B00P I'm Chatbot9000\nYour wish is my command\n"
                +
                "'help' -> access the help page\n'reset' -> clear sample.txt data";
        return new Response(responseMessage, false);
    }


    public Response getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            String msg = c.execute(taskList, ui, storage);
            return new Response(msg, c.isExit());
        } catch (InvalidCommandException | EmptyArgumentException e) {
            String errorMsg = e.getMessage();
            return new Response(errorMsg, false);
        } catch (Exception e) {
            String errorMsg = e.toString();
            return new Response(errorMsg, false);
        }
    }

    /**
     * Starts the chatbot by greeting the user and entering the command loop.
     * Continuously reads and executes commands until the exit command is issued.
     */
    public void start() {
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                getResponse(fullCommand);
            } catch (Exception e) {
                e.getMessage();
            }
        }
    }

    /**
     * The main entry point of the program.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        new Chatbot9000().start();
    }
}
