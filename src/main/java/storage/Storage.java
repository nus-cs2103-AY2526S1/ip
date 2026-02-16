package storage;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

import task.TaskList;
import task.Task;
import task.TodoTask;
import task.DeadlineTask;
import task.EventTask;

/**
 * @author Anand Bala
 * The storage class deals with the saving and loading of tasks onto the disk, in order for the
 * user to retain their data in between separate runs of the program. If the file does not exist,
 * it makes the file and any related parent directories.
 */

public class Storage {
    private final String filePath;

    /**
     * Creates a storage helper bound to a specific filesystem path for saving and loading tasks.
     *
     * @param filePath Path to the storage file (relative or absolute). Parent directories will be
     *                 created on the first {@link #load()} if they do not exist.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Writes all tasks to the backing file, one line per task, using each task's
     * {@link task.Task#toStorage()} representation. Existing file contents are overwritten.
     *
     * @param tasks Task list to persist.
     * @throws IOException If the file cannot be created or written to.
     */
    public void save(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        String txt = "";
        for (Task t : tasks.getTasks()) {
            txt += t.toStorage() + System.lineSeparator();
        }
        fw.write(txt);
        fw.close();
    }

    /**
     * Loads tasks from the backing file into a {@link task.TaskList}. If the file does not exist,
     * it (and any missing parent directories) is created and an empty list is returned. Lines that
     * cannot be parsed are skipped.
     *
     * @return A {@link task.TaskList} containing the successfully parsed tasks (possibly empty).
     */
    public TaskList load() {
        File f = new File(filePath);
        ArrayList<Task> loadedTasks = new ArrayList<>();

        if (!f.exists()) {
            try {
                f.getParentFile().mkdirs(); // make directories
                f.createNewFile();          // make empty file
            } catch (IOException e) {
                System.out.println("Could not create save file: " + e.getMessage());
            }
            return new TaskList(); // empty task list
        }

        try (Scanner sc = new Scanner(f)) { // try-with-resources auto-closes
            while (sc.hasNextLine()) {
                String storedTask = sc.nextLine();
                try {
                    Task parsedTask = parseTask(storedTask);
                    loadedTasks.add(parsedTask);
                } catch (IllegalArgumentException e) {
                    System.out.println("Skipping bad task line: " + storedTask);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found, starting with empty task list.");
        }
        return new TaskList(loadedTasks);
    }

    private Task parseTask(String storedTask) {
        String[] parts = storedTask.split(" \\| ");
        String type = parts[0].trim();

        boolean isDone = parts[1].trim().equals("1");
        System.out.println(parts[1].trim());
        System.out.println(parts[2]);
        String taskName = parts[2].trim();

        switch (type) {
            case "T":
                return new TodoTask(taskName, isDone);
            case "D":
                LocalDate by = LocalDate.parse(parts[3].trim());
                return new DeadlineTask(taskName, isDone, by);
            case "E":
                LocalDate from = LocalDate.parse(parts[3].trim());
                LocalDate to = LocalDate.parse(parts[4].trim());
                return new EventTask(taskName, isDone, from, to);
        }
        throw new IllegalArgumentException(); // maybe throw some exception
    }
}
