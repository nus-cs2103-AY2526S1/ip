package phuc;

import phuc.parser.ProcessInput;
import phuc.exception.PhucException;
import phuc.ui.UserInterface;

import java.util.Scanner;

/**
 * Main application class for Phuc task manager.
 * Handles the main application loop and user input processing.
 */
public class Phuc {
    private final ProcessInput pi;

    /**
     * Constructs a new Phuc application instance.
     * Initializes the input processor.
     */
    public Phuc() {
        this.pi = new ProcessInput();
    }

    /**
     * Starts the main application loop.
     * Loads saved tasks, processes user input, and handles open, shutdown.
     *
     * @throws PhucException if any error occurs during application execution
     */
    public void run() throws PhucException {
        pi.start();
        pi.load();

        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                String input = sc.nextLine();
                if (input.equals("bye")) {
                    pi.end();
                    break;
                }
                pi.process(input);
            } catch (PhucException e) {
                UserInterface.showError(e.getMessage());
            }
        }
    }
}
