package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

public class DebugCommand extends Command {
    /**
     * Constructs a {@link DebugCommand} with the relevant user input.
     *
     * @param input the raw user input that triggered the command
     */
    public DebugCommand(String input) {
        super(input);
    }

    /**
     * Executes the debug command: prints out debug info - java version and file path.
     *
     * @param tasklist the {@link TaskList} where tasks are stored
     * @param ui       the {@link Ui} that the user interacts with
     * @param storage  the {@link Storage} that retrieves and updates the save file
     * @throws Exception
     */
    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        ui.chatbotPrint("java version: " + System.getProperty("java.version"));
        ui.chatbotPrint("filepath: " + storage.getFilePath());

    }

    /**
     * Returns the help message associated with the debug command
     *
     * @return the corresponding help message
     */
    @Override
    public String getHelpMessage() {
        return """
                debug:
                prints out debug info - java version and file path.
                format: debug""";
    }
}
