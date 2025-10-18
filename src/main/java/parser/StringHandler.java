package parser;

import storage.FileHandler;
import tasks.Task;

/**
 * Abstracts out functions related to processing string inputs like
 * mark x, delete x, unmark x
 */
public class StringHandler {

    /**
     * Processes input: delete x
     */
    public static String delete(String userInput) {
        String[] words = userInput.split("\\s+");
        int taskNumber = Integer.parseInt(words[1]) - 1;
        Task deletedTask = Constants.TASK_LIST.remove(taskNumber);
        FileHandler.save();
        return (Constants.REMOVETASK
                + deletedTask + "\n"
                + Helper.tasksLeft(Constants.TASK_LIST.size()));
    }

    /**
     * Processes input: unmark x
     */
    public static String unmark(String userInput) {
        String[] words = userInput.split("\\s+");
        int taskNumber = Integer.parseInt(words[1]) - 1;
        Constants.TASK_LIST.get(taskNumber).markNotDone();
        FileHandler.save();
        return (Constants.MARKNOTDONE
                + Constants.TASK_LIST.get(taskNumber));
    }

    /**
     * Processes input: mark x
     */
    public static String mark(String userInput) {
        String[] words = userInput.split("\\s+");
        int taskNumber = Integer.parseInt(words[1]) - 1;
        Constants.TASK_LIST.get(taskNumber).markDone();
        FileHandler.save();
        return (Constants.MARKASDONE
                + Constants.TASK_LIST.get(taskNumber));
    }

    /**
     * Processes input: find x
     */
    public static String find(String userInput) {
        return (Constants.FINDRESULTS + "\n"
                + findResults(userInput));
    }

    /**
     * Uses search to find matches according to user input
     * @param userInput for user input
     * @return matches as a string
     */
    public static String findResults(String userInput) {
        String[] parts = userInput.split("\\s+", 2);
        if (parts[1].isEmpty()) {
            return "Nothing here...";
        }

        String search = parts[1].toLowerCase();
        StringBuilder results = new StringBuilder();

        for (Task task : Constants.TASK_LIST) {
            if (task.getDescription().toLowerCase().contains(search)) {
                if (!results.isEmpty()) {
                    results.append("\n"); // add newline before appending next task
                }
                results.append("\t").append(task.toString());
            }
        }
        return !results.isEmpty()
                ? results.toString()
                : "No matches...";
    }

}
