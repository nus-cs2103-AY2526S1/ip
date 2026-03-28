package clare.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import clare.exception.StringConvertExceptions;
import clare.task.Task;
import clare.task.TaskList;

/**
 * Represents the backend class to handle data CRUD
 */
public class Storage {
    private final String dataPath;

    public Storage(String path) {
        this.dataPath = path;
    }

    /**
     * Reads data from the given data path and load task into tasks array
     *
     * @throws FileNotFoundException if the file is not found
     * @throws StringConvertExceptions if there is string conversion error on the command
     */
    public ArrayList<Task> loadData() throws FileNotFoundException, StringConvertExceptions {
        File f = new File(dataPath);
        Scanner sc = new Scanner(f);
        ArrayList<Task> tasks = new ArrayList<>();
        while (sc.hasNext()) {
            tasks.add(Task.convert(sc.nextLine()));
        }
        return tasks;
    }

    /**
     * Overwrites the file from data path from tasks array
     *
     * @param tasks the array of tasks
     * @throws IOException if the file has problem
     */
    public void rewriteData(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(dataPath);
        fw.write(tasks.getAllTaskSaveString());
        fw.close();
    }

    /**
     * Adds new line of data to the file
     *
     * @param newTask the new data to be added
     * @throws IOException if the file has problem
     */
    public void addData(Task newTask) throws IOException {
        FileWriter fw = new FileWriter(dataPath, true);
        fw.write(newTask.toSaveString() + System.lineSeparator());
        fw.close();
    }

    /**
     * create a file if the path is not existed
     */
    public void createFile() {
        File dir = new File("data");
        if (!dir.exists()) {
            boolean isCreated = dir.mkdir();
            if (!isCreated) {
                System.out.println("Error creating directory");
            }
        }

        File file = new File("data\\data.txt");

        try {
            boolean isCreated = file.createNewFile();
            if (!isCreated) {
                System.out.println("Error creating file");
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
