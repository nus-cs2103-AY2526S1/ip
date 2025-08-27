package beebong;

import java.util.Scanner;

// Import Commands
import beebong.command.Command;
// Import Exceptions
import beebong.exception.BBongException;
// Import Parser
import beebong.parser.Parser;
// Import Storage
import beebong.storage.Storage;
// Import Tasks
import beebong.task.TaskList;
// Import UI
import beebong.ui.UI;

public class BeeBong {
    private TaskList taskList;
    private final Storage storage;
    private final UI ui;
    private final Parser parser;

    public BeeBong() {
        this.ui = new UI();
        this.storage = new Storage("bbongSave.txt");
        this.parser = new Parser();

        // Check for Saved Data
        try {
            this.taskList = new TaskList(this.storage.readTasksFromFile());
            this.ui.showMessage("Bing! Saved Tasks found, loading saved tasks...");
        } catch (BBongException e) {
            this.ui.showErrorMessage(e.getMessage());
            this.taskList = new TaskList();
        }
    }

    public void start() {
        this.ui.showGreetingMessage();
        this.ui.showCommands();

        Scanner s = new Scanner(System.in);

        while (true) {
            // Ask for user input
            System.out.print(">>> ");
            System.out.flush();

            // For EOF error when doing testing
            if (!s.hasNextLine()) {
                break;
            }

            // Process user Input
            String input = s.nextLine().trim();

            try {
                Command command = parser.parseCommand(input);
                command.execute(this.taskList, this.ui, this.storage);
                if (command.isExit()) {
                    break;
                }
            } catch (BBongException e) {
                this.ui.showErrorMessage(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new BeeBong().start();
    }
}
