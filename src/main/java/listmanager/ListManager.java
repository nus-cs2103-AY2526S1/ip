package listmanager;

import customexceptions.NoSuchTagException;
import taskfinder.TaskFinder;
import taskstorage.TaskSaver;
import uimanager.UI;
import parser.Parser;

import customexceptions.EmptyListException;
import customexceptions.IncompleteTaskException;
import customexceptions.NoSuchTaskException;

import java.util.List;
import java.util.Iterator;
import java.util.Objects;

/**
 * Stores <code>Task</code> objects in a <code>List</code> taskList.
 * Tracks taskCount, completedTasks and uncompletedTasks.
 * Contains <code>TaskSaver</code> object which saves stored tasks to a file on end.
 *
 * The ListManager class can add/remove tasks, update tasks (add tags, mark/unmark as complete).
 */
public class ListManager {
    private List<Task> taskList;
    private int completedTasks = 0;
    private int taskCount = 0;
    private int uncompletedTasks = 0;

    private TaskSaver taskSaver;
    private TaskFinder taskFinder;
    private UI ui;
    private Parser parser;

    /**
     * Initializes <code>TaskStorage</code> and <code>TaskFinder</code> instance.
     * Checks for existing save file and stores to <code>TaskList</code> object.
     */
    public ListManager() {
        taskSaver = new TaskSaver();
        importTaskList(taskSaver.loadTasks());
        taskFinder = new TaskFinder();
        ui = new UI();
        parser = new Parser();
    }

    /**
     * Creates task based on taskDescription.
     * updates the task counter.
     * returns a string message acknowledging the task has been added.
     *
     * @param taskDescription task description in string format.
     * @throws NoSuchTaskException If task description doesn't match any known format.
     * @throws IncompleteTaskException If task description matches known format but is incomplete.
     */
    public String add(String taskDescription) throws NoSuchTaskException, IncompleteTaskException {
        Task newTask = addTaskToList(taskDescription);
        updateTaskCounter();
        return ui.onNewTask(newTask, taskCount, completedTasks, uncompletedTasks);
    }

    /**
     * Displays the currently stored tasks in <code>TaskList</code>.
     * The task status along with the task name and relevant information is shown.
     *
     * @throws EmptyListException If the list is empty
     */
    public String displayList() throws EmptyListException {
        String returnString = "";
        if (taskList.isEmpty()) {
            throw new EmptyListException("There are no tasks to display, please input some tasks");
        }
        Iterator<Task> iterator = taskList.iterator();
        int count = 1;
        while (iterator.hasNext()) {
            Task task = iterator.next();
            String taskString = count + "." + task.getTaskWithStatus() + "\n";
            returnString += taskString;
            count++;
        }
        return returnString;
    }

    /**
     * Updates completion status of the task at the specified index
     *
     * @param isComplete True to set task as complete, false to set task as incomplete.
     * @param userInput is the input entered  by the user in the chatbox.
     * @throws NoSuchTaskException If index > taskList.size().
     */
    public String updateTask(boolean isComplete, String userInput)
            throws NoSuchTaskException, IncompleteTaskException {
        List<String> wordSegments = validateAndParseInput(userInput, 2, " ");
        String indexStr = wordSegments.get(1);
        int taskNumber = validateAndParseIndex(indexStr);
        Task task = taskList.get(taskNumber);
        task.changeStatus(isComplete);
        updateTaskCounter();
        return ui.onUpdateTask(isComplete, task);
    }

    /**
     * Deletes the task at the specified index.
     *
     * @param userInput is the input enterd by the user into the chatbox.
     * @throws NoSuchTaskException If index > taskList.size().
     */
    public String deleteTasks(String userInput)
            throws NoSuchTaskException, IncompleteTaskException {
        List<String> wordSegments = validateAndParseInput(userInput, 2, " ");
        String indexStr = wordSegments.get(1);
        int taskNumber = validateAndParseIndex(indexStr);
        Task deletedTask = taskList.remove(taskNumber);
        updateTaskCounter();
        return ui.onDeleteTask(deletedTask);
    }

    /**
     * Adds a tag to the task.
     * @param input follows the format "tag <>TASK NUMBER</> <>TAG NAME</>"
     * @return a message indicating the tag has been added/failed to add as a String.
     * @throws NoSuchTaskException if no Task corresponds to the <>TASK NUMBER</>
     * @throws NoSuchTagException if missing Tag Name
     */
    public String addTagToTask(String input) throws NoSuchTaskException, NoSuchTagException {

        List<String> wordSegments = parser.stringSplitter(input, " ", " ");

        if (wordSegments.size() < 3) {
            throw new NoSuchTagException("Please follow this format for tagging: 'tag <task number> <tag name>'");
        }

        int index = validateAndParseIndex(wordSegments.get(1));
        String tagName = wordSegments.get(2);

        Task task = taskList.get(index);
        task.addTag(tagName);
        return ui.onTagTask(tagName, task);
    }

