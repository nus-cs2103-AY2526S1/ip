package aurora.cli;

import java.util.Scanner;

import aurora.Aurora;
import aurora.ui.Ui;

/**
 * The {@code AuroraCli} class provides a command-line interface (CLI)
 * for interacting with the Aurora chatbot.
 * <p>
 * This class initializes the chatbot in CLI mode.
 * The session ends when the user types the "bye" command.
 * </p>
 */
public class AuroraCli {

    private final Aurora aurora;
    private final Ui ui;

    /**
     * Constructs a new {@code AuroraCli} instance with a default
     * data file path for storing tasks.
     */
    public AuroraCli() {
        this.aurora = new Aurora();
        this.ui = new Ui(new Scanner(System.in));
    }

    /**
     * Entry point for launching the Aurora chatbot in CLI mode.
     *
     * @param args command-line arguments (unused)
     */
    public static void main(String[] args) {
        new AuroraCli().run();
    }

    /**
     * Starts the chatbot in CLI mode.
     */
    public void run() {
        ui.speakIntro();
        String input = ui.readInput();
        while (!input.equalsIgnoreCase("bye")) {
            String response = aurora.getResponse(input);
            ui.speak(response);
            input = ui.readInput();
        }
        ui.speakOutro();
    }
}


