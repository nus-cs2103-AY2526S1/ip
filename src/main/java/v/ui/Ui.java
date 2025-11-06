package v.ui;

import java.util.List;
import java.util.Scanner;

import v.task.Task;
import v.task.TaskList;

/**
 * Handles all user interface interactions.
 */
public class Ui {
    private static final String HORIZONTAL_LINE = "    ____________________________________________________________";
    //CHECKSTYLE.OFF: Indentation
    private static final String LOGO =
    "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%##*#%##%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n"
    + "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%@@@@@@@@@%@@@@*%#%%@@%%%%%%%%%%%%%%%%%%%%%%%%\n"
    + "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%@@@@@@@@@@@+*+:=+%@@@@@@@@@@%%%%%%%%%%%%%%%%%%%%\n"
    + "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%%%%%%%%%%%%%%%%%\n"
    + "%%%%%%%%%%%%%%%%%%%%%%%%%%%%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%%%%%%%%%%%%%%%\n"
    + "%%%%%%%%%%%%%%%%%%%%%%%%%%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%%%%%%%%%%%%%\n"
    + "%%%%%%%%%%%%%%%%%%%%%%%%%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%%%%%%%%%%%\n"
    + "%%%%%%%%%%%%%%%%%%%%%%%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%%%%%%%%%\n"
    + "%%%%%%%%%%%%%%%%%%%%%%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%%%%%%%%\n"
    + "%%%%%%%%%%%%%%%%%%%%%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%%%%%%%\n"
    + "%%%%%%%%%%%%%%%%%%%%@%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%%%%%%\n"
    + "%%%%%%%%%%%%%%%%%%%%@@@@@@@@@%@@@@@@%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%%%%%\n"
    + "%%%%%%%%%%%%%%%%%%%@%@@@@@@%@@@@@@@@@@@@@@%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%%%%\n"
    + "%%%%%%%%%%%%%%%%%%%@%@@@%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%%%\n"
    + "%%%%%%%%%%%%%%%%%%%%@@@@@@@@@@@#*+:....::+*++=--:-----===+#%@@@@@@@@@@@@@@@@@%%%\n"
    + "%%%%%%%%%%%%%%%%%%%%%@@@@@@:....:::.....:.::::::::::::::-==+**#@@@@@@@@@@@@@@%%%\n"
    + "%%%%%%%%%%%%%%%%%%%%@%@%%=...:::::::..:::::::::::::.:-+*****####*#@@@@@@@@@@@@%%\n"
    + "%%%%%%%%%%%%%%%%%@@%%%@#:.:.*%@+%@@@*+::::::::::..:=+*%@@@@@@@@@@%#%@@@@@@@@@@%%\n"
    + "%%%%%%%%%%%%%%%%@@%%%%@#:-=-::=+*+@%%@@@@::::::::::@@@@@%%%%**+***%%@@@@@@@@@@@%\n"
    + "%%%%%%%%%%%%%%%%%%%%@@%%:.......:::-++#@@@%::::::.@@@%%%#*===++++++#@@@@@@@@@@@%\n"
    + "%%%%%%%%%%%%%%%%%@%%%@%%........::::---++*-::::--+*%%%#*+===+++*+**%@@@@@@@@@@@@\n"
    + "%%%%%%%%%%%%%%%%%%%@%@@%:::::--***##+====-:...:=*#%%#**%%%@@@@%###%@@@@@@@@@@@@@\n"
    + "%%%%%%%%%%%%%%%@@%%%@@@%-:-+#@@@@@@@@@@%+:....:+*###@@@@@@@@@@%@@@@@@@@@@@@@@@@@\n"
    + "%%%%%%%%%%%%%%%@@%@%%@%@-.....:---=+=:::......:+**##%#++-=+**++++*%@@@@@@@@@@@@@\n"
    + "%%%%%%%%%%%%%%%@@@@%%@%%......................:+***##+--:::---==+*#%%%%@@@@@@@@@\n"
    + "%%%%%%%%%%%%%%%@@@%@@@@%......................:+++*##*=-----===++*#%@%@@@@@@@@@@\n"
    + "%%%%%%%%%%%%%%@@@%%%@@@%:.....................:+******++==-==++**##%@@@@@@@@@@@@\n"
    + "%%%%%%%%%%%%%%@@@@%@@@@@=:..:::::::...........:=*+*#**#**+++++**##%@@@@@@@@@@@@@\n"
    + "%%%%%%%%%%%%%%%@@@@%@@%@%-:::::-----=:........:=+******##%######%%@@@%@@@@@@@@@@\n"
    + "%%%%%%%%%%%%%%%%@@%%%@@@@+::-+**#=:.....=##:==+***%%#*****##%@@@@@@@@@@@@@@@@@@@\n"
    + "%%%%%%%%%%%%%%%%@@%@%%@@@@-:-+-##-........:+=%@@@@%#******##@@@@@@@@@@@@@@@@@@@@\n"
    + "%%%%%%%%%%%%%#@%%@@%%@@@@@@-:==.#@%+.....+%*#@%*@@@@@***#%@@@@@@@@@@@%@@@@@@@@@@\n"
    + "%%%%%#%%%#%%%%%%@%%%%%@@@@@%-:-=.:%@@@@@%%%%%-:::%@@@@@@@@@@%@@@@@@@@@@@@@@@@@@@\n"
    + "%%%%%%%%#%%%%#%@%@%%@@@@@@@%#-.:=:....::+%%%@@@@@@@@%%%%%%#%%@@@@@@@@@@@@@@@@@@@\n"
    + "%%%#%#%#%%%%#%%%%%%%%@@@@@@@%*-.:--::.......::----=+++*###%%@@@@@@@@@@@@@@@@@@@@\n"
    + "%%#%%%%#%%%%%%%%@@%%%%@@@@@@@%*-...-....::--==+**########%%@@@@@@@@@%%@@@@@@@@@@\n"
    + "%%%%%%%#%#%%#%@%@@%%%%@@@@@@@@%+-...::.......-@@@#*+**###%@@@@@@@@@@%@@@@@@@@@@@\n"
    + "%%%%%%%%%%%%%%%@@@@%@%@@@@@@@@@@+::..........=@%@#*++*##%%%@@@@@@@@@%@@@@@@@@@@@\n"
    + "%%%%%%%%%%%%%%%%@%%%@%@%@@@@@@@@@%-:.........+%@@@#*+*#%%%@@@@@@@@@@@@@@@@@@@@@@\n"
    + "%%%%%%%%%%%%%%%%%@@%@@@@@@@@@@@@@@@@-:.......#%@@@##**##%@@@@@@@@@@@@@@@@@@@@@@@\n"
    + "%%%%%%%%%%%%%%%%%%%@@%@@@@@@@@@@@@@@@@+:.....+%@@@#**##%@@@@@@@%@@@@@@@@@@@@@@@@\n"
    + "%%%%%%%%%%%%%%@%@@@%@@@@@@@@@@@@@@@@@@@@@@%=::.-%@@*#*##@@@@@@@@@%@@@%@@@@@@@@@@@@\n"
    + "%%%%#%%%%%@%%@%%%@%@@@@@@@@@@@@@@@@@@@@@@@@@@@@%@@@%@@@@@@@@@@@@@@@@@%@@@@@@@@@@@@\n"
    + "%#%%#%%%%%%%%%%%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%%@@@@@@@@@@@\n"
    + "#%%#%%%%@%@@%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
    + "%##%%%%%%@@@%@%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%@@@@@@@@@@@@@\n"
    + "#%%%%@@%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
    + "#%%%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
    + "###%%%%%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%%%%%%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
    + "%%%%%%%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%@@@#@@%@@@%%%@@%%@@%@@@@@@@@@@@@@@@@@@@@@@\n"
    + "%%%%%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%%@@%%%@@%%@@@@%@@@@@@%%@@@@@@@@@@@@@@@@@@@@@\n"
    + "%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%%#%@@@%%%%%%%@@@@@@@@@@@%@@@@@@@@@@@@@@@@@@\n"
    + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%%%%%#%@@%#######%@@@@@@@%%@@@@@@@@@@@@@@@@@@\n"
    + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%@@@##%%%#%%%%%@@@@@@@@%%%@@@@@@@@@@@@@@@@@@@\n"
    + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%%%%%%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
    + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%%%%%%%%%@@@@@@@@@@%%@@@@@@@@@@@@@@@@@@@@@@\n"
    + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%@@%%%%%@@@@@@%%%@@@@@@@@@@@@@@@@@@@@@@@@@\n"
    + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%%%%#%%%%%%%%%@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
    + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%%%%##%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
    + "@@@@%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%+--+%%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
    + "@@@@@%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*+=%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
    + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%#@@@@%%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
    + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%@@%#@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
    + "@@@@@@@@@%@@@@@@@@@@@@@@@@@@@@%%%%%@@@@@@@@@@@@@%#%@@@@@@@@@@%%%%%%%%@@@@@@@@@@@\n"
    + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%@%%@@@@@@@@@@%#%@@@@@@@@%%%######%%@@@@@@@@@@\n"
    + "@@@@@@@@@@%@@@@@@@@@@@@@@@@@@@@@@@@@%@%%@@@@@@@@##@@@@@@@%%%######%@@@@@@@@@@@@@\n"
    + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%%%@@%@@@@@*#@@@@%#%%#%#####@@@@@@@@@@@@@@@\n"
    + "@@@@@@@@@@@@@@@@@@@%%%%%%%%@@@@@@@@@@@@%%%%@@@@@@@+%@@@%%%%%######@@@@@@@@@@@@@@@@\n"
    + "@@@@@@@@@@@@@@@@@@@@##%%%%%%%@@%%%%@@@@@@@%@@@@@@+@@@%%%%%######@@@@@@@@@@@@@@@@@\n"
    + "@@@@@@@@@@@@@@@@@@%%##*####%%%%%%%%%%@@@@%@@@@@@+@@@@%%######%@@@@@@@@@@@@@@@@@@\n"
    + "@@@@@@@@@@@@@@@@@@@@#########%%%%%@@@@@@@@@@%%@@*@@@%######@@@@@@@@@@@@@@@@@@@@@\n";
    //CHECKSTYLE.ON: Indentation
    private final Scanner scanner;

