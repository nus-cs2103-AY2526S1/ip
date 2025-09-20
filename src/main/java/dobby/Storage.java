package dobby;

import dobby.task.Task;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;

public class Storage {
    private final Path filePath;

    public Storage(Path filePath) {
        this.filePath = filePath;
    }

    // Save tasks
    public void saveTasks(ArrayList<Task> tasks) {
        try {
            File file = filePath.toFile();
            file.getParentFile().mkdirs();
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(tasks);
            oos.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    // Load tasks
    @SuppressWarnings("unchecked")
    public ArrayList<Task> loadTasks() {
        File file = filePath.toFile();
        if (!file.exists()) return new ArrayList<>(); // no file yet

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            ArrayList<Task> tasks = (ArrayList<Task>) ois.readObject();
            ois.close();
            return tasks;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
