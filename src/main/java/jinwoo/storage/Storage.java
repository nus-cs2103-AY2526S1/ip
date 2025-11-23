package jinwoo.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import jinwoo.exception.DukeException;
import jinwoo.tasks.Deadline;
import jinwoo.tasks.Event;
import jinwoo.tasks.Task;
import jinwoo.tasks.Todo;

/**
 * Storage class for handling data input output
 */
public class Storage {

    private final String filePath;

    /**
     * Constructor for Storage
     *
     * @param filePath path to the file
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Load task
     * if the file does not exist, create a new file and return an empty list
     *
     * @throws RuntimeException if there is an error reading the file
     */
    public ArrayList<Task> loadTasks() {
        ArrayList<Task> todoList = new ArrayList<>();
        try {
            File file = new File(filePath);
            if (!file.exists()) { // Create the file if it does not exist
                boolean mkdirSuccess = file.getParentFile().mkdirs();
                boolean fileSuccess = file.createNewFile();
                if (!mkdirSuccess || !fileSuccess) {
                    throw new IOException("Failed to create file or directory");
                }
                return todoList;
            }
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String text = scanner.nextLine();
                Task task = taskFromText(text);
                todoList.add(task);
            }
        } catch (IOException e) {
            System.out.println("Error reading save file: " + e.getMessage());
            throw new RuntimeException("Failed to handle save file", e);
        }
        return todoList;
    }

    /**
     * Save task
     */
    public void saveTasks(ArrayList<Task> todoList) {
        try {
            File file = new File(filePath);
            FileWriter writer = new FileWriter(file);
            for (Task task : todoList) {
                // Searched System method from copilot
                writer.write(textFromTask(task) + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing to save file: " + e.getMessage());
            throw new RuntimeException("Failed to write to save file", e);
        }
    }

    /**
     * Parse task from text
     *
     * @throws RuntimeException if the text format is invalid
     */
    public Task taskFromText(String text) {
        String type = null;
        String description = null;
        try {
            String[] texts = text.split(" \\| ");
            type = texts[0];
            boolean isDone = texts[1].equals("1");
            description = texts[2];
            switch (type) {
            case "T":
                Task todo = new Todo(description);
                if (isDone) {
                    todo.mark();
                }
                return todo;
            case "D":
                String by = texts[3];
                Task deadline = new Deadline(description, by);
                if (isDone) {
                    deadline.mark();
                }
                return deadline;
            case "E":
                String from = texts[3];
                String to = texts[4];
                Task event = new Event(description, from, to);
                if (isDone) {
                    event.mark();
                }
                return event;
            default:
                throw new RuntimeException("Please check task type T, D, E");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Argument missing for: " + type + " " + description);
            throw new RuntimeException("Fix save file", e);
        } catch (DukeException e) {
            System.out.println("Text to Task failed" + e.getMessage());
            throw new RuntimeException("Fix save file", e);
        }
    }

    /**
     * Get text from task
     * Assume tasks in the list are all valid
     */
    public String textFromTask(Task task) {
        int status = task.getIsDone();
        String description = task.getJustDescription();
        if (task instanceof Todo) {
            return String.format("T | %d | %s", status, description);
        } else if (task instanceof Deadline) {
            String by = ((Deadline) task).getBy();
            return String.format("D | %d | %s | %s", status, description, by);
        } else if (task instanceof Event) {
            String from = ((Event) task).getFrom();
            String to = ((Event) task).getTo();
            return String.format("E | %d | %s | %s | %s", status, description, from, to);
        } else {
            throw new RuntimeException("Wrong task type");
        }
    }
}
