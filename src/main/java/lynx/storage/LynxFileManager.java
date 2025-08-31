package lynx.storage;

import objectclasses.exception.LynxException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Contains methods to read and write data to the drive.
 */
public abstract class LynxFileManager {

    // Specified path where the data file should be created
    private static final Path FILEPATH = Paths.get("./data/log.txt");

    /**
     * Creates a directory <code>data</code> and a file <code>log.txt</code>.
     * <p>
     * Skips if directory / file already exists.
     * @throws LynxException If file cannot be created or found.
     */
    public static void createFile() throws LynxException {
        try {
            // Ensure directory exists
            if (!Files.exists(FILEPATH.getParent())) {
                Files.createDirectories(FILEPATH.getParent());
            }
            // Create the file if it doesn't exist
            if (!Files.exists(FILEPATH)) {
                Files.createFile(FILEPATH);
            }
        } catch (IOException e) {
            throw new LynxException("⚠️ Warning: Lynx couldn't set up your data file!\n"
                    + "Details: " + e.getMessage() + "\n"
                    + "Your tasks may not be saved. Please check your file permissions or disk space.");
        }
    }

    /**
     * Reads all lines from the file <code>log.txt</code>.
     *
     * @return File contents as list of strings.
     * @throws LynxException If file cannot be read.
     */
    public static List<String> readFromFile() throws LynxException {
        try {
            return Files.readAllLines(FILEPATH);
        } catch (IOException e) {
            throw new LynxException("⚠️ Oops! Lynx couldn't read your data file.\n"
                    + "Details: " + e.getMessage() + "\n"
                    + "Your tasks could not be loaded. Starting with an empty list.");
        }
    }

    /**
     * Writes to the file <code>log.txt</code>.
     *
     * @param text Lines of text to be written.
     * @throws LynxException If file cannot be written.
     */
    public static void writeToFile(List<String> text) throws LynxException {
        try {
            Files.write(FILEPATH, text);
        } catch (IOException e) {
            throw new LynxException("⚠️ Oops! Lynx couldn't save your tasks.\n"
                    + "Details: " + e.getMessage() + "\n"
                    + "Any changes made during this session may not be saved.");
        }
    }

}

