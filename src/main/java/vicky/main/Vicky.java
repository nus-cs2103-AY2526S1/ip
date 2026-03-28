package vicky.main;

import java.io.IOException;
import java.util.Scanner;

import vicky.command.Command;
import vicky.exception.InvalidInputException;
import vicky.exception.InvalidTaskException;
import vicky.parser.Parser;
import vicky.storage.Storage;
import vicky.tasklist.TaskList;
import vicky.ui.Ui;

/**
 * Vicky represents the Vicky chatbot
 */
public class Vicky {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean isExit;

    /**
     * Initialises ui and storage variables when chatbot starts.
     */
    public Vicky() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.isExit = false;

        try {
            this.storage.init();
            this.tasks = new TaskList(this.storage.load());
        } catch (IOException e) {
            System.err.println(ui.showLoadingError(e.getMessage()));
            this.tasks = new TaskList();
        }
    }

    /**
     * Runs the main execution flow of the Vicky chatbot.
     */
    public void run() {
        System.out.print(ui.showGreeting());
        Scanner scanner = new Scanner(System.in);
        boolean isExit = false;

        while (!isExit) {
            String fullCommand = scanner.nextLine();
            try {
                Command command = Parser.parse(fullCommand);
                String output = command.execute(tasks, ui, storage);
                System.out.print(output);
                isExit = command.isExit();

            } catch (InvalidInputException e) {
                System.err.print(ui.showError(e.getMessage() + "Please enter a valid command!"));
            } catch (InvalidTaskException e) {
                System.err.print(ui.showError(e.getMessage()));
            } catch (Exception e) {
                System.err.print(ui.showError(e.getMessage()));
            }
        }
    }

    public String getGreeting() {
        return this.ui.showGreeting();
    }

    /** Returns a response based on user's input.
     *
     * @param input
     * @return
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            String output = command.execute(tasks, ui, storage);
            System.out.print(output);
            this.isExit = command.isExit();
            return output;

        } catch (InvalidInputException e) {
            return ui.showError(e.getMessage());
        } catch (InvalidTaskException e) {
            return ui.showError(e.getMessage());
        } catch (Exception e) {
            return ui.showError(e.getMessage());
        }
    }

    public boolean isExit() {
        return this.isExit;
    }

}


