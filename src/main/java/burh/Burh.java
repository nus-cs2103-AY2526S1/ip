package burh;
import java.io.IOException;

/**
 * Entry point of the Burh chatbot.
 * Handles initialization of storage, tasks, and user interface,
 * and runs the main event loop to process commands.
 */
public class Burh {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Creates a Burh instance with the given file path.
     *
     * @param filePath Path to the file used for saving and loading tasks.
     */
    public Burh(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (BurhException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public String getWelcomeString() {
        return ui.showWelcome();
    }

    /**
     * Generates a response for the user's chat message.
     *
     * @return String of correct output after user inputs.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.getCommand(input);
            switch (command) {

                case BYE: {
                    // Save data before exiting
                    try {
                        storage.save(tasks);
                    } catch (BurhException e) {
                        ui.showError("Error saving data: " + e.getMessage());
                    }
                    return ui.showGoodbye();
                }

                case LIST: {
                    return tasks.listTasks();
                }

                case MARK: {
                    int taskIndex = Parser.parseIndex(input, tasks.size());
                    return tasks.completeTask(taskIndex);
                }

                case UNMARK: {
                    int taskIndex = Parser.parseIndex(input, tasks.size());
                    return tasks.uncompleteTask(taskIndex);
                }

                case DELETE: {
                    int taskIndex = Parser.parseIndex(input, tasks.size());
                    return tasks.deleteTask(taskIndex);
                }

                case TODO: {
                    Task todoTask = Parser.parseTodoTask(input);
                    return tasks.addTask(todoTask);
                }

                case DEADLINE: {
                    Task deadlineTask = Parser.parseDeadlineTask(input);
                    return tasks.addTask(deadlineTask);
                }

                case EVENT: {
                    Task eventTask = Parser.parseEventTask(input);
                    return tasks.addTask(eventTask);
                }

                case FIND: {
                    return tasks.findKeywordInTasks(Parser.parseFindKeyword(input));
                }

                case SORT: {
                    return tasks.sortChrono();
                }

                default:
                    return "";
            }
        } catch (BurhException e) {
            return e.getMessage();
        }
    }

    public static void main(String[] args) {
        new Burh("data/test.txt");
    }
}
