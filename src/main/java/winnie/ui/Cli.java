package winnie.ui;

import java.time.LocalDateTime;

import winnie.chatmessage.Readable;
import winnie.task.Task;
import winnie.tasklist.TaskList;
import winnie.util.DateTimeUtil;
import winnie.uitool.CliReader;
import winnie.uitool.CliWriter;
import winnie.chatmessage.GoodByeMessage;
import winnie.chatmessage.GreetingMessage;
import winnie.chatmessage.TaskListMessage;
import winnie.chatmessage.TaskAddedMessage;
import winnie.chatmessage.MarkTaskMessage;
import winnie.chatmessage.UnmarkTaskMessage;
import winnie.command.Command;
import winnie.command.VoidCommand;
import winnie.exception.WinnieException;
import winnie.parser.Parser;
import winnie.chatmessage.DeleteTaskMessage;
import winnie.chatmessage.ErrorMessage;
import winnie.chatmessage.FoundTasksMessage;

/**
 * CLI class for handling input and output.
 */
public class Cli implements Ui {

    private CliWriter writer;
    private CliReader reader;

    public Cli() {
        this.writer = new CliWriter();
        this.reader = new CliReader();
    }

    @Override
    public void showWelcome() {
        String logo = """
                    %:.     %:-
                    --%::::::%%
                    %::::::::-
                    -:%:#%::::%
                    -:::::::::::
                    :-:::-%::::%%
                    *+%+-----%++*.
                +++++*+++++%+++++++
                ::::++*+++++++++++*%:
                -:::%***++++++++++%:-
                -:::***+%%%%%%#-:%=
                %:::--:::::::::::%:
                    ---::::::::::::=
                    %%::%:::::::::%
                    -:::-:::::::%
                    =-:::-----=-:%
                    -:::   %-:::
                    -::#   %-::%
                    %-::%   %-:::%
                    %%%
                """;
        System.out.println("Hello from\n" + logo);
        writer.write(new GreetingMessage());
    }

    @Override
    public void showGoodbye() {
        writer.write(new GoodByeMessage());
    }

    @Override
    public void showTaskList(TaskList tasks) {
        writer.write(new TaskListMessage(tasks));
    }

    @Override
    public void showTaskAdded(Task task, int taskCount) {
        writer.write(new TaskAddedMessage(task, taskCount));
    }

    @Override
    public void showTaskMarked(Task task) {
        writer.write(new MarkTaskMessage(task));
    }

    @Override
    public void showTaskUnmarked(Task task) {
        writer.write(new UnmarkTaskMessage(task));
    }

    @Override
    public void showTaskDeleted(Task task, int taskCount) {
        writer.write(new DeleteTaskMessage(task, taskCount));
    }

    @Override
    public void showError(String errorMessage) {
        writer.write(new ErrorMessage(errorMessage));
    }

    @Override
    public void showLoadingError() {
        showError("Error loading tasks from file. Starting with empty task list.");
    }

    @Override
    public void showFoundTasks(TaskList foundTasks) {
        writer.write(new FoundTasksMessage(foundTasks));
    }

    @Override
    public void showTaskSnoozed(Task task, LocalDateTime snoozeUntil) {
        String message = "Nice! I've snoozed this task:\n  " + task.toString()
                + "\nIt will reappear at: " + DateTimeUtil.formatForDisplay(snoozeUntil);
        writer.write(new ErrorMessage(message));
    }

    @Override
    public Command readCommand() {
        System.out.print("> ");
        Readable userInput = reader.read();
        try {
            return Parser.parse(userInput.getMessageContent().trim());
        } catch (WinnieException e) {
            showError(e.getMessage());
        }
        return new VoidCommand();
    }
}
