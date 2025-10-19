package com.cc.tasks;

/**
 * parent class for todos, events and deadlines. Handles mark/unmark, getter for name and priority
 */
public class Task {
    private boolean isDone;
    private String name;
    private int priority;

    public Task(String name) {       //constructor for Task
        this.isDone = false;
        this.name = name;
        this.priority = 0;
    }

    /**
     * toggles task status to done
     */
    public void MarkAsDone() {
        this.isDone = true;
    }

    /**
     * toggles task status to undone
     */
    public void MarkAsUndone() {
        this.isDone = false;
    }

    /**
     * getter for name field
     *
     * @return String name
     */
    public String getName(){
        return this.name;
    }

    /**
     * adds a priority for the task
     *
     * @param x piority
     */
    public void addPriority(int x){
        this.priority = x;
    }

    @Override
    public String toString() {
        String status = null;
        String msg = null;
        if(this.isDone == true) {
            status = "X";
        } else {
            status = " ";
        }

        if(this.priority == 0){
            return "[" + status + "] " + "" + this.name;
        }
        return "[" + status + "]" + "[P=" + this.priority + "] " + this.name;
    }
}
