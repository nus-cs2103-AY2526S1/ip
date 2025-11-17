package bin.ui;


import bin.exception.BinException;
import bin.parser.Parser;
import bin.storage.Storage;
import bin.task.TaskList;

public class Bin {
    private Storage storage;
    private TaskList tasks;

    public Bin(String filePath) {
        assert filePath != null : "File path must not be null";
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadingTasks());
        } catch (BinException e) {
            tasks = new TaskList();
        }
    }

    public String getResponse(String command) {
        assert tasks != null : "Task list should have been initialized";
        assert command != null : "Command input should not be null";
        Parser parser = new Parser(command, this.tasks);
        storage.save(tasks);
        return parser.parseCommand();
    }
}
