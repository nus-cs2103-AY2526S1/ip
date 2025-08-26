package minhgpt.task;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Encapsulate a deadline task.
 */
class TaskDeadline extends Task {
    static {
        register("^deadline .+ /by .+", TaskDeadline::new);
    }

    /** Time that task need to be done before. */
    private LocalDate deadline;

    /**
     * Construct a basic task.
     * 
     * @param input Input from user to create a deadline task.
     */
    public TaskDeadline(String input) {
        super(input.substring(9).split(" /by ")[0]);
        deadline = LocalDate.parse(input.substring(9).split(" /by ")[1]);
    }

    @Override
    public ArrayList<String> toCommands() {
        ArrayList<String> commands = super.toCommands();
        commands.set(0, String.format("deadline %s /by %s", commands.get(0),
                deadline.format(DATE_SAVE_FORMAT)));
        return commands;
    }

    /**
     * @return String representation of a deadline task.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                this.deadline.format(DATE_OUTPUT_FORMAT));
    }
}
