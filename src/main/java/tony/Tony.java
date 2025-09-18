package tony;

import tony.commands.Command;
import tony.exceptions.TonyException;
import tony.parsers.Parser;
import tony.storage.Storage;
import tony.tasks.TaskList;
import tony.ui.UI;

/**
 * Represents the Tony chatbot which contains the {@link Storage} file, {@link TaskList} and {@link UI}.
 */
public class Tony {
    private final Storage storage;
    private final TaskList tasks;
    private final UI ui;

    /**
     * Constructs the Tony chatbot.
     */
    public Tony() {
        this.storage = new Storage("./data/tasks.txt");
        this.tasks = this.storage.load();
        this.ui = new UI();
    }

    public String getGreeting() {
        return this.ui.greeting();
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (TonyException e) {
            return this.ui.showError(e.getMessage());
        }
    }
}
