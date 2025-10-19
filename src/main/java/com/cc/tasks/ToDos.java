package com.cc.tasks;

public class ToDos extends Task {
    private String task;

    public ToDos(String task) {
        super(task);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
