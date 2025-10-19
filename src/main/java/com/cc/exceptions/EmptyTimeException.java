package com.cc.exceptions;

/**
 * Exception for missing time argument for deadline, event commands
 */
public class EmptyTimeException extends Exception {
    public EmptyTimeException(){
        super("where got time?");
    }
}
