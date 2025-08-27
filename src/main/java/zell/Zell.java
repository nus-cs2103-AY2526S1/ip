package zell;

import zell.storage.Storage;
import zell.ui.Ui;
import zell.task.TaskList;
import zell.exception.ZellException;

import java.util.ArrayList;

/**
 * Represents the Zell chatbot.
 */
public class Zell {
    /** Path to the local storage file */
    private static final String FILE_PATH = "./data/Zell.txt";

    public static void main(String[] args) {
        Storage storage = new Storage(FILE_PATH);
        Ui ui = new Ui();
        TaskList taskList;

        try {
            taskList = new TaskList(storage.loadTasks());
        } catch (ZellException ze) {
            ui.showMessage(ze.toString());
            taskList = new TaskList(new ArrayList<>());
        }

        ChatLoop chatLoop = new ChatLoop(taskList , storage, ui);
        chatLoop.run();

    }
}
