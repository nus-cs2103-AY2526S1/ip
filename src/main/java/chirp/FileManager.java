package chirp;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import chirp.exceptions.ChirpException;
import chirp.exceptions.CorruptedFileException;
import chirp.tasks.Deadline;
import chirp.tasks.Event;
import chirp.tasks.Task;
import chirp.tasks.TaskList;
import chirp.tasks.Todo;

/**
 * Manages task data file
 */
public class FileManager {
    private final Path filePath;

    /**
     * Initialises the file manager and creates data file if it doesnt exist
     *
     * @param filePathStr File path
     * @throws IOException
     */
    public FileManager(String filePathStr) throws IOException {
        filePath = Paths.get(filePathStr);
        if (Files.notExists(filePath)) {
            createFile();
        }
    }

    private void createFile() throws IOException {
        // Make sure parent directories exist
        Files.createDirectories(filePath.getParent());
        Files.createFile(filePath);
    }

    /**
     * Serialises the tasks in the task list to a data string to be written to the file
     *
     * @param taskList Task list
     * @return Data string
     * @throws ChirpException
     */
    private String serialiseTasks(TaskList taskList) throws ChirpException {
        StringBuilder data = new StringBuilder();
        for (int i = 0; i < taskList.getNumOfTasks(); i++) {
            data.append(taskList.getTask(i).serialise());
            data.append('\n');
        }
        return data.toString();
    }

    /**
     * Deserialises a data string to a Task object
     *
     * @param data Data string of the task
     * @return Deserialised task
     * @throws ChirpException
     */
    private Task deserialiseTask(String data) throws ChirpException {
        if (data.isEmpty()) {
            throw new CorruptedFileException("Empty task data!");
        }
        switch (data.substring(0, 1)) {
        case Deadline.TAG -> {
            return Deadline.deserialise(data);
        }
        case Event.TAG -> {
            return Event.deserialise(data);
        }
        case Todo.TAG -> {
            return Todo.deserialise(data);
        }
        default -> {
            throw new CorruptedFileException("Invalid Task Tag!");
        }
        }
    }

    /**
     * Saves the serialised data of the tasks into the task data file
     *
     * @param taskList task list to save
     * @throws IOException
     * @throws ChirpException
     */
    public void saveTasks(TaskList taskList) throws IOException, ChirpException {
        String data = serialiseTasks(taskList);
        Files.write(filePath, data.getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
    }

    /**
     * Loads the tasks stored in the task data file
     *
     * @return Tasklist of the deserialised tasks
     * @throws ChirpException
     * @throws IOException
     */
    public TaskList loadTasks() throws ChirpException, IOException {
        TaskList taskList = new TaskList();
        BufferedReader reader = Files.newBufferedReader(filePath);
        String line;
        while ((line = reader.readLine()) != null) {
            if (!line.isEmpty()) {
                taskList.addTask(deserialiseTask(line));
            }
        }
        return taskList;
    }
}
