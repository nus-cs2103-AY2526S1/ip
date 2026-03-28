package mel.tasks;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import mel.apps.Storage;
import mel.exceptions.MelException;

/**
 * Represents the list of tasks.
 */
public class TaskList {
    private List<Task> taskList;
    private Storage storage;

    public TaskList(Storage storage) {
        this.storage = storage;
        this.taskList = new ArrayList<>();

    }

    /**
     * Returns the object for the taskList class.
     * Throws an exception when the stored data is empty
     *
     * @param savedStrings
     * @param storage
     * @throws MelException
     */
    public TaskList(String[] savedStrings, Storage storage) throws MelException {
        assert storage != null;
        this.storage = storage;
        this.taskList = new ArrayList<>();
        for (String s : savedStrings) {
            taskList.add(Task.fromSavedString(s));

        }

    }

    /**
     * Returns the output when a task is added to the task list
     *
     * @param task
     * @return String when task is added to the list of tasks
     * @throws MelException
     */
    public String add(Task task) throws MelException {
        assert task != null;
        taskList.add(task);
        this.update();
        String output = String.format(
                "Got it. I've added this task:\n  %s\n Now you have %d task(s) in the list.",
                task.toString(), taskList.size());
        return output;

    }

    /**
     * Returns the output when a task is removed from the list of tasks.
     * @param index
     * @return String when task is removed from the list of tasks
     * @throws MelException
     */
    public String remove(int index) throws MelException {
        if (!isValidIndex(index)) {
            throw new MelException.InvalidIndexException(
                    String.format(
                            "It is out of range! Please put a number from 1 to %d.",
                            taskList.size()));

        }
        Task task = taskList.remove(index);
        this.update();
        String output = String.format(
                "OK, I've removed this task:\n  %s\nNow you have %d tasks in the list.",
                task.toString(), taskList.size());
        return output;

    }

    /**
     * Returns the size of list of tasks.
     *
     * @return an integer which is the number of tasks in the list
     */
    public int size() {
        return taskList.size();

    }

    /**
     * Returns the task at the index specified in the list.
     *
     * @param index
     * @return Task object at index
     */
    public Task get(int index) {
        return taskList.get(index);

    }

    /**
     * Returns output when a task is marked as done.
     * Throws exception when index is out of range.
     *
     * @param index
     * @return String when task is marked as done
     * @throws MelException
     */
    public String mark(int index) throws MelException {
        if (!isValidIndex(index)) {
            throw new MelException.InvalidIndexException(
                    String.format(
                            "It is out of range! Please put a number from 1 to %d.",
                            taskList.size()));

        }

        String s =  taskList.get(index).markAsDone();
        this.update();
        return s;

    }

    /**
     * Returns an output when a task is unmarked.
     * Throws an exception when index is out of range.
     *
     * @param index
     * @return String when task is unmarked.
     * @throws MelException
     */
    public String unmark(int index) throws MelException {
        if (!isValidIndex(index)) {
            throw new MelException.InvalidIndexException(
                    String.format("It is out of range! Please put a number from 1 to %d.",
                            taskList.size()));
        }
        String s = taskList.get(index).undo();
        this.update();
        return s;

    }

    /**
     * Returns a string of the output whether there are matching tasks in the list along with the tasks.
     *
     * @param argument
     * @return
     * @throws MelException
     */
    public String findTask(String argument) throws MelException {
        if (taskList.isEmpty()) {
            throw new MelException.EmptyListException();

        }

        int index = 1;
        StringBuilder output = new StringBuilder();
        output.append("Here are the matching tasks in your list:\n ");
        for (Task task : taskList) {
            if (hasWord(task.toString(), argument)) {
                if (index > 1) {
                    output.append("\n ");

                }

                String taskString = String.format("%d.%s", index, task.toString());
                output.append(taskString);
                index++;
            }

        }

        if (index == 1) {
            return "There are no matching tasks in your list!";

        }

        return output.toString();


    }


    /**
     * Returns true if the text has the word, case-insensitive
     *
     * @param text
     * @param word
     * @return
     */
    public static boolean hasWord(String text, String word) {
        return Pattern.compile("\\b" + word + "\\b", Pattern.CASE_INSENSITIVE)
                .matcher(text)
                .find();

    }

    /**
     * Returns a boolean when checking whether the index is valid.
     * Throws an exception when the list is empty.
     *
     * @param index
     * @return boolean if index is valid
     * @throws MelException.EmptyListException
     */
    public boolean isValidIndex(int index) throws MelException.EmptyListException {
        if (taskList.isEmpty()) {
            throw new MelException.EmptyListException();

        }

        return index >= 0 && index < taskList.size();

    }

    /**
     * Updates the text file anytime the task list is modified.
     * Throws an exception when file is not saving properly.
     *
     * @throws MelException
     */
    public void update() throws MelException {
        List<String> savedStrings = new ArrayList<>();
        for (Task task : taskList) {
            savedStrings.add(task.toSaveString());

        }

        try {
            storage.save(savedStrings.toArray(String[]::new));

        } catch (IOException e) {
            throw new MelException("File is not saving!");

        }
    }

    @Override
    public String toString() {
        if (taskList.isEmpty()) {
            return "The list is empty!";

        }
        int index = 1;
        StringBuilder output = new StringBuilder();
        output.append("Here are the tasks in your list:\n ");
        for (Task task : taskList) {
            if (index > 1) {
                output.append("\n ");

            }

            String taskString = String.format("%d.%s", index, task.toString());
            output.append(taskString);
            index++;

        }

        return output.toString();
    }

}
