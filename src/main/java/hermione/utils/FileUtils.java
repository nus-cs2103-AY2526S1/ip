package hermione.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Utility class for file operations in the Hermione application.
 */
public class FileUtils {

    /**
     * Ensures that the specified file exists.
     * If the file does not exist, it will be created along with any necessary parent directories
     * if they do not already exist.
     *
     * @param filePath The path to the file that should exist.
     */
    public static void ensureFileExists(Path filePath) {
        try {
            if (filePath.getParent() != null) {
                Files.createDirectories(filePath.getParent());
            }

            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
        } catch (SecurityException e) {
            System.out.println("Don't have permission to create file");
        } catch (IOException e) {
            System.out.println("Error creating file");
        } catch (UnsupportedOperationException e) {
            System.out.println("Cannot create file in a directory that is not a directory");
        }
    }

    /**
     * Reads all lines from a file at the specified path.
     * This method ensures that the file exists before attempting to read it.
     * If the file does not exist or an error occurs during reading, it returns an empty
     * list.
     *
     * @param filePath The path to the file to be read.
     * @return A list of strings representing the lines in the file,
     */
    public static List<String> readAllLines(Path filePath) {
        try {
            ensureFileExists(filePath);
            return Files.readAllLines(filePath);
        } catch (IOException e) {
            System.out.println("Failed to read file: " + filePath);
            return List.of();
        }
    }

    /**
     * Writes all lines to a file at the specified path.
     * This method ensures that the file exists before attempting to write to it.
     * If the file does not exist or an error occurs during writing, it prints an error
     * message to the console.
     *
     * @param filePath The path to the file where lines should be written.
     * @param lines    The list of strings to be written to the file.
     */
    public static void writeAllLines(Path filePath, List<String> lines) {
        try {
            ensureFileExists(filePath);
            Files.write(filePath, lines);
        } catch (IOException e) {
            System.out.println("Failed to write file: " + filePath);
        }
    }
}
