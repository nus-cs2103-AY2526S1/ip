import java.io.File;
import java.util.ArrayList;

import application.Parser;
import application.Storage;
import application.TaskList;
import application.Ui;
import command.Command;
import exception.RomidasException;
import tasks.Task;

public class Romidas {
    private static final String DATA_PATH = getProjectRootPath() + File.separator + "romidas.txt";
    
    private static String getProjectRootPath() {
        String currentDir = System.getProperty("user.dir");
        
        if (currentDir.endsWith("text-ui-test")) {
            return new File(currentDir).getParent();
        }
        
        return currentDir;
    }
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Romidas() {
        this.ui = new Ui();
        this.storage = new Storage();
        ArrayList<Task> store = storage.loadTasks(DATA_PATH);
        this.taskList = new TaskList(store);
    }

    public void run() {
        ui.welcome();
        boolean isBye = false;
        while (!isBye) {
            try {
                String input = ui.readCommand();
                if (input.equalsIgnoreCase("bye")) {
                    isBye = true;
                    ui.showLine();
                    break;
                }
                ui.showLine();
                Command c = Parser.parse(input, taskList, ui, storage, DATA_PATH);
                c.execute(taskList, ui, storage);
                isBye = c.isBye();
            } catch (NumberFormatException e) {
                ui.showError("tasks.Task number must be an integer.");
            } catch (IllegalArgumentException e) {
                ui.showError("I'm sorry, I don't recognise that command. "
                        + "Try one of list, event, todo, deadline, mark, unmark, delete");
            } catch (RomidasException e) {
                ui.showError(e.getMessage());
            } finally {
                if (!isBye) {
                    ui.showLine();
                }
            }
        }
        ui.showGoodbye();
    }

    public static void main(String[] args) {
        Romidas romidas = new Romidas();
        romidas.run();
    }
}