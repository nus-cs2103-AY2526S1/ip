package mel.apps;

import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.List;

import mel.exceptions.MelException;

/**
 * Represents the storage of data. A storage object loads tasks from the file
 * and saves tasks in the file
 */
public class Storage {
    private final Path filePath;

    /**
     * Returns a storage object
     *
     * @param filePath
     */
    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);

    }

    /**
     * Saves strings into file at set file path
     *
     * @param stringsToSave
     * @throws IOException
     */
    public void save(String[] stringsToSave) throws IOException {
        assert filePath != null;
        FileWriter fw = new FileWriter(filePath.toFile());
        for (String s : stringsToSave) {
            fw.write(s + System.lineSeparator());

        }
        fw.close();
    }

    /**
     * Loads the strings from the file at the file path
     *
     * @return strings stored in the file as an array
     * @throws MelException
     */
    public String[] load() throws MelException {
        try {
            if (!Files.exists(filePath.getParent())) {
                Files.createDirectories(filePath.getParent());
            }

            if (!Files.exists(filePath)) {
                Files.createFile(filePath);

            }

            List<String> lines = Files.readAllLines(this.filePath);

            return lines.toArray(String[]::new);



        } catch (IOException e) {
            throw new MelException("Error loading files!");

        }

    }


}
