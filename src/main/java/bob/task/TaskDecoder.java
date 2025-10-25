package bob.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class responsible for decoding save string to task
 */
public class TaskDecoder {
    /**
     * Method that task a save string and decode it back to task
     * 
     * @param taskDesc Save string of the task
     * @return Task based on the save string
     */
    public static Task retTask(String taskDesc) {
        String[] splits = taskDesc.split("\\|");
        String tags = splits[0];
        switch (tags) {
        case "T":
            return new Todo(splits[1], splits[2].equals("X"), Integer.parseInt(splits[3]));
        case "D":
            return new Deadline(splits[1], LocalDate.parse(splits[2], DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                    splits[3].equals("X"), Integer.parseInt(splits[4]));
        case "E":
            return new Event(splits[1], LocalDate.parse(splits[2], DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                    LocalDate.parse(splits[3], DateTimeFormatter.ofPattern("yyyy-MM-dd")), splits[4].equals("X"),
                    Integer.parseInt(splits[5]));

        default:
            throw new IllegalArgumentException("Wrong task type found");
        }
    }
}
