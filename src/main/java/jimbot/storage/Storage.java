package jimbot.storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import jimbot.tasktype.Task;
import jimbot.tasktype.TaskList;

/**
 * This class stores task data in hard disk.
 *
 * @author limjimin-nus
 */
public class Storage {
    private final String path;

    public Storage(String filePath) {
        this.path = filePath;
    }

    public void update(TaskList tasks) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
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
