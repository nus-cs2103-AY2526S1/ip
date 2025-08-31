package poopiemeow.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import poopiemeow.task.Deadline;
import poopiemeow.task.Event;
import poopiemeow.task.Task;
import poopiemeow.task.Todo;

public class Storage {
    private final Path filePath;

    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    public void save(ArrayList<Task> tasks) throws IOException {
        Files.createDirectories(filePath.getParent());
        FileWriter writer = new FileWriter(filePath.toFile());
        for (Task task : tasks) {
            writer.write(task.toFileString() + "\n");
        }
        writer.close();
    }

    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        if (!Files.exists(filePath)) {
            return tasks;
        }

        Scanner fileScanner = new Scanner(new File(filePath.toString()));
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            try {
                String[] parts = line.split("\\|");
                if (parts.length < 3) continue;

                Task task = null;
                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                String description = parts[2];

                switch (type) {
                    case "T":
                        task = new Todo(description);
                        break;
                    case "D":
                        if (parts.length < 4) continue;
                        task = new Deadline(description, parts[3]);
                        break;
                    case "E":
                        if (parts.length < 5) continue;
                        task = new Event(description, parts[3], parts[4]);
                        break;
                }

                if (task != null) {
                    if (isDone) task.markAsDone();
                    tasks.add(task);
                }
            } catch (Exception e) {
                continue;
            }
        }
        fileScanner.close();
        return tasks;
    }
}