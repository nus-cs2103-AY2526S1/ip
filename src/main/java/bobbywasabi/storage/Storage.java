package bobbywasabi.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

<<<<<<< HEAD:src/main/java/BobbyWasabi/Storage/Storage.java
/**
 * Handles the loading, saving, and creation of data storage for tasks.
 */
=======
import bobbywasabi.exceptions.BobbyWasabiException;
import bobbywasabi.parser.Parser;
import bobbywasabi.tasks.Deadline;
import bobbywasabi.tasks.Event;
import bobbywasabi.tasks.Task;
import bobbywasabi.tasks.TaskList;
import bobbywasabi.tasks.ToDo;



>>>>>>> branch-A-CodingStandard:src/main/java/bobbywasabi/storage/Storage.java
public class Storage {
    private String filepath;
    private String folderpath;

    /**
     * Constructs a Storage object with the given file and folder paths.
     *
     * @param filepath   Path to the data file (e.g., "./data/BobbyWasabiTasks.txt")
     * @param folderpath Path to the folder containing the data file (e.g., "./data")
     */
    public Storage(String filepath, String folderpath) {
        this.filepath = filepath;
        this.folderpath = folderpath;
    }

    /**
     * Checks if the data folder and file exist. If not, creates them.
     * End result: "./data/BobbyWasabiTasks.txt" will be present.
     *
     * @throws BobbyWasabiException If the folder or file could not be created.
     */
    public void createDataStorage() throws BobbyWasabiException {
        File folder = new File(this.folderpath);
        File file = new File(this.filepath);

        // check if folder exists
        if (!folder.exists()) {
            // create the folder if it does not exist
            if (!folder.mkdirs()) {
                throw new BobbyWasabiException("Could not create the folder ./data!");
            }
        }

        // check if the file exists
        if (!file.exists()) {
            // create the file if it does not exist
            try {
                if (!file.createNewFile()) {
                    throw new BobbyWasabiException("Could not create the file!");
                }
            } catch (IOException e) {
                throw new BobbyWasabiException("Could not create the file!");
            }
        }

    }


    /**
     * Loads tasks from the data file and returns them as a list.
     *
     * @return ArrayList of tasks loaded from the data file.
     * @throws BobbyWasabiException If the file cannot be found or read.
     */
    public ArrayList<Task> load() throws BobbyWasabiException {
        try {
            ArrayList<Task> tasks = new ArrayList<>();

            File file = new File(this.filepath);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                Task task = taskParser(scanner.nextLine());
                tasks.add(task);
            }

            return tasks;
        } catch (FileNotFoundException e) {
            throw new BobbyWasabiException("File not found!");
        }
    }

    /**
     * Parses a string line from the data file into a Task object.
     *
     * @param line String representing a task (e.g., "T|read book|[X]")
     * @return Task created from the parsed string.
     * @throws BobbyWasabiException If the line cannot be parsed into a valid Task.
     */
    public Task taskParser(String line) throws BobbyWasabiException {
        String[] infos = line.split("\\|");

        String type = infos[0];
        String description = infos[1];
        boolean isMarked = infos[2].equals("[X]");


        if (type.equals("T")) {
            return new ToDo(description, isMarked);
        } else if (type.equals("D")) {

            LocalDateTime dateTime = Parser.parseDateString(infos[3]);
            return new Deadline(description, isMarked, dateTime);

        } else if (type.equals("E")) {
            return new Event(description, isMarked, infos[3], infos[4]);
        }

        return null;
    }

    /**
     * Saves the current list of tasks to the data file.
     * First clears the file, then writes each task in the list.
     *
     * @param tasks TaskList containing the tasks to be written to file.
     * @throws BobbyWasabiException If writing to the file fails.
     */
    public void updateDataFileFromTasks(TaskList tasks) throws BobbyWasabiException {
        try {
            // clear the current data file
            PrintWriter writer = new PrintWriter(this.filepath);
            writer.print("");
            writer.close();

            // update the fresh data file with current tasks
            for (int i = 0; i < tasks.size(); i++) {
                Task cur = tasks.get(i);
                String line = cur.getData();
                fileWrite(line);
            }

        } catch (FileNotFoundException | BobbyWasabiException e) {
            throw new BobbyWasabiException(e.getMessage());
        }

    }

    /**
     * Writes a single line (representing a task) to the data file.
     * Overwrites existing file content each time.
     *
     * @param line Line to be written to the data file.
     * @throws BobbyWasabiException If the file cannot be written to.
     */
    public void fileWrite(String line) throws BobbyWasabiException {
        try {
            FileWriter filewriter = new FileWriter(this.filepath);
            filewriter.write(line);
            filewriter.close();
        } catch (IOException e) {
            throw new BobbyWasabiException(e.getMessage());
        }
    }

}
