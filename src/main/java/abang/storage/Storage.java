package abang.storage;

import abang.task.TaskList;
import java.io.*;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles persistence of the task list to/from disk.
 * <p>
 * This implementation reads/writes a single text file at data/Abang.txt.
 * /
 **/
public class Storage {
    private static final String FILE_PATH = "data/Abang.txt";

    /**
     * Creates a storage object and ensures the backing file exists.
     * If the parent directory/file does not exist, they will be created.
     */
    public Storage() {
        assert FILE_PATH != null && !FILE_PATH.isBlank();

        try {
            File file = new File(FILE_PATH);
            file.getParentFile().mkdir();
            file.createNewFile();
            assert file.isFile() : "Storage target must be a file";
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

/**
 * Loads tasks from the backing file and returns them as a {@link TaskList}.
 * <p>
 * Lines that cannot be read will be ignored, and a warning is printed.
 **/
    public TaskList load() {
        ArrayList<String> fileLines = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new File(FILE_PATH));
            while (sc.hasNextLine()) {
                fileLines.add(sc.nextLine());
            }
            sc.close();
        } catch (IOException e) {
            System.out.println("Error: Corrupted file");
        }
        return TaskList.fromFileLines(fileLines);
    }

/**
 * Saves the given lines to the backing file, one line per task.
 */
    public void save(ArrayList<String> fileLines) {
        try {
            FileWriter fw = new FileWriter(new File(FILE_PATH));
            for (String line : fileLines) {
                fw.write(line + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }
}



