package bestie;

/**
 * Entry point of Bestie.
 *
 * <p>The class wires together the user interface, persistent storage, and
 * in-memory task list before handing control over to the {@link Parser} for the
 * command loop.</p>
 */

public class Bestie {
    private final Storage storage;
    private final TaskList tasks;
    private final Parser parser;
    private boolean hasLoadingError;
    private boolean isExit;

    /**
     * Constructs a Bestie instance with persistent storage and task parsing
     * capabilities.
     */
    public Bestie() {
        this.storage = new Storage();
        this.parser = new Parser();
        TaskList loaded;
        try {
            loaded = new TaskList(storage.load());
        } catch (Exception e) {
            loaded = new TaskList();
            this.hasLoadingError = true;
        }
        this.tasks = loaded;
    }

    /**
     * Bootstraps and runs the chatbot.
     *
     * @param args command-line arguments supplied by the user; ignored by Bestie
     */
    public static void main(String[] args) {
        Bestie bestie = new Bestie();
        Ui ui = new Ui();
        if (bestie.hasLoadingError) {
            ui.showLoadingError();
        }

        // Introduction
        ui.showWelcome();

        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readCommand();
                ui.showLine();
                isExit = bestie.processInput(input, ui);
            } catch (BestieException e) {
                ui.showError(e.getMessage());
            } catch (Exception e) {
                ui.showError("Something feels off! Check your input again dear!");
            } finally {
                ui.showLine();
            }
        }
    }

    private boolean processInput(String input, Ui ui) throws BestieException {
        boolean exit = parser.parse(input, tasks, ui, storage);
        this.isExit = exit;
        return exit;
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        GuiUi ui = new GuiUi();
        ui.showLine();
        try {
            processInput(input, ui);
        } catch (BestieException e) {
            ui.showError(e.getMessage());
        } catch (Exception e) {
            ui.showError("Something feels off! Check your input again dear!");
        } finally {
            ui.showLine();
        }
        return ui.flush();
    }

    /**
     * Returns the welcome banner (and loading error, if any) for the GUI to display.
     *
     * @return greeting text mirroring the CLI experience
     */
    public String getGreeting() {
        GuiUi ui = new GuiUi();
        if (hasLoadingError) {
            ui.showLoadingError();
        }
        ui.showWelcome();
        return ui.flush();
    }

    /**
     * Indicates if the last processed command should close the application.
     *
     * @return {@code true} if the exit command was received
     */
    public boolean isExit() {
        return isExit;
    }
}
