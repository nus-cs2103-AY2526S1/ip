package guibot.command;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import guibot.Parser;
import guibot.Storage;
import guibot.TaskList;
import guibot.exception.FileNotFoundException;
import guibot.exception.GuibotException;
import guibot.exception.WrongInputFormatException;

/**
 * Command to archive the tasklist into a .txt file
 */
public class LoadCommand extends Command {
    private static final String EXPECTED_INPUT_FORMAT = "Load <name of file (without .txt)>";
    private static final Path ARCHIVE_PATH = Paths.get("src/main/data/archive");
    private String fileName;
    private String output = "Okay, I've loaded the tasks from %s and deleted it. Your tasks are:\n%s";

    /**
     * Creates a LoadCommand
     *
     * @param fileName Name of file to load tasks from.
     */
    private LoadCommand(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Static factory method to construct a LoadCommand from an input string.
     *
     * @param input The input string to construct the LoadCommand from.
     * @return A LoadCommand constructed from the string.
     * @throws WrongInputFormatException If the string is not in the correct format.
     */
    public static LoadCommand of(String input) throws WrongInputFormatException, FileNotFoundException, IOException {
        try {
            String[] fileNames = Files.list(ARCHIVE_PATH)
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .toArray(size -> new String[size]);
            String[] details = Parser.getDetails(input, " ");
            String fileName = details[1].replaceAll("\\s+", "");
            if (!Arrays.asList(fileNames).contains(fileName + ".txt")) {
                throw new FileNotFoundException(fileNames);
            }
            return new LoadCommand(fileName);
        } catch (WrongInputFormatException e) {
            throw new WrongInputFormatException(EXPECTED_INPUT_FORMAT);
        }
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws IOException, GuibotException {
        Storage archiveStorage = new Storage(String.format("src/main/data/archive/%s.txt", fileName));
        assert tasks != null : "Cannot load tasks into a null tasklist";
        tasks.clear();
        archiveStorage.loadTasks(tasks);
        assert storage != null : "Cannot save to a null storage";
        storage.saveTasks(tasks);
        archiveStorage.deleteFile();
        return String.format(output, fileName, tasks.toString());
    }
}
