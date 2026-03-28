package Meal;

import java.util.Scanner;

public class Meal {
    private static final String SEP = "____________________________________________________________";
    private final Scanner sc = new Scanner(System.in);
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initialises a new meal application with the given storage filepath
     * @param filePath the file path where the tasks are stored
     */
    public Meal(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (MealException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main loop of the app
     */
    public void run() {
        ui.showWelcome();
        boolean shouldExit = false;

        while (!shouldExit) {
            try {
                String line = ui.readCommand();
                Command cmd = Parser.parse(line);
                shouldExit = cmd.execute(tasks, ui, storage);
            } catch (Exception e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Meal("data/mealapp.txt").run();
    }

}
