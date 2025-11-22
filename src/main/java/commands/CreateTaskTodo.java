package commands;

import java.util.HashMap;

import exceptions.ApunableException;
import models.Task;
import models.Todo;

/**
 * Handles the {@code todo} command from user and add a new {@code Todo} task.
 */
public class CreateTaskTodo extends CreateTaskHandler {
    @Override
    public Task createTask(String firstParam, HashMap<String, String> params) throws ApunableException {
        String desc = firstParam;
        
        assert !desc.isEmpty() : "Can you provide me what is this todo about?";
    
        return new Todo(desc);
    }
}