package shaniqua;

import shaniqua.command.Command;
import shaniqua.gui.GuiController;
import shaniqua.parser.Parser;
import shaniqua.storage.Storage;
import shaniqua.taskcore.TaskList;
import shaniqua.ui.Ui;

public class Shaniqua {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private GuiController controller;

    /**
     * Constructs Shaniqua object with filepath
     * @param filepath string input of folder path with respect to root
     */
    public Shaniqua(String filepath) {
        this.storage = new Storage(filepath);
        this.ui = new Ui();
        tasks = new TaskList();
        Parser.setUi(ui);
    }

    /**
     * Runs chatbot
     */
    public void handle(String input) {
        boolean isExit = false;
        try {
            Command cmd = Parser.parse(input);
            if (cmd == null) {
                ui.invalidInput();
                return;
            }
            cmd.execute(tasks, ui, storage);
            isExit = cmd.isExit();
        } catch (ShaniquaException e) {
            ui.error(e);
        }
    }

    public Ui getUi() {
        return ui;
    }
}

