package Coffee;

/**
 * Represents the main entry point of the Coffee task management application.
 * Initializes the user interface, storage, and task list, and manages
 * the execution of user commands.
 */
public class Coffee {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public String greet() {
        return "Hi! I’m Coffee ☕ — what can I do for you? (try: todo, deadline, list, find, bye)";
    }

    /**
     * Constructs a Coffee application instance with the given file path for storage.
     * Initializes the UI, loads tasks from storage, and displays a welcome message.
     *
     * @param filePath File path used for storing and loading tasks.
     */
    public Coffee(String filePath) {
        String coffee =
                "  ____       __  __\n" +
                        " / ___|___  / _|/ _| ___  ___\n" +
                        "| |   / _ \\| |_| |_ / _ \\/ _ \\\n" +
                        "| |__| (_) |  _|  _|  __/  __/\n" +
                        " \\____\\___/|_| |_|  \\___|\\___|\n";

        System.out.println(coffee);
        System.out.println("Hello! I'm Coffee.Coffee.\n");
        System.out.println("What can I do for you?\n");
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Coffee application loop.
     * Continuously reads user commands, executes them, and handles exceptions
     * until an exit command is issued.
     */
    public void run() {
        while (true) {
            String full = ui.readCommand();
            try {
                Command c = Parser.parseCommand(full);
                c.execute(tasks, ui, storage);
                if (c.isExit()) {
                    break;
                }
            } catch (IllegalArgumentException e) {
                ui.showMessage(e.getMessage());
            } catch (Exception e) {
                ui.showMessage("Unexpected error: " + e.getMessage());
            }
            LineBreak.printLineBreak();
        }
    }

    /**
     * Main entry point of the Coffee application.
     * Creates a Coffee instance with default storage path and runs it.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        new Coffee("data/tasks.txt").run();
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        java.io.PrintStream originalOut = System.out;
        java.io.ByteArrayOutputStream buf = new java.io.ByteArrayOutputStream();
        java.io.PrintStream capture = new java.io.PrintStream(buf, true, java.nio.charset.StandardCharsets.UTF_8);
        System.setOut(capture);
        try {
            Command c = Parser.parseCommand(input);
            // run one command using your existing pipeline
            c.execute(tasks, ui, storage);
            String out = buf.toString(java.nio.charset.StandardCharsets.UTF_8);
            if (c.isExit()) {
                javafx.application.Platform.exit();
            }
                return out.isBlank() ? "(no output)" : out.strip();
            } catch (IllegalArgumentException e) {
                return e.getMessage();
            } catch (Exception e) {
                return "Unexpected error: " + e.getMessage();
            } finally {
                System.setOut(originalOut);
                try {
                    capture.close();
                } catch (Exception ignore) {

                }
                try {
                    buf.close();
                } catch (Exception ignore) {

                }
            }
    }
}
