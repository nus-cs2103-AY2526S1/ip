package benn;

import java.io.IOException;

import benn.commands.Command;
import benn.commands.InitializationCommand;
import benn.exceptions.BennException;
import benn.parser.Parser;
import benn.tasks.TaskManager;

/**
 * Represents a benn.
 */
public class Benn {
    private TaskManager taskManager;
    private boolean shouldExit;

    /**
     * Initializes the logic unit behind benn.
     */
    public Benn() {
        try {
            this.taskManager = new TaskManager();
        } catch (BennException | IOException exception) {
            System.out.println(exception.getMessage());
            this.taskManager = null;
        }
    }

    /**
     * Returns the response after executing the command
     * associated with the user input.
     *
     * @param input User input
     * @return Response from execution of the command
     */
    public String getResponse(String input) {
        Command command = Parser.parse(input);
        String response = command.execute(taskManager);
        this.shouldExit = command.shouldExit();
        return response;
    }

    public boolean isExit() {
        return shouldExit;
    }

    /**
     * Returns the message for when benn welcomes the user.
     * @return Welcome message in String.
     */
    public String getWelcome() {
        Command command = new InitializationCommand("");
        return command.execute(taskManager);
    }
}