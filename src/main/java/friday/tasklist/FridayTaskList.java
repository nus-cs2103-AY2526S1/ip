package friday.tasklist;

import friday.tasks.Task;
import friday.tasks.ToDos;
import friday.tasks.Deadlines;
import friday.tasks.Events;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class FridayTaskList {
    private ArrayList<Task> list = new ArrayList<>();
    private int numberOfTasks = 0;

    public FridayTaskList(ArrayList<Task> taskList) {
        this.list = taskList;
        numberOfTasks = taskList.size();
    }

    public FridayTaskList(){}

    /**
     * Marks the task corresponding to the int position, given as argument,
     * on the list as isDone.
     * @param taskNo is the task number on the list to be marked.
     */
    public void markTaskAsDone(int taskNo) {
        Task currTask = list.get(taskNo - 1);
        currTask.markTaskAsDone();
    }

    /**
     * Unmarks the task corresponding to the int position, given as argument,
     * on the list as isDone.
     * @param taskNo is the task number on the list to be unmarked.
     */
    public void markTaskAsUndone(int taskNo) {
        Task currTask = list.get(taskNo - 1);
        currTask.markTaskAsUndone();
    }

    public void deleteTask(int taskNo) {
        Task currTask = list.get(taskNo - 1);
        list.remove(taskNo - 1);
        numberOfTasks--;
    }

    public void addTodoTask(String description, String tag) {
        ToDos td = new ToDos(description, tag);
        list.add(td);
        numberOfTasks++;
    }

    public void addDeadlineTask(String description, String deadline, String tag) {
        Deadlines dl = new Deadlines(description, deadline, tag);
        list.add(dl);
        numberOfTasks++;
    }

    public void addEventTask(String description, String from, String to, String tag) {
        Events event = new Events(description, from, to, tag);
        list.add(event);
        numberOfTasks++;
    }

    public int getNumberOfTasks() {
        return numberOfTasks;
    }
    public ArrayList<Task> getList() {
        return list;
    }
    
    public void printList() {
        for (int i = 0; i < list.size(); i++) {
            Task currTask = list.get(i);
            System.out.println((i + 1) + "." + currTask.taskAsString());
        }
    }

    public Task getTask(int taskNo) {
        return list.get(taskNo - 1);
    }

    /**
     * Finds the task in the list that have matching keyword in the description.
     * @param keyword is the keyword the user want to use to search.
     * @return the ArrayList of String with the tasks that have the keyword.
     */
    public ArrayList<String> findTasks(String keyword) {
        ArrayList<String> results = new ArrayList<>();
        int index = 1;
        for (Task t : list) {
            if (t.getDescription().contains(keyword) && !keyword.isEmpty()) {
                results.add(index + "." + t.taskAsString());
                index++;
            }
        }
        return results;
    }

    public String listAsString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append((i + 1) + ". " + list.get(i).taskAsString() + "\n");
        }
        return sb.toString().trim();
    }



}
