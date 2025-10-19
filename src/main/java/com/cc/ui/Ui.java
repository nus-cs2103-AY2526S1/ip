package com.cc.ui;

import com.cc.exceptions.EmptyTimeException;
import com.cc.exceptions.NoTaskException;
import com.cc.exceptions.WrongHeadingException;
import com.cc.parsers.Parser;
import com.cc.TaskList;
import com.cc.tasks.Deadlines;
import com.cc.tasks.Events;
import com.cc.tasks.Task;
import com.cc.tasks.ToDos;
import com.cc.Storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.System;
import java.util.Scanner;

/**
 * Ui class handles the interaction with users, and executes the actual tasks
 */
public class Ui {
    private static final String ChatBotName = "com/cc";
    private static final String stop = "bye";
    private static final String[] commands = {"todo", "event", "deadline", "mark", "unmark", "list"};
    private static final String FILE_PATH = "data" + File.separator + "duke.txt";

    /**
     * abstraction for opening greeting, for CLI mode
     */
    public void start() {     //print greetings
        System.out.println("Hello from " + ChatBotName);
        System.out.println("What can I do for you?");
    }

    /**
     * abstraction for opening greeting, for fxml mode
     * @return String of opening greetings
     */
    public String startFxml() {     //print greetings
        return "Hello from " + ChatBotName + "\n" + "What can I do for you?";
    }

    /**
     * abstraction for printing the messages of adding task in CLI mode
     *
     * @param task task to be added
     * @param tasks tasklist to get number of tasks in the list
     */
    public void printAddMessage(Task task, TaskList tasks){
        System.out.println("added: " + task.toString() + "\n"
                + tasks.getSize() + " tasks now");
    }

    /**
     * loop for taking action in the CLI mode
     *
     * @throws EmptyTimeException
     * @throws WrongHeadingException
     * @throws NoTaskException
     */
    public void waitForCommand() throws EmptyTimeException, WrongHeadingException, NoTaskException {       //loop that waits for command
        Scanner scanner = new Scanner(System.in);
        String temp = null;
        TaskList tasks = new TaskList();
        Parser parser = new Parser();

        while (true) {
            temp = scanner.nextLine().trim();
            int actionCode = parser.getAction(temp);
            if (actionCode == -1) {
                throw new WrongHeadingException();
            }

            if (actionCode == 1 || actionCode == 2 || actionCode == 3) {
                String[] details = parser.handleTaskAction(actionCode, temp);

                if (actionCode == 1) {
                    ToDos todo = new ToDos(details[0]);
                    tasks.addTask(todo);
                    printAddMessage(todo, tasks);
                    Storage.saveTaskToFile(todo);
                    temp = null;

                } else if (actionCode == 2) {
                    Deadlines deadline = new Deadlines(details[0], details[1]);
                    tasks.addTask(deadline);
                    printAddMessage(deadline, tasks);
                    Storage.saveTaskToFile(deadline);
                    temp = null;

                } else if (actionCode == 3) {
                    Events event = new Events(details[0], details[1], details[2]);
                    tasks.addTask(event);
                    printAddMessage(event, tasks);
                    Storage.saveTaskToFile(event);
                    temp = null;
                }

            } else if (actionCode == 4) {
                System.out.println(tasks.toString());

            } else if (actionCode == 5) {
                int index = parser.handleMarkAndDelete(5, temp);
                tasks.MarkAsDone(index - 1);
                System.out.println(makeMarkMessage(index - 1, tasks));

            } else if (actionCode == 6) {
                int index = parser.handleMarkAndDelete(6, temp);
                tasks.MarkAsUndone(index - 1);
                System.out.println(makeUnmarkMessage(index - 1, tasks));

            } else if (actionCode == 7) {
                int index = parser.handleMarkAndDelete(7, temp);
                String deletedTask = tasks.getTask(index - 1).toString();
                tasks.deleteTask(index - 1);
                System.out.println(makeDeleteMessage(tasks.getSize(), deletedTask));

            } else if (actionCode == 8) {
                String name = parser.handleFind(8, temp);
                Task[] finds = tasks.findWord(name);
                for (int i = 0; i < finds.length; i++) {
                    System.out.println(finds[i].toString());
                }
            } else if (actionCode == 10) {
                String[] index = parser.handlePriority(temp);
                int taskIndex = Integer.parseInt(index[0]);
                int priority = Integer.parseInt(index[1]);
                if(taskIndex > tasks.getSize()){
                    throw new NoTaskException();
                }
                tasks.addPriority(taskIndex, priority);
                System.out.println(tasks.getTask(taskIndex).toString());
            } else if (actionCode == 9){
                System.out.println("Don't come back again");
                System.exit(0);
            } else {
                throw new WrongHeadingException();
            }
        }

    }

