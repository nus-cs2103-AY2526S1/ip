package chatbot.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import chatbot.task.Task;

/**
 * Allows for persistent storage of chatbot tasks to and from a file.
 * Loads and saves data from the specified file.
 */
public class Storage {
    private final File file;
    private final int max;
    Storage(String filepath, int max) {
        this.file = new File(filepath);
        this.max = max;
    }

    /**
     * Loads the data from the file in filepath
     *
     * This method first creates the file if the filepath does not exist. The file
     * is then read line by line and parsed to create an ArrayList of tasks that
     * represent the previous state of the Task List as saved
     *
     * @return ArrayList containing list of tasks
     */
    protected ArrayList<Task> loadData() {

        ArrayList<Task> data = new ArrayList<>(max);

        try {
            if (!file.exists()) {
                // If file doesn't currently exist, create it
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                Task t = Task.fromFile(line);
                data.add(t);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * Saves the data into the filepath
     *
     * Each Task is converted into a line of text to be saved into the filepath,
     * so that the data can be loaded later.
     *
     * @param data ArrayList of tasks to be saved
     */
    protected void saveData(ArrayList<Task> data) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            for (Task t: data) {
                bw.write(t.toFile());
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
