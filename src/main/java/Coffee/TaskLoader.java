package Coffee;

/**
 * Loads {@link Task} instances from their serialized file representation.
 * Lines are expected to be pipe-delimited with the following canonical formats:
 * <ul>
 *   <li>ToDo: {@code T | [ ]|[X]|[1] | DESCRIPTION}</li>
 *   <li>Deadline: {@code D | [ ]|[X]|[1] | DESCRIPTION | yyyy-MM-dd HHmm}</li>
 *   <li>Event: {@code E | [ ]|[X]|[1] | DESCRIPTION | yyyy-MM-dd HHmm yyyy-MM-dd HHmm}</li>
 * </ul>
 * Status accepts {@code X} (or {@code 1}) for done; anything else is treated as not done.
 */
public class TaskLoader {

    /**
     * Returns a concrete {@link Task} parsed from a single line of persisted data.
     * The input must conform to one of the supported formats documented in the class header.
     *
     * @param line one line from the storage file.
     * @return the parsed {@link Task}.
     * @throws IllegalArgumentException if the line has an unknown type or an invalid/insufficient field layout.
     */
    public static Task fromFileLine(String line) {
        System.out.println("Parsing: " + line);
        String[] p = line.split("\\|");
        String type = p[0].trim();

        String status = p[1].trim();
        boolean isDone = "X".equals(status) || "1".equals(status);

        String desc = p[2].trim();

        switch (type) {
        case "T":
            return new ToDo(desc, isDone);

        case "D":
            return new Deadline(desc, p[3].trim(), isDone);

        case "E":
            String[] parts = p[3].trim().split(" ");
            if (parts.length < 4) {
                throw new IllegalArgumentException("Bad Coffee.Event line: " + line);
            }
            String from = parts[0] + " " + parts[1];
            String to   = parts[2] + " " + parts[3];
            return new Event(desc, from, to, isDone);

        default:
            throw new IllegalArgumentException("Bad save line: " + line);
        }
    }
}
