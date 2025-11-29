package kleebot.task;

import java.util.*;
/**
  * Represents a high-level interface for manipulating the tasks contained in a tasklist variable.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public int getSize() {
        return tasks.size();
    }

    public Task getTask(int i) {
        return tasks.get(i);
    }

    /**
     * Adds a task to the class' tasklist.
     *
     * @param task The task to be added to the list.
     */
    public void addToList(Task task) {
        tasks.add(task);
    }

    public void delete(int index) {
        Task task = tasks.get(index - 1);
        tasks.remove(task);
    }


    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void markItem(String[] input) {
        Task task = tasks.get(Integer.parseInt(input[1]) - 1);
        task.markAsDone();
    }

    public void unmarkItem(String[] input) {
        Task task = tasks.get(Integer.parseInt(input[1]) - 1);
        task.unmarkAsDone();
    }

    public void readList() {
        for (int i=0; i<tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.println(i+1 + ". " + task.toString());
        };
    }

//    void handleTodo(String[] input) throws KleeExceptions {
//        if (input.length <= 1) throw new KleeExceptions(Ui.ErrorMessage.MISSING_DETAILS.getMessage());
//        String taskDescription = Arrays.stream(input, 1, input.length)
//                .reduce("", (a, b) -> a + " " + b);
//        addToList(new ToDo(taskDescription));
//    }
//    void handleDeadline(String[] input) throws KleeExceptions {
//        if (input.length <= 1) throw new KleeExceptions(Ui.ErrorMessage.MISSING_DETAILS.getMessage());
//        int byIndex = IntStream.range(0, input.length)
//                .filter(i -> input[i].equals("/by"))
//                .findFirst()
//                .orElse(-1);
//        if (byIndex == -1) throw new KleeExceptions(Ui.ErrorMessage.MISSING_BY.getMessage());
//        if (byIndex == input.length - 1) throw new KleeExceptions(Ui.ErrorMessage.MISSING_BY_2.getMessage());
//        String taskDescription = Arrays.stream(input, 1, byIndex)
//                .reduce("", (a, b) -> a + " " + b)
//                .trim();
//        String by = Arrays.stream(input, byIndex + 1, input.length)
//                .reduce("", (a, b) -> a + " " + b)
//                .trim();
//        String dated_by = Parser.parseDateStr(by); // returns the string format in MMM dd yyyy if the input is a valid date
//
//        addToList(new Deadline(taskDescription, dated_by));
//    }
//
//    void handleEvent(String[] input) throws KleeExceptions{
//        if (input.length <= 1) throw new KleeExceptions(Ui.ErrorMessage.MISSING_DETAILS.getMessage());
//        int fromIndex = IntStream.range(0, input.length)
//                .filter(i -> input[i].equals("/from"))
//                .findFirst()
//                .orElse(-1);
//
//        if (fromIndex == -1) throw new KleeExceptions(Ui.ErrorMessage.MISSING_FROM.getMessage());
//        // test is separated from others bc the toIndex stream uses fromIndex
//
//        int toIndex = IntStream.range(fromIndex, input.length)
//                .filter(i -> input[i].equals("/to"))
//                .findFirst()
//                .orElse(-1);
//
//        if (toIndex == -1) throw new KleeExceptions(Ui.ErrorMessage.MISSING_TO.getMessage());
//        if (fromIndex == toIndex - 1) throw new KleeExceptions("Gimmie more info on when it starts!!");
//        if (toIndex == input.length - 1) throw new KleeExceptions("Gimmie more info on when it ends!!");
//
//        String taskDescription = Arrays.stream(input, 1, fromIndex)
//                .reduce("", (a, b) -> a + " " + b)
//                .trim();
//        String from = Arrays.stream(input, fromIndex + 1, toIndex)
//                .reduce("", (a, b) -> a + " " + b)
//                .trim();
//        String to = Arrays.stream(input, toIndex + 1, input.length)
//                .reduce("", (a, b) -> a + " " + b)
//                .trim();
//        String dated_from = Parser.parseDateStr(from);
//        String dated_to = Parser.parseDateStr(to);
//        addToList(new Event(taskDescription, dated_from, dated_to));
//    }



}
