package commands.others;

import java.io.InputStream;
import java.util.Scanner;

import commands.Command;
import commands.CommandsEnum;
import ineffaexceptions.IneffaException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Gets text from help file and prints it out.
 */
public class HelpCommand extends Command {
    private final String mainPath = "help/";
    private String fileName = "help.txt";

    /** Instantiates super class */
    public HelpCommand(String commandType) {
        super(false, CommandsEnum.HELP);
        if (!commandType.isEmpty()) {
            this.fileName = commandType + ".txt";
        }
    }

    /**
     * Prints help.txt text to UI
     *
     * @return All text in help.txt.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IneffaException {
        String path = this.mainPath + this.fileName;
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(path);

        if (inputStream == null) {
            throw new IneffaException("Error: cannot find help file in classpath: " + path);
        }

        Scanner scanner = new Scanner(inputStream);
        StringBuilder message = new StringBuilder();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            System.out.println(line);
            message.append(line).append("\n");
        }

        return message.toString();
    }
}
