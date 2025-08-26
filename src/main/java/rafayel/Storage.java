package rafayel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import rafayel.task.Deadline;
import rafayel.task.Event;
import rafayel.task.Task;
import rafayel.task.Todo;

public class Storage {

    String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void save(ArrayList<Task> tasks) throws Exception {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task task : tasks) {
                fw.write(task.saveTaskName() + "\n");
            }
            fw.close();

        } catch (IOException e) {
            System.out.println("An error occurred while saving file.");
            e.printStackTrace();
        }

    }

    private void ensureFileExists() throws RafayelException, IOException {
        File file = new File(filePath);
        File directory = file.getParentFile();

        if (directory != null && !directory.exists()) {
            boolean dirCreated = directory.mkdirs();
            if (!dirCreated) {
                throw new RafayelException("Failed to create folder: " + directory.getAbsolutePath());
            }
        }

        if (!file.exists()) {
            boolean fileCreated = file.createNewFile();
            if (!fileCreated) {
                throw new RafayelException("Failed to create new file: " + file.getAbsolutePath());
            }
        }
    }

    public ArrayList<Task> load() throws RafayelException, IOException {
        ArrayList<Task> tasks = new ArrayList<Task>();
        // Tasklist?

        // check if directory/folder and file exists
        ensureFileExists();

        try {
            FileReader reader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                // System.out.println(line);
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] parts = line.split(" \\| ");
                // for (int i = 0; i < parts.length; i++) {
                // System.out.println(parts[i]);
                // }
                // System.out.println(parts);
                if (parts.length < 2) {
                    continue;
                }

                String taskType = parts[0].trim();
                boolean isDone = parts[1].trim().equals("1");
                String description = parts[2].trim();

                Task task = null;

                switch (taskType) {
                case "T":
                    task = new Todo(description);
                    break;
                case "D":
                    if (parts.length >= 4) {
                        LocalDateTime by = handleReadDate(parts[3].trim());
                        task = new Deadline(description, by);
                    }
                    break;
                case "E":
                    if (parts.length >= 5) {
                        LocalDateTime from = handleReadDate(parts[3].trim());
                        LocalDateTime to = handleReadDate(parts[4].trim());
                        task = new Event(description, from, to);
                    }
                    break;
                }
                if (task != null) {
                    if (isDone) {
                        task.markAsDone();
                    }
                    tasks.add(task);
                }
            }
            bufferedReader.close();
            reader.close();

        } catch (IOException e) {
            System.out.println("Error");
        }

        return tasks;
    }

    public static LocalDateTime handleReadDate(String input) {
        // check if valid format
        DateTimeFormatter[] differenTimeFormatters = new DateTimeFormatter[] {
                DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"), DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"),
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm") };

        for (DateTimeFormatter formatter : differenTimeFormatters) {
            try {
                return LocalDateTime.parse(input, formatter);
            } catch (Exception ignore) {
                // ignore
            }
        }

        System.out.println("Please use one of: MMM d yyyy HH:mm | yyyy/MM/dd HH:mm | dd-MM-yyyy HH:mm");

        return null;
    }

}
