package cat;

import java.util.ArrayList;
import java.util.List;

public class Meow {

    public static ArrayList<Task> tasks = new ArrayList<>();

    public static String printAll() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1)
                    .append(". ")
                    .append(tasks.get(i).getStatus())
                    .append("\n");
        }
        return sb.toString();
    }

    public static String find(String keyword) {
        StringBuilder sb = new StringBuilder();

        List<String> matchedTasks = tasks.stream()
                .filter(task -> task.name.contains(keyword))
                .map(Task::getStatus)
                .toList(); // Java 16+; use collect(Collectors.toList()) if older Java

        for (int i = 0; i < matchedTasks.size(); i++) {
            sb.append(i + 1).append(". ").append(matchedTasks.get(i)).append("\n");
        }

        if (matchedTasks.isEmpty()){
            return "Tasks with " + keyword + " does not exist.\n";
        }else{
            return sb.toString();
        }
    }


    public static String main(String userInput) {
        String output = "";
        Dataloader dataloader = new Dataloader();  // Create a dataloader object
        TaskSaver tasksaver = new TaskSaver();
        tasks = dataloader.getTasks();
        output += Parser.analyse(userInput);
        tasksaver.save(tasks);
        //output += Ui.showAsk();
        return output;
    }




}
