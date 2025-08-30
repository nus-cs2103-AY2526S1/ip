package duke;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles loading tasks from the file and saving tasks to the file.
 */
public class Storage {
    private String filePath;

    /**
     * Creates a Storage instance with the specified file path.
     * @param filePath The path to the data file
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the data file.
     * @return ArrayList of tasks loaded from file
     * @throws PenguinException if there's an error loading the file
     */
    public ArrayList<Task> load() throws PenguinException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            return tasks; // Return empty list if file doesn't exist
        }

        try {
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(" \\| ");
                if (parts.length >= 3) {
                    String taskType = parts[0];
                    boolean isDone = parts[1].equals("1");
                    String description = parts[2];

                    Task task = null;
                    if (taskType.equals("T")) {
                        task = new Todo(description);
                    } else if (taskType.equals("D") && parts.length >= 4) {
                        String deadline = parts[3];
                        try {
                            task = new Deadline(description, deadline);
                        } catch (Exception e) {
                            // Skip invalid date entries
                            continue;
                        }
                    } else if (taskType.equals("E") && parts.length >= 5) {
                        String from = parts[3];
                        String to = parts[4];
                        task = new Event(description, from, to);
                    }

                    if (task != null) {
                        task.setDone(isDone);
                        tasks.add(task);
                    }
                }
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            throw new PenguinException("Error loading tasks from file: " + e.getMessage());
        }

        return tasks;
    }

    /**
     * Saves the list of tasks to the data file.
     * @param tasks The list of tasks to save
     * @throws PenguinException if there's an error saving to the file
     */
    public void save(ArrayList<Task> tasks) throws PenguinException {
        try {
            // Create data directory if it doesn't exist
            File file = new File(filePath);
            File parentDir = file.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
            }

            StringBuilder content = new StringBuilder();
            for (Task task : tasks) {
                String taskType = "";
                String additionalInfo = "";

                if (task instanceof Todo) {
                    taskType = "T";
                } else if (task instanceof Deadline) {
                    taskType = "D";
                    additionalInfo = " | " + ((Deadline) task).getBy().toString();
                } else if (task instanceof Event) {
                    taskType = "E";
                    additionalInfo = " | " + ((Event) task).getFrom() + " | " + ((Event) task).getTo();
                }

                content.append(taskType + " | " + (task.getDone() ? "1" : "0")
                        + " | " + task.getDescription() + additionalInfo + "\n");
            }

            FileWriter fw = new FileWriter(filePath);
            fw.write(content.toString());
            fw.close();
        } catch (IOException e) {
            throw new PenguinException("Error saving tasks to file: " + e.getMessage());
        }
    }
}
