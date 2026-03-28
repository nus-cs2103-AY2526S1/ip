package ackermann.codewords;

import ackermann.exceptions.CheckedException;

/**
 * Superclass for all codewords
 */
public abstract class Codeword {

    /**
     * Returns a string to print
     * @return
     */
    public abstract String execute() throws CheckedException;
}
