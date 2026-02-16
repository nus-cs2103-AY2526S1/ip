package guibot.command;

import java.io.IOException;

import guibot.Parser;
import guibot.Storage;
import guibot.TaskList;
import guibot.exception.WrongInputFormatException;

/**
 * Command to archive the tasklist into a .txt file
 */
public class ArchiveCommand extends Command {
    private static final String EXPECTED_INPUT_FORMAT = "archive <name of file (without .txt)>";
    private String fileName;
    private String output = "Okay, I've archived your tasks into a file called %s.txt. Starting from an empty TaskList";

    /**
     * Creates an ArchiveCommand
     *
     * @param fileName Name of file to archive tasks into.
     */
    private ArchiveCommand(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Static factory method to construct an ArchiveCommand from an input string.
     *
     * @param input The input string to construct the ArchiveCommand from.
     * @return An ArchiveCommand constructed from the string.
     * @throws WrongInputFormatException If the string is not in the correct format.
     */
    public static ArchiveCommand of(String input) throws WrongInputFormatException {
        try {
            String[] details = Parser.getDetails(input, " ");
            String fileName = details[1].replaceAll("\\s+", "");
            return new ArchiveCommand(fileName);
        } catch (WrongInputFormatException e) {
            throw new WrongInputFormatException(EXPECTED_INPUT_FORMAT);
        }
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws IOException {
        Storage archiveStorage = new Storage(String.format("src/main/data/archive/%s.txt", fileName));
        assert tasks != null : "Cannot archive tasks in a null tasklist";
        archiveStorage.saveTasks(tasks);
        tasks.clear();
        assert storage != null : "Cannot save to a null storage";
        storage.saveTasks(tasks);
        return String.format(output, fileName);
    }
}
