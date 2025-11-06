package bestbot;

import bestbot.command.Command;
import bestbot.task.TaskList;
import bestbot.exception.BestbotException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Entry point of the Bestbot application.
 * Handles setup, main loop, and delegates commands.
 */
public class Bestbot {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Creates a Bestbot instance with storage file.
     *
     * @param filePath Path to storage file.
     */
    public Bestbot(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);

        TaskList loadedTasks;
        try {
            loadedTasks = new TaskList(storage.load());
        } catch (BestbotException e) {
            loadedTasks = new TaskList();
        }

        this.tasks = loadedTasks;
    }

    /**
     * Starts the interactive loop to process user commands.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();

            } catch (BestbotException e) {
                ui.showError(e.getMessage()); // centralized error handling

            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Program entry point.
     */
    public static void main(String[] args) {
        String filePath = "bestbot.txt";

        Bestbot bot = new Bestbot(filePath);

        if (args.length == 0) {
            // Interactive mode
            bot.run();
        } else if (args.length == 1) {
            // Just storage file, still interactive
            bot.run();
        } else if (args.length == 2) {
            // Test mode: redirect System.in to file
            try {
                java.io.InputStream inputFile = new java.io.FileInputStream(args[1]);
                System.setIn(inputFile);
                bot.run();
            } catch (java.io.FileNotFoundException e) {
                System.err.println("Input file not found: " + args[1]);
            }
        } else {
            System.err.println("Usage: ./gradlew run [storageFile [inputFile]]");
        }
    }


    /**
     * Generates a response string for a given input, without printing to console.
     *
     * @param input user input command
     * @return Bestbot's response as a string
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintStream ps = new PrintStream(baos);
            Ui tempUi = new Ui(ps);                 // Use temporary Ui that writes to ByteArrayOutputStream
            c.execute(tasks, tempUi, storage);
            return baos.toString().trim();

        } catch (BestbotException e) {
            return e.getMessage();
        }
    }

    public void runFromFile(String inputFilePath) {
        ui.showWelcome();
        boolean isExit = false;

        try (Scanner scanner = new Scanner(new File(inputFilePath))) {
            while (!isExit && scanner.hasNextLine()) {
                String fullCommand = scanner.nextLine();
                ui.showLine();
                try {
                    Command c = Parser.parse(fullCommand);
                    c.execute(tasks, ui, storage);
                    isExit = c.isExit();
                } catch (BestbotException e) {
                    ui.showError(e.getMessage());
                } finally {
                    ui.showLine();
                }
            }
        } catch (FileNotFoundException e) {
            ui.showError("Input file not found: " + inputFilePath);
        }
    }

}
