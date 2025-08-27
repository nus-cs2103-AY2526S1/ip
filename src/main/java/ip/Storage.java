package ip;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    // Save the tasks to file
    public void save(List<Task> tasks) throws IOException {
        File folder = new File(filePath).getParentFile();
        if (!folder.exists()) {
            folder.mkdirs(); // create folder if it doesn't exist
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task t : tasks) {
                String line = "";
                switch (t.getTypeIcon()) { // use getTypeIcon() instead of getType()
                    case "T":
                        line = "T | " + (t.isDone() ? "1" : "0") + " | " + t.getDescription();
                        break;
                    case "D":
                        line = "D | " + (t.isDone() ? "1" : "0") + " | " + t.getDescription() + " | " + ((Deadline) t).getBy();
                        break;
                    case "E":
                        line = "E | " + (t.isDone() ? "1" : "0") + " | " + t.getDescription() + " | " + ((Event) t).getFrom() + " to " + ((Event) t).getTo();
                        break;
                }
                writer.write(line);
                writer.newLine();
            }
        }
    }

    // Load tasks from file
    public List<Task> load() throws IOException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            return tasks; // no file yet, return empty list
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
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
                        Deadline deadline = new Deadline(desc, parts[3]);
                        if (isDone) deadline.markAsDone();
                        tasks.add(deadline);
                        break;
                    case "E":
                        String[] fromTo = parts[3].split(" to ");
                        Event event = new Event(desc, fromTo[0], fromTo[1]);
                        if (isDone) event.markAsDone();
                        tasks.add(event);
                        break;
                }
            }
        }

        return tasks;
    }
}
