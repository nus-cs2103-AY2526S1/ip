package aurora;

import java.util.Scanner;

import aurora.command.Command;
import aurora.command.CommandReader;
import aurora.storage.Storage;
import aurora.task.TaskList;
import aurora.ui.Ui;

/**
 * Aurora is a simple task management chatbot that runs on the command line.
 * <p>
 * It allows user to add, list, mark as done, and delete tasks.
 * Saving and loading between sessions is supported and tasks are stored in a text file on disk.
 * </p>
 */
public class Aurora {
    private final Storage storage;
    private final TaskList list;
    private final Ui ui;
    private String commandType;

    /**
     * Constructs a new Aurora chatbot.
     *
     * @param filePath path to the file used for saving and loading tasks
     */
    public Aurora(String filePath) {
        this.ui = new Ui(new Scanner(System.in));
        this.storage = new Storage(filePath);
        this.list = storage.load();
    }

    /**
     * Constructs a new Aurora chatbot.
     */
    public Aurora() {
        this.ui = new Ui(new Scanner(System.in));
        this.storage = new Storage("");
        this.list = storage.load();
    }

    /**
     * Runs the chatbot session.
     * <p>
     * Displays a greeting, enter a command loop,
     * and display a goodbye message when loop terminates.
     * </p>
     */
    public void run() {
        ui.speakIntro();
        loop();
        ui.speakOutro();
    }

    private void loop() {
        String input = ui.readInput();

        while (!input.equalsIgnoreCase("bye")) {
            Command command = CommandReader.read(input);
            ui.speak(command.execute(list));
            storage.save(list);
            input = ui.readInput();
        }
    }

    /**
     * Entry point of the program.
     *
     * @param args command-line arguments (unused)
     */
    public static void main(String[] args) {
        new Aurora("./data/aurora.txt").run();
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        Command c = CommandReader.read(input);
        commandType = c.getClass().getSimpleName();
        return c.execute(list);
    }

    public String getCommandType() {
        return commandType;
    }
}
