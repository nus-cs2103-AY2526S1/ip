package jeff.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Handles persistent storage operations for the Jeff chatbot system. Manages
 * saving and loading task data to/from the file system.
 */
public class Storage {

    private static final String FILE_PATH = "data/tasks.txt";

    /**
     * Loads task data from the storage file. Creates the data directory if it
     * doesn't exist.
     *
     * @return an ArrayList of task data strings, empty if file doesn't exist
     */
    public ArrayList<String> load() {
        ArrayList<String> lines = new ArrayList<>();
        try {

            Path path = Paths.get(FILE_PATH);
            if (!Files.exists(path)) {
                Files.createDirectories(path.getParent());
                return lines;
            }

            lines.addAll(Files.readAllLines(path));

        } catch (IOException e) {
            System.out.println("File not found: " + e.getMessage());
        }
        assert lines != null : "Lines should not be null";
        return lines;
    }

    /**
     * Saves task data to the storage file. Creates the data directory if it
     * doesn't exist.
     *
     * @param lines the ArrayList of task data strings to save
     */
    public void save(ArrayList<String> lines) {
        try {
            assert lines != null : "Lines should not be null";
            Path path = Paths.get(FILE_PATH);
            Files.createDirectories(path.getParent());
            Files.write(path, lines);
            System.out.println("Tasks saved successfully!");

        } catch (IOException e) {

            System.out.println("File could not be saved: " + e.getMessage());
        }
    }
}
