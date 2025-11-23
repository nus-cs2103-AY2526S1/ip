package Mithrandir.task;

import Mithrandir.MithrandirExceptions.InvalidArgumentException;
import Mithrandir.MithrandirExceptions.MithrandirException;

public enum Tasks {
    TODO {
        /**
         * Creates a new Todo task with the given completion status and description.
         * If the completion status is "done", the task is marked as done.
         * Otherwise, the task is marked as undone.
         *
         * @param completionStatus A string representing the completion status of the task.
         *                          Must be "done" or "undone".
         * @param description       The description of the task.
         * @return                  The newly created Todo task.
         * @throws IllegalArgumentException If the completion status is neither "done" nor "undone".
         */
        @Override
        public Task createTask(String completionStatus, String description) {
            Todo newTask = new Todo(description);
            if (completionStatus.equals("done")) {
                newTask.markDone();
            } else if (completionStatus.equals("undone")) {
                newTask.markUndone();
            } else {
                throw new IllegalArgumentException("Invalid completion status: " + completionStatus);
            }
            return newTask;
        }
    },
    EVENT {
        /**
         * Creates a new Event task with the given completion status and description.
         * If the completion status is "done", the task is marked as done.
         * Otherwise, the task is marked as undone.
         *
         * @param completionStatus A string representing the completion status of the task.
         *                          Must be "done" or "undone".
         * @param description       The description of the task.
         * @return                  The newly created Event task.
         * @throws IllegalArgumentException If the completion status is neither "done" nor "undone".
         */
        @Override
        public Task createTask(String completionStatus, String description) throws MithrandirException {
            Event newTask = new Event(description);
            if (completionStatus.equals("done")) {
                newTask.markDone();
            } else if (completionStatus.equals("undone")) {
                newTask.markUndone();
            } else {
                throw new IllegalArgumentException("Invalid completion status: " + completionStatus);
            }
            return newTask;
        }
    },
    DEADLINE {
        /**
         * Creates a new Deadline task with the given completion status and description.
         * If the completion status is "done", the task is marked as done.
         * Otherwise, the task is marked as undone.
         *
         * @param completionStatus A string representing the completion status of the task.
         *                          Must be "done" or "undone".
         * @param description       The description of the task.
         * @return                  The newly created Deadline task.
         * @throws IllegalArgumentException If the completion status is neither "done" nor "undone".
         */
        @Override
        public Task createTask(String completionStatus, String description) throws InvalidArgumentException {
            Deadline newTask = new Deadline(description);
            if (completionStatus.equals("done")) {
                newTask.markDone();
            } else if (completionStatus.equals("undone")) {
                newTask.markUndone();
            } else {
                throw new IllegalArgumentException("Invalid completion status: " + completionStatus);
            }
            return newTask;
        }
    };

    public abstract Task createTask(String completionStatus, String description) throws Exception;
}
