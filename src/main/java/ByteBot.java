package bytebot;

import bytebot.ui.Ui;
import bytebot.storage.Storage;
import bytebot.parser.Parser;
import bytebot.command.Command;
import bytebot.task.TaskList;
import bytebot.ByteException;
import java.io.IOException;

/**
 * Entry point for the ByteBot
 */
public class ByteBot {
    private final Ui ui;
    private final Storage storage;
    

    public ByteBot() {
        this.ui = new Ui();
        this.storage = new Storage();
        
        try {
            storage.load();
        } catch (IOException e) {
            storage.initializeWithTaskList(new TaskList());
            ui.showError("Could not load tasks from file: " + e.getMessage());
        }
        
    }

    public void run() {
        ui.showGreeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(ui, storage);
                isExit = c.isExit();
            } catch (ByteException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.closeScanner();
    }

    public static void main(String[] args) {
        new ByteBot().run();
    }
}
