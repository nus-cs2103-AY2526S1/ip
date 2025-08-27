package bytebot.task;

import bytebot.ByteException;

/**
 * Represents the completion status of a task.
 */
public enum Status {
    DONE("1"),
    NOT_DONE("0");

    private final String value;

    Status(String value) {
        this.value = value;
    }

    public static Status fromString(String status) throws ByteException {
        for (Status s : Status.values()) {
            if (s.value.equals(status)) {
                return s;
            }
        }
        throw new ByteException("Invalid status: " + status);
    }
}
