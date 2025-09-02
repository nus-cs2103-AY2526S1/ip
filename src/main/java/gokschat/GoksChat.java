package gokschat;

import gokschat.commands.Command;
import gokschat.exceptions.BadFileException;
import gokschat.exceptions.DeadlineException;
import gokschat.exceptions.InvalidPromptException;
import gokschat.exceptions.TodoException;
import gokschat.tasks.Task;

import java.util.ArrayList;
import java.util.List;

/// This is the main class for GoksChat.
///
/// @author Ravichandran Gokul
public class GoksChat {
    private Ui ui;
    private Storage storage;
    private InputProcessor inputProcessor;
    private List<Task> listOfTasks;

    /**
     * Constructs a new {@code gokschat.GoksChat} object with the file path.
     * This constructor initializes the object's internal state based on the provided parameters.
     *
     * @param filePath
     */
    public GoksChat(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            listOfTasks = storage.intialiseTaskList();
        } catch (BadFileException e) {
            ui.exceptionMessage(e);
            listOfTasks = new ArrayList<>();
        }
        inputProcessor = new InputProcessor(ui, storage, listOfTasks);
    }

    public void run() {
        ui.printWelcomeMessage();

        // Get user input
        String userInput = ui.readUserInput();

        // Print according to what the user input is
        while (!userInput.equals("bye")) {
            try {
                Command c = inputProcessor.processInput(userInput);
                c.execute();
            } catch (TodoException e) {
                ui.exceptionMessage(e);
            } catch (InvalidPromptException e) {
                ui.exceptionMessage(e);
            } catch (DeadlineException e) {
                ui.exceptionMessage(e);
            } finally {
                // Get user input again
                userInput = ui.readUserInput();
            }
        }

        try {
            storage.updateFile(listOfTasks);
        } catch (BadFileException e) {
            ui.exceptionMessage(e);
            listOfTasks = new ArrayList<>();
        }

        ui.printGoodbyeMessage();
    }

    public static void main(String[] args) {
        GoksChat goks = new GoksChat("src/data/gokschat.txt");
        goks.run();
    }
}