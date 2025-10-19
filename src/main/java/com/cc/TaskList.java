package com.cc;

import com.cc.tasks.Task;

import java.util.ArrayList;

/**
 * Collection of tasks, handles collective logics involving tasks
 */
public class TaskList {
    private static int counter = 0;
    private static ArrayList<Task> tasks;

    /**
     * constructor, initialises new tasklist
     */
    public TaskList() {
        tasks =new ArrayList<>(100);
    }

    /**
     * adds a task to the current tasklist
     * @param task
     */
    public void addTask(Task task) {     //adds a task into a tasklist
        tasks.add(task);
        counter += 1;

    }

    /**
     * toggles task on index to "done" state
     * @param index index of the task to be marked
     */
    public void MarkAsDone(int index) {  //asks task to mark done
        tasks.get(index).MarkAsDone();
    }

    /**
     * toggles task on index to "undone" state
     * @param index index of the task to be unmarked
     */
    public void MarkAsUndone(int index) {    //asks task to mark undone
        tasks.get(index).MarkAsUndone();
    }

    /**
     * returns the number of tasks in the current tasklist
     * @return
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * get task at the specific index
     * @param index index of the task
     * @return task item on the index
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * delete task at the index
     * @param index
     */
    public void deleteTask(int index) {
        tasks.remove(index);
    }

    /**
     * add priority to a task item
     * @param index
     * @param priority
     */
    public void addPriority(int index, int priority){
        tasks.get(index).addPriority(priority);
    }

    /**
     * find all tasks whose description has the word
     * @param s key word to be searched
     * @return array of tasks that has the word
     */
    public Task[] findWord(String s) {
        return tasks.stream()
                .filter(x->x.getName().contains(s))
                .toArray(n -> new Task[n]);
    }

    /**
     * builds a new array that are the index of tasks which have s in their descriptions.
     * @param s
     * @return array of index of the fitting tasks
     */
    public int[] findWordIndices(String s){     //suggested by gpt4.1
        return java.util.stream.IntStream.range(0, tasks.size())
                .filter(i -> tasks.get(i).getName().contains(s))
                .toArray();
    }

    @Override
    public String toString() {
        String temp = "";
        for(int i=0; i<tasks.size(); i++) {
            temp += i + 1 + ". " + tasks.get(i).toString() + "\n";
        }
        return temp;
    }
}
