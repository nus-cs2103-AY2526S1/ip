package mon;

import java.util.ArrayList;

import mon.command.Command;
import mon.command.Parser;
import mon.exception.MonException;
import mon.storage.Storage;
import mon.task.Task;
import mon.task.TaskList;
import mon.ui.Ui;

/**
 * Main class for the Mon task manager application.
 */
public class Mon {
    private static final String FILE_PATH = "data/mon.txt";

    private final Storage storage;
    private final TaskList taskList;
    private final Ui ui;

    /**
     * Creates a new Mon application instance and initializes components.
     */
    public Mon() {
        this.ui = new Ui();
        this.storage = new Storage(FILE_PATH);
        TaskList tempTaskList;
        try {
            ArrayList<Task> tasks = storage.loadTasks();
            assert tasks != null : "Loaded tasks list cannot be null";
            tempTaskList = new TaskList(tasks);
        } catch (MonException e) {
            ui.showError("Error loading tasks: " + e.getMessage());
            tempTaskList = new TaskList();
        }
        this.taskList = tempTaskList;
        
        // Critical assertions for initialized components
        assert this.ui != null : "UI component must be initialized";
        assert this.storage != null : "Storage component must be initialized"; 
        assert this.taskList != null : "TaskList component must be initialized";
        
        // Check for reminders on startup
        showReminders();
    }

    /**
     * Main entry point of the application.
     * 
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        Mon mon = new Mon();
        mon.run();
    }

    /**
     * Runs the main application loop.
     */
    public void run() {
        ui.showWelcome();

        boolean shouldExit = false;
        
        while (true) {
            boolean hasMoreInput = ui.hasNextLine();
            boolean shouldContinue = hasMoreInput && !shouldExit;
            
            if (!shouldContinue) {
                break;
            }
            
            String input = ui.readCommand();
            shouldExit = handleInput(input);
        }
        
        ui.close();
    }

    /**
     * Handles a single line of user input.
     * 
     * @param input the user input to handle
     * @return true if the application should exit, false otherwise
     */
    private boolean handleInput(String input) {
        assert input != null : "Input cannot be null";
        
        try {
            Command command = Parser.parse(input);
            assert command != null : "Parser returned null command";

            String response = command.execute(taskList, storage);
            assert response != null : "Command response cannot be null";
            
            ui.showMessage(response);
            
            // Save tasks after every command that modifies the task list
            if (!command.isExit()) {
                storage.saveTasks(taskList.getTaskList());
            }
            
            return command.isExit();
        } catch (Exception e) {
            ui.showError(e.getMessage());
            return false;
        }
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        if (input == null) {
            return "Error: Input cannot be null";
        }
        
        try {
            Command command = Parser.parse(input);
            assert command != null : "Parser returned null command";

            String response = command.execute(taskList, storage);
            assert response != null : "Command response cannot be null";
            
            // Save tasks after every command that modifies the task list
            if (!command.isExit()) {
                storage.saveTasks(taskList.getTaskList());
            }
            
            return response;
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    /**
     * Checks for tasks that need reminders and displays them to the user.
     */
    private void showReminders() {
        try {
            ArrayList<Task> reminderTasks = storage.loadReminderTasks();
            
            if (!reminderTasks.isEmpty()) {
                ui.showMessage("🔔 REMINDERS - Tasks due within 7 days:");
                ui.showMessage("─".repeat(50));
                
                for (int i = 0; i < reminderTasks.size(); i++) {
                    Task task = reminderTasks.get(i);
                    ui.showMessage((i + 1) + ". " + task.toString());
                }
                
                ui.showMessage("─".repeat(50));
                ui.showMessage("Don't forget to complete these tasks!\n");
            }
        } catch (MonException e) {
            // Don't show error for reminders to avoid disrupting the user experience
            // Just silently continue if reminders can't be loaded
        }
    }
}
