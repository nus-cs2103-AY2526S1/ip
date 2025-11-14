package baymax;

import java.util.Scanner;

import command.Command;
import exception.BaymaxException;
import javafx.application.Platform;
import storage.Storage;
import task.TaskList;
import ui.Parser;
import ui.Ui;


public class Baymax {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    private String commandType;


    public Baymax() {
        ui = new Ui();
        storage = new Storage();
        TaskList loadedTasks;
        try {
            loadedTasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showError("Failed to load tasks!");
            loadedTasks = new TaskList();
        }
        tasks = loadedTasks;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(ui.showWelcome());

        while (true) {
            String input = scanner.nextLine();
            try {
                Command cmd = Parser.parse(input);
                String response = cmd.execute(tasks, ui, storage);
                System.out.println(response);
                if (cmd.isExit()) break;
            } catch (BaymaxException e) {
                System.out.println(ui.showError(e.getMessage()));
            }
        }

    }

    public String getResponse(String input) {
        try {
            Command cmd = Parser.parse(input);
            String response = cmd.execute(tasks, ui, storage);
            commandType = cmd.getClass().getSimpleName();
            if (cmd.isExit()) {
                Platform.exit();
            }
            return response;
        } catch (BaymaxException e) {
            commandType = "";
            return ui.showError(e.getMessage());
        }
    }

    public Ui getUi() {
        return ui;
    }

    public String getCommandType() {
        return commandType;
    }


    public static void main(String[] args) {
        new Baymax().run();
    }
}
