package dino;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Stores the task list data.
 * Allows reading and writing the data file.
 */
public class Storage {
    private File folder;
    private File file;

    /**
     * Constructs a Storage object and ensures that the path exist.
     *
     * @param filePath path to the file where tasks will be stored
     * @throws IOException if the file cannot be created
     */
    public Storage(String filePath) throws IOException {
        this.file = new File(filePath);
        this.folder = file.getParentFile();
        if (folder != null && !folder.exists()) {
            folder.mkdirs();
        }
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    /**
     * Loads tasks from the storage file.
     *
     * @return an ArrayList of Task object from the file
     * @throws IOException if the file cannot be read
     */
    public ArrayList<Task> loadData() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String task = scanner.nextLine();
            try {
                String type = task.substring(0, 1);
                Task temp = null;
                if (type.equals("T")) {
                    String[] parts = task.split(" \\| ", 4);
                    String description = parts[2];
                    temp = new Todo(description);
                    if (parts.length > 3 && !parts[3].isEmpty()) {
                        temp.setTag(parts[3]);
                    }
                    if (parts[1].equals("1")) { temp.markAsDone(); }
                } else if (type.equals("D")) {
                    String[] parts = task.split(" \\| ", 5);
                    String description = parts[2];
                    String deadline = parts[3];
                    temp = new Deadline(description, deadline);
                    if (parts.length > 4 && !parts[4].isEmpty()) {
                        temp.setTag(parts[4]);
                    }
                    if (parts[1].equals("1")) { temp.markAsDone(); }
                } else if (type.equals("E")) {
                    String[] parts = task.split(" \\| ", 6);
                    String description = parts[2];
                    String start = parts[3];
                    String end = parts[4];
                    temp = new Event(description, start, end);
                    if (parts.length > 5 && !parts[5].isEmpty()) {
                        temp.setTag(parts[5]);
                    }
                    if (parts[1].equals("1")) { temp.markAsDone(); }
                }
                tasks.add(temp);

            } catch (Exception e) {
                System.out.println("Error getting data.");
            }
        }
        scanner.close();
        return tasks;
    }

    /**
     * Saves the given list of tasks to the storage file.
     *
     * @param tasks ArrayList of tasks to save
     * @throws IOException if the file cannot be edited
     */
    public void saveData(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(file);
        for (Task t: tasks) {
            fw.write(t.toStoreFormat() + System.lineSeparator());
        }
        fw.close();
    }

    public static void main(String[] args) {
        try {
            Storage storage = new Storage("data/dino.txt");
            ArrayList<Task> tasks = storage.loadData();
            for (Task t : tasks) {
                System.out.println(t.toStoreFormat());
            }
            //test
            Task newTodo = new Todo("homework");
            tasks.add(newTodo);
            Task newDeadline = new Deadline("lecture", "2025-11-09 1800");
            tasks.add(newDeadline);
            newDeadline.markAsDone();
            Task newEvent = new Event("tutorial", "4pm", "6pm");
            tasks.add(newEvent);
            storage.saveData(tasks);
            System.out.println("New tasks are saved successfully!");
        } catch (IOException e) {
            System.out.println("Error when accessing storage.");
        }
    }

}