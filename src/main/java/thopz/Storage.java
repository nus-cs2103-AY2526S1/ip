package thopz;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Storage class used to store and save data
 */

public class Storage {

    private final String fileLocation;

    /**
     * Constructs a Storage to store lists which where previously created
     *
     * @param fileLocation  location of file to save date
     *
     */

    public Storage(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    /**
     * Save the various tasks to the file
     */

    public void save(List<Task> tasks) throws IOException {

        FileWriter fw = new FileWriter(fileLocation);
        for (Task t : tasks) {
            fw.write(t.saveformat() + "\n");
        }
        fw.close();
    }

    /**
     * Load the tasks from the file
     * Mark the specific tasks depending on whether it was marked
     */
    public ArrayList<Task> load() throws IOException {

        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(fileLocation);

        if (!file.exists()) {
            throw new IOException("File doesn't exist");
        }

        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();


            if (line.startsWith("T")) {
                String[] splits = line.split("/");
                boolean isMarked = splits[1].trim().equals("1");
                Todo t = new Todo(splits[2].trim());
                if (isMarked) {
                    t.markTask();
                }
                tasks.add(t);

            } else if (line.startsWith("D")) {
                String[] splits = line.split("/");
                boolean isMarked = splits[1].contains("1");
                Deadline d = new Deadline(splits[2].trim(), splits[3].trim());
                if (isMarked) {
                    d.markTask();
                }
                tasks.add(d);

            } else if (line.startsWith("E")) {
                String[] splits = line.split("/");
                boolean isMarked = splits[1].contains("1");
                Events e = new Events(splits[2].trim(), splits[3].trim(), splits[4].trim());
                if (isMarked) {
                    e.markTask();
                }
                tasks.add(e);
            }
        }
        scanner.close();
        return tasks;
    }

}
