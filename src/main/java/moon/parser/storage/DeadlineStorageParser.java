package moon.parser.storage;

import moon.models.Deadline;
import moon.models.MoonDateTime;
import moon.parser.exceptions.ParseException;
import moon.parser.util.DateTimeParser;

/**
 * Parser for reconstructing {@link Deadline} tasks from storage.
 * <p>
 * Expected storage format:
 * {@code D | <done-flag> | <name> | <by-time>}
 */
public class DeadlineStorageParser implements StorageParser<Deadline> {

    /**
     * Parses the given storage fields into a {@link Deadline} task.
     *
     * @param inputs the split storage line (must contain 4 fields)
     * @return the reconstructed {@link Deadline}
     * @throws ParseException if the date/time cannot be parsed
     */
    @Override
    public Deadline parse(String[] inputs) throws ParseException {
        String name = inputs[2];
        boolean done = inputs[1].equals("1");
        MoonDateTime deadlineTime = DateTimeParser.parse(inputs[3], true);
        assert deadlineTime != null : "DateTimeParser should always return a MoonDateTime or throw";

        Deadline deadline = new Deadline(name, deadlineTime);
        deadline.setDone(done);
        return deadline;
    }
}
