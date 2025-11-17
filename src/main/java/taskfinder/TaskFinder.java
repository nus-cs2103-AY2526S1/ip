package taskfinder;

import customexceptions.EmptyListException;
import customexceptions.IncompleteTaskException;
import listmanager.Task;
import parser.Parser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * TaskFinder class adds found tasks to a filteredList and then displays the tasks.
 */
public class TaskFinder {
    private List<Task> filteredList = new ArrayList<>();
    private Parser parser = new Parser();

    /**
     * setFilteredList filters out tasks based on userInput.
     *
     * @param taskList Contains all stored <code>Task</code> objects.
     * @param userInput Find command containing search keyword.
     * @throws EmptyListException If taskList is empty.
     */
    public String setFilteredList(List<Task> taskList, String userInput)
            throws EmptyListException, IncompleteTaskException {
        filteredList = new ArrayList<>();
        List<String> wordSegments = parser.stringSplitter(userInput, " ");

        if (wordSegments.size() < 2) {
            throw new IncompleteTaskException("please specify what to find");
        }
        if (taskList.isEmpty()) {
            throw new EmptyListException("There are no tasks to filter, please input some tasks");
        }
        String keyword = wordSegments.get(1);

        for (Task task : taskList) {
            if (task.getName().contains(keyword)) {
                filteredList.add(task);
            }
        }
        return displayFilteredList();
    }

    private String displayFilteredList() {
        String returnString = "";
        Iterator<Task> iterator = filteredList.iterator();
        int count = 1;
        while(iterator.hasNext()) {
            Task task = iterator.next();
            returnString += count + "." + task.getTaskWithStatus() + "\n";
            count++;
        }
        return returnString;
    }
}
