import java.util.Scanner;
import java.io.IOException;

/**
 * Entry point for the Byte chatbot
 */
public class Byte {
    private final Ui ui;
    private final Storage storage;
    private final Parser parser;

    public Byte() {
        this.ui = new Ui();
        this.storage = new Storage();
        
        try {
            storage.load();
        } catch (IOException e) {
            storage.initializeWithTaskList(new TaskList());
            ui.showError("Could not load tasks from file: " + e.getMessage());
        }
        
        this.parser = new Parser(storage, ui);
    }

    public void run() {
        ui.showGreeting();
        Scanner scanner = new Scanner(System.in);
        String command = "";
        
        while (!"bye".equals(command)) {
            command = scanner.nextLine();
            try {
                parser.handle(command);
            } catch (ByteException e) {
                ui.showError(e.getMessage());
            }
        }
        
        scanner.close();
    }

    public static void main(String[] args) {
        new Byte().run();
    }
}
