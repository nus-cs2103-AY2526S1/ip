package mambo;

import mambo.command.Command;
import mambo.parser.Parser;
import mambo.task.TaskList;

/**
 * Represents the Mambo chatbot.
 * Supports functionality for getting a chatbot response.
 *
 * @author kentalim2
 */
public class Mambo {
    private Ui ui;
    private boolean isRunning;
    private TaskList list;
    private TaskListFileManager file;
    private String commandType;

    /**
     * Initializes a new instance of the Mambo chatbot.
     * If there is an existing tasklist that is saved into the system, load that file.
     */
    public Mambo() {
        this.ui = new Ui();
        this.isRunning = true;
        this.file = new TaskListFileManager();
        file.initializeFile();
        list = file.loadFile();
        this.commandType = "";

    }

    public String getCommandType() {
        return this.commandType;
    }

    /**
     * Generates the welcome message to be sent by chatbot.
     *
     * @return Welcome message
     */
    public String getWelcome() {
        return this.ui.sendEntry();
    }

    /**
     * Generates a response for the users input message.
     * If the user's input is an input which is supposed to end the program, it will save the current
     * task list locally then end the program.
     *
     * @param input User input
     * @return Chatbot response
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parseCommand(input);
            String response = c.execute(ui, list, file);
            isRunning = c.isRunning();
            commandType = c.getClass().getSimpleName();

            if (!isRunning) {
                file.saveFile(list);
            }

            return response;

        // catch any exceptions thrown by any of the commands
        } catch (MamboException e) {
            return e.getMessage();
        }
    }
}
