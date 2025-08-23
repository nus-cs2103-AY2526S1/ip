package BobbyWasabi;

import BobbyWasabi.Exceptions.BobbyWasabiException;
import BobbyWasabi.Parser.Parser;
import BobbyWasabi.Storage.Storage;
import BobbyWasabi.Tasks.Task;
import BobbyWasabi.Tasks.Deadline;
import BobbyWasabi.Tasks.Event;
import BobbyWasabi.Tasks.ToDo;
import BobbyWasabi.Tasks.TaskList;
import BobbyWasabi.UI.UI;



import java.util.ArrayList;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;


public class BobbyWasabi {

    public enum Command {
        LIST,
        BYE,
        MARK,
        UNMARK,
        DELETE,
        TODO,
        DEADLINE,
        EVENT,
        OTHERS;

        public static Command toCommand(String input) {
            try {
                return Command.valueOf(input.toUpperCase());
            } catch (IllegalArgumentException e) {
                return Command.OTHERS;
            }
        }
    }

    private TaskList taskList;
    private Storage storage;
    private UI ui;

    public BobbyWasabi() {
        this.ui = new UI();
        this.storage = new Storage("./data/BobbyWasabiTasks.txt", "./data");

        try {
            this.storage.createDataStorage();
            this.taskList = new TaskList(storage.load());
        } catch (BobbyWasabiException e) {
            ui.generateErrorMsg(e.getMessage());
            this.taskList = new TaskList(new ArrayList<Task>());
        }
    }

    public void run() {
        this.ui.greetUser();

        while (true) {

            // Get user input and command
            String userInput = ui.getNextInput();
            Command command = Parser.parseCommand(userInput);

            switch (command) {
                case BYE:

                    ui.farewellUser();
                    return;

                case LIST:

                    ui.listMessage(this.taskList);
                    continue;

                case MARK:
                    try {

                        int indx = Parser.parseCommandIndex(userInput, this.taskList.size());
                        Task targetTask = this.taskList.get(indx - 1);
                        targetTask.setIsMarked(true);

                        ui.markTaskMessage(indx, targetTask);
                        storage.updateDataFileFromTasks(this.taskList);
                        continue;

                    } catch (BobbyWasabiException e) {
                        ui.generateErrorMsg(e.getMessage());
                        continue;
                    }
                case UNMARK:
                    try {

                        int indx = Parser.parseCommandIndex(userInput, this.taskList.size());
                        Task targetTask = this.taskList.get(indx - 1);
                        targetTask.setIsMarked(false);

                        ui.unmarkTaskMessage(indx, targetTask);
                        storage.updateDataFileFromTasks(this.taskList);
                        continue;

                    } catch (BobbyWasabiException e) {
                        ui.generateErrorMsg(e.getMessage());
                        continue;
                    }
                case TODO:
                    try {

                        String description = Parser.parseTodo(userInput);

                        Task todo = new ToDo(description, false);
                        this.taskList.add(todo);

                        ui.addTaskMessage(todo, this.taskList.size());
                        storage.fileWrite(todo.getData());
                        continue;

                    } catch (BobbyWasabiException e) {
                        ui.generateErrorMsg(e.getMessage());
                        continue;
                    }
                case DEADLINE:
                    try {
                        String[] details = Parser.parseDeadline(userInput);
                        String description = details[0];
                        String deadline = details[1];

                        LocalDateTime dateTime = Parser.parseDateString(deadline);

                        Task deadlineTask = new Deadline(description, false, dateTime);
                        this.taskList.add(deadlineTask);

                        ui.addTaskMessage(deadlineTask, this.taskList.size());
                        storage.fileWrite(deadlineTask.getData());
                        continue;

                    } catch (BobbyWasabiException | DateTimeParseException e) {
                        ui.generateErrorMsg(e.getMessage());
                        continue;
                    }

                case EVENT:
                    try {
                        String[] details = Parser.parseEvent(userInput);
                        String description = details[0];
                        String start = details[1];
                        String end = details[2];

                        Task eventTask = new Event(description, false, start, end);
                        this.taskList.add(eventTask);

                        ui.addTaskMessage(eventTask, this.taskList.size());
                        storage.fileWrite(eventTask.getData());
                        continue;

                    } catch (BobbyWasabiException e) {
                        ui.generateErrorMsg(e.getMessage());
                        continue;
                    }
                case DELETE:
                    try {
                        int indx = Parser.parseCommandIndex(userInput, this.taskList.size());
                        Task targetTask = this.taskList.get(indx - 1);
                        this.taskList.remove(indx - 1);

                        ui.deleteMessage(targetTask, this.taskList.size());
                        storage.updateDataFileFromTasks(this.taskList);
                        continue;

                    } catch (BobbyWasabiException e) {
                        ui.generateErrorMsg(e.getMessage());
                        continue;
                    }
                case OTHERS:
                    ui.invalidMessage();
            }


        }
    }



    public static void main(String[] args) {
        new BobbyWasabi().run();
    }
}