    /**
     * add message for fxml mode
     *
     * @param task being added
     * @param tasks tasklist to get remaining numbers
     * @return a string of add message
     */
    public String makeAddMessage(Task task, TaskList tasks){
        return "added: " + task.toString() + "\n"
                + tasks.getSize() + " task now";
    }

    /**
     * mark message for fxml mode
     *
     * @param index position of task in tasklist
     * @param tasks tasklist to get remaining numbers
     * @return a string of add message
     */
    public String makeMarkMessage(int index, TaskList tasks){
        return "Nice! You got it boss: \n" + tasks.getTask(index - 1).toString();
    }

    /**
     * unmark message for fxml mode
     *
     * @param index position of task in tasklist
     * @param tasks tasklist to get remaining numbers
     * @return a string of add message
     */
    public String makeUnmarkMessage(int index, TaskList tasks){
        return "Fine get it done soon: \n" + tasks.getTask(index - 1).toString();
    }

    /**
     * delete message for fxml mode
     *
     * @param size size of task in tasklist
     * @param deletedTask String of deleted task
     * @return a string of add message
     */
    public String makeDeleteMessage(int size, String deletedTask){
        return "magic magic it's gone: \n"
                + deletedTask + "\n"
                + size + " tasks left";
    }

    /**
     * loop for fxml mode dialogue box
     *
     * @param input String of user input
     * @param tasks TaskList that users operate on
     * @param parser parser for extracting information from commands
     * @return String of response from CC bot that is to be printed in the dialogue box
     * @throws EmptyTimeException
     * @throws WrongHeadingException
     * @throws NoTaskException
     */
    public String waitForCommandFxml(String input, TaskList tasks, Parser parser) throws EmptyTimeException, WrongHeadingException, NoTaskException{       //loop that waits for command
        String temp = input;

        int actionCode = parser.getAction(temp);
        if (actionCode == -1) {
            throw new WrongHeadingException();
        }

        if (actionCode == 1 || actionCode == 2 || actionCode == 3) {
            String[] details = parser.handleTaskAction(actionCode, temp);
            if (actionCode == 1) {
                ToDos todo = new ToDos(details[0]);
                tasks.addTask(todo);
                Storage.saveTaskToFile(todo);
                return makeAddMessage(todo, tasks);


            } else if (actionCode == 2) {
                Deadlines deadline = new Deadlines(details[0], details[1]);
                tasks.addTask(deadline);
                Storage.saveTaskToFile(deadline);
                return makeAddMessage(deadline, tasks);


            } else if (actionCode == 3) {
                Events event = new Events(details[0], details[1], details[2]);
                tasks.addTask(event);
                Storage.saveTaskToFile(event);
                return makeAddMessage(event, tasks);
            }

        } else if (actionCode == 4) {
            return tasks.toString();

        } else if (actionCode == 5) {
            int index = parser.handleMarkAndDelete(5, temp);
            tasks.MarkAsDone(index - 1);
            return makeMarkMessage(index, tasks);

        } else if (actionCode == 6) {
            int index = parser.handleMarkAndDelete(6, temp);
            tasks.MarkAsUndone(index - 1);
            return makeUnmarkMessage(index, tasks);

        } else if (actionCode == 7) {
            int index = parser.handleMarkAndDelete(7, temp);
            String deletedTask = tasks.getTask(index - 1).toString();
            tasks.deleteTask(index - 1);
            int newSize = tasks.getSize();

            return makeDeleteMessage(newSize, deletedTask);

        } else if (actionCode == 8) {       //suggested by GPT4.1
            String name = parser.handleFind(8, temp);
            int[] indices = tasks.findWordIndices(name);
            StringBuilder result = new StringBuilder();
            for (int idx : indices) {
                result.append(idx+1).append(". ").append(tasks.getTask(idx).toString()).append("\n");
            }
            return result.toString();

        } else if (actionCode == 10) {
            String[] index = parser.handlePriority(temp);
            int taskIndex = Integer.parseInt(index[0]) - 1;
            int priority = Integer.parseInt(index[1]);
            if(taskIndex > tasks.getSize()){
                throw new NoTaskException();
            }
            tasks.addPriority(taskIndex, priority);
            return tasks.getTask(taskIndex).toString();

        } else if ( actionCode == 9) {
            return "Don't come back again";
        }
        return "Don't come back again";
        }
    }
