package bob.util;

import java.util.HashMap;
import java.util.Map;

public class Help {
    private static final Map<String, String> commands = new HashMap<>();

    static {
        commands.put("list", "list\n    Lists all tasks");
        commands.put("todo", "todo <task>\n    Adds a todo item");
        commands.put("deadline", "deadline <task> /by <duedate>\n    Adds a deadline with <duedate> (format: yyyy-MM-dd)");
        commands.put("event", "event <task> /from <startdate> /to <enddate>\n    Adds an event with a start and end date (format: yyyy-MM-dd)");
        commands.put("find", "find <query>\n    Searches for tasks with <query> in the description");
        commands.put("delete", "delete <index>\n    Removes the task at the given index");
        commands.put("update", "update <index> /des <new description>\n" +
                "update <index> /by <new deadline>\n" +
                "update <index> /from <new start> /to <new end>\n" +
                "    Updates details of tasks");
        commands.put("help", "help [command]\n    Shows all commands, or detailed help for one command");
    }

    public static String getHelp(String command) {
        if (command == null || command.isBlank()) {
            StringBuilder sb = new StringBuilder("Available commands:\n");
            for (String key : commands.keySet()) {
                sb.append("  ").append(key).append("\n");
            }
            sb.append("\nType 'help <command>' for details on a specific command.");
            return sb.toString();
        } else {
            String cmd = command.toLowerCase();
            return commands.getOrDefault(cmd, "Unknown command: " + command);
        }
    }
}
