package Mithrandir;

import static java.lang.Thread.sleep;

import javafx.animation.PauseTransition;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import javafx.util.Duration;

import Mithrandir.storage.FileStorage;
import Mithrandir.ui.Ui;
import Mithrandir.util.CommandParser;

public class Application {
    private final Ui ui = new Ui();
    private final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private final FileStorage fileStorage = new FileStorage("./save/Save.txt", "./save/Archive.txt");
    private TaskList taskList = new TaskList();

    public void loadTaskList() throws Exception{
        this.taskList = fileStorage.loadTaskList();
    }

    /**
     * Starts the application and processes user input.
     *
     * @throws Exception if an I/O error occurs
     * @throws AssertionError if required components are not properly initialized
     */
    public void run() throws Exception {

        ui.greet();
        loadTaskList();
        assert taskList != null : "TaskList must be properly loaded from storage";
        String nextLine = bufferedReader.readLine();
        while (nextLine != null) {
            nextLine = nextLine.trim();
            HashMap<String, String> command = CommandParser.parse(nextLine);
            try {
                Command.valueOf(command.get("command word")).execute(this.ui, this.taskList,
                        command.get("description"), this.fileStorage);
                if (command.get("command word").equals("BYE")) {
                    return;
                }
            } catch (Exception e) {
                if (e.getMessage().contains("No enum constant")) {
                    ui.print("No such command! Supported commands are: TODO, EVENT, DEADLINE, BYE, LIST, MARK, " +
                            "UNMARK, FIND, ARCHIVE");
                } else {
                    ui.print(e.getMessage());
                }
            }
            nextLine = bufferedReader.readLine();
        }
    }

    /**
     * Processes a GUI command and returns the response.
     *
     * @param input the user input to process
     * @return the response to display in the GUI
     * @throws AssertionError if input is null or empty, or if required components are not initialized
     */
    public String getGUiResponse(String input) {
        assert input != null && !input.trim().isEmpty() : "Input cannot be null or empty";
        assert taskList != null : "TaskList must be initialized";

        HashMap<String, String> command = CommandParser.parse(input);
        try {
            if (!command.get("command word").equals("BYE")) {
                return Command.valueOf(command.get("command word")).execute(this.ui, this.taskList,
                        command.get("description"), this.fileStorage);
            }
            PauseTransition delay = new PauseTransition(Duration.millis(3000));
            delay.setOnFinished(event -> javafx.application.Platform.exit());
            delay.play();
            return ui.exit();
        } catch (Exception e) {
            if (e.getMessage().contains("No enum constant")) {
                return ui.print("No such command! Supported commands are: TODO, EVENT, DEADLINE, BYE, LIST, MARK, " +
                        "UNMARK, FIND, ARCHIVE");
            } else {
                return ui.print(e.getMessage());
            }
        }
    }

    public String greet() {
        return this.ui.greet();
    }
}
