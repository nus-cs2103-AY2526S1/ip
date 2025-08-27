package zell;

import zell.storage.Storage;
import zell.task.TaskList;
import zell.ui.Ui;
import zell.exception.ZellException;

/**
 * Deals with the chat loop of the Zell chatbot
 */
public class ChatLoop {
    /** The TaskList object used to store tasks */
    private final TaskList taskList;

    /** The Parser object used to parse inputs */
    private final Parser parser;

    /** The Storage object used for local storage of tasks */
    private final Storage storage;

    /** The Ui object used to display messages to the user */
    private final Ui ui;

    public ChatLoop(TaskList taskList, Storage storage, Ui ui) {
        this.taskList = taskList;
        this.parser = new Parser();
        this.storage = storage;
        this.ui = ui;
    }

    /**
     * Starts up the chat loop for the Zell chatbot.
     * Here we have a local variable {@code endProgram} used by the while loop to check whether
     * we should terminate the loop and end the program.
     * <p>
     * We use {@link zell.ui.Ui} to read the user input and display the welcome message and each
     * message related to the user's input/command.
     * </p>
     * <p>
     * We use {@link zell.Parser} to parse the user's input and execute the commands.
     * </p>
     */
    public void run() {
        boolean endProgram = false;

        ui.showMessage(ZellMessage.WELCOME_MESSAGE.message());

        while (!endProgram) {
            String userInput = ui.readInput();

            String output;
            try {
                output = parser.parseInput(userInput, taskList, storage);
            } catch (ZellException ze) {
                output = ze.toString();
            }

            ui.showMessage(output);
            endProgram = parser.getEndProgram();
        }
    }
}
