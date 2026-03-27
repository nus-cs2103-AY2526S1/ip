package amogus;

import java.io.IOException;

import command.Command;
import tasks.TaskList;

/**
 * Represents a chatbot which functions as a task manager
 * which responds to user inputs and reacts accordingly.
 */
public class Amogus {

    static final String NAME = "amogus";
    private static final String FILE_PATH = "./data/Tasks.TaskList.txt";

    /**
     * Creates all necessary objects for interaction between each other,
     * namely Amogus.UI for responses to user, Amogus.FileStorage for hard disk load
     * and store, Tasks.TaskList for list of tasks.
     */
    public static void chat() {
        UI ui = new UI();
        ui.welcome();
        FileStorage fileStorage = new FileStorage(FILE_PATH);
        TaskList tasks;

        try {
            tasks = fileStorage.loadTasks();
        } catch (AmogusException | IOException e) {
            ui.showMsg(e.getMessage());
            tasks = new TaskList();
        }

        while (true) {
            String input = ui.readCommand();
            try {
                Command command = Parser.parse(input);
                command.execute(tasks, ui, fileStorage);
                if (command.isExit()) {
                    break;
                }
            } catch (AmogusException e) {
                ui.showMsg(e.getMessage());
            }
        }
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        return "Amogus heard: " + input;
    }

    public static void main(String[] args) {
        chat();
    }
}
