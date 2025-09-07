package dobby;

import dobby.task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;


public class Storage {
    private final Path filePath;

    public Storage(Path filePath) {
        this.filePath = filePath;
    }

    public void saveTasks(ArrayList<Task> tasks) {
        try {
            File file = filePath.toFile();
            file.getParentFile().mkdirs(); // ensure ./data exists
            FileWriter fw = new FileWriter(file);
            for (Task task : tasks) {
                fw.write(task.toString() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }
}
