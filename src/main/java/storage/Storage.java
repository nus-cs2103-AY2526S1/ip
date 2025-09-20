package storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import taskmodule.DeadlineTask;
import taskmodule.EventTask;
import taskmodule.Task;
import taskmodule.TaskList;
import taskmodule.ToDoTask;

/**
 * Handles the saving and loading of tasks from persistent storage.
 *
 * <p>The {@code Storage} class is responsible for reading tasks from a CSV file
 * at startup and writing tasks back to the file whenever changes are made.
 * Tasks are stored in the global {@link TaskList} and serialized/deserialized
 * using {@link Task#toDataString()}.</p>
 */
public class Storage {
    protected TaskList tasklist;
    protected String filePath = "data/task_storage.csv";

    /**
     * Constructs a {@code Storage} object and initializes the task list
     * by loading tasks from the storage file.
     */
    public Storage() {
        this.tasklist = setTaskList();
    }

    /**
     * Saves all tasks in the current task list to the storage file.
     *
     * <p>If the file or its parent directory does not exist, they are created.
     * Each task is written in its serialized form, one per line.</p>
     *
     * @throws IOException if an error occurs while creating or writing to the file
     */
    public void saveTasks() throws IOException {
        File file = new File(this.filePath);
        File parentDir = file.getParentFile();

        try {
            if (parentDir != null && !parentDir.exists()) {
                boolean a = parentDir.mkdirs();
            }
            if (!file.exists()) {
                boolean b = file.createNewFile();
            }

            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this.filePath, false))) {
                for (int i = 0; i < this.tasklist.getTaskCount(); i++) {
                    Task task = this.tasklist.getTask(i);
                    bufferedWriter.write(task.toDataString());
                    bufferedWriter.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from the storage file and constructs a {@link TaskList}.
     *
     * <p>The CSV format uses {@code |}-separated values with the following columns:</p>
     * <ul>
     *   <li>Type ({@code T}, {@code D}, {@code E})</li>
     *   <li>Completion status (0 or 1)</li>
     *   <li>Description</li>
     *   <li>Optional note</li>
     *   <li>Deadlines and event dates (when applicable)</li>
     * </ul>
     *
     * @return a populated {@link TaskList} if tasks are found, otherwise an empty one
     */
    public TaskList setTaskList() {
        List<Task> taskStore = new ArrayList<Task>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean hasTasks = false;

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue; // skip blank lines
                hasTasks = true;

                String[] cols = line.split("\\|", -1);
                if (cols.length < 3) {
                    continue;
                }

                String type = cols[0].trim();
                boolean isDone = cols[1].trim().equals("1");
                String description = cols[2].trim();
                String note = cols.length > 3 ? cols[3].trim() : null;
                if (note != null && note.isEmpty()) {
                    note = null;
                }

                Task task = null;

                switch (type) {
                case "T":
                    task = new ToDoTask(description);
                    break;
                case "D":
                    LocalDate by = LocalDate.parse(cols[4].trim());
                    task = new DeadlineTask(description, by);
                    break;
                case "E":
                    LocalDate from = LocalDate.parse(cols[4].trim());
                    LocalDate to = LocalDate.parse(cols[5].trim());
                    task = new EventTask(description, from, to);
                    break;
                }

                if (task != null) {
                    if (isDone) {
                        task.markAsDone();
                    }
                    if (note != null) {
                        task.addNote(note);
                    }
                    taskStore.add(task);
                }
            }
            if (hasTasks) {
                return new TaskList(taskStore);
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }

        return new TaskList();
    }

    /**
     * Returns the current task list.
     *
     * @return the {@link TaskList} managed by this storage instance
     */
    public TaskList getTaskList() {
        return this.tasklist;
    }
}
