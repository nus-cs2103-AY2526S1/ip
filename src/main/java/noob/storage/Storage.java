package noob.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import noob.exception.NoobException;
import noob.task.DeadlineTask;
import noob.task.EventTask;
import noob.task.Task;
import noob.task.TaskList;
import noob.task.TaskType;
import noob.task.TodoTask;

/**
 * Stores and retrieves the list of tasks in the specified file path
 */
public class Storage {
    private File file;
    private String filePath;

    public Storage(String filePath) throws NoobException {
        this.filePath = filePath;
        this.file = new File(filePath);
        if (!file.exists()) {
            this.initialiseStorageFile(file);
        }
    }

    /**
     * Creates file as specified by input
     *
     * @param file File object with defined file path
     * @throws NoobException If IOException occurs on file creation
     */
    private void initialiseStorageFile(File file) throws NoobException {
        file.getParentFile().mkdirs();
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new NoobException("Error creating .txt file storage");
        }
    }

    /**
     * Reads the text contents of the specified file path and converts them to a string
     *
     * @param filePath Relative path of file to be read
     * @return String containing file text
     * @throws FileNotFoundException If file is not found
     */
    private String readFileContents(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner sc = new Scanner(file);
        StringBuilder sb = new StringBuilder();

        while (sc.hasNext()) {
            sb.append(sc.nextLine());
            sb.append("\n");
        }

        return sb.toString().trim();
    }

    /**
     * Writes input text to specified file path
     *
     * @param text Text to write to file path
     * @throws IOException If an error occurs on file writing
     */
    private void writeToFile(String text) throws IOException {
        FileWriter fileWriter = new FileWriter(this.filePath);
        fileWriter.write(text);
        fileWriter.close();
    }

    /**
     * Takes a list of tasks and writes it into a specified file path
     *
     * @param tasks List of tasks to store
     * @throws NoobException If an error occurs on file writing
     */
    public void writeTasksToFile(TaskList tasks) throws NoobException {
        StringBuilder sb = new StringBuilder();
        int n = tasks.getNumTasks();

        for (int i = 1; i <= n; i++) {
            Task task = tasks.getTask(i);
            String taskString = formatTaskStringToTxt(task);
            sb.append(taskString).append("\n");
        }

        try {
            writeToFile(sb.toString().trim());
        } catch (IOException e) {
            throw new NoobException("Error writing tasks to file");
        }
    }

    /**
     * Formats a given task to a string to be stored in a line in the storage txt file
     *
     * @param task Input task to be formatted
     * @return Formatted string representation of a task's status in the storage txt file
     * @throws NoobException If the task type is invalid
     */
    private String formatTaskStringToTxt(Task task) throws NoobException {
        TaskType taskType = task.getType();
        String desc = task.getDesc();
        int isDone = task.isDone() ? 1 : 0;

        switch (taskType) {
        case TODO:
            return taskType + " | " + isDone + " | " + desc;
        case DEADLINE:
            DeadlineTask deadlineTask = (DeadlineTask) task;
            return taskType + " | " + isDone + " | " + desc + " | " + deadlineTask.getIsoDeadline();
        case EVENT:
            EventTask eventTask = (EventTask) task;
            return taskType + " | " + isDone + " | " + desc + " | " + eventTask.getIsoFrom()
                    + " | " + eventTask.getIsoTo();
        default:
            throw new NoobException("Unknown task type");
        }
    }

    /**
     * Gets a list of tasks based on the txt file specified
     *
     * @return List of tasks as parsed in the storage file
     * @throws NoobException If file is not found or text formats are invalid
     */
    public ArrayList<Task> getListOfTasks() throws NoobException {
        try {
            String text = readFileContents(this.filePath);

            // .txt file was just created and is empty
            if (text.isEmpty()) {
                return new ArrayList<>();
            }

            String[] taskStrings = text.split("\n");
            ArrayList<Task> tasks = new ArrayList<>();

            for (String str : taskStrings) {
                String[] taskDetails = str.split(" \\| ");
                TaskType taskType = TaskType.valueOf(taskDetails[0]);
                boolean isDone = Integer.parseInt(taskDetails[1]) == 1;
                String desc = taskDetails[2];

                switch (taskType) {
                case TODO:
                    TodoTask todoTask = new TodoTask(desc);
                    if (isDone) {
                        todoTask.markDone();
                    }
                    tasks.add(todoTask);
                    break;
                case DEADLINE:
                    String deadline = taskDetails[3];
                    DeadlineTask deadlineTask = new DeadlineTask(desc, deadline);
                    if (isDone) {
                        deadlineTask.markDone();
                    }
                    tasks.add(deadlineTask);
                    break;
                case EVENT:
                    String from = taskDetails[3];
                    String to = taskDetails[4];
                    EventTask eventTask = new EventTask(desc, from, to);
                    if (isDone) {
                        eventTask.markDone();
                    }
                    tasks.add(eventTask);
                    break;
                }
            }

            return tasks;

        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (NumberFormatException e) {
            throw new NoobException("Invalid done status");
        } catch (IllegalArgumentException e) {
            throw new NoobException("Invalid task types in txt file");
        } catch (IndexOutOfBoundsException e) {
            throw new NoobException("Task type and details mismatch");
        }
    }
}
