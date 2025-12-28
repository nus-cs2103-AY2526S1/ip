package bytebuddy.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import bytebuddy.task.Deadline;
import bytebuddy.task.Event;
import bytebuddy.task.Task;
import bytebuddy.task.TaskList;
import bytebuddy.task.Todo;

/**
 * Handles reading and writing tasks to the disk.
 * Responsible for loading and saving the task list.
 */
public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from disk.
     *
     * @return ArrayList of tasks loaded from file
     * @throws IOException If the file cannot be read
     */
    public TaskList load() throws IOException {
        File file = new File(filePath);
        TaskList tasks = new TaskList();
        if (!file.exists()) {
            file.getParentFile().mkdirs(); // make ./data if missing
            file.createNewFile();
            return tasks;
        }

        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(" \\| ");
            Task t = parse(parts);
            tasks.addTask(t);
        }
        br.close();
        return tasks;
    }

    /**
     * Saves the given {@link TaskList} to disk.
     * <p>
     * Each task will be converted into a string representation
     * and written line by line into the save file.
     *
     * @param tasks The TaskList to save
     */
    public void save(TaskList tasks) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));
            for (Task t : tasks) {
                bw.write(t.toString());
                bw.newLine();
        }
            bw.close();
        } catch (IOException e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }
    }

    /**
     * Converts raw task data from the save file into a {@link Task} object.
     *
     * @param parts The split string data representing a task
     * @return The corresponding Task object
     * @throws RuntimeException If the data format is invalid or corrupted
     */
    private Task parse(String[] parts) {
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        DateTimeFormatter inputFormat;
        switch (type) {
        case "T":
            return new Todo(parts[2], isDone);
        case "D":
            inputFormat = DateTimeFormatter.ofPattern("MMM d yyyy");
            LocalDate by = LocalDate.parse(parts[3], inputFormat);
            return new Deadline(parts[2], by, isDone);
        case "E":
            String[] timeframe = parts[3].split(" to ");
            inputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
            LocalDateTime from = LocalDateTime.parse(timeframe[0], inputFormat);
            LocalDateTime to = LocalDateTime.parse(timeframe[1], inputFormat);
            return new Event(parts[2], from, to, isDone);
        default:
            throw new RuntimeException("Corrupted save file!");
        }
    }
}
