package snowy;

import snowy.task.*;

import java.io.*;

import java.util.ArrayList;

import java.util.Scanner;

/**
 * Stores and saves the changes made to the TaskList locally.
 * Reads from a local txt files and adds the tasks inside to the TaskList.
 */
public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads content from a txt file and stores it.
     * @return a TaskList consists of content inside the txt file
     */
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();

        File file = new File(filePath);

        if (!file.exists()) {
            // create folder if it does not exist
            file.getParentFile().mkdirs();
            return tasks; // Return empty list if file does not exist
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                try {
                    tasks.add(parseLine(line));
                } catch (Exception e) {
                    System.out.println("Corrupted line: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading file: " + e.getMessage());
        }

        return tasks;
    }

    /**
     * Saves the changes made to the TaskList to the local disk.
     * @param tasks
     */
    public void save(ArrayList<Task> tasks) {
        assert tasks != null : "Empty task list should not be saved to local file";
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Task task : tasks) {
                writer.write(task.toString() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    /**
     * Creates different tasks based on the type of the task reads from the local txt file.
     * @param line
     * @return A task corresponding to the required task type
     * @throws Exception
     */
    public Task parseLine(String line) throws Exception {
        String[] parts = line.split(" | ");

        if (parts.length < 3) {
            throw new Exception("Invalid input!");
        }

        String type = parts[0];

        boolean isDone = parts[1].equals("1");

        String task = parts[2];

        switch (type) {
            case "T":
                return new ToDo(task, isDone);
            case "D":
                return new Deadline(task, isDone, parts[3]);
            case "E":
                return new Event(task, isDone, parts[3]);
            case "F":
                return new FixedDuration(task, isDone, parts[3]);
            default:
                throw new IllegalArgumentException("Unknown task type: " + type);
        }
    }

}
