package friday;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import friday.task.Deadline;
import friday.task.Event;
import friday.task.Task;
import friday.task.ToDo;

public class Storage {
    private static final String filePath = System.getProperty("user.dir") + "/src/main/data/Friday.txt";

    /**
     * Reads tasks from the file and returns a list of tasks
     * @return List of tasks read from the file, empty list if file doesn't exist or error occurs
     */
    public static List<Task> readFile() {
        try {
            File f = new File(Storage.filePath);
            if (!f.exists()) {
                return new ArrayList<>();
            }
            Scanner sc = new Scanner(f);
            List<Task> tasks = new ArrayList<>();
            while (sc.hasNextLine()) {   // read line by line
                String line = sc.nextLine().trim();
                if (line.isEmpty()) {
                    continue; // Skip empty lines
                }
                
                Task task = parseTaskFromLine(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
            sc.close();

            return tasks;
        } catch(IOException e) {
            System.err.println("Error reading tasks file: " + e.getMessage());
            return new ArrayList<>();
        } catch(Exception e) {
            System.err.println("Unexpected error while reading tasks: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Saves the list of tasks to the file
     * @param tasks The list of tasks to save to the file
     */
    public static void saveToFile(List<Task> tasks) {
        final int maxTries = 3;
        int tries = 0;
        while (tries < maxTries) {
            try {
                File f = new File(Storage.filePath);
                if (!f.getParentFile().exists()) {
                    f.getParentFile().mkdirs();
                }
                try (FileWriter writer = new FileWriter(Storage.filePath)) {
                    for (Task task : tasks) {
                        String taskType = task.getTypeOfTask();
                        String isDone = task.isDone() ? "1" : "0";
                        writer.write(taskType + " | " + isDone + " | " + task.getFullDesc() + System.lineSeparator());
                    }
                    break;
                }
            } catch (IOException e) {
                System.err.println("Error writing to file (attempt " + (tries + 1) + "/" + maxTries + "): " + e.getMessage());
                if (tries == maxTries - 1) {
                    System.err.println("Failed to save tasks after " + maxTries + " attempts. Data may be lost.");
                }
            } finally {
                tries++;
            }
        }
    }

    /**
     * Parses a single line from the storage file into a Task object.
     * @param line The line to parse
     * @return Task object if parsing successful, null otherwise
     */
    private static Task parseTaskFromLine(String line) {
        try {
            String[] parts = line.split("\\|");
            if (parts.length != 3) {
                System.err.println("Invalid line format in storage file: " + line);
                return null;
            }
            
            String taskType = parts[0].trim();
            String isDoneStr = parts[1].trim();
            String desc = parts[2].trim();
            
            if (desc.isEmpty()) {
                System.err.println("Empty task description in storage file: " + line);
                return null;
            }
            
            boolean isDone = "1".equals(isDoneStr);
            Task task = null;

            switch (taskType) {
                case "[T]" -> task = new ToDo(desc);
                case "[D]" -> {
                    try {
                        task = new Deadline(desc);
                    } catch (Exception e) {
                        System.err.println("Error creating deadline task: " + e.getMessage());
                        return null;
                    }
                }
                case "[E]" -> {
                    try {
                        task = new Event(desc);
                    } catch (Exception e) {
                        System.err.println("Error creating event task: " + e.getMessage());
                        return null;
                    }
                }
                default -> {
                    System.err.println("Unknown task type in storage file: " + taskType);
                    return null;
                }
            }
            
            if (task != null && isDone) {
                task.markAsDone();
            }
            
            return task;
        } catch (Exception e) {
            System.err.println("Error parsing line from storage file: " + line + " - " + e.getMessage());
            return null;
        }
    }
}
