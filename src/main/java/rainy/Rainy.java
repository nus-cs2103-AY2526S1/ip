package rainy;

import commands.Command;
import exception.RainyException;
import parser.Parser;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

public class Rainy {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Rainy(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (RainyException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.close();
    }

    public static void main(String[] args) {
        new Rainy("data/rainy.txt").run();
    }
}