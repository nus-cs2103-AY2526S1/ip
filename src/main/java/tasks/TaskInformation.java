package tasks;

import exceptions.InvalidInputException;
import exceptions.NoEndException;
import exceptions.NoStartException;
import exceptions.NoTaskException;
import time.Time;

/**
 * Represents a set of information for a task
 */
public class TaskInformation {
    private String[] parts;
    private int partsLength;
    private String description;
    private String startString;
    private String endString;
    private Time startTime;
    private Time endTime;
    private String type;

    /**
     * Constructor of TaskInformation
     *
     * @param text string description of the whole command
     * @param type Type of the
     */
    public TaskInformation(String text, String type) {
        this.type = type;

        //split text to:
        //[0]: command + description
        //[1]: start time if any
        //[2]: end time if any
        this.parts = text.split("/");
        this.partsLength = this.parts.length;

        //deadline: should be 2: command & description + end time
        //event: should be 3: command & description + start time + end time
        if ((type.equals("deadline") && partsLength != 2) ||
                (type.equals("event") && partsLength != 3)) {
            throw new InvalidInputException();
        }

        //initialise
        this.description = readDescription();
        this.startString = readStartString();
        this.endString = readEndString();
        this.endTime = readEndTime();
        this.startTime = readStartTime();
    }

    /**
     * Returns the description from task
     */
    public String readDescription() {
        String toRead = this.parts[0];
        String toReturn;
        //Used chatGPT to search and understand the indexOf function
        if (this.type.equals("todo")) {
            //todo has 4 letters hence description
            //starts from index 4
            toReturn = toRead.substring(4);
        } else if (this.type.equals("deadline")) {
            //deadline has 8 letters
            toReturn = toRead.substring(8);
        } else if (this.type.equals("event")) {
            //event has 5 letters
            toReturn = toRead.substring(5);
        } else {
            //should not reach here
            toReturn = "";
        }

        if (toReturn.isBlank()) {
            throw new NoTaskException();
        }
        return toReturn.trim();
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the start time of task in String
     */
    public String readStartString() {
        if (type.equals("event")) {
            String start = this.parts[1];
            //since date is only in numbers and '-', remove the alphabets
            //chatGPT to help with regex
            String date = start.replaceAll("[a-zA-Z]", "").trim();
            if (date.isBlank()) {
                throw new NoStartException();
            }
            return date;
        } else {
            return null;
        }
    }

    /**
     * Returns the start time of task in Time
     */
    public Time readStartTime() {
        if (this.startString == null) {
            return null;
        }
        return new Time(this.startString);

    }

    public Time getStartTime() {
        return this.startTime;
    }

    /**
     * Return end time of task in String
     */
    public String readEndString() {
        String end;
        if (type.equals("deadline")) {
            end = this.parts[1];
        } else if (type.equals("event")) {
            end = this.parts[2];
        } else {
            return null;
        }
        //since date is only in numbers and '-', remove the alphabets
        //chatGPT to help with the regex
        String date = end.replaceAll("[a-zA-Z]", "").trim();

        if (date.isBlank()) {
            throw new NoEndException();
        }
        return date;
    }

    /**
     * Returns end time of task in Time
     */
    public Time readEndTime() {
        if (this.endString == null) {
            return null;
        } else {
            return new Time(this.endString);
        }
    }

    public Time getEndTime() {
        return this.endTime;
    }

    public String getType() {
        return this.type;
    }
}
