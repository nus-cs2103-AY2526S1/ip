package johnny.commands;

import java.time.LocalDate;

import johnny.storage.Storage;
import johnny.tasklist.TaskList;
import johnny.tasks.PeriodTask;
import johnny.tasks.Task;
import johnny.ui.Ui;

public class PeriodCommand extends Command {
    protected String name;
    protected LocalDate start;
    protected LocalDate end;

    public PeriodCommand(String name, LocalDate start, LocalDate end) {
        this.name = name;
        this.start = start;
        this.end = end;
    }

    public String getName() {
        return this.name;
    }

    public LocalDate getStart() {
        return this.start;
    }

    public LocalDate getEnd() {
        return this.end;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task newTask = new PeriodTask(this.name, this.start, this.end);
        tasks.addTask(newTask);
        return ui.printAddTaskMessage(tasks, newTask);
    }

    @Override
    public boolean isBye() {
        return false;
    }
}
