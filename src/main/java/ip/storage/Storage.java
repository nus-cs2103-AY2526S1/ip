package ip.storage;

import java.io.FileNotFoundException;

import ip.exceptions.FileCorruptedException;
import ip.tasks.Task;
import ip.tasks.TaskList;

/**
 * Interface for storage created mainly to create stubs for testing
 * Methods are those utilized by other classes and required for testing
 */

public interface Storage {

    //Appends into data file
    void writeToStorage(Task task) throws FileCorruptedException;

    //Rewrite data file based on tasks list
    void rewriteStorage(TaskList tasks) throws FileCorruptedException;

    //Check and create folder and file if they do not exist
    void start();

    //Load data file into list
    void loadFile(TaskList tasks) throws FileNotFoundException, FileCorruptedException;
}
