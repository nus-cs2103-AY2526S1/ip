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

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) throws Exception {
        ui.chatbotPrint("java version: " + System.getProperty("java.version"));
        ui.chatbotPrint("filepath: " + storage.getFilePath());

    }

    @Override
    public String getHelpMessage() {
        return """
                debug:
                prints out debug info - java version and file path.
                format: debug""";
    }
}
