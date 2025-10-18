package uy;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Handles reading and writing tasks to a simple text file and parsing
 * task lines from the file format.
 */
public class Storage {
    private final String home = System.getProperty("user.home");
    private final String project_dir = java.nio.file.Paths.get(home, "OneDrive", "Desktop", "CS2103T", "ip", "src").toString();
    private Path data_path;

    /**
     * Constructs a Storage instance pointing at the specified directory under the project src.
     * It will create the data file if it does not exist.
     *
     * @param file_path relative path under project's src directory where the data file lives
     */
    public Storage(String file_path) {
        // check if path exists
        if (Files.notExists(Paths.get(project_dir, file_path, "Uy.txt"))) {
            try {
                Files.createFile(Paths.get(project_dir, file_path, "Uy.txt"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            this.data_path = Paths.get(project_dir, file_path, "Uy.txt");
        } catch (Exception e) {
            // Create the directory if it doesn't exist
            e.printStackTrace();
        }
    }

    /**
     * Write the provided tasks to the data file, one per line.
     *
     * @param tasks tasks to write
     */
    public void writeTasks(TaskList tasks) {
        try (FileWriter fw = new FileWriter(data_path.toString())) {
            for (int i = 0; i < tasks.getTaskCount(); i++) {
                fw.write(tasks.getTask(i).toString() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Parse a single line from the storage file and return the appropriate Task instance.
     *
     * @param line storage line representing a task
     * @return Task instance described by the line
     * @throws UnknownTaskError when the line cannot be parsed or has an unknown type
     */
    public Task parseTask(String line) throws UnknownTaskError {
        String type = line.substring(1, 2);
        String description = line.substring(6, line.length()).trim();

        if(type.length() <= 0 || description.length() <= 0) {
            throw new UnknownTaskError("Error: Invalid task format");
        }
        Task newTask;

        if(type.equals("T")) {
            newTask = new ToDos(description);
        } else if(type.equals("E")) {
            newTask = new Events(description);
        } else if(type.equals("D")) {
            newTask = new Deadlines(description);
        } else {
            throw new UnknownTaskError("Error: Unknown task type");
        }

        return newTask;
    }

    /**
     * Load tasks from the data file into a TaskList.
     *
     * @return TaskList populated from file
     * @throws IOException when reading fails or parsing fails
     */
    public TaskList loadTasks() throws IOException {
        try {
            System.out.println(this.data_path.toString());
            List<String> lines = Files.readAllLines(this.data_path);
            TaskList tasks = new TaskList();
            for (String line : lines) {
                // Parse each line and add the task to the TaskList
                Task task = this.parseTask(line);
                tasks.addTask(task);
            }
            return tasks;
        } catch (UnknownTaskError e) {
            throw new IOException("Error loading tasks from file", e);
        }
    }
}
