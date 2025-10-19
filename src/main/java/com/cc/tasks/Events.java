package com.cc.tasks;

import com.cc.parsers.Parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event class containing field task and field deadline and filed start
 */
public class Events extends com.cc.tasks.Task {
    private String Task;
    private LocalDate deadline;
    private LocalDate start;

    /**
     * Constructor for events
     *
     * @param Tsk String of description of task
     * @param Strt String of start date
     * @param Ddl String of deadline
     */
    public Events(String Tsk, String Strt, String Ddl) {
        super(Tsk);
        this.deadline = Parser.parseFlexibleDate(Ddl.trim());
        this.Task = Tsk;
        this.start = Parser.parseFlexibleDate(Strt.trim());
    }

    @Override
    public String toString() {
        String deadline = this.deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        String start = this.start.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return "[E]" + super.toString() + " ( from " + start + " to " + deadline + ")";
    }
}
