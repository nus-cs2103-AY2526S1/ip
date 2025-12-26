package friday.storage;

import friday.exceptions.FridayTaskDecodeException;
import friday.tasks.Task;
import friday.tasks.ToDos;
import friday.tasks.Deadlines;
import friday.tasks.Events;

import java.util.ArrayList;
import java.util.List;

public class FridayDecoder {
    /**
     * Decodes the list that is stored in a file
     * @param encodedTasks is the List of String of the stored tasklist
     * @return an ArrayList of tasks
     * @throws FridayTaskDecodeException if the Task does not correspond to known tasks
     */
    public static List<Task> decodeTasks(List<String> encodedTasks) throws FridayTaskDecodeException {
        List<Task> tasks = new ArrayList<>();
        for (String line : encodedTasks) {
            if (line.length() < 7) {
                throw new FridayTaskDecodeException("Line too short: " + line);
            }

            char type = line.charAt(1);
            boolean isDone = (line.charAt(4) == 'X');

            try {
                switch (type) {
                    case 'T':
                        String raw = line.substring(7).trim();
                        String descT = raw;
                        String tagT = "";

                        // check if last word starts with #
                        int hashIndexT = raw.lastIndexOf(" #");
                        if (hashIndexT != -1) {
                            descT = raw.substring(0, hashIndexT).trim();
                            tagT = raw.substring(hashIndexT + 1).trim(); // includes #
                        }

                        ToDos todo = new ToDos(descT, tagT);
                        if (isDone) todo.markTaskAsDone();
                        tasks.add(todo);
                        break;
                    case 'D':
                        int byIndex = line.indexOf("(by:");
                        if (byIndex == -1) throw new FridayTaskDecodeException("Missing deadline: " + line);

                        String rawDesc = line.substring(7, byIndex).trim();
                        String desc = rawDesc;
                        String tag = "";

                        int hashIndex = rawDesc.lastIndexOf(" #");
                        if (hashIndex != -1) {
                            desc = rawDesc.substring(0, hashIndex).trim();
                            tag = rawDesc.substring(hashIndex + 1).trim();
                        }

                        String by = line.substring(byIndex + 5, line.length() - 1).trim();
                        Deadlines deadline = new Deadlines(desc, by, tag);
                        if (isDone) deadline.markTaskAsDone();
                        tasks.add(deadline);
                        break;
                    case 'E':
                        int fromIndex = line.indexOf("(from:");
                        int toIndex = line.indexOf("to:", fromIndex);
                        if (fromIndex == -1 || toIndex == -1)
                            throw new FridayTaskDecodeException("Malformed event: " + line);

                        String rawDescE = line.substring(7, fromIndex).trim();
                        String descE = rawDescE;
                        String tagE = "";

                        int hashIndexE = rawDescE.lastIndexOf(" #");

                        if (hashIndexE != -1) {
                            desc = rawDescE.substring(0, hashIndexE).trim();
                            tag = rawDescE.substring(hashIndexE + 1).trim();
                        }

                        String from = line.substring(fromIndex + 6, toIndex).trim();
                        String to = line.substring(toIndex + 3, line.length() - 1).trim();
                        Events event = new Events(descE, from, to, tagE);
                        if (isDone) event.markTaskAsDone();
                        tasks.add(event);
                        break;
                    default:
                        throw new FridayTaskDecodeException("Unknown task type: " + type);
                }
            } catch (Exception e) {
                throw new FridayTaskDecodeException("Failed to decode line: " + line + " (" + e.getMessage() + ")");
            }
        }
        return tasks;
    }

}
