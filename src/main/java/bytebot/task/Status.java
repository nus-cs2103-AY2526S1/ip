package bytebot.task;

import bytebot.ByteException;

/**
 * Represents the completion status of a task.
 */
public enum Status {
    DONE("1"),
    NOT_DONE("0");

    private final String value;

    /**
     * Associates a string value used in persistence with the status.
     *
     * @param value String representation ("1" for done, "0" for not done)
     */
    Status(String value) {
        this.value = value;
    }

    /**
     * Parses a string value into a status.
     *
     * @param status String value read from storage
     * @return Corresponding status
     * @throws ByteException If the value is not recognized
     */
    public static Status fromString(String status) throws ByteException {
        for (Status s : Status.values()) {
            if (s.value.equals(status)) {
                return s;
            }
        }
        throw new ByteException("Invalid status: " + status);
    }
}
