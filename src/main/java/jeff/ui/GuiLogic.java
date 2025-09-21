package jeff.ui;

import java.util.ArrayList;

import jeff.command.Command;
import jeff.command.Parser;
import jeff.storage.JeffException;
import jeff.storage.Storage;
import jeff.task.Deadline;
import jeff.task.Event;
import jeff.task.FixedDurationTask;
import jeff.task.Task;
import jeff.task.TaskList;
import jeff.task.Todo;

class GuiLogic {

    static class Reply {

        final String message;
        final boolean isExit;

        Reply(String message, boolean isExit) {
            this.message = message;
            this.isExit = isExit;
        }
    }

    private final Storage storage = new Storage();
    private final TaskList tasks = new TaskList();

    GuiLogic() {
        // Load existing tasks from storage at startup
        ArrayList<String> lines = storage.load();
        ArrayList<Task> existingTasks = parseStoredLines(lines);
        for (Task t : existingTasks) {
            tasks.add(t);
        }
    }

    Reply handle(String input) {
        try {
            Parser.Result result = Parser.parseCommand(input);
            Command cmd = result.command;
            String description = result.description;

            if (cmd == Command.BYE) {
                return new Reply("Bye! Hope to you see you again soon!", true);
            }

            if (cmd == null) {
                throw new JeffException("Unknown command. Try 'list', 'todo', 'deadline', 'event', 'find', 'mark', 'unmark', 'delete', or 'bye'.");
            }

            switch (cmd) {
                case LIST: {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < tasks.size(); i++) {
                        if (tasks.get(i) != null) {
                            sb.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
                        }
                    }
                    return new Reply(sb.toString().trim(), false);
                }

                case MARK: {
                    int markIdx = Integer.parseInt(description);
                    tasks.get(markIdx - 1).markAsDone();
                    String msg = "Task marked as done!\n" + tasks.get(markIdx - 1) + "\n______________________________";
                    updateStorage(tasks, storage);
                    return new Reply(msg, false);
                }

                case UNMARK: {
                    int unmarkIdx = Integer.parseInt(description);
                    tasks.get(unmarkIdx - 1).undo();
                    String msg = "Task marked as undone!\n" + tasks.get(unmarkIdx - 1);
                    updateStorage(tasks, storage);
                    return new Reply(msg, false);
                }

                case DELETE: {
                    int idx = (Integer.parseInt(description) - 1); // This would be the index to be deleted.
                    if (idx < 0 || idx >= tasks.size()) {
                        throw new JeffException("Invalid task number. Please try again.");
                    }
                    tasks.remove(idx);
                    String msg = "Task has been deleted.\nYou now have " + tasks.size() + " tasks in the list.";
                    updateStorage(tasks, storage);
                    return new Reply(msg, false);
                }

                case TODO: {
                    tasks.add(new Todo(description));
                    String msg = added(input, tasks);
                    updateStorage(tasks, storage);
                    return new Reply(msg, false);
                }

                case DEADLINE: {
                    String[] parts;
                    if (description.contains(" by ")) {
                        parts = description.split(" by ", 2);
                    } else if (description.contains("/by")) {
                        parts = description.split("/by", 2);
                    } else {
                        parts = description.split(" ", 2);
                    }
                    tasks.add(new Deadline(parts[0].trim(), parts[1].trim()));
                    String msg = added(input, tasks);
                    updateStorage(tasks, storage);
                    return new Reply(msg, false);
                }

                case EVENT: {
                    String[] parts;
                    if (description.contains("/at")) {
                        parts = description.split("/at", 2);
                    } else {
                        parts = description.split(" ", 2);
                    }
                    tasks.add(new Event(parts[0].trim(), parts[1].trim()));
                    String msg = added(input, tasks);
                    updateStorage(tasks, storage);
                    return new Reply(msg, false);
                }

                case FIND: {
                    String keyword = description;
                    String msg = findTasks(tasks, keyword);
                    // no save
                    return new Reply(msg, false);
                }

                case FIXEDDURATION: {
                    if (description.isBlank()) {
                        throw new JeffException("Usage: fixedduration <desc> for <n> hours");
                    }

                    String[] fd = description.split(" for ", 2);
                    if (fd.length != 2) {
                        throw new JeffException("Usage: fixedduration <desc> for <n> hours");
                    }

                    String desc = fd[0].trim();
                    String hoursString = fd[1].trim();

                    int hours;
                    try {
                        hours = Integer.parseInt(hoursString.split(" ")[0]);
                    } catch (NumberFormatException e) {
                        throw new JeffException("Duration must be an integer.");
                    }

                    if (hours <= 0) {
                        throw new JeffException("Duration must be > 0.");
                    }

                    tasks.add(new FixedDurationTask(desc, hours));
                    String msg = added(input, tasks);
                    updateStorage(tasks, storage);
                    return new Reply(msg, false);
                }
            }

            return new Reply("", false);

        } catch (JeffException e) {
            return new Reply(e.getMessage(), false);
        } catch (Exception e) {
            return new Reply("Error: " + e.getMessage(), false);
        }
    }

    private static String added(String input, TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("______________________________\n");
        sb.append("Task has been added: ").append(input).append("\n");
        sb.append("You now have ").append(tasks.size()).append(" tasks in the list.\n");
        sb.append("______________________________");
        return sb.toString();
    }

    private static String findTasks(TaskList tasks, String keyword) {
        StringBuilder sb = new StringBuilder();
        sb.append("______________________________\n");
        sb.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getDescription().contains(keyword)) {
                sb.append((i + 1)).append(". ").append(task).append("\n");
            }
        }
        sb.append("______________________________");
        return sb.toString();
    }

    private static String formatTask(Task t) {
        String done = t.isDone() ? "1" : "0";
        String type = t.getType();
        String description = t.getDescription();

        String formatted = String.format("%s|%s|%s", type, done, description);

        if ("D".equals(type)) {
            // this is ok beacuse type is deadline, safe typecast
            formatted += "|" + ((Deadline) t).getForStorage();
        } else if ("E".equals(type)) {
            // this is ok beacuse type is event, safe typecast
            formatted += "|" + ((Event) t).getForStorage();
        } else if ("FD".equals(type)) {
            formatted += "|" + ((FixedDurationTask) t).getDuration();
        }

        return formatted;
    }

    private static void updateStorage(TaskList tasks, Storage storage) {
        ArrayList<String> lines = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            lines.add(formatTask(task));
        }
        storage.save(lines);
    }

    private static ArrayList<Task> parseStoredLines(ArrayList<String> tasks) {
        ArrayList<Task> result = new ArrayList<>();
        if (tasks == null) {
            return result;
        }
        for (String task : tasks) {
            try {
                if (task == null || task.isBlank()) {
                    continue;
                }
                String[] parts = task.split("\\|", -1);
                if (parts.length < 3) {
                    continue;
                }
                String type = parts[0];
                String done = parts[1];
                String desc = parts[2];

                Task t;
                switch (type) {
                    case "T":
                        t = new Todo(desc);
                        break;
                    case "D":
                        if (parts.length < 4) {
                            continue;
                        }
                        t = new Deadline(desc, parts[3]);
                        break;
                    case "E":
                        if (parts.length < 4) {
                            continue;
                        }
                        t = new Event(desc, parts[3]);
                        break;
                    case "FD":
                        if (parts.length < 4) {
                            continue;
                        }
                        int duration;
                        try {
                            duration = Integer.parseInt(parts[3]);
                        } catch (NumberFormatException e) {
                            continue;
                        }
                        t = new FixedDurationTask(desc, duration);
                        break;
                    default:
                        continue;
                }

                if ("1".equals(done)) {
                    t.markAsDone();
                }
                result.add(t);
            } catch (Exception e) {
                System.out.println("Skipping invalid task: " + task);
            }
        }
        return result;
    }
}
