package com.cc;

import com.cc.tasks.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * class that interacts with storage. Contains the field of file-path
 */
public class Storage {
    private static String FILE_PATH;

    public Storage(String filepath) {
        this.FILE_PATH = filepath;
    }

    /**
     * abstraction of creating a storage file when CC is ran for the first time
     */
    public static void ensureDataFileExists() {     //checks that the Data file exists,if not, create one
        File folder = new File("data");
        if(!folder.exists()) {
            folder.mkdir();
        }

        File file = new File(folder, "duke.txt");
        try{
            if(!file.exists()) {
                file.createNewFile();
            }
        }catch (IOException e) {
            System.out.println("Error: no such file");
        }
    }

    public static void saveTaskToFile(Task task){       //writes a task into Data
        try{
            FileWriter writer = new FileWriter(FILE_PATH, true);
            writer.write(task.toString() + System.lineSeparator());
            writer.close();
        } catch (IOException e) {
            System.out.println("Error: task not found" + e.getMessage());
        }
    }

}
