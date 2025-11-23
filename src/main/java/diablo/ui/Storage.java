package diablo.ui;

import diablo.task.Deadline;
import diablo.task.Event;
import diablo.task.Task;
import diablo.task.ToDo;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.ArrayList;


/**
 * Provides storage capability to the Diablo chatbot.
 * This class takes in a filepath string for reading and writing
 * task information obtained from Diablo to a text file located
 * via the specified filepath.
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;

        Path path = Paths.get(filePath);
        try {
            // First, check if the file exists on the file system (for development)
            if (!Files.exists(path)) {
                // If not, check if it's a resource within the JAR
                URL resourceUrl = getClass().getResource(filePath);
                if (resourceUrl == null) {
                    // If it's not on the file system or in the JAR,
                    // create the parent directories and then the file.
                    Path parentDir = path.getParent();
                    if (parentDir != null && !Files.exists(parentDir)) {
                        Files.createDirectories(parentDir);
                    }
                    Files.createFile(path);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to handle storage file at " + filePath, e);
        }
    }

    /**
     * Reads the file specified by the filePath string and returns an ArrayList of tasks stored in the file.
     *
     * @return ArrayList of Task objects.
     * @throws IOException If file does not exist.
     */
    public ArrayList<Task> readDiablo() throws IOException {
        // Initialise string list
        ArrayList<String> listOfStrings = new ArrayList<>();

        // Initialise output task list
        ArrayList<Task> taskList = new ArrayList<>();

        // Load data
        BufferedReader bf = new BufferedReader(new FileReader(filePath));

        // Read first line as string
        String line = bf.readLine();

        // Read rest of strings until end of file
        while (line != null) {
            listOfStrings.add(line);
            line = bf.readLine();
        }

        // Close bufferreader object
        bf.close();

        // Add tasks from strings
        for (int i = 0; i < listOfStrings.size(); i++) {
            String[] taskDetails = listOfStrings.get(i).split("___");

            // Add specific task from initial element
            switch (taskDetails[0]) {
            case "T":
                Task toDo = new ToDo(taskDetails[2]);
                taskList.add(toDo);
                break;

            case "D":
                Task deadline = new Deadline(taskDetails[2], taskDetails[3]);
                taskList.add(deadline);
                break;

            case "E":
                Task event = new Event(taskDetails[2], taskDetails[3], taskDetails[4]);
                taskList.add(event);
                break;
            }

            // Completes task if it is already done
            if (taskDetails[1].equals("1")) {
                taskList.get(i).complete();
            }

        }

        return taskList;
    }

    /**
     * Writes a list of strings from an ArrayList to the file specified by the Storage object's filePath.
     *
     * @param listOfStrings ArrayList of strings to write to the file.
     * @throws IOException If the file does not exist.
     */
    public void writeDiablo(ArrayList<String> listOfStrings) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        for (int i = 0; i < listOfStrings.size(); i++) {
            writer.append(listOfStrings.get(i));
            writer.append("\n");
        }
        writer.close();
    }
}
