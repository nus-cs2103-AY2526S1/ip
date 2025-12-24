package echo.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import echo.task.Deadline;
import echo.task.Event;
import echo.task.Task;
import echo.task.Todo;
import echo.tasklist.TaskList;

/**
 *  Represents persistent storage to store and retrieve user tasks.
 *  The <code>Storage</code> class provides methods to read tasks from a file and
 *  write tasks to a file, allowing the application to maintain task data between
 *  programs. A <code>Storage</code> object stores a File <code>file</code>
 *  from which the information is retrieved.
 */
public class Storage {
    private final File file;

    public Storage(String path) {
        this.file = new File(path);

        if (!file.exists()) {
            try {
                File parentFile = file.getParentFile();
                if (parentFile != null && !parentFile.exists()) {
                    boolean newDir = parentFile.mkdirs();
                    assert newDir : "Unable to create new directory";
                }
                boolean newFile = file.createNewFile();
                assert newFile : "Unable to create new file";
            } catch (IOException error) {
                System.out.println("I'm unable to create a containment for your tasks. Unable to create new file");
            }
        }
    }

    /**
     * Returns a task list after reading the file from the storage.
     *
     * @return task list of tasks stored in file.
     */
    public TaskList readFile() {
        ArrayList<Task> list = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(this.file);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" \\| ");
                switch (parts[0]) {
                case "T":
                    Task todoTask = new Todo(parts[2]);
                    markTaskIfDone(todoTask, parts[1]);
                    list.add(todoTask);
                    break;
                case "D":
                    LocalDateTime deadline = formatDateTime(parts[3]);
                    Task deadlineTask = new Deadline(parts[2], deadline);
                    markTaskIfDone(deadlineTask, parts[1]);
                    list.add(deadlineTask);
                    break;
                case "E":
                    LocalDateTime start = formatDateTime(parts[3]);
                    LocalDateTime end = formatDateTime(parts[4]);
                    Task eventTask = new Event(parts[2], start, end);
                    markTaskIfDone(eventTask, parts[1]);
                    list.add(eventTask);
                    break;
                }
            }
            scanner.close();
        } catch (FileNotFoundException error) {
            System.out.println("I am unable to find the containment for your Alien tasks");
        }
        return new TaskList(list);
    }

    /**
     * Saves the given list of tasks by writing it onto the storage file
     *
     * @param taskList The list of tasks to be stored
     */
    public void saveFile(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(this.file);
            List<Task> list = taskList.getList();

            for (Task task : list) {
                fw.write(task.toDataString());
                fw.write("\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("I am unable to send your Alien tasks into the Null Void for storage");
        }
    }

    private static void markTaskIfDone(Task task, String status) {
        if (status.equals("1")) {
            task.markAsDone();
        }
    }

    private static LocalDateTime formatDateTime(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
        return LocalDateTime.parse(dateTime, formatter);
    }
}
