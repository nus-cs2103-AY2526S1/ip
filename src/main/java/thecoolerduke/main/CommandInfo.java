package thecoolerduke.main;

/**
 * Provides information and usage format for each command.
 */
public enum CommandInfo {
    LIST_TASK("list") {
        @Override
        public String getHelpText() {
            return "Shows all tasks currently in your list.\n"
                    + "Format: list";
        }
    },

    ADD_TODO_TASK("todo") {
        @Override
        public String getHelpText() {
            return "Adds a new todo task with a priority.\n"
                    + "Format: todo TASK_NAME /p PRIORITY";
        }
    },

    ADD_DEADLINE_TASK("deadline") {
        @Override
        public String getHelpText() {
            return "Adds a deadline task with due date and priority.\n"
                    + "Format: deadline TASK_NAME /p PRIORITY /by DD/MM/YYYY HH:MM";
        }
    },

    ADD_EVENT_TASK("event") {
        @Override
        public String getHelpText() {
            return "Adds an event task with start, end datetime, and priority.\n"
                    + "Format: event TASK_NAME /p PRIORITY /from DD/MM/YYYY HH:MM /to DD/MM/YYYY HH:MM";
        }
    },

    DELETE_TASK("delete") {
        @Override
        public String getHelpText() {
            return "Deletes a task from the list by its index.\n"
                    + "Format: delete INDEX";
        }
    },

    MARK_TASK("mark") {
        @Override
        public String getHelpText() {
            return "Marks a task as done.\n"
                    + "Format: mark INDEX";
        }
    },

    UNMARK_TASK("unmark") {
        @Override
        public String getHelpText() {
            return "Unmarks a task (set as not done).\n"
                    + "Format: unmark INDEX";
        }
    },

    FIND_TASK("find") {
        @Override
        public String getHelpText() {
            return "Finds tasks with task names containing the given name.\n"
                    + "Format: find NAME";
        }
    },

    HELP("help") {
        @Override
        public String getHelpText() {
            return "Shows a list of all commands or detailed info for a specific command.\n"
                    + "Format: help / help {command}";
        }
    };

    private final String keyword;

    CommandInfo(String keyword) {
        this.keyword = keyword;
    }

    public abstract String getHelpText();

    /**
     * Returns the CommandInfo associated with a keyword.
     *
     * @param keyword command keyword (e.g. "todo", "list")
     * @return CommandInfo or null if not found
     */
    public static CommandInfo fromKeyword(String keyword) {
        for (CommandInfo info : CommandInfo.values()) {
            if (info.keyword.equals(keyword)) {
                return info;
            }
        }
        return null;
    }
}
