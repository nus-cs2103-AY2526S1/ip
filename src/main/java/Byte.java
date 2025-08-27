import java.io.IOException;

/**
 * Entry point for the Byte chatbot
 */
public class Byte {
    private final Ui ui;
    private final Storage storage;
    

    public Byte() {
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
        new Byte().run();
    }
}
