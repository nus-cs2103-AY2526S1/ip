package command;

import parser.Parser;
import storage.Storage;
import task.TaskList;
import ui.Ui;

import java.util.Objects;

public class HelpCommand extends Command {
    /**
     * Constructs a {@link HelpCommand} with the relevant user input.
     *
     * @param input the raw user input that triggered the command
     */
    public HelpCommand(String input) {
        super(input);
    }

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        try {
            if (Objects.equals(input, "help")) {
                ui.chatbotPrint(this.getHelpMessage());
            } else {
                String keyword = input.substring(5);
                Command c = Parser.parse(keyword);
                ui.chatbotPrint(c.getHelpMessage());
            }
        } catch (Exception e) {
            ui.chatbotPrint(this.getHelpMessage());
        }
    }

    /**
     * Returns the help message associated with the help command
     *
     * @return the corresponding help message
     */
    @Override
    public String getHelpMessage() {
        return """
                help:
                provides helpful information on the specified command.
                format: help [command]
                
                the possible commands are: list, todo, deadline, event, mark, unmark, delete, find, exit, help""";
    }
}
