package storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import tasks.DeadlineTask;
import tasks.EventTask;
import tasks.Task;
import tasks.TodoTask;

public class Storage {
    private static final String SAVE_FILE_PATH = "./data/savedTasks.txt";
    
    /**
     * Saves all tasks in taskList based on their .getSaveString() value into savedTasks.txt
     * 
     * @param taskList ArrayList of all Tasks added
     */
    public static void saveTaskList(ArrayList<Task> taskList) {
        Path path = Paths.get(SAVE_FILE_PATH);
        try {
            Files.createDirectories(path.getParent());
            if (!Files.exists(path)) {
                Files.createFile(path);
            }
            
            ArrayList<String> saveList = new ArrayList<>();
            for (Task task: taskList) {
                saveList.add(task.getSaveString());
            }
            Files.write(path, saveList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Parses all tasks from savedTasks.txt and restores them into taskList
     * 
     * @param taskList ArrayList where all tasks are to be stored
     */
    public static void restoreTaskList(ArrayList<Task> taskList) {
        Path path = Paths.get(SAVE_FILE_PATH);
        try {
            Files.createDirectories(path.getParent());
            if (!Files.exists(path)) {
                Files.createFile(path);
            }
            
            List<String> taskStrings = Files.readAllLines(path);
            for (String taskString : taskStrings) {
                String[] split = taskString.split("\\|\\|\\|");
                String type = split[0];
                boolean mark = Boolean.parseBoolean(split[1]);
                String description = split[2].trim();
                Task task = null;
                
                switch (type) {
                case "Todo":
                    task = new TodoTask(description);
                    break;
                case "Deadline":
                    String deadline = split[3];
                    task = new DeadlineTask(description, deadline);
                    break;
                case "Event":
                    String from = split[3];
                    String to = split[4];
                    task = new EventTask(description, from, to);
                    break;
                default:
                    continue;
                } 
                taskList.add(task);
                if (mark && task != null) {
                    task.setMarked(true);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
