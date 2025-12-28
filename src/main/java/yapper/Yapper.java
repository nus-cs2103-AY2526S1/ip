package yapper;

import commons.exceptions.InvalidCommandException;
import logic.commands.Command;
import logic.parser.Parser;
import models.TaskList;
import storage.FileManager;
import ui.Ui;

/**
 * The Yapper class is the Chatbot for CS2103T.
 */
public class Yapper {
    private Ui ui;
    private TaskList tasks;

    /**
     * Constructs a new Yapper chatbot instance.
     * Initializes the UI, loads tasks from storage, and starts the main loop
     */
    public Yapper() {
        this.ui = new Ui();
        this.tasks = new TaskList(FileManager.loadTasks());
    }

    /**
     * Returns the response given a user input
     */
    public String getResponse(String userInput) {
        System.out.println(userInput);
        try {
            Command command = Parser.parseCommand(userInput);
            String response = command.execute(tasks, ui);
            return response;

        } catch (InvalidCommandException e) {
            return ui.getErrorResponse(e.getMessage());
        }

    }

    public String getGreet() {
        return ui.getGreetResponse();
    }
}
