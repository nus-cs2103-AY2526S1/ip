package jerome;

import jerome.task.Deadline;
import jerome.task.Event;
import jerome.task.Task;
import jerome.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Represents storage that loads and saves tasks input by user.
 */
public class Storage {
    private String path;

    public Storage(String path) {
        this.path = path;
    }

    /**
     * Provides tasks list previously saved by user
     * @return <code>ArrayList</code> containing previously saved tasks
     */
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(path);
        try {
            if (!file.exists()) {
                return tasks;
            }
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                Task t = parseTask(line);
                if (t!=null) {
                    tasks.add(t);
                }
            }
            file.createNewFile();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to read file");
        } catch (IOException e) {
            System.out.println("Something went wrong with file creation");
        }
        return tasks;
    }

    /**
     * Saves the array of tasks to .txt file at <code>path</code>
     */
    public void save(ArrayList<Task> tasks) {
        try {
            FileWriter fw = new FileWriter(path);
            for (Task task : tasks) {
                fw.write(task.toSaveFormat() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Tasks failed to save: " + e.getMessage());
        }
    }

    /**
     * Provides the corresponding task based on the String representation for .txt storage
     * @return <code>Task</code> represented by the String
     */
    public Task parseTask(String line){
        String[] parts = line.split(" \\| ");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String desc = parts[2];
        switch (type) {
        case "T":
            return new Todo(desc, isDone);
        case "D":
            return new Deadline(desc, isDone, parts[3]);
        case "E":
            return new Event(desc, isDone, parts[3], parts[4]);
        default:
            return null;

        }
    }
}
