package stella;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;

import java.io.IOException;
import java.io.FileNotFoundException;

import java.util.ArrayList;

import stella.task.Deadline;
import stella.task.Event;
import stella.task.Task;
import stella.task.ToDo;

/**
 * Responsible for loading tasks from the local storage
 * and saving tasks to the local storage
 */
public interface Storage {
    String DATA_STORAGE_PATH = "../data/stella.txt";
    String taskPrefixExample = "[T][X] ";
    int taskPrefixLength = taskPrefixExample.length();

    /**
     * Return task with corresponding details (e.g. task description, type of task,
     * whether task is completed or not) depending on description
     *
     * @param description Specify task to be created
     * @return Corresponding task that matches description given
     */
    static Task createTask(String description) {
        Task newTask = null;
        int indexForTaskType = 1;
        int indexForTaskCompletion = 4;

        if (description.charAt(indexForTaskType) == 'T') {
            newTask = Storage.createToDo(description);
        } else if (description.charAt(indexForTaskType) == 'D') {
            newTask = Storage.createDeadline(description);
        } else if (description.charAt(indexForTaskType) == 'E') {
            newTask = Storage.createEvent(description);
        } else {
            System.out.println("Invalid Data Type");
        }

        if (description.charAt(indexForTaskCompletion) == 'X') {
            newTask.markDone();
        }

        return newTask;
    }

    private static ToDo createToDo(String description) {
        String token1 = " (Priority: ";

        int startOfToken1 = description.indexOf(token1);

        int endOfToken1 = startOfToken1 + token1.length();

        String details = description.substring(taskPrefixLength, startOfToken1);
        String priorityLevel = description.substring(endOfToken1, description.length() - 1);

        return new ToDo(details, Priority.valueOf(priorityLevel));
    }

    private static Deadline createDeadline(String description) {
        String token1 = " (by: ";
        String token2 = ") (Priority: ";

        int startOfToken1 = description.indexOf(token1);
        int startOfToken2 = description.indexOf(token2);

        int endOfToken1 = startOfToken1 + token1.length();
        int endOfToken2 = startOfToken2 + token2.length();

        String details = description.substring(taskPrefixLength, startOfToken1);
        String deadline = description.substring(endOfToken1, startOfToken2);
        String priorityLevel = description.substring(endOfToken2, description.length() - 1);

        return new Deadline(details, deadline, Priority.valueOf(priorityLevel));
    }

    private static Event createEvent(String description) {
        String token1 = " (from: ";
        String token2 = " | to: ";
        String token3 = ") (Priority: ";

        int startOfToken1 = description.indexOf(token1);
        int startOfToken2 = description.indexOf(token2);
        int startOfToken3 = description.indexOf(token3);

        int endOfToken1 = startOfToken1 + token1.length();
        int endOfToken2 = startOfToken2 + token2.length();
        int endOfToken3 = startOfToken3 + token3.length();

        String details = description.substring(taskPrefixLength, startOfToken1);
        String start = description.substring(endOfToken1, startOfToken2);
        String end = description.substring(endOfToken2, startOfToken3);
        String priorityLevel = description.substring(endOfToken3, description.length() - 1);

        return new Event(details, start, end, Priority.valueOf(priorityLevel));
    }

    /**
     * Read in the .txt input from local storage and returns a list containing
     * the corresponding tasks.Create the .txt file if no such input .txt file found.
     *
     * @return ArrayList<Task> containing tasks based on information stored in local storage
     */
    static ArrayList<Task> readFile() {
        for (int attempt = 1; attempt <= 2; attempt++) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(DATA_STORAGE_PATH));
                ArrayList<Task> tasks = new ArrayList<>();
                String taskDescription = reader.readLine();

                while (taskDescription != null) {
                    Task currentTask = Storage.createTask(taskDescription);
                    tasks.add(currentTask);
                    taskDescription = reader.readLine();
                }

                return tasks;
            } catch (FileNotFoundException e) {
                File folder = new File("../data");
                folder.mkdirs();
                new File(DATA_STORAGE_PATH);
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
    static void addTask(Task task) {
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
    static void modifyTaskList(ArrayList<Task> list) {
        try {
            FileWriter writer = new FileWriter(DATA_STORAGE_PATH, false);

            for (int i = 0 ; i < list.size(); i++) {
                String taskDescription = list.get(i).toString();
                writer.write(taskDescription);
                writer.write(System.lineSeparator());
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }
}