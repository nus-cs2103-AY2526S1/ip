package stella;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;

import java.io.IOException;
import java.io.FileNotFoundException;

import java.util.ArrayList;


/**
 * Responsible for loading tasks from the local storage
 * and saving tasks to the local storage
 */
public interface Storage {
    public final String DATA_STORAGE_PATH = "../data/stella.txt";

    /**
     * Return task with corresponding details (e.g. task description, type of task,
     * whether task is completed or not) depending on description
     *
     * @param description Specify task to be created
     * @return Corresponding task that matches description given
     */
    public static Task createTask(String description) {
        Task newTask = null;
        if (description.charAt(1) == 'T') {
            newTask = new ToDo(description.substring(7));
        } else if (description.charAt(1) == 'D') {
            int pointer1 = description.indexOf("(by: ");
            newTask = new Deadline(description.substring(7, pointer1 - 1),
                    description.substring(pointer1 + 5, description.length() - 1));
        } else if (description.charAt(1) == 'E') {
            int pointer1 = description.indexOf(" (from: ");
            int pointer2 = description.indexOf(" | to: ");
            newTask = new Event(description.substring(7, pointer1),
                    description.substring(pointer1 + 8, pointer2),
                    description.substring(pointer2 + 7, description.length() - 1));
        }

        if (description.charAt(7) == 'X') {
            newTask.markDone();
        }

        return newTask;
    }

    /**
     * Read in the .txt input from local storage and returns a list containing
     * the corresponding tasks.Create the .txt file if no such input .txt file found.
     *
     * @return ArrayList<Task> containing tasks based on information stored in local storage
     */
    public static ArrayList<Task> readFile() {
        for (int attempt = 1; attempt <= 2; attempt++) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader((DATA_STORAGE_PATH)));
                String taskDescription;
                ArrayList<Task> tasks = new ArrayList<>();

                while ((taskDescription = reader.readLine()) != null) {
                    tasks.add(Storage.createTask(taskDescription));
                }

                return tasks;
            } catch (FileNotFoundException e) {
                File folder = new File("../data");
                folder.mkdirs();
                File dataFile = new File(DATA_STORAGE_PATH);
            } catch (IOException e) {
                System.out.println("IOException: " + e.getMessage());
            }
        }
        return new ArrayList<>();
    }

    /**
     * Update list in local storage to include task
     *
     * @param task Task to be added to local storage
     */
    public static void addTask(Task task) {
        try {
            FileWriter writer = new FileWriter(DATA_STORAGE_PATH, true);
            writer.write(task.toString());
            writer.write(System.lineSeparator());
            writer.close();
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }

    /**
     * When list is recently modified, call this function to update the local storage
     *
     * @param list The newly modified list used to update local storage
     */
    public static void modifyTaskList(ArrayList<Task> list) {
        try {
            FileWriter writer = new FileWriter(DATA_STORAGE_PATH, false);
            for (int i = 0 ; i < list.size(); i++) {
                writer.write(list.get(i).toString());
                writer.write(System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }
}