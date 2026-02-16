package jooh;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import jooh.exception.JoohException;
import jooh.parser.Parser;
import jooh.storage.Storage;
import jooh.task.*;
import jooh.ui.Ui;

// Code follows SE-EDU Java coding standards, checked for code quality violations

/**
 * Entry point for the Jooh chatbot application.
 * Initializes the user interface, storage, and task list,
 * then runs an interactive loop to process user commands until exit.
 */
public class Jooh {

    /**
     * Launches the Jooh chatbot.
     * Sets up storage, loads previously saved tasks, and enters
     * a command-processing loop that responds to user input.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskList taskList = new TaskList();
        Storage storage = new Storage();
        Ui ui = new Ui();
        ui.welcomeMsg();

        try {
            List<Task> loaded = storage.load();
            loaded.forEach(taskList::addTask);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        while (true) {
            String raw = sc.nextLine();
            try {
                Parser.Parsed p = Parser.Parsed.parse(raw);
                System.out.println("DEBUG Parsed type = " + p.type);
                assert p != null : "Parsed command must not be null";
                switch (p.type) {
                    case BYE:
                        ui.goodbyeMsg();
                        sc.close();
                        return;

                    case LIST:
                        ui.listTasksMsg(taskList.getTaskList());
                        break;

                    case TODO:
                        assert p.desc != null && !p.desc.isEmpty() : "Todo description must not be empty";
                        Task t1 = new Todo(p.desc, false);
                        taskList.addTask(t1);
                        ui.addTaskMsg(t1, taskList.getSize());
                        break;

                    case DEADLINE:
                        assert p.desc != null && !p.desc.isEmpty() : "Deadline description must not be empty";
                        assert p.by != null && !p.by.isEmpty() : "Deadline 'by' must not be empty";
                        Task t2 = new Deadline(p.desc, p.by, false);
                        taskList.addTask(t2);
                        ui.addTaskMsg(t2, taskList.getSize());
                        break;

                    case EVENT:
                        assert p.desc != null && !p.desc.isEmpty() : "Event description must not be empty";
                        assert p.from != null && !p.from.isEmpty() : "Event 'from' must not be empty";
                        assert p.to != null && !p.to.isEmpty() : "Event 'to' must not be empty";
                        Task t3 = new Event(p.desc, p.from, p.to, false);
                        taskList.addTask(t3);
                        ui.addTaskMsg(t3, taskList.getSize());
                        break;

                    case MARK: {
                        int n = p.index;
                        assert n > 0 : "Task index must be positive";
                        if (n > taskList.getSize()) {
                            throw new JoohException("Task doesn't exist...");
                        }
                        Task t4 = taskList.getTask(n - 1);
                        assert t4 != null : "Task at index must exist";
                        taskList.markTaskDone(n - 1);
                        ui.taskMarkedDoneMsg(t4);
                        break;
                    }

                    case UNMARK: {
                        int n = p.index;
                        if (n > taskList.getSize()) {
                            throw new JoohException("Task doesn't exist...");
                        }
                        Task t5 = taskList.getTask(n - 1);
                        taskList.markTaskUndone(n - 1);
                        ui.taskMarkedUndoneMsg(t5);
                        break;
                    }

                    case DELETE: {
                        int n = p.index;
                        if (n > taskList.getSize()) {
                            throw new JoohException("Task doesn't exist...");
                        }
                        String rmv = taskList.getTaskAsString(n - 1);
                        taskList.removeTask(n - 1);
                        ui.taskRemovedMsg(rmv);
                        break;
                    }

                    case FIND:
                        List<Task> matches = taskList.findTasks(p.desc);
                        ui.findTasksMsg(matches);
                        break;

                    case FIXED: {
                        assert p.desc != null && !p.desc.isEmpty() : "Fixed task description must not be empty";
                        assert p.duration != null && !p.duration.isEmpty() : "Fixed task duration must not be empty";
                        Task t = new Fixed(p.desc, false, p.duration);
                        taskList.addTask(t);
                        ui.addTaskMsg(t, taskList.getSize());
                        break;
                    }

                    default:
                        assert false : "Unexpected command type reached: " + p.type;
                        throw new JoohException("Unknown command type: " + p.type);
                }
            } catch (JoohException e) {
                System.out.println(e.getMessage());
            }
            try {
                storage.save(taskList.getTaskList());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}


