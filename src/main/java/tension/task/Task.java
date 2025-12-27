package tension.task;

import java.util.ArrayList;

import tension.TensionException;
import tension.helper.Parser;

/**
 * Abstract class for all tasks
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected ArrayList<String> tags;

    /**
     * Initialises the task description and completion
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.tags = new ArrayList<>();
    }

    /**
     * returns status icon based on tasks completion
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * returns a string of a task marked as done
     */
    public String markDone() {
        isDone = true;
        String s = ("Nice! I've marked this tension.task as done:\n["
                + getStatusIcon() + "] " + description);
        return s;
    }

    /**
     * returns a string of a task unmarked as done
     */
    public String unmarkDone() {
        isDone = false;
        String s = ("OK, I've marked this tension.task as not done yet:\n["
                + getStatusIcon() + "] " + description);
        return s;
    }

    /**
     * returns Task made from string input by user
     */
    public static Task makeTask(String command) throws TensionException {
        String[] parts = command.split("\\s+");
        String firstWord = parts[0];
        String[] splitBySlash = command.split("/");
        try {
            if (firstWord.equals("todo")) {
                String description = Task.secondWordOnwards("todo", splitBySlash[0]);
                checkStrings(description);
                return new Todo(description);
            } else if (firstWord.equals("deadline")) {
                String description = Task.secondWordOnwards("deadline", splitBySlash[0]);
                String byTime = Task.secondWordOnwards("by", splitBySlash[1]);
                checkStrings(description, byTime);
                Deadline.checkFormat(byTime);
                return new Deadline(description, byTime);
            } else if (firstWord.equals("event")) {
                String description = Task.secondWordOnwards("event", splitBySlash[0]);
                String startTime = Task.secondWordOnwards("from", splitBySlash[1]);
                String endTime = Task.secondWordOnwards("to", splitBySlash[2]);
                checkStrings(description, startTime, endTime);
                return new Event(description, startTime, endTime);
            }
        } catch (TensionException e) {
            throw e;
        } catch (Exception e) {
            throw new TensionException("Missing a slash according to the given command");
        }
        throw new TensionException("Unknown command: " + command);
    }

    /**
     * returns Task made from string returned from storage
     */
    public static Task makeTaskFromMemory(String command) {
        String[] parts = command.split("[|]");
        Parser parser = new Parser();
        String firstWord = parts[0];
        Task task = null;
        if (firstWord.equals("T")) {
            checkStrings(parts[2]);
            ArrayList<String> tags = parser.parseStringToArrayList(parts[3]);
            task = new Todo(parts[2], tags);
        } else if (firstWord.equals("D")) {
            checkStrings(parts[2], parts[3]);
            ArrayList<String> tags = parser.parseStringToArrayList(parts[4]);
            task = new Deadline(parts[2], parts[3], tags);
        } else if (firstWord.equals("E")) {
            checkStrings(parts[2], parts[3], parts[4]);
            ArrayList<String> tags = parser.parseStringToArrayList(parts[5]);
            task = new Event(parts[2], parts[3], parts[4], tags);
        }
        assert task != null;
        task.getStatus(Boolean.parseBoolean(parts[1]));
        return task;
    }

    /**
     * checks that strings are not null or empty
     */
    public static void checkStrings(String... strings) {
        for (String s : strings) {
            if (s != null && s.isEmpty()) {
                throw new Error("String cannot be empty");
            }
        }
    }

    public String getStatus(Boolean isMark) {
        if (isMark) {
            return markDone();
        } else {
            return unmarkDone();
        }
    }

    /**
     * Returns second word after key phrase
     * @param valid the keyword that is needed
     * @param s the whole string
     */
    private static String secondWordOnwards(String valid, String s) throws TensionException {
        String[] parts = s.strip().split("\\s+");
        if (!parts[0].equals(valid)) {
            throw new TensionException("Invalid task description, keyword missing is: " + valid);
        }
        StringBuilder result = new StringBuilder();
        for (int i = 1; i < parts.length; i++) {
            result.append(parts[i]);
            result.append(" ");
        }
        return result.toString().strip();
    }

    /**
     * Returns list of tasks that contained queried keyword
     * @param tasks the list of all tasks to be reviewed
     * @param foundTasks the list of tasks containing keyword
     * @param fullCommand the whole command
     */
    public static void findTasksWithKeyword(ArrayList<Task> tasks, ArrayList<Task> foundTasks, String fullCommand) {
        for (Task task : tasks) {
            String keyword = fullCommand.split(" ")[1];
            String[] taskKeywords = (task.getDescription().split(" "));
            for (String taskKeyword : taskKeywords) {
                if (taskKeyword.equals(keyword)) {
                    foundTasks.add(task);
                    break;
                }
            }
        }
    }

    /**
     * returns string to store in storage
     */
    public String makeStoreString() {
        return "";
    }

    /**
     * adds a singular tag to the tags arrayList
     */
    public void addTag(String tag) {
        tags.add(tag);
    }

    public String getTagsAsString() {
        if (tags.isEmpty()) {
            return "";
        }
        return String.join(" #", tags);
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public String getDescription() {
        return description;
    }
}
