package Note.ui;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles saving and loading of tasks to and from a file.
 */
public class Storage {

    private String filePath;
    private static final DateTimeFormatter STORAGE_FORMATTER =
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    /**
     * Constructs a Storage object for the given file path.
     *
     * @param filePath the path to the file where tasks are stored
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves a list of tasks to the file.
     *
     * Format:
     * Todo: T | 0/1 | description
     * Deadline: D | 0/1 | description | d/M/yyyy HHmm
     * Event: E | 0/1 | description | d/M/yyyy HHmm to d/M/yyyy HHmm
     *
     * @param tasks the list of tasks to save
     * @throws IOException if an I/O error occurs
     */
    public void save(List<Task> tasks) throws IOException {
        File folder = new File(filePath).getParentFile();
        if (!folder.exists()) folder.mkdirs();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task t : tasks) {
                String line = "";
                switch (t.getTypeIcon()) {
                    case "T":
                        line = "T | " + (t.isDone() ? "1" : "0") + " | " + t.getDescription();
                        break;
                    case "D":
                        Deadline d = (Deadline) t;
                        line = "D | " + (d.isDone() ? "1" : "0") + " | " + d.getDescription()
                                + " | " + d.getByDateTime().format(STORAGE_FORMATTER);
                        break;
                    case "E":
                        Event e = (Event) t;
                        line = "E | " + (e.isDone() ? "1" : "0") + " | " + e.getDescription()
                                + " | " + e.getFromDateTime().format(STORAGE_FORMATTER)
                                + " to " + e.getToDateTime().format(STORAGE_FORMATTER);
                        break;
                }
                writer.write(line);
                writer.newLine();
            }
        }
    }

    /**
     * Loads tasks from the file.
     *
     * @return list of tasks loaded from storage
     * @throws IOException if an I/O error occurs
     */
    public List<Task> load() throws IOException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) return tasks;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    String[] parts = line.split(" \\| ");
                    String type = parts[0];
                    boolean isDone = parts[1].equals("1");
                    String desc = parts[2];

                    switch (type) {
                        case "T":
                            Todo todo = new Todo(desc);
                            if (isDone) todo.markAsDone();
                            tasks.add(todo);
                            break;
                        case "D":
                            LocalDateTime by = LocalDateTime.parse(parts[3], STORAGE_FORMATTER);
                            Deadline deadline = new Deadline(desc, by.format(STORAGE_FORMATTER));
                            if (isDone) deadline.markAsDone();
                            tasks.add(deadline);
                            break;
                        case "E":
                            String[] fromTo = parts[3].split(" to ");
                            LocalDateTime from = LocalDateTime.parse(fromTo[0], STORAGE_FORMATTER);
                            LocalDateTime to = LocalDateTime.parse(fromTo[1], STORAGE_FORMATTER);
                            Event event = new Event(desc, from.format(STORAGE_FORMATTER), to.format(STORAGE_FORMATTER));
                            if (isDone) event.markAsDone();
                            tasks.add(event);
                            break;
                        default:
                            System.err.println("Unknown task type in storage: " + line);
                    }
                } catch (Exception e) {
                    System.err.println("Skipping invalid task in storage: " + line);
                }
            }
        }

        return tasks;
    }
}
