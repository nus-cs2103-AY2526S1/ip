package jimmy;

import java.util.ArrayList;

import jimmy.exception.JimmyException;
import jimmy.task.Task;
import jimmy.task.TaskList;

/**
 * Represents a Jimmy chatbot object.
 */
public class Jimmy {
    private ArrayList<Task> storedTasks;
    private Storage taskStorage;
    private Ui ui;
    private Parser parser;
    private TaskList taskList;
    private boolean isRunning;

    /**
     * Constructor for a Jimmy object.
     */
    public Jimmy() {
        this.taskStorage = new Storage("taskStorage.txt");
        this.taskList = new TaskList();
        this.taskList = this.taskStorage.loadData(this.taskList);
        this.ui = new Ui();
        this.isRunning = true;
        this.parser = new Parser(this.ui, this.taskStorage, this.taskList);
    }

    /**
     * Runs the chatbot program.
     */
    public void run() {
        // Handle user inputs
        while (this.isRunning) {
            String command = this.ui.readCommand(); // Retrieve initial user input
            if (this.ui.displayExit().equals(this.parser.parseCommand(command))) {
                this.isRunning = false; // Stop the program
            }
        }
    }

    /**
     * Generates a response for the user's chat message.
     * Parses the input and do the corresponding tasks required like adding tasks to the taskList etc.
     */
    public String getResponse(String input) throws JimmyException {
        return this.parser.parseCommand(input);
    }

    public static void main(String[] args) throws JimmyException {
        new Jimmy().run();
    }
}

