package com.arnavjhajharia.penguin.logic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Utility class for basic file input and output operations related to
 * reading and writing lists of lines.
 * <p>
 * Responsibilities:
 * <ul>
 *   <li>Read all lines from a text file into a {@link List} of strings.</li>
 *   <li>Write a list of strings to a text file, creating parent directories if necessary.</li>
 *   <li>Handle I/O exceptions gracefully by printing stack traces and returning safe defaults
 *       (empty list or {@code false}).</li>
 * </ul>
 *
 * @since 1.0
 */
public final class FileParser {

    private FileParser() { }

    /**
     * Reads all lines from the file at the given path.
     *
     * @param filePath the path of the file to read
     * @return a list of lines read from the file, or an empty list if an error occurs
     */
    public static List<String> readLinesFromFile(String filePath) {
        try {
            return Files.readAllLines(Path.of(filePath));
        } catch (IOException e) {
            e.printStackTrace();
            return List.of(); // return empty list if something fails
        }
    }

    /**
     * Writes the given list of lines to the file at the given path.
     * <p>
     * Behavior:
     * <ul>
     *   <li>If parent directories do not exist, they are created.</li>
     *   <li>If the file exists, it is overwritten.</li>
     *   <li>If an I/O error occurs, the method prints the stack trace and returns {@code false}.</li>
     * </ul>
     *
     * @param filePath the path of the file to write
     * @param lines    the lines to write into the file
     * @return {@code true} if writing succeeded, {@code false} if an error occurred
     */
    public static boolean writeLinesToFile(String filePath, List<String> lines) {
        try {
            Path p = Path.of(filePath);
            if (p.getParent() != null) {
                Files.createDirectories(p.getParent());
            }
            Files.write(p, lines);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false; // signal failure to caller
        }
    }
}
