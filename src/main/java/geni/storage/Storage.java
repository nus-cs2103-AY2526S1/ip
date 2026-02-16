package geni.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import geni.exception.GeniException;
import geni.task.Deadline;
import geni.task.Event;
import geni.task.Task;
import geni.task.Todo;

/**
 * Handles saving and loading tasks to and from a file.
 * Ensures that the user's task list persists across sessions.
 */
public class Storage {
    private String filePath;
    /**
     * Creates a {@code Storage} object with the given file path.
     *
     * @param filePath location of the save file
     */

    public Storage(String filePath) {
        assert filePath != null && !filePath.isEmpty() : "Storage.filePath must be non-null and non-empty";
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file and converts them into  Task objects.
     *
     * @return a list of saved tasks
     */
    public ArrayList<Task> load() {
        ArrayList<Task> storeTask = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new File(filePath));
            while (sc.hasNextLine()) {
                String inp = sc.nextLine().trim();
                if (inp.isEmpty()) {
                    continue;
                }

                String[] parts = inp.split("\\|");
                for (int i = 0; i < parts.length; i++) {
                    parts[i] = parts[i].trim();
                }

                String type = parts[0];
                boolean isDone = parts[1].equals("1");

                switch (type) {
                case "T":
                    Task todo = new Todo(parts[2]);
                    if (isDone) {
                        todo.markAsDone();
                    }
                    storeTask.add(todo);
                    break;
                case "D":
                    Task deadline = new Deadline(parts[2], parts[3]);
                    if (isDone) {
                        deadline.markAsDone();
                    }
                    storeTask.add(deadline);
                    break;
                case "E":
                    String[] timeline = parts[3].split(" - ");
                    Task event = new Event(parts[2], timeline[0], timeline[1]);
                    if (isDone) {
                        event.markAsDone();
                    }
                    storeTask.add(event);
                    break;
                default:
                    continue;
                }

            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (GeniException e) {
            System.out.println("please enter valid input");
        }
        return storeTask;
    }

    /**
     * Removes a given task from the save file.
     *
     * @param taskToDelete the task to delete
     */
    public void savedelete(Task taskToDelete) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));

            for (int i = 0; i < lines.size(); i++) {
                if (lines.get(i).equals(taskToDelete.toSaveFormat())) {
                    lines.remove(i);
                    break;
                }
            }
            Files.write(Paths.get(filePath), lines);
        } catch (IOException e) {
            System.out.println("Error deleting task: " + e.getMessage());
        }
    }
    /**
     * Updates a task in the save file at the given index.
     *
     * @param newTask updated task
     * @param index   position of the task to update
     */
    public void saveMarkReplace(Task newTask, int index) {


        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));

            if (index >= 0 && index < lines.size()) {
                lines.set(index, newTask.toSaveFormat());
            }

            Files.write(Paths.get(filePath), lines);
        } catch (IOException e) {
            System.out.println("Error updating task: " + e.getMessage());
        }

    }

    /**
     * Appends a new task to the save file.
     *
     * @param task the task to save
     */

    public void saveAdd(Task task) {
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(filePath, true));
            pw.println(task.toSaveFormat());
            pw.close();
        } catch (IOException e) {
            System.out.println("Error adding task: " + e.getMessage());
        }
    }


}
