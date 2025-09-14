package john;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import john.tasks.Deadline;
import john.tasks.Event;
import john.tasks.ToDo;

/**
 * A storage that loads and saves the task list data to a file.
 */
public class Storage {
    private String filePath;
    /**
     * Constructor for storage.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the data from save file into a TaskList.
     */
    public TaskList load() throws JohnException {
        TaskList list = new TaskList();
        File dataFile = new File(this.filePath);
        File parentDir = dataFile.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }
        if (!dataFile.exists()) {
            try {
                dataFile.createNewFile();
            } catch (IOException e) {
                throw new JohnException("Could not create save file.");
            }
        }
        try {
            Scanner fileReader = new Scanner(dataFile);
            while (fileReader.hasNext()) {
                String[] data = fileReader.nextLine().split(" \\| ");
                switch (data[0]) {
                case "T":
                    list.addTask(new ToDo(data[2], data[1].equals("1")));
                    break;
                case "D":
                    list.addTask(new Deadline(data[2], LocalDate.parse(data[3]), data[1].equals("1")));
                    break;
                case "E":
                    list.addTask(new Event(data[2], LocalDate.parse(data[3]),
                            LocalDate.parse(data[4]), data[1].equals("1")));
                    break;
                default:
                    // ChatGPT suggested to throw an exception here instead of doing nothing
                    throw new JohnException("Unrecognized task type in save file: " + data[0]);
                }
            }
            fileReader.close();
        } catch (DateTimeParseException e) {
            throw new JohnException("Unable to parse date from save file.");
        } catch (FileNotFoundException e) {
            throw new JohnException("Save file not found.");
        }
        return list;
    }

    /**
     * Saves the data from the TaskList to the save file.
     */
    public void save(TaskList list) throws JohnException {
        assert new File(this.filePath).exists() : "Save file does not exist.";
        try {
            FileWriter fileWriter = new FileWriter(this.filePath);
            for (int i = 1; i <= list.size(); i++) {
                fileWriter.write(list.get(i).writeString() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new JohnException("Unable to write to save file.");
        }
    }
}
