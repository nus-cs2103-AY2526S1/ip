package hachi;


/**
 * The Ui class handles the user interface and interaction with the user.
 * Instead of printing directly, all methods return Strings so the caller
 * can decide how to display or test them.
 */
public class Ui {
    private TaskList tasks;

    /**
     * Constructs a Ui instance that interacts with the provided TaskList.
     *
     * @param tasks the TaskList this UI will manage interactions for
     */
    public Ui(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the initial greeting message and application logo when the program starts.
     */
    public static String start() {
        return "Woof! Nice to meet you\n";
    }

    /**
     * Returns the exit message and application logo when the program terminates.
     */
    public static String end() {
        return "Come play again!\n";
    }

    public String success(CommandCode code) {
        StringBuilder sb = new StringBuilder();
        switch (code) {
        case TODO:
            sb.append("🐶 Hachi fetched a new task for you!\n");
            break;
        case DEADLINE:
            sb.append("🐶 Hachi noted your deadline carefully!\n");
            break;
        case EVENT:
            sb.append("🐶 Hachi added your event to the calendar!\n");
            break;
        case MARK:
            sb.append("🐶 Hachi wags his tail proudly: 'Task complete!'\n");
            break;
        case UNMARK:
            sb.append("🐶 Hachi whines softly: 'Did we bark too soon?'\n");
            break;
        case DELETE:
            sb.append("🐶 Hachi dug a hole and buried that task. It’s gone!\n");
            break;
        case FIND:
            sb.append("🐶 Hachi sniffed around and found these matching tasks:\n");
            break;
        case NOTE:
            sb.append("🐶 Hachi has an increased knowledge of that task! \n");
            break;
        }
        //print updated task count if it is altered
        if (code == CommandCode.TODO || code == CommandCode.EVENT ||
                code == CommandCode.DEADLINE || code == CommandCode.DELETE) {
            sb.append(String.format("You now have (%d) task%s\n",
                    tasks.tasks.size(),
                    tasks.tasks.size() == 1 ? "" : "s"));
        }
        return sb.toString();
    }

    /**
     * Returns an error message based on the action that failed.
     */
    public static String failure(CommandCode error) {
        StringBuilder sb = new StringBuilder();
        switch (error) {
        case TODO:
            sb.append("Woof! You can't just do nothing!\n");
            break;
        case DEADLINE:
            sb.append("Hachi paws at you: 'I need both a task and a deadline! "
                    + "Use it like: deadline <task> /by <time>'\n");
            sb.append(printTimeNote());
            break;
        case EVENT:
            sb.append("Hachi tilts his head: 'I need a task, a start time, and an end time! "
                    + "Use it like: event <task> /from <start> /to <end>'\n");
            sb.append(printTimeNote());
            break;
        case MARK:
            sb.append("Hachi is confused: 'which task should i mark?'\n");
            break;
        case UNMARK:
            sb.append("Hachi is confused: 'which task should i unmark?'\n");
            break;
        case DELETE:
            sb.append("Hachi is confused: 'which task should i remove?'\n");
            break;
        case UNKNOWN:
            sb.append("Hachi tilts his head: 'I don’t understand that command.'\n");
            break;
        case MISSING:
            sb.append("Hachi sniffed everywhere, but no task found with that number.\n");
            sb.append("Maybe fetch another number?\n");
            break;
        case FIND:
            sb.append("Hachi sniffed around but couldn’t find any matching tasks.\n");
            sb.append("Try a different keyword?\n");
            break;
        case BYE:
            sb.append("Hachi tried to save your tasks, but something went wrong.\n");
            sb.append("It seems like there was an issue with the storage.\n");
            sb.append("Please check your storage or try again later.\n");
            break;
        case NOTE:
            sb.append("What should Hachi know about this task?\n");
            sb.append("Try: Note <TaskNumber> <additional notes>\n");
            break;
        case DISPLAY:
            sb.append("Hachi cannot remember anything regarding this task\n");
            break;
        }
        return sb.toString();
    }

    private static String printTimeNote() {
        return "Note: Input your time as d/M/yyyy HHmm so Hachi can understand\n";
    }
}