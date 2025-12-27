package tension;

import java.util.ArrayList;

import tension.helper.Command;
import tension.helper.Parser;
import tension.helper.Storage;
import tension.helper.TaskList;
import tension.helper.Ui;
import tension.task.Task;

/**
 * starts the main chatbot application
 */
public class Tension {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Initialises the Tension bot based on data storage
     * @param filePath
     */
    public Tension(String filePath) throws Exception {
        assert filePath != null;
        assert !filePath.isEmpty();
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        ArrayList<Task> tasks = storage.retrieveTasks();
        this.taskList = new TaskList(tasks, new ArrayList<>(), storage, ui);
    }

    /**
     * responds appropriately to input without printing
     */
    public String responseToInput(String input) {
        try {
            Parser parser = new Parser();
            Command command = parser.parse(input);
            taskList.insertCommand(command);
            return taskList.executesAndReturnString();
        } catch (TensionException e) {
            return e.getMessage();
        } catch (Exception e) {
            return "Something went wrong, please try again, error not implemented yet: " + e.getMessage();
        }
    }

    public static void main(String[] args) throws Exception {
        new Tension("data.txt");
    }
}
