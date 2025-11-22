package commands;

import java.time.LocalDateTime;
import java.util.HashMap;

import exceptions.ApunableException;
import models.Event;
import models.Task;
import utils.DateTimeUtil;

/**
 * Handles the {@code event} command from user and add a new {@code Event} task.
 */
public class CreateTaskEvent extends CreateTaskHandler {
    @Override
    public Task createTask(String firstParam, HashMap<String, String> params) throws ApunableException {
        String desc = firstParam;

        String fromStr = params.getOrDefault("from", "");
        String toStr = params.getOrDefault("to", "");

        assert !desc.isEmpty() : "May I know what's this event about? I would like to record it down also.";
        assert !fromStr.isEmpty() : "When does your event start? Please include a /from date and time.";
        assert !toStr.isEmpty() : "When does your event end? Please include a /to date and time.";

        LocalDateTime from = DateTimeUtil.tryParse(fromStr);
        LocalDateTime to = DateTimeUtil.tryParse(toStr);
        
        assert from != null : "Hmm... I couldn't understand the /from time. Try something like '2025-10-26 14:00'.";
        assert to != null : "I couldn't read the /to time. Please use a clear format, like '2025-10-26 16:00'.";

        return new Event(desc, from, to);
    }
}