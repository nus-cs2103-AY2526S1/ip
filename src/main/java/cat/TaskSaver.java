package cat;

import java.io.IOException;
import java.util.ArrayList;
import java.io.FileWriter;

/**
 * Handles saving a list of tasks to a text file.
 * Each task is formatted using its getFormat() method before writing.
 */
public class TaskSaver {

    /** The name of the file to save tasks to. */
    String fileName = "meow.txt";

    /**
     * Saves the given list of tasks to the specified file.
     * Each task is written on a new line using its getFormat() representation.
     *
     * @param tasks The list of Task objects to save.
     */
    public void save(ArrayList<Task> tasks) {

        try (FileWriter writer = new FileWriter(fileName)) {
            for (Task t : tasks) {
                writer.write(t.getFormat());
                writer.write("\n");
            }
            //System.out.println("Tasks saved to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
