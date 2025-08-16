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
                // list tasks
                ui.displayTasks(tasks);
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