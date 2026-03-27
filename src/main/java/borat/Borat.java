package borat;

import borat.exception.BoratExceptions;
import borat.parser.Parser;
import borat.storage.Storage;
import borat.task.TaskList;
import borat.ui.UI;

/**
 * Runs the Borat application and coordinates command processing.
 */
public class Borat {

    private final UI ui;
    private final Storage storage;
    private final TaskList tasks;
    private final Parser parser;

    /**
     * Creates a new Borat instance using the given storage file path.
     *
     * @param filePath Path to the tasks data file.
     */
    public Borat(String filePath) {
        assert filePath != null : "File path cannot be null";
        assert !filePath.trim().isEmpty() : "File path cannot be empty";

        this.ui = new UI();
        this.storage = new Storage(filePath);
        this.parser = new Parser();
        // load existing tasks from storage file
        this.tasks = new TaskList(storage.loadTasks());

    }

    /**
     * Starts the application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        new Borat("data/tasks.txt").run();
    }

    /**
     * Processes a single command and returns a textual response for GUI display.
     *
     */
    private void run() {
        ui.greet();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                String response = getResponse(fullCommand);
                System.out.println(response);
                if (borat.ui.UI.GOODBYE_MESSAGE.equals(response)) {
                    ui.exit();
                    isExit = true;
                }
            } catch (Exception e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Processes a single command and returns a textual response for GUI/CLI display.
     * Uses Parser for parsing, TaskList for state changes, and Storage for persistence.
     *
     * @param fullCommand raw user input
     * @return response text
     */
    public String getResponse(String fullCommand) {
        assert fullCommand != null : "Command cannot be null";

        StringBuilder currResponse = new StringBuilder();
        try {
            String[] parsedCommand = parser.parseCommand(fullCommand);
            assert parsedCommand != null : "Parser must return non-null result";
            assert parsedCommand.length == 2 : "Parser must return exactly 2 elements";

            String firstWord = parsedCommand[0];
            String description = parsedCommand[1];

            assert firstWord != null : "First word cannot be null";
            assert !firstWord.trim().isEmpty() : "First word cannot be empty";

            switch (firstWord) {
                case "bye":
                    currResponse.append(ui.exit());
                    break;
                case "list":
                    currResponse.append(tasks.listTasks());
                    break;
                case "mark":
                case "unmark":
                    currResponse.append(tasks.markTask(new String[]{firstWord, description}));
                    break;
                case "todo":
                    currResponse.append(tasks.addToDo(description));
                    break;
                case "event":
                    try {
                        String[] eventParts = parser.parseEvent(description);
                        currResponse.append(tasks.addEvent(eventParts[0], eventParts[1], eventParts[2]));
                    } catch (IllegalArgumentException e) {
                        ui.showError(e.getMessage());
                    }
                    break;
                case "deadline":
                    try {
                        String[] deadlineParts = parser.parseDeadline(description);
                        currResponse.append(tasks.addDeadline(deadlineParts[0], deadlineParts[1]));
                    } catch (IllegalArgumentException e) {
                        ui.showError(e.getMessage());
                    }
                    break;
                case "find":
                    currResponse.append(tasks.find(description));
                    break;
                case "delete":
                    currResponse.append(tasks.delete(description));
                default:
                    currResponse.append("Please try again");
            }

            // save tasks once complete
            storage.save(tasks.getTasks());
        } catch (NumberFormatException e) {
            ui.showError("Please provide a valid number. ");
        } catch (BoratExceptions e) {
            ui.showError(e.getMessage());
        }

        return currResponse.toString();
    }
}