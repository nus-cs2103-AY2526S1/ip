package ember;

import ember.exception.InvalidInputException;
import ember.exception.MissingInformationException;
import ember.task.DeadlineTask;
import ember.task.EventTask;
import ember.task.Task;
import ember.task.TodoTask;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Manages a list of tasks, allowing addition, deletion,
 * marking, unmarking, and printing of tasks.
 * Also handles persisting tasks to a file via ListFileWriter.
 */
public class ListManager {
    public ArrayList<Task> list;

    /**
     * Constructs a new ListManager, initializes the task list,
     * and prepares the file writer for saving the list.
     */
    public ListManager() {
        try {
            ListFileWriter fileWriter = new ListFileWriter();
            this.list = ListFileWriter.readFromFile();
        } catch (MissingInformationException | InvalidInputException e) {
            System.out.println("Error reading tasks from file: " + e.getMessage());
            this.list = new ArrayList<>(); // fallback to empty list
        }
    }

    /**
     * Updates the saved task list text file to reflect the current list.
     */
    public void updateText() {
        ListFileWriter.listToText(list);
    }

    /**
     * Adds a task to the list, updates the file,
     * and prints confirmation to the console.
     *
     * @param task the Task object to add
     */
    public String addTask(Task task) {
        list.add(task);
        updateText();
        return
                "Got it. I've added this task:\n" +
                task.description() +
                "\n\n" +
                "There is now " +
                list.size() +
                " tasks in the list.\n";
    }

    /**
     * Deletes a task by its index in the list (1-based).
     * Prints confirmation or throws an exception if index invalid.
     *
     * @throws InvalidInputException if index is out of bounds
     */
    public String deleteTask(int i) throws InvalidInputException {
        if (i > list.size() || i < 1) {
            throw new InvalidInputException("You currently have " + list.size() + " tasks currently." +
                    "\n" +
                    "Please try again. \n");
        } else {
            String response = "Noted. I've removed this task:\n" +
                    list.get(i - 1).description() +
                    "\n" +
                    "Now you have " +
                    (list.size() - 1) +
                    " tasks in the list.";
            list.remove(i - 1);
            updateText();
            return response;
        }

    }

    //Ember.TodoTask
    public String inputList(String s) throws MissingInformationException, InvalidInputException {
        Task task = new TodoTask(s);
        updateText();
        return addTask(task);
    }

    //Ember.DeadlineTask
    public String inputList(String s, String by) throws MissingInformationException, InvalidInputException {
        Task task = new DeadlineTask(s, by);
        updateText();
        return addTask(task);

    }

    //Ember.EventTask
    public String inputList(String s, String from, String to) throws MissingInformationException, InvalidInputException {
        Task task = new EventTask(s, from, to);
        updateText();
        return addTask(task);

    }

    public String printList() {
        int i = 0;
        String response = "Here are the tasks in your list:\n";
        while (i < list.size() && list.get(i) != null) {
            response += (i + 1) + ". " + list.get(i).description() + "\n";
            i++;
        }
        return response;
    }

    public String printMatchingList() {
        int i = 0;
        String response = "Here are the matching tasks in your list:\n";
        while (i < list.size() && list.get(i) != null) {
            response += (i + 1) + ". " + list.get(i).description() + '\n';
            i++;
        }
        return response;
    }

    /**
     * Finds tasks whose names contain the given keyword (non-case-sensitive substring match),
     * find items even if the keyword matches the item only partially
     * builds a temporary list with those tasks, and returns the formatted output
     * from printMatchingList().
     *
     * Behavior notes:
     * - Matching uses String.contains and is case-sensitive.
     * - The original list remains unchanged.
     *
     * @param keyword the substring to search for within each task's name; must not be null
     * @return a formatted string of matching tasks produced by printMatchingList()
     */
    public String findKeyword(String keyword) {
        ArrayList<Task> filteredTasks = (ArrayList<Task>) list.stream()
                .filter(task -> task.getName().toLowerCase()
                .contains(keyword.toLowerCase())).collect(Collectors.toList());
        ListManager keywordList = new ListManager();
        keywordList.list = filteredTasks;
        return keywordList.printMatchingList();
    }

    /**
     * Returns tasks sorted by name in ascending lexicographical order,
     * builds a temporary list with the sorted tasks, and returns the formatted output
     * from printMatchingList().
     *
     * Behavior notes:
     * - Sorting uses String.compareTo (case-sensitive, locale-independent).
     * - The original list remains unchanged; sorting is applied to a streamed copy.
     *
     * @return a formatted string of tasks sorted by name produced by printMatchingList()
     */
    public String sortByName() {
        ArrayList<Task> sortedTask = (ArrayList<Task>) list.stream()
                .sorted((a, b) -> a.getName().compareTo(b.getName()))
                .collect(Collectors.toList());
        ListManager sortedList = new ListManager();
        sortedList.list = sortedTask;
        return sortedList.printMatchingList();
    }

    /**
     * Returns tasks sorted by date in ascending order,
     * builds a temporary list with the sorted tasks, and returns the formatted output
     * from printMatchingList().
     *
     * Behavior notes:
     * - Sorting delegates to Task.getDate().compareTo; ensure getDate() is non-null or handle nulls accordingly.
     * - The original list remains unchanged; sorting is applied to a streamed copy.
     *
     * @return a formatted string of tasks sorted by date produced by printMatchingList()
     */
    public String sortByDate() {
        ArrayList<Task> sortedTask = (ArrayList<Task>) list.stream()
                .sorted((a, b) -> a.getDate().compareTo(b.getDate()))
                .collect(Collectors.toList());
        ListManager sortedList = new ListManager();
        sortedList.list = sortedTask;
        return sortedList.printMatchingList();
    }

    /**
     * Marks the task at the given 1-based index (marks as not done).
     * Updates the saved file and prints confirmation.
     *
     * @param taskNumber 1-based index of the task to unmark
     */
    public String mark(int taskNumber) throws InvalidInputException{
        if (taskNumber > list.size() || taskNumber < 1) {
            throw new InvalidInputException("You only have " + list.size() + " tasks currently." +
                    "\n" +
                    "Please try again. \n");
        }
        else {
            list.get(taskNumber - 1).mark();
            String response = "Nice! I've marked this task as done:\n" +
                    list.get(taskNumber - 1) +
                    "\n";
            updateText();
            return response;
        }
    }

    /**
     * Unmarks the task at the given 1-based index (marks as not done).
     * Updates the saved file and prints confirmation.
     *
     * @param taskNumber 1-based index of the task to unmark
     */
    public String unmark(int taskNumber) throws InvalidInputException {
        if (taskNumber > list.size() || taskNumber < 1) {
            throw new InvalidInputException("You only have " + list.size() + " tasks currently." +
                    "\n" +
                    "Please try again. \n");
        }
        else {
            list.get(taskNumber - 1).unmark();
            String response = "OK, I've marked this task as not done yet:\n" +
                    list.get(taskNumber - 1).toString() +
                    "\n";
            updateText();
            return response;
        }
    }
}
