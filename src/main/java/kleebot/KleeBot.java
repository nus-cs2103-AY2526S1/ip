package kleebot;

import kleebot.command.Command;
import kleebot.storage.Storage;
import kleebot.task.TaskList;
import kleebot.ui.KleeExceptions;
import kleebot.ui.Parser;
import kleebot.ui.Ui;

import java.util.Scanner;

import java.io.FileNotFoundException;

/**
 * Represents a chatbot called KleeBot for all your task management needs.
 */
public class KleeBot {

    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    public KleeBot(String filePath) {
        assert filePath != null : "File path should not be null";
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
//            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.greet();
        Scanner textScanner = new Scanner(System.in);
        boolean isExit = false;
        loop: while (!isExit) {
            try {
                ui.showLine();

                String userInput = textScanner.nextLine();
                Command c = Parser.parse(userInput);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (KleeExceptions e) {
                System.out.println(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
//        ui.exit();
        // Saves files to local after program ends
        storage.saveTasksToLocal(tasks.getTasks());

    }

    /**
     * Returns a string literal for the GUI chatbot.
     *
     * @param response The user input to be parsed by KleeBot.
     */
    public String getResponse(String response) {
        try {
            Command c = Parser.parse(response);
            String kleeText = c.executeGUI(tasks, ui, storage);
            assert !kleeText.isEmpty();
            return kleeText;
        } catch (KleeExceptions e) {
            return e.getMessage();
        }
    }



    public static void main(String[] args) {
        // Initialise the klee
        new KleeBot("KleeData/taskFile").run();

    }
}