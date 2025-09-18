package marcus;

import marcus.task.DeadlineTask;
import marcus.task.EventTask;
import marcus.task.Task;
import marcus.task.ToDoTask;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class SaveFileManager {
    private static final String path = "./data/marcus.txt";

    /**
     * Creates a save file if a save file has yet to be created.
     */
    public static void init() {
        File file = new File(path);
        //creates parent directory if missing
        file.getParentFile().mkdirs();
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Save file creation failed");
            }
        }
    }

    /**
     * Writes to the save file
     *
     * @param text Tasks details saved in an easy to parse format.
     */
    public static void writeToFile(String text) {
        assert text != null : "Text should not be null";

        try {
            FileWriter fw = new FileWriter(path);
            fw.write(text);
            fw.close();
        } catch (IOException e) {
            System.out.println("Failed to write to save file");
        }
    }

    /**
     * Reads from the save file.
     * It converts the data in the file to an ArrayList of Tasks.
     *
     * @return An ArrayList of the tasks stored in the save file.
     */
    public static ArrayList<Task> readFromFile() throws FileNotFoundException {
        File file = new File(path);
        Scanner s = new Scanner(file);
        ArrayList<Task> tasks = new ArrayList<>();
        int listSize = 0;

        //Assume that the file content is always of a valid format
        while (s.hasNext()) {
            String[] newTask = s.nextLine().split("\\|");

            if (newTask[0].equals("T")) {
                tasks.add(new ToDoTask(newTask[2]));
            } else if (newTask[0].equals("D")) {
                tasks.add(new DeadlineTask(newTask[2], LocalDate.parse(newTask[3])));
            } else {
                tasks.add(new EventTask(newTask[2], newTask[3], newTask[4]));
            }

            if (newTask[1].equals("1")) {
                tasks.get(listSize).markComplete();
            }

            listSize++;
        }
        return tasks;
    }
}


