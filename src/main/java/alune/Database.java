package alune;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import alune.tasks.Task;
import alune.tasks.TaskList;

/**
 * This class stores the task data across program uses.
 * 
 * @author nghnaomi
 */
public class Database {
    private final String path;
    private List<Task> prev;
    private boolean hasPreviousState;

    public Database(String filePath) {
        this.path = filePath;
        this.prev = new ArrayList<>();
        this.hasPreviousState = false;
    }

    /**
     * Updates the TaskList in the database for future reference and saves a copy
     * for undo command.
     * 
     * @param tasks Latest version of the TaskList.
     */
    public void update(TaskList tasks) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
            this.prev = tasks.getTasks().stream()
                    .map(Task::cloneTask)
                    .collect(Collectors.toList());
            this.hasPreviousState = true;
            oos.writeObject(tasks.getTasks());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns previous state of the TaskList to undo most recent change, then
     * sets getPreviousState to false as only one change can be undone.
     * 
     * @return TaskList before latest change.
     */
    public TaskList getPreviousState() {
        if (!this.hasPreviousState) {
            return null;
        }
        this.hasPreviousState = false;
        return new TaskList(new ArrayList<>(this.prev));
    }

    /**
     * Loads the existing TaskList or creates a new one for future reference.
     * 
     * @return Existing or newly created TaskList.
     */
    @SuppressWarnings("unchecked")
    public List<Task> load() {
        File file = new File(path);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Task>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }
}
