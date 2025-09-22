package broccoli;

import broccoli.Tasks.Task;

import java.util.ArrayList;
import java.util.Scanner;
/**
 * Represents the main application class that manages task tracking functionality.
 * Initializes and coordinates the user interface, storage, parser, and task list components.
 */
public class Broccoli {
    private Ui userInterface;
    private Storage storage;
    private Parser parser;
    private TaskList taskList;
    private Scanner scanner;

    public Broccoli() {
        this.scanner = new Scanner(System.in);
        this.userInterface = new Ui();
        TaskList taskList1 = new TaskList(new ArrayList<Task>());
        this.storage = new Storage(taskList1, "./data/broccoli.txt");
        storage.loadFromFile(storage.getFilePath());
        this.taskList = new TaskList(storage.getTaskList());
        this.parser = new Parser(storage, userInterface, scanner);
    }

    public String getResponse(String input) {
        return parser.processCommand(input, this.taskList);
    }

    /**
     * Returns the initial greeting message to display when the app starts.
     * @return The greeting message string
     */
    public String getGreeting() {
        return userInterface.greeting();
    }
}
