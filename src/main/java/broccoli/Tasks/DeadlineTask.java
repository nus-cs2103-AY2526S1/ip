package broccoli.Tasks;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a specific deadline and time.
 * Extends the base Task class.
 */
public class DeadlineTask extends Task{
        private String dueTime;
        private String exactTime;
    //    private String description;
        private LocalDate date;

        public DeadlineTask(String description) {
            String test = description;
                if (description == null || test.trim().isEmpty()) {
                    throw new IllegalArgumentException("Please enter the name of the task!");
                }
                if (!description.contains("/by")) {
                    throw new IllegalArgumentException("Please enter the deadline!");
                }
                String[] information = description.split("/by ");
                String taskDescription = information[0];
                this.description = taskDescription;
                String[] parts = information[1].split("/");
                if (!(parts.length == 4)) {
                    throw new IllegalArgumentException("Please enter a valid due date, eg: DD/MM/YYYY/Exact Time");
                }
                this.exactTime = parts[3];
                this.dueTime = information[1];
                DateValidatorUsingDateFormat validator = new DateValidatorUsingDateFormat("dd/MM/yyyy");
                if (!validator.isValid(parts[0] + "/" + parts[1] + "/" + parts[2])) {
                    throw new IllegalArgumentException("Please enter a valid due date, eg: DD/MM/YYYY/Exact Time");
                }
                this.date = LocalDate.parse(validator.convertDateFormat(dueTime));
        }

        public DeadlineTask(String description, String dueTime, boolean isDone) {
            this.description = description;
            this.dueTime = dueTime;
            String[] parts = dueTime.split("/");
            if(!(parts.length == 4)){
                throw new IllegalArgumentException("Please enter a valid due date, eg: DD/MM/YYYY/Exact Time");
            }
            this.exactTime = parts[3];
            DateValidatorUsingDateFormat validator = new DateValidatorUsingDateFormat("dd/MM/yyyy");
            if(!validator.isValid(parts[0]+ "/" + parts[1] + "/" + parts[2]) ){
                throw new IllegalArgumentException("Please enter a valid due date, eg: DD/MM/YYYY/Exact Time");
            }
            this.date = LocalDate.parse(validator.convertDateFormat(dueTime));
            this.isDone = isDone;
        }


    /**
     * Returns the formatted text representation for file storage.
     *
     * @return The todo task formatted as a string for storage.
     */
    @Override
    public String taskText() {
        String isComplete = this.isDone ? "1" : "0";
        String taskText = "D" + " | " + isComplete + " | " + this.description + " | " + this.dueTime;
        return taskText;
    }

    @Override
        public String toString(){
            return "[D] " + getStatusIcon() + this.description + "(by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " + exactTime + ")";
        }

        public static void main(String[] args){
            Task test = new broccoli.Tasks.DeadlineTask("return book /by 03/12/2019/8pm");
            System.out.println(test.toString());
        }

    }

