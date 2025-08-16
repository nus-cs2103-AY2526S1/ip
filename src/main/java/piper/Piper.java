package piper;

import piper.ui.Ui;
import piper.task.TaskList;
import piper.task.Task;

public class Piper {
    public static void main(String[] args) {
        final String CHATBOT_NAME = "Piper";
        Ui ui = new Ui(CHATBOT_NAME);
        TaskList tasks = new TaskList();
        boolean exit = false;

        ui.greetUser();

        while (!exit) { // user is active
            String userInput = ui.read();
            if (userInput.equals("bye")) { // user is inactive
                ui.farewellUser();
                exit = true;
            } else if (userInput.equals("list")) {
                // list all tasks
                ui.displayTasks(tasks);
            } else if (userInput.startsWith("mark ")) {
                // mark task as done
                String numberSubstring = userInput.substring(5); // get index of task to mark as done
                int taskNumber = Integer.parseInt(numberSubstring);
                int index = taskNumber - 1; // task list starts from index 1 but array list starts from index 0

                Task task = tasks.getTask(index);
                task.markDone();
                ui.showTaskStatus(task);
            } else {
                // add new task
                Task task = new Task(userInput);
                tasks.addTask(task);
                ui.showAddedTask(task);
            }
        }
        ui.close();
    }
}