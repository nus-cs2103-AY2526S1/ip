package jinbot;

import java.util.List;

import jinbot.command.ByeCommand;
import jinbot.command.Command;
import jinbot.storage.Storage;
import jinbot.task.Task;
import jinbot.task.TaskList;
import jinbot.ui.Ui;




/**
 * The main class of the JinBot application.
 * JinBot is a task manager that supports commands such as adding todos,
 * deadlines, events, marking tasks, and saving them to disk.
 */
public class JinBot {
    private final Ui ui;
    private final TaskList taskList;
    private final Storage storage;

    /**
     * Constructs a JinBot instance.
     * Initializes the UI and storage, and loads previously saved tasks.
     */
    public JinBot() {
        this.ui = new Ui();
        this.storage = new Storage();
        // Load persisted tasks, if any
        List<Task> loaded = storage.loadTasks();
        assert loaded != null : "Loaded tasks from storage should not be null";
        this.taskList = new TaskList();
        for (Task t : loaded) {
            assert t != null : "Loaded tasks from storage should not be null";
            taskList.addTask(t);
        }
    }

    /**
     * Runs the main loop of JinBot.
     * Continuously reads user input, parses it into a command,
     * executes the command, and saves the updated task list.
     * Terminates when a ByeCommand is issued.
     */
    public void run() {
        ui.showGreeting();

        while (true) {
            try {
                String userInput = ui.readCommand();
                Command command = Parser.parse(userInput);
                command.execute(ui, taskList);
                storage.saveTasks(taskList);

                if (command instanceof ByeCommand) {
                    return;
                }
            } catch (JinBotException e) {
                ui.printBox(e.getMessage());
            } catch (IndexOutOfBoundsException e) {
                ui.printBox("Error! Invalid task number. Task number must be positive integer "
                    + "within size of the current task list");
            } catch (Exception e) {
                ui.printBox("Oops! Something went wrong. Please try again.");
            }
        }
    }

    /**
     * Application entry point.
     */
    public static void main(String[] args) {
        new JinBot().run();
    }

    /**
     * Generates a response for the user's chat message.
     * @param input User input command.
     * @return It returns response of the user input command.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            String response = command.execute(ui, taskList);
            storage.saveTasks(taskList);
            return response;
        } catch (JinBotException e) {
            return e.getMessage();
        } catch (IndexOutOfBoundsException e) {
            return "Error! Invalid task number. Task number must be positive integer "
                    + "within size of the current task list";
        } catch (Exception e) {
            return "Oops! Something went wrong. Please try again.";
        }
    }
}
