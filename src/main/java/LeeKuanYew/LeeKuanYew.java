package LeeKuanYew;

import LeeKuanYew.Task.TaskList;
import LeeKuanYew.Command.*;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;


public class LeeKuanYew {

    private Storage storage;
    private Ui ui;
    private ChatBot chatBot;
    private TaskList taskList;

    public LeeKuanYew(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new Ui();
        this.chatBot = new ChatBot();

        try {
            this.taskList = storage.load();
        } catch (IOException e) {
            this.taskList = new TaskList();
        }
    }

    /**
     * Runs the main program loop.
     *
     * Displays the intro message, repeatedly reads user input, parses it into a
     * Command, executes it, and checks whether to exit. Handles exceptions by
     * showing error messages through the UI.
     */
    public void run() {
        ui.showIntroMessage();
        boolean exit = false;

        while (!exit) {
            try {
                String input = ui.getInput();
                Command c = chatBot.parseCommand(input);
                c.execute(taskList, ui, storage);
                exit = c.checkExit();
            } catch (Exception e) {
                ui.showMessage(e.getMessage());
            }
        }
    }

    public String getResponse(String input) {
        try {
            Command c = chatBot.parseCommand(input);
            return c.execute(taskList, ui, storage);
        } catch (Exception e) {
            return ui.showMessage(e.getMessage());
        }
    }


    public static void main(String[] args) {
        new LeeKuanYew("data/LeeKuanYew.txt").run();
    }
}