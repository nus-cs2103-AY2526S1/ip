package lynx.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import objectclasses.exception.LynxFileException;

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
     * @throws LynxFileException If file cannot be located or created.
     */
    public void createFile() throws LynxFileException {
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
            throw LynxFileException.createError(e.getMessage());
        }
    }

    /**
     * Reads all lines from the data file.
     *
     * @return File contents as list of strings.
     * @throws LynxFileException If file cannot be read.
     */
    public List<String> readFromFile() throws LynxFileException {
        try {
            return Files.readAllLines(filePath);
        } catch (IOException e) {
            throw LynxFileException.readError(e.getMessage());
        }
    }

    /**
     * Writes to the data file.
     *
     * @param text Lines of text to be written.
     * @throws LynxFileException If file cannot be written.
     */
    public void writeToFile(List<String> text) throws LynxFileException {
        try {
            Files.write(filePath, text);
        } catch (IOException e) {
            throw LynxFileException.writeError(e.getMessage());
        }
    }

}

