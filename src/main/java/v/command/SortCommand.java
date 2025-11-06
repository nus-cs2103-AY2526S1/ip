package v.command;

import java.util.Comparator;
import java.util.List;

import v.storage.Storage;
import v.task.Task;
import v.task.TaskList;
import v.ui.Ui;

/**
 * Command to sort tasks by various criteria with V's dramatic flair.
 */
public class SortCommand extends Command {
    private final String sortCriteria;

    /**
     * Constructs a SortCommand with the specified sort criteria.
     *
     * @param sortCriteria the criteria to sort by (date, type, status, name, or default)
     */
    public SortCommand(String sortCriteria) {
        this.sortCriteria = sortCriteria.toLowerCase().trim();
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        // Assertion: parameters should not be null
        assert tasks != null : "TaskList cannot be null";
        assert ui != null : "Ui cannot be null";
        assert storage != null : "Storage cannot be null";

        try {
            // If no criteria specified, show sort options
            if (sortCriteria.equals("default") || sortCriteria.isEmpty()) {
                showSortOptions(ui);
                return false;
            }

            List<Task> sortedTasks = getSortedTasks(tasks);
            ui.showSortedTasks(sortedTasks, sortCriteria);
            return false;
        } catch (Exception e) {
            ui.showError("The shadows whisper of an error while sorting: " + e.getMessage());
            return false;
        }
    }

    /**
     * Shows available sort options with V's dramatic flair.
     */
    private void showSortOptions(Ui ui) {
        String optionsText = "Voilà! The shadows reveal the art of organizing your revolutionary agenda:\n\n"
                + "🎭 SORTING OPTIONS:\n"
                + "• sort by date - Sort by date (deadlines/events chronologically)\n"
                + "• sort by type - Group by type (todo, deadline, event)\n"
                + "• sort by status - Pending tasks first, then completed\n"
                + "• sort by name - Sort alphabetically by description\n\n"
                + "The shadows whisper: Choose your preferred method of organization!";

        ui.showError(optionsText); // Using showError to display the options
    }

    /**
     * Sorts tasks based on the specified criteria.
     *
     * @param tasks the TaskList to sort
     * @return a sorted list of tasks
     */
    private List<Task> getSortedTasks(TaskList tasks) {
        List<Task> taskList = tasks.getAllTasks();
        switch (sortCriteria) {
        case "date":
            return sortByDate(taskList);
        case "type":
            return sortByType(taskList);
        case "status":
            return sortByStatus(taskList);
        case "name":
            return sortByName(taskList);
        case "default":
        case "":
            return taskList; // Return original order
        default:
            throw new IllegalArgumentException("Unknown sort criteria: " + sortCriteria);
        }
    }

    /**
     * Sorts tasks by date (deadlines and events first, then by date).
     */
    private List<Task> sortByDate(List<Task> tasks) {
        return tasks.stream()
                .sorted(Comparator
                        .comparing((Task task) -> {
                            // Extract date for comparison using toString parsing
                            String taskString = task.toString();
                            if (taskString.contains("(by:")) {
                                // Deadline task - extract date from display format
                                String datePart = taskString.substring(taskString.indexOf("(by:") + 4);
                                datePart = datePart.substring(0, datePart.indexOf(")"));
                                return parseDateForSorting(datePart.trim());
                            } else if (taskString.contains("(from:")) {
                                // Event task - extract date from display format
                                String datePart = taskString.substring(taskString.indexOf("(from:") + 6);
                                datePart = datePart.substring(0, datePart.indexOf(" to:"));
                                return parseDateForSorting(datePart.trim());
                            } else {
                                // Todo task - put at end
                                return "9999-12-31";
                            }
                        })
                        .thenComparing(Task::toString)) // Secondary sort by description
                .toList();
    }


    /**
     * Parses date string for sorting comparison.
     * Converts dates to YYYY-MM-DD format for proper chronological sorting.
     */
    private String parseDateForSorting(String dateStr) {
        try {
            // Trim the input string
            dateStr = dateStr.trim();
            // Handle different date formats
            if (dateStr.matches("\\d{4}-\\d{2}-\\d{2}")) {
                // Already in YYYY-MM-DD format
                return dateStr;
            } else if (dateStr.matches("\\w{3} \\d{1,2} \\d{4}")) {
                // Format: "Jun 9 4069" -> "4069-06-09"
                String[] parts = dateStr.split(" ");
                String month = parts[0];
                String day = parts[1];
                String year = parts[2];
                // Convert month name to number
                String monthNum = switch (month) {
                case "Jan" -> "01"; case "Feb" -> "02"; case "Mar" -> "03";
                case "Apr" -> "04"; case "May" -> "05"; case "Jun" -> "06";
                case "Jul" -> "07"; case "Aug" -> "08"; case "Sep" -> "09";
                case "Oct" -> "10"; case "Nov" -> "11"; case "Dec" -> "12";
                default -> "01";
                };
                // Pad day with leading zero if needed
                if (day.length() == 1) {
                    day = "0" + day;
                }
                return year + "-" + monthNum + "-" + day;
            } else {
                // Fallback for other formats
                return "9999-12-31";
            }
        } catch (Exception e) {
            return "9999-12-31";
        }
    }

    /**
     * Sorts tasks by type (todo, deadline, event).
     */
    private List<Task> sortByType(List<Task> tasks) {
        return tasks.stream()
                .sorted(Comparator
                        .comparing((Task task) -> {
                            String taskString = task.toString();
                            if (taskString.startsWith("[T]")) {
                                return "1"; // Todo first
                            }
                            if (taskString.startsWith("[D]")) {
                                return "2"; // Deadline second
                            }
                            if (taskString.startsWith("[E]")) {
                                return "3"; // Event third
                            }
                            return "4"; // Unknown type last
                        })
                        .thenComparing(Task::toString)) // Secondary sort by description
                .toList();
    }

    /**
     * Sorts tasks by status (pending first, then completed).
     */
    private List<Task> sortByStatus(List<Task> tasks) {
        return tasks.stream()
                .sorted(Comparator
                        .comparing((Task task) -> task.isDone() ? "1" : "0") // Pending first
                        .thenComparing(Task::toString)) // Secondary sort by description
                .toList();
    }

    /**
     * Sorts tasks alphabetically by description.
     */
    private List<Task> sortByName(List<Task> tasks) {
        return tasks.stream()
                .sorted(Comparator.comparing(task -> {
                    // Extract description part for proper alphabetical sorting
                    String taskString = task.toString();
                    // Find the description part after the status indicator
                    if (taskString.startsWith("[T]")) {
                        // Format: [T][X] description or [T][ ] description
                        // Find the first space after the status indicator
                        int startIndex = taskString.indexOf("] ", 3) + 2;
                        return taskString.substring(startIndex).toLowerCase();
                    } else if (taskString.startsWith("[D]")) {
                        // Format: [D][D][X] description (by: date) or [D][D][ ] description (by: date)
                        // Find the first space after the status indicator
                        int startIndex = taskString.indexOf("] ", 6) + 2;
                        return taskString.substring(startIndex).toLowerCase();
                    } else if (taskString.startsWith("[E]")) {
                        // Format: [E][E][X] description (from: date to: date) or [E][E][ ] description
                        // Find the first space after the status indicator
                        int startIndex = taskString.indexOf("] ", 6) + 2;
                        return taskString.substring(startIndex).toLowerCase();
                    } else {
                        return taskString.toLowerCase();
                    }
                }))
                .toList();
    }
}
