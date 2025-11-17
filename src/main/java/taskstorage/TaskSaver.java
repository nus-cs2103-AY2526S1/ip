package taskstorage;

import customexceptions.IncompleteTaskException;
import listmanager.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Stores <code>Task</code> objects in the task list to a file
 * Can load in tasks from an external file.
 */
public class TaskSaver {

    private String filePath;


    private String getFilePath() {
        //get file path for storage
        try {
            //gets path of the JAR file
            File jarFile = new File(TaskSaver.class.getProtectionDomain()
                                .getCodeSource().getLocation()
                                .toURI());
            String filePath = jarFile.getPath();

            if (filePath.endsWith(".jar")) { //this is to differentiate between running in IDE vs JAR
                //this solution was suggested by claude AI
                File parentDirectory = jarFile.getParentFile();
                return parentDirectory.getPath();
            } else {
                //if using IDE default to this
                return System.getProperty("user.dir");
            }
        } catch (URISyntaxException e) {
            e.getMessage();
        }
        return null;
    }


    /**
     * Saves currently stored <code>Task</code> objects to a file in string format.
     *
     * @param taskList List containing stored <code>Task</code> objects.
     */
    //The idea of utilizing Printwriter originates from consulting with Claude AI on ways to read and write to files.
    public void saveTasks(List<Task> taskList) {
        String directory = getFilePath();
        System.out.println(directory);
        File dir = new File(directory);

        File newFile = new File(dir, "Tasks.txt");
        System.out.println(newFile);
        System.out.println("Saving tasks");
        try (PrintWriter writer = new PrintWriter(new FileWriter(newFile))) {

            for (Task task : taskList) {
                writer.println(task.toStringFormat());
            }
        } catch (Exception e) {
            System.out.println("failed to save");
            e.printStackTrace();
        }
    }

    /**
     * Load tasks from a file.
     * Prints "File not found" if no file exists.
     * @return A <code>List</code> object containing <code>Task</code> objects.
     */
    //Use of Claude AI to figure out scanner.nextLine().trim() to fix errors.
    public List<Task> loadTasks() {
        String directory = getFilePath();
        File dir = new File(directory);
        System.out.println(dir);

        List<Task> taskList = new ArrayList<Task>();
        try (Scanner scanner = new Scanner(new File(dir, "Tasks.txt"))) {//searches for txt file in the dir
            while (scanner.hasNextLine()) {
                String taskLine = scanner.nextLine().trim(); //remove trailing spaces to fix error (suggested by Claude)
                if (!taskLine.isEmpty()) {
                    taskList.add(Task.stringToTask(taskLine));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IncompleteTaskException e) {
            System.out.println(e.getMessage());
        }
        return taskList;
    }
}
