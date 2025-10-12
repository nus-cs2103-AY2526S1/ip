package com.oadultradeepfield.smartotter.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.oadultradeepfield.smartotter.SmartOtterException;
import com.oadultradeepfield.smartotter.task.Task;
import com.oadultradeepfield.smartotter.task.TaskParser;

/**
 * Utility class for reading and writing {@link Task} objects from and to files.
 */
public class FileManager {
    private final String fileName;

    /**
     * Creates a new FileManager instance for the specified file.
     *
     * @param fileName the file path to manage tasks for
     */
    public FileManager(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Reads tasks from the managed file. Lines that cannot be parsed are skipped.
     *
     * @return a list of successfully parsed tasks, or an empty list if the file does not exist
     */
    public List<Task> readTasksFromFile() {
        List<Task> tasks = new ArrayList<>();
        int success = 0;
        int failed = 0;

        // If file doesn't exist, return empty list without error
        if (!Files.exists(Paths.get(fileName))) {
            return tasks;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    Optional<Task> task = TaskParser.parse(line);
                    if (task.isPresent()) {
                        tasks.add(task.get());
                        success++;
                    }
                } catch (SmartOtterException e) {
                    failed++;
                }
            }
        } catch (IOException e) {
            CustomIO.printPretty(
                CustomIO.formatError("Error reading file: %s (%s)".formatted(fileName, e.getMessage())));
        }

        CustomIO.printPretty("Parsing complete: %s success, %s failed".formatted(success, failed));
        return tasks;
    }

    /**
     * Saves a list of tasks to the managed file. Creates parent directories if necessary. Existing
     * content will be overwritten.
     *
     * @param tasks the list of tasks to save
     */
    public void saveTasksToFile(List<Task> tasks) {
        try {
            Path path = Path.of(fileName);

            // Ensure parent directory exists
            if (path.getParent() != null && Files.notExists(path.getParent())) {
                Files.createDirectories(path.getParent());
            }

            // Open writer (auto-creates file if missing, truncates if exists)
            try (BufferedWriter writer =
                     Files.newBufferedWriter(
                         path,
                         StandardOpenOption.CREATE,
                         StandardOpenOption.TRUNCATE_EXISTING,
                         StandardOpenOption.WRITE)) {
                tasks.stream()
                    .map(Task::convertToLine)
                    .forEach(
                        line -> {
                            try {
                                writer.write(line);
                                writer.newLine();
                            } catch (IOException e) {
                                throw new UncheckedIOException(e);
                            }
                        });

                CustomIO.printPretty("Tasks saved to %s successfully 🐟".formatted(fileName));
            }

        } catch (IOException e) {
            CustomIO.printPretty(
                CustomIO.formatError("Error saving file: " + fileName + " (" + e.getMessage() + ")"));
        }
    }
}
