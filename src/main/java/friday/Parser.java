package friday;

import friday.task.Deadline;
import friday.task.DeadlineArgsException;
import friday.task.Event;
import friday.task.EventArgsException;
import friday.task.Task;
import friday.task.ToDo;

public class Parser {
        private static final String TASK_NUMBER_PROMPT = "Please also specify the task number.\n";
        private static final String INVALID_TASK_NUMBER = "Ah... This task number does not exist!\n";
        private static final String INVALID_DESCRIPTION = "Please provide a valid task description.\n";

        private final Ui ui;

        public Parser(Ui ui) {
                assert ui != null : "UI instance cannot be null";
                this.ui = ui;
        }

        /**
         * Parses the user input and executes the corresponding command.
         * @param input The user input string.
         * @return The response string after executing the command.
         */

        public String parse(String input) {
                assert input != null : "Input string cannot be null";
                assert !input.trim().isEmpty() : "Input string cannot be empty";

                try {
                        Command cmd = Command.getCommand(input);
                        assert cmd != null : "Command cannot be null";

                        return switch (cmd) {
                                case BYE -> handleBye();
                                case LIST -> handleList();
                                case FIND -> handleFind(input);
                                case DONE -> handleDoneTasks();
                                case PENDING -> handlePendingTasks();
                                case HELP -> handleHelp();
                                case UNMARK, MARK, DELETE -> handleTaskStateChange(cmd, input);
                                case TODO, DEADLINE, EVENT -> handleTaskCreation(cmd, input);
                        };
                } catch (IllegalArgumentException e) {
                        return handleUnknownCommand(input);
                }
        }

        private String handleBye() {
                this.ui.stop();
                return "";
        }

        /**
         * Creates a string representation of the task list.
         * */
        private String handleList() {
                StringBuilder out = new StringBuilder("Here are your tasks:\n");
                for (int i = 0; i < this.ui.getTaskCount(); i++) {
                        out.append(i + 1).append(". ").append(this.ui.getTask(i)).append("\n");
                }
                return out.toString();
        }

        /**
         * Finds tasks containing the specified keyword
         * */
        private String handleFind(String input) {
                String[] parts = input.split(" ");
                if (parts.length < 2) {
                        return "Please specify the keyword to find";
                }

                String keyword = parts[1].trim();
                TaskList matchedTasks = this.ui.find(keyword);
                StringBuilder out = new StringBuilder("Here are the matching tasks in your list:\n");
                for (int i = 0; i < matchedTasks.count(); i++) {
                        out.append(i + 1).append(". ").append(matchedTasks.get(i)).append("\n");
                }
                return out.toString();
        }

        private String handleTaskStateChange(Command cmd, String input) {
                String[] parts = input.split(" ");
                if (parts.length < 2) {
                        return TASK_NUMBER_PROMPT;
                }

                try {
                        int taskNumber = Integer.parseInt(parts[1]) - 1;
                        if (!isValidTaskIndex(taskNumber)) {
                                return INVALID_TASK_NUMBER;
                        }
                        return executeTaskStateChange(cmd, taskNumber);
                } catch (NumberFormatException e) {
                        return "Please enter a valid task number.\n";
                }
        }

        private boolean isValidTaskIndex(int index) {
                return index >= 0 && index < this.ui.getTaskCount();
        }

        private String executeTaskStateChange(Command cmd, int taskNumber) {
                Task task = this.ui.getTask(taskNumber);
                return switch (cmd) {
                        case UNMARK -> {
                                task.markAsNotDone();
                                yield "OK, I've marked this task as not done: " + task + "\n";
                        }
                        case MARK -> {
                                task.markAsDone();
                                yield "Nice! I've marked this task as done: " + task + "\n";
                        }
                        case DELETE -> {
                                Task removedTask = this.ui.deleteTask(taskNumber);
                                yield String.format("Noted. I've removed this task: %s\nNow you have %d task(s) in the list.\n",
                                        removedTask, this.ui.getTaskCount());
                        }
                        default -> "";
                };
        }

        private String handleTaskCreation(Command cmd, String input) {
                String[] parts = input.split(" ", 2);
                if (parts.length < 2) {
                        return INVALID_DESCRIPTION;
                }

                try {
                        Task task = createTask(cmd, parts[1]);
                        if (task == null) {
                                return "Unknown command. Please use 'todo', 'deadline', or 'event'.\n";
                        }

                        this.ui.addTask(task);
                        return String.format("Got it. I've added this task:\n%s\nNow you have %d task(s) in the list.\n",
                                task, this.ui.getTaskCount());
                } catch (DeadlineArgsException | EventArgsException e) {
                        return e.getMessage();
                }
        }

