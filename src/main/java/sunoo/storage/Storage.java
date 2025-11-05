package sunoo.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import sunoo.task.Deadline;
import sunoo.task.Event;
import sunoo.task.Task;
import sunoo.task.TaskList;
import sunoo.task.ToDo;

/**
 * Represents a storage that writes tasks to a txt file and unloads tasks from the same file.
 */
public class Storage {

    /** Default path to the txt file */
    private static final String FILE_PATH = "data/sunoo.txt";

    /**
     * Returns the current tasklist as represented in the txt file.
     *
     * @return The current tasklist.
     * @throws IOException If the file cannot be read or does not exist.
     */
    public static TaskList loadTasks() throws IOException {
        TaskList tasks = new TaskList();
        ensureFileExists();
        Scanner s = new Scanner(new File(FILE_PATH));
        while (s.hasNextLine()) {
            String taskText = s.nextLine();
            String[] taskParts = taskText.split(" \\| ");
            assert taskParts.length >= 3;
            boolean isDone;
            switch (taskParts[0]) {
            case "T":
                isDone = taskParts[1].equals("1");
                tasks.addTask(new ToDo(isDone, taskParts[2]));
                break;
            case "D":
                isDone = taskParts[1].equals("1");
                tasks.addTask(new Deadline(isDone, taskParts[2],
                        LocalDateTime.parse(taskParts[3], DateTimeFormatter.ISO_LOCAL_DATE_TIME)));
                break;
            case "E":
                isDone = taskParts[1].equals("1");
                tasks.addTask(new Event(isDone, taskParts[2],
                        LocalDateTime.parse(taskParts[3], DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                        LocalDateTime.parse(taskParts[4], DateTimeFormatter.ISO_LOCAL_DATE_TIME)));
                break;
            default:
                assert false;
                break;
            }
        }
        return tasks;
    }

    /**
     * Updates and writes list of tasks into the txt file.
     *
     * @param tasks Current list of tasks.
     * @throws IOException If the file cannot be written to or an I/O error occurs.
     */
    public static void updateTaskListInTxt(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        for (Task task : tasks.getTasks()) {
            fw.write(task.getTxtRepresentation());
            fw.write("\n");
        }
        fw.close();
    }

    /**
     * Ensures that the file and its parent directories exist.
     * Creates the file and directories if they do not exist.
     *
     * @throws IOException If the file or directories cannot be created.
     */
    private static void ensureFileExists() throws IOException {
        File file = new File(FILE_PATH);
        file.getParentFile().mkdirs();
        file.createNewFile();
    }
}
