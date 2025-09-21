package thopz;

import java.util.ArrayList;
import java.util.List;

/**
 * Task List manages the various tasks in a arraylist
 * Has the functionalities of add,remove,mark and list
 * Double constructor in case nothing to load
 */

public class TaskList {

    private ArrayList<Task> ls = new ArrayList<>();
    /**
     * Constructs a Tasklist which takes in a list of tasks
     *
     * @param tasks  list of tasks
     *
     */

    public TaskList(List<Task> tasks) {
        ls = new ArrayList<>(tasks);
    }

    public TaskList() {
        ls = new ArrayList<>();
    }

    /**
     * Add tasks into tasklist
     */

    public void addTask(Task task) {
        ls.add(task);
    }

    /**
     * Remove tasks from tasklist
     *
     * @return
     */

    public Task removeTask(int no) {
        return ls.remove(no - 1);
    }




    /**
     * Get specific tasks from tasklist
     * Throw illegal argument if no tasks found
     * Updated find task to find based on matching keywords
     * Partially typed words will also be shown in the list
     */

    public List<String> findTasks(String string) {

        assert string != null : "Keywords should not be null";

        List<String> matched =new ArrayList<>();

        String[] keywords = string.toLowerCase().split("\\s");

        for (int i = 0; i < ls.size(); i++) {
            Task task = ls.get(i);
            String finder = task.toString().toLowerCase();
            boolean isMatch = true;

            for(String keyword : keywords) {
                if(!finder.contains(keyword)) {
                    isMatch = false;
                    break;
                }
            }

            if (isMatch) {
                matched.add(" [" + (i+1) + ". " + task.toString()+"]");
            }
        }

        return matched;
    }


    /**
     * Get all tasks from tasklist
     */

    public List<Task> getAllTasks() {
        return ls;
    }

    /**
     * Get all tasks from tasklist
     */

    public int size() {
        return ls.size();
    }


    public Task getTask(int no) {
        assert no>0 && no<=ls.size() : "Give a valid task number" ;
        return ls.get(no - 1);
    }

}