    /**
     * Removes tag from the specified task.
     *
     * @param input follows the format "untag <>TASK NUMBER</> <>TAG NAME</>.
     * @return message that indicates the tag was/was not removed.
     * @throws NoSuchTagException if TAG NAME is missing or doesn't exist
     * @throws NoSuchTaskException if no Tasks exists at TASK NUMBER.
     */
    public String removeTagFromTask(String input)
            throws NoSuchTagException, NoSuchTaskException, IncompleteTaskException{
        List<String> wordSegments = validateAndParseInput(input, 3, " ", " ");

        if (wordSegments.size() < 3) {
            throw new NoSuchTagException("Please follow this format for untagging: 'untag <task number> <tag name>'");
        }

        int index = validateAndParseIndex(wordSegments.get(1));
        String tagName = wordSegments.get(2);

        Task task = taskList.get(index);
        boolean isRemoved = task.removeTag(tagName);

        if (!isRemoved) {
            throw new NoSuchTagException("I am unable to find a tag with the specified name to remove.");
        }
        return ui.onUntagTask(tagName, task);
    }

    /**
     * Calls TaskFinder class to filter list by keyword.
     *
     * @param userInput The find command issued by the user.
     */
    public String findTasks(String userInput) throws EmptyListException, IncompleteTaskException{
        return taskFinder.setFilteredList(taskList, userInput);
    }

    /**
     * Classifies the task into one of three types:
     * <code>Todo</code>, <code>Deadline</code>, <code>Event</code>.
     *
     * @param task Task descriptor in <code>String</code> format.
     * @return <code>Task</code> object that has been initialized as one of the three types.
     * @throws NoSuchTaskException If task descriptor does not match known format.
     * @throws IncompleteTaskException If task descriptor matches known format but is incomplete.
     */
    public Task taskClassifier(String task) throws NoSuchTaskException, IncompleteTaskException {
        //split the task string into keywords
        List<String> taskKeyWords = validateAndParseInput(task, 2, " ");

        //by splitting the string up we can now compare the first word to identify the task type
        if (taskKeyWords.get(0).equals("todo")) {
            return new Todo(task);
        } else if (taskKeyWords.get(0).equals("deadline")) {
            return new Deadline(task);
        } else if (taskKeyWords.get(0).equals("event")) {
            return new Event(task);
        } else {
            throw new NoSuchTaskException("Sorry I don't recognize this task, can you please use the keywords?");
        }
    }

    /**
     * Calls taskSaver to save currently stored tasks.
     */
    public void closeList() {
        taskSaver.saveTasks(taskList);
    }


    //Claude AI suggested abstracting parsing and validation of indexes to a separate method to reduce
    //code duplication.
    private int validateAndParseIndex(String indexStr) throws NoSuchTaskException {
        try {
            int index = Integer.parseInt(indexStr) - 1;
            if (index < 0 || index >= taskList.size()) {
                throw new NoSuchTaskException("Invalid task number: " + (Integer.parseInt(indexStr)));
            }
            return index;
        } catch (NumberFormatException e) { //Additional exception by Claude to catch unaccepted number formats
            throw new NoSuchTaskException("Invalid task number format: " + indexStr);
        }
    }


    private List<String> validateAndParseInput(String input, int expectedSegments, String... splitPoints)
        throws IncompleteTaskException {
        List<String> wordSegments = parser.stringSplitter(input, splitPoints);
        if (wordSegments.size() < expectedSegments) {
            throw new IncompleteTaskException("I do not recognize your input.");
        }
        return wordSegments;
    }

    //These private methods abstract commonly used tasks.
    //Consulted with Claude AI on potential solutions to improve code quality
    private Task addTaskToList(String taskDescription)
            throws NoSuchTaskException, IncompleteTaskException {
        Task taskType = taskClassifier(taskDescription);
        taskList.add(taskType);
        return taskType;
    }

    private void updateTaskCounter() {
        Iterator<Task> iterator = taskList.iterator();

        int totalTasks = 0;
        int totalCompletedTasks = 0;
        int totalUncompletedTasks = 0;

        //update task count statistics
        while (iterator.hasNext()) {
            Task task = iterator.next();
            totalTasks += 1;
            if (Objects.equals(task.getStatus(), "X")) {
                totalCompletedTasks += 1;
            } else {
                totalUncompletedTasks += 1;
            }
        }

        taskCount = totalTasks;
        completedTasks = totalCompletedTasks;
        uncompletedTasks = totalUncompletedTasks;

        //completed and uncompleted tasks should never be negative. Or exceed total task count
        assert completedTasks >= 0 : "Number of completed tasks should never be negative";
        assert uncompletedTasks >= 0 : "Number of completed tasks should never be negative";
        assert (uncompletedTasks + completedTasks) == taskCount : "Uncompleted and completed tasks equal task count";
    }

    private void importTaskList(List<Task> taskList) {
        this.taskList = taskList;
        updateTaskCounter();
    }
}
