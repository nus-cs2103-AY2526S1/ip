package abang;

import abang.ui.UI;
import abang.storage.Storage;
import abang.task.TaskList;
import abang.command.Command;
import abang.parser.Parser;
import abang.exception.AbangException;

public class Abang {
    private Storage storage;
    private TaskList taskList;
    private UI ui;

    public Abang(String filePath) {
        ui = new UI();
        storage = new Storage();
        try {
            taskList = storage.load();
        } catch (Exception e) {
            taskList = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String Command = ui.readCommand();
                Command c = Parser.parse(Command);
                String result = c.execute(taskList, ui, storage);
                System.out.println(result);
                isExit = c.isExit();
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(taskList, ui, storage);
        } catch (AbangException e) {
            return "Error: " + e.getMessage();
        }
    }

    public static void main(String[] args) {
        new Abang("data/tasks.txt").run();
    }
}