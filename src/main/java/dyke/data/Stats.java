package dyke.data;

import dyke.parse.DateTimeParser;
import dyke.tasks.Deadline;
import dyke.tasks.Event;
import dyke.tasks.Task;
import dyke.tasks.Todo;

/**
 * Houses methods to obtain Statistics on {@code Tasks} in the {@code Library}
 */
public class Stats {
    private final Library library;

    public Stats(Library library) {
        this.library = library;
    }

    /**
     * Returns basic statistics on each type of {@code Task} in the {@code Library}
     * @return Statistics message
     */
    public String result() {
        String resEvent = this.getEventStats();
        String resDeadline = this.getDeadlineStats();
        String resTodo = this.getTodoStats();
        return resEvent + "\n" + resDeadline + "\n" + resTodo;
    }

    private String getEventStats() {
        long totalDuration = 0;
        int totalTasks = 0;
        for (Task t : library.getTasks()) {
            if (t instanceof Event e) {
                totalTasks++;
                DateTimeParser[] dates = e.getTime();
                totalDuration += dates[0].timeDifference(dates[1]);
            }
        }
        assert totalTasks >= 0;
        String format = "Event Stats: \n"
                + "Number of Tasks: " + totalTasks + "\n"
                + "Average duration of Events: "
                + (totalTasks > 0 ? totalDuration / totalTasks : 0) + " days\n";
        return format;
    }

    private String getDeadlineStats() {
        int totalTasks = 0;
        for (Task t : library.getTasks()) {
            if (t instanceof Deadline d) {
                totalTasks++;
            }
        }
        assert totalTasks >= 0;
        String format = "Deadline Stats: \n"
                + "Number of Tasks: " + totalTasks + "\n";
        return format;
    }

    private String getTodoStats() {
        int totalTasks = 0;
        for (Task t : library.getTasks()) {
            if (t instanceof Todo todo) {
                totalTasks++;
            }
        }
        assert totalTasks >= 0;
        String format = "Todo Stats: \n"
                + "Number of Tasks: " + totalTasks + "\n";
        return format;
    }
}
