package duke;

import java.util.List;
import java.util.Scanner;

import duke.userinterface.Parser;
import duke.userinterface.UI;

/**
 * Represents the main entry point for the Duke application.
 */
public class Nelson {

    private final UI voice;
    private final Storage storage;
    private Parser p;

    /**
     * Constructs a Duke instance.
     * Initializes UI, storage, and parser.
     */
    public Nelson() {
        this.voice = new UI();
        this.storage = new Storage();

        // First, preload tasks without readback
        this.p = new Parser(false);
        if (storage.hasData()) {
            List<String> storedInputs = storage.loadAll();
            for (String prev : storedInputs) {
                p.parse(prev);
            }
        }

        // Finally, set parser with requested readback mode
        this.p = new Parser(true);
    }

    /**
     * CLI entry point. Runs Duke in text-based mode.
     */
    public static void main(String[] args) {
        Nelson duke = new Nelson(); // CLI mode with readback
        Scanner sc = new Scanner(System.in);

        duke.voice.welcome();

        boolean isNotFinished = true;
        while (isNotFinished) {
            String input = sc.nextLine();
            duke.storage.storeData(input);
            String response = duke.p.parse(input);
            if (!response.isEmpty()) {
                System.out.println(response);
            }
            if (input.equalsIgnoreCase("bye")) {
                isNotFinished = false;
            }
        }

        sc.close();
    }

    /**
     * Returns Duke's response for the GUI.
     *
     * @param input user input
     * @return Duke's response string
     */
    public String getResponse(String input) {
        storage.storeData(input);
        return p.parse(input);
    }
    public String getWelcomeMessage() {
        return voice.welcomeGui();
    }

    public String getLoadMessage() {
        if (storage.hasData()) {
            return "Loaded previous tasks from storage.";
        } else {
            return "New storage file created.";
        }
    }
}
