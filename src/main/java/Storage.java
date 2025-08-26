import java.io.*;
import java.util.ArrayList;

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

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                String type = parts[0];
                boolean isDone = parts[1].equals("1");

                switch (type) {
                case "T":
                    Task todo = new Todo(parts[2]);
                    if (isDone) todo.markAsDone();
                    tasks.add(todo);
                    break;
                case "D":
                    Task deadline = new Deadline(parts[2], parts[3]);
                    if (isDone) deadline.markAsDone();
                    tasks.add(deadline);
                    break;
                case "E":
                    Task event = new Event(parts[2], parts[3], parts[4]);
                    if (isDone) event.markAsDone();
                    tasks.add(event);
                    break;
                default:
                    System.out.println("OOPS!!! Skipping corrupted line... " + line);
                }
            }
        } catch (IOException e) {
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
