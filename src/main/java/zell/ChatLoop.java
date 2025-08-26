package zell;

import zell.storage.Storage;
import zell.task.TaskList;
import zell.ui.Ui;
import zell.exception.ZellException;

public class ChatLoop {
    private final TaskList taskList;
    private final Parser parser;
    private final Storage storage;
    private final Ui ui;

    public ChatLoop(TaskList taskList, Storage storage, Ui ui) {
        this.taskList = taskList;
        this.parser = new Parser();
        this.storage = storage;
        this.ui = new Ui();
    }

    public void run() {
        boolean endProgram = false;

        ui.showMessage(ZellMessage.WELCOME_MESSAGE.message());

        while (!endProgram) {
            String userInput = ui.readInput();

            try {
                String output = parser.parseInput(userInput, taskList, storage);
                ui.showMessage(output);
            } catch (ZellException ze) {
                ui.showError(ze.toString());
            }

            endProgram = parser.getEndProgram();
        }
    }
}
