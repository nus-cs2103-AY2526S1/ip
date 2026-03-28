package com.arnavjhajharia.penguin.common.exceptions;

/**
 * Exception thrown when a user provides an invalid task index.
 * <p>
 * Typical causes:
 * <ul>
 *   <li>The index is out of bounds (negative or greater than the last task).</li>
 *   <li>The input cannot be interpreted as a valid task index.</li>
 * </ul>
 * <p>
 * The error message is constructed to be user-friendly, showing either
 * the raw invalid input string or the invalid one-based index.
 *
 * @since 1.0
 */
public final class InvalidIndexException extends PenguinException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs an exception with a message referring to the raw string input
     * that failed to parse as a valid index.
     *
     * @param raw the raw user input string representing an index
     */
    public InvalidIndexException(String raw) {
        super("Invalid task index: \"" + String.valueOf(raw) + "\".");
    }

    /**
     * Constructs an exception with a message referring to an out-of-range
     * integer index. The displayed index is adjusted to one-based numbering
     * (to match user-facing conventions).
     *
     * @param idx the zero-based index that is invalid
     */
    public InvalidIndexException(int idx) {
        super("Invalid task index: " + (idx + 1) + ".");
    }
}
