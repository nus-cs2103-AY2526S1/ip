package jobe.ui;

import java.util.List;
import java.util.Scanner;

import jobe.task.Task;
import jobe.task.TaskList;

/**
 * Represents a Ui class for displaying messages to user.
 */
public class Ui {
    private Scanner sc;
    private String response;
    
    public Ui() {
        this.sc = new Scanner(System.in);
    }
    
    /**
     * Sets response to welcome greetings for the user upon launching Jobe.
     */
    public void showHelloResponse() {
        this.response = "Hello! I'm Jobe. What can I do for you?";
    }
    
    /**
     * Sets response to confirmation messages for a deadline command.
     *
     * @param newTask New deadline task to be created.
     * @param taskList User's task list.
     */
    public void showDeadlineResponse(Task newTask, TaskList taskList) {
        this.response = "Got it. I've added this task:\n"
                + newTask.toString()
                + "\n"
                + "Now you have " + taskList.size() + " tasks in the list";
    }
    
    /**
     * Sets response to confirmation messages for a delete command.
     *
     * @param deletedTask Task to be deleted.
     * @param taskList User's task list.
     */
    public void showDeleteResponse(Task deletedTask, TaskList taskList) {
        this.response = "Noted. I've removed this task:\n"
                + deletedTask.toString()
                + "\n"
                + "Now you have " + taskList.size() + " tasks in the list";
    }
    
    /**
     * Sets response to confirmation messages for an event command.
     *
     * @param newTask New event task to be created.
     * @param taskList User's task list.
     */
    public void showEventResponse(Task newTask, TaskList taskList) {
        this.response = "Got it. I've added this task:\n"
                + newTask.toString()
                + "\n"
                + "Now you have " + taskList.size() + " tasks in the list";
    }
    
    /**
     * Sets response to confirmation messages for a find command.
     *
     * @param taskList User's task list.
     */
    public void showFindResponse(List<Task> taskList) {
        int count = 1;
        StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");
        for (Task task : taskList) {
            sb.append(count).append(". ").append(task).append("\n");
            count++;
        }
        this.response = sb.toString();
    }
    
    /**
     * Sets response to confirmation messages for a list command.
     *
     * @param taskList User's task list.
     */
    public void showListResponse(TaskList taskList) {
        this.response = taskList.toString();
    }
    
    /**
     * Sets response to confirmation messages for a mark command.
     *
     * @param task Task to be marked.
     */
    public void showMarkResponse(Task task) {
        this.response = "Nice! I've marked this task as done:\n" + task;
    }
    
    /**
     * Sets response to confirmation messages for a to do command.
     * @param newTask New to do task to be created.
     * @param taskList User's task list.
     */
    public void showTodoResponse(Task newTask, TaskList taskList) {
        this.response = "Got it. I've added this task:\n"
                + newTask.toString()
                + "\n"
                + "Now you have " + taskList.size() + " tasks in the list";
    }
    
    /**
     * Sets response to confirmation messages for a unmark command
     *
     * @param task Task to be unmarked.
     */
    public void showUnmarkResponse(Task task) {
        this.response = "OK, I've marked this task as not done yet:\n" + task;
    }
    
    /**
     * Gets response for Jobe to display.
     *
     * @return response.
     */
    public String getResponse() {
        return this.response;
    }
}
