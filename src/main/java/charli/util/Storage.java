package charli.util;

import charli.task.Deadline;
import charli.task.Event;
import charli.task.Task;
import charli.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Handles file storage operations for the Charli chatbot.
 * Manages loading tasks from file and saving tasks to file persistence.
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        assert filePath != null : "filePath for storage of tasks cannot be null";
        this.filePath = filePath;
    }

    //helper for loadTasks
    private Task parseSavedTask(String line) {
        try {
            // Allow flexible spacing around pipes, keep empty trailing fields if any
            String[] parts = line.split("\\s*\\|\\s*", -1);

            // Minimal sanity: type + done + description must exist
            if (parts.length < 3) { return null; }

            String type = parts[0].trim();
            boolean isDone = "1".equals(parts[1].trim());
            String description = parts[2].trim();

            Task task;
            switch (type) {
                case "T": {
                    task = new Todo(description);
                    if (parts.length >= 4) { task.addTagsCsv(parts[3].trim()); }
                    break;
                }
                case "D": {
                    // requires: type | done | desc | by | [tags?]
                    if (parts.length < 4) { return null; }
                    LocalDateTime by = LocalDateTime.parse(parts[3].trim(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                    task = new Deadline(description, by);
                    if (parts.length >= 5) { task.addTagsCsv(parts[4].trim()); }  // optional tags
                    break;
                }
                case "E": {
                    // requires: type | done | desc | from | to | [tags?]
                    if (parts.length < 5) { return null; }
                    String from = parts[3].trim();
                    String to   = parts[4].trim();
                    task = new Event(description, from, to);
                    if (parts.length >= 6) { task.addTagsCsv(parts[5].trim()); } // optional tags
                    break;
                }
                default:
                    return null;
            }

            if (isDone) { task.markAsDone(); }
            return task;

        } catch (Exception e) {
            // swallow bad/corrupted lines gracefully
            return null;
        }
    }

    /*
    Check if saved ./data/charli.txt exists and if it does,
    load tasks into this instance's tasks list - ensure previous work saved
     */
    public List<Task> load() {
        List<Task> tasks = new ArrayList<>();
        try {
            //Create file object to represent data file
            File file = new File(filePath);

            //If file doesnt exist, dont need to load
            if (!file.exists()) {
                return tasks;
            }

            //Else, need to load, use Scanner to read the file
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = parseSavedTask(line); // Convert the text line back to a Task object
                if (task != null) { //if parser succeeded
                    tasks.add(task);
                }
            }
            scanner.close(); //prevent leaks
        } catch (IOException e) {
            //still need to handle exception although if (!file.exists()) prevents it
            System.out.println("    Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }


    public void save(List<Task> tasks) {
        assert tasks != null : "tasks to save cannot be null";
        try {
            //1. Ensure the ./data directory is created if program runs for first time
            File dataDir = new File("./data");
            if (!dataDir.exists()) {
                dataDir.mkdirs(); // This creates the directory and any necessary parent directories
            }

            //2. Create a FileWriter to write to the file
            FileWriter writer = new FileWriter(filePath);

            //3. Loop through all tasks and convert them to a savable string format
            for (Task task : tasks) {
                //Write string to file followed by newline
                writer.write(convertTaskToFileString(task) + System.lineSeparator());
            }

            //4. Close the writer to ensure all data is saved and resources are freed.
            writer.close();

        } catch (IOException e) {
            System.out.println("Something went wrong saving tasks: " + e.getMessage());
        }
    }

    //Helper for saveTasksToFile after program end
    private String convertTaskToFileString(Task task) {
        String typeCode;
        List<String> fields = new ArrayList<>();

        // type | done | description | ... | tagsCsv
        int doneFlag = task.isDone() ? 1 : 0;

        if (task instanceof Todo) {
            typeCode = "T";
            fields.add(task.getDescription());

        } else if (task instanceof Deadline) {
            typeCode = "D";
            Deadline d = (Deadline) task;
            fields.add(d.getDescription());
            fields.add(d.getBy().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        } else if (task instanceof Event) {
            typeCode = "E";
            Event e = (Event) task;
            fields.add(e.getDescription());
            fields.add(e.getFrom());
            fields.add(e.getTo());

        } else {
            // Unknown type fallback
            typeCode = "?";
            fields.add(task.getDescription());
        }

        // Append tags as the last field (CSV). If no tags, write empty string for a clean trailing column.
        String tagsCsv = (task.getTags() == null || task.getTags().isEmpty())
                ? ""
                : String.join(",", task.getTags());
        fields.add(tagsCsv); // <— last column = tags

        // Assemble: type | done | <fields...>
        StringBuilder sb = new StringBuilder();
        sb.append(typeCode).append(" | ").append(doneFlag);
        for (String f : fields) {
            sb.append(" | ").append(f);
        }
        return sb.toString();
    }

}
