import java.util.ArrayList;
import java.util.List;

/// Skeletal Version of GoksChat
///
/// @author Ravichandran Gokul
public class GoksChat {
    private Ui ui;
    private Storage storage;
    private InputProcessor inputProcessor;
    private List<Task> listOfTasks;

    public GoksChat(String filePath) throws BadFileException {
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

    public static void main(String[] args) throws InvalidPromptException, TodoException, BadFileException {
        GoksChat goks = new GoksChat("src/data/gokschat.txt");
        goks.run();
    }
}
