package bambam;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import bambam.task.Deadlines;
import bambam.task.Events;
import bambam.task.Task;
import bambam.task.ToDos;

/**
 * Handles the loading and saving of tasks in the hard disk.
 */
public class TaskStorage {
    private static final String FILE_PATH = "./data/bambam.txt";

    public TaskStorage() throws IOException {
        handleFileExistence();
    }

    /**
     * Handles the case where the file does not exist.
     * @throws IOException If an input or output operation fails.
     */
    private void handleFileExistence() throws IOException {
        assert (FILE_PATH != null && !FILE_PATH.isEmpty()) :
                "FILE_PATH must not be null or empty";

        File file = new File(FILE_PATH);
        File parentDirectory = file.getParentFile();

        if (!parentDirectory.exists()) { // If parent directories does not exist, create a new one
            parentDirectory.mkdirs();
        }

        if (!file.exists()) { // If file does not exist, create a new file
            file.createNewFile();
        }
    }

    /**
     * Loads data in the file as the task list.
     * @return The task list previously saved.
     * @throws FileNotFoundException If a file is not found.
     */
    public TaskList loadTasks() throws FileNotFoundException {
        TaskList taskList = new TaskList();
        File taskFile = new File(FILE_PATH);
        Scanner scanner = new Scanner(taskFile);

        while (scanner.hasNext()) {
            String taskString = scanner.nextLine();
            String[] taskCommands = taskString.split(" \\| ", 4);

            String taskType = taskCommands[0];
            boolean isDone = taskCommands[1].equals("Done");

            Task task = null;

            switch (taskType) {
            case "T":
                task = new ToDos(taskCommands[2]);
                break;
            case "D":
                task = new Deadlines(taskCommands[2], taskCommands[3]);
                break;
            case "E":
                String[] eventsTaskTimeDetails = taskCommands[3].split(" to ", 2);
                task = new Events(taskCommands[2], eventsTaskTimeDetails[0],
                        eventsTaskTimeDetails[1]);
                break;
            }

            assert task != null : "Task should not be null";

            if (isDone) {
                task.markAsDone();
            }

            taskList.addTaskToList(task);
        }

        scanner.close();

        return taskList;
    }

    /**
     * Saves and writes new task to hard disk file.
     * @param taskList The current task list.
     * @throws IOException If an input or output operation fails.
     * @throws BambamException If there is an error related to the passing of input or the chatbot.
     */
    public void saveTasks(TaskList taskList) throws IOException, BambamException {
        FileWriter fw = new FileWriter(FILE_PATH);

        for (int i = 0; i < taskList.getTaskSize(); i++) {
            Task task = taskList.getTask(i);

            assert task != null : "Task at index " + i + " is null";

            fw.write(task.taskStorageString() + "\n");
        }

        fw.close();
    }

}
