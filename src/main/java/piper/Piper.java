package piper;

import piper.ui.Ui;
import piper.task.TaskList;
import piper.task.Task;

public class Piper {
    public static void main(String[] args) {
        final String CHATBOT_NAME = "Piper";
        Ui ui = new Ui(CHATBOT_NAME);
        TaskList taskList = new TaskList();
        boolean exit = false;

        ui.greetUser();

        while (!exit) { // user is active
            String userInput = ui.read();
            if (userInput.equals("bye")) { // user is inactive
                ui.farewellUser();
                exit = true;
            } else if (userInput.equals("list")) {
                // list tasks
            } else {
                // add new task
            }
        }
        ui.close();
    }
}