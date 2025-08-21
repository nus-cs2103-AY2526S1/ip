
public class LynxCommand {

    public static void addTodo(String input) throws LynxException {
        if (input.length() <= 4) {
            throw new MissingArgumentException("todo");
        }
        String name = input.substring(5).trim();
        LynxStorage.addTask(new TodoTask(name));
    }

    public static void addDeadline(String input) throws LynxException {
        if (input.length() <= 8) {
            throw new MissingArgumentException("deadline");
        }
        String[] parts = input.substring(9).split("/by", 2);
        if (parts.length < 2) {
            throw new LynxException("Please specify a deadline using '/by'.");
        }
        String name = parts[0].trim();
        String by = parts[1].trim();
        LynxStorage.addTask(new DeadlineTask(name, by));
    }

    public static void addEvent(String input) throws LynxException {
        if (input.length() <= 5) {
            throw new MissingArgumentException("event");
        }
        String[] nameSplit = input.substring(6).split("/from", 2);
        if (nameSplit.length < 2) {
            throw new LynxException("Please specify a start time using '/from'.");
        }
        String name = nameSplit[0].trim();
        String[] timeSplit = nameSplit[1].split("/to", 2);
        if (timeSplit.length < 2) {
            throw new LynxException("Please specify an end time using '/to'.");
        }
        String from = timeSplit[0].trim();
        String to = timeSplit[1].trim();
        LynxStorage.addTask(new EventTask(name, from, to));
    }

    public static void markTask(String input) throws LynxException {
        if (input.length() <= 4) {
            throw new MissingArgumentException("mark");
        }
        input = input.substring(5).trim();
        Task task = findTask(input);
        task.setCompleted();
        LynxUI.printBox("Excellent! Marked as done:\n     " + task.toString());
    }

    public static void unmarkTask(String input) throws LynxException {
        if (input.length() <= 6) {
            throw new MissingArgumentException("unmark");
        }
        input = input.substring(7).trim();
        Task task = findTask(input);
        task.resetCompleted();
        LynxUI.printBox("Alright, marked as not done:\n     " + task.toString());
    }

    private static Task findTask(String input) throws LynxException {
        if (input.startsWith("id:")) {
            // Mark by unique ID
            try {
                int id = Integer.parseInt(input.substring(3).trim());
                return LynxStorage.findTaskById(id);
            } catch (NumberFormatException e) {
                throw new LynxException("Sorry, that isn't a valid ID.");
            }
        } else {
            // Mark by position in list
            try {
                int pos = Integer.parseInt(input);
                return LynxStorage.findTaskByPosition(pos);
            } catch (NumberFormatException e) {
                throw new LynxException("Please provide a valid position number.");
            }
        }
    }

    public static void deleteTask(String input) throws LynxException {
        if (input.length() <= 6) {
            throw new MissingArgumentException("delete");
        }
        input = input.substring(7).trim();
        Task task = findTask(input);
        LynxStorage.removeTask(task);
    }

}
