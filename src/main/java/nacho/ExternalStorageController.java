package nacho;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Interfaces with External Storage Text File
 */
public class ExternalStorageController {
    // Handles Interactions with external file

    private static final String DATA_DIR = "./data/";
    private static final String DATA_FILENAME = "mainStore.txt";
    private static final String CORRUPTED_TEMP_FILENAME = "oldCorrupted.txt";

    /**
     * Replaces storage text file with newContent String
     * @param newContent String in storage format
     */
    public static void updateStore(String newContent) {
        assert newContent != null;

        // Create Data Directory and File if not exist
        try {
            Files.createDirectories(Paths.get(DATA_DIR));
            File storageFile = new File(DATA_DIR + DATA_FILENAME);
            storageFile.createNewFile();

            FileWriter storageWriter = new FileWriter(DATA_DIR + DATA_FILENAME);
            storageWriter.write(newContent);
            storageWriter.close();

        } catch (IOException e) {
            System.out.println("An I/O error occurred " + e.getMessage());
        }
    }

    /**
     * Copies content of text file referenced by the default save location into a separate text file
     * <p>
     *     Save new text file at path "./data/ted.txt"
     * </p>
     */
    public static void createTempCorruptedFile() {
        // Creates a copy of the data file into a temporary file storing potentially corrupted data
        try {
            Files.createDirectories(Paths.get(DATA_DIR));
            File corruptedTempFile = new File(DATA_DIR + CORRUPTED_TEMP_FILENAME);
            corruptedTempFile.createNewFile();

            String corruptedContent = ExternalStorageController.getStore();

            FileWriter corruptWriter = new FileWriter(DATA_DIR + CORRUPTED_TEMP_FILENAME);
            corruptWriter.write(corruptedContent);
            corruptWriter.close();

        } catch (IOException e) {
            System.out.println("An I/O error occurred " + e.getMessage());
        }
    }

    public static String getStore() {
        try {
            File storageFile = new File(DATA_DIR + DATA_FILENAME);
            if (!storageFile.exists()) {
                Files.createDirectories(Paths.get(DATA_DIR));
                storageFile.createNewFile();
            }
            return Files.readString(Paths.get(DATA_DIR + DATA_FILENAME));
        } catch (IOException e) {
            System.out.println("An I/O error occurred " + e.getMessage());
            return "";
        }
    }
}
