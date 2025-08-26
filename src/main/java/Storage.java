import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        File parentDir = file.getParentFile();

        if (parentDir != null && !parentDir.exists()) {
            boolean dirCreated = parentDir.mkdirs();
            if (!dirCreated) {
                System.out.println("OOPS!!! Could not create data directory :-(");
            }
        }

        if (!file.exists()) {
            try {
                boolean fileCreated = file.createNewFile();
                if (!fileCreated) {
                    System.out.println("OOPS!!! We couldn't create save file.");
                }
            } catch (IOException e) {
                System.out.println("OOPS!!! We couldn't create save file: " + e.getMessage());
            }
            return tasks;
        }

        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split(" \\| ");
                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                String description = parts[2];

                switch (type) {
                case "T":
                    Task todo = new Todo(parts[2]);
                    if (isDone) todo.markAsDone();
                    tasks.add(todo);
                    break;
                case "D":
                    LocalDateTime by = LocalDateTime.parse(parts[3]);
                    Task deadline = new Deadline(description, by);
                    if (isDone) deadline.markAsDone();
                    tasks.add(deadline);
                    break;
                case "E":
                    LocalDateTime from = LocalDateTime.parse(parts[3]);
                    LocalDateTime to = LocalDateTime.parse(parts[4]);
                    Task event = new Event(description, from, to);
                    if (isDone) event.markAsDone();
                    tasks.add(event);
                    break;
                default:
                    System.out.println("OOPS!!! Skipping corrupted line... " + line);
                }
            }
        } catch (Exception e) {
            System.out.println("OOPS!!! There is an error reading file..." + e.getMessage());
        }
        return tasks;
    }

    public void save(ArrayList<Task> tasks) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : tasks) {
                bw.write(task.toFileFormat());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("OOPS!!! We couldn't save the following task: " + e.getMessage());
        }
    }
}
