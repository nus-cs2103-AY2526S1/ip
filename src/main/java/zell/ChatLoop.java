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
        this.ui = ui;
    }

    public void run() {
        boolean endProgram = false;

        ui.showMessage(ZellMessage.WELCOME_MESSAGE.message());

        while (!endProgram) {
            String userInput = ui.readInput();

            String output;
            try {
                output = parser.parseInput(userInput, taskList, storage);
            } catch (ZellException ze) {
                output = ze.toString();
            }

            ui.showMessage(output);
            endProgram = parser.getEndProgram();
        }
    }
}
