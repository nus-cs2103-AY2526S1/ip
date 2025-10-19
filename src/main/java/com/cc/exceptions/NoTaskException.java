package com.cc.exceptions;

/**
 * Exception for wrong index, thrown by mark/unmark/delete commands
 */
public class NoTaskException extends Exception{
    public NoTaskException(){
        super("no such task boss. Check index or content please?");
    }
}
