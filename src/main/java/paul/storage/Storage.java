package paul.storage;

import paul.exception.PaulException;
import paul.task.Deadline;
import paul.task.Event;
import paul.task.Task;
import paul.task.TaskList;
import paul.task.ToDo;

import java.io.File;
import java.io.FileWriter;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.time.LocalDate;
import java.util.Scanner;

/**
 * Handles reading from and writing to the storage file
 * which keeps track of tasks.
 */
public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;

        // create directory if it does not exist
        File parentFile = new File(filePath).getParentFile();
        if (!parentFile.exists()) {
            if (!parentFile.mkdirs()) {
                System.out.println("Error: Could not create folder.");
            }
        }
    }

    /**
     * Loads tasks from the file into a TaskList
     *
     * @return Tasklist containing the tasks from the storage.
     * @throws PaulException if the file is not found.
     */
    public TaskList loadTasks() throws PaulException {
        TaskList tasks = new TaskList();
        File file = new File(filePath);

        if (!file.exists()) {
            return tasks;
        }

        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                String[] parts = line.split(" \\| ");
                String type = parts[0]; // T, D, E
                boolean isDone = parts[1].equals("1");
                String description = parts[2];

                Task task;
                switch (type) {
                    case "T" -> task = new ToDo(description);
                    case "D" -> task = new Deadline(description, LocalDate.parse(parts[3]));
                    case "E" -> task = new Event(
                            description, LocalDate.parse(parts[3]), LocalDate.parse(parts[4]));
                    default -> {
                        System.out.println("Unknown task type in file, skipping: " + type);
                        continue;
                    }
                }

                if (isDone) {
                    task.markTask();
                }

                tasks.add(task);
            }
        } catch (FileNotFoundException e) {
            throw new PaulException("Error: File not found: " + e.getMessage());
        }

        return tasks;
    }

    /**
     * Saves the tasks from the TaskList into the storage file.
     *
     * @param taskList TaskList containing the tasks to save.
     */
    public void saveTasks(TaskList taskList) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= taskList.size(); i++) {
            sb.append(taskList.get(i).toSaveString()).append(System.lineSeparator());
        }

        try {
            File file = new File(filePath);

            // Write to file
            FileWriter fw = new FileWriter(file);
            fw.write(sb.toString());
            fw.close();
        } catch (IOException e) {
            System.out.println("Error: Unable to save tasks: " + e.getMessage());
        }
    }
}
