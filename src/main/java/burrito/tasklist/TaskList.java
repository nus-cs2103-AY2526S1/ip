package burrito.tasklist;

import burrito.parser.Parser;
import burrito.storage.Storage;

import java.util.List;
import java.util.ArrayList;

public class TaskList {

    public TaskList(){}

    /**
     * Marks task as done
     *
     * @param inputArr User input
     * @param cache List of tasks
     * @return String representation of command outcome.
     */
    static public String mark(List<Task> cache, String[] inputArr) {
        String output = "";
        if (inputArr.length <= 1) {
            output = "Sorry! you didn't specify the index.";
        } else {
            try {
                cache.get(Integer.parseInt(inputArr[1]) - 1).setStatus(true);
                output += "Nice! I've marked this task as done." + "\n";
                output += cache.get(Integer.parseInt(inputArr[1]) - 1).toString() + "\n ";

            } catch (Exception e) {
                output = "Wrong formatting for index!";
            } finally { }
        }
        Storage.saveToDisk(cache);
        assert output.length() > 1 : "Task not found in cache.";
        return output;

    }

    /**
     * Marks task as undone
     *
     * @param inputArr User input
     * @param cache List of tasks
     * @return String representation of command outcome.
     */
    static public String unmark(List<Task> cache, String[] inputArr) {
        String output = "";

        if (inputArr.length <= 1) {
            output += "Sorry! you didn't specify the index.";
        } else {
            try {
                cache.get(Integer.parseInt(inputArr[1]) - 1).setStatus(false);
                output += "Nice! I've marked this task as not done yet." + "\n";
                output += cache.get(Integer.parseInt(inputArr[1]) - 1).toString() + "\n ";


            } catch (Exception e) {
                output = "Wrong formatting for index!";
            } finally { }

        }
        Storage.saveToDisk(cache);
        assert output.length() > 1 : "Task not found in cache.";
        return output;
    }

    /**
     * Creates todo task and adds it to cache
     *
     * @param inputArr User input
     * @param cache List of tasks
     * @return String representation of command outcome.
     */
    static public String todo(List<Task> cache, String[] inputArr) {
        String output = "";
        try {
            cache.add(new Todo(inputArr[1]));
            output += "Got it. I've added this task:" + "\n";
            output += cache.get(cache.size() - 1).toString() + "\n";
            output += "Now you have " + cache.size() + " task(s) in the list." + "\n ";

        } catch (Exception e) {
            output = "Todo description cannot be empty!";

        } finally {}
        Storage.saveToDisk(cache);
        assert output.length() > 1 : "Task not found in cache.";
        return output;

    }

    /**
     * Creates deadline task and adds it to cache
     *
     * @param inputArr User input
     * @param cache List of tasks
     * @return String representation of command outcome.
     */
    static public String deadline(List<Task> cache, String[] inputArr) {
        String output = "";
        try {
            String[] newInputArr = inputArr[1].split(" /by ", 2);
            String[] newInputArr2 = newInputArr[1].split(" ");
            newInputArr[1] = Parser.dateParser(newInputArr2[0]) + " " + Parser.timeParser(newInputArr2[1]);

            cache.add(new Deadline(newInputArr[0], newInputArr[1]));
            output += "Got it. I've added this task:" + "\n";
            output += cache.get(cache.size() - 1).toString() + "\n";
            output += "Now you have " + cache.size() + " task(s) in the list."  + "\n ";

        } catch (Exception e) {
            output = "Something went wrong, check your syntax! (seperate with \"/by\"";

        } finally {}
        Storage.saveToDisk(cache);
        assert output.length() > 1 : "Task not found in cache.";
        return output;

    }

    /**
     * Creates event task and adds it to cache
     *
     * @param inputArr User input
     * @param cache List of tasks
     * @return String representation of command outcome.
     */
    static public String event(List<Task> cache, String[] inputArr) {

        String output = "";
        try {
            String[] newInputArr = inputArr[1].split(" /from ", 2);
            String[] newInputArr2 = newInputArr[1].split(" /to ", 2);

            String[] newInputArr3 = newInputArr2[0].split(" ");
            newInputArr2[0] = Parser.dateParser(newInputArr3[0]) + " " + Parser.timeParser(newInputArr3[1]);

            String[] newInputArr4 = newInputArr2[1].split(" ");
            newInputArr2[1] = Parser.dateParser(newInputArr4[0]) + " " + Parser.timeParser(newInputArr4[1]);

            cache.add(new Event(newInputArr[0], newInputArr2[0], newInputArr2[1]));
            output += "Got it. I've added this task:" + "\n";
            output += cache.get(cache.size() - 1).toString() + "\n";
            output += "Now you have " + cache.size() + " task(s) in the list."  + "\n ";

        } catch (Exception e) {
            output = "Something went wrong, check your syntax! (seperate with \"/from\" and \"/to\"";

        } finally {}
        Storage.saveToDisk(cache);
        assert output.length() > 1 : "Task not found in cache.";
        return output;

    }

    /**
     * Deletes task from cache
     *
     * @param inputArr User input
     * @param cache List of tasks
     * @return String representation of command outcome.
     */
    static public String delete(List<Task> cache, String[] inputArr) {
        String output = "";
        try {
            output += "Noted. I've removed this task:" + "\n";
            output += cache.get(Integer.parseInt(inputArr[1]) - 1).toString() + "\n";
            cache.remove(Integer.parseInt(inputArr[1]) - 1);
            output += "Now you have " + cache.size() + " task(s) in the list.";

        } catch (Exception e) {
            output = "Invalid index!";
        } finally {}
        Storage.saveToDisk(cache);
        assert output.length() > 1 : "Task not found in cache.";
        return output;
    }

    /**
     * Searches description of tasks from list of tasks for task based on search query from user
     *
     * @param inputArr User input, search query.
     * @param cache List of tasks
     * @return String representation of command outcome.
     */
    static public String find(List<Task> cache, String[] inputArr) {
        String output = "";
        try {
            List<Task> tempCache = new ArrayList<Task>();
            for (int i = 0; i < cache.size(); i++) {
                if (cache.get(i).description.contains(inputArr[1])) {
                    tempCache.add(cache.get(i));
                }
            }
            if (tempCache.size() > 0) {
                output += "Here are matching tasks in your list:" + "\n";
                for (int i = 0; i < tempCache.size(); i++) {
                    output += (i + 1) + ". " + tempCache.get(i).toString() + "\n ";
                }
            } else {
                output = "No matching tasks in your list.";
            }

        } catch (Exception e) {
            output = "Invalid search term!";
        } finally {}
        assert output.length() > 1 : "Task not found in cache.";
        return output;

    }

}
