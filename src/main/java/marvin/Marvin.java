package marvin;

import java.util.Scanner;

import marvin.command.Command;
import marvin.command.CommandResult;
import marvin.task.TaskList;
import marvin.ui.Ui;

/**
 * Encapsulates the entrypoint to the Marvin application.
 */
public class Marvin {
    private final TaskList tasks;

    /**
     * Initiates Marvin, loading a task list from storage if applicable.
     */
    public Marvin() {
        this.tasks = StorageHandler.loadTaskList();
    }

    /**
     * Initiates and begins running Marvin.
     */
    public static void main(String[] args) {
        new Marvin().run();
    }

    /**
     * Runs the main loop of Marvin, taking in user input and responding accordingly.
     */
    public void run() {
        Ui.printGreeting(Personality.getGreeting());
        boolean isExit = false;
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            // Attempt to parse command
            String fullCommand;
            try {
                fullCommand = Ui.readCommand(sc);
            } catch (MarvinException e) {
                Ui.printGeneric(e.getMessage());
                continue;
            }

            Command c = Parser.parse(fullCommand);
            CommandResult crs = c.execute(this.tasks);
            crs.printResponse();
            StorageHandler.storeTaskList(this.tasks); // save state
            isExit = c.isExit();
            if (isExit) {
                return;
            }
            Ui.printUserInput();
        }
    }

    /**
     * Generates a response for the user's chat message
     */
    public String getResponse(String input) {
        Command c;
        try {
            c = Parser.parse(input);
        } catch (MarvinException e) {
            return e.getMessage();
        }

        CommandResult crs = c.execute(this.tasks);
        StorageHandler.storeTaskList(this.tasks); // save state
        return crs.getMessage();
    }
}
