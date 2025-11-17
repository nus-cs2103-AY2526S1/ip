package bara;

import command.Command;
import parser.Parser;
import storage.Storage;
import tasklist.TaskList;
import ui.UI;

public class Bara {
    private static TaskList taskList = new TaskList();

    public Bara() {
        Storage.initialize();
        taskList.loadFromStorage();
    }
    public String run(String input) {
        try {
            Command command = Parser.parse(input, taskList);
            return command.execute(taskList);
        } catch (Exception e) {
            return UI.showMessage("An unexpected error occurred: " + e.getMessage());
        }

    }

}
