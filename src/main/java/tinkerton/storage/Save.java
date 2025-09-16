package tinkerton.storage;

import tinkerton.core.TinkertonException;
import tinkerton.task.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Handles saving and loading of tasks to and from a file.
 */
public class Save {
    /** The file path for saving and loading tasks. */
    private final String filePath;

    /**
     * Constructs a Save object with the specified file path.
     *
     * @param filePath The path to the save file.
     */
    public Save(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the save file and returns them as a TaskList. If the file does not exist,
     * creates a new file and returns an empty TaskList.
     *
     * @return The loaded TaskList.
     */
    public TaskList load() {
        TaskList tasks = new TaskList();
        File file = new File(filePath);

        try {
            if (!file.exists()) {
                file.getParentFile().mkdir();
                file.createNewFile();
                return tasks;
            }

            Scanner scanner = new Scanner(file);

            while (scanner.hasNext()) {
                String nextLine = scanner.nextLine();
                String[] parts = nextLine.split("\\|");
                String type = parts[0].trim();
                boolean isCompleted = parts[1].trim().equals("1");

                switch (type) {
                case "T":
                    tasks.add(new ToDo(parts[2].trim(), isCompleted));
                    break;

                case "D":
                    tasks.add(new Deadline(parts[2].trim(), isCompleted, parts[3].trim()));
                    break;

                case "E":
                    tasks.add(new Event(parts[2].trim(), isCompleted, parts[3].trim(),
                            parts[4].trim()));
                    break;

                default:
                    throw new TinkertonException("Task type unknown");
                }
            }

            scanner.close();
        } catch (IOException e) {
            System.out.println("File Reading Error: " + e.getMessage());
        } catch (TinkertonException e) {
            System.out.println(e.getMessage());
        }

        return tasks;
    }

    /**
     * Saves the given TaskList to the save file.
     *
     * @param tasks The TaskList to save.
     */
    public void save(TaskList tasks) {
        try {
            FileWriter writer = new FileWriter(filePath);

            for (int i = 0; i < tasks.size(); i++) {
                Task curr = tasks.get(i);
                writer.write(curr.toFile());
                writer.write(System.lineSeparator());
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("File Writing Error: " + e.getMessage());
        }
    }
}