        private Task createTask(Command cmd, String description) {
                return switch (cmd) {
                        case TODO -> new ToDo(description);
                        case DEADLINE -> new Deadline(description);
                        case EVENT -> new Event(description);
                        default -> null;
                };
        }

        /**
         * Handles unknown/invalid commands and provides helpful suggestions to the user.
         * @param input The invalid command input
         * @return A helpful error message with command suggestions
         */
        private String handleUnknownCommand(String input) {
                String trimmedInput = input.trim().toLowerCase();
                StringBuilder response = new StringBuilder();
                response.append("Sorry, I don't understand '" + input + "'.\n\n");
                response.append("Here are the commands I understand:\n");
                response.append("• todo <description> - Add a todo task\n");
                response.append("• deadline <description> /by <date> - Add a deadline task\n");
                response.append("• event <description> /from <start> /to <end> - Add an event\n");
                response.append("• list - Show all tasks\n");
                response.append("• find <keyword> - Search tasks by keyword\n");
                response.append("• done - Show completed tasks\n");
                response.append("• pending - Show incomplete tasks\n");
                response.append("• mark <number> - Mark task as done\n");
                response.append("• unmark <number> - Mark task as not done\n");
                response.append("• delete <number> - Delete a task\n");
                response.append("• help - Show detailed help\n");
                response.append("• bye - Exit the application\n\n");
                
                // Provide suggestions for common typos
                if (trimmedInput.contains("todo") || trimmedInput.contains("to do")) {
                        response.append("Did you mean 'todo <description>'?\n");
                } else if (trimmedInput.contains("dead") || trimmedInput.contains("due")) {
                        response.append("Did you mean 'deadline <description> /by <date>'?\n");
                } else if (trimmedInput.contains("event") || trimmedInput.contains("meet")) {
                        response.append("Did you mean 'event <description> /from <start> /to <end>'?\n");
                } else if (trimmedInput.contains("list") || trimmedInput.contains("show") || trimmedInput.contains("all")) {
                        response.append("Did you mean 'list'?\n");
                } else if (trimmedInput.contains("find") || trimmedInput.contains("search")) {
                        response.append("Did you mean 'find <keyword>'?\n");
                } else if (trimmedInput.contains("done") || trimmedInput.contains("complete")) {
                        response.append("Did you mean 'mark <task number>'?\n");
                } else if (trimmedInput.contains("remove") || trimmedInput.contains("delete")) {
                        response.append("Did you mean 'delete <task number>'?\n");
                }
                
                return response.toString();
        }

        /**
         * Handles the 'done' command to show all completed tasks
         * @return String representation of completed tasks
         */
        private String handleDoneTasks() {
                TaskList completedTasks = this.ui.findByStatus(true);
                if (completedTasks.count() == 0) {
                        return "You have no completed tasks yet. Keep working!\n";
                }
                
                StringBuilder out = new StringBuilder("Here are your completed tasks:\n");
                for (int i = 0; i < completedTasks.count(); i++) {
                        out.append(i + 1).append(". ").append(completedTasks.get(i)).append("\n");
                }
                return out.toString();
        }

        /**
         * Handles the 'pending' command to show all incomplete tasks
         * @return String representation of pending tasks
         */
        private String handlePendingTasks() {
                TaskList pendingTasks = this.ui.findByStatus(false);
                if (pendingTasks.count() == 0) {
                        return "Great! You have no pending tasks. All done!\n";
                }
                
                StringBuilder out = new StringBuilder("Here are your pending tasks:\n");
                for (int i = 0; i < pendingTasks.count(); i++) {
                        out.append(i + 1).append(". ").append(pendingTasks.get(i)).append("\n");
                }
                return out.toString();
        }

        /**
         * Handles the 'help' command to show available commands
         * @return String with help information
         */
        private String handleHelp() {
                StringBuilder response = new StringBuilder();
                response.append("Friday Task Manager - Available Commands:\n\n");
                response.append("• todo <description> - Add a todo task\n");
                response.append("• deadline <description> /by <date> - Add a deadline task\n");
                response.append("• event <description> /from <start> /to <end> - Add an event\n");
                response.append("• list - Show all tasks\n");
                response.append("• find <keyword> - Search tasks by keyword\n");
                response.append("• done - Show all completed tasks\n");
                response.append("• pending - Show all incomplete tasks\n");
                response.append("• mark <number> - Mark task as done\n");
                response.append("• unmark <number> - Mark task as not done\n");
                response.append("• delete <number> - Delete a task\n");
                response.append("• help - Show this help message\n");
                response.append("• bye - Exit the application\n\n");
                response.append("Examples:\n");
                response.append("  todo Buy groceries\n");
                response.append("  deadline Submit report /by 2023-12-25\n");
                response.append("  event Team meeting /from 2023-12-20 10:00 /to 2023-12-20 11:00\n");
                return response.toString();
        }
}
