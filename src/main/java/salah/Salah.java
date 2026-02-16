package salah;

import java.util.ArrayList;
import java.util.List;

public class Salah {

    // load once; keep state in this instance (GUI reuses the same instance)
    private final ArrayList<Task> userTasks = Storage.load();
    private boolean exitRequested = false;

    /** Optional CLI entry point stays as-is for console runs */
    public static void main(String[] args) {
    }

    /** Greeting text for GUI to show first */
    public String getGreeting() {
        return "Hi, I'm Mohammed Salah, The Egyptian King\nHow may I assist you today?";
    }

    /** Goodbye text for GUI to show at the end */
    public String getFarewell() {
        return "Hope you have a great day! Remember, you'll never walk alone :))";
    }

    /** GUI checks this after each response to decide whether to exit */
    public boolean isExitRequested() {
        return exitRequested;
    }

    /** Runs the same logic as CLI, but returns a string for the GUI */
    public String getResponse(String input) {
        try {
            if (input.equals(CommandType.BYE.getKeyword())) {
                Storage.save(userTasks);
                exitRequested = true;
                return getFarewell();
            }

            // LIST
            if (input.equals(CommandType.LIST.getKeyword())) {
                if (userTasks.isEmpty()) return "Your list is empty.";
                StringBuilder sb = new StringBuilder("The Egyptian King has these tasks for you to complete:\n");
                for (int i = 0; i < userTasks.size(); i++) {
                    sb.append(String.format("%d. %s%n", i + 1, userTasks.get(i).toString()));
                }
                return sb.toString().trim();
            }

            // DELETE
            if (input.startsWith(CommandType.DELETE.getKeyword())) {
                int index = Integer.parseInt(input.substring(CommandType.DELETE.getKeyword().length()).trim()) - 1;
                Task t = userTasks.remove(index);
                Storage.save(userTasks);
                return "The Egyptian king has removed this task:\n" + t + "\nThere are still " + userTasks.size()
                        + " tasks in your list!";
            }

            // MARK
            if (input.startsWith(CommandType.MARK.getKeyword())) {
                int index = Integer.parseInt(input.substring(CommandType.MARK.getKeyword().length()).trim()) - 1;
                Task t = userTasks.get(index);
                t.markAsComplete();
                Storage.save(userTasks);
                return "This task is marked as complete. Well done!\n" + t;
            }

            // UNMARK
            if (input.startsWith(CommandType.UNMARK.getKeyword())) {
                int index = Integer.parseInt(input.substring(CommandType.UNMARK.getKeyword().length()).trim()) - 1;
                Task t = userTasks.get(index);
                t.markAsIncomplete();
                Storage.save(userTasks);
                return "Task has been marked as incomplete.\n" + t;
            }

            // DEADLINE
            if (input.startsWith(CommandType.DEADLINE.getKeyword())) {
                try {
                    Deadlines d = Deadlines.parser(input);

                    if (userTasks.contains(d)) {
                        return "Unable to add task: \"" + d.getDescription() + "\" is already present.";
                    }

                    userTasks.add(d);
                    Storage.save(userTasks);
                    return "Got it. I've added this task:\n" + d + "\nThe Egyptian King detects "
                            + userTasks.size() + " tasks in your list!";

                } catch (EmptyDescriptionException | TaskFormattingException e) {
                    return "Error: " + e.getMessage();
                }
            }

            // EVENT
            if (input.startsWith(CommandType.EVENT.getKeyword())) {
                try {
                    Events ev = Events.parser(input);

                    if (userTasks.contains(ev)) {
                        return "Unable to add task: \"" + ev.getDescription() + "\" is already present.";
                    }

                    userTasks.add(ev);
                    Storage.save(userTasks);
                    return "Got it. I've added this task:\n" + ev + "\nThe Egyptian King detects "
                            + userTasks.size() + " tasks in your list!";

                } catch (EmptyDescriptionException | TaskFormattingException e) {
                    return "Error: " + e.getMessage();
                }
            }

            // FIND
            if (input.startsWith(CommandType.FIND.getKeyword())) {
                String[] parts = input.split("\\s+", 2);
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    return "Please provide a keyword to search for.";
                }
                String keyword = parts[1].trim().toLowerCase();
                
                List<Task> matches = userTasks.stream()
                    .filter(t -> t.getDescription().toLowerCase().contains(keyword))
                    .toList();

                if (matches.isEmpty()) {
                    return "No tasks found matching: " + keyword;
                }

                StringBuilder sb = new StringBuilder("Here are the matching tasks with keyword \"" + keyword + "\":\n");
                for (int i = 0; i < matches.size(); i++) {
                    sb.append(String.format("%d. %s%n", i + 1, matches.get(i)));
                }
                return sb.toString().trim();
            }

            // default → treat as ToDo
            ToDos todo = ToDos.parser(input);
            if (userTasks.contains(todo)) {
                return "Unable to add task: \"" + todo.getDescription() + "\" is already present.";
            }

            userTasks.add(todo);
            Storage.save(userTasks);
            return "Got it. I've added this task:\n" + todo + "\nThe Egyptian King detects "
                + userTasks.size() + " tasks in your list!";

        } catch (IndexOutOfBoundsException e) {
            return "Error: the input number has exceeded the number of tasks.";
        } catch (NumberFormatException e) {
            return "Error: please provide a valid number.";
        } catch (EmptyDescriptionException e) {
            return "Error: " + e.getMessage();
        }
    }
}