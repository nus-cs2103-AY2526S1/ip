package ackermann;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import ackermann.exceptions.CheckedException;
import ackermann.functions.Parser;
import ackermann.functions.Storage;
import ackermann.functions.TaskList;
import ackermann.functions.Ui;

/**
 * Main program logic for chatbot
 */
public class Ackermann {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    /**
     * Constructor for chatbot
     * @param filePath filepath of saved tasks
     */
    public Ackermann(Path filePath) {
        assert filePath != null : "FilePath is null";
        this.storage = new Storage(filePath);

        try {
            this.tasks = storage.load();
            this.ui = new Ui(this.tasks);
        } catch (CheckedException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Gets a response for GUI to output
     * @param input Query for bot to process
     * @return A string for GUI to output
     */
    public String getResponse(String input) {
        Parser parser = new Parser(this.tasks);

        try {
            String result = parser.parse(input);
            this.storage.save(tasks);
            return result;
        } catch (CheckedException e) {
            return e.getMessage();
        }
    }

    /**
     * Runs the chatbot
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean isRun = true;

        this.ui.startup();

        while (isRun) {
            try {
                String input = scanner.nextLine();
                ui.printline();
                isRun = ui.isRun(input);
                this.storage.save(tasks);
                if (isRun) {
                    ui.printline();
                }
            } catch (CheckedException e) {
                ui.printException(e.getMessage());
            }
        }
        ui.end();
    }

    public static void main(String[] args) {
        new Ackermann(Paths.get(".", "tasks.txt")).run();
    }
}