    /**
     * Constructor for Ui.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Shows welcome message when the application starts.
     */
    public void showWelcome() {
        System.out.println(LOGO);
        System.out.println("Voilà! In view, a voice of the vox populi.");
        System.out.println();
        System.out.println("Behold—the visage behind the letter.");
        System.out.println();
        System.out.println("A humble vessel of verbosity and vigilance.");
        System.out.println();
        System.out.println("I am V. Voice for the voiceless. What do you require?");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Shows a horizontal line separator.
     */
    public void showLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Shows an error message.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        System.out.println("     " + message);
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Shows a message when a task is added.
     *
     * @param task The task that was added.
     * @param size The new size of the task list.
     */
    public void showTaskAdded(Task task, int size) {
        System.out.println("     Got it. I have inscribed this task:");
        System.out.println("       " + task);
        System.out.println("     Now you have " + size + " task" + (size != 1 ? "s" : "") + " in the list.");
    }

    /**
     * Shows the list of tasks.
     *
     * @param tasks The list of tasks to display.
     */
    public void showTaskList(TaskList tasks) {
        if (tasks.isEmpty()) {
            System.out.println("     Your list of tasks is empty, like a stage before the play begins.");
            return;
        }
        System.out.println("     Your current conspiracies (tasks):");
        // Use streams to format task list with indices
        java.util.stream.IntStream.range(0, tasks.size())
                .mapToObj(i -> "     " + (i + 1) + "." + tasks.get(i))
                .forEach(System.out::println);
    }

    /**
     * Shows the results of a find query.
     *
     * @param matches List of matching tasks.
     */
    public void showFindResults(List<Task> matches) {
        showLine();
        if (matches.isEmpty()) {
            System.out.println("     No matches. Even the shadows are silent.");
        } else {
            System.out.println("     Here are the matching tasks in your list:");
            // Use streams to format search results with indices
            java.util.stream.IntStream.range(0, matches.size())
                    .mapToObj(i -> "     " + (i + 1) + "." + matches.get(i))
                    .forEach(System.out::println);
        }
        showLine();
    }

    /**
     * Shows a goodbye message when the application exits.
     */
    public void showGoodbye() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("     The curtain descends, everything ends too soon, too soon.");
        System.out.println("     Beneath this mask there is more than flesh. Beneath this mask there is an idea.");
        System.out.println("     And ideas are bulletproof!");
        System.out.println("     Farewell. May we meet again in the shadows.");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Reads a command from the user.
     *
     * @return The command entered by the user.
     */
    public String readCommand() {
        System.out.println("     What is your command?");
        return scanner.nextLine().trim();
    }

    /**
     * Shows a loading error message.
     */
    public void showLoadingError() {
        showError("Error loading tasks. Starting with an empty task list.");
    }

    /**
     * Shows sorted tasks with V's dramatic flair.
     *
     * @param sortedTasks The sorted list of tasks to display.
     * @param criteria The sort criteria used.
     */
    public void showSortedTasks(List<Task> sortedTasks, String criteria) {
        if (sortedTasks.isEmpty()) {
            System.out.println("     The stage is set, but the script is blank. "
                    + "Your revolutionary agenda awaits its first act.");
            return;
        }

        // V-themed response based on sort criteria
        String dramaticIntro = getDramaticSortIntro(criteria);
        System.out.println("     " + dramaticIntro);
        System.out.println();

        // Use streams to format sorted task list with indices
        java.util.stream.IntStream.range(0, sortedTasks.size())
                .mapToObj(i -> "     " + (i + 1) + "." + sortedTasks.get(i))
                .forEach(System.out::println);

        System.out.println();
        System.out.println("     Total acts of rebellion: " + sortedTasks.size() + " task(s).");
    }

    /**
     * Gets a dramatic V-themed introduction based on sort criteria.
     *
     * @param criteria The sort criteria.
     * @return A dramatic introduction message.
     */
    private String getDramaticSortIntro(String criteria) {
        switch (criteria.toLowerCase()) {
        case "date":
            return "Voilà! Your revolutionary agenda, now perfectly orchestrated by time! "
                    + "The shadows reveal your conspiracies in chronological order.";
        case "type":
            return "Behold! Your tasks, now organized by their nature! "
                    + "The shadows have grouped your conspiracies by type.";
        case "status":
            return "The shadows reveal the status of your revolutionary agenda! "
                    + "Pending missions first, then completed victories.";
        case "name":
            return "Voilà! Your conspiracies, now arranged alphabetically! "
                    + "The shadows have organized your agenda in perfect order.";
        default:
            return "Behold! Your current conspiracies against the mundane! "
                    + "The shadows reveal your revolutionary agenda.";
        }
    }

    /**
     * Shows help information with V's dramatic flair.
     *
     * @param topic The help topic to display.
     */
    public void showHelp(String topic) {
        switch (topic.toLowerCase()) {
        case "sort":
            showSortHelp();
            break;
        case "general":
        default:
            showGeneralHelp();
            break;
        }
    }

    /**
     * Shows general help information.
     */
    private void showGeneralHelp() {
        System.out.println("     Voilà! The shadows reveal the secrets of this revolutionary tool:");
        System.out.println();
        System.out.println("     📋 TASK MANAGEMENT:");
        System.out.println("     • todo <description> - Add a new task");
        System.out.println("     • deadline <description> /by <date> - Add a deadline");
        System.out.println("     • event <description> /from <start> /to <end> - Add an event");
        System.out.println("     • list - Show all tasks");
        System.out.println("     • mark <number> - Mark task as done");
        System.out.println("     • unmark <number> - Mark task as not done");
        System.out.println("     • delete <number> - Remove a task");
        System.out.println();
        System.out.println("     🔍 SEARCH & ORGANIZE:");
        System.out.println("     • find <keyword> - Search for tasks");
        System.out.println("     • sort [by <criteria>] - Sort tasks (see 'help sort')");
        System.out.println();
        System.out.println("     ℹ️  HELP:");
        System.out.println("     • help - Show this help");
        System.out.println("     • help sort - Show sorting options");
        System.out.println("     • bye - Exit the application");
        System.out.println();
        System.out.println("     The shadows whisper: Use 'help sort' for sorting secrets!");
    }

    /**
     * Shows sorting help information.
     */
    private void showSortHelp() {
        System.out.println("     Voilà! The shadows reveal the art of organizing your revolutionary agenda:");
        System.out.println();
        System.out.println("     🎭 SORTING COMMANDS:");
        System.out.println("     • sort - Show tasks in original order");
        System.out.println("     • sort by date - Sort by date (deadlines/events chronologically)");
        System.out.println("     • sort by type - Group by type (todo, deadline, event)");
        System.out.println("     • sort by status - Pending tasks first, then completed");
        System.out.println("     • sort by name - Sort alphabetically by description");
        System.out.println();
        System.out.println("     📅 DATE SORTING:");
        System.out.println("     - Deadlines and events are sorted by their dates");
        System.out.println("     - Todos appear at the end (no specific date)");
        System.out.println("     - Chronological order: earliest dates first");
        System.out.println();
        System.out.println("     🏷️  TYPE SORTING:");
        System.out.println("     - Todos appear first");
        System.out.println("     - Deadlines appear second");
        System.out.println("     - Events appear third");
        System.out.println();
        System.out.println("     ✅ STATUS SORTING:");
        System.out.println("     - Pending (incomplete) tasks appear first");
        System.out.println("     - Completed tasks appear second");
        System.out.println();
        System.out.println("     🔤 NAME SORTING:");
        System.out.println("     - Tasks are sorted alphabetically by description");
        System.out.println("     - Case-insensitive sorting");
        System.out.println();
        System.out.println("     The shadows whisper: Each sort reveals your agenda in a new light!");
    }
}
