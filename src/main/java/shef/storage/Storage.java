package shef.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import shef.exception.CorruptFileException;
import shef.task.DeadlineTask;
import shef.task.EventTask;
import shef.task.Task;
import shef.task.TodoTask;
import shef.tasklist.TaskList;

/*
dude.storage.Storage format:
<type>,<is_done>,<task_name>,[by|from,to]

types:
T: todo task
D: deadline task
E: event task

e.g.
T,1,read book
D,0,return book,June 6th
E,0,project meeting,Aug 6th 2pm,4pm
T,1,join sports club
*/

/**
 * Object that handles file IO for chatbot.
 */
public class Storage {
    private static final String DELIMITER = ",";

    private final File file;

    /**
     * Returns a Storage object that handles file IO.
     * @param filePath
     */
    public Storage(String filePath) {
        this.file = new File(filePath);

        // Create file if it does not exist
        try {
            file.getParentFile().mkdirs();
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes content into file.
     *
     * @param contents to write into the file.
     */
    public void write(String contents) {
        try {
            FileWriter fw = new FileWriter(file);
            fw.write(contents);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads contents from file and adds into list of tasks.
     * Assumes file type is csv.
     *
     * @param tasks TaskList object.
     */
    public void read(TaskList tasks) {
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                tasks.add(parseFileLine(line));
            }
        } catch (FileNotFoundException | CorruptFileException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private Task parseFileLine(String line) {
        String[] parts = line.split(DELIMITER);
        boolean isDone = parts[1].equals("1");

        switch (parts[0]) {
        case "T":
            return new TodoTask(parts[2], isDone);
        case "D":
            return new DeadlineTask(parts[2], isDone, parts[3]);
        case "E":
            return new EventTask(parts[2], isDone, parts[3], parts[4]);
        default:
            throw new CorruptFileException();
        }
    }
}
