package angsoontong;

import angsoontong.task.Task;
import angsoontong.task.Deadline;
import angsoontong.task.Event;
import angsoontong.task.ToDo;


public class TaskDecoder {
    public static Task decode(String line) {
        assert line != null : "decode line is null"; // assertion to make sure no empty input
        String[] parts = line.split(" \\| ");
        String type = parts[0];
        Task t;

        /**
         * read the task type and return the task with proper description
         */
        switch (type) {
            case "T": {
                String desc = parts[2];
                t = new ToDo(desc);
                if ("1".equals(parts[1])) t.markDone();
                if (parts.length >= 4) t.loadTagsFromCsv(parts[3]);
                break;
            }
            case "D": {
                String desc = parts[2];
                String by = parts[3];
                t = new Deadline(desc, by);
                if ("1".equals(parts[1])) t.markDone();
                if (parts.length >= 5) t.loadTagsFromCsv(parts[4]);
                break;
            }
            case "E": {
                String desc = parts[2];
                String start = parts[3];
                String end = parts[4];
                t = new Event(desc, start, end);
                if ("1".equals(parts[1])) t.markDone();
                if (parts.length >= 6) t.loadTagsFromCsv(parts[5]);
                break;
            }
            default:
                // if task is not a T, D or E
                throw new IllegalArgumentException("Unknown task type: " + type);
        }

        return t;
    }
}
