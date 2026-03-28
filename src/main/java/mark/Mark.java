package mark;

import command.Command;
import exceptions.MarkExceptions;
import inputhandler.InputHandler;
import storage.Storage;
import task.TaskList;
import ui.Message;
import ui.Ui;


public class Mark {
    private TaskList taskList;
    private Storage storage;
    private Ui ui;

    public Mark() {
        String path = "./data";
        String file = "./data/Mark.txt";
        ui = new Ui();
        storage = new Storage(path, file);
        taskList = new TaskList(storage.load());
    }

    public void run() {
        Ui.intro();

        boolean isExit = false;
        while (!isExit) {
            String msg = ui.readCommand();
            try {
                Command c = InputHandler.handle(msg, taskList);
                c.executeAndSave(storage);
                isExit = c.isExit();
            } catch (MarkExceptions e) {
                Ui.printError(e.getMessage());
            }
        }
    }

    public Message getResponse(String input) {
        try {
            Command c = InputHandler.handle(input, taskList);

            assert (c != null);

            c.executeAndSave(storage);
            return Ui.getMessage();
        } catch (MarkExceptions e) {
            Ui.printError(e.getMessage());
            return Ui.getMessage();
        }

    }

    public static void main(String[] args) {
        new Mark().run();
    }

}


