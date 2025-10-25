package dwight.storage;

import dwight.task.DuplicateTaskException;
import dwight.task.Task;
import dwight.task.TaskList;
import dwight.task.TaskParser;
import dwight.task.TaskParserFactory;
import dwight.task.UnknownTaskTypeException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Handles reading from and writing to the save file that stores tasks. Provides methods to load
 * tasks into a {@link TaskList} and to save tasks back to storage.
 */
public class Storage {

    private static final String TASK_MARKED = "1";

    /** The path to the save file used for persistence. */
    private final String filepath;

    /**
     * Creates a new {@code Storage} instance with the specified file path.
     *
     * @param filepath The file path where tasks will be saved and loaded from.
     */
    public Storage(String filepath) {
        assert filepath != null && !filepath.trim().isEmpty() : "Filepath cannot be null or empty.";

        this.filepath = filepath;
    }

    /**
     * Parses a single line of text from the save file into a {@link Task}.
     *
     * @param line The line from the save file.
     * @return A {@code Task} parsed from the line.
     * @throws CorruptSaveException If the line is malformed or incomplete.
     * @throws UnknownTaskTypeException If the task type identifier is not recognized.
     */
    public Task parseFromLine(String line) throws CorruptSaveException, UnknownTaskTypeException {
        assert line != null : "Line to parse cannot be null.";

        String trimmedLine = line.trim();

        String[] parts = trimmedLine.split("\\|", 3);

        if (parts.length < 3) {
            throw new CorruptSaveException(line);
        }

        String type = parts[0].trim();
        TaskParser parser = TaskParserFactory.createFileParser(type);

        String description = parts[2].trim();
        Task ret = parser.parseFromFile(description);

        assert ret != null : "TaskParser returned a null task.";

        boolean isMarked = parts[1].trim().equals(TASK_MARKED);
        if (isMarked) {
            ret.mark();
        }

        return ret;
    }

    /**
     * Loads tasks from the save file into a new {@link TaskList}. If the file does not exist, it is
     * created and an empty list is returned.
     *
     * @return A {@code TaskList} populated with tasks from the save file.
     * @throws IOException If an I/O error occurs when accessing the file.
     */
    public TaskList load() throws IOException {
        TaskList tasks = new TaskList();
        File file = new File(filepath);

        assert file != null : "File object creation failed.";

        if (!file.exists()) {
            return tasks;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    tasks.add(parseFromLine(line));
                } catch (CorruptSaveException
                        | UnknownTaskTypeException
                        | DuplicateTaskException e) {
                    System.out.println("Oh no! " + e.getMessage());
                }
            }
        }

        assert tasks != null : "TaskList loading resulted in a null object.";

        return tasks;
    }

    /**
     * Saves the given {@link TaskList} to the save file. Creates the file and directories if they
     * do not already exist.
     *
     * @param list The task list to save.
     * @throws IOException If an I/O error occurs when writing to the file.
     */
    public void save(TaskList list) throws IOException {
        assert list != null : "TaskList to save cannot be null.";

        File file = new File(filepath);

        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath, false))) {
            String content = list.serialize();

            assert content != null : "TaskList serialization resulted in a null string.";

            writer.write(content);
            System.out.println("Autosaved to " + filepath);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
