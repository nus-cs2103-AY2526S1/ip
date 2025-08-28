import java.io.IOException;
import java.util.ArrayList;
import evansbot.Exceptions.*;
import evansbot.command.Command;
import evansbot.task.Storage;
import evansbot.task.Task;
import evansbot.task.TaskList;
import evansbot.ui.Parser;
import evansbot.ui.Ui;

public class EvansBot {
    private final Storage storage;
    private final Ui ui;
    private TaskList tasks;

    public EvansBot(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            ArrayList<Task> loadedTasks = storage.load(); // evansbot.task.Storage returns ArrayList<evansbot.task.Task>
            tasks = new TaskList(storage, loadedTasks); // wrap in evansbot.task.TaskList
        } catch (IOException e) {
            ui.showError("Could not load save file, starting with empty list.");
            tasks = new TaskList(storage); // empty evansbot.task.TaskList with storage reference
        }
    }

    public void run() {
        ui.greet();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand(); // read user input
                Command command = Parser.parse(fullCommand); // parse into evansbot.command.Command
                command.execute(tasks, ui, storage); // execute command
                isExit = command.isExit(); // check if it was evansbot.command.ExitCommand
            } catch (EvansBotException e) {
                ui.showError(e.getMessage());
            }
        }

        ui.close(); // close scanner
    }

    public static void main(String[] args) {
        new EvansBot("./data/evansbot.txt").run();
    }

}
