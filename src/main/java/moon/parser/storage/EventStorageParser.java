package moon.parser.storage;

import moon.models.Event;
import moon.models.MoonDateTime;
import moon.parser.exceptions.ParseException;
import moon.parser.util.DateTimeParser;

/**
 * Parser for reconstructing {@link Event} tasks from storage.
 * <p>
 * Expected storage format:
 * {@code E | <done-flag> | <name> | <from-time> | <to-time>}
 */
public class EventStorageParser implements StorageParser<Event> {

    /**
     * Parses the given storage fields into an {@link Event} task.
     *
     * @param inputs the split storage line (must contain 5 fields)
     * @return the reconstructed {@link Event}
     * @throws ParseException if either date/time cannot be parsed
     */
    @Override
    public Event parse(String[] inputs) throws ParseException {
        String name = inputs[2];
        boolean done = inputs[1].equals("1");
        MoonDateTime fromTime = DateTimeParser.parse(inputs[3], true);
        MoonDateTime toTime = DateTimeParser.parse(inputs[4], true);
        assert fromTime != null && toTime != null : "DateTimeParser should always return a MoonDateTime or throw";

        Event event = new Event(name, fromTime, toTime);
        event.setDone(done);
        return event;
    }
}
