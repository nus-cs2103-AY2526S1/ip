package tarawrr;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *  * Storage Class saves and loads data to/from storage hard drive
 */
public class Storage {
    private final Path path;
    private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy"); // Added formatter for input format

    //Constructor initialises an instance of Storage with a filePath
    public Storage(String filePath) {
        this.path = Paths.get(filePath);
    }

    /**
     * Load data from the hard drive into TaskList
     * @return
     * @throws IOException
     */
    public TaskList load() throws IOException {
        TaskList tasks = new TaskList();

        Path directory = path.getParent();
        if (!Files.exists(directory)) {
            try {
                Files.createDirectories(directory);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (!Files.exists(path)) {
            Files.createFile(path);
        }

        Scanner fileScanner = new Scanner(path.toFile());

        while (fileScanner.hasNextLine()) {
            String[] parts = fileScanner.nextLine().split(" \\| ");
            boolean done = parts[1].trim().equals("1");
            switch (parts[0].trim()) {
                case "T":
                    ToDos todo = new ToDos(parts[2]);
                    if (done) {
                        todo.complete();
                    }
                    tasks.addToTaskList(todo);
                    break;
                case "D":
                    // Parse the date using the new INPUT_FORMATTER
                    Task deadline = new Deadline(parts[2].trim(),
                            LocalDate.parse(parts[3].trim(), INPUT_FORMATTER).format(FORMATTER));
                    if (done) {
                        deadline.complete();
                    }
                    tasks.addToTaskList(deadline);
                    break;
                case "E":
                    String timeframe = parts[3].trim();
                    String start = timeframe.split("to")[0].trim();
                    String end = timeframe.split("to")[1].trim();
                    // Parse the dates using the new INPUT_FORMATTER
                    Task event = new Event(parts[2].trim(),
                            LocalDate.parse(start, INPUT_FORMATTER).format(FORMATTER),
                            LocalDate.parse(end, INPUT_FORMATTER).format(FORMATTER));
                    if (done) {
                        event.complete();
                    }
                    tasks.addToTaskList(event);
                    break;
                default:
                    System.out.println("Unknown task type: " + parts[0]);
            }
        }

        return tasks;
    }

    /**
     * Saves data from TaskList to the hard drive
     * @param tasks
     * @throws TarawrrException
     */
    public void save(TaskList tasks) throws TarawrrException {
        try {
            FileWriter writer = new FileWriter(path.toFile());
            ArrayList<Task> tasklist = tasks.getTasks();
            for (Task task : tasklist) {
                writer.write(task.toStorageString() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            throw new TarawrrException("Error saving tasks: " + e.getMessage());
        }
    }
}
