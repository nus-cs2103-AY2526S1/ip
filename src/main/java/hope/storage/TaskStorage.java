package hope.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import hope.tasks.DeadlineTask;
import hope.tasks.EventTask;
import hope.tasks.FixedDurationTask;
import hope.tasks.Task;
import hope.tasks.ToDoTask;

/**
 * A utility class for managing the persistence of tasks in a file.
 * The {@code TaskStorage} class handles reading tasks from a file and writing tasks to it,
 * supporting the storage of {@link ToDoTask}, {@link DeadlineTask}, and {@link EventTask} objects.
 * Tasks are stored in a specific format and can be appended, updated, or loaded into a list.
 */
public class TaskStorage {

    /** The file used for storing tasks. */
    private File file;

    /**
     * Constructs a {@code TaskStorage} instance with the specified file.
     *
     * @param file the {@link File} where tasks are stored
     */
    public TaskStorage(File file) {
        this.file = file;
    }

    /**
     * Appends a task to the storage file.
     * The task is formatted using its {@link Task#format()} method and written to the file
     * with a newline character. If an error occurs during writing, a {@link RuntimeException}
     * is thrown.
     *
     * @param t the {@link Task} to append to the file
     * @throws RuntimeException if an error occurs during file writing
     */
    public void append(Task t) {
        try (FileWriter fileWriter = new FileWriter(file, true);) {
            fileWriter.append(t.format()).append("\n");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Updates the storage file with the contents of the provided to-do list.
     * Writes the formatted representation of each task in the {@link ToDoList} to the file,
     * overwriting its previous contents. Tasks are separated by newlines, except for the last task.
     * If an {@link IOException} occurs, the method silently returns without updating the file.
     *
     * @param list the {@link ToDoList} containing tasks to write to the file
     */
    public void update(ToDoList list) {
        try (FileWriter fileWriter = new FileWriter(file);) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < list.size(); i++) {
                if (i == list.size() - 1) {
                    sb.append(list.get(i).format());
                    break;
                }
                sb.append(list.get(i).format()).append("\n");
            }
            fileWriter.write(sb.toString());
        } catch (IOException e) {
            return;
        }

    }

    /**
     * Loads tasks from the storage file into a list.
     * Reads the file line by line, parsing each line into a {@link Task} object based on its type
     * ("T" for {@link ToDoTask}, "D" for {@link DeadlineTask}, "E" for {@link EventTask}).
     * Tasks are marked as done if indicated in the file. If the file is not found, an empty
     * list is returned.
     *
     * @return an {@link ArrayList} of {@link Task} objects loaded from the file
     */
    @SuppressWarnings("checkstyle:MissingSwitchDefault")
    public ArrayList<Task> toList() {
        ArrayList<Task> ans = new ArrayList<Task>();
        try (Scanner s = new Scanner(file);) {
            int length = 0;
            while (s.hasNext()) {
                String tempRawData = s.nextLine();
                String[] tempData = tempRawData.split("\\|");
                String dataType = tempData[0];
                switch (dataType) {
                case("T"):
                    Task t = new ToDoTask(tempData[2]);
                    if (tempData[1].equals("1")) {
                        t.markAsDone();
                    }
                    ans.add(t);
                    break;
                case("D"):
                    Task t1 = new DeadlineTask(tempData[2], tempData[3]);
                    if (tempData[1].equals("1")) {
                        t1.markAsDone();
                    }
                    ans.add(t1);
                    break;
                case("E"):
                    Task t2 = new EventTask(tempData[2], tempData[3], tempData[4]);
                    if (tempData[1].equals("1")) {
                        t2.markAsDone();
                    }
                    ans.add(t2);
                    break;
                case("FD"):
                    Task t3 = new FixedDurationTask(tempData[2], tempData[3]);
                    if (tempData[1].equals("1")) {
                        t3.markAsDone();
                    }
                    ans.add(t3);
                    break;
                default:
                    throw new IllegalStateException("Something went wrong in "
                            + "TaskStorage.toList(), unexpected data type:  " + dataType);
                }
            }
            return ans;
        } catch (FileNotFoundException e) {
            return ans;
        }

    }
}
