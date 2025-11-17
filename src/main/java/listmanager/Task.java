package listmanager;

import customexceptions.IncompleteTaskException;
import tags.Tag;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;


/**
 * Task object stores information of the task such as taskDescriptor and taskName.
 * Tracks completion of task using boolean isComplete.
 */
public class Task {
    protected String taskDescriptor;
    protected boolean isComplete;
    protected String taskName;
    protected List<Tag> taskTags;



    public Task(String taskDescriptor) {
        this.taskDescriptor = taskDescriptor;
        taskTags = new ArrayList<>();
    }


    public String toStringFormat() {
        return taskName + "," + isComplete;
    }


    /**
     * Public method which converts file string output to a <code>Task</code>.
     * Used for reading external files and generating the corresponding <code>Task</code> object.
     *
     * @param fileOutput String containing information on stored <code>Task</code>.
     * @return <code>Task</code> object that was stored.
     * @throws IncompleteTaskException If fileOutput is corrupted.
     */
    public static Task stringToTask(String fileOutput) throws IncompleteTaskException {
        String[] words = fileOutput.split(",");
        String taskType = words[0];
        String taskDescriptor = words[1];
        boolean isComplete = Boolean.parseBoolean(words[2]);
        Task returnTask;
        if (taskType.equals("Todo")) {
            returnTask = new Todo(taskDescriptor);
            returnTask.changeStatus(isComplete);
        } else if (taskType.equals("Deadline")) {
            returnTask = new Deadline(taskDescriptor);
            returnTask.changeStatus(isComplete);
        } else {
            returnTask = new Event(taskDescriptor);
            returnTask.changeStatus(isComplete);
        }

        //Add tags to returnTask
        for (int i = 3; i < words.length; i++) {
            returnTask.addTag(words[i]);
        }

        return returnTask;
    }

    public String getTaskWithStatus() {
        return "[" + getStatus() + "] "
                + getName();
    }

    public String getName() {
        return taskName;
    }

    public String getStatus() {
        if (isComplete) {
            return "X";
        } else {
            return " ";
        }
    }

    /**
     * Creates a new <class>Tag</class> object and adds it to list of tags within the Task.
     * @param tagName is the name of the tag being added.
     */
    public void addTag(String tagName) {
        this.taskTags.add(new Tag(tagName));
    }

    /**
     * Removes tag with matching tag name from the list of tags associated with the <class>Task</class> object.
     * If there are multiple tags with the same name, all will be removed.
     *
     * @param tagName is the name of the tag being removed.
     * @return isRemoved boolean if a tag was successfully removed.
     */
    public boolean removeTag(String tagName) {
        Iterator<Tag> iterator = taskTags.iterator();
        boolean isRemoved = false;
        while(iterator.hasNext()) {
            Tag temp = iterator.next();
            if (Objects.equals(temp.getName(), tagName)) {
                iterator.remove();
                isRemoved = true;
            }
        }
        return isRemoved;
    }

    public String getTags() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < taskTags.size(); i++) {
            sb.append("#").append(taskTags.get(i).getName()).append(" ");
        }

        return sb.toString();
    }

    /**
     * Changes the completion status of the task.
     * @param isComplete is set true if the task is set to be completed, false otherwise.
     * @return true if there is a change in status. False otherwise.
     */
    public boolean changeStatus(boolean isComplete) {
        if (this.isComplete != isComplete) {
            this.isComplete = isComplete;
            return true;
        }
        return false;
    }
}
