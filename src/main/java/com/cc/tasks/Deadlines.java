package com.cc.tasks;

import com.cc.parsers.Parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * deadline task containing field: name and field: date
 */
public class Deadlines extends Task {
    //private String Task; delete suggested by chatgpt4.1, as a part of deletion of unused information
    private LocalDate deadline;

    /**
     * constructor for deadline tasks
     *
     * @param Task name of the task
     * @param deadline string in the format of local date
     */
    public Deadlines(String Task, String deadline) {
        super(Task);
        this.deadline = Parser.parseFlexibleDate(deadline.trim()); //suggested by chatgpt4.1 to handle more time formats
    }

    /**
     * toString method for deadline task
     *
     * @return [task type] + task + date
     */
    @Override
    public String toString() {
        String deadline = this.deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return "[D]" + super.toString() + " ( by " + deadline + " )";
    }
}