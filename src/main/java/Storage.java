import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Storage {
    private String filePath;
    DateTimeFormatter format =
            DateTimeFormatter.ofPattern("MMM dd yyyy HHmm", Locale.ENGLISH);

    public Storage(String filePath){
        this.filePath = filePath;
    }
    public TaskList load() throws IOException {
        TaskList tasks = new TaskList();
        File file = new File(filePath);
        if(!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
            return tasks;
        }
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(" \\| ");
            String type = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];
            switch (type) {
            case "T":
                Todo todo = new Todo(description);
                if (isDone) {
                    todo.markAsDone();
                }
                tasks.addTask(todo);
                break;
            case "D":
                LocalDateTime by = LocalDateTime.parse(parts[3], format);
                Deadline deadline =new Deadline(description,by);
                if (isDone) {
                    deadline.markAsDone();
                }
                tasks.addTask(deadline);
                break;
            case "E":
                LocalDateTime from = LocalDateTime.parse(parts[3], format);
                LocalDateTime to = LocalDateTime.parse(parts[4], format);
                Event event = new Event(description,from,to);
                if(isDone) {
                    event.markAsDone();
                }
                tasks.addTask(event);
                break;
            default:
                System.out.println("Invalid line: " + line);
            }
        }
        return tasks;
    }

    public void save(TaskList tasks) throws IOException {
        FileWriter file = new FileWriter(filePath);
        for (Task task : tasks.getFullTasks()) {
            file.write(task.toFormat() + System.lineSeparator());
        }
        file.close();
    }
}
