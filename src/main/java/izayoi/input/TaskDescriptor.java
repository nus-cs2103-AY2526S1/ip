package izayoi.input;

import java.util.Map;

/**
 * Represents an object capable of describing the parameters for a task
 */
public interface TaskDescriptor {
    /**
     * Provides a list of parameters for task creation
     * @return a map of argument names and their values. The task description is mapped to "message"
     */
    public Map<String, String> getTask();
}
