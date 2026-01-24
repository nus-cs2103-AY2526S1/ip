package noob.command;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import noob.storage.Storage;
import noob.task.TaskList;
import noob.ui.Ui;

public class HelpCommand extends Command {

    private String commandInfos;

    public HelpCommand() {
        List<String> allCommandInfos = Arrays.stream(new String[] {
                todoInfo(),
                deadlineInfo(),
                eventInfo(),
                markInfo(),
                unmarkInfo(),
                deleteInfo(),
                listInfo(),
                findInfo(),
                exitInfo()
        }).sorted().toList();

        this.commandInfos = IntStream.range(0, allCommandInfos.size())
                .boxed()
                .map(i -> (i + 1) + ". " + allCommandInfos.get(i))
                .collect(Collectors.joining("\n\n"));
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) {
        return this.commandInfos;
    }

    private String todoInfo() {
        return "todo <description>: Add a todo task with no deadline";
    }

    private String deadlineInfo() {
        return "deadline <description> /by <yyyy-mm-dd>: Add a deadline task with the specified deadline " +
                "in the given format";
    }

    private String eventInfo() {
        return "event <description> /from <yyyy-mm-dd> /to <yyyy-mm-dd>: Add an event task with the " +
                "specified start and end dates in the given format";
    }

    private String markInfo() {
        return "mark <num>: Mark the task number {num} as done";
    }

    private String unmarkInfo() {
        return "unmark <num>: Mark the task number {num} as not done";
    }

    private String deleteInfo() {
        return "delete <num>: Delete the task number {num} from the list of tasks";
    }

    private String listInfo() {
        return "list: Display the list of tasks that have been added";
    }

    private String findInfo() {
        return "find <matcher>: Filter and display the list of tasks whose descriptions contain {matcher}";
    }

    private String exitInfo() {
        return "bye: Exit the application";
    }
}
