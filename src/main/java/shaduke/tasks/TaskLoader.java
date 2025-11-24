package shaduke.tasks;


import shaduke.ShadukeException;

/**
 * Class that handles the conversion of saved task data from storage
 * into the respective Task objects.
 */
public class TaskLoader {

    /**
     * Converts a saved task into a Task object.
     *
     * @param line the task in storage format.
     * @return the converted Task object.
     */
    public static Task load(String line) {
        String[] parts = line.split(" \\| ");

        String type = parts[0];
        boolean isDone = parts[1].equals("1");

        switch (type) {
            case "T":
                return new Todo(parts[2], isDone);
            case "D":
                return new Deadline(parts[2], parts[3], isDone);
            case "E":
                String from = parts[3].split(" - ")[0];
                String to = parts[3].split(" - ")[1];
                return new Event(parts[2], from, to, isDone);
            default:
                throw new ShadukeException("Too slow a file for me!");
        }
    }
}


