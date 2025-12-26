package Baozii;

public class Task {
    private final String name;
    private boolean done;
    private String tag;

    public Task() {
        this("");
    }
    public Task(String name) {
        this.name = name;
        done = false;
    }

    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }

    public void tag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "[" + (done ? "X" : " ") + "] " + name + (tag == null ? "" : (" #" + tag));
    }

    public String toSerial() { return name + "&" + done + (tag == null ? "" : ("#" + tag)); }

    public boolean match(String s) {
        return name.contains(s);
    }
}
