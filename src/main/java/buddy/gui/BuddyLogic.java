package buddy.gui;

import buddy.exception.BuddyException;
import buddy.model.TaskList;
import buddy.parser.Parser;
import buddy.storage.Storage;

/**
 * Entry point for the GUI version of Buddy. Logic is similar to Buddy.
 * Avoided modifying Buddy to retain the CLI version.
 */

public class BuddyLogic {
    private final Storage storage;
    private final TaskList tasks;
    private boolean lastExit = false;

    public BuddyLogic() {
        this.storage = new Storage("data/tasks.txt");
        this.tasks = new TaskList(storage.load());
    }

    /** Lets the controller know when to exit */
    public boolean shouldExit() {
        return lastExit;
    }

    /** Returns Buddy's textual reply to a user input. */
    public String getResponse(String input) {
        lastExit = false;
        UiLogic ui = new UiLogic();
        try {
            lastExit = Parser.handle(input, tasks, ui, storage);
        } catch (BuddyException | IllegalArgumentException e) {
            ui.showError(e.getMessage());
        }
        String out = ui.getMessage();
        return out.isBlank() ? "Please type in a message" : out;
    }
}
