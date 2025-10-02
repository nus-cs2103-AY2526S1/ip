//package com.neokortex.exceptions;
//
//import com.neokortex.task.TaskType;
//
//public class WrongTaskTypeException extends RuntimeException {
//    public WrongTaskTypeException(String message) {
//        super(message);
//    }
//
//    public WrongTaskTypeException(TaskType wrongTaskType, TaskType correctTaskType) {
//        super(String.format("Tried to create a %s Task from %s arguments", correctTaskType, wrongTaskType));
//    }
//}
