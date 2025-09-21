package thopz;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Main class where the application runs
 *
 */


public class Thopz {

    private TaskList ls;
    private final Storage storage;
    private final Ui ui;

    /**
     * Constructs the thopz chatbot which is the main class
     * @param filePath  file to store the data in
     *
     *
     */
    public Thopz(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new Ui();
        try {
            ls = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            ls = new TaskList(new ArrayList<>());
        }
    }

    public static void main(String[] args) {
        new Thopz("TaskStorage.txt").run();
    }

    /**
     * Run command which will call the start of the chatbot
     * will only exit upon bye
     */

    public void run() {
        ui.showGreetings();
        boolean isDone = false;
        while (!isDone) {
            try {
                String fullInput = ui.readInput();
                Command command = Parser.parse(fullInput);
                command.perform(ls, storage, ui);
                isDone = command.isDone();

            } catch (IOException e) {
                e.getMessage();
            } catch (IllegalArgumentException e) {
                ui.showError(e.getMessage());
            } catch (Exception e) {
                ui.showError(e.getMessage());
            }
        }
        ui.showGoodbye();
    }

    /**
     * Get response will call the application to run..
     * @param input
     * @return
     */

    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            String output = command.perform(ls, storage, ui);
            if (command.isDone()) {
                return output + ui.showGoodbye() +"!";
            }
            return output;
        } catch (IOException | IllegalArgumentException e) {
            return e.getMessage();
        } catch (Exception e) {
            return e.getMessage();
        }
    }


}


