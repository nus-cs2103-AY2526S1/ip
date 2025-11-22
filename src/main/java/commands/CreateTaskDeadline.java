package commands;

import java.time.LocalDateTime;
import java.util.HashMap;

import exceptions.ApunableException;
import models.Deadline;
import models.Task;
import utils.DateTimeUtil;

/**
 * Handles the {@code deadline} command from user and add a new {@code Deadline} task.
 */
public class CreateTaskDeadline extends CreateTaskHandler {
    @Override
    public Task createTask(String firstParam, HashMap<String, String> params) throws ApunableException {
        String desc = firstParam;
        String byStr = params.getOrDefault("by", "");

        assert !desc.isEmpty() : "You forgot to tell me what the deadline is for, please add a description!";
        assert !byStr.isEmpty() : "When is this task due? Please include a /by date and time.";

        LocalDateTime by = DateTimeUtil.tryParse(byStr);

        assert by != null : "I couldn't understand the /by time. Try something like '2025-10-26 23:59'.";

        return new Deadline(desc, by);
    }
}