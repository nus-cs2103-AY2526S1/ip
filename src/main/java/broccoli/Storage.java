package broccoli;

import broccoli.Tasks.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Manages data for task storage and retrieval.
 * Handles loading from and writing to the data file.
 */
public class Storage {
    private TaskList taskList;
    public String filePath;

    public Storage(TaskList taskList, String filePath){
        assert taskList != null : "TaskList cannot be null";
        assert filePath != null : "File path cannot be null";
        assert !filePath.trim().isEmpty() : "File path cannot be empty";
        this.taskList = taskList;
        this.filePath = filePath;
    }

    public String getFilePath(){
        return this.filePath;
    }


    /**
     * Writes all tasks from the task list to the storage file.
     * Creates the data directory if it doesn't exist. Each task is written
     * on a separate line using the task's text representation.
     * Handles IOException by printing error message to console.
     */
    public void writeToFile() {
        try {
            Path data = Paths.get("./data");
            if (!Files.exists(data)) {
                Files.createDirectories(data);
            }
            FileWriter fw = new FileWriter(this.filePath);
            for(Task task : taskList.getList()){
                String taskContent = task.taskText();
                fw.write(taskContent + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from the specified file into the task list.
     * Parses each line of the file as a task and adds valid tasks to the list.
     * Returns immediately if the file doesn't exist.
     *
     * @param filePath The path to the file containing saved tasks
     * @throws FileNotFoundException handled internally with error message output
     */
    public void loadFromFile(String filePath){
        try{
            File file = new File(filePath);
            if(!file.exists()) {
                return;
            }
            Scanner textScanner = new Scanner(file);
            while(textScanner.hasNextLine()){
                String content = textScanner.nextLine();
                Task task = Task.parseTask(content);
                if(task != null) {
                    taskList.add(task);
                }
            }
            textScanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public ArrayList<Task> getTaskList(){
        return taskList.getList();
    }

}
