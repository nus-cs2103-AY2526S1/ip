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

    /**
     * Overloaded constructor taking in no arguments
     */
    public GoksChat() {
        this("src/data/gokschat.txt");
    }

    public static void main(String[] args) {
        System.out.println("Hello!");
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            Command c = inputProcessor.processInput(input);
            String s = c.execute();
            storage.updateFile(listOfTasks);
            return s;
        } catch (TodoException e) {
            return ui.exceptionMessage(e);
        } catch (InvalidPromptException e) {
            return ui.exceptionMessage(e);
        } catch (DeadlineException e) {
            return ui.exceptionMessage(e);
        } catch (BadFileException e) {
            return ui.exceptionMessage(e);
        }
    }
}