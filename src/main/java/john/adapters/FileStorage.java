package john.adapters;

import john.model.*;

import john.ports.Storage;
import john.util.DateTimeParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;

import java.util.ArrayList;
import java.util.List;

public class FileStorage implements Storage {
    private final String filePath;

    public FileStorage(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public TaskList load() {
        List<Task> tasks = new ArrayList<Task>();
        File file = new File(this.filePath);
        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    System.out.println("File created: " + file.getAbsolutePath());
                } else {
                    System.out.println("Failed to create file.");
                }
                return new TaskList(tasks);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");

                if (parts.length == 0) continue;

                // Use the first argument as command
                String command = parts[0].trim();

                switch (parts[0].trim().toUpperCase()) {
                    case "T":
                        if (parts.length >= 3) {
                            String title = parts[2].trim();
                            Task todo = new Todo(title);

                            if (parts[1].trim().equals("1")) {
                                todo.markAsComplete();
                            }

                            tasks.add(todo);
                        }
                        break;

                    case "D":
                        if (parts.length >= 4) {
                            String title = parts[2].trim();
                            String deadline = parts[3].trim();
                            Task deadlineTask = new Deadline(title, DateTimeParser.parseDateTime(deadline));

                            if (parts[1].trim().equals("1")) {
                                deadlineTask.markAsComplete();
                            }

                            tasks.add(deadlineTask);
                        }
                        break;

                    case "E":
                        if (parts.length >= 5) {
                            String title = parts[2].trim();
                            String from = parts[3].trim();
                            String to = parts[4].trim();
                            Task eventTask = new Event(title, DateTimeParser.parseDateTime(from),
                                    DateTimeParser.parseDateTime(to));

                            if (parts[1].trim().equals("1")) {
                                eventTask.markAsComplete();
                            }

                            tasks.add(eventTask);
                        }
                        break;

                    default:
                        System.out.println("Unknown task type: " + parts[0]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new TaskList(tasks);
    }

    @Override
    public void save(TaskList tasks) {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                writer.write(task.serialise() + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}