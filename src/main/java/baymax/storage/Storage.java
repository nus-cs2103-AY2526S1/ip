package baymax.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import baymax.task.Task;
import baymax.task.TaskList;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public TaskList load() throws IOException {
        File file = new File(filePath);

        if (!file.exists()) {
            return initialise(file);
        }

        TaskList tasks = new TaskList();
        Scanner sc = new Scanner(file);

        while (sc.hasNext()) {
            Task task = Task.toTaskFormat(sc.nextLine());
            assert task != null : "Task parsing returned null";
            tasks.addTask(task);
        }

        sc.close();

        return tasks;
    }

    public void save(TaskList tasks) throws IOException {
        FileWriter writer = new FileWriter(filePath);

        for (Task task : tasks.getAll()) {
            assert task != null : "Task in TaskList should not be null";
            writer.write(task.toSaveFormat() + "\n");
        }

        writer.close();
    }

    private TaskList initialise(File file) throws IOException {
        file.getParentFile().mkdirs();
        file.createNewFile();
        assert file.exists() : "File should exist after being created";
        return new TaskList();
    }
}
