package minhgpt.task;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Encapsulate an event task.
 */
class TaskEvent extends Task {
    static {
        register("^event .+ /from .+ /to .+", TaskEvent::new);
    }

    /** Start time of event. */
    private LocalDate from;
    /** End time of event. */
    private LocalDate to;

    /**
     * Construct a basic task.
     * 
     * @param input Input from user to create an event task.
     */
    public TaskEvent(String input) {
        super(input.substring(6).split(" /from ")[0]);
        String[] split = input.substring(6).split(" /from ")[1].split(" /to ");
        from = LocalDate.parse(split[0]);
        to = LocalDate.parse(split[1]);
    }

    @Override
    public ArrayList<String> toCommands() {
        ArrayList<String> commands = super.toCommands();
        commands.set(0, String.format("event %s /from %s /to %s", commands.get(0),
                from.format(DATE_SAVE_FORMAT), to.format(DATE_SAVE_FORMAT)));
        return commands;
    }

    /**
     * @return String representation of an event task.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
                from.format(DATE_OUTPUT_FORMAT), to.format(DATE_OUTPUT_FORMAT));
    }
}
