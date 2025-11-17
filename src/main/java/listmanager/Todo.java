package listmanager;

import customexceptions.IncompleteTaskException;
import parser.Parser;

import java.util.List;

/**
 * Subtype of <code>Task</code>, it just has a task name.
 */
public class Todo extends Task {

    private Parser parser = new Parser();
    private static final int MAX_EXPECTED_SEGMENTS = 2;


    public Todo(String taskDescriptor) throws IncompleteTaskException {
        super(taskDescriptor);
        descriptorProcessor(taskDescriptor);
    }

    /**
     * Returns task name with status in string format.
     * @return String containing task name and completion status.
     */
    @Override
    public String getTaskWithStatus() {
        return "[T]"
                + "[" + getStatus() + "] "
                + getName() + " "
                + super.getTags();
    }

    /**
     * Converts task to string format for storing to file.
     *
     * @return String that contains information of the task for storing.
     */
    @Override
    public String toStringFormat() {
        StringBuilder sb = new StringBuilder();
        sb.append("Todo,").append(super.taskDescriptor + ",").append(super.isComplete);

        //Append tags
        for (int i = 0; i < super.taskTags.size(); i++) {
            sb.append("," + super.taskTags.get(i).getName());
        }
        return sb.toString();
    }


    /**
     * Processes taskDescriptor and splits it into task name.
     *
     * @param taskDescriptor String of user input passed into constructor.
     * @return Task name in string format.
     * @throws IncompleteTaskException If taskDescriptor is in known format but incomplete.
     */
    public void descriptorProcessor(String taskDescriptor) throws IncompleteTaskException {
        List<String> words = parser.stringSplitter(taskDescriptor, " ");

        //words length should at most be 2.
        assert (words.size() <= MAX_EXPECTED_SEGMENTS): "word segments exceed expected amount";

        if (words.size() != MAX_EXPECTED_SEGMENTS) {
            throw new IncompleteTaskException("please include the task name, thank you.");
        }
        super.taskName = words.get(1);
    }

}
