package banana.main;

import java.io.IOException;
//import java.time.format.DateTimeParseException;

import banana.command.Command;
import banana.exceptions.BananaException;
import banana.utils.Parser;
import banana.utils.Storage;
import banana.utils.TaskList;

/**
 * A personal assistant chatbot that helps users manage their tasks.
 */
public class BananaBot {
    private static TaskList tasks = new TaskList();
    private Storage storage;

    /**
     * Initializes the BananaBot with the specified file path for storage.
     *
     * @param filePath The file path where tasks are stored.
     */
    public BananaBot(String filePath) {
        storage = new Storage(filePath);
    }
    /**
     * Loads tasks from the storage file.
     *
     * @return A message indicating the result of the load operation.
     */
    public String loadTasks() {
        try {
            tasks = storage.load();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < tasks.size(); i++) {
                sb.append(tasks.getTask(i).toString()).append("\n");
            }
            return sb.toString().trim(); // Remove trailing newline
        } catch (IOException e) {
            return "Error: Failed to reload tasks: " + e.getMessage();
        } catch (BananaException e) {
            throw new RuntimeException(e);
        }
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, storage);
        } catch (NumberFormatException e) {
            return "Error: Task number must be an integer.";
        } catch (BananaException e) {
            return "Error: " + e.getMessage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        //do nothing
    }
}
