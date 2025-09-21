package task;

import java.util.ArrayList;
import java.util.Objects;
import parser.Parser;

public class TaskList {
    public ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void loadTask(String input) throws Exception {
        String[] args = input.split("\\|");
        switch (Parser.parseInput(input)) {
            case CREATE_TODO:
                ToDo td = new ToDo(args[2]);
                if (Objects.equals(args[1], "1")) td.markAsDone();
                tasks.add(td);
                break;
            case CREATE_DEADLINE:
                Deadline dl = new Deadline(args[2], args[3]);
                if (Objects.equals(args[1], "1")) dl.markAsDone();
                tasks.add(dl);
                break;
            case CREATE_EVENT:
                Event ev = new Event(args[2], args[3], args[4]);
                if(Objects.equals(args[1], "1")) ev.markAsDone();
                tasks.add(ev);
                break;
            default:
                throw new Exception("couldn't load task: " + input);
        }
    }

    public void add (Task t) {
        tasks.add(t);
    }

    public int size(){
        return tasks.size();
    }

    public Task get(int i) {
        return tasks.get(i);
    }

    public void remove(int i) {
        tasks.remove(i);
    }

    public TaskList filter(String input) {
        TaskList filtered = new TaskList();
        for (Task t : tasks) {
            if (t.description.matches(".*" + input + ".*")) {
                filtered.add(t);
            }
        }

        return filtered;
    }

    @Override
    public String toString() {
        StringBuilder list = new StringBuilder();
        int index = 0;
        list.append("\n");
        for (Task i : tasks) {
            index++;
            list.append("    ").append(index).append(".").append(i).append("\n");
        }
        return list.toString();
    }
}
