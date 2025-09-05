package lynx.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import objectclasses.exception.LynxException;

/**
 * Contains methods to read and write data to a filepath.
 */
public class LynxFileManager {

    private final Path filePath;

    public LynxFileManager(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    /**
     * Creates the directory and file as specified by the filepath.
     * <p>
     * Skips if directory / file already exists.
     * @throws LynxException If file cannot be located or created.
     */
    public void createFile() throws LynxException {
        try {
            // Ensure directory exists
            if (!Files.exists(filePath.getParent())) {
                Files.createDirectories(filePath.getParent());
            }
            // Create the file if it doesn't exist
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
        } catch (IOException e) {
            throw new LynxException("⚠️ Warning: Lynx couldn't set up your data file!\n"
                    + "Details: " + e.getMessage() + "\n"
                    + "Your tasks may not be saved. Please check your file permissions or disk space.");
        }
    }

    /**
     * Reads all lines from the data file.
     *
     * @return File contents as list of strings.
     * @throws LynxException If file cannot be read.
     */
    public List<String> readFromFile() throws LynxException {
        try {
            return Files.readAllLines(filePath);
        } catch (IOException e) {
            throw new LynxException("⚠️ Oops! Lynx couldn't read your data file.\n"
                    + "Details: " + e.getMessage() + "\n"
                    + "Your tasks could not be loaded. Starting with an empty list.");
        }
    }

    /**
     * Writes to the data file.
     *
     * @param text Lines of text to be written.
     * @throws LynxException If file cannot be written.
     */
    public void writeToFile(List<String> text) throws LynxException {
        try {
            Files.write(filePath, text);
        } catch (IOException e) {
            throw new LynxException("⚠️ Oops! Lynx couldn't save your tasks.\n"
                    + "Details: " + e.getMessage() + "\n"
                    + "Any changes made during this session may not be saved.");
        }
    }

}

