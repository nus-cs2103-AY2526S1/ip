package zell.storage;

import zell.task.Task;
import zell.exception.ZellException;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> loadTasks() throws ZellException{
        List<Task> tasks = new ArrayList<>();

        File file = new File(this.filePath);

        // Create /data folder if it does not exist
        file.getParentFile().mkdirs();

        try (Scanner scanner = new Scanner(file)){
            while (scanner.hasNext()) {
                tasks.add(Task.stringToTask(scanner.nextLine()));
            }
        } catch (FileNotFoundException fe) {
            try {
                FileWriter fileWriter = new FileWriter(this.filePath, true);
                fileWriter.close();
            } catch (IOException ie) {
                throw new ZellException("Failed to store task to local storage because of: "
                        + ie.getMessage());
            }
        }

        return tasks;
    }

    public void storeTask(Task task) throws ZellException {
        try {
            FileWriter fileWriter = new FileWriter(this.filePath, true);
            fileWriter.write(task.taskToString() + "\n");
            fileWriter.close();
        } catch (IOException e) {
            throw new ZellException("Failed to store task to local storage because of: " + e.getMessage());
        }
    }

    public void updateTasks(List<String> tasks) throws ZellException {
        try {
            FileWriter fileWriter = new FileWriter(this.filePath);

            for (String task: tasks) {
                fileWriter.write(task + "\n");
            }

            fileWriter.close();
        } catch (IOException e) {
            throw new ZellException("Failed to store task to local storage because of: " + e.getMessage());
        }
    }
}
