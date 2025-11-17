package pingu;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles loading tasks from and saving tasks to a file.
 */
public class Storage {
    private final Path filePath;

    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    /**
     * Saves the current list of tasks to the data file.
     *
     * @param taskList The list of tasks to save.
     * @throws PinguException If an error occurs while saving tasks.
     */
    public void save(ArrayList<Task> taskList) throws PinguException {
        try {
            Files.createDirectories(filePath.getParent());
            FileWriter writer = new FileWriter(filePath.toFile());
            for (Task task : taskList) {
                writer.write(task.toFileString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new PinguException("An error occurred while saving tasks: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from the data file into the task list.
     *
     * @return An ArrayList of tasks loaded from the file.
     * @throws PinguException If the file cannot be created or read.
     */
    public ArrayList<Task> load() throws PinguException {
        ArrayList<Task> taskList = new ArrayList<>();
        File dataFile = filePath.toFile();
        DateTimeFormatter fileFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        try {
            if (!Files.exists(filePath.getParent())) {
                Files.createDirectories(filePath.getParent());
            }
            if (!dataFile.exists()) {
                dataFile.createNewFile();
                return taskList;
            }

            Scanner fileScanner = new Scanner(dataFile);
            while (fileScanner.hasNext()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(" \\| ");
                try {
                    String type = parts[0];
                    boolean isDone = parts[1].equals("1");
                    String description = parts[2];
                    Task task = null;

                    switch (type) {
                    case "T":
                        task = new Task(description);
                        break;
                    case "D":
                        LocalDateTime by = LocalDateTime.parse(parts[3], fileFormat);
                        task = new Deadline(description, by);
                        break;
                    case "E":
                        LocalDateTime from = LocalDateTime.parse(parts[3], fileFormat);
                        LocalDateTime to = LocalDateTime.parse(parts[4], fileFormat);
                        task = new Event(description, from, to);
                        break;
                    }

                    if (task != null) {
                        if (isDone) {
                            task.toggleStatus(isDone);
                        }
                        taskList.add(task);
                    }
                } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
                    System.out.println("Warning: Skipping corrupted task line in data file: " + line);
                }
            }
            fileScanner.close();
        } catch (IOException e) {
            throw new PinguException("Error loading tasks from file: " + e.getMessage());
        }
        return taskList;
    }
}
