package storage;

import candy.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import exceptions.InvalidTaskReadException;
import tasks.DeadlineTask;
import tasks.EventTask;
import tasks.Task;
import tasks.TaskInformation;
import tasks.TodoTask;

/**
 * Represents a storage that handle edits to file
 */
public class Storage {
    private File dataFolder;
    private File candyStorage;

    /**
     * Constructs a storage file
     *
     * @param filePath path to the storage file
     * @throws IOException if file cannot be created
     */
    public Storage(String filePath) {
        try {
            //chatGPT taught me about the File class
            //which I never heard of before this
            //clarified how the functions worked with
            //chatGPT after reading the docs
            this.candyStorage = new File(filePath);
            this.dataFolder = candyStorage.getParentFile();
            //makes folder and file if doesn't exist
            if (dataFolder != null && !dataFolder.exists()) {
                boolean isFolderCreated = dataFolder.mkdir();
                assert isFolderCreated : "Error: Folder does not exist";
            }
            if (!candyStorage.exists()) {
                boolean isFileCreated = candyStorage.createNewFile();
                assert isFileCreated : "Error: FIle does not exist";
            }
        } catch (IOException e) {
            Ui.printError(e);
        }
    }


    /**
     * Writes to storage file
     *
     * @param string string description of the task
     * @param toAppend true to add on, false to overwrite the current file
     */
    public void write(String string, boolean toAppend) {
        try {
            //asked chatGPT about existing methods we can use to write to a file
            FileWriter writer = new FileWriter(candyStorage, toAppend);
            writer.write(string);
            writer.close();
        } catch (IOException e) {
            Ui.printError(e);
        }
    }

    /**
     * Returns arraylist of the tasks as String
     */
    public ArrayList<String> readToString() {
        ArrayList<String> list = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(candyStorage);
            //scan each line in the file for task
            while (scanner.hasNextLine()) {
                String toAdd = scanner.nextLine();
                list.add(toAdd);
            }
            scanner.close();
        } catch (Exception e) {
            Ui.printError(e);
        }
        return list;
    }

    /**
     * Returns a todo task
     * Extracts data from string to create the task
     *
     * @param parts Information of task that has been split
     *              into an array
     */
    public Task createTodo(String[] parts) {
        boolean isDone = parts[1].trim().equals("X");
        String description = parts[2].trim();

        String infoString = "todo " + description;
        TaskInformation info = new TaskInformation(infoString, "todo");
        Task task = new TodoTask(info);
        if (isDone) {
            task.markDone();
        }
        return task;
    }

    /**
     * Returns a deadline task
     * Extracts data from string to create the task
     *
     * @param parts Information of task that has been split
     *              into an array
     */
    public Task createDeadline(String[] parts) {
        boolean isDone = parts[1].trim().equals("X");
        String description = parts[2].trim();

        //deadline task has end time
        String end = parts[3].trim();
        String infoString ="deadline " + description + " /by " + end;
        TaskInformation info = new TaskInformation(infoString, "deadline");
        Task task = new DeadlineTask(info);
        if (isDone) {
            task.markDone();
        }
        return task;
    }

    /**
     * Returns an event task
     * Extracts data from string to create the task
     *
     * @param parts Information of task that has been split
     *              into an array
     */
    public Task createEvent(String[] parts) {
        boolean isDone = parts[1].trim().equals("X");
        String description = parts[2].trim();

        //event task have start and end time
        String start = parts[3].trim();
        String end = parts[4].trim();
        String infoString = "event " + description + " /from " + start + " /to " + end;
        TaskInformation info = new TaskInformation(infoString, "event");
        Task task = new EventTask(info);
        if (isDone) {
            task.markDone();
        }
        return task;
    }

    /**
     * Returns Task with information from the string
     *
     * @param taskString String of information of task from the storage
     */
    public Task stringToTask(String taskString) {
        //Splits information into type, whether it's done and description
        //universal for todo, deadline and event tasks
        //chatGPT taught me about split method
        //and trim
        String[] parts = taskString.split("\\|");
        String type = parts[0].trim();

        //create the task
        if (type.equals("T")) {
            return createTodo(parts);
        } else if (type.equals("D")) {
            return createDeadline(parts);
        } else if (type.equals("E")) {
            return createEvent(parts);
        } else {
            //should not reach here
            throw new InvalidTaskReadException();
        }
    }

    /**
     * Returns arraylist of the tasks as Task
     */
    public ArrayList<Task> readToTask() {
        ArrayList<Task> list = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(candyStorage);
            //scan each line in the file for task
            while (scanner.hasNextLine()) {
                String taskString = scanner.nextLine();
                Task task = stringToTask(taskString);
                list.add(task);
            }
            scanner.close();
        } catch (FileNotFoundException | InvalidTaskReadException e) {
            Ui.printError(e);
        }
        return list;
    }
}
