package steven;

import java.util.Scanner;

import steven.command.Command;
import steven.parser.Parser;
import steven.storage.Storage;
import steven.task.TaskList;
import steven.ui.Ui;

/**
 * The main class of the Steven chatbot.
 */
public class Steven {
    private final Storage storage;
    private final TaskList tasks;
    private final Parser parser = new Parser();
    private final Ui ui = new Ui();

    private boolean isClosed = false;

    /**
     * Constructs a new Steven chatbot instance.
     *
     * @param filePath the path to the file where tasks will be stored
     */
    public Steven(String filePath) {
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(storage.fetchTasks());
    }

    /**
     * Starts the command-line interface of the application.
     *
     * <p>This method prints a greeting, continuously reads user input and calls the getResponse(String input)
     * method with the given user input.</p>
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        ui.printGreeting();
        ui.printHorizontalLine();

        while (!isClosed) {
            String input = scanner.nextLine();
            ui.print(getResponse(input));
            ui.printHorizontalLine();
        }
        scanner.close();
    }

    /**
     * A helper for the run() method, it is continuously called with the user string input.
     *
     * <p>This method interprets commands using <code>Parser</code>, and performs the corresponding
     * actions such as adding tasks, marking tasks, deleting tasks, or exiting.
     * All changes to tasks are persisted via the <code>Storage</code> class.</p>
     *
     * @param input the input command in a form of a string to the chatbot
     * @return the response of the chatbot to the input
     */
    public String getResponse(String input) {
        if (isClosed) {
            return null;
        }
        if (input.equals("bye")) {
            isClosed = true;
            return "\tbye";
        }
        Command command = parser.parse(input);
        return command.execute(storage, tasks);
    }

    /**
     * Returns the greeting of the chatbot as a string.
     *
     * @return the greeting of the chatbot
     */
    public String greet() {
        return ui.getGreeting();
    }

    /**
     * The entry point of the application.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        new Steven("data/tasklist.txt").run();
    }
}
