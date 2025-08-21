public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String curStatus() {
        if (isDone) {
            return "X";
        }
        else {
            return " ";
        }
    }

    public void markDone() {
        isDone = true;
    }

    public void markUndone() {
        isDone = false;
    }



    @Override
    public String toString() {
        return "[" + this.curStatus() + "] " + this.description;
    }

}
