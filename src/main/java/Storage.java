import taskTypes.Task;
import taskTypes.TaskList;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Storage {
    private final String path;

    public Storage(String filePath) {
        this.path = filePath;
    }

    public void update(TaskList tasks) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
            oos.writeObject(new ArrayList<>(tasks.getTaskList()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public TaskList load() {
        File file = new File(path);
        if (!file.exists()) {
            if (file.getParentFile() != null) {
                file.getParentFile().mkdirs();
            }
            return new TaskList();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            List<Task> tasks = (List<Task>) ois.readObject();
            return new TaskList(tasks);
        } catch (IOException | ClassNotFoundException e) {
            return new TaskList();
        }
    }
}
