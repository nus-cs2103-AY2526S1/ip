package LeeKuanYew;

import LeeKuanYew.Task.TaskList;
import LeeKuanYew.Task.Task;
import LeeKuanYew.Task.ToDoTask;
import LeeKuanYew.Task.DeadlineTask;
import LeeKuanYew.Task.EventTask;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads saved data when program is ran.
     *
     * If the file does not exist, an empty TaskList is returned. Each non-empty
     * line in the file is parsed into a Task and added to the list, with the use of
     * parseTask method.
     *
     * @return a TaskList containing the tasks loaded from the file
     * @throws IOException if an error occurs while reading the file
     */
    public TaskList load() throws IOException {
        TaskList tasks = new TaskList();
        File file = new File(filePath);

        if (!file.exists()) {
            return tasks;
        }

        Scanner sc = new Scanner(file);
        while(sc.hasNextLine()) {
            String line = sc.nextLine();
            if (!line.isEmpty()) {
                tasks.add(parseTask(line));
            }
        }

        sc.close();
        return tasks;
    }

    /**
     * Saves current task list into data file
     *
     * Format each task in TaskList into text, and writes it into data file.
     * Existing content in data file is overwritten.
     *
     * @param tasks the TaskList to save
     * @throws IOException
     */
    public void save(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);

        for (int i = 0; i < tasks.size(); i++) {
            fw.write(formatTask(tasks.get(i)));
        }

        fw.close();
    }

    /**
     * Parses a line from the storage file and converts it into a Task object.
     *
     * The line should be in the format produced by formatTask, with fields
     * separated by '|'. Supports ToDoTask, DeadlineTask, and EventTask types.
     *
     * @param line a single line from the storage file representing a task
     * @return a Task object corresponding to the parsed line
     * @throws IllegalArgumentException if the task type is unrecognized
     */
    private Task parseTask(String line) {
        String[] parts = line.split("\\|");
        String taskType = parts[0];
        boolean isDone = parts[1].equals("1");
        String taskDetails = parts[2];

        if (taskType.equals("T")) {
            ToDoTask toDoTask = new ToDoTask(taskDetails);
            if (isDone) {
                toDoTask.markDone();
            }
            return toDoTask;
        } else if (taskType.equals("D")) {
            DeadlineTask deadlineTask = new DeadlineTask(taskDetails, parts[3]);
            if (isDone) {
                deadlineTask.markDone();
            }
            return deadlineTask;
        } else if (taskType.equals("E")) {
            EventTask eventTask = new EventTask(taskDetails, parts[3], parts[4]);
            if (isDone) {
                eventTask.markDone();
            }
            return eventTask;
        } else {
            throw new IllegalArgumentException("Unknown LeeKuanYew.Task.LeeKuanYew.Task Type: " + taskType);
        }
    }

    /**
     * Formats a Task into a string representation for storage.
     *
     * The format is:
     * - ToDoTask:   T|isDone|description
     * - DeadlineTask: D|isDone|description|deadline
     * - EventTask:   E|isDone|description|startDate|endDate
     *
     * Each entry ends with a newline character.
     *
     * @param task the Task to format
     * @return the formatted string representation of the task
     */
    protected String formatTask(Task task) {
        String isDone = task.checkDone() ? "1" : "0";

        if (task instanceof ToDoTask) {
            return "T|" + isDone + "|" + task.getDescription() + "\n";
        } else if (task instanceof DeadlineTask d) {
            return "D|" + isDone + "|" + d.getDescription() + "|" + d.getDeadline() + "\n";
        } else if (task instanceof EventTask e) {
            return "E|" + isDone + "|" + e.getDescription()
                    + "|" + e.getStartDate() + "|" + e.getEndDate() + "\n";
        } else {
            return "\n";
        }
    }


}
