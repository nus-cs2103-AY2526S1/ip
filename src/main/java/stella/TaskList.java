package stella;

import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> list;
    public TaskList(ArrayList<Task> input) {
        list = input;
    }

    public void printList() {
        if (this.list.isEmpty()) {
            System.out.println("Task list is empty. Add one now: ");
        }
        for (int i = 1; i <= list.size(); i = i + 1) {
            System.out.println(" > " + i + ". " + list.get(i-1));
        }
    }

    public void deleteItem(int index) {
        Task temp = list.remove(index);
        System.out.println("I have removed the following: ");
        System.out.println("" + temp);
        System.out.println(" > Now you have " + list.size() + " task(s) in the list");
        Storage.modifyTaskList(list);
    }

    public void modifyItem(int index, String description) {
        if (description == "unmark") {
            list.get(index).markUndone();
            System.out.println(" > OK, I've marked this task as not done yet: ");
            System.out.println(" > " + list.get(index));
            Storage.modifyTaskList(list);
        }
        if (description == "mark") {
            list.get(index).markDone();
            System.out.println(" > Nice! I've marked this task as done: ");
            System.out.println(" > " + list.get(index));
            Storage.modifyTaskList(list);
        }
    }

    public void addItem(Task task) {
        list.add(task);
        System.out.println(" > added: " + list.get(list.size() - 1));
        System.out.println(" > Now you have " + list.size() + " task(s) in the list");
        Storage.addTask(task);
    }

    public ArrayList<Task> getList() {
        return list;
    }

    /**
     * Search for all the tasks whose description matches the identifier
     * @param identifier A keyword that find tasks whose description matches the identifier
     * @return A TaskList with tasks whose description matches the identifier
     */
    public TaskList findItem(String identifier) {
        TaskList result = new TaskList(new ArrayList<>());
        if (this.list.isEmpty()) {
            return result;
        }
        for (int i = 1; i <= list.size(); i = i + 1) {
            if (list.get(i - 1).getDescription().contains(identifier)) {
                result.list.add(list.get(i - 1));
            }
        }
        return result;
    }




}
