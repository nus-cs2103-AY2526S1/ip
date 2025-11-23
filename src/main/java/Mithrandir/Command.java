package Mithrandir;

import java.io.IOException;

import Mithrandir.MithrandirExceptions.InvalidArgumentException;
import Mithrandir.MithrandirExceptions.MithrandirException;
import Mithrandir.storage.FileStorage;
import Mithrandir.task.Deadline;
import Mithrandir.task.Event;
import Mithrandir.task.Task;
import Mithrandir.task.Todo;
import Mithrandir.ui.Ui;

public enum Command {
    BYE {
        /**
         * Executes the BYE command to exit the application.
         *
         * @param ui the user interface component
         * @param list the task list (not used in this command)
         * @param input the command input, must be empty
         * @param storage the file storage component (not used in this command)
         * @throws InvalidArgumentException if input is not empty
         */
        @Override
        public String execute(Ui ui, TaskList list, String input, FileStorage storage) throws MithrandirException {
            if (!input.isEmpty()) {
                return "BYE command have no argument!";
            }
            return ui.exit();
        }
    },
    LIST {
        /**
         * Executes the LIST command to display all tasks in the task list.
         *
         * @param ui the user interface component
         * @param list the task list to display
         * @param input the command input, must be empty
         * @param storage the file storage component (not used in this command)
         * @throws InvalidArgumentException if input is not empty
         */
        @Override
        public String execute(Ui ui, TaskList list, String input, FileStorage storage) throws MithrandirException {
            if (!input.isEmpty()) {
                return "LIST command have no argument!";
            }
            if (list.isEmpty()) {
                return ui.print("""
                        There is nothing here, not even the faintest shadow of a task.
                        Your TaskList stands empty, like the halls of Khazad-dum after the dwarves had gone.
                        Add a task, and perhaps light will return to these darkened records.""");
            } else {
                return ui.printList(list);
            }
        }
    },
    MARK {
        /**
         * Executes the MARK command to mark a task as done.
         *
         * @param ui the user interface component
         * @param list the task list containing the task to mark
         * @param input the command input containing the task index (1-based)
         * @param storage the file storage component to save changes
         * @throws InvalidArgumentException if input is empty or not a valid integer
         * @throws IOException if an error occurs while saving to storage
         */
        @Override
        public String execute(Ui ui, TaskList list, String input, FileStorage storage) throws MithrandirException,
                IOException {
            if (input.isEmpty()) {
                return "MARK command need EXACTLY ONE integer as argument!";
            }
            try {
                int index = Integer.parseInt(input.split(" ")[0]) - 1;
                list.Mark(index);
                String result = ui.mark(list.getTask(index));
                storage.store(list.generateFileStrings(), false);
                return result;
            } catch (NumberFormatException e) {
                return "MARK command need one INTEGER as argument!";
            } catch (IndexOutOfBoundsException e) {
                return "The index you have chosen lies beyond the bounds of the TaskList.";
            }
        }
    },
    UNMARK {
        /**
         * Executes the UNMARK command to mark a task as not done.
         *
         * @param ui the user interface component
         * @param list the task list containing the task to unmark
         * @param input the command input containing the task index (1-based)
         * @param storage the file storage component to save changes
         * @throws InvalidArgumentException if input is empty or not a valid integer
         * @throws IOException if an error occurs while saving to storage
         */
        @Override
        public String execute(Ui ui, TaskList list, String input, FileStorage storage) throws MithrandirException,
                IOException {
            if (input.isEmpty()) {
                return "UNMARK command need EXACTLY ONE integer as argument!";
            }
            try {
                int index = Integer.parseInt(input.split(" ")[0]) - 1;
                list.Unmark(index);
                String result = ui.unmark(list.getTask(index));
                storage.store(list.generateFileStrings(), false);
                return result;
            } catch (NumberFormatException e) {
                return "UNMARK command need one INTEGER as argument!";
            } catch (IndexOutOfBoundsException e) {
                return "The index you have chosen lies beyond the bounds of the TaskList.";
            }
        }
    },
    TODO {
        /**
         * Executes the TODO command to create a new Todo task.
         *
         * @param ui the user interface component
         * @param list the task list to add the new task to
         * @param input the command input containing the task description
         * @param storage the file storage component to save changes
         * @throws InvalidArgumentException if input is empty
         * @throws IOException if an error occurs while saving to storage
         */
        @Override
        public String execute(Ui ui, TaskList list, String input, FileStorage storage) throws MithrandirException,
                IOException {
            if (input.isEmpty()) {
                return "TODO command need STRING as Mithrandir.task description!";
            }
            Todo todo = new Todo(input);
            String result = ui.addTodoToList(todo);
            list.addTask(todo);
            storage.store(list.generateFileStrings(), false);
            return result;
        }
    },
    EVENT {
        /**
         * Executes the EVENT command to create a new Event task.
         *
         * @param ui the user interface component
         * @param list the task list to add the new task to
         * @param input the command input containing task description and time information
         * @param storage the file storage component to save changes
         * @throws InvalidArgumentException if input is empty
         * @throws MithrandirException if the event format is invalid
         * @throws IOException if an error occurs while saving to storage
         */
        @Override
        public String execute(Ui ui, TaskList list, String input, FileStorage storage) throws MithrandirException,
                IOException {
            if (input.isEmpty()) {
                return "Event command need 4 parts: task description, '/by', " +
                        "start time of task and end time of task!";
            }
            try {
                Event event = new Event(input);
                String result = ui.addEventToList(event);
                list.addTask(event);
                storage.store(list.generateFileStrings(), false);
                return result;
            } catch (MithrandirException e) {
                return e.getMessage();
            }
        }
    },
    DEADLINE {
        /**
         * Executes the DEADLINE command to create a new Deadline task.
         *
         * @param ui the user interface component
         * @param list the task list to add the new task to
         * @param input the command input containing task description and deadline information
         * @param storage the file storage component to save changes
         * @throws InvalidArgumentException if input is empty
         * @throws MithrandirException if the deadline format is invalid
         * @throws IOException if an error occurs while saving to storage
         */
        @Override
        public String execute(Ui ui, TaskList list, String input, FileStorage storage) throws MithrandirException,
                IOException {
            if (input.isEmpty()) {
                return "Deadline command need 3 parts: task description, '/by' and " +
                        "deadline " +
                        "of the task!";
            }
            try {
                Deadline deadline = new Deadline(input);
                String result = ui.addDeadlineToList(deadline);
                list.addTask(deadline);
                storage.store(list.generateFileStrings(), false);
                return result;
            } catch (MithrandirException e) {
                return e.getMessage();
            }
        }
    },
    DELETE {
        /**
         * Executes the DELETE command to remove a task from the task list.
         *
         * @param ui the user interface component
         * @param list the task list to remove the task from
         * @param input the command input containing the task index (1-based)
         * @param storage the file storage component to save changes
         * @throws InvalidArgumentException if input is empty, not a valid integer, or index is out of bounds
         * @throws IOException if an error occurs while saving to storage
         */
        @Override
        public String execute(Ui ui, TaskList list, String input, FileStorage storage) throws MithrandirException,
                IOException {
            if (input.isEmpty()) {
                return "DELETE command need EXACTLY ONE integer as argument!";
            }
            try {
                int index = Integer.parseInt(input.split(" ")[0]) - 1;
                String result = ui.delete(list.DeleteTask(index));
                storage.store(list.generateFileStrings(), false);
                return result;
            } catch (NumberFormatException e) {
                return "DELETE command need one INTEGER as argument!";
            } catch (IndexOutOfBoundsException e) {
                return "Index to be deleted is out of bounds of the todo list!";
            }
        }
    },
    FIND {
        @Override
        public String execute(Ui ui, TaskList list, String input, FileStorage storage) throws MithrandirException {
            if (input.isEmpty()) {
                return "FIND command need STRING as argument!";
            }
            TaskList foundTasks = list.findTasks(input);
            return ui.printFoundTasks(foundTasks);
        }
    },
    ARCHIVE {
        @Override
        public String execute(Ui ui, TaskList list, String input, FileStorage storage) throws MithrandirException, IOException {
            if (input.isEmpty()) {
                return "ARCHIVE command need EXACTLY ONE integer as argument!";
            }
            try {
                int index = Integer.parseInt(input.split(" ")[0]) - 1;
                Task archivedTask = list.getTask(index);
                ui.delete(list.DeleteTask(index));
                String result = ui.archive(archivedTask);
                storage.store(list.generateFileStrings(), false);
                storage.store(archivedTask.toFileString(), true);
                return result;
            } catch (NumberFormatException e) {
                return "ARCHIVE command need one INTEGER as argument!";
            } catch (IndexOutOfBoundsException e) {
                return "Index to be archived is out of bounds of the todo list!";
            }
        }
    };

    abstract String execute(Ui ui, TaskList list, String input, FileStorage storage) throws MithrandirException,
            IOException;
}