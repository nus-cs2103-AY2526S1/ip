package bobby.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import bobby.exception.BobbyException;

/**
 * Saves the final TaskList. When loaded, returns a List of String
 * that can then be loaded by the TaskList class
 */
public class Storage {
    private static final String FILE_PATH = "./data/bobby.txt";

    public Storage() {
    }

    /**
     * Loads TaskList from previous runs of Bobby from bobby.txt
     * Empty list if file does not exist
     *
     * @return List of Tasks converted to String format
     */
    public List<String> load() {
        List<String> tasks = new ArrayList<>();
        try {
            File file = new File(FILE_PATH);
            File parentDir = file.getParentFile();
            if (!parentDir.exists()) {
                parentDir.mkdirs(); // create directory if not exists
            }
            if (!file.exists()) {
                file.createNewFile(); // create file if not exists
            }
            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                tasks.add(s.nextLine());
            }
            s.close();
        } catch (IOException e) {
            // Ignore or handle file creation and reading errors as needed
        }
        return tasks;
    }

    /**
     * Saves any command that is successfully executed by Bobby.
     * Creates bobby.txt file if it does not exist, then appends to it.
     *
     * @param tasks
     * @throws BobbyException if some unknown error occurs
     */
    public void save(List<String> tasks) throws BobbyException {
        try {
            File file = new File(FILE_PATH);
            File parentDir = file.getParentFile();
            if (!parentDir.exists()) {
                parentDir.mkdirs(); // create directory if not exists
            }
            if (!file.exists()) {
                file.createNewFile(); // create file if not exists
            }
            FileWriter fw = new FileWriter(file);
            for (String task : tasks) {
                fw.write(task);
                fw.write(System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new BobbyException("An unknown error has occurred. " + e.getMessage());
        }
    }
}

