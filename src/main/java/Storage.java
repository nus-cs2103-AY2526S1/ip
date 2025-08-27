import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath){
        this.filePath = filePath;
    }
    public List<Task> load() throws IOException {
        List<Task> tasks = new ArrayList<>();
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
                tasks.add(todo);
                break;
            case "D":
                String by = parts[3];
                Deadline deadline =new Deadline(description,by);
                if (isDone) {
                    deadline.markAsDone();
                }
                tasks.add(deadline);
                break;
            case "E":
                String from = parts[3];
                String to = parts[4];
                Event event = new Event(description,from,to);
                if(isDone) {
                    event.markAsDone();
                }
                tasks.add(event);
            default:
                System.out.println("Invalid line: " + line);
            }
        }
        return tasks;
    }

    public void save(ArrayList<Task> tasks) throws IOException {
        FileWriter file = new FileWriter(filePath);
        for (Task task : tasks) {
            file.write(task.toFormat() + System.lineSeparator());
        }
        file.close();
    }





}
