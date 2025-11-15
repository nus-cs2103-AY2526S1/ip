package shaniqua.taskcore.tasks;

import shaniqua.ShaniquaException;

public class InvalidTaskDataException extends ShaniquaException {
    InvalidTaskDataException(String msg) {
        super("Invalid task data entry: " + msg);
    }
    InvalidTaskDataException() {
    }
}
