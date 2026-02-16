package angus.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import angus.exception.AngusException;
import angus.task.Deadline;
import angus.task.Event;
import angus.task.Task;
import angus.task.TaskList;
import angus.task.ToDo;
import angus.ui.Parser;

/**
 * Handles the reading and writing of tasks to a local storage file.
 * <p>
 * The Storage class is responsible for loading tasks from the specified storage file
 * when the chatbot is initialised, and saving the user's tasks to the storage file when
 * the chatbot is terminated.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a new instance of the Storage class with the specified storage file path.
     * @param filePath The file path to the local storage file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;

        File file = new File(filePath);
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs(); // create data/ folder if it doesn't exist
        }
    }

    /**
     * Loads tasks from the local storage file.
     * <p>
     * Each task is parsed into a task, which can either be a ToDo, Deadline or Event task.
     * Task completion status is also preserved from the storage file.
     * @return A list of task loaded from the local storage file.
     * @throws AngusException If the local storage file is not found.
     */
    public List<Task> load() throws AngusException {
        assert filePath != null : "filePath cannot be null";
        assert !filePath.isEmpty() : "filePath cannot be empty";
        try {
            File f = new File(filePath);
            Scanner s = new Scanner(f);
            List<Task> tmp = new ArrayList<>();
            while (s.hasNextLine()) {
                String curTask = s.nextLine();
                String[] taskDetails = curTask.split("//");
                Task.TaskTypes taskType = Task.TaskTypes.valueOf(taskDetails[0]);
                switch (taskType) {
                case T:
                    loadTodo(taskDetails, tmp);
                    break;
                case D:
                    loadDeadline(taskDetails, tmp);
                    break;
                case E:
                    loadEvent(taskDetails, tmp);
                    break;
                default:
                    throw new AngusException("Unknown Error while creating task!");
                }
            }
            return tmp;
        } catch (FileNotFoundException e) {
            throw new AngusException("No save data found! Generating a new save...");
        }
    }

    private static void loadEvent(String[] taskDetails, List<Task> tmp) {
        LocalDate formattedStartDate = LocalDate.parse(taskDetails[3], Parser.FORMATTER_FROM);
        LocalDate formattedEndDate = LocalDate.parse(taskDetails[4], Parser.FORMATTER_FROM);
        Event event = new Event(taskDetails[2], formattedStartDate, formattedEndDate);
        if (taskDetails[1].equals("1")) {
            event.markDone();
        }
        tmp.add(event);
    }

    private static void loadDeadline(String[] taskDetails, List<Task> tmp) {
        LocalDate dateTime = LocalDate.parse(taskDetails[3], Parser.FORMATTER_FROM);
        Deadline deadline = new Deadline(taskDetails[2], dateTime);
        if (taskDetails[1].equals("1")) {
            deadline.markDone();
        }
        tmp.add(deadline);
    }

    private static void loadTodo(String[] taskDetails, List<Task> tmp) {
        ToDo toDo = new ToDo(taskDetails[2]);
        if (taskDetails[1].equals("1")) {
            toDo.markDone();
        }
        tmp.add(toDo);
    }

    /**
     * Saves all tasks in the given TaskList to the local storage file.
     * <p>
     * Each task is saved in a serialised format, with each field separated by //.
     * @param tasks The current list of task the user has.
     * @throws AngusException If the file cannot be written to.
     */
    public void save(TaskList tasks) throws AngusException {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (int i = 0; i < tasks.getSize(); i++) {
                Task curTask = tasks.getTask(i);
                fw.write(curTask.saveTask());
                fw.write("\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new AngusException("Unable to save! :(");
        }
    }
}
