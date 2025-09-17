package app;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Handles saving and loading of string data to and from a specified file path.
 * Provides utility methods to persist data across program executions.
 */
public class SaveHandler {
    private final Path savePath;

    /**
     * Creates a SaveHandler with the given file path for saving and loading data.
     *
     * @param savePathName file path to store or load data
     */
    public SaveHandler(String savePathName) {
        this.savePath = Paths.get(savePathName);
    }

    /**
     * Saves given strings into the file at the previously set File Path
     *
     * @param saveStrings
     * @throws IOException
     */
    public void save(String[] saveStrings) throws IOException {
        if (!Files.exists(savePath)) {
            Files.createDirectories(savePath.getParent());
        }
        try (BufferedWriter writer = Files.newBufferedWriter(savePath)) {
            for (String line : saveStrings) {
                writer.write(line);
                writer.newLine();
            }
        }
    }

    /**
     * Loads strings from the file at the specified path.
     * If the file does not exist, it will be created (along with any parent
     * directories),
     * and an empty array will be returned.
     *
     * @return an array of strings read from the file
     * @throws IOException if an I/O error occurs while creating directories or
     *                     reading from the file
     */
    public String[] load() throws IOException {
        if (!Files.exists(savePath)) {
            Files.createDirectories(savePath.getParent());
            return new String[0];
        }

        List<String> lines = Files.readAllLines(savePath);

        return lines.toArray(String[]::new);
    }
}
