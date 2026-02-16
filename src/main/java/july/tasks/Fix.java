package july.tasks;

import july.datetime.StringTime;
import july.error.JulyException;

public class Fix extends Task {

    protected StringTime need;

    public Fix(String description, String need) {
        super(description);
        assert need != null : "Time cannot be null";
        this.need = new StringTime(need);
    }


    public static Fix process(String argument) throws JulyException {
        assert argument != null : "Argument cannot be null";
        String[] tmp = argument.split("/need", 2);
        if (tmp.length != 2) {
            throw new JulyException("You need to have ONE '/need' in fix command");
        }
        String description = tmp[0].trim();
        String need = tmp[1].trim();
        assert !description.isEmpty() : "Description cannot be empty";
        assert !need.isEmpty() : "Time taken cannot be empty";
        return new Fix(description, need);
    }


    @Override
    public String toString() {
        return "[F]" + super.toString() + " (need: " + need + ")";
    }

    public String toSave() {
        return String.format("F %s %s /need %s", super.isDone ? "1" : "0", super.description, this.need.toSave());
    }


}
