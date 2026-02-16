package bubbles;

public class Todo extends Task{
    public Todo(String description){
        super(description);
    }

    public String typeTag(){
        return "[T]";
    }

    public String getDescription(){
        return super.toString();
    }

    @Override
    public String toString(){
        return "\t[T]" + this.getStatusIcon() + " " + getDescription();
    }
}
