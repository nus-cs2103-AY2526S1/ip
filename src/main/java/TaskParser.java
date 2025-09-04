public class TaskParser {
    public static Task fromString(String line) throws ChashException {
        String[] parts = line.split(" \\| ");
        //Sanity check
        if (parts.length < 3) {
            throw new ChashException("Invalid line");
        }

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Task task;
        switch (type) {
        case Todo.TASKTYPE:
            //Sanity check
            if (parts.length != 3) {
                throw new ChashException("Todo task invalid");
            }
            task = new Todo(description);
            break;

        case Deadline.TASKTYPE:
            //Sanity check
            if (parts.length != 4) {
                throw new ChashException("Deadline task invalid");
            }
            task = new Deadline(description, parts[3]);
            break;

        case Event.TASKTYPE:
            //Sanity check
            if (parts.length != 5) {
                throw new ChashException("Event task invalid");
            }
            task = new Event(description, parts[3], parts[4]);
            break;
        
        default:
            throw new ChashException("Invalid task type: " + type);
        }

        return task.setDone(isDone);
    }
}
