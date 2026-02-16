package talkgpt;

import talkgpt.command.Command;
import talkgpt.parser.Parser;
import talkgpt.storage.Storage;
import talkgpt.tasklist.TaskList;
import talkgpt.ui.Ui;

/**
 * Main class for the TalkGPT application.
 * Handles initialization, user interaction, and command execution.
 */
public class Talkgpt {
    private Storage storage;
    private Ui ui;
    private Parser parser;
    private TaskList list;

    /**
     * Constructs a TalkGPT instance with the specified storage file path.
     *
     * @param path The file path for storing and loading tasks.
     */
    public Talkgpt(String path) {
        this.storage = new Storage(path);
        this.ui = new Ui();
        this.parser = new Parser();
        this.list = storage.load();
    }

    /**
     * Processes user input and returns the response as a String.
     *
     * @param input The user's input command.
     * @return The response after executing the command.
     */
    public String getResponse(String input) {
        try {
            Command c = parser.parse(input);
            return c.execute(list, ui, storage);
        } catch (TalkgptException e) {
            return e.getMessage();
        }
    }

    public static void main(String[] args) {}
}
