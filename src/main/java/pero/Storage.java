package pero;

import pero.exception.PeroException;
import pero.model.Deadline;
import pero.model.Event;
import pero.model.Task;
import pero.model.TaskList;
import pero.model.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;


/**
 * Responsible for reading and writing to a file
 * Responsible for saving task to storage
 * Should not call UI
 */
public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return this.filePath;
    }

    /**
     * Writes tasks from this session to Pero_storage to be stored
     *
     * @param tasks Task list of tasks accumulated from this session.
     * @throws IOException If filePath used to write to is invalid
     */
    public void saveList(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task t : tasks.getAllTasks()) {
            fw.write(t.toStorageString() + "\n");
        }
        fw.close();
    }

    /**
     * Checks whether first char in line is either 1 or 0.
     * If 1, marked.
     * If 0, unmarked.
     *
     * @param oneZero First char: either 1 or 0.
     * @return Whether task is marked or unmarked.
     */
    private boolean isMarked(String oneZero) {
        if (oneZero.equals("1")) { // marked
            return true;
        } else if (oneZero.equals("0")) { // unmarked
            return false;
        } else { // not 1 or 0
            throw new IllegalArgumentException("Invalid marked value: " + oneZero);
        }
    }

    /**
     * Loads all the tasks from filePath storage
     * Parse through each line and convert to task obj to add to task list.
     * PeroExceptions (where line is invalid) are thrown and handled within loadList.
     *
     * @return Task list object.
     * @throws IOException If file path cannot be found or read. (outside of control)
     */
    public TaskList loadList() throws IOException {

        // initialise empty task to add tasks read from storage
        TaskList tasks = new TaskList();

        File file = new File(this.filePath);
        if (!file.exists()) {
            // return tasks; // return empty list if no file found
            throw new IOException("No storage file found in filePath: " + this.filePath);
        }

        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            String currTaskLine = scanner.nextLine();
            if (currTaskLine.isEmpty()) {
                continue;
            }
            String[] parts = currTaskLine.split(" \\| "); //split the line into parts
            String firstLetter = parts[0];

            //if error then catch and go to next line (next iteration of loop)
            try {
                switch (firstLetter) {
                case "T": { //pero.model.ToDo
                    if (parts.length != 3) { //wrong format
                        throw new PeroException("Invalid ToDo line from storage: " + currTaskLine);
                    }
                    boolean isDone = isMarked(parts[1]);
                    Task t = new ToDo(parts[2], isDone);
                    tasks.addTask(t);
                    break;
                }
                case "D": { //Deadline
                    if (parts.length != 4) { //wrong format
                        throw new PeroException("Invalid Deadline line from storage: " + currTaskLine);
                    }
                    boolean isDone = isMarked(parts[1]);
                    LocalDateTime byTimeObj = Task.parseDateTime(parts[3]);
                    Task t = new Deadline(parts[2], isDone, byTimeObj);
                    tasks.addTask(t);
                    break;
                }
                case "E": { //Event
                    if (parts.length != 5) { //wrong format
                        throw new PeroException("Invalid Deadline line from storage: " + currTaskLine);
                    }
                    boolean isDone = isMarked(parts[1]);

                    LocalDateTime fromTimeObj = Task.parseDateTime(parts[3]);
                    LocalDateTime byTimeObj = Task.parseDateTime(parts[4]);
                    Task t = new Event(parts[2], isDone, fromTimeObj, byTimeObj);
                    tasks.addTask(t);
                    break;
                }
                default: // not any of the tasks
                    throw new PeroException("Unknown task type line from storage: " + currTaskLine);
            }
            } catch (PeroException e) {
                System.out.println("Skipping invalid line: " + currTaskLine);
            }
        }
        return tasks;
    }
}
